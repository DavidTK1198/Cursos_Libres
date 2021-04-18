<%-- 
    Document   : View
    Created on : Apr 17, 2021, 3:20:43 PM
    Author     : Daniel Madrigal
--%>

<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="Logic.Usuario"%>
<%@page import="Logic.Service"%>
<%@page import="Presentation.Profesor.Model"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <%@include file = "/Presentation/head.jsp" %>
    <main>
        <%@include file = "/Presentation/header.jsp" %>
        <% Model model = (Model) request.getAttribute("model"); %>
        <% Map<String, String> errores = (Map<String, String>) request.getAttribute("errores"); %>
        <% Map<String, String[]> form = (errores == null) ? this.getForm(model) : request.getParameterMap();%>

        <div class="container-fluid container-lg container-md container-sm container-xl" id="app1">

            <form name="form" action="${pageContext.request.contextPath}/Presentation/Administrador/GestionP" method="post" > 
                <div class="panel" style="width:30%;">
                    <div class="fila encabezado">Registrar Profesor</div>
                    <div class="fila">
                        <div class="etiqueta">Cedula</div>
                        <div class="campo"><input class="<%=erroneo("cedulaFld", errores)%>" placeholder="Cedula del Profesor" type="text" name="cedulaFld" value="<%=form.get("cedulaFld")[0]%>" title="<%=title("cedulaFld", errores)%>"></div>
                    </div>
                    <div class="fila">
                        <div class="etiqueta">Nombre</div>
                        <div class="campo"><input class="<%=erroneo("NombreFld", errores)%>" placeholder="Nombre del Profesor" type="text" name="NombreFld" value="<%=form.get("NombreFld")[0]%>" title="<%=title("cedulaFld", errores)%>"></div>
                    </div>
                    <div class="fila">
                        <div class="etiqueta">Telefono</div>
                        <div class="campo"><input class="<%=erroneo("telFld", errores)%>" placeholder="Telefono del Profesor" type="text" name="telFld" value="<%=form.get("telFld")[0]%>" title="<%=title("cedulaFld", errores)%>"></div>
                    </div>
                    <div class="fila">
                        <div class="etiqueta">Correo electronico</div>
                        <div class="campo"><input class="<%=erroneo("correoFld", errores)%>" placeholder="Correo del Profesor" type="text" name="correoFld" value="<%=form.get("correoFld")[0]%>" title="<%=title("correoFld", errores)%>"></div>
                    </div>
                    <div class="fila">
                        <div class="etiqueta">Especialidad</div>
                        <div class="campo"><input class="<%=erroneo("espFld", errores)%>" placeholder="Especialidad del Profesor" type="text" name="espFld" value="<%=form.get("espFld")[0]%>" title="<%=title("espFld", errores)%>"></div>
                    </div>

                    <div class="fila encabezado"><button  style="margin-bottom: 15px">Registrar</button> </div>
                </div>
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
        String n = Integer.toString(model.getCurrent().getIdProfe());
        values.put("cedulaFld", new String[]{n});
        values.put("NombreFld", new String[]{model.getCurrent().getNomProfe()});
        values.put("telFld", new String[]{model.getCurrent().getTelProfe()});
        values.put("correoFld", new String[]{model.getCurrent().getCorreoProfe()});
        values.put("espFld", new String[]{model.getCurrent().getEspecialidad()});

        return values;
    }
%>