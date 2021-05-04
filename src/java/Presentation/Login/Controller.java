/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation.Login;

import Logic.Curso;
import Logic.Service;
import Logic.Usuario;
import java.io.IOException;
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
 * @author jsanchez
 */
@WebServlet(name = "LoginController", urlPatterns = {"/Presentation/Login/Show", "/Presentation/Login", "/Presentation/Login/Logout", "/Presentation/Registro",
    "/Presentation/Inicio", "/Presentation/buscarcurnom"})
public class Controller extends HttpServlet {

    protected void processRequest(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        request.setAttribute("model", new Model());

        String viewUrl = "";
        switch (request.getServletPath()) {
            case "/Presentation/Login/Show":
                viewUrl = this.show(request);
                break;
            case "/Presentation/Login":
                viewUrl = this.login(request);
                break;
            case "/Presentation/Login/Logout":
                viewUrl = this.logout(request);
                break;
            case "/Presentation/Registro":
                viewUrl = this.showRegister(request);
                break;
            case "/Presentation/Inicio":
                viewUrl = this.mostrarInicio(request);
                break;
            case "/Presentation/buscarcurnom":
                viewUrl = this.Buscar(request);
                break;
        }
        request.getRequestDispatcher(viewUrl).forward(request, response);
    }

    private String login(HttpServletRequest request) {
        try {
            Map<String, String> errores = this.validar(request);
            if (errores.isEmpty()) {
                this.updateModel(request);
                return this.loginAction(request);
            } else {
                request.setAttribute("errores", errores);
                return "/Presentation/Login/View.jsp";
            }
        } catch (Exception e) {
            return "/Presentation/Error.jsp";
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

    void updateModel(HttpServletRequest request) {
        Model model = (Model) request.getAttribute("model");
        String id = request.getParameter("cedulaFld");
        int idd = Integer.parseInt(id);
        model.getCurrent().setIdUsu((idd));
        model.getCurrent().setClave(request.getParameter("claveFld"));
        request.setAttribute("model", model);
    }

    public String loginAction(HttpServletRequest request) {
        Model model = (Model) request.getAttribute("model");
        Service serv = Service.getInstance();
        HttpSession session = request.getSession(true);
        try {
            Usuario real = serv.login(model.getCurrent());
            session.setAttribute("usuario", real);
            String viewUrl = "";
            switch (real.getRol()) {
                case "1":
                    viewUrl = "/Presentation/Administrador";
                    break;
                case "2":
                    viewUrl = "/Presentation/Profesor";
                    break;
                case "3":
                    viewUrl = "/Presentation/Estudiante";
                    break;

            }
            return viewUrl;
        } catch (Exception ex) {
            Map<String, String> errores = new HashMap<>();
            request.setAttribute("errores", errores);
            errores.put("cedulaFld", "Usuario o clave incorrectos");
            errores.put("claveFld", "Usuario o clave incorrectos");
            return "/Presentation/Login/View.jsp";
        }
    }

    public String logout(HttpServletRequest request) {
        return this.logoutAction(request);
    }

    public String logoutAction(HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        session.removeAttribute("usuario");
        session.invalidate();
        return "/Presentation/Inicio";
    }

    public String show(HttpServletRequest request) {
        return this.showAction(request);
    }

    public String showAction(HttpServletRequest request) {
        Model model = (Model) request.getAttribute("model");
        model.getCurrent().setIdUsu(0);
        model.getCurrent().setClave("");
        model.getCurrent().setRol("");
        return "/Presentation/Login/View.jsp";
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

    private String showRegister(HttpServletRequest request) {
        return "/Presentation/Register/View.jsp";
    }

    private String mostrarInicio(HttpServletRequest request) {
        Model model = (Model) request.getAttribute("model");
        List<Curso> lc = Service.getInstance().buscarPorOferta();
        model.setCursos(lc);
        request.setAttribute("model", model);
        HttpSession session = request.getSession(true);
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        if (usuario != null) {
            return "/Presentation/index.jsp";
        } else {
            return "/Presentation/index.jsp?bandera=1";
        }
    }

    private String Buscar(HttpServletRequest request) {
        Model model = (Model) request.getAttribute("model");
        String atributo = request.getParameter("id");
        String valorBus = request.getParameter("busqueda");
        List<Curso> lc = null;
        switch (valorBus) {
            case "tematica":
                lc = Service.getInstance().buscar("Tematica", atributo);
                break;
            case "curso":
                lc = Service.getInstance().buscar("Curso", atributo);
                break;
        }
        model.setCursos(lc);
        request.setAttribute("model", model);
        return "/Presentation/index.jsp?bandera=1";
    }

}
