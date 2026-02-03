<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ page import="es.uma.taw.sceneit.dto.PersonDTO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
<link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.css" rel="stylesheet">

<%
    boolean esEditar = false;
    PersonDTO person = (PersonDTO) request.getAttribute("personDTO");

    if(person.getId() != -1){
        esEditar = true;
    }
%>


<div class="modal fade" tabindex="-1" id="editarModal">
    <div class="modal-dialog modal-dialog-centered modal-dialog-scrollable">
        <div class="modal-content">
            <div class="modal-header">
                <h3><%=(!esEditar ? "Nueva Persona" : "Editar Persona")%></h3>
                <button type="button" class="btn-close" data-bs-dismiss="modal"
                        aria-label="Close"></button>
            </div>

            <form:form method="post" action="/person/guardar" modelAttribute="personDTO" style="overflow-y: auto">
                <form:hidden path="id" />
                <div class="modal-body w-100" style="overflow-x: hidden">

                    <!-- Nombre -->
                    <div class="input-group my-3 m-1">
                        <span class="input-group-text">Nombre</span>
                        <form:input  class="form-control" path="name" required="required"/>
                    </div>

                    <!-- Género -->
                    <div class="input-group my-3 m-1">
                        <label class="input-group-text" for="generoSelect">Género</label>
                        <form:select class="form-select" path="gender" id="generoSelect" required="required">
                            <form:option value="0" label="Hombre" />
                            <form:option value="1" label="Mujer" />
                            <form:option value="2" label="Otro" />
                        </form:select>
                    </div>

                    <!-- Actor -->
                    <div class="input-group my-3 m-1">
                        <label class="input-group-text" for="actorSelect">¿Es actor?</label>
                        <form:select class="form-select" path="actor" id="actorSelect">
                            <form:option value="true" label="Sí" />
                            <form:option value="false" label="No" />
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