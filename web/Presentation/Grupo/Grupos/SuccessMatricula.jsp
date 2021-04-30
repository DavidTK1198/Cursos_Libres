<%-- 
    Document   : SuccessMatricula
    Created on : Apr 30, 2021, 5:16:25 PM
    Author     : Daniel Madrigal
--%>

<%@page import="Presentation.Inscripcion.Model"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%@include file = "/Presentation/head.jsp" %>
     <% Model modell = (Presentation.Inscripcion.Model)request.getAttribute("model"); %>
    <body>
        <div>
            <h1 class='text-center text-white'>MATRICULA EXITOSA EN EL GRUPO # <%=modell.getGrupo().getNumGrup()%> DEL CURSO <%=modell.getGrupo().getCurso().getNomCur()%></h1>
            
            <div class="text-center"><a href="${pageContext.request.contextPath}/Presentation/PresentarCursos">Regresar al Inicio</a></div>

        </div>



    </body>
    <%@include file = "/Presentation/footer.jsp" %>

</html>