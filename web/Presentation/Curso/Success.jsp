<%-- 
    Document   : Success
    Created on : Apr 28, 2021, 11:28:33 AM
    Author     : Daniel Madrigal
--%>

<%@page import="Logic.Curso"%>
<%@page import="Logic.Usuario"%>
<%@page import="Presentation.Curso.Model"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%@include file = "/Presentation/head.jsp" %>
    <% Model model = (Presentation.Curso.Model) request.getAttribute("model"); %>
    <% Curso cursito = model.getCurrent();%>
   
    <body>
        
            <h1 class='text-center text-white'>CURSO REGISTRADO EXITOSAMENTE!!!</h1>
            <div class="container-fluid container-lg container-md container-sm container-xl">
                <div class="d-flex justify-content-center row">
                    <div class="col-3 col-sm-8 col-md-4 col-xl-4">

                        <table class="table text-white">
                            <thead class="thead-dark">
                                <tr>
                                    <th scope="col">NRC</th>
                                    <th scope="col">Nombre del curso </th>

                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <th scope="col"><%=cursito.getNrc()%></th>
                                    <th scope="col"><%=cursito.getNomCur()%></th>

                                </tr>

                            </tbody>
                        </table>

                    </div>
                </div>
                <div class="text-center d-flex justify-content-between">
                    
                    <a href="${pageContext.request.contextPath}/Presentation/Administrador">Regresar al menu general</a>
                </div>
            </div>
        
    </body>
    <%@include file = "/Presentation/footer.jsp" %>

</html>