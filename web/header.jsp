
<%@page import="Logic.Usuario"%>
<% Usuario usuario = (Usuario) session.getAttribute("usuario");  %>
<header><h1>Bienvenidos a Cursos Libres.com</h1>
    <img class="rounded-circle"src="IMG/logo.png" alt>
    <div class="menu">
        <ul> 
            <li>
                <a href="${pageContext.request.contextPath}/index.jsp">Inicio</a>
            </li>
            <% if (usuario != null) { %>
            <li>
                <% if (usuario.getRol() == "1") {  %>
                <a href=#>Gestionar Grupos</a>
            </li>
            <li>
                <a href=#>Gestionar Profesores</a>
            </li>
            <% } %> 
            <li>
                <% if (usuario.getRol() == "2") {  %>
                <a href=#>Ver Grupos</a>
            </li>
            <li>
                <a href=#>Ver Cursos</a>
            </li>
            <li>
                <a href=#>Ingresar notas</a>
            </li>
            <% } %> 
            <li>
                <% if (usuario.getRol() == "3") {  %>
                <a href=#>Matricular</a>
            </li>
            <li>
                <a href=#>Ver historial</a>
            </li>
            <li>
                <a href=#>Generar Certificado</a>
            </li>
            <% } %> 
            <li >
                <a  href="${pageContext.request.contextPath}/Presentation/Login/Logout">Logout</a>
               
            </li>                
            <% } %>
            <% if (usuario == null) {%>
            <li>
                <a href="/${pageContext.request.contextPath}Presentation/Login/Show">Login</a>
            </li>

            <% }%>             
        </ul>
    </div>
</header>