<%-- 
    Document   : conductor
    Created on : 11 dic 2024, 12:36:48
    Author     : FMHJ97
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.ConnMysql"%>
<%@page import="java.sql.*"%>

<%
    if (session.getAttribute("autenticado") == null) {
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

    // Saco el valor del atributo Autenticado de la sesión.
    Object[] conductor = (Object[]) session.getAttribute("autenticado");
    // Datos.
    String dni = (String) conductor[0];
    String nombre = (String) conductor[1];
    int puntos = (int) conductor[2];
%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Examen HLC - DGT (Conductor)</title>
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
            .insert {
                background-color: lightgreen;
            }
            .btnCerrar {
                margin-top: 1em;
                margin-bottom: 1em;
            }
        </style>
    </head>
    <body>
        <img src="img/logo.png" width="300px" height="150px"/>
        <h1><% out.println(nombre); %> <span style="background-color: yellow">Puntos: <% out.println(puntos); %></span></h1>
        <h2>Consulta y gestiona los coches asociados a tu cuenta</h2>
        <form action="s2">
            <button class="btnCerrar" type="submit" name="logout">Cerrar sesión</button>
        </form>
        <table>
            <thead>
                <tr>
                    <th>Matrícula</th>
                    <th>Modelo</th>
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
                        String sql = "SELECT * FROM coche WHERE propietario = '" + dni + "'";
                        ResultSet rs = instruccion.executeQuery(sql);
                        // Insertamos cada registro en la tabla.
                        while (rs.next()) {
                            out.println("<tr>");
                            out.println("<td>" + rs.getString("matricula") + "</td>");
                            out.println("<td>" + rs.getString("modelo") + "</td>");
                            out.println("<form action='s3' method='POST'>");
                            out.println("<td>");
                            out.println("<button type='submit' name='borrar' value='" + rs.getString("matricula") + "'>Borrar</button>");
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
                <tr>
                    <th class="insert" colspan="3">Añadir Nuevo Coche</th>
                </tr>
                <tr>
            <form action="s3" method="POST">
                <td><input type="text" name="matricula" placeholder="Introduce la matrícula"></td> 
                <td><input type="text" name="modelo" placeholder="Introduce el modelo"></td> 
                <td><button type="submit" name="insert">Añadir Coche</button></td> 
            </form>
        </tr>
    </tbody>
</table>
<%
    if (request.getAttribute("msg2") != null) {
        String msg = (String) request.getAttribute("msg2");
        out.println("<br>");
        out.println(msg);
    }
%>
</body>
</html>
