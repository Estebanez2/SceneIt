<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="button" uri="http://www.springframework.org/tags/form" %>
<%@ page import="java.util.List" %>
<%@ page import="es.uma.taw.sceneit.dto.KeywordDTO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.css" rel="stylesheet">
    <title>Keywords</title>
    <style>
        .card:hover {
            transform: none !important;
            transition: none !important;
        }
    </style>
</head>

<%
    List<KeywordDTO> lista = (List<KeywordDTO>) request.getAttribute("keyword");
    request.setAttribute("pagina", "keyword");
%>

<body>
<jsp:include page="cabecera.jsp" />

<div class="container my-5 d-flex justify-content-center">
    <div class="card shadow w-100" style="max-width: 800px;">
        <div class="card-body">
            <h1 class="card-title text-center mb-4">Lista de keywords</h1>

            <form:form method="post" action="/keyword/filtrar" modelAttribute="filtro" class="d-flex mb-4">
                <form:input path="nombre" class="form-control me-2" placeholder="Filtrar por nombre..." />
                <button:button class="btn btn-outline-primary">Filtrar</button:button>
            </form:form>

            <div class="table-responsive">
                <table class="table table-striped align-middle text-center">
                    <thead class="table-light">
                    <tr>
                        <th>ID</th>
                        <th>Nombre</th>
                        <th colspan="2">Acciones</th>
                    </tr>
                    </thead>
                    <tbody>
                    <%
                        for(KeywordDTO keyword : lista){
                    %>
                    <tr>
                        <td><%= keyword.getId() %></td>
                        <td><%= keyword.getName() %></td>
                        <td colspan="2">
                            <div class="d-flex justify-content-center align-items-center gap-2" style="height: 100%;">
                                <form method="post" onsubmit="abrirModal(this, 'editar'); return false" class="m-0">
                                    <input type="hidden" name="id" value="<%= keyword.getId() %>">
                                    <button type="submit" class="btn btn-light rounded-circle d-flex justify-content-center align-items-center p-0" style="width:40px; height:40px;">
                                        <i class="bi bi-pencil-fill"></i>
                                    </button>
                                </form>
                                <form method="post" onsubmit="abrirModal(this, 'borrar'); return false" class="m-0">
                                    <input type="hidden" name="id" value="<%= keyword.getId() %>">
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
                    <i class="bi bi-plus-circle me-2"></i>Crear nuevo keyword
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
            fetch("/keyword/editar", {
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
            fetch("/keyword/menuBorrar", {
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
