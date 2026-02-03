<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="es.uma.taw.sceneit.dto.*" %>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
<link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.css" rel="stylesheet">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    boolean esEditar = true;
    MovieDTO movie = (MovieDTO) request.getAttribute("movie");

    if(movie.getId()==null){
        esEditar=false;
    }
%>

<div class="modal fade" tabindex="-1" id="editarModal">
    <div class="modal-dialog modal-dialog-centered modal-dialog-scrollable">
       <div class="modal-content">
           <div class="modal-header">
               <h3><%=(!esEditar ? "Nueva película" : "Editar Película")%></h3>
               <button type="button" class="btn-close" data-bs-dismiss="modal"
                       aria-label="Close"></button>
           </div>

                <form:form method="post" action="/movie/guardar" modelAttribute="movie" style="overflow-y: auto">
                    <form:hidden path="id"></form:hidden>
                    <div class="modal-body w-100" style="overflow-x: hidden">
                        <div class="input-group my-3 m-1">
                            <span class="input-group-text">Título</span>
                            <form:input type="text" path="title" class="form-control" required="required"/>
                        </div>
                        <div class="input-group my-3 m-1 my-3">
                            <span class="input-group-text">Título original</span>
                            <form:input type="text" path="originalTitle" class="form-control" />
                        </div>
                        <div class="row my-1">
                            <div class="col-sm input-group my-3 ms-1">
                                <span class="input-group-text">Fecha</span>
                                <form:input type="date" path="date" class="form-control" required="required"/>
                            </div>
                            <div class="col-sm input-group my-3" >
                                <span class="input-group-text">Status</span>
                                <form:input type="text" path="status" class="form-control" />
                            </div>
                        </div>
                        <div class="input-group my-3 m-1">
                            <span class="input-group-text">Frase Destacada</span>
                            <form:input type="text" path="tagline" class="form-control" />
                        </div>
                        <div class="row my-1">
                            <div class="col-sm input-group my-3 ms-1">
                                <span class="input-group-text"><i class="bi bi-clock-fill"></i></span>
                                <form:input type="number" path="runtime" class="form-control" placeholder="Duración" required="required"/>
                            </div>
                            <div class="col-sm input-group my-3">
                                <span class="input-group-text"><i class="bi bi-currency-dollar"></i></span>
                                <form:input type="number" path="revenue" class="form-control" placeholder="Ganancias" />
                            </div>
                        </div>
                        <div class="input-group my-3 m-1">
                            <span class="input-group-text">Imagen</span>
                            <form:input type="text" path="image" class="form-control"/>
                        </div>
                        <div class="input-group my-3 m-1">
                            <span class="input-group-text">Homepage</span>
                            <form:input type="text" path="homePage" class="form-control"/>
                        </div>
                        <div class="input-group my-3 m-1">
                            <span class="input-group-text">Géneros</span>
                            <form:select path="genders" class="overflow-y-auto form-select" multiple="true" items="${gender}" itemLabel="name" itemValue="id"></form:select>
                        </div>
                        <div class="input-group my-3 m-1">
                            <span class="input-group-text">Cast</span>
                            <form:select path="casts" items="${cast}" itemValue="id" itemLabel="character" class="overflow-y-auto form-select" multiple="true"></form:select>
                        </div>
                        <div class="input-group my-3 m-1">
                            <span class="input-group-text">Crew</span>
                            <form:select path="crews" items="${crew}" itemValue="id" itemLabel="nombrePersona" class="overflow-y-auto form-select" multiple="true"></form:select>
                        </div>
                        <div class="input-group my-3 m-1">
                            <span class="input-group-text">Keywords</span>
                            <form:select path="keywords" class="overflow-y-auto form-select" items="${keyword}" itemValue="id" itemLabel="name"></form:select>
                        </div>
                        <div class="input-group my-3 m-1">
                            <span class="input-group-text">Idiomas</span>
                            <form:select path="languages" class="overflow-y-auto form-select" items="${language}" itemLabel="name" itemValue="id"></form:select>
                        </div>
                        <div class="input-group my-3 m-1">
                            <span class="input-group-text">Compañías de producción</span>
                            <form:select path="productionCompanies" class="overflow-y-auto form-select" items="${productionCompany}" itemValue="id" itemLabel="name"></form:select>
                        </div>
                        <div class="input-group my-3 m-1">
                            <span class="input-group-text">Paises de producción</span>
                            <form:select path="productionCountries" class="overflow-y-auto form-select" multiple="true" items="${productionCountry}" itemLabel="name" itemValue="id"></form:select>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <form:button class="btn btn-primary w-100">Confirmar Cambios</form:button>
                    </div>
                </form:form>

       </div>
    </div>
</div>