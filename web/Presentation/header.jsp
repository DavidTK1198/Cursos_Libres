
<%@page import="Logic.Usuario"%>
<% Usuario usuario = (Usuario) session.getAttribute("usuario");  %>


<header class="bg-fixed">
    <nav  class="navbar navegacion text-white">
        <div>
            <a href="${pageContext.request.contextPath}/Presentation/index.jsp">Inicio</a>
        </div>
        <% if (usuario != null) { %>
        <% int rol = Integer.parseInt(usuario.getRol()); %>
        <%if (rol == 1) {  %>
        
        <li class="nav-item dropdown lista">
            <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                Gestionar Cursos
            </a>
            <div class="dropdown-menu " aria-labelledby="navbarDropdown">
                <a class="dropdown-item" href="${pageContext.request.contextPath}/Presentation/Curso/Show">Agregar Cursos</a>
                <a class="dropdown-item" href="${pageContext.request.contextPath}/Presentation/Administrador/Listar">Cambiarle status</a>
                <div class="dropdown-divider"></div>
                <a class="dropdown-item" href="#">Something else here</a>
            </div>
        </li>

        <div>
            <a href="${pageContext.request.contextPath}/Presentation/Profesor/Show">Registrar Profesores</a>
        </div>

        <% } else if (rol == 2) {  %>
      
        <div>
              <a href="${pageContext.request.contextPath}/Presentation/Profesor/CursosMios?idprof=<%=usuario.getIdUsu()%>">Mis Cursos</a>
        </div>
        <% } %> 
        <% if (rol == 3) {  %>
        <div>
            <a href="${pageContext.request.contextPath}/Presentation/Estudiante/Matricular">Matricular</a>
        </div>
        <div>
            <a href=#>Ver historial</a>
        </div>
        <div>
            <a href=#>Generar Certificado</a>
        </div>
        <% } %> 
        <div >
            <a  href="${pageContext.request.contextPath}/Presentation/Login/Logout">Salir</a>
        </div>                
        <% } %>         
        <% if (usuario == null) {%>
        <form class="form">
            <input class="form mr-5" type="search" placeholder="Buscar Curso" aria-label="Buscar">
            <button class="btn btn-outline-info" type="submit">Buscar</button>
        </form>
        <div><a href="${pageContext.request.contextPath}/Presentation/Estudiante/Show">Registrarse</a></div>
        <div><a href="${pageContext.request.contextPath}/Presentation/Login/Show">Iniciar Sesion</a></div>

        <% }%>   
    </nav> 
     <div class="d-flex justify-content-center">
        <img class="border border-secondary border-5 rounded-circle imagen" src="${pageContext.request.contextPath}/IMG/logo.png">
    </div>
</header>