/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation.Profesor;

import Logic.Profesor;
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
@WebServlet(name = "ProfesorController", urlPatterns = {"/Presentation/Profesor/AgregrarProfesor","/Presentation/Profesor/Show",
"/Presentation/Profesor"})
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
                viewUrl =this.ProfesorLogin(request);
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
        Usuario us = new Usuario("2",model.getCurrent().getIdProfe(),"");
        us.generarClave();
        model.getCurrent().setUsuarioIdUsu(us);
     
    }
    private String registrarProfesor(HttpServletRequest request){
          try {
            Map<String, String> errores = this.validar(request);
            if (errores.isEmpty()) {
                this.updateModel(request);
              
                Model model = (Model) request.getAttribute("model");
                Profesor pr = Service.getInstance().buscarProfesor(model.getCurrent().getIdProfe());
                if (pr != null) {
                    return "/Presentation/Profesor/ErrorProfesor.jsp";
                } else {
                   
                    Service.getInstance().agregarUsuario(model.getCurrent().getUsuarioIdUsu());
                     Service.getInstance().agregarProfesor(model.getCurrent());
                   
                    model.reset();
                    return "/Presentation/Administrador/View.jsp";
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
        return "/Presentation/index.jsp";
    }

}
