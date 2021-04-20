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
    <%@include file = "/Presentation/head.jsp" %>
    <body class="bg-fixed text-white">
        <main>
            <%@include file = "/Presentation/header.jsp" %>

            <div class="container-fluid container-lg container-md container-sm text-white mt-5" id="app1">
                <h1>Cursos en Oferta</h1>
                <div>
                    <div  class="row" method = "post">                     
                        <div v-for="item in lista" class="col col-sm-8 col-md-4 col-xl-4">
                            <ul class="border border-success">
                                {{item.text}}
                                <img src="${pageContext.request.contextPath}/IMG/4401280-768x432.jpg">
                                <li class="border">
                                    <p class="mr-5">
                                        {{item.descripcion}}
                                        <a  href="${pageContext.request.contextPath}/Presentation/Login/Show">TEST</a>
                                    </p>
                                </li>
                            </ul> 
                        </div>
                    </div>
                </div>
            </div>
        </main>

        <aside></aside>
            <%@include file = "/Presentation/footer.jsp" %>
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
