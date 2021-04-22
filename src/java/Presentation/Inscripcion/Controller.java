/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation.Inscripcion;

import Logic.Curso;
import Logic.Estudiante;
import Logic.Grupo;
import Logic.Inscripcion;
import Logic.Service;
import Logic.Usuario;
import java.io.IOException;
import java.util.List;
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
@WebServlet(name = "RegisterController", urlPatterns = {"/Presentation/Inscripcion/Matricular", "/Presentation/MostrarG"})
public class Controller extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("model", new Model());

        String viewUrl = "";
        switch (request.getServletPath()) {
            case "/Presentation/MostrarG":
                viewUrl = this.show(request);
                break;
            case "/Presentation/Inscripcion/Matricular":
                viewUrl = this.Matricular(request);
                break;

        }
        request.getRequestDispatcher(viewUrl).forward(request, response);
    }

    public String Matricular(HttpServletRequest request) {
        Model model = (Model) request.getAttribute("model");
        HttpSession session = request.getSession(true);
        Usuario real = (Usuario) session.getAttribute("usuario");
        List<Inscripcion> listaIn = Service.getInstance().InscripcionesPorEstudiante(real.getIdUsu());
        model.setInscripciones(listaIn);
        try {
             this.UpdateModel(request, real);
            if (listaIn.isEmpty()) {
               
                Service.getInstance().agregarInscripcion(model.getInscrip());
                return "/Presentation/PresentarCursos";
            }else{
                this.validadUnicaInscripcion(listaIn, request);
                Service.getInstance().agregarInscripcion(model.getInscrip());
                return "/Presentation/PresentarCursos";
            }

        } catch (Exception ex) {
            boolean bandera = false;
            request.setAttribute("bandera", bandera);
            return "/Presentation/PresentarCursos";//verificar despues
        }
    }

    public String show(HttpServletRequest request) {
        return this.showAction(request);
    }

    public void UpdateModel(HttpServletRequest request, Usuario real) throws Exception {
        Model model = (Model) request.getAttribute("model");
        int idEs = real.getIdUsu();
        Estudiante est = Service.getInstance().buscarEstudiante(idEs);
        est.setUsuarioIdUsu(real);
        String n = (String) request.getParameter("num_Grup");
        int numGrupo = Integer.parseInt(n);
        Grupo grupito = Service.getInstance().buscarGrupo(numGrupo);
        Inscripcion insc = new Inscripcion();
        insc.setEstudiante(est);
        insc.setGruponumGrup(grupito);
        model.setInscrip(insc);
        request.setAttribute("model", model);
    }

    public String showAction(HttpServletRequest request) {

        Model model = (Model) request.getAttribute("model");
        String nrc = (String) request.getParameter("NRC");
        int nr = Integer.parseInt(nrc);
        List<Grupo> grupos = Service.getInstance().obtenerGrupoPorCurso(nr);
        model.setGrupito(grupos);
        return "/Presentation/Grupo/Grupos/View.jsp";
    }
    public void validadUnicaInscripcion(List<Inscripcion> ls,HttpServletRequest request) throws Exception{
        Model model = (Model) request.getAttribute("model");
        Grupo gr = model.getGrupo();
        Grupo gr2 = null;
        Curso cursito2 = gr.getCurso();
        Curso cursito = null;
        for(Inscripcion s: ls){
             gr2 = s.getGruponumGrup();
             cursito = gr2.getCurso();
             if(cursito.getNrc()==cursito2.getNrc()){
                 throw new Exception("hola");
             }
             if(gr2.getNumGrup() == gr.getNumGrup()){
                  throw new Exception("hola");
             }
             
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

}
