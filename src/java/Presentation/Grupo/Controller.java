/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation.Grupo;

import Logic.Curso;
import Logic.Estudiante;
import Logic.Grupo;
import Logic.Profesor;
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
 * @author Daniel Madrigal
 */
@WebServlet(name = "GrupoController", urlPatterns = {"/Presentation/Grupo/AgregarGrupo", "/Presentation/Grupo/Show", "/Presentation/Grupo/Notas","/Presentation/Digitar/Notas"})
public class Controller extends HttpServlet {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("model", new Presentation.Grupo.Model());
        
        String viewUrl = "";
        switch (request.getServletPath()) {
            case "/Presentation/Grupo/AgregarGrupo":
                viewUrl = this.agregarGrupo(request);
                break;
            case "/Presentation/Grupo/Show":
                viewUrl = this.showGrupo(request);
                break;
            case "/Presentation/Grupo/Notas":
                viewUrl = this.registrarNotas(request);
                break;
            case "/Presentation/Digitar/Notas":
                viewUrl=this.actualizarHistorial(request);
            
        }
        request.getRequestDispatcher(viewUrl).forward(request, response);
    }
    
    private String agregarGrupo(HttpServletRequest request) {
        try {
            Map<String, String> errores = this.validar(request);
            if (errores.isEmpty()) {
                
                this.updateModel(request);
                Logic.Service service = Service.getInstance();
                Presentation.Grupo.Model model = (Presentation.Grupo.Model) request.getAttribute("model");
                Grupo grupito = service.buscarGrupo(model.getCurrent().getNumGrup());
                if (grupito != null) {
                    return "/Presentation/Grupos/ErrorGrupo";
                } else {
                    if(model.getCurrent().getProfesoridProfe() == null){
                        return "/Presentation/Grupo/Error.jsp";
                    }
                    service.agregarGrupo(model.getCurrent());
                    return "/Presentation/Grupo/Success.jsp";
                }
            } else {
                request.setAttribute("errores", errores);
                return "/Presentation/Grupo/Show";
            }
        } catch (Exception e) {
            return "/Presentation/Grupo/Show";
        }
       
    }
    
    Map<String, String> validar(HttpServletRequest request) {
        Map<String, String> errores = new HashMap<>();
        
        if (request.getParameter("horFld").isEmpty()) {
            errores.put("horFld", "Horario requerido");
        }
        if (request.getParameter("profFld").isEmpty()) {
            errores.put("profFld", "Profesor requerido");
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
            Presentation.Grupo.Model model = (Presentation.Grupo.Model) request.getAttribute("model");
            String horario = request.getParameter("horFld");
            model.getCurrent().setHorario(horario);
            HttpSession session = request.getSession(true);
            String nrc = (String) session.getAttribute("NRC");
            int nrcc = Integer.parseInt(nrc);
            String id = request.getParameter("profFld");
            int idProf = Integer.parseInt(id);
            
            try {
                Profesor pr = Service.getInstance().buscarProfesor(idProf);
                Curso cur = Service.getInstance().buscarCurso(nrcc);
                if (cur != null) {
                    model.getCurrent().setCurso(cur);
                }
                if (pr != null) {
                    model.getCurrent().setProfesoridProfe(pr);
                }
            } catch (Exception e) {
                return;
            }
            
        } catch (Exception e) {
            System.out.print("Tipos invalidados");
            
        }
    }
    
    private String showGrupo(HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        Model m = (Model) request.getAttribute("model");
        String nrc = request.getParameter("NRC");
        List<Profesor> profes = Service.getInstance().obtenerProfesores();
        m.setProfesores(profes);
        request.setAttribute("model", m);
        session.setAttribute("NRC", nrc);
        
        return "/Presentation/Grupo/View.jsp";
    }

    private String registrarNotas(HttpServletRequest request) {
        try {
            Model m = (Model) request.getAttribute("model");
            Grupo gr = (Grupo) request.getAttribute("Grupo");
            String nota=(String)request.getAttribute("nota");
            request.setAttribute("nota", nota);
            HttpSession session = request.getSession(true);
            Usuario usuario = (Usuario) session.getAttribute("usuario");            
            Profesor pr = Service.getInstance().buscarProfesor(usuario.getIdUsu());
            List<Estudiante> lista = Service.getInstance().obtenerEstudiantesG(gr.getNumGrup(), pr);
            this.SetListaInscripEstudiante(lista, gr);
            m.setCurrent(gr);
            m.setEstudiantes(lista);
            request.setAttribute("model", m);
            return "/Presentation/Profesor/Estudiantes/View.jsp";
            
        } catch (Exception ex) {
            return "";
        }
    }

    private String actualizarHistorial(HttpServletRequest request) {
        String nota=request.getParameter("nota");
        String grupo=request.getParameter("num_Grup");
        String isEst= request.getParameter("idest");
        int ced_Est=Integer.parseInt(isEst);
        int nFinal=Integer.parseInt(nota);
        int g=Integer.parseInt(grupo);
        try{
            Service.getInstance().ActualizarNota(ced_Est, nFinal,g);
            request.setAttribute("nota",nota);
        }catch(Exception e){
            return "/Presentation/Profesor/IngresarNotas?num_Grup="+grupo;
        }
        return "/Presentation/Profesor/IngresarNotas?num_Grup="+grupo;
    }
    private void SetListaInscripEstudiante(List<Estudiante> es,Grupo gr)throws Exception{
        for(Estudiante est:es){
            est.setInscripcion(Service.getInstance().InscripcionesPorEstudiante(est.getIdEstudiante()));
            est.eliminarInscripcion(gr.getNumGrup());
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
}
