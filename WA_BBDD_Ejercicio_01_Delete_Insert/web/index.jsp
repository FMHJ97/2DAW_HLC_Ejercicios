<%-- 
    Document   : index
    Created on : 22 nov 2024, 12:06:38
    Author     : FMHJ97
--%>

<%@page import="model.ConnMysql"%>
<%@page import="java.sql.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>WA_BBDD_Ejercicio_01_CRUD</title>
    </head>
    <style>
        table, th, td {
            border: 3px solid black;
            border-collapse: collapse;
        }
        th, td {
            text-align: center;
            padding: 1em;
            font-size: 1.2rem;
        }
        th {
            background-color: lightblue;
        }
        button, input {
            font-size: 1.2rem;
        }
    </style>
    <body>
        <h1>Datos de la BD</h1>
        <form action="s1" method="POST">
            <table>
                <tr>
                    <th>ID</th>
                    <th>Nombre</th>
                    <th>Nota</th>
                    <th>Fecha Nacimiento</th>
                    <th>Acción</th>
                </tr>
            <%
                try {
                    // Creamos el objeto conexion
                    Connection conn = new ConnMysql().getConnection();
                    // Creamos un objeto Statement
                    Statement instruccion = conn.createStatement(
                            ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
                    // Creamos el query
                    String sql = "select * from datos";
                    ResultSet rs = instruccion.executeQuery(sql);
                    // Creamos una fila por cada registro.
                    while (rs.next()) {
                        out.println("<tr>");
                        out.println("<td>" + rs.getInt(1) + "</td>");
                        out.println("<td>" + rs.getString(2) + "</td>");
                        out.println("<td>" + rs.getInt(3) + "</td>");
                        out.println("<td>" + rs.getDate(4) + "</td>");
                        out.println("<td>");
                        out.println("<button type='submit' name='borrar' value='" + rs.getInt(1) + "'>Borrar</button>");
                        out.println("</td>");
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
                <td></td>
                <td><input type="text" name="nombre"></td>
                <td><input type="text" name="nota"></td>
                <td><input type="text" name="fecha_nac"></td>
                <td><input type="submit" name="insertar" value="Insertar"></td>
            </tr>
            </table>
        </form>
    </body>
</html>
