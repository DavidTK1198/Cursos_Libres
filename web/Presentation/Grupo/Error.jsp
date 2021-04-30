<%-- 
    Document   : Error
    Created on : Apr 30, 2021, 4:46:32 PM
    Author     : Daniel Madrigal
--%>
<%@page import="Presentation.Grupo.Model"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%@include file = "/Presentation/head.jsp" %>
     <% Model model = (Presentation.Grupo.Model) request.getAttribute("model"); %>
    <body>
        <div>
            <h1 class='text-center text-white'>NO ES POSIBLE CREAR UN GRUPO SIN PROFESOR!!!</h1>
            <h2 class="text-center text-white">INGRESAR AL MENOS UN PROFESOR ANTES DE USAR ESTA FUNCION!!</h2>
            <div class="text-center"><a href="${pageContext.request.contextPath}/Presentation/Grupo/Show">Regresar al registro de nuevo grupo</a></div>

        </div>



    </body>
    <%@include file = "/Presentation/footer.jsp" %>

</html>