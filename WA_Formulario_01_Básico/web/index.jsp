<%-- 
    Document   : index
    Created on : 7 oct 2024, 13:38:33
    Author     : FMHJ97
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>WA - Formulario 01</title>
    </head>
    <body>
        <h1>WA - Formulario 01</h1>
        <form action="s1" method="post">
            <!-- Checkboxes -->
            <p>Seleccione sus intereses:</p>
            <input type="checkbox" name="intereses[]" value="Peliculas"><label>Películas</label>
            <input type="checkbox" name="intereses[]" value="Libros"><label>Libros</label>
            <input type="checkbox" name="intereses[]" value="Videojuegos"><label>Videojuegos</label>
            <br>
            <!-- Selección Simple -->
            <p>Seleccione un país:</p>
            <select name="pais">
                <option value="Spain">España</option>
                <option value="Francia">Francia</option>
                <option value="Portugal">Portugal</option>
            </select>
            <br>
            <!-- Selección RadioButton -->
            <p>Seleccione un color:
                <input type="radio" name="color" value="Rojo" checked><label>Rojo</label>
                <input type="radio" name="color" value="Verde"><label>Verde</label>
                <input type="radio" name="color" value="Azul"><label>Azul</label>
            </p>
            <button type="submit" name="enviar">Enviar</button>            
        </form>
    </body>
</html>
