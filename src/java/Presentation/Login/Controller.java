/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation.Login;

import Logic.Service;
import Logic.Usuario;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Escinf
 */
@WebServlet(name = "Controller", urlPatterns = {"/Presentation/Login", "/presentation/Register"})
public class Controller extends HttpServlet {

    protected void processRequest(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException, Exception {
        HttpSession session = request.getSession(true);
        
        request.setAttribute("model", new Model());
        String urlView = "";
        switch (request.getServletPath()) {
            case "/presentation/Register":
                urlView = "xd";
                break;
            case "/Presentation/Login": {
                Usuario us = new Usuario("1", 111, "David");
                us = Service.getInstance().login(us);
                if (us != null) {
                    
                    session.setAttribute("usuario", us);
                    urlView = "/index.jsp";
                }else{
                    urlView = "/index.jsp";
                }
                //urlView = "/footer.jsp";

            }
            break;

        }
        request.getRequestDispatcher(urlView).forward(request, response);

    }

    private String getRestriccion(String placa) {
        if (placa.length() == 0) {
            return "Sin restriccion";
        }
        char d = placa.charAt(placa.length() - 1);
        switch (d) {
            case '1':
            case '2':
                return "Presentation/Lunes";
            case '3':
            case '4':
                return "Martes";
            default:
                return "Sin restriccion";
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
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
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

}
