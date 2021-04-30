<%-- 
    Document   : ErrorMatriculaCurso
    Created on : Apr 30, 2021, 5:43:56 PM
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
            <h1 class='text-center text-white'>NO ES POSIBLE MATRICULAR EN EL GRUPO # <%=modell.getGrupo().getNumGrup()%></h1>
            <h2 class="text-center text-white">YA SE ENCUENTRA MATRICULADO EN EL CURSO: <%=modell.getGrupo().getCurso().getNomCur()%></h2>
            <div class="text-center"><a href="${pageContext.request.contextPath}/Presentation/Estudiante">Regresar al Inicio</a></div>

        </div>



    </body>
    <%@include file = "/Presentation/footer.jsp" %>

</html>
