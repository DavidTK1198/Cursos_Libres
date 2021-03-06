<%-- 
    Document   : View
    Created on : Apr 20, 2021, 11:45:19 AM
    Author     : Daniel Madrigal
--%>

<%@page import="Presentation.Inscripcion.Model"%>
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
        <% Model m = (Presentation.Inscripcion.Model) request.getAttribute("model"); %>
        <% List<Grupo> lista = m.getGrupito(); %>

        <div class="container-fluid container-lg container-md container-sm container-xl bg-fixed">
            <div  class="text-white row">
                <% for (Grupo c : lista) {%>
                <div class="col col-sm-8 col-md-4 col-xl-4">
                    <ul class="border border-primary">

                        <div class = "d-flex justify-content-center text-black bg-secondary">
                            <p>Horario&nbsp;<%=c.getHorario()%></p>
                        </div>
                        <div class = "d-flex justify-content-center text-black bg-secondary">
                            <p><b>Profesor: &nbsp;<%=c.getProfesoridProfe().getNomProfe()%></b></p>
                        </div>
                        <div class = "d-flex justify-content-center text-black bg-secondary">
                            <p><b>Grupo#&nbsp;<%=c.getNumGrup()%></b></p>
                        </div>
                        
                            <div class="text-center bg-dark" id="font"><a href="${pageContext.request.contextPath}/Presentation/Inscripcion/Matricular?num_Grup=<%=c.getNumGrup()%>" class="btn btn-secondary">Matricular</a></div>


                        
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
