<%@ page import="java.util.List" %>
<%@ page import="es.uma.taw.sceneit.dto.*" %>
<%@ page import="es.uma.taw.sceneit.ui.FiltroStatistics" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Estadísticas</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.css" rel="stylesheet">
</head>
<%
    request.setAttribute("pagina", "statistics");
    List<MovieDTO> peliculas = (List<MovieDTO>) request.getAttribute("peliculas");
    List<KeywordDTO> keywords = (List<KeywordDTO>) request.getAttribute("keywords");
    List<GenderDTO> generos = (List<GenderDTO>) request.getAttribute("generos");
    List<LanguageDTO> idiomas = (List<LanguageDTO>) request.getAttribute("idiomas");
    List<ProductionCountryDTO> paises = (List<ProductionCountryDTO>) request.getAttribute("paises");
    List<ProductionCompanyDTO> companias = (List<ProductionCompanyDTO>) request.getAttribute("companias");
    List<CrewDTO> crew = (List<CrewDTO>) request.getAttribute("crew");
    List<CastDTO> cast = (List<CastDTO>) request.getAttribute("cast");
    List<PersonDTO> personas = (List<PersonDTO>) request.getAttribute("personas");
%>
<body>
<jsp:include page="cabecera.jsp" />

<div class="container mt-5">
    <div class="card shadow mb-4">
        <div class="card-body">
            <h2 class="card-title text-center mb-4">Estadísticas Generales</h2>

            <!-- Mostrar las estadísticas base -->
            <div class="row">
                <div class="col-md-4">
                    <p><strong>Películas:</strong> ${peliculas != null ? peliculas.size() : 0}</p>
                </div>
                <div class="col-md-4">
                    <p><strong>Keywords:</strong> ${keywords != null ? keywords.size() : 0}</p>
                </div>
                <div class="col-md-4">
                    <p><strong>Géneros:</strong> ${generos != null ? generos.size() : 0}</p>
                </div>
            </div>

            <div class="row">
                <div class="col-md-4">
                    <p><strong>Idiomas:</strong> ${idiomas != null ? idiomas.size() : 0}</p>
                </div>
                <div class="col-md-4">
                    <p><strong>Países de Producción:</strong> ${paises != null ? paises.size() : 0}</p>
                </div>
                <div class="col-md-4">
                    <p><strong>Compañías de Producción:</strong> ${companias != null ? companias.size() : 0}</p>
                </div>
            </div>

            <div class="row">
                <div class="col-md-4">
                    <p><strong>Crew:</strong> ${crew != null ? crew.size() : 0}</p>
                </div>
                <div class="col-md-4">
                    <p><strong>Cast:</strong> ${cast != null ? cast.size() : 0}</p>
                </div>
                <div class="col-md-4">
                    <p><strong>Personas:</strong> ${personas != null ? personas.size() : 0}</p>
                </div>
            </div>
        </div>
    </div>


    <!-- Mostrar todos los de un tipo -->
    <div class="card shadow">
        <div class="card-body">
            <h2 class="card-title text-center mb-4">Filtrar por Tipo de Dato</h2>

            <form:form method="post" modelAttribute="filtroStatistics" action="/statistics/verTodosUnTipo" class="mb-3">
                <div class="row align-items-center">
                    <div class="col-md-10">
                        <h2>Selecciona el tipo de dato:<h2>
                        <form:select path="tipoDato" items="${posibles}" class="form-select"></form:select>
                    </div>
                    <div class="col-md-2 mt-4 mt-md-0 text-center">
                        <form:button class="btn btn-primary w-100" type="submit">Ver</form:button>
                    </div>
                </div>
            </form:form>
        </div>
    </div>

    <%-- Mostrar el tipo selecionado --%>
    <table class="table table-striped align-top text-center"  style="width: 100%">
        <tr>
            <td style="width: 50%;">
                <%
                    FiltroStatistics filtroStatistics = (FiltroStatistics) request.getAttribute("filtroStatistics");
                    String tipoDato = filtroStatistics != null ? filtroStatistics.getTipoDato() : null;
                    if (tipoDato != null && tipoDato.equals("Peliculas")) {
                %>
                <form:form method="post" modelAttribute="filtroStatistics" action="/statistics/filtrar" class="d-flex flex-nowrap bg-body-tertiary py-2 px-4 shadow w-60 rounded-pill me-4 my-2 flex-grow-1" style="min-width: 200px; flex: 1 1 auto">
                    <div class="input-group d-flex">
                        <form:input path="nombre" type="search" placeholder="Filtrar por nombre..." class="d-flex flex-grow-1 flex-fill w-25 form-control rounded-start-pill"/>
                        <form:select path="genre" class="form-select flex-shrink-1 bg-body-secondary" style="cursor: pointer; flex-shrink: 0; min-width: 180px;">
                            <form:option value="-1">Todos los géneros</form:option>
                            <form:options items="${generos}" itemValue="id" itemLabel="name" />
                        </form:select>
                        <button class="btn btn-light bg-body-secondary form-select border-start border flex-shrink-1" type="button" data-bs-toggle="dropdown" data-bs-auto-close="outside" aria-expanded="false">Idiomas</button>
                        <div class="dropdown-menu p-0">
                            <form:select path="languages"  multiple="true" class="py-3 ps-2 pe-0 form-select flex-shrink-1 bg-body-secondary" style="cursor: pointer">
                                <form:option value="-1">Todos los idiomas</form:option>
                                <form:options items="${idiomas}" itemLabel="name" itemValue="id"></form:options>
                            </form:select>
                        </div>
                        <form:button class="btn btn-primary rounded-end-pill"><i class="bi bi-search"></i></form:button>
                        <form:hidden path="tipoDato"></form:hidden>
                    </div>
                </form:form>
                <%
                } else if(tipoDato != null && tipoDato.equals("Keywords")) {
                %>
                    <form:form method="post" action="/statistics/filtrar" modelAttribute="filtroStatistics" class="d-flex flex-nowrap bg-body-tertiary py-2 px-4 shadow w-60 rounded-pill me-4 my-2 flex-grow-1" style="min-width: 200px; flex: 1 1 auto">
                        <form:input path="nombre" class="form-control me-2" placeholder="Filtrar por nombre..." />
                        <form:hidden path="tipoDato" value="<%= tipoDato %>"></form:hidden>
                        <form:button class="btn btn-primary rounded-end-pill"><i class="bi bi-search"></i></form:button>
                    </form:form>
                <%
                } else if(tipoDato != null && tipoDato.equals("Generos")) {
                %>
                    <form:form method="post" action="/statistics/filtrar" modelAttribute="filtroStatistics" class="d-flex flex-nowrap bg-body-tertiary py-2 px-4 shadow w-60 rounded-pill me-4 my-2 flex-grow-1" style="min-width: 200px; flex: 1 1 auto">
                        <form:input path="nombre" class="form-control me-2" placeholder="Filtrar por nombre..." />
                        <form:hidden path="tipoDato" value="<%= tipoDato %>"></form:hidden>
                        <form:button class="btn btn-primary rounded-end-pill"><i class="bi bi-search"></i></form:button>
                    </form:form>
                <%
                } else if(tipoDato != null && tipoDato.equals("Idiomas")) {
                %>
                    <form:form method="post" action="/statistics/filtrar" modelAttribute="filtroStatistics" class="d-flex flex-nowrap bg-body-tertiary py-2 px-4 shadow w-60 rounded-pill me-4 my-2 flex-grow-1" style="min-width: 200px; flex: 1 1 auto">
                        <form:input path="nombre" class="form-control me-2" placeholder="Filtrar por nombre..." />
                        <form:hidden path="tipoDato" value="<%= tipoDato %>"></form:hidden>
                        <form:button class="btn btn-primary rounded-end-pill"><i class="bi bi-search"></i></form:button>
                    </form:form>
                <%
                } else if(tipoDato != null && tipoDato.equals("Companias")) {
                %>
                    <form:form method="post" action="/statistics/filtrar" modelAttribute="filtroStatistics" class="d-flex flex-nowrap bg-body-tertiary py-2 px-4 shadow w-60 rounded-pill me-4 my-2 flex-grow-1" style="min-width: 200px; flex: 1 1 auto">
                        <form:input path="nombre" class="form-control me-2" placeholder="Filtrar por nombre..." />
                        <form:hidden path="tipoDato" value="<%= tipoDato %>"></form:hidden>
                        <form:button class="btn btn-primary rounded-end-pill"><i class="bi bi-search"></i></form:button>
                    </form:form>
                <%
                } else if(tipoDato != null && tipoDato.equals("Paises")) {
                %>
                    <form:form method="post" action="/statistics/filtrar" modelAttribute="filtroStatistics" class="d-flex flex-nowrap bg-body-tertiary py-2 px-4 shadow w-60 rounded-pill me-4 my-2 flex-grow-1" style="min-width: 200px; flex: 1 1 auto">
                        <form:input path="nombre" class="form-control me-2" placeholder="Filtrar por nombre..." />
                        <form:hidden path="tipoDato" value="<%= tipoDato %>"></form:hidden>
                        <form:button class="btn btn-primary rounded-end-pill"><i class="bi bi-search"></i></form:button>
                    </form:form>
                <%
                    } else if(tipoDato != null && tipoDato.equals("Cast")) {
                %>
                    <form:form method="post" action="/statistics/filtrar" modelAttribute="filtroStatistics" class="d-flex flex-nowrap bg-body-tertiary py-2 px-4 shadow w-60 rounded-pill me-4 my-2 flex-grow-1" style="min-width: 200px; flex: 1 1 auto">
                        <form:select path="tipo" items="${listaCast}" cssClass="form-select me-2" style="max-width: 200px;"></form:select>
                        <form:input path="nombre" class="form-control me-2" placeholder="Filtrar por nombre..." />
                        <form:hidden path="tipoDato" value="<%= tipoDato %>"></form:hidden>
                        <form:button class="btn btn-primary rounded-end-pill"><i class="bi bi-search"></i></form:button>
                    </form:form>
                <%
                } else if(tipoDato != null && tipoDato.equals("Crew")) {
                %>
                    <form:form method="post" action="/statistics/filtrar" modelAttribute="filtroStatistics" class="d-flex flex-nowrap bg-body-tertiary py-2 px-4 shadow w-60 rounded-pill me-4 my-2 flex-grow-1" style="min-width: 200px; flex: 1 1 auto">
                        <form:select path="tipo" items="${listaCrew}" cssClass="form-select me-2" style="max-width: 200px;"></form:select>

                        <form:input path="nombre" class="form-control me-2" placeholder="Filtrar por nombre..." />

                        <form:button class="btn btn-primary rounded-end-pill"><i class="bi bi-search"></i></form:button>
                        <form:hidden path="tipoDato" value="<%= tipoDato %>"></form:hidden>
                    </form:form>
                <%
                } else if(tipoDato != null && tipoDato.equals("Personas")) {
                %>
                    <form:form method="post" action="/statistics/filtrar" modelAttribute="filtroStatistics" class="d-flex flex-nowrap bg-body-tertiary py-2 px-4 shadow w-60 rounded-pill me-4 my-2 flex-grow-1" style="min-width: 200px; flex: 1 1 auto">
                            <form:input path="nombre" class="form-control" placeholder="Filtrar por nombre..." />

                            <form:select path="id" class="form-select">
                                <form:option value="-1" label="Género (Todos)" />
                                <form:option value="0" label="Hombre" />
                                <form:option value="1" label="Mujer" />
                                <form:option value="2" label="Otro" />
                            </form:select>


                            <form:select path="esActor" class="form-select">
                                <form:option value="" label="Actor (Todos)" />
                                <form:option value="true" label="Actor" />
                                <form:option value="false" label="No Actor" />
                            </form:select>



                            <form:button class="btn btn-primary rounded-end-pill"><i class="bi bi-search"></i></form:button>


                        <form:hidden path="tipoDato"></form:hidden>
                    </form:form>
                <%
                    }
                %>

            </td>
            <td>
                <%
                    tipoDato = filtroStatistics.getTipoDato();
                    if (tipoDato != null) {
                        switch (tipoDato) {
                            case "Peliculas":
                %>

                <div class="table-responsive">
                    <table class="table table-striped align-middle text-center">
                        <thead class="table-light">
                        <tr>
                            <th>Películas</th>
                        </tr>
                        </thead>
                        <tbody>
                        <%
                            for(MovieDTO m : peliculas){
                        %>
                        <tr>
                            <td><%= m.getTitle()%></td>
                        </tr>
                        <%
                            }
                        %>
                        </tbody>
                    </table>
                </div>
                <%
                        break;
                    case "Keywords":
                %>
                <div class="table-responsive">
                    <table class="table table-striped align-middle text-center">
                        <thead class="table-light">
                        <tr>
                            <th>Keywords</th>
                        </tr>
                        </thead>
                        <tbody>
                        <%
                            for(KeywordDTO kw : keywords){
                        %>
                        <tr>
                            <td><%= kw.getName() %></td>
                        </tr>
                        <%
                            }
                        %>
                        </tbody>
                    </table>
                </div>
                <%
                        break;
                    case "Generos":
                %>
                <div class="table-responsive">
                    <table class="table table-striped align-middle text-center">
                        <thead class="table-light">
                        <tr>
                            <th>Géneros</th>
                        </tr>
                        </thead>
                        <tbody>
                        <%
                            for(GenderDTO g : generos){
                        %>
                        <tr>
                            <td><%= g.getName() %></td>
                        </tr>
                        <%
                            }
                        %>
                        </tbody>
                    </table>
                </div>
                <%
                        break;
                    case "Idiomas":
                %>
                <div class="table-responsive">
                    <table class="table table-striped align-middle text-center">
                        <thead class="table-light">
                        <tr>
                            <th>Idiomas</th>
                        </tr>
                        </thead>
                        <tbody>
                        <%
                            for(LanguageDTO l : idiomas){
                        %>
                        <tr>
                            <td><%= l.getName() %></td>
                        </tr>
                        <%
                            }
                        %>
                        </tbody>
                    </table>
                </div>
                <%
                        break;
                    case "Paises":
                %>
                <div class="table-responsive">
                    <table class="table table-striped align-middle text-center">
                        <thead class="table-light">
                        <tr>
                            <th>Paises</th>
                        </tr>
                        </thead>
                        <tbody>
                        <%
                            for(ProductionCountryDTO p : paises){
                        %>
                        <tr>
                            <td><%= p.getName() %></td>
                        </tr>
                        <%
                            }
                        %>
                        </tbody>
                    </table>
                </div>
                <%
                        break;
                    case "Companias":
                %>
                <div class="table-responsive">
                    <table class="table table-striped align-middle text-center">
                        <thead class="table-light">
                        <tr>
                            <th>Compañías</th>
                        </tr>
                        </thead>
                        <tbody>
                        <%
                            for(ProductionCompanyDTO p : companias){
                        %>
                        <tr>
                            <td><%= p.getName() %></td>
                        </tr>
                        <%
                            }
                        %>
                        </tbody>
                    </table>
                </div>
                <%
                        break;
                    case "Crew":
                %>
                <div class="table-responsive">
                    <table class="table table-striped align-middle text-center">
                        <thead class="table-light">
                        <tr>
                            <th>Crew</th>
                        </tr>
                        </thead>
                        <tbody>
                        <%
                            for(CrewDTO c : crew){
                        %>
                        <tr>
                            <td><%= c.getNombrePersona()%> --- Puesto: <%= c.getJob()%> --- Empresa: <%= c.getNombreProductCompany()%></td>
                        </tr>
                        <%
                            }
                        %>
                        </tbody>
                    </table>
                </div>
                <%
                        break;
                    case "Cast":
                %>
                <div class="table-responsive">
                    <table class="table table-striped align-middle text-center">
                        <thead class="table-light">
                        <tr>
                            <th>Cast</th>
                        </tr>
                        </thead>
                        <tbody>
                        <%
                            for(CastDTO c : cast){
                        %>
                        <tr>
                            <td><%= c.getCharacter() %> --- Actor: <%= c.getNombreActor() %> --- Película: <%= c.getNombrePelicula() %></td>
                        </tr>
                        <%
                            }
                        %>
                        </tbody>
                    </table>
                </div>
                <%
                        break;
                    case "Personas":
                %>
                <div class="table-responsive">
                    <table class="table table-striped align-middle text-center">
                        <thead class="table-light">
                        <tr>
                            <th>Personas</th>
                        </tr>
                        </thead>
                        <tbody>
                        <%
                            for(PersonDTO p : personas){
                        %>
                        <tr>
                            <td><%= p.getName() %> --- Genero: <%=p.getGenderString(p.getGender())%> --- Actor: <%=p.getActor()==true?"Si":"No"%></td>
                        </tr>
                        <%
                            }
                        %>
                        </tbody>
                    </table>
                </div>
                <%
                        break;
                    default:
                        }
                    }
                %>
            </td>
        </tr>
    </table>





</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
