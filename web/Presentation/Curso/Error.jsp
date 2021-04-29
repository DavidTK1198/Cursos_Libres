<%-- 
    Document   : Error
    Created on : Apr 28, 2021, 11:28:19 AM
    Author     : Daniel Madrigal
--%>

<%@page import="Presentation.Curso.Model"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%@include file = "/Presentation/head.jsp" %>
     <% Model model = (Presentation.Curso.Model) request.getAttribute("model"); %>
    <body>
        <div>
            <h1 class='text-center text-white'>YA EXISTE UN CURSO CON EL NRC : <%=model.getCurrent().getNrc()%></h1>
            <h2 class="text-center text-white">NO PUEDE EXISTIR DOS CURSOS CON EL MISMO NRC!!!</h2>
            <div class="text-center"><a href="${pageContext.request.contextPath}/Presentation/Curso/Show">Regresar al registro de nuevo curso</a></div>

        </div>



    </body>
    <%@include file = "/Presentation/footer.jsp" %>

</html>