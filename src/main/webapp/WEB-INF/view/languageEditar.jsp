<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ page import="es.uma.taw.sceneit.dto.LanguageDTO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
<link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.css" rel="stylesheet">

<%
    boolean esEditar = false;
    LanguageDTO language = (LanguageDTO) request.getAttribute("languageDTO");

    if(language.getId() != -1){
        esEditar = true;
    }
%>


<div class="modal fade" tabindex="-1" id="editarModal">
    <div class="modal-dialog modal-dialog-centered modal-dialog-scrollable">
        <div class="modal-content">
            <div class="modal-header">
                <h3><%=(!esEditar ? "Nuevo idioma" : "Editar idioma")%></h3>
                <button type="button" class="btn-close" data-bs-dismiss="modal"
                        aria-label="Close"></button>
            </div>

            <form:form method="post" modelAttribute="languageDTO" action="/language/guardar" style="overflow-y: auto">
                <div class="modal-body w-100" style="overflow-x: hidden">
                    <div class="input-group my-3 m-1">
                        <form:hidden path="id" class="form-control"/>
                        <span class="input-group-text">Nombre</span>
                        <form:input path="name" class="form-control" required="required"/>
                    </div>
                </div>

                <div class="modal-footer">
                    <form:button class="btn btn-primary w-100">Confirmar Cambios</form:button>
                </div>
            </form:form>

        </div>
    </div>
</div>