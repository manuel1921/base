<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%-- <%@page import="java.util.*, com.dominio.conexion.*"%>--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Lista Actores</title>
<style type="text/css">
.cuerpo{
	display:flex; 
	justify-content: center;
	background-image:url("https://png.pngtree.com/background/20230519/original/pngtree-red-carpet-for-fashion-show-picture-image_2666949.jpg");
	
	
}

.cabecera{
	font-size: 1.2em;
	font-weight: bold;
	color: #ffffff;
	background-color:#ff000085;

}
.fila{
	text-align:center;


}
.tabla{
	
	background:#AA000085;
	padding:25px;
	color:white;
	border-radius:15px;
	box-shadow:10px 5px 50px black;
	
}
</style>
</head>

<body class="cuerpo">
	<table class="tabla">
		<tr>
			<td class="cabecera" font color = "#F17C07">idActor</td>
			<td class="cabecera">Nombre Actor</td>
			<td class="cabecera">Apellido Actor</td>
			<td class="cabecera">Fecha Actualización</td>
			<td class="cabecera">Modificar</td>

		</tr>
		
		<c:forEach var="tempActor" items="${ListaActores}">
		
		<!-- enlace para el id de cada actor a actualizar-->
		<c:url var="linkTemp" value="ControladorActor">
		<c:param name="instruccion" value="cargar"> </c:param>
		<c:param name="id_Actor" value="${tempActor.actor_id}"></c:param>
		</c:url>
		<!-- enlace para el id de cada actor a actualizar-->
		<c:url var="linkelininarTemp" value="ControladorActor">
		<c:param name="instruccion" value="eliminar"> </c:param>
		<c:param name="id_Actor" value="${tempActor.actor_id}"></c:param>
		</c:url>
		
			<tr>
				<td class="filas">${tempActor.actor_id}</td>
				<td class="filas">${tempActor.first_name}</td>
				<td class="filas">${tempActor.last_name}</td>
				<td class="filas">${tempActor.last_update}</td>
				<td class="filas"><a href="${linkTemp}">Actualizar</a>&nbsp;<a href="${linkelininarTemp}">Borrar</a></td>
				
			</tr>
		</c:forEach>

	</table>
	<div id="contenedorBoton">
	<input type="button" value="insertar" onclick="window.location.href='formularioInsertar.jsp'"/>
	</div>

</body>
</html>