<%-- 
    Document   : index
    Created on : 09/04/2021, 07:43:13 PM
    Author     : DavidTK1198
--%>

<%@page import="Logic.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="Logic.Service"%>
<!DOCTYPE html>
<html>
    <%@include file = "/head.jsp" %>
    <body class="bg-light">
        <main>
           <% Usuario us = (Usuario) session.getAttribute("usuario"); %>
            <%@include file = "/header.jsp" %>
            
            
            <% if(us!= null){ %>
            <h2>Monitas chinas encueradas</h2>
            <% } else{ %>
            <h2>
                hola
            </h2>
            <% }%>
            
            <%@include file = "/nav.jsp" %>
            <div class="container-fluid container-lg container-md container-sm container-xl" id="app1">
                <h1>Cursos en Oferta</h1>
                <div class="row">
                    <form method = "post" action = "/Cursos_Libres/Presentation/Login">                     
                        <div v-for="item in lista" class="col col-sm-8 col-md-4 col-xl-4">
                            <ul class="border border-success">
                                {{item.text}}
                                <img src="IMG/4401280-768x432.jpg">
                                <li class="border">
                                    <p class="mr-5">
                                        {{item.descripcion}}
                                        <button type="submit" class="btn btn-outline-success" >Prueba1</button>
                                    </p>
                                </li>
                            </ul> 
                        </div>
                    </form>
                </div>
            </div>
        </main>
        <aside></aside>
            <%@include file = "/footer.jsp" %>
        <script src="https://cdn.jsdelivr.net/npm/vue@2/dist/vue.js"></script>
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" Integeregrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.bundle.min.js" Integeregrity="sha384-Piv4xVNRyMGpqkS2by6br4gNJ7DXjqk09RmUpJ8jgGtD7zP9yug3goQfGII0yAns" crossorigin="anonymous"></script>
        <script>
const app1 = new Vue({
    el: '#app1',
    data: {
        lista: [
            {text: 'Programacion', descripcion: 'C++ pura vida'},
            {text: 'Cocina', descripcion: 'C# pura vida'},
            {text: 'Perreo Integerenso', descripcion: 'Ganoza'},
            {text: 'Baile tipico', descripcion: 'Guanacaste'},
            {text: 'Economia', descripcion: 'XD'},
            {text: 'Filosofia', descripcion: 'ella no te ama'}
        ]
    }
})
        </script>
    </body>
</html>
