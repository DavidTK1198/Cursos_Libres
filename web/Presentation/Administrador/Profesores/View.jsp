<%-- 
    Document   : View
    Created on : Apr 24, 2021, 4:12:02 PM
    Author     : DavidTK1198
--%>
<%@page import="Logic.Profesor"%>
<%@page import="Presentation.Administrador.Model"%>
<%@page import="java.util.List"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <%@include file = "/Presentation/head.jsp" %>
    <main class="bg-fixed ">
        <%@include file = "/Presentation/header.jsp" %>
        <% Model m = (Model) request.getAttribute("model"); %>
        <% List<Profesor> lista = m.getProfesores(); %>

        <div class="container-fluid container-lg container-md container-sm container-xl bg-fixed mt-5">
            <div class="text-black row">
                <% for (Profesor c : lista) {%>

                <div class="col-lg-4 col-md-12 mb-4 mb-lg-0">
                    <div class="card text-center">
                        <div class="card-header text-black">Disponible</div>
                        <div class="card-body">
                            <h5 class="card-title">Especialidad:&nbsp;<%=c.getEspecialidad()%><br></h5>
                            <p class="card-text">
                                Nombre: &nbsp;<%=c.getNomProfe()%><br>
                                Cedula: &nbsp;<%=c.getIdProfe()%>
                            </p>
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
