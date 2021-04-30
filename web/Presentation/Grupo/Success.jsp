<%-- 
    Document   : Success
    Created on : Apr 30, 2021, 4:46:52 PM
    Author     : Daniel Madrigal
--%>

<%@page import="Logic.Grupo"%>
<%@page import="Presentation.Grupo.Model"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%@include file = "/Presentation/head.jsp" %>
    <% Model model = (Presentation.Grupo.Model) request.getAttribute("model"); %>
    <% Grupo Grupito = model.getCurrent();%>
   
    <body>
        
            <h1 class='text-center text-white'>GRUPO REGISTRADO EXITOSAMENTE!!!</h1>
            <div class="container-fluid container-lg container-md container-sm container-xl">
                <div class="d-flex justify-content-center row">
                    <div class="col-3 col-sm-8 col-md-4 col-xl-4">

                        <table class="table text-white">
                            <thead class="thead-dark">
                                <tr>
                                    <th scope="col">Horario</th>
                                    <th scope="col">Profesor asignado </th>

                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <th scope="col"><%=Grupito.getHorario()%></th>
                                    <th scope="col"><%=Grupito.getProfesoridProfe().getNomProfe()%></th>

                                </tr>

                            </tbody>
                        </table>

                    </div>
                </div>
                <div class="text-center d-flex justify-content-between">
                    
                    <a href="${pageContext.request.contextPath}/Presentation/Administrador/View.jsp">Regresar al menu principal</a>
                </div>
            </div>
        
    </body>
    <%@include file = "/Presentation/footer.jsp" %>

</html>

