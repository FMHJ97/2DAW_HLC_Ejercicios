<%-- 
    Document   : admin
    Created on : 11 dic 2024, 12:36:34
    Author     : FMHJ97
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.ConnMysql"%>
<%@page import="java.sql.*"%>

<%
    if (session.getAttribute("autenticado") == null) {
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }
%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Examen HLC - DGT (Admin)</title>
        <style>
            table {
                border-collapse: collapse;
            }
            th, td {
                border: 2px solid black;
                padding: 1.3em;
                text-align: center;
                font-size: 1.1rem;
            }
            th{
                background-color: lightblue;
            }
            .btnCerrar {
                margin-top: 1em;
                margin-bottom: 1em;
            }
        </style>
    </head>
    <body>
        <img src="img/logo.png" width="300px" height="150px"/>
        <h1>Gestión de Puntos de Conductores</h1>
        <form action="s2">
            <button class="btnCerrar" type="submit" name="logout">Cerrar sesión</button>
        </form>
        <table>
            <thead>
                <tr>
                    <th>DNI</th>
                    <th>Nombre</th>
                    <th>Puntos</th>
                    <th></th>
                </tr>
            </thead>
            <tbody>
                <%
                    // Consulta en la BD para cargar todos los clientes (no admin).
                    try {
                        // Creamos el objeto conexion
                        Connection conn = new ConnMysql().getConnection();
                        // Creamos un objeto Statement
                        Statement instruccion = conn.createStatement(
                                ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
                        // Creamos el query
                        String sql = "SELECT * FROM usuario WHERE admin = 0";
                        ResultSet rs = instruccion.executeQuery(sql);
                        // Insertamos cada registro en la tabla.
                        while (rs.next()) {
                            out.println("<tr>");
                            out.println("<td>" + rs.getString("dni") + "</td>");
                            out.println("<td>" + rs.getString("nombre") + "</td>");
                            out.println("<form action='s2' method='POST'>");
                            out.println("<td>");
                            out.println("<input type='number' name='puntos' value='" + rs.getInt("puntos") + "' min='0' max='15'>");
                            out.println("</td>");
                            out.println("<td>");
                            out.println("<button type='submit' name='guardar' value='" + rs.getString("dni") + "'>Guardar</button>");
                            out.println("</td>");
                            out.println("</form>");
                            out.println("</tr>");
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
    </body>
</html>
