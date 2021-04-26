/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation.Estudiante;

import Logic.Estudiante;
import Logic.Usuario;
import Logic.Service;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "EstudianteController", urlPatterns = {"/Presentation/Estudiante/AgregrarEstudiante","/Presentation/Estudiante/Matricular","/Presentation/Estudiante/VInscrip",
"/Presentation/Estudiante/Show","/Presentation/Estudiante"})
public class Controller extends HttpServlet {


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("model", new Model());

        String viewUrl = "";
        switch (request.getServletPath()) {
            case "/Presentation/Estudiante/AgregrarEstudiante":
                viewUrl = this.registrarEstudiante(request);
                break;
          
            case "/Presentation/Estudiante/Matricular":
                viewUrl = this.matricular(request);
                break;
            case "/Presentation/Estudiante/VInscrip":
                
                break;
            case "/Presentation/Estudiante/Show":
                viewUrl = this.show(request);
                break;
            case "/Presentation/Estudiante":
                viewUrl = this.paginaPrincipal(request);
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
        return errores;
    }

    void updateModel(HttpServletRequest request) {
        Presentation.Estudiante.Model model = (Presentation.Estudiante.Model) request.getAttribute("model");
        String id = request.getParameter("cedulaFld");
        int idd = Integer.parseInt(id);
        model.getCurrent().setIdEstudiante(idd);
        model.getCurrent().setNomEst(request.getParameter("NombreFld"));
        model.getCurrent().setTelEst(request.getParameter("telFld"));
        model.getCurrent().setCorreoEst(request.getParameter("correoFld"));
        Usuario us = new Usuario("3",model.getCurrent().getIdEstudiante(),"");
        us.generarClave();
        model.getCurrent().setUsuarioIdUsu(us);
     
    }
    private String registrarEstudiante(HttpServletRequest request){
          try {
            Map<String, String> errores = this.validar(request);
            if (errores.isEmpty()) {
                this.updateModel(request);
              
                Model model = (Model) request.getAttribute("model");
                Estudiante pr = Service.getInstance().buscarEstudiante(model.getCurrent().getIdEstudiante());
                if (pr != null) {
                    return "/Presentation/Estudiante/ErrorEstudiante.jsp";
                } else {
                   
                    Service.getInstance().agregarUsuario(model.getCurrent().getUsuarioIdUsu());
                     Service.getInstance().agregarEstudiante(model.getCurrent());
                   
                   
                    return "/Presentation/Login/Show";
                }
            } else {
                request.setAttribute("errores", errores);
                return "/Presentation/Estudiante/View.jsp";
            }
        } catch (Exception e) {
            return "/Presentation/Estudiante/View.jsp";
        }
    }
     private String show(HttpServletRequest request) {
         return "/Presentation/Estudiante/View.jsp";
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

    private String paginaPrincipal(HttpServletRequest request) {
        return "/Presentation/Cursoest";
    }

    private String matricular(HttpServletRequest request) {
        
        return "/Presentation/PresentarCursos";
    }

   

}
