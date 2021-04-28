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
import com.itextpdf.io.font.constants.StandardFonts;//hola
import com.itextpdf.io.image.ImageData;
import com.itextpdf.kernel.colors.Color;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.*;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;
import java.io.*;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.layout.property.UnitValue;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.io.FileUtils;

/**
 *
 * @author Daniel Madrigal
 */
@WebServlet(name = "RegisterController", urlPatterns = {"/Presentation/Inscripcion/Matricular", "/Presentation/MostrarG", "/Presentation/Cursoest", "/Presentation/G/Historial"})
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
            case "/Presentation/Cursoest":
                viewUrl = this.CursosEst(request);
                break;
            case "/Presentation/G/Historial":
                viewUrl = this.generarHistorial(request, response);
                break;

        }
        request.getRequestDispatcher(viewUrl).forward(request, response);
    }

    public String Matricular(HttpServletRequest request) {
        Model model = (Model) request.getAttribute("model");
        HttpSession session = request.getSession(true);
        Usuario real = (Usuario) session.getAttribute("usuario");
        if (real == null) {
            return "/Presentation/Inicio";
        }
        List<Inscripcion> listaIn = Service.getInstance().InscripcionesPorEstudiante(real.getIdUsu());
        model.setInscripciones(listaIn);
        try {
            this.UpdateModel(request, real);
            if (listaIn.isEmpty()) {

                Service.getInstance().agregarInscripcion(model.getInscrip());
                return "/Presentation/PresentarCursos";
            } else {
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

    private String generarHistorial(HttpServletRequest request, HttpServletResponse response) {
        Date date = new Date();
        String fech = new SimpleDateFormat("dd/MM/yyyy").format(date);
        try {
            HttpSession session = request.getSession(true);
            Usuario usuario = (Usuario) session.getAttribute("usuario");
            Model model = (Model) request.getAttribute("model");
            Estudiante est = Service.getInstance().buscarEstudiante(usuario.getIdUsu());
            List<Inscripcion> ls = Service.getInstance().InscripcionesPorEstudiante(usuario.getIdUsu());
            model.setInscripciones(ls);
            Grupo gr2 = null;
            Curso cursito = null;

            PdfFont font = PdfFontFactory.createFont(StandardFonts.TIMES_ROMAN);
            PdfDocument pdf = new PdfDocument(new PdfWriter(response.getOutputStream()));
            Document document = new Document(pdf, PageSize.A4);
            document.setMargins(20, 20, 20, 20);

            File imagen1 = new File("C:/AAA/images/logo.png");
            File imagen2 = new File("C:/AAA/images/imagen.png");

            FileUtils.copyFile(imagen1, imagen2);
            Service.getInstance().resize(imagen1, imagen2, 150, 150);
            ImageData data = ImageDataFactory.create("C:/AAA/images/imagen.png");
            Image img = new Image(data);
            document.add(img);
            document.add(new Paragraph("Historial Academico: ").setFont(font).setBold().setFontSize(12f).setTextAlignment(TextAlignment.RIGHT));
            document.add(new Paragraph(""));
            document.add(new Paragraph("Fecha: " + fech).setFont(font).setBold().setFontSize(12f).setTextAlignment(TextAlignment.RIGHT));
            document.add(new Paragraph("Nombre del Estudiante:    " + est.getNomEst()).setFont(font).setBold().setFontSize(12f));
            document.add(new Paragraph("Cedula del Estudiante: " + est.getIdEstudiante()).setFont(font).setBold().setFontSize(12f));
            document.add(new Paragraph("Telefono:    " + est.getTelEst()).setFont(font).setBold().setFontSize(12f));
            document.add(new Paragraph("Correo Electronico:    " + est.getCorreoEst()).setFont(font).setBold().setFontSize(12f));
            Table table = new Table(3);
            Cell c;
            Color bkg = ColorConstants.BLUE;
            Color frg = ColorConstants.WHITE;
            c = new Cell();
            c.add(new Paragraph("Nombre del curso")).setBackgroundColor(bkg).setFontColor(frg);
            table.addHeaderCell(c);
            c = new Cell();
            c.add(new Paragraph("Nota")).setBackgroundColor(bkg).setFontColor(frg);
            table.addHeaderCell(c);
            c = new Cell();
            c.add(new Paragraph("Condición")).setBackgroundColor(bkg).setFontColor(frg);
            table.addHeaderCell(c);
            for (Inscripcion s : ls) {
                gr2 = s.getGruponumGrup();
                cursito = gr2.getCurso();
                table.addHeaderCell(cursito.getNomCur());
                table.addHeaderCell(Float.toString(s.getNota()));
                table.addHeaderCell(this.determinarEstado(s.getNota()));
            }
            table.setWidth(UnitValue.createPercentValue(100));
            document.add(table);
            document.close();
            response.setContentType("application/pdf");
            response.addHeader("Content-disposition", "inline");
            return "/Presentation/Inicio";
        } catch (IOException ex) {
            System.out.println("hola");
        } catch (Exception ex) {
            System.out.println("hola");
        }
        return "/Presentation/Inicio";
    }

    public void validadUnicaInscripcion(List<Inscripcion> ls, HttpServletRequest request) throws Exception {
        Model model = (Model) request.getAttribute("model");
        Grupo gr = model.getGrupo();
        Grupo gr2 = null;
        Curso cursito2 = gr.getCurso();
        Curso cursito = null;
        for (Inscripcion s : ls) {
            gr2 = s.getGruponumGrup();
            cursito = gr2.getCurso();
            if (cursito.getNrc() == cursito2.getNrc()) {
                throw new Exception("hola");
            }
            if (gr2.getNumGrup() == gr.getNumGrup()) {
                throw new Exception("hola");
            }

        }
    }

    private String determinarEstado(float n) {

        if (n > 0.0f && n < 60.0) {
            return "Reprobado";
        }
        if (n >= 60.0 && n < 66.5) {
            return "Ampliacion";
        }
        if (n >= 67.5 && n <= 100) {
            return "Aprobado";
        }
        return "Sin calificacion asignada";

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

    private String CursosEst(HttpServletRequest request) {
        try {
            HttpSession session = request.getSession(true);
            Usuario real = (Usuario) session.getAttribute("usuario");
            Model model = (Model) request.getAttribute("model");

            int idd = real.getIdUsu();

            Estudiante pr = Service.getInstance().buscarEstudiante(idd);
            model.setCurrent(pr);
            List<Inscripcion> misIns = Service.getInstance().InscripcionesPorEstudiante(idd);
            List<Curso> misCursos = new ArrayList<>();
            Inscripcion aux = null;
            Grupo grupoAux = null;
            Curso aux2 = null;
            for (int i = 0; i < misIns.size(); i++) {
                aux = misIns.get(i);
                grupoAux = aux.getGruponumGrup();
                aux2 = grupoAux.getCurso();
                if (!misCursos.contains((Curso) aux2)) {
                    misCursos.add(aux2);
                }
            }

            if (!misCursos.isEmpty()) {
                model.setLc(misCursos);
                model.setCurrent(pr);

            } else {
                return ""; //analizar un poco mas...
            }
            request.setAttribute("model", model);
            return "/Presentation/Estudiante/Cursos/View.jsp";

        } catch (Exception ex) {
            return "";

        }
    }

}
