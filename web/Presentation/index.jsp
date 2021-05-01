<%-- 
    Document   : index
    Created on : 09/04/2021, 07:43:13 PM
    Author     : DavidTK1198
--%>

<%@page import="Logic.Curso"%>
<%@page import="java.util.List"%>
<%@page import="Presentation.Login.Model"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="Logic.Service"%>
<%@page import="Logic.Usuario"%>

<!DOCTYPE html>
<html>
    <%@include file = "/Presentation/head.jsp" %>

    <body class="bg-image text-white">
        <div class="mask" style="background-color: rgba(0, 0, 0, 0.6)">
    <div class="d-flex justify-content-center align-items-center h-100">
    </div>
  </div>
        <main>
            <%@include file = "/Presentation/header.jsp" %>
            <%if (usuario == null) {%>

            <% Model m = (Model) request.getAttribute("model"); %>
            <% List<Curso> lista = m.getCursos(); %>
            <div class="container-fluid container-lg container-md container-sm container-xl">
                <h1>Cursos en Oferta</h1>
                <ul  class="text-black row col-container">
                    <%if (lista.isEmpty()) {%>
                    <h2 class="text-white">SIN CURSOS DESTACADOS</h2>
                    <%} else {%>
                    <% for (Curso c : lista) {%>
                    <div class="col col-sm-8 col-md-4 col-xl-4 d-flex justify-content-center">
                        <li class="border border-primary card">
                            <div id="imagen">
                            <img src="${pageContext.request.contextPath}/Presentation/Curso/Imagen?NRC=<%=c.getNrc()%>">
                            </div>
                            <div class = "d-flex justify-content-center">
                                <p>
                                    Nombre:&nbsp;<%=c.getNomCur()%><br>
                                    NRC:&nbsp;<%=c.getNrc()%><br>
                                    Descripcion:&nbsp;<%=c.getDesCur()%><br>
                                <p>
                            </div>
                            <div class="border d-flex justify-content-center">
                                <a href="${pageContext.request.contextPath}/Presentation/Login/Show" class="btn btn-primary">Matricular</a>
                            </div>
                        </li> 
                    </div>
                    <%}%>
                    <%}%>
                </ul>
            </div>

            <%} else {%>

            <%}%>
        </main>

        <aside></aside>
            <%@include file = "/Presentation/footer.jsp" %>
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" Integeregrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.bundle.min.js" Integeregrity="sha384-Piv4xVNRyMGpqkS2by6br4gNJ7DXjqk09RmUpJ8jgGtD7zP9yug3goQfGII0yAns" crossorigin="anonymous"></script>
    </body>
</html>
