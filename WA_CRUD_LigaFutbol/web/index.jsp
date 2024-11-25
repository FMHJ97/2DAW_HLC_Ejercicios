<%-- 
    Document   : index
    Created on : 25 nov 2024, 14:37:28
    Author     : FMHJ97
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.ConnMysql"%>
<%@page import="java.sql.*"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>WA (CRUD - Liga Fútbol)</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
    </head>
    <body>
        <div class="container">
            <div class="row">
                <h1>CRUD - Liga Fútbol</h1>
                <table class="table">
                    <thead>
                        <tr>
                            <th scope="col">#</th>
                            <th scope="col">Escudo</th>
                            <th scope="col">Equipo</th>
                            <th scope="col">Puntos</th>
                            <th scope="col">Victorias</th>
                            <th scope="col">Empates</th>
                            <th scope="col">Derrotas</th>
                            <th scope="col">Goles F</th>
                            <th scope="col">Goles C</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            try {
                                // Creamos el objeto conexion
                                Connection conn = new ConnMysql().getConnection();
                                // Creamos un objeto Statement
                                Statement instruccion = conn.createStatement(
                                        ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
                                // Creamos el query
                                String sql = "SELECT * from equipo ORDER BY puntos DESC";
                                ResultSet rs = instruccion.executeQuery(sql);
                                // Creamos una fila por cada registro.
                                int cont = 1;
                                while (rs.next()) {
                                    out.println("<tr>");
                                    out.println("<th scope='row'>" + cont + "</th>");
                                    out.println("<td></td>");
                                    out.println("<td>" + rs.getString(2) + "</td>");
                                    out.println("</tr>");
                                    cont++;
                                }
                                // Cerrar cada uno de los objetos utilizados
                                rs.close();
                                instruccion.close();
                                conn.close();
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                        %>
                    </tbody>
                </table>
            </div>
        </div>
    </body>
</html>
