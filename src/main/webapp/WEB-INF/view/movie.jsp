<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="java.util.List" %>
<%@ page import="es.uma.taw.sceneit.dto.MovieDTO" %>
<%@ page import="es.uma.taw.sceneit.dto.GenderDTO" %>
<%@ page import="es.uma.taw.sceneit.dto.LanguageDTO" %>
<%@ page import="es.uma.taw.sceneit.dto.UserDTO" %>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
<link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.css" rel="stylesheet">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Movie</title>
    <style>
        .card{
            transition: scale 0.3s ease-in-out;
        }
        .card:hover{
            cursor: pointer;
            scale: 105%;
        }

        a{
            text-decoration: none;
            color: inherit;
        }
    </style>
</head>

<%
    List<MovieDTO> listaMovie = (List<MovieDTO>) request.getAttribute("movie");
    List<GenderDTO> listaGender = (List<GenderDTO>) request.getAttribute("gender"); //rescatamos lo que hay en model
    List<LanguageDTO> listaIdiomas = (List<LanguageDTO>) request.getAttribute("language"); //rescatamos lo que hay en model
    request.setAttribute("pagina", "movie");
    UserDTO userDTO = (UserDTO) session.getAttribute("user");

%>

<body>
<jsp:include page="cabecera.jsp" />

<h1 class="m-2 ms-3">Lista de Películas:</h1>
<div class="d-flex flex-wrap align-items-center m-2 ms-3 container-fluid w-50">
<%--   TODO onSubmit --%>
    <form:form method="post" modelAttribute="filtro" action="/movie/filtrar" class="d-flex flex-nowrap bg-body-tertiary py-2 px-4 shadow w-50 rounded-pill me-4 my-2 flex-grow-1" style="min-width: 200px; flex: 1 1 auto">
        <div class="input-group d-flex">
            <form:input path="nombre" type="search" placeholder="Buscar por nombre..." class="d-flex flex-grow-1 flex-fill w-25 form-control rounded-start-pill"/>
            <form:select path="genre" class="form-select flex-shrink-1 bg-body-secondary" style="cursor: pointer">
                <form:option value="-1">Todos los géneros</form:option>
                <form:options items="${gender}" itemValue="id" itemLabel="name" />
            </form:select>
            <button class="btn btn-light bg-body-secondary form-select border-start border flex-shrink-1" type="button" data-bs-toggle="dropdown" data-bs-auto-close="outside" aria-expanded="false">Idiomas</button>
            <div class="dropdown-menu p-0">
                <form:select path="languages"  multiple="true" class="py-3 ps-2 pe-0 form-select flex-shrink-1 bg-body-secondary" style="cursor: pointer">
                    <form:option value="-1">Todos los idiomas</form:option>
                    <form:options items="${language}" itemLabel="name" itemValue="id"></form:options>
                </form:select>
            </div>
            <form:button class="btn btn-primary rounded-end-pill"><i class="bi bi-search"></i></form:button>

        </div>
    </form:form>
    <%if (userDTO != null && userDTO.getEditor()){%>
        <form method="post" onsubmit="abrirModal(this, 'editar'); return false" class="flex-shrink-1 mb-0">
            <button type="submit" class="btn btn-light shadow p-3 rounded-2 mx-3">Nueva Película</button>
        </form>
    <%}%>

</div>
<div id="peliculas" class="d-flex flex-wrap justify-content-around">
    <% for(MovieDTO movie: listaMovie){
        %>
        <a href="/movie/visualizar?id=<%=movie.getId()%>" class="m-3  rounded-pill" style="text-decoration: none">
            <div class="card shadow position-relative rounded" style="width:300px;">

                <img src="<%=movie.getImage().isEmpty() ? ( "/NoFoto.png") : movie.getImage()%>" class="card-img-top object-fit-cover" style="height: 350px">

                <%if (userDTO != null && userDTO.getEditor()){%>
                <div class="position-absolute w-100 d-flex flex-wrap justify-content-between p-2">

                    <form  method="post" onsubmit="abrirModal(this, 'editar'); return false">
                        <input type="hidden" name="id" value="<%=movie.getId()%>">
                        <button type="submit" class="btn btn-light rounded-circle d-flex justify-content-center align-items-center" style="width:40px; height:40px;"><i class="bi bi-pencil-fill"></i></button>
                    </form>
                    <form  method="post" onsubmit="abrirModal(this, 'borrar'); return false">
                        <input type="hidden" name="id" value="<%=movie.getId()%>">
                        <button class=" btn btn-light rounded-circle d-flex justify-content-center align-items-center" style="width:40px; height:40px;"><i class="bi bi-trash3-fill"></i></button>

                    </form>
                </div>
                <%}%>


                <div class="card-body">
                    <h5 class="card-title"><%=movie.getTitle()%></h5>
                    <%--                ratings--%>
                    <div class="d-flex flex-wrap">
                        <%
                            Double rating = movie.getVoteAverage();
                            int fullStars = (int) Math.floor(rating); // truncar decimales
                            int emptyStars = 5 - fullStars;
                            for(int i=0 ; i<fullStars; i++){
                        %>
                        <i class="bi bi-star-fill"></i>
                        <%
                            }
                            for(int i=0 ; i<emptyStars; i++){
                        %>
                        <i class="bi bi-star"></i>
                        <%
                            }
                        %>
                    </div>

                </div>
                <div class="card-footer">

                    <%for(String genre:movie.getNombreGenders()){%>
                        <span class="badge rounded-pill shadow-sm text-black text-bg-light"><%=genre%></span>
                    <%}%>

                </div>
            </div>

        </a>
    <%
    }%>
</div>
<div id="modal-container">
    <%--                       MENÚ PARA EDITAR PELÍCULA --%>

</div>
<%--<table border="1">--%>
<%--    <th>ID</th>--%>
<%--    <th>Título</th>--%>
<%--    <th>Generos</th>--%>
<%--    <th>Valoración</th>--%>
<%--    <th></th>--%>
<%--    <th></th>--%>
<%--    <%--%>
<%--        for(Movie movie : listaMovie){--%>
<%--    %>--%>
<%--    <tr>--%>

<%--        <td><%= movie.getId() %></td>--%>
<%--        <td><%= movie.getTitle() %></td>--%>
<%--        <td>--%>
<%--            <%--%>
<%--                for(Gender genero : movie.getGenders()){ %>--%>
<%--                    <%=genero.getName()%>--%>
<%--                <%}--%>
<%--            %>--%>
<%--        </td>--%>
<%--        <td>--%>
<%--            <%=movie.getVoteAverage()%>--%>
<%--        </td>--%>
<%--        <td>--%>
<%--            <form method="post" action="/movie/editar">--%>
<%--                <input type="hidden" name="id" value="<%= movie.getId() %>"/>--%>
<%--                <input type="submit" value="Editar"/>--%>
<%--            </form>--%>
<%--        </td>--%>
<%--        <td><a href="/movie/borrar?id=<%= movie.getId() %>">Borrar</a></td>--%>

<%--    </tr>--%>

<%--    <%--%>
<%--        }--%>
<%--    %>--%>
<%--</table>--%>
<%--<form method="post" action="/movie/editar">--%>
<%--    <input type="submit" value="Nueva Película" />--%>
<%--</form>--%>
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

        if(tipo === "editar"){
            fetch("/movie/editar", {
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
        }else{
            fetch("/movie/menuBorrar", {
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
                .catch(err => console.error("Error cargando modal:", err));   }
    }
</script>
</body>
</html>
