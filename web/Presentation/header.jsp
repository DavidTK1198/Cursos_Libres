
<%@page import="Logic.Usuario"%>
<% Usuario usuario = (Usuario) session.getAttribute("usuario");  %>


<header><h1>Bienvenidos a Cursos Libres.com</h1>
    <img class="rounded-circle"src="${pageContext.request.contextPath}/IMG/logo.png" alt>
    <nav  class="navbar navegacion"">
        <div>
            <a href="${pageContext.request.contextPath}/Presentation/index.jsp">Inicio</a>
        </div>
                <% if (usuario != null){ %>
                <% int rol = Integer.parseInt(usuario.getRol()); %>
                <%if (rol == 1){  %>
        <div>
            <a href="/Presentation/GestionarG">Gestionar Grupos</a>
        </div>
        <div>
            <a href="${pageContext.request.contextPath}/Presentation/Profesor/Show">Registrar Profesores</a>
        </div>
               
                <% }else if (rol == 2){  %>
        <div>
            <a href=#>Ver Grupos</a>
        </div>
        <div>
            <a href=#>Ver Cursos</a>
        </div>
        <div>
            <a href=#>Ingresar notas</a>
        </div>
        <% } %> 
        <% if (rol == 3){  %>
        <div>
            <a href=#>Matricular</a>
        </div>
        <div>
            <a href=#>Ver historial</a>
        </div>
        <div>
            <a href=#>Generar Certificado</a>
        </div>
        <% } %> 
        <div >
            <a  href="${pageContext.request.contextPath}/Presentation/Login/Logout">Logout</a>
        </div>                
        <% } %>         
        <% if (usuario == null) {%>
        <li class="nav-item dropdown lista">
            <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                Categorias
            </a>
            <div class="dropdown-menu " aria-labelledby="navbarDropdown">
                <a class="dropdown-item" href="#">Action</a>
                <a class="dropdown-item" href="#">Another action</a>
                <div class="dropdown-divider"></div>
                <a class="dropdown-item" href="#">Something else here</a>
            </div>
        </li>
        <form class="form">
            <input class="form mr-5" type="search" placeholder="Buscar" aria-label="Buscar">
            <button class="btn btn-outline-info" type="submit">Buscar</button>
        </form>
        <div><a href="#">Registrarse</a></div>
        <div><a href="#">Iniciar Sesion</a></div>
        
        <% }%>   
    </nav> 
</header>