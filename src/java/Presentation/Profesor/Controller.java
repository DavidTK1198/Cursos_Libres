/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation.Profesor;

import Logic.Curso;
import Logic.Grupo;
import Logic.Profesor;
import Logic.Usuario;
import Logic.Service;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Daniel Madrigal
 */
@WebServlet(name = "ProfesorController", urlPatterns = {"/Presentation/Profesor/AgregrarProfesor", "/Presentation/Profesor/Show",
    "/Presentation/Profesor", "/Presentation/Profesor/GruposMios", "/Presentation/Profesor/CursosMios", "/Presentation/Profesor/IngresarNotas"})
public class Controller extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("model", new Model());

        String viewUrl = "";
        switch (request.getServletPath()) {
            case "/Presentation/Profesor/AgregrarProfesor":
                viewUrl = this.registrarProfesor(request);
                break;
            case "/Presentation/Profesor/Show":
                viewUrl = this.showProfesor(request);
                break;
            case "/Presentation/Profesor":
                viewUrl = this.ProfesorLogin(request);
                break;
            case "/Presentation/Profesor/GruposMios":

                viewUrl = this.misGrupos(request);
                break;
            case "/Presentation/Profesor/CursosMios":
                viewUrl = this.misCursos(request);
                break;
            case "/Presentation/Profesor/IngresarNotas":
                viewUrl = this.RegistrarNotas(request);
                break;

        }
        request.getRequestDispatcher(viewUrl).forward(request, response);
    }

    Map<String, String> validar(HttpServletRequest request) {
        Map<String, String> errores = new HashMap<>();
        if (request.getParameter("cedulaFld").isEmpty()) {
            errores.put("cedulaFld", "Cedula requerida");
        }
        if (request.getParameter("NombreFld").isEmpty()) {
            errores.put("NombreFld", "Nombre requerido");
        }
        if (request.getParameter("telFld").isEmpty()) {
            errores.put("telFld", "Telefono requerido");
        }
        if (request.getParameter("correoFld").isEmpty()) {
            errores.put("correoFld", "Correo requerido");
        }
        if (request.getParameter("espFld").isEmpty()) {
            errores.put("espFld", "Especialidad requerida");
        }
        return errores;
    }

    void updateModel(HttpServletRequest request) {
        Presentation.Profesor.Model model = (Presentation.Profesor.Model) request.getAttribute("model");
        String id = request.getParameter("cedulaFld");
        int idd = Integer.parseInt(id);
        model.getCurrent().setIdProfe(idd);
        model.getCurrent().setNomProfe(request.getParameter("NombreFld"));
        model.getCurrent().setTelProfe(request.getParameter("telFld"));
        model.getCurrent().setCorreoProfe(request.getParameter("correoFld"));
        model.getCurrent().setEspecialidad(request.getParameter("espFld"));
        Usuario us = new Usuario("2", model.getCurrent().getIdProfe(), "");
        us.generarClave();
        model.getCurrent().setUsuarioIdUsu(us);

    }

    private String registrarProfesor(HttpServletRequest request) {
        try {
            Map<String, String> errores = this.validar(request);
            if (errores.isEmpty()) {
                this.updateModel(request);

                Model model = (Model) request.getAttribute("model");
                Profesor pr = Service.getInstance().buscarProfesor(model.getCurrent().getIdProfe());
                if (pr != null) {
                    request.setAttribute("model", model);
                    return "/Presentation/Profesor/Error.jsp";
                } else {

                    Service.getInstance().agregarUsuario(model.getCurrent().getUsuarioIdUsu());
                    Service.getInstance().agregarProfesor(model.getCurrent());
                    request.setAttribute("model", model);
                    return "/Presentation/Profesor/Success.jsp";
                }
            } else {
                request.setAttribute("errores", errores);
                return "/Presentation/Register/View.jsp";
            }
        } catch (Exception e) {
            return "/Presentation/Register/View.jsp";
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private String showProfesor(HttpServletRequest request) {
        return "/Presentation/Profesor/View.jsp";
    }

    private String ProfesorLogin(HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        String id = Integer.toString(usuario.getIdUsu());
        return "/Presentation/Profesor/CursosMios?idprof=" + id;
    }

    private String misGrupos(HttpServletRequest request) {
        try {
            Model model = (Model) request.getAttribute("model");
            HttpSession session = request.getSession(true);
            Usuario usuario = (Usuario) session.getAttribute("usuario");
            Profesor pr = Service.getInstance().buscarProfesor(usuario.getIdUsu());
            model.setCurrent(pr);
            List<Grupo> misGrupos = Service.getInstance().obtenerGrupoPorProfesor(pr.getIdProfe());
            model.setMios(misGrupos);
            request.setAttribute("model", model);
            return "/Presentation/Profesor/Grupos/View.jsp";
        } catch (Exception ex) {
            return "";

        }

    }

    private String misCursos(HttpServletRequest request) {
        try {
            Model model = (Model) request.getAttribute("model");
            String id = (String) request.getParameter("idprof");
            int idd = Integer.parseInt(id);

            Profesor pr = Service.getInstance().buscarProfesor(idd);
            model.setCurrent(pr);
            List<Grupo> misGrupos = Service.getInstance().obtenerGrupoPorProfesor(idd);
            List<Curso> misCursos = new ArrayList<>();
            Grupo aux = null;
            Curso aux2 = null;
            for (int i = 0; i < misGrupos.size(); i++) {
                aux = misGrupos.get(i);
                aux2 = aux.getCurso();
                if (!misCursos.contains((Curso) aux2)) {
                    misCursos.add(aux2);
                }
            }

            if (!misCursos.isEmpty()) {
                model.setMisCursos(misCursos);

            } else {
                return ""; //analizar un poco mas...
            }
            request.setAttribute("model", model);
            return "/Presentation/Profesor/Cursos/View.jsp";

        } catch (Exception ex) {
            return "";

        }

    }

    private String RegistrarNotas(HttpServletRequest request) {
        try {

            String num = (String) request.getParameter("num_Grup");
            String n = (String)request.getAttribute("nota");
            request.setAttribute("Nota", n);
            int numerG = Integer.parseInt(num);
            Grupo gr = Service.getInstance().buscarGrupo(numerG);
            request.setAttribute("Grupo", gr);
            return "/Presentation/Grupo/Notas";

        } catch (Exception ex) {
            return "";

        }
    }
}
