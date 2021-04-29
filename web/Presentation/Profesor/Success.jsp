<%-- 
    Document   : Success
    Created on : Apr 28, 2021, 11:51:31 AM
    Author     : Daniel Madrigal
--%>

<%@page import="Logic.Usuario"%>
<%@page import="Presentation.Profesor.Model"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%@include file = "/Presentation/head.jsp" %>
    <% Model model = (Presentation.Profesor.Model) request.getAttribute("model"); %>
    <% Usuario us = model.getCurrent().getUsuarioIdUsu();%>
    <body>
        
            <h1 class='text-center text-white'>PROFESOR REGISTRADO CORRECTAMENTE!!!</h1>
            <h2 class='text-center text-white'>SE LE MUESTRA A CONTINUACION EL USUARIO Y CONTRASENIA CORRESPONDIENTE AL PROFESOR INGRESADO!!!</h2>
            <div class="container-fluid container-lg container-md container-sm container-xl">
                <div class="d-flex justify-content-center row">
                    <div class="col-3 col-sm-8 col-md-4 col-xl-4">

                        <table class="table text-white">
                            <thead class="thead-dark">
                                <tr>
                                    <th scope="col">Usuario</th>
                                    <th scope="col">Contrasenia</th>

                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <th scope="col"><%=us.getIdUsu()%></th>
                                    <th scope="col"><%=us.getClave()%></th>

                                </tr>

                            </tbody>
                        </table>

                    </div>
                </div>
                <div class="text-center d-flex justify-content-between">
                    <a href="${pageContext.request.contextPath}/Presentation/Profesor/Show">Registro de nuevo profesor</a>
                    <a href="${pageContext.request.contextPath}/Presentation/Inicio">Volver al inicio</a>
                </div>
            </div>
        
    </body>
    <%@include file = "/Presentation/footer.jsp" %>

</html>