<%-- 
    Document   : View
    Created on : Apr 21, 2021, 9:08:09 AM
    Author     : DavidTK1198
--%>


<%@page import="Logic.Inscripcion"%>
<%@page import="Logic.Grupo"%>
<%@page import="Presentation.Grupo.Model"%>
<%@page import="java.util.List"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="Logic.Estudiante"%>
<%@page import="Logic.Service"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <%@include file = "/Presentation/head.jsp" %>
    <main class="bg-fixed ">
        <%@include file = "/Presentation/header.jsp" %>
        <% Model m = (Model) request.getAttribute("model"); %>
        <% List<Estudiante> lista = m.getEstudiantes(); %>
        <% Grupo gr = m.getCurrent(); %>
        <% List<Inscripcion> ls;%>
        <% Inscripcion ins;%>

        <div class="text-center bg-secondary text-white">
            <h2 id="mensaje">
                CURSO : <%=m.getCurrent().getCurso().getNomCur()%>
            </h2>
            <h3>
                GRUPO# <%=m.getCurrent().getNumGrup()%>
            </h3>
             <h3>
               ESTUDIANTES MATRICULADOS:
            </h3>

        </div>
        <div class="container-fluid container-lg container-md container-sm container-xl bg-fixed">
            <div  class="text-black row">
                <% for (Estudiante c : lista) {%>
                <%ls = c.getInscripcion();%>
                <%ins = ls.get(0);%>
                <div class="col col-sm-8 col-md-4 col-xl-4">
                    <ul class="border border-primary">

                        <div class = "d-flex justify-content-center" id="pro">
                            <p class="text-center">
                                Cedula del Estudiante:&nbsp;<%=c.getIdEstudiante()%><br>
                                <b> Estudiante:&nbsp;<%=c.getNomEst()%></b> <br>
                                Nota actual del estudiante:&nbsp;<b><%=ins.getNota()%></b>
                            </p>
                        </div>
                        <div class="bg-secondary text-white">
                            <form name="form" action="${pageContext.request.contextPath}/Presentation/Digitar/Notas" method="post" requested>
                                <input type="number" min ="1" max="100" name="nota" class="text-black">
                                <input type="hidden" name="idest" value="<%=c.getIdEstudiante()%>">
                                <input type="hidden" name="num_Grup" value="<%=gr.getNumGrup()%>">
                                <button type="submit" class="btn btn-secondary" data-toggle="button" aria-pressed="false" autocomplete="off">
                                    Actualizar nota
                                </button>
                            </form>
                        </div>
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