<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Iniciar sesión</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
          crossorigin="anonymous">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.css" rel="stylesheet">
</head>

<%
    String error = (String) request.getAttribute("error");
%>

<body class="bg-light">
<jsp:include page="cabecera.jsp"/>
<%
    if(error != null){%>
<div class="text-danger fs-4 fw-bold text-center mb-3">
    <%=error%>
</div>
<%}%>
<div class="container d-flex justify-content-center align-items-center" style="min-height: 80vh;">
    <div class="card shadow w-100" style="max-width: 400px;">
        <div class="card-body">
            <h2 class="card-title text-center mb-4">Iniciar sesión</h2>

            <form:form method="post" action="/user/logear" modelAttribute="userDTO">
                <div class="mb-3">
                    <label for="usuario" class="form-label">Usuario</label>
                    <div class="input-group">
                        <span class="input-group-text"><i class="bi bi-person-fill"></i></span>
                        <form:input path="username" class="form-control" id="usuario" placeholder="Nombre de usuario"/>
                    </div>
                </div>

                <div class="mb-4">
                    <label for="contrasena" class="form-label">Contraseña</label>
                    <div class="input-group">
                        <span class="input-group-text"><i class="bi bi-lock-fill"></i></span>
                        <form:password path="pwd" class="form-control" id="contrasena" placeholder="Contraseña"/>
                    </div>
                </div>

                <div class="d-grid">
                    <button type="submit" class="btn btn-primary">
                        <i class="bi bi-box-arrow-in-right me-2"></i>Acceder
                    </button>
                </div>
            </form:form>

            <!-- Botón para crear nueva cuenta -->
            <div class="text-center mt-3">
                <a href="/user/editarUser?id=-1" class="btn btn-link">¿No tienes cuenta? Crear nueva cuenta</a>
            </div>
        </div>
    </div>
</div>

</body>
</html>