<%-- 
    Document   : Error
    Created on : Apr 28, 2021, 11:51:40 AM
    Author     : Daniel Madrigal
--%>

<%@page import="Presentation.Profesor.Model"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%@include file = "/Presentation/head.jsp" %>
     <% Model model = (Presentation.Profesor.Model) request.getAttribute("model"); %>
    <body>
        <div>
            <h1 class='text-center text-white'>YA EXISTE UN PROFESOR CON LA CEDULA: <%=model.getCurrent().getIdProfe()%></h1>
            <h2 class="text-center text-white">NO PUEDE EXISTIR DOS PROFESORES CON LA MISMA CEDULA DE IDENTIDAD!!!</h2>
            <div class="text-center"><a href="${pageContext.request.contextPath}/Presentation/Profesor/Show">Regresar al formulario de ingreso de profesores</a></div>

        </div>



    </body>
    <%@include file = "/Presentation/footer.jsp" %>

</html>
