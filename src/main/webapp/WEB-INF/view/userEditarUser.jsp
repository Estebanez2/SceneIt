<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="es.uma.taw.sceneit.dto.UserDTO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
<link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.css" rel="stylesheet">

<%
    boolean esEditar = false;
    UserDTO user = (UserDTO) request.getAttribute("userDTO");

    if(user.getId() != -1){
        esEditar = true;
    }
%>

<jsp:include page="cabecera.jsp" />

<div class="container mt-5">
    <div class="row justify-content-center">
        <div class="col-lg-8 col-md-10 col-12">
            <div class="card shadow-lg border-0 rounded-4">
                <div class="card-header bg-primary text-white text-center py-4">
                    <h3 class="fw-bold"><%=(!esEditar ? "Nuevo Usuario" : "Editar Usuario")%></h3>
                </div>
                <div class="card-body p-5">
                    <form:form method="post" action="/user/guardarUser" modelAttribute="userDTO">
                        <form:hidden path="id" />
                        <form:hidden path="admin" />
                        <form:hidden path="editor" />
                        <form:hidden path="estadista" />
                        <form:hidden path="userLectura" />
                        <form:hidden path="userEscritura" />

                        <div class="mb-4">
                            <label class="form-label" for="username">Usuario</label>
                            <form:input class="form-control" path="username" id="username" required="required"/>
                        </div>

                        <div class="mb-4">
                            <label class="form-label" for="pwd">Password</label>
                            <form:input type="password" class="form-control" path="pwd" id="pwd" required="required"/>
                        </div>

                        <div class="mb-4">
                            <label class="form-label" for="firstName">Nombre</label>
                            <form:input class="form-control" path="firstName" id="firstName" required="required"/>
                        </div>

                        <div class="mb-4">
                            <label class="form-label" for="secondName1">Apellido 1</label>
                            <form:input class="form-control" path="secondName1" id="secondName1" required="required"/>
                        </div>

                        <div class="mb-4">
                            <label class="form-label" for="secondName2">Apellido 2</label>
                            <form:input class="form-control" path="secondName2" id="secondName2" required="required"/>
                        </div>

                        <div class="mb-4">
                            <label class="form-label" for="email">Email</label>
                            <form:input class="form-control" path="email" id="email" required="required"/>
                        </div>

                        <div class="mb-4">
                            <label class="form-label" for="birthDate">Fecha de nacimiento</label>
                            <form:input type="date" class="form-control" path="birthDate" id="birthDate" required="required"/>
                        </div>

                        <form:hidden path="idRole" />

                        <button type="submit" class="btn btn-primary w-100 py-2">Confirmar Cambios</button>
                    </form:form>
                </div>
            </div>
        </div>
    </div>
</div>
