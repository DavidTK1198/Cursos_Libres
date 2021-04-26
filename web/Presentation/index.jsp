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
    
    <body class="bg-fixed text-white">
        <main>
            <%@include file = "/Presentation/header.jsp" %>
            <%if(usuario == null){%>
            
            <% Model m = (Model) request.getAttribute("model"); %>
            <% List<Curso> lista = m.getCursos(); %>


            <div class="container-fluid container-lg container-md container-sm container-xl bg-fixed">
                <h1>Cursos en Oferta</h1>
                <div  class="text-white row">
                    <%if (lista.isEmpty()) {%>
                    <h2>SIN CURSOS DESTACADOS</h2>
                    <%} else {%>
                    <% for (Curso c : lista) {%>
                    <div class="col col-sm-8 col-md-4 col-xl-4">
                        <ul class="border border-success">
                            <img src="${pageContext.request.contextPath}/Presentation/Curso/Imagen?NRC=<%=c.getNrc()%>">
                            <div class = "d-flex justify-content-center">
                                <div>Nombre:&nbsp;<%=c.getNomCur()%></div><br>
                                <div>NRC:&nbsp;<%=c.getNrc()%></div><br>
                                <div>Descripcion:&nbsp;<%=c.getDesCur()%> </div>
                            </div>
                            <li class="border">
                                <p class="mr-5">
                                    <a href="${pageContext.request.contextPath}/Presentation/Inscripcion/Matricular?NRC=<%=c.getNrc()%>" class="btn btn-primary">Matricular</a>

                                </p>
                            </li>
                        </ul> 
                    </div>
                    <%}%>
                    <%}%>
                </div>
            </div>
           <%}else{%>
           
           <%}%>
        </main>

        <aside></aside>
            <%@include file = "/Presentation/footer.jsp" %>
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" Integeregrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.bundle.min.js" Integeregrity="sha384-Piv4xVNRyMGpqkS2by6br4gNJ7DXjqk09RmUpJ8jgGtD7zP9yug3goQfGII0yAns" crossorigin="anonymous"></script>
    </body>
</html>
