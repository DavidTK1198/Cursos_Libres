<%-- 
    Document   : ViewC
    Created on : Apr 20, 2021, 11:43:03 AM
    Author     : Daniel Madrigal
--%>

<%@page import="Presentation.Curso.Cursos.Model"%>
<%@page import="java.util.List"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="Logic.Curso"%>
<%@page import="Logic.Service"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <%@include file = "/Presentation/head.jsp" %>
    <main class="bg-fixed ">
        <%@include file = "/Presentation/header.jsp" %>
        <% Model m = (Model) request.getAttribute("model"); %>
        <% List<Curso> lista = m.getCursos(); %>

        <div class="container-fluid container-lg container-md container-sm container-xl bg-fixed">
            <div  class="text-white row">
                <% for (Curso c : lista) {%>
                <div class="col col-sm-8 col-md-4 col-xl-4">
                    <ul class="border border-success col-container" id="imagen">

                        <div id="imagen">
                            <img src="${pageContext.request.contextPath}/Presentation/Curso/Imagen?NRC=<%=c.getNrc()%>">
                        </div>

                        <div class = "d-flex justify-content-center bg-secondary">
                            <p id="font">Nombre: <%=c.getNomCur()%><br>
                                Tematica: <%=c.getTematica()%>
                            </p>
                        </div>
                        <li class="border border-success">
                            <div class="text-center bg-dark">
                                <b><a href="${pageContext.request.contextPath}/Presentation/MostrarG?NRC=<%=c.getNrc()%>" class="btn btn-success">Elegir Grupo</a></b>
                            </div>
                        </li>
                    </ul> 
                </div>
                <%}%>
            </div>
        </div>
    </main>
    <aside></aside>
        <%@include file = "/Presentation/footer.jsp" %>
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" Integeregrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.bundle.min.js" Integeregrity="sha384-Piv4xVNRyMGpqkS2by6br4gNJ7DXjqk09RmUpJ8jgGtD7zP9yug3goQfGII0yAns" crossorigin="anonymous"></script>
</body>
</html>
