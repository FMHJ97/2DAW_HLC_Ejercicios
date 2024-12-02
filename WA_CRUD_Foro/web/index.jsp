<%-- 
    Document   : index
    Created on : Nov 30, 2024, 8:57:41 PM
    Author     : FMHJ97
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    if (session.getAttribute("login") != null) {
        request.getRequestDispatcher("foro.jsp").forward(request, response);
    }
%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Index - CRUD (Foro)</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
    </head>
    <body>
        <div class="container mt-5">
            <h1>Index - CRUD (Foro)</h1>
            <form action="s1" method="POST">
                <div class="my-3">
                    <label for="user">Usuario:</label>
                    <input type="text" class="form-control" id="user" placeholder="Introduzca usuario" name="user">
                </div>
                <div class="mb-3">
                    <label for="pwd">Contraseña:</label>
                    <input type="password" class="form-control" id="pwd" placeholder="Introduzca contraseña" name="pwd">
                </div>
                <button type="submit" class="btn btn-primary">Iniciar sesión</button>
            </form>
        </div>
    </body>
</html>
