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

        <div class="mb-5 justify-content-center text-center mt-5">
            <form name="form" action="${pageContext.request.contextPath}/Presentation/Inscripcion/Buscar" method="post" > 
                <div class="select text-black pr-5"> <p class="text-white mb-0">Filtro</p>
                    <select name="busqueda">
                        <option value ="tematica">Tematica</option>
                        <option value="curso" selected="">Curso</option>
                    </select>
                </div>

                <input class="form text-black" type="search" placeholder="Buscar Curso" aria-label="Buscar" name ="id" >
                <button class="btn btn-secondary" type="submit">Buscar</button>
            </form>
        </div>
        <div class="container-fluid container-lg container-md container-sm container-xl ">



            <div class="text-white row justify-content-center">

                <% for (Curso c : lista) {%>
                <div class="col col-sm-8 col-md-4 col-xl-4 mb-5">

                    <div class="card">
                        <div class="embed-responsive embed-responsive-16by9" id="zoom">
                            <img src="${pageContext.request.contextPath}/Presentation/Curso/Imagen?NRC=<%=c.getNrc()%>" class="card-img-top embed-responsive-item" alt="...">
                        </div>

                        <div class="card-body border">
                            <h5 class="card-title"> Nombre:&nbsp;<%=c.getNomCur()%></h5>
                            <p class="card-text text-center text-black">
                                NRC:&nbsp;<%=c.getNrc()%>
                                <br>
                                Descripcion:&nbsp;<%=c.getDesCur()%>
                                <br>
                                Tematica:&nbsp;<%=c.getTematica()%>
                                <br>
                            </p>
                            <div class="text-center bg-light d-flex justify-content-center">
                                <b><a href="${pageContext.request.contextPath}/Presentation/MostrarG?NRC=<%=c.getNrc()%>" class="btn btn-success">Elegir Grupo</a></b>
                            </div>

                        </div>
                    </div>
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
