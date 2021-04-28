/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation.Curso;

import Logic.Curso;
import Logic.Grupo;
import Logic.Inscripcion;
import Logic.Service;
import Logic.Usuario;

import java.util.HashMap;
import java.util.Map;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import java.io.File;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.apache.commons.io.FileUtils;

/**
 *
 * @author Daniel Madrigal
 */
@WebServlet(name = "CursoController", urlPatterns = {"/Presentation/Curso/Agregar", "/Presentation/Curso/AgregarGrupos",
    "/Presentation/Curso/Show", "/Presentation/Curso/Imagen", "/Presentation/Curso/Pdf"})
@MultipartConfig(location = "C:/AAA/images")
public class Controller extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String p = request.getParameter("NRC");
        request.setAttribute("model", new Model());

        String viewUrl = "";
        switch (request.getServletPath()) {
            case "/Presentation/Curso/Agregar":
                viewUrl = this.agregarCurso(request);
                break;
            case "/Presentation/Curso/AgregarGrupos":
                viewUrl = this.agregarGrupo(request);
                break;
            case "/Presentation/Curso/Show":
                viewUrl = this.showCurso(request);
                break;
            case "/Presentation/Curso/Imagen":
                viewUrl = this.image(request, response);
                break;
            case "/Presentation/Curso/Pdf":
                viewUrl = this.print(request, response);
                break;

        }
        request.getRequestDispatcher(viewUrl).forward(request, response);
    }

    private String agregarCurso(HttpServletRequest request) throws IOException, ServletException {
        Model model = (Model) request.getAttribute("model");
        Map<String, String> errores = this.validar(request);
        final Part imagen;
        try {
            if (errores.isEmpty()) {
                this.updateModel(request);
                Logic.Service service = Service.getInstance();

                Curso cursito = service.buscarCurso(model.getCurrent().getNrc());
                imagen = request.getPart("imagen");
                imagen.write(Integer.toString(model.getCurrent().getNrc()));
                if (cursito != null) {
                    return "/Presentation/Cursos/ErrorCurso";
                } else {
                    service.agregarCurso(model.getCurrent());
                    String s = Integer.toString(model.getCurrent().getNrc());
                    // imagen.write(s);
                    return "/Presentation/Administrador/View.jsp";
                }
            } else {
                request.setAttribute("errores", errores);
                return "/Presentation/Curso/View.jsp";
            }
        } catch (Exception e) {
            return "/Presentation/Curso/View.jsp";
        }
    }

    Map<String, String> validar(HttpServletRequest request) {
        Map<String, String> errores = new HashMap<>();

        String n = request.getParameter("nomCur");
        if (request.getParameter("nomCur").isEmpty()) {
            errores.put("nomCur", "Nombre requerido");
        }
        if (request.getParameter("desCur").isEmpty()) {
            errores.put("desCur", "Descripcion requerido");
        }
        if (request.getParameter("Precio").isEmpty()) {
            errores.put("Precio", "Precio requerido");
        }
        if (request.getParameter("NRC").isEmpty()) {
            errores.put("NRC", "NRC requerido");
        }
        if (request.getParameter("Tematica").isEmpty()) {
            errores.put("Tematica", "Tematica requerida");
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
            String tematica = request.getParameter("Tematica");
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
            model.getCurrent().setTematica(tematica);
        } catch (Exception e) {
            System.out.print("Tipos invalidados");

        }
    }

    private String agregarGrupo(HttpServletRequest request) {

        return "/Presentation/Grupo/AgregarGrupo";
    }

    private String showCurso(HttpServletRequest request) {
        return "/Presentation/Curso/View.jsp";
    }

    private String image(HttpServletRequest request, HttpServletResponse response) {
        String codigo = request.getParameter("NRC");
        Path path = FileSystems.getDefault().getPath("C:/AAA/images", codigo);
        try (OutputStream out = response.getOutputStream()) {
            Files.copy(path, out);
            out.flush();
        } catch (IOException e) {
            // handle exception
        }
        return null;
    }

    private String print(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String codigo = request.getParameter("NRC");

        int cod = Integer.parseInt(codigo);
        Curso curso;
        try {
            curso = Service.getInstance().buscarCurso(cod);
            Inscripcion ins = this.inscripcionEst(request, cod);
            if (ins.getNota() >= 70.0f) {
                File imagen1 = new File("C:/AAA/images/logo.png");
                File imagen2 = new File("C:/AAA/images/imagen.png");
                FileUtils.copyFile(imagen1, imagen2);
                Service.getInstance().resize(imagen1, imagen2, 150, 150);
                ImageData data = ImageDataFactory.create("C:/AAA/images/imagen.png");
                PdfDocument pdf = new PdfDocument(new PdfWriter(response.getOutputStream()));
                Document doc = new Document(pdf, PageSize.A4.rotate());
                PdfFont font = PdfFontFactory.createFont(StandardFonts.HELVETICA);
                Image img = new Image(data);
                doc.add(img);
                doc.add(new Paragraph("CURSO: " + curso.getNomCur()));
                doc.add(new Paragraph("NRC: " + curso.getNrc()));
                doc.add(new Paragraph("Descripcion del curso: " + curso.getDesCur()));

                doc.add(new Paragraph("Grupo matriculado: " + ins.getGruponumGrup().getNumGrup()));
                doc.add(new Paragraph("Nota del curso: " + ins.getNota()));
                doc.add(new Paragraph("ESTADO DEL CURSO: " + this.determinarEstado(ins.getNota())));
                doc.close();
                response.setContentType("application/pdf");
                response.addHeader("Content-disposition", "inline");
            } else {
                return "/Presentation/Inicio/Error";
            }
            return "/Presentation/Inicio";
        } catch (Exception ex) {
            return "/presentation/Error.jsp";
        }
    }

    private Inscripcion inscripcionEst(HttpServletRequest request, int nrc) {

        HttpSession session = request.getSession(true);
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        List<Inscripcion> lista = Service.getInstance().InscripcionesPorEstudiante(usuario.getIdUsu());
        Grupo grup;
        if (!lista.isEmpty()) {
            for (Inscripcion inss : lista) {
                grup = inss.getGruponumGrup();
                if (grup.getCurso().getNrc() == nrc) {
                    return inss;
                }
            }
            return null;
        }
        return null;
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

}
