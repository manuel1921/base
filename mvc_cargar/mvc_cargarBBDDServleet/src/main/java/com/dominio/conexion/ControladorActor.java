package com.dominio.conexion;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;



/**
 * Servlet implementation class ControladorActor
 * 
 * Sistema de Gestión de Actores Descripción General
 * Desarrollar una aplicación web utilizando el modelo MVC
 * (Modelo-Vista-Controlador) para gestionar información sobre actores de una
 * base de datos.
 * 
 * @author Manuel Vargas Tello
 * 
 */
@WebServlet("/ControladorActor")
public class ControladorActor extends HttpServlet {
	private static final long serialVersionUID = 1L;
	// creamos una variable del tipo modelo actor
	private ModeloActor modeloActor;

	@Resource(name = "jdbc/actor")
	private DataSource miPool;
/**
 * @param Inicializa el programa 
 * 
 **/
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		try {
			modeloActor = new ModeloActor(miPool);
		} catch (Exception e) {
			throw new ServletException(e);
		}
		
		System.out.println("salida desde método init");

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	
	/**
	 * @param Gestiona las peticiones 
	 * 
	 * **/

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// TODO Auto-generated method stub
		// response.getWriter().append("Served").append(request.getContextPath());
		String comando = request.getParameter("instruccion");
				if(comando == null) comando = "listar";
		switch(comando){
		case "listar": 
			obtenerActor(request, response);
			
			break;
		case "insertarBBDD": 
			insertarActor(request, response);
			break;
		case "cargar": 
			try {
				cargarActor(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("Error: " + e.getMessage());	
			}
			break;
		case "actualizarBBDD": 
			try {
				actualizarNuevoActor(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println(e.getMessage());
			}
			break;
			
		case "eliminar": 
			elimiarActor(request, response);
			break;
			
		default: obtenerActor(request, response);
			
		}
		System.out.println("salida desde método doGet");


		/*obtenerActor(request, response);

		insertarActor(request, response);*/

	}

	/**
	 * 
	 * @param metodo que hace deleter del CRUD
	 * **/
	
	private void elimiarActor(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		int idActor = Integer.parseInt(request.getParameter("id_Actor"));
	
		try {
			modeloActor.elimiarActorActorBBDD(idActor);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//obtenerActor(request, response);
		response.sendRedirect("ControladorActor?instruccion=listar");
	
		
	}
	
	

	/*private void actualizarNuevoActor(HttpServletRequest request, HttpServletResponse response) throws Exception{
		// TODO Auto-generated method stub
		int idActor = Integer.parseInt(request.getParameter("id_Actor"));
		String NombreActor=request.getParameter("nombreA");
		String ApellidorActor=request.getParameter("apellidoA");
		String Fecha=request.getParameter("actualizacion");
		Actor nuevoActor = new Actor(idActor,NombreActor,ApellidorActor,Fecha);
		modeloActor.actualizarActorBBDD(nuevoActor);
		//obtenerActor(request, response);
		response.sendRedirect("ControladorActor?instruccion=listar");*
	
		
	}*/
	
	
	/**
	 * 
	 * @param metodo que hace el update del CRUD
	 * **/
	
	private void actualizarNuevoActor(HttpServletRequest request, HttpServletResponse response) throws Exception {
	    // Obtener los datos del formulario
	    int idActor = Integer.parseInt(request.getParameter("id_Actor"));
	    String nombreActor = request.getParameter("nombreA");
	    String apellidoActor = request.getParameter("apellidoA");
	    String actualizacion = request.getParameter("actualizacion");

	    // Crear un objeto Actor con los datos actualizados
	    Actor nuevoActor = new Actor(idActor, nombreActor, apellidoActor, actualizacion);

	    // Llamar al método del modelo para actualizar el actor en la BBDD
	    modeloActor.actualizarActorBBDD(nuevoActor);

	    // Redireccionar de nuevo a la lista de actores
	    response.sendRedirect("ControladorActor?instruccion=listar");
	}

	
	/**
	 * @param carga los datos desde base de datos 
	 * **/
	

	private void cargarActor(HttpServletRequest request, HttpServletResponse response) throws Exception{
		// TODO Auto-generated method stub
		//leer datos del id del actor
		int idActor = Integer.parseInt(request.getParameter("id_Actor"));
		//comunicar con el modelo para enviarle el id y que este nos devuelva el actor con ese id
		Actor actorCargar = modeloActor.getActorCargar(idActor);
		//establecer el atributo
		request.setAttribute("id_actorCargar", actorCargar);
		//enviar el id del actor al modelo
		RequestDispatcher dispatcher = request.getRequestDispatcher("/formularioActualizar.jsp");
		dispatcher.forward(request, response);
		

	}
	
	/**
	 * @param metodo que hace el create de CRUD 
	 * **/

	private void insertarActor(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		int idActor = Integer.parseInt(request.getParameter("idActor"));
		String nombreActor = request.getParameter("nombreA");
		String apellidoActor = request.getParameter("apellidoA");
		String actualizacion = request.getParameter("actuallizacion");

		// creamos un objeto de tipo actor
		Actor actores = new Actor(idActor, nombreActor, apellidoActor, actualizacion);
		try {
			modeloActor.insertarNuevoActor(actores);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//obtenerActor(request, response);
		response.sendRedirect("ControladorActor?instruccion=listar");

	}
	
	
	/**
	 * @param metodo que hace el read del CRUD
	 * 
	 * **/

	private void obtenerActor(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		// obtener la lista de actores desde el modelo
		// creamos una lista para contener la lista de actores del return
		List<Actor> actores;

		try {
			actores = modeloActor.getActor();
			// Añadimos esa lista de actores al request
			request.setAttribute("ListaActores", actores);

			// enviar el request a la pagina jsp(aún no está creada)
			RequestDispatcher miDispatcher = request.getRequestDispatcher("/listaActores.jsp");
			miDispatcher.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Enunciado del Proyecto: Sistema de Gestión de Actores Descripción General
	 * Desarrollar una aplicación web utilizando el modelo MVC
	 * (Modelo-Vista-Controlador) para gestionar información sobre actores de una
	 * base de datos. El sistema debe permitir realizar operaciones CRUD (Crear,
	 * Leer, Actualizar y Eliminar) sobre los registros de actores. La base de datos
	 * utilizada será actorholywood, que contiene información básica sobre los
	 * actores.
	 * 
	 * Requisitos Funcionales Listar Actores
	 * 
	 * Mostrar una lista de todos los actores en la base de datos. Cada registro
	 * debe mostrar el ID del actor, nombre, apellido y fecha de actualización. Debe
	 * haber enlaces para actualizar y eliminar cada actor. Insertar Nuevo Actor
	 * 
	 * Proporcionar un formulario para ingresar los datos de un nuevo actor. Los
	 * campos del formulario deben incluir ID del actor, nombre, apellido y fecha de
	 * actualización. Al enviar el formulario, el actor debe ser añadido a la base
	 * de datos. Actualizar Actor Existente
	 * 
	 * Proporcionar un formulario para actualizar los datos de un actor existente.
	 * El formulario debe estar prellenado con los datos actuales del actor. Al
	 * enviar el formulario, los datos del actor deben ser actualizados en la base
	 * de datos. Eliminar Actor
	 * 
	 * Permitir la eliminación de un actor de la base de datos. Solicitar
	 * confirmación antes de proceder con la eliminación. Estructura del Proyecto 1.
	 * Modelo Clase Actor: Representa la entidad Actor con atributos actor_id,
	 * first_name, last_name, y last_update. Clase ModeloActor: Contiene los métodos
	 * para interactuar con la base de datos, como obtener la lista de actores,
	 * insertar un nuevo actor, actualizar un actor existente y eliminar un actor.
	 * 2. Vista JSPs listaActores.jsp: Muestra la lista de actores con enlaces para
	 * actualizar y eliminar. formularioInsertar.jsp: Formulario para ingresar un
	 * nuevo actor. formularioActualizar.jsp: Formulario para actualizar un actor
	 * existente. 3. Controlador Servlet ControladorActor: Controla las solicitudes
	 * del cliente y determina las acciones a tomar según las instrucciones
	 * recibidas (listar, insertarBBDD, cargar, actualizarBBDD, eliminar). Detalles
	 * de Implementación Base de Datos Nombre: actores_sakila Tabla: actorholywood
	 * Campos: actor_id (int), first_name (varchar), last_name (varchar),
	 * last_update (timestamp)
	 * @author Manuel Vargas Tello
	 **/

}
