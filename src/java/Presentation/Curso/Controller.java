/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation.Curso;

import Logic.Curso;
import Logic.Service;
import java.io.FileOutputStream;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.io.File;
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
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;

/**
 *
 * @author Daniel Madrigal
 */
@WebServlet(name = "CursoController", urlPatterns = {"/Presentation/Curso/Agregar", "/Presentation/Curso/AgregarGrupos",
    "/Presentation/Curso/Show","/Presentation/Curso/Imagen"})
@MultipartConfig(location ="C:/Users/Daniel Madrigal/Documents/NetBeansProjects/Cursos_Libres/web/IMG")
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

    private Map<String, String> isMultipart(HttpServletRequest request) throws FileUploadException {
        Map<String, String> errores = new HashMap<>();
        Model model = (Model) request.getAttribute("model");
        List<FileItem> items = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
        boolean isMultipart = ServletFileUpload.isMultipartContent(request);
        if (isMultipart) {
            try {

                for (FileItem item : items) {
                    if (item.isFormField()) //your code for getting form fields
                    {
                        String name = item.getFieldName();
                        String value = item.getString();
                        System.out.println(name + value);
                        switch (name) {
                            case "NRC": {
                                if (value == "") {
                                    errores.put("NRC", "NRC requerido");
                                } else {
                                    int nrc = Integer.parseInt(value);
                                    model.getCurrent().setNrc(nrc);
                                }
                            }
                            break;
                            case "nomCur": {
                                if (value == "") {
                                    errores.put("nomCur", "Nombre requerido");

                                } else {
                                    model.getCurrent().setNomCur(value);
                                }
                            }
                            break;
                            case "desCur": {
                                if (value == "") {
                                    errores.put("desCur", "Descripcion requerido");
                                } else {
                                    model.getCurrent().setDesCur(value);
                                }
                            }
                            break;
                            case "oferta": {
                                if (value == "1") {
                                    boolean oferta = true;
                                    model.getCurrent().setOferta(oferta);
                                } else {
                                    boolean oferta = false;
                                    model.getCurrent().setOferta(oferta);
                                }
                            }
                            break;
                            case "Precio": {
                                if (value == "") {

                                    errores.put("Precio", "Precio requerido");
                                } else {
                                    Float precio = Float.parseFloat(value);
                                    model.getCurrent().setPrecio(precio);
                                }
                            }
                            break;
                            case "Tematica": {
                                if (value == "") {
                                    errores.put("Tematica", "Tematica requerida");
                                } else {
                                    model.getCurrent().setTematica(value);
                                }

                            }

                        }

                    }

                    if (!item.isFormField()) {
                        File seshdir = new File("c:\\servlet");

                        if (!seshdir.exists()) {
                            seshdir.mkdirs();
                        }

                        byte[] fileBytes = item.get();
                        File file = new File(seshdir, item.getName());

                        FileOutputStream fileOutputStream = new FileOutputStream(file);
                        fileOutputStream.write(fileBytes);
                        fileOutputStream.flush();

// File (or directory) with new name
                        String nr = Integer.toString(model.getCurrent().getNrc());
                        boolean p = this.cambiarNombre(file, nr);

                    }

                }
                return errores;
            } catch (Exception ex) {
                return null;
            }

        }
        return errores;

    }

    private boolean cambiarNombre(File f, String nrc) {
        File oldName = new File(f.getPath());
        File newName = new File("c:/servlet/" + nrc + ".png");

        if (oldName.renameTo(newName)) {
            System.out.println("renamed");
            return true;
        } else {
            System.out.println("Error");
            return false;
        }
    }
     private String image(HttpServletRequest request,  HttpServletResponse response) {     
        String codigo = request.getParameter("NRC");
        Path path = FileSystems.getDefault().getPath("C:/Users/Daniel Madrigal/Documents/NetBeansProjects/Cursos_Libres/web/IMG", codigo);
        try (OutputStream out = response.getOutputStream()) {
            Files.copy(path, out);
            out.flush();
        } catch (IOException e) {
            // handle exception
        }
        return null;
    }    


}
