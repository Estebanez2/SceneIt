<%@ page import="es.uma.taw.sceneit.dto.CrewDTO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
<link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.css" rel="stylesheet">

<%
    boolean esEditar = false;
    CrewDTO crew = (CrewDTO) request.getAttribute("crewDTO");

    if(crew.getId() != -1){
        esEditar = true;
    }
%>


<div class="modal fade" tabindex="-1" id="editarModal">
    <div class="modal-dialog modal-dialog-centered modal-dialog-scrollable">
        <div class="modal-content">
            <div class="modal-header">
                <h3><%=(!esEditar ? "Nuevo Crew" : "Editar Crew")%></h3>
                <button type="button" class="btn-close" data-bs-dismiss="modal"
                        aria-label="Close"></button>
            </div>

            <form:form method="post" action="/crew/guardar" modelAttribute="crewDTO" style="overflow-y: auto">
                <form:hidden path="id"/>

                <div class="modal-body w-100" style="overflow-x: hidden">
                    <!-- Campo Nombre -->
                    <div class="input-group my-3 m-1">
                        <label class="input-group-text" for="idPerson">Nombre</label>
                        <form:select path="idPersona" cssClass="form-select" id="idPerson" required="required">
                            <form:option value="">-- Selecciona una persona --</form:option>
                            <form:options items="${person}" itemValue="id" itemLabel="name"/>
                        </form:select>
                    </div>

                    <!-- Campo Puesto -->
                    <div class="input-group my-3 m-1">
                        <span class="input-group-text">Puesto</span>
                        <form:input path="job" cssClass="form-control" required="required"/>
                    </div>

                    <!-- Campo Empresa -->
                    <div class="input-group my-3 m-1">
                        <label class="input-group-text" for="idProductionCompany">Empresa</label>
                        <form:select path="idProductCompany" cssClass="form-select" id="idProductionCompany" required="required">
                            <form:option value="-1">-- Selecciona una empresa --</form:option>
                            <form:options items="${productionCompany}" itemValue="id" itemLabel="name"/>
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

