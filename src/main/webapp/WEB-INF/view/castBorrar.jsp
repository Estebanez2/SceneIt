<%@ page import="es.uma.taw.sceneit.dto.CastDTO" %>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
<link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.css" rel="stylesheet">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    CastDTO cast = (CastDTO) request.getAttribute("cast");
%>

<div class="modal fade" tabindex="-1" id="borrarModal">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
            <div class="modal-header">
                <h3>¿Quieres borrar el Cast <%=cast.getCharacter()%>?</h3>
                <button type="button" class="btn-close" data-bs-dismiss="modal"
                        aria-label="Close">

                </button>
            </div>
            <div class="modal-body">

                <form method="post" action="/cast/borrar">
                    <input type="hidden" name="id" value="<%=cast.getId()%>">
                    <button type="submit" class="btn btn-primary w-100">Borrar</button>
                </form>
            </div>
        </div>
    </div>

</div>