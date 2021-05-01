<%-- 
    Document   : View
    Created on : Apr 18, 2021, 2:30:44 PM
    Author     : Daniel Madrigal
--%>


<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="Logic.Usuario"%>
<%@page import="Logic.Service"%>
<%@page import="Presentation.Curso.Model"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <%@include file = "/Presentation/head.jsp" %>
    <main class="bg-fixed ">
        <%@include file = "/Presentation/header.jsp" %>
        <% Model model = (Model) request.getAttribute("model"); %>
        <% Map<String, String> errores = (Map<String, String>) request.getAttribute("errores"); %>
        <% Map<String, String[]> form = (errores == null) ? this.getForm(model) : request.getParameterMap();%>

        <div class="container-fluid container-lg container-md container-sm container-xl">

            <form class="text-white mt-5" enctype="multipart/form-data" action="${pageContext.request.contextPath}/Presentation/Curso/Agregar" method="POST" > 
                <div class="d-flex justify-content-center" >Registrar Curso</div>
                <div class="mb-2">
                    <div>NRC</div>
                    <div><input class="form-control <%=erroneo("NRC", errores)%>" placeholder="NRC del curso" type="text" name="NRC" value="<%=form.get("NRC")[0]%>" title="<%=title("NRC", errores)%>"></div>
                </div>
                <div class="mb-2">
                    <div>Nombre</div>
                    <div class=><input class="form-control <%=erroneo("nomCur", errores)%>" placeholder="Nombre del Curso" type="text" name="nomCur" value="<%=form.get("nomCur")[0]%>" title="<%=title("nomCur", errores)%>"></div>
                </div>
                <div class="mb-2">
                    <div>Descripcion</div>
                    <div><input class="form-control <%=erroneo("desCur", errores)%>" placeholder="Descripcion del curso" type="text" name="desCur" value="<%=form.get("desCur")[0]%>" title="<%=title("desCur", errores)%>"></div>
                </div>
                <div class="mb-2">
                    <div class="flex-container">
                        <label>Oferta</label>
                        <div id="flex">
                            <div class="flex-item">
                                <input type="radio" name="oferta" value="1" checked>Curso destacado
                            </div>
                        </div>

                        <div id="flex">
                            <div class="flex-item">
                                <input type="radio" name="oferta" value="0">Curso normal
                            </div>
                        </div>
                    </div>
                </div>
                <div class="mb-2">
                    <div>Precio del curso</div>
                    <div><input class="form-control <%=erroneo("Precio", errores)%>" placeholder="Precio del curso" type="number" name="Precio" value="<%=form.get("Precio")[0]%>" title="<%=title("Precio", errores)%>"></div>
                </div>
                <div class="mb-2">
                    <div>Tematica del curso</div>
                    <div><input class="form-control <%=erroneo("Tematica", errores)%>" placeholder="Tematica del curso" type="text" name="Tematica" value="<%=form.get("Tematica")[0]%>" title="<%=title("Tematica", errores)%>"></div>
                </div>
                <div class="mb-2">Imagen del Curso
                    <input type="file" name="imagen" accept="image/*" required></div>
                <div><input type="submit"  class="py-2 px-4 bg-green-500 text-white font-semibold rounded-lg shadow-md hover:bg-green-700 focus:outline-none focus:ring-2 focus:ring-green-400 focus:ring-opacity-75" value="Agregar"></div>
            </form>  
        </div>
    </main>

    <aside></aside>
        <%@include file = "/Presentation/footer.jsp" %>
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" Integeregrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.bundle.min.js" Integeregrity="sha384-Piv4xVNRyMGpqkS2by6br4gNJ7DXjqk09RmUpJ8jgGtD7zP9yug3goQfGII0yAns" crossorigin="anonymous"></script>
</body>
</html>
<%!
    private String erroneo(String campo, Map<String, String> errores) {
        if ((errores != null) && (errores.get(campo) != null)) {
            return "is-invalid";
        } else {
            return "";
        }
    }

    private String title(String campo, Map<String, String> errores) {
        if ((errores != null) && (errores.get(campo) != null)) {
            return errores.get(campo);
        } else {
            return "";
        }
    }

    private Map<String, String[]> getForm(Model model) {
        Map<String, String[]> values = new HashMap<>();
        String n = Integer.toString(model.getCurrent().getNrc());
        values.put("NRC", new String[]{n});
        values.put("nomCur", new String[]{model.getCurrent().getNomCur()});
        values.put("desCur", new String[]{model.getCurrent().getDesCur()});
        String m = Float.toString(model.getCurrent().getPrecio());
        values.put("Precio", new String[]{m});
        values.put("Tematica", new String[]{model.getCurrent().getTematica()});

        return values;
    }
%>