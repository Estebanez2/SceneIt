<%@ taglib prefix="button" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="java.util.List" %>
<%@ page import="es.uma.taw.sceneit.dto.PersonDTO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.css" rel="stylesheet">
    <title>Personas</title>
    <style>
        .card:hover {
            transform: none !important;
            transition: none !important;
        }
    </style>
</head>

<%
    List<PersonDTO> lista = (List<PersonDTO>) request.getAttribute("person");
    request.setAttribute("pagina", "person");
%>

<body>
<jsp:include page="cabecera.jsp" />

<div class="container my-5 d-flex justify-content-center">
    <div class="card shadow w-100" style="max-width: 800px;">
        <div class="card-body">
            <h1 class="card-title text-center mb-4">Lista de personas</h1>

            <form:form method="post" action="/person/filtrar" modelAttribute="filtro" class="row g-3 mb-4">

                <!-- Filtro por nombre -->
                <div class="col-md-4">
                    <form:input path="nombre" class="form-control" placeholder="Filtrar por nombre..." />
                </div>

                <!-- Filtro por género -->
                <div class="col-md-3">
                    <form:select path="id" class="form-select">
                        <form:option value="-1" label="Género (Todos)" />
                        <form:option value="0" label="Hombre" />
                        <form:option value="1" label="Mujer" />
                        <form:option value="2" label="Otro" />
                    </form:select>
                </div>

                <!-- Filtro por si es actor -->
                <div class="col-md-3">
                    <form:select path="esActor" class="form-select">
                        <form:option value="" label="Actor (Todos)" />
                        <form:option value="true" label="Actor" />
                        <form:option value="false" label="No Actor" />
                    </form:select>
                </div>

                <!-- Botón de búsqueda -->
                <div class="col-md-2">
                    <button:button class="btn btn-primary w-100">Filtrar</button:button>
                </div>

            </form:form>

            <div class="table-responsive">
                <table class="table table-striped align-middle text-center">
                    <thead class="table-light">
                    <tr>
                        <th>ID</th>
                        <th>Nombre</th>
                        <th>Genero</th>
                        <th>Actor</th>
                        <th colspan="2">Acciones</th>
                    </tr>
                    </thead>
                    <tbody>
                    <%
                        for(PersonDTO person : lista){
                    %>
                    <tr>
                        <td><%= person.getId() %></td>
                        <td><%= person.getName() %></td>
                        <td><% if(person.getGender() == 0){ %>
                            Hombre
                            <%} else if(person.getGender() == 1){ %>
                            Mujer
                            <%} else { %>
                            Otro
                            <%}%>
                        </td>
                        <td><input type="checkbox" id="checkbox" disabled <%= person.getActor() == true ? "checked" : "" %> /></td>
                        <td colspan="2">
                            <div class="d-flex justify-content-center align-items-center gap-2" style="height: 100%;">
                                <form method="post" onsubmit="abrirModal(this, 'editar'); return false" class="m-0">
                                    <input type="hidden" name="id" value="<%= person.getId() %>">
                                    <button type="submit" class="btn btn-light rounded-circle d-flex justify-content-center align-items-center p-0" style="width:40px; height:40px;">
                                        <i class="bi bi-pencil-fill"></i>
                                    </button>
                                </form>
                                <form method="post" onsubmit="abrirModal(this, 'borrar'); return false" class="m-0">
                                    <input type="hidden" name="id" value="<%= person.getId() %>">
                                    <button class="btn btn-light rounded-circle d-flex justify-content-center align-items-center p-0" style="width:40px; height:40px;">
                                        <i class="bi bi-trash3-fill"></i>
                                    </button>
                                </form>
                            </div>
                        </td>
                    </tr>
                    <%
                        }
                    %>
                    </tbody>
                </table>
            </div>

            <form method="post" onsubmit="abrirModal(this, 'editar'); return false" class="text-center mt-4">
                <input type="hidden" name="id" value="-1">
                <button type="submit" class="btn btn-primary">
                    <i class="bi bi-plus-circle me-2"></i>Crear nueva persona
                </button>
            </form>
        </div>
    </div>
</div>


<div id="modal-container">
    <%--                       MENÚ PARA EDITAR PELÍCULA --%>

</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
        crossorigin="anonymous"></script>
<script>
    function abrirModal(form, tipo) {
        const formData = new FormData(form);
        console.log(formData);
        for (let pair of formData.entries()) {
            console.log(`${pair[0]}: ${pair[1]}`);
        }

        if (tipo === "editar") {
            fetch("/person/editar", {
                method: "POST",
                body: new URLSearchParams(formData)
            })
                .then(res => res.text())
                .then(html => {
                    console.log("Respuesta del servidor:", html);
                    document.getElementById("modal-container").innerHTML = html;

                    const modalEl = document.querySelector("#editarModal");
                    const modal = new bootstrap.Modal(modalEl);
                    modal.show();
                })
                .catch(err => console.error("Error cargando modal:", err));
        } else {
            fetch("/person/menuBorrar", {
                method: "POST",
                body: new URLSearchParams(formData)
            })
                .then(res => res.text())
                .then(html => {
                    console.log("Respuesta del servidor:", html);
                    document.getElementById("modal-container").innerHTML = html;

                    const modalEl = document.querySelector("#borrarModal");
                    const modal = new bootstrap.Modal(modalEl);
                    modal.show();
                })
                .catch(err => console.error("Error cargando modal:", err));
        }
    }

</script>

</body>
</html>