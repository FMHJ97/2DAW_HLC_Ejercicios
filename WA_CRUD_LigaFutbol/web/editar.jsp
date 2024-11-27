<%-- 
    Document   : editar
    Created on : Nov 26, 2024, 8:47:05 PM
    Author     : FMHJ97
--%>

<%@page import="model.ConnMysql"%>
<%@page import="java.sql.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    ResultSet registro = (ResultSet) session.getAttribute("registro");
    ResultSet equipos = (ResultSet) session.getAttribute("equipos");

    // Equipo local y visitante del registro.
    int eq_local = registro.getInt(2);
    int eq_visit = registro.getInt(3);
%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Editar - CRUD (Liga Fútbol)</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
    </head>
    <body>
        <h1>Editar - CRUD (Liga Fútbol)</h1>
        <form action="s3" method="POST">
            <select class="form-select" name="eq_local">
                <%
                    try {
                        while (equipos.next()) {
                            int id_equipo = equipos.getInt(1);
                            String nombre_equipo = equipos.getString(2);
                %>
                <option value="<%= id_equipo%>" 
                        <% if (id_equipo == eq_local) out.print("selected");%>>
                        <%= nombre_equipo%>
                </option>
                <%
                        }
                        equipos.absolute(1); // Reiniciamos cursor.
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                %>
            </select>
            <input type="number" name="goles_local" value="<% out.println(goles_local); %>">
            <select class="form-select" name="eq_visit" value="<% out.println(goles_visit); %>">
                <%
                    try {
                        // Creamos el objeto conexion
                        Connection conn = new ConnMysql().getConnection();
                        // Creamos un objeto Statement
                        Statement instruccion = conn.createStatement(
                                ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
                        // Creamos el query
                        String sql = "SELECT * FROM equipo";
                        ResultSet rs = instruccion.executeQuery(sql);
                        while (rs.next()) {
                            out.println("<option value='" + rs.getInt(1) + "'");
                            // Seleccionamos el equipo según el registro para editar.
                            if (rs.getInt(1) == eq_visit) {
                                out.println("selected=''>");
                            }
                            out.println(rs.getString(2));
                            out.println("</option>");
                        }
                        // Cerrar cada uno de los objetos utilizados
                        rs.close();
                        instruccion.close();
                        conn.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                %>
            </select>
            <input type="number" name="goles_visit">
            <button type="submit" name="update" value="">Actualizar</button>
        </form>
    </body>
</html>
