/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation.Administrador;


import Logic.Profesor;
import Logic.Service;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 *
 * @author jsanchez
 */
@WebServlet(name = "AdminController", urlPatterns = {"/Presentation/GestionarG", "/Presentation/Administrador", "/Presentation/Administrador/GestionP",
"/Presentation/GestionarCursos","/Presentation/Administrador/Listar","/Presentation/Administrador/Listar/Profesores","/Presentation/Administrador/Buscar"})
public class Controller extends HttpServlet {

    protected void processRequest(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
           request.setAttribute("model", new Model());
        String viewUrl = "";
        switch (request.getServletPath()) {
            case "/Presentation/Administrador":
                viewUrl = this.show(request);
                break;
            case "/Presentation/Administrador/GestionP":
                viewUrl = this.RegistrarProfesores(request);
                break;
            case "/Presentation/GestionarCursos":
                viewUrl = this.crearCurso(request);
                break;
            case"/Presentation/Administrador/Listar":
            viewUrl=this.enviar();
           break;
            case "/Presentation/Administrador/Listar/Profesores":
                viewUrl=this.listarProfesores(request);
                break;
            case "/Presentation/Administrador/Buscar":
                //viewUrl = this.Buscar(request);
                break;
        }
        
        
        request.getRequestDispatcher(viewUrl).forward(request, response);
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

    public String RegistrarProfesores(HttpServletRequest request){
       return "/Presentation/Profesor/AgregrarProfesor";
    }
    public String show(HttpServletRequest request) {
        return this.showAction(request);
    }

    public String showAction(HttpServletRequest request) {
        return "/Presentation/Administrador/View.jsp";
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

    private String crearCurso(HttpServletRequest request) {
        return "/Presentation/Curso/Agregar";
    }

    private String enviar() {
       return "/Presentation/Curso/CambiarStatus"; //To change body of generated methods, choose Tools | Templates.
    }

    private String listarProfesores(HttpServletRequest request) {
        Model m= (Model) request.getAttribute("model");
        List<Profesor> profesores=Service.getInstance().obtenerProfesores();
        m.setProfesores(profesores);
        request.setAttribute("model", m);
        return "/Presentation/Administrador/Profesores/View.jsp";
    }
    


}
