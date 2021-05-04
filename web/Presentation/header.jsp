
<%@page import="Logic.Usuario"%>
<% Usuario usuario = (Usuario) session.getAttribute("usuario");  %>
<% String s= request.getParameter("bandera");  %>
<%boolean flag;%>
<%if(s!=null){%>
<%flag=(Integer.parseInt(s)==1);%>
<%}else{%>
<%flag=false;}%>
<header class="bg-fixed">
    <nav  class="navbar navegacion text-white navbar-light">
        <% if (usuario != null) { %>
        <% int rol = Integer.parseInt(usuario.getRol()); %>
        <%if (rol == 1) {  %>
        <div>
            <a href="${pageContext.request.contextPath}/Presentation/Inicio">Inicio</a>
        </div>
         <%if(flag){%>
        <form name="form" action="${pageContext.request.contextPath}/Presentation/Administrador/Buscar" method="post" > 
           
            <input class="form text-black" type="search" placeholder="Buscar Profesor" aria-label="Buscar" name ="id" >
            <button class="btn btn-secondary" type="submit">Buscar</button>
        </form>
            <%}%>
        <li class="nav-item dropdown lista">
            <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                Gestionar Cursos
            </a>
            <div class="dropdown-menu " aria-labelledby="navbarDropdown">
                <a class="dropdown-item" href="${pageContext.request.contextPath}/Presentation/Curso/Show">Agregar Cursos</a>
                <a class="dropdown-item" href="${pageContext.request.contextPath}/Presentation/Administrador/Listar">Cambiarle status/Agregar Grupo</a>
                <div class="dropdown-divider"></div>

            </div>
        </li>
        <li class="nav-item dropdown lista">
            
            <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                Gestionar Profesores
            </a>
            <div class="dropdown-menu " aria-labelledby="navbarDropdown">
                <a class="dropdown-item" href="${pageContext.request.contextPath}/Presentation/Profesor/Show">Registrar Profesores</a>
                <a class="dropdown-item" href="${pageContext.request.contextPath}/Presentation/Administrador/Listar/Profesores">Listar Profesores</a>
                <div class="dropdown-divider"></div>

            </div>
        </li>
        <% } else if (rol == 2) {  %>
        <div>
            <a href="${pageContext.request.contextPath}/Presentation/Profesor/CursosMios?idprof=<%=usuario.getIdUsu()%>">Inicio</a>
        </div>

        <% } %> 
        <% if (rol == 3) {  %>
          <div>
            <a href="${pageContext.request.contextPath}/Presentation/Cursoest">Inicio</a>
        </div>
        <div>
            <a href="${pageContext.request.contextPath}/Presentation/Estudiante/Matricular">Matricular</a>
        </div>
        <div>
            <a href="${pageContext.request.contextPath}/Presentation/G/Historial" target="_blank">Ver historial</a>
        </div>
        <% }%> 
        <div >
            <a  href="${pageContext.request.contextPath}/Presentation/Login/Logout">Salir</a>
        </div> 

        <div id="usuario">
            <p>
                Usuario:<%=usuario.getIdUsu()%><br>
                <%=determinaRol(rol)%>
            </p>
        </div> 

        <% } %>         
        <% if (usuario == null) {%>
          <div>
            <a href="${pageContext.request.contextPath}/Presentation/Inicio">Inicio</a>
        </div>
        <%if(flag){%>
        <form  class="text-white" name="form" action="${pageContext.request.contextPath}/Presentation/buscarcurnom" method="post" > 
            <input class="form text-black" type="search" placeholder="Buscar Curso" aria-label="Buscar" name ="id" >
            <button class="btn btn-secondary" type="submit">Buscar</button>
        </form>
            <%}%>
        <div><a href="${pageContext.request.contextPath}/Presentation/Estudiante/Show">Registrarse</a></div>
        <div><a href="${pageContext.request.contextPath}/Presentation/Login/Show">Iniciar Sesion</a></div>

        <% }%>   
    </nav> 
    <div class="d-flex justify-content-center">
        <img class="border border-secondary border-5 rounded-circle imagen" src="${pageContext.request.contextPath}/IMG/logo.png" id="logo">
    </div>
</header>
    
<%!
    private String determinaRol(int rol){
       
        switch(rol){
            case 1: return "Administrador"; 
            case 2: return "Profesor"; 
            case 3: return "Estudiante"; 
        }
        return "";
    }    



%>