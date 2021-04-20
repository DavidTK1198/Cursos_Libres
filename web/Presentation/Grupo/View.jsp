<%-- 
    Document   : View
    Created on : Apr 19, 2021, 10:01:31 PM
    Author     : Daniel Madrigal
--%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="Logic.Usuario"%>
<%@page import="Logic.Service"%>
<%@page import="Presentation.Grupo.Model"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <%@include file = "/Presentation/head.jsp" %>
    <main class="bg-fixed ">
        <%@include file = "/Presentation/header.jsp" %>

        <% Model model = (Presentation.Grupo.Model) request.getAttribute("model"); %>
        <% Map<String, String> errores = (Map<String, String>) request.getAttribute("errores"); %>
        <% Map<String, String[]> form = (errores == null) ? this.getForm(model) : request.getParameterMap();%>

        <div class="container-fluid container-lg container-md container-sm container-xl" id="app1">

            <form class="text-white mt-5" name="form" action="${pageContext.request.contextPath}/Presentation/Curso/AgregarGrupos" method="post" > 
                <div class="d-flex justify-content-center" >Registrar Grupo</div>
                <div class="mb-2">
                    <div>Horario</div>
                    <div><input class="form-control <%=erroneo("horFld", errores)%>" placeholder="Horario del grupo" type="text" name="horFld" value="<%=form.get("horFld")[0]%>" title="<%=title("horFld", errores)%>"></div>
                </div>
               


                <div><button class="py-2 px-4 bg-green-500 text-white font-semibold rounded-lg shadow-md hover:bg-green-700 focus:outline-none focus:ring-2 focus:ring-green-400 focus:ring-opacity-75">Registrar</button> </div>
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
       
        values.put("horFld", new String[]{model.getCurrent().getHorario()});
       

        return values;
    }
%>