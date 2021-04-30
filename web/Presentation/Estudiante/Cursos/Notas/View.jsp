<%-- 
    Document   : ViewECert
    Created on : Apr 30, 2021, 4:07:10 PM
    Author     : Daniel Madrigal
--%>

<%@page import="Presentation.Estudiante.Model"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%@include file = "/Presentation/head.jsp" %>
    <body>
        <div>
            <h1 class='text-center text-white'>NO SE PUDO GENERAR CERTIFICADO!!!</h1>
             <h2 class='text-center text-white'>LA NOTA DEL ESTUDIANTE DEBE SER 70 O SUPERIOR!!</h1>
            <div class="text-center text-white"><a href="${pageContext.request.contextPath}/Presentation/Cursoest">Regresar a inicio</a>

        </div>
    </body>
    <%@include file = "/Presentation/footer.jsp" %>

</html>
