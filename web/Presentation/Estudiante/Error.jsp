<%-- 
    Document   : Error
    Created on : Apr 27, 2021, 12:45:24 PM
    Author     : Daniel Madrigal
--%>

<%@page import="Presentation.Estudiante.Model"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%@include file = "/Presentation/head.jsp" %>
     <% Model model = (Model) request.getAttribute("model"); %>
    <body>
        <div>
            <h1 class='text-center'>YA EXISTE UN ESTUDIANTE CON LA CEDULA: <%=model.getCurrent().getIdEstudiante()%></h1>
            <div class="text-center"><a href="${pageContext.request.contextPath}/Presentation/Estudiante/Show">Regresar al registro</a></div>

        </div>



    </body>
    <%@include file = "/Presentation/footer.jsp" %>

</html>
