<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insetar Dato</title>
</head>
<body style="background-image:url(https://png.pngtree.com/background/20230519/original/pngtree-red-carpet-for-fashion-show-picture-image_2666949.jpg);
height:100vh; display:flex;flex-direction:column;justify-content:center;align-items:center">

	<h1 style = "background:#AA000085;padding:25px;color:white;
	border-radius:15px;box-shadow:10px 5px 50px black">Insetar Dato </h1>


	<form action="ControladorActor" name="form" method="get">
		<input type="hidden" name="instruccion" value="insertarBBDD">
		<table style = "background:#AA000085;padding:25px;color:white;border-radius:15px;box-shadow:10px 5px 50px black">
			<tr>
				<td width="27%">id actor</td>
				<td width="73%"><input type="text" name="idActor"></td>
			</tr>
			<tr>
				<td>Nomre Actor</td>
				<td><input type="text" name="nombreA"></td>
			</tr>
			<tr>
				<td>Apellido Actor</td>
				<td><input type="text" name="apellidoA"></td>
			</tr>
			<tr>
				<td>Actualizacion</td>
				<td><input type="text" name="actuallizacion"></td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="submit" value="insertar">
					<input type="reset" value="borrar"></td>
			</tr>
		</table>

	</form>
</body>
</html>