<%@ page import="es.uma.taw.sceneit.dto.UserDTO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Cabecera</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.css" rel="stylesheet">
</head>
<%
  UserDTO user = (UserDTO) session.getAttribute("user");
  String pagina = (String) request.getAttribute("pagina");
%>
<body>




<nav class="navbar navbar-expand-lg navbar-light bg-light px-3">
  <div class="container-fluid">
    <!-- Botón de colapso para móviles -->
      <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarContenido" aria-controls="navbarContenido" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
    <div class="collapse navbar-collapse" id="navbarContenido">
      <ul class="navbar-nav flex-wrap">
        <li class="nav-item">
          <a class="nav-link <%= "movie".equals(pagina) ? "active fw-bold text-primary" : "" %>" href="/movie/">Películas</a>
        </li>

        <%if(user != null && user.getEditor()){%>
        <li class="nav-item">
          <a class="nav-link <%= "keyword".equals(pagina) ? "active fw-bold text-primary" : "" %>" href="/keyword/">Keywords</a>
        </li>
        <%}%>

        <%if(user != null && user.getEditor()){%>
        <li class="nav-item">
          <a class="nav-link <%= "gender".equals(pagina) ? "active fw-bold text-primary" : "" %>" href="/gender/">Géneros</a>
        </li>
        <%}%>

        <%if(user != null && user.getEditor()){%>
        <li class="nav-item">
          <a class="nav-link <%= "language".equals(pagina) ? "active fw-bold text-primary" : "" %>" href="/language/">Idiomas</a>
        </li>
        <%}%>

        <%if(user != null && user.getEditor()){%>
        <li class="nav-item">
          <a class="nav-link <%= "productionCountry".equals(pagina) ? "active fw-bold text-primary" : "" %>" href="/productionCountry/">Países de Producción</a>
        </li>
        <%}%>

        <%if(user != null && user.getEditor()){%>
        <li class="nav-item">
          <a class="nav-link <%= "productionCompany".equals(pagina) ? "active fw-bold text-primary" : "" %>" href="/productionCompany/">Compañías de Producción</a>
        </li>
        <%}%>

        <%if(user != null && user.getEditor()){%>
        <li class="nav-item">
          <a class="nav-link <%= "crew".equals(pagina) ? "active fw-bold text-primary" : "" %>" href="/crew/">Crew</a>
        </li>
        <%}%>

        <%if(user != null && user.getEditor()){%>
        <li class="nav-item">
          <a class="nav-link <%= "cast".equals(pagina) ? "active fw-bold text-primary" : "" %>" href="/cast/">Cast</a>
        </li>
        <%}%>

        <%if(user != null && user.getEditor()){%>
        <li class="nav-item">
          <a class="nav-link <%= "person".equals(pagina) ? "active fw-bold text-primary" : "" %>" href="/person/">Personas</a>
        </li>
        <%}%>

        <%if(user != null && user.getAdmin()){%>
        <li class="nav-item">
          <a class="nav-link <%= "usuarios".equals(pagina) ? "active fw-bold text-primary" : "" %>" href="/user/">Usuarios</a>
        </li>
        <%}%>

        <%if(user != null && user.getEstadista()){%>
        <li class="nav-item">
          <a class="nav-link <%= "statistics".equals(pagina) ? "active fw-bold text-primary" : "" %>" href="/statistics/">Estadisticas</a>
        </li>
        <%}%>
      </ul>
    </div>

    <div class="ms-auto d-flex">
      <% if (user == null) { %>
      <a class="btn btn-outline-primary" href="/user/login">Loguear</a>
      <% } else {%>
      <div class="dropdown">
        <button class="btn btn-primary dropdown-toggle" type="button" data-bs-toggle="dropdown" aria-expanded="false">
          Usuario: <%= user.getUsername() %>
        </button>
        <ul class="dropdown-menu dropdown-menu-end">
            <%if(user != null && !user.getAdmin() && !user.getEstadista() && !user.getEditor()){%>
              <!-- Aquí cambiamos el enlace por un "a" con un evento que llame a abrirModal() -->
              <a class="dropdown-item" href="/user/editarUser?id=<%=user.getId()%>">Editar datos</a>
            </li>
          <%}%>
          <li><a class="dropdown-item" href="/user/logout">Desloguear</a></li>
        </ul>
      </div>
      <% } %>
    </div>

  </div>
</nav>

</body>
</html>
