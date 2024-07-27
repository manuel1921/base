<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Actualizar Actor</title>
</head>

<body style="background-image:url(https://png.pngtree.com/background/20230519/original/pngtree-red-carpet-for-fashion-show-picture-image_2666949.jpg); height:100vh; display:flex; flex-direction:column; justify-content:center; align-items:center">

    <h1 style="background:#AA000085;padding:25px;color:white; border-radius:15px;box-shadow:10px 5px 50px black">Actualizar Actor</h1>
    
    <form action="ControladorActor" name="form" method="get">
        <input type="hidden" name="instruccion" value="actualizarBBDD">
        <input type="hidden" name="id_Actor" value="${id_actorCargar.actor_id}">
        
        <table style="background:#AA000085;padding:25px;color:white;border-radius:15px;box-shadow:10px 5px 50px black">
            <tr>
                <td>Nombre Actor</td>
                <td><input type="text" name="nombreA" value="${id_actorCargar.first_name}"></td>
            </tr>
            <tr>
                <td>Apellido Actor</td>
                <td><input type="text" name="apellidoA" value="${id_actorCargar.last_name}"></td>
            </tr>
            <tr>
                <td>Actualización</td>
                <td><input type="text" name="actualizacion" value="${id_actorCargar.last_update}"></td>
            </tr>
            <tr>
                <td colspan="2">
                    <input type="submit" value="actualizar">
                    <input type="reset" value="borrar">
                </td>
            </tr>
        </table>
    </form>
</body>

</html>