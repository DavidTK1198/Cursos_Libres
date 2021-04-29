<%-- 
    Document   : View
    Created on : Apr 20, 2021, 6:35:13 PM
    Author     : Daniel Madrigal
--%>

<%@page import="Logic.Curso"%>
<%@page import="Presentation.Profesor.Model"%>
<%@page import="java.util.List"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="Logic.Grupo"%>
<%@page import="Logic.Service"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <%@include file = "/Presentation/head.jsp" %>
    <main class="bg-fixed ">
        <%@include file = "/Presentation/header.jsp" %>
        <% Model m = (Presentation.Profesor.Model) request.getAttribute("model"); %>
        <% List<Curso> lista = m.getMisCursos(); %>

        <div class="container-fluid container-lg container-md container-sm container-xl">
                <h1 class="text-white justify-content-center">MIS CURSOS</h1>
                <ul  class="text-black row col-container">
                    <%if (lista.isEmpty()) {%>
                    <h2 class="text-white justify-content-center">SIN CURSOS ASIGNADOS</h2>
                    <%} else {%>
                    <% for (Curso c : lista) {%>
                    <div class="col col-sm-8 col-md-4 col-xl-4" id="col1">
                        <li class="border border-primary card">
                            <img src="${pageContext.request.contextPath}/Presentation/Curso/Imagen?NRC=<%=c.getNrc()%>">
                            <div class = "d-flex justify-content-center">
                                <p>
                                    Nombre:&nbsp;<%=c.getNomCur()%><br>
                                    NRC:&nbsp;<%=c.getNrc()%><br>
                                    Descripcion:&nbsp;<%=c.getDesCur()%><br>
                                <p>
                            </div>
                            <div class="border d-flex justify-content-center">
                               <a href="${pageContext.request.contextPath}/Presentation/Profesor/GruposMios" class="btn btn-primary">Elegir Grupo </a>
                            </div>
                        </li> 
                    </div>
                    <%}%>
                    <%}%>
                </ul>
            </div>
    </main>
    <aside></aside>
        <%@include file = "/Presentation/footer.jsp" %>
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" Integeregrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.bundle.min.js" Integeregrity="sha384-Piv4xVNRyMGpqkS2by6br4gNJ7DXjqk09RmUpJ8jgGtD7zP9yug3goQfGII0yAns" crossorigin="anonymous"></script>
</body>
  
</html>