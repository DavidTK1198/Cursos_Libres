/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation.Curso.Cursos;

import Logic.Curso;
import Logic.Service;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "CursosController", urlPatterns = {"/Presentation/Cursos/Show/", "/Presentation/Curso/CambiarStatus","/Presentation/PresentarCursos"})
public class Controller extends HttpServlet {

    protected void processRequest(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        request.setAttribute("model", new Model());

        String viewUrl = "";
        switch (request.getServletPath()) {
            case "/Presentation/Cursos/Show":
                viewUrl = this.show(request);
                break;
            case "/Presentation/Curso/CambiarStatus":
                viewUrl = this.cambiarStatus(request);
                break;
            case "/Presentation/PresentarCursos":
                viewUrl = this.ListarCursos(request);
                break;
        }
        request.getRequestDispatcher(viewUrl).forward(request, response);
    }

    public String show(HttpServletRequest request) {
        return this.showAction(request);
    }

    private String cambiarStatus(HttpServletRequest request) {
        Model model = (Model) request.getAttribute("model");
        List<Curso> c = Service.getInstance().obtenerCursos();
        model.setCursos(c);
        return "/Presentation/Curso/Cursos/View.jsp";
    }

    public String showAction(HttpServletRequest request) {
        Model model = (Model) request.getAttribute("model");
        try {
            String ss =  request.getParameter("NRC");
            int s=Integer.parseInt(ss);
            Curso cur = Service.getInstance().buscarCurso(s);
            model.setSeleccionado(cur);
            if (cur != null) {
                if (cur.getOferta() == false) {
                    Service.getInstance().actualizarStatusCurso(true, cur.getNrc());
                } else {
                    Service.getInstance().actualizarStatusCurso(false, cur.getNrc());
                }
                request.setAttribute("alertapapu", true);
                return "/Presentation/Curso/CambiarStatus";

            }
        } catch (Exception ex) {
            System.out.println("hola");
        }
        return "/Presentation/Curso/CambiarStatus";
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

    private String ListarCursos(HttpServletRequest request) {
        
        Model model = (Model) request.getAttribute("model");
        List<Curso> c = Service.getInstance().obtenerCursos();
        model.setCursos(c);
        request.setAttribute("model",model);
        return "/Presentation/Inscripcion/ViewC.jsp";
    }

}
