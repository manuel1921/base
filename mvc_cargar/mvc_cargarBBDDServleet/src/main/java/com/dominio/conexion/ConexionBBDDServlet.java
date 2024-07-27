package com.dominio.conexion;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class ConexionBBDDServlet
 */
@WebServlet("/ConexionBBDDServlet")
public class ConexionBBDDServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	//establecemos el pool de conexiones
	@Resource(name="jdbc/actor")
	private DataSource miPool; 
	
    public ConexionBBDDServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	PrintWriter salida = response.getWriter();
	response.setContentType("text/plain");
	
	//Establecer la conexión
	//1.Definimos la conexión
	Connection miConexion = null;
	Statement miStatement = null;
	ResultSet miResultset = null;
	
	try {
		//2.establecemos la conexión
		miConexion = miPool.getConnection();
		String miSql = "SELECT * FROM actores_sakila.actores";
		miStatement = miConexion.createStatement();
		//3.Ejecutamos la query
		miResultset = miStatement.executeQuery(miSql);
		while(miResultset.next()) {
			String apellidos = miResultset.getString(3);
			salida.println(apellidos);
		}	
	}catch(Exception e) {
		e.printStackTrace();
	}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
