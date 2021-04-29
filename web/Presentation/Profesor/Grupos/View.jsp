<%-- 
    Document   : View
    Created on : Apr 20, 2021, 9:29:49 PM
    Author     : Daniel Madrigal
--%>

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
        <% List<Grupo> lista = m.getMios(); %>

        <div class="container-fluid container-lg container-md container-sm container-xl bg-fixed">
            <div  class="text-white row">
                <%if(lista.isEmpty()){%>
                <h1 class="justify-content-center text-white">SIN GRUPOS ASIGNADOS</h1>
                <%}%>
                <% for (Grupo c : lista) {%>
                <div class="col col-sm-8 col-md-4 col-xl-4">
                    <ul class="border border-success">

                        <div class = "d-flex justify-content-center">
                            <p>
                                Horario: &nbsp;<%=c.getHorario()%><br>
                                Numero de Grupo:&nbsp;<%=c.getNumGrup()%><br>
                                Curso al que pertenece:&nbsp;<%=c.getCurso().getNomCur()%> 
                            </p>
                        </div>
                        <li class="border">
                            <p class="mr-5" align = "center">
                                <a href="${pageContext.request.contextPath}/Presentation/Profesor/IngresarNotas?num_Grup=<%=c.getNumGrup()%>" class="btn btn-primary">Agregar Notas</a>

                            </p>
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