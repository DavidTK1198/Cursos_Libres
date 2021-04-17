/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation.Curso;

import Logic.Curso;
import Logic.Service;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Daniel Madrigal
 */
@WebServlet(name = "CursoController", urlPatterns = {"/Presentation/Curso/Agregar", "/Presentation/Curso/CambiarStatus", "/Presentation/Curso/AgregarGrupos"})
public class Controller extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("model", new Model());

        String viewUrl = "";
        switch (request.getServletPath()) {
            case "/Presentation/Curso/Agregar":
                viewUrl = this.agregarCurso(request);
                break;
            case "/Presentation/Curso/CambiarStatus":
                viewUrl = this.cambiarStatus(request);
                break;
            case "/Presentation/Curso/AgregarGrupos":
                break;
           
        }
        request.getRequestDispatcher(viewUrl).forward(request, response);
    }

    private String agregarCurso(HttpServletRequest request) {
        try {
            Map<String, String> errores = this.validar(request);
            if (errores.isEmpty()) {
                this.updateModel(request);
                Logic.Service service = Service.getInstance();
                Model model = (Model) request.getAttribute("model");
                Curso cursito = service.buscarCurso(model.getCurrent().getNrc());
                if (cursito != null) {
                    return "/Presentation/Cursos/ErrorCurso";
                } else {
                    service.agregarCurso(model.getCurrent());
                    return "/Presentation/Cursos/ListarGrupos";
                }
            } else {
                request.setAttribute("errores", errores);
                return "/Presentation/Login/View.jsp";
            }
        } catch (Exception e) {
            return "/Presentation/Curso/AgregarCurso.jsp";
        }
    }

    private String cambiarStatus(HttpServletRequest request) {
        try {
            Map<String, String> errores = this.validar(request);
            if (errores.isEmpty()) {
                Model model = (Model) request.getAttribute("model");
                Curso cur = Service.getInstance().buscarCurso(model.getCurrent().getNrc());
                if (cur != null) {
                    if (cur.getOferta() == false) {
                        Service.getInstance().actualizarStatusCurso(true, cur.getNrc());
                    } else {
                        Service.getInstance().actualizarStatusCurso(false, cur.getNrc());
                    }
                    return "/Presentation/Curso/ListarGrupo.jsp";
                } else {
                    errores.put("NotFind", "Curso no encontrado");
                    request.setAttribute("errores", errores);
                    return "/Presentation/Curso/View.jsp";
                }
            } else {
                request.setAttribute("errores", errores);
                return "/Presentation/Curso/View.jsp";
            }
        } catch (Exception e) {
            return "";
        }
    }

    Map<String, String> validar(HttpServletRequest request) {
        Map<String, String> errores = new HashMap<>();
        if (request.getParameter("cedulaFld").isEmpty()) {
            errores.put("cedulaFld", "Cedula requerida");
        }

        if (request.getParameter("claveFld").isEmpty()) {
            errores.put("claveFld", "Clave requerida");
        }
        return errores;
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

    private void updateModel(HttpServletRequest request) {
        try {
            Model model = (Model) request.getAttribute("model");
            String nrc = request.getParameter("NRC");
            int nrcV = Integer.parseInt(nrc);
            String nomCur = request.getParameter("nomCur");
            String desCur = request.getParameter("desCur");
            String aux = request.getParameter("oferta");
            int ofe = Integer.parseInt(aux);
            boolean oferta = false;
            if (ofe == 1) {
                oferta = true;
            } else {
                oferta = false;
            }
            String p = request.getParameter("Precio");
            Float precio = Float.parseFloat(p);
            model.getCurrent().setNrc(nrcV);
            model.getCurrent().setNomCur(nomCur);
            model.getCurrent().setDesCur(desCur);
            model.getCurrent().setOferta(oferta);
            model.getCurrent().setPrecio(precio);
        } catch (Exception e) {
            System.out.print("Tipos invalidados");

        }
    }
    private String agregarGrupo(HttpServletRequest request){
        return "";
    }

}