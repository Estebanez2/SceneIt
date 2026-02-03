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


<div class="modal fade" tabindex="-1" id="editarModal">
    <div class="modal-dialog modal-dialog-centered modal-dialog-scrollable">
        <div class="modal-content">
            <div class="modal-header">
                <h3><%=(!esEditar ? "Nuevo Usuario" : "Editar Usuario")%></h3>
                <button type="button" class="btn-close" data-bs-dismiss="modal"
                        aria-label="Close"></button>
            </div>

            <form:form method="post" action="/user/guardar" modelAttribute="userDTO" style="overflow-y: auto">
                <form:hidden path="id" />
                <div class="modal-body w-100" style="overflow-x: hidden">

                    <div class="input-group my-3 m-1">
                        <span class="input-group-text">Usuario</span>
                        <form:input  class="form-control" path="username" required="required"/>
                    </div>

                    <div class="input-group my-3 m-1">
                        <span class="input-group-text">Password</span>
                        <form:input type="password" class="form-control" path="pwd" required="required"/>
                    </div>

                    <div class="input-group my-3 m-1">
                        <span class="input-group-text">Nombre</span>
                        <form:input  class="form-control" path="firstName" required="required"/>
                    </div>

                    <div class="input-group my-3 m-1">
                        <span class="input-group-text">Apellido 1</span>
                        <form:input  class="form-control" path="secondName1" required="required"/>
                    </div>

                    <div class="input-group my-3 m-1">
                        <span class="input-group-text">Apellido 2</span>
                        <form:input  class="form-control" path="secondName2" required="required"/>
                    </div>

                    <div class="input-group my-3 m-1">
                        <span class="input-group-text">Email</span>
                        <form:input  class="form-control" path="email" required="required"/>
                    </div>

                    <div class="input-group my-3 m-1">
                        <span class="input-group-text">Fecha de nacimiento</span>
                        <form:input type="date" class="form-control" path="birthDate" required="required"/>
                    </div>

                    <!-- Género -->
                    <div class="input-group my-3 m-1">
                        <label class="input-group-text" for="idRol">Rol</label>
                        <form:select path="idRole" class="form-select" id="idRol" required="required">
                            <form:option value="">-- Selecciona un rol --</form:option>
                            <form:options items="${role}" itemValue="id" itemLabel="name"/>
                        </form:select>
                    </div>

                </div>

                <div class="modal-footer">
                    <form:button class="btn btn-primary w-100">Confirmar Cambios</form:button>
                </div>
            </form:form>

        </div>
    </div>
</div>