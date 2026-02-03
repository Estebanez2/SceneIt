<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="java.util.List" %>
<%@ page import="es.uma.taw.sceneit.dto.*" %>
<%@ page import="java.text.NumberFormat" %>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
<link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.css" rel="stylesheet">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
    <head>
        <title>Película</title>
        <style>
            .icon-toggleNO input[type="checkbox"] {
                display: none; /* Oculta el checkbox */
            }

            /* Estado por defecto: círculo vacío */
            .icon-toggleNO input[type="checkbox"] ~ .bi-hand-thumbs-up-fill {
                display: none;
            }

            /* Si está marcado, se oculta el círculo vacío */
            .icon-toggleNO input[type="checkbox"]:checked ~ .bi-hand-thumbs-down-fill {
                display: none;
            }

            .icon-toggleNO i {
                cursor: pointer;
                font-size: 2rem;
            }

            /* Si está marcado, se muestra el círculo con check */
            .icon-toggleNO input[type="checkbox"]:checked ~ .bi-hand-thumbs-up-fill {
                display: inline;
            }

            .icon-toggle input[type="checkbox"] {
                display: none; /* Oculta el checkbox */
            }

            /* Estado por defecto: círculo vacío */
            .icon-toggle input[type="checkbox"] ~ .bi-hand-thumbs-up-fill {
                display: none;
            }

            /* Si está marcado, se oculta el círculo vacío */
            .icon-toggle input[type="checkbox"]:checked ~ .bi-hand-thumbs-down-fill {
                display: none;
            }

            .icon-toggle i {
                cursor: pointer;
                font-size: 2rem;
                color: red;
            }

            /* Si está marcado, se muestra el círculo con check */
            .icon-toggle input[type="checkbox"]:checked ~ .bi-hand-thumbs-up-fill {
                display: inline;
                color: green;
            }

        </style>
    </head>

    <%
    MovieDTO movie = (MovieDTO) request.getAttribute("movie");
    List<CastDTO> casts = (List<CastDTO>) request.getAttribute("cast");
    List<CrewDTO> crews = (List<CrewDTO>) request.getAttribute("crew");
    List<CommentaryDTO> comm = (List<CommentaryDTO>) request.getAttribute("commentaries");
    UserDTO userDTO = (UserDTO) session.getAttribute("user");
    InteractionDTO interaccionUsuario = (InteractionDTO) request.getAttribute("interaccionUsuario");
    %>

    <body>
    <div class="overflow-hidden h-100">
    <div class="position-fixed w-100">
        <div class="position-sticky">
            <jsp:include page="cabecera.jsp" />
        </div>
    </div>
    <div class="d-flex flex-wrap w-100 h-100 pt-5">
        <form method="get" action="/movie/" class="position-absolute" style="top:7%; left:2%">
            <button type="submit" class="btn btn-light rounded-circle" style="width:50px; height: 50px"><i class="bi bi-arrow-left"></i></button>
        </form>
        <img class="w-25 h-100 object-fit-cover shadow" src="<%=movie.getImage().isEmpty() ? "/NoFoto.png" : movie.getImage()%>">
        <div class="w-75 h-100 overflow-y-auto overflow-x-hidden p-3 ps-5 row">
            <div id="titulo" class="row">
                <h1 style="font-size: 5em"><%=movie.getTitle()%></h1>
                <div id="rating" class="d-flex flex-wrap">
                    <div class="m-2">
                        <h4>Rating</h4>
                    </div>
                    <div class="m-2">
                        <h4 class="d-flex flex-nowrap">
                            <%
                                double rating=movie.getVoteAverage();
                                int fullStars = (int) Math.floor(rating); // truncar decimales
                                int emptyStars = 5 - fullStars;
                                for(int i=0 ; i<fullStars; i++){
                            %>
                            <i class="bi bi-star-fill" name="<%=rating%>"></i>
                            <%
                                }
                                for(int i=0 ; i<emptyStars; i++){
                            %>
                            <i class="bi bi-star"></i>
                            <%
                                }
                            %>
                        </h4>
                    </div>

                    <%if(userDTO != null && userDTO.getUserEscritura()){%>
                        <div class="d-flex flex-column justify-content-center w-100 align-content-center">
                            <h4>¡Dinos tu opinión sobre <%=movie.getTitle()%>!</h4>
                            <form:form method="post" action="/movie/editarRating" modelAttribute="interaccionUsuario" class="d-flex flex-grow-1 flex-wrap w-50 justify-content-evenly my-2"> <%-- TODO RELLENAR ACTION Y HACERLO FORM:FORM --%>
                                <form:hidden path="interactue" />
                                <form:hidden path="id" />
                                <form:hidden path="idUser" value="<%=userDTO.getId()%>"/>
                                <form:hidden path="idMovie" value="<%=movie.getId()%>"/>
                                <div class="w-auto d-flex justify-content-center">
                                    <label for="nota" class="form-label fs-5">Evalúa la película: </label>
                                    <form:select path="valoration" class="form-select w-auto mx-2" id="nota">
                                        <form:option value="0" label="0"/>
                                        <form:option value="1" label="1"/>
                                        <form:option value="2" label="2"/>
                                        <form:option value="3" label="3"/>
                                        <form:option value="4" label="4"/>
                                        <form:option value="5" label="5"/>
                                    </form:select>
                                </div>
                                <label class="<%= (interaccionUsuario.getInteractue() == false) ? "icon-toggleNO" : "icon-toggle" %>">
                                <form:checkbox path="recomended" id="toggleCheck" />
                                    <i class="bi bi-hand-thumbs-down-fill"></i>
                                    <i class="bi bi-hand-thumbs-up-fill"></i>
                                </label>
                                <button class="btn btn-light">Subir opinión</button>
                            </form:form>
                        </div>

                    <%}%>
                </div>
            </div>
            <div class="row mt-2 mb-5">
                <div class="col-12 col-lg-8 ">
                    <div id="info" class="p-3 shadow rounded-4 row">
                        <h2>Más sobre la película</h2>
                        <div class="fs-5">
                            <div class="d-flex flex-wrap mt-2 mx-2 align-items-center <%=movie.getOriginalTitle()==null ? "" : "d-none"%>">
                                <strong class="m-2">Título original: </strong>
                                <span  class="m-2"  ><%=movie.getOriginalTitle()%></span>
                            </div>
                            <div>
                                <div class="<%=movie.getDate()==null ? "d-none" : ""%> d-flex flex-wrap mt-2 mx-2 align-items-center">
                                    <strong class="m-2">Fecha de estreno:</strong>
                                    <span  class="m-2"  ><%=movie.getDate()%></span>
                                </div>
                            </div>

                            <div class="<%=(movie.getLanguages()==null || movie.getLanguages().isEmpty()) ? "d-none" : ""%> d-flex flex-wrap mt-2 mx-2 align-items-center">
                                <strong class="m-2">Idiomas: </strong>
                                <%for(String l:movie.getNLanguages()){%>
                                <span  class="m-2 badge rounded-pill text-bg-light"  ><%=l%></span>
                                <%}%>
                            </div>
                            <div class="<%=(movie.getProductionCountries()==null || movie.getProductionCountries().isEmpty()) ? "d-none" : ""%> d-flex flex-wrap mt-2 mx-2 align-items-center">
                                <strong class="m-2">Países de Producción:</strong>
                                <%for(String l:movie.getNPrCountries()){%>
                                <span  class="m-2 badge rounded-pill text-bg-light"  ><%=l%></span>
                                <%}%>
                            </div>
                            <div class="<%=(movie.getProductionCompanies()==null || movie.getProductionCompanies().isEmpty()) ? "d-none" : ""%> d-flex flex-wrap mt-2 mx-2 align-items-center">
                                <strong class="m-2">Compañías de producción:</strong>
                                <%for(String l:movie.getNPrCompanies()){%>
                                <span  class="m-2 badge rounded-pill text-bg-light"  ><%=l%></span>
                                <%}%>
                            </div>
                            <div>
                                <div class="<%=movie.getRevenue()==null ? "d-none" : ""%> d-flex flex-wrap mt-2 mx-2 align-items-center">
                                    <strong class="m-2">Ganancias:</strong>
                                    <%
                                        NumberFormat formatter = NumberFormat.getInstance();
                                        String revenueFormatted = formatter.format(movie.getRevenue());
                                    %>
                                    <span  class="m-2"  ><%=revenueFormatted%> €</span>
                                </div>
                            </div>

                        </div>
                    </div>

                </div>
                <div class="col-12 col-lg-4 d-flex align-content-center">
                    <div id="relevante" class="align-self-center rounded-4 ms-3 shadow p-3 d-flex flex-column align-content-around fs-5">
                        <h2>Destacado</h2>
                        <div class="d-flex flex-column align-self-center h-100 justify-content-around py-5">
                            <span><i class="bi bi-calendar-fill"></i><strong> Fecha: </strong><%=movie.getDate()%></span>
                            <span><i class="bi bi-hash"> </i><strong>Votos de la comunidad:</strong> <%=movie.getVoteCount()==-1?0 : movie.getVoteCount()%></span>
                            <span><i class="bi bi-emoji-smile-fill"></i> <strong>Media de votos:</strong> <%=movie.getVoteAverage()==-1? "no hay" : String.format("%.2f", movie.getVoteAverage())%></span>
                            <span><i class="bi bi-chat-quote-fill"></i> <strong>Palabras clave:</strong><br/>
                            <%for(String k:movie.getNKeywords()){%>
                                <span class="badge rounded-pill shadow-sm text-black text-bg-light"><%=k%></span>
                            <%}%>
                        </span>
                        </div>
                    </div>
                </div>
            </div>


            <div id="cast" class="p-3 shadow rounded-4 my-5 row">
                <h2>Cast</h2>
                <div class="d-flex flex-nowrap overflow-x-auto">
                    <%for(CastDTO p: casts){%>
                    <div class="card rounded-4 overflow-hidden me-2 mb-2" style="min-width: 30%">
                        <div class="row g-0 h-100">
                            <div class="col-2 text-bg-dark">
                            </div>
                            <div class="col-10 d-flex flex-column justify-content-start" style="word-break: normal">
                                <div class="card-body w-100">
                                    <h5 class="card-title"><%=p.getCharacter()%></h5>
                                </div>
                                <div class="card-body w-100 border-top">
                                    <p class="card-text"><%=p.getNombreActor()%></p>
                                </div>
                            </div>
                        </div>
                    </div>
                    <%}%>
                </div>
            </div>
            <div id="crew" class="p-3 shadow rounded-4 my-5 row">
                <h2>Crew</h2>
                <div class="d-flex flex-wrap overflow-x-auto">
                    <%for(CrewDTO p: crews){%>
                    <div class="card rounded-4 overflow-hidden me-2 mb-2" style="min-width: 30%">
                        <div class="row g-0">
                            <div class="col-2 text-bg-dark">
                            </div>
                            <div class="col-10 d-flex flex-column align-items-center justify-content-start">
                                <div class="card-body w-100">
                                    <h5 class="card-title"><%=p.getNombrePersona()%></h5>
                                </div>
                                <div class="card-body w-100 border-top">
                                    <p class="card-text"><%=p.getJob()%></p>
                                </div>
                            </div>
                        </div>
                    </div>
                    <%}%>
                </div>
            </div>
            <div id="comentarios" class="row w-100 rounded-4 shadow p-2">
                <h2>Comentarios: </h2>
                <div class="row overflow-y-auto overflow-x-hidden w-100 p-3" style="max-height: 500px">
                    <%--                   TODO Div con los comentarios--%>
                    <%if(!comm.isEmpty()){%>
                        <% for(CommentaryDTO c: comm) { %>
                        <div class="mx-2 my-3 text-bg-light w-100 rounded py-2 px-3">
                            <span class="fs-5"><strong><%= c.getNameUserInteraction() %></strong></span>
                            <p id="comment-content-<%=c.getIdCom()%>"><%= c.getContent() %></p>
                            <div class="d-flex w-100 justify-content-between pe-2">
                                <span class="text-end"><%= c.getDate() %></span>
                                <% if( (userDTO != null && userDTO.getId().equals(c.getIdUserCom())) || (userDTO != null && userDTO.getAdmin()) ) { %>
                                    <button class="btn btn-sm btn-outline-secondary"
                                            id="btn-editar-<%=c.getIdCom()%>"
                                            onclick="editarComentario('<%=c.getIdCom()%>', '<%=c.getContent().replace("'", "\\'")%>', '<%=c.getIdUserCom()%>', '<%=c.getIdInteractionCom()%>')">
                                        <i class="bi bi-pencil-square"></i> Editar
                                    </button>
                                <form method="post" action="/movie/borrarComentario" class="d-inline">
                                    <input type="hidden" name="idBorrar" value="<%=c.getIdCom()%>">
                                    <input type="hidden" name="idPelicula" value="<%=movie.getId()%>">
                                    <button type="submit" class="btn btn-sm btn-outline-danger" onclick="return confirm('¿Seguro que quieres borrar este comentario?')">
                                        <i class="bi bi-trash-fill"></i> Borrar
                                    </button>
                                </form>
                                <% } %>
                            </div>
                        </div>
                        <% } %>
                    <%}else{%>
                        <div class="h-100 d-flex justify-content-center align-content-center">
                            <span class="fs-5 p-2"><i class="bi b bi-emoji-surprise-fill me-2"></i> Todavía no hay comentarios, ¡sé el primero!</span>
                        </div>
                    <%}%>


                </div>
                <%if(userDTO!=null && userDTO.getUserEscritura()){%>
                <form:form modelAttribute="nuevoComentario" method="post"  action="/movie/editarComentario" class="w-100 row my-2">
                    <%--                   TODO Div con textarea para escribir comentarios--%>
                    <label for="comentario" class="col-auto"><i class="bi bi-person-circle fs-3"></i></label>
                    <form:textarea readonly="false" path="content" class="form-control col" rows="2" id="comentario" style="resize: none; overflow-y: auto; max-height: calc(1.5em * 5); min-height: calc(1.5em * 2);"></form:textarea>
                    <form:hidden path="idCom"></form:hidden>
                    <form:hidden path="date"></form:hidden>
                    <form:hidden path="idInteractionCom" value="<%=interaccionUsuario.getId()%>"></form:hidden>
                    <form:hidden path="idMovie" value="<%=movie.getId()%>"></form:hidden>
                    <form:hidden path="idUserCom" value="<%=userDTO.getId()%>"></form:hidden>
                    <form:button class="border-0 col-auto fs-4 py-2 px-3 d-flex align-content-end" style="background: none; cursor: pointer"><i class="bi bi-send-fill align-self-end"></i></form:button>
                </form:form>
                <%}%>
            </div>
        </div>

    </div>
    </div>
    <script>
        const textarea = document.getElementById('comentario');
        const lineHeight = parseFloat(getComputedStyle(textarea).lineHeight);
        const maxHeight = lineHeight * 5;

        textarea.addEventListener('input', () => {
            textarea.style.height = 'auto'; // reset to shrink if needed
            textarea.style.height = Math.min(textarea.scrollHeight, maxHeight) + 'px';
            textarea.style.overflowY = textarea.scrollHeight > maxHeight ? 'auto' : 'hidden';
        });
    </script>
    <script>
        let comentarioEditandoId = -1;

        function editarComentario(id, contenido, idUserOriginal, idInteraccionOriginal) {
            const textarea = document.getElementById("comentario");
            const idInput = document.querySelector("input[name='idCom']");
            const button = document.querySelector("form button");
            const btnEditar = document.getElementById("btn-editar-" + id);
            const idUserInput = document.querySelector("input[name='idUserCom']");
            const idInteraccionInput = document.querySelector("input[name='idInteractionCom']");

            // Si ya se está editando este comentario, desactiva edición
            if (comentarioEditandoId === id) {
                comentarioEditandoId = -1;
                textarea.value = "";
                idInput.value = "-1";
                idUserInput.value = "<%= (userDTO != null) ? userDTO.getId() : "" %>";
                idInteraccionInput.value = "<%= (interaccionUsuario != null) ? interaccionUsuario.getId() : "" %>";
                button.innerHTML = '<i class="bi bi-send-fill align-self-end"></i>';
                resetAllEditButtons();
            } else {
                // Si se edita un comentario nuevo, actualiza el contenido y cambia el botón
                comentarioEditandoId = id;
                textarea.value = contenido;
                idInput.value = id;
                idUserInput.value = idUserOriginal;
                idInteraccionInput.value = idInteraccionOriginal;
                button.innerHTML = '<i class="bi bi-pencil-fill align-self-end"></i>';

                resetAllEditButtons();
                btnEditar.classList.remove("btn-outline-secondary");
                btnEditar.classList.add("btn-primary", "text-white");
            }
        }

        function resetAllEditButtons() {
            const buttons = document.querySelectorAll("button[id^='btn-editar-']");
            buttons.forEach(btn => {
                btn.classList.remove("btn-primary", "text-white");
                btn.classList.add("btn-outline-secondary");
            });
        }
    </script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
            crossorigin="anonymous"></script>
    </body>
</html>