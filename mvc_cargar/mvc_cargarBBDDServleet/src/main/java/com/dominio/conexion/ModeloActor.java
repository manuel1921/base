package com.dominio.conexion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;



public class ModeloActor {
	private DataSource origenDatos;

	public ModeloActor(DataSource origenDatos) {
		this.origenDatos = origenDatos;
	}

/**
 * @param metodo para devolver una lista de actores
 * @return actores de actores de BBDD
 * **/
	public List<Actor> getActor() throws Exception {
		List<Actor> actores = new ArrayList<>();

		// establecer conexión
		Connection miconexion = null;
		Statement miStatement = null;
		ResultSet miResultset = null;
		
	try {
			// 1.establecer la conexión
			miconexion = origenDatos.getConnection();
	
			// 2.crear sentencia sql y statement
			String instruccionSql = "SELECT * FROM actores_sakila.actores";
			miStatement = miconexion.createStatement();
	
			// ejecutar sql
			miResultset = miStatement.executeQuery(instruccionSql);
			// recorrer el miResulset obtenido
	
			while (miResultset.next()) {
				int idActor = miResultset.getInt("actor_id");
				String nombreActor = miResultset.getString("first_name");
				String apellidoActor = miResultset.getString("last_name");
				String actualizacion = miResultset.getString("last_update");
				// añadimos estos datos a la lista en cada vuelta de bucle
				Actor tempActor = new Actor(idActor, nombreActor, apellidoActor, actualizacion);
				// añadimos el objeto a la lista
				actores.add(tempActor);
			}
			
	}catch (Exception e) {
		System.out.println("ERROR: " + e.getMessage());
	}finally {
		miconexion.close();
		miStatement.close();
	}
	
			return actores;
}
/**
 * @param carga datos desde BBDD
 * **/
	public void insertarNuevoActor(Actor actores) throws Exception {
		// TODO Auto-generated method stub

		// establecer la conexion
		Connection conexion = null;
		PreparedStatement statement = null;
		try {
			conexion = origenDatos.getConnection();
			// creamos instrucción sql
			String sql = "INSERT INTO actores_sakila.actores(actor_id, first_name, " + "last_name, last_update) VALUES(?,?,?,?)";
			statement = conexion.prepareStatement(sql);
			statement.setInt(1, actores.getActor_id());
			statement.setString(2, actores.getFirst_name());
			statement.setString(3, actores.getLast_name());
			statement.setString(4, actores.getLast_update());
			// ejecuta sql
			statement.execute();

		} catch (Exception e) {
			System.out.println("ERROR: " + e.getMessage());
		}finally {
			conexion.close();
			statement.close();
		}

	}
	
	/**
	 * @param carga datos desde BBDD
	 * @return actorBuscar carga los actor
	 * **/

	public Actor getActorCargar(int idActor) throws Exception {
		// TODO Auto-generated method stub
		//creamos un objeto de la clase actor para guardar los datos del registro
		Actor actorBuscar = null;
		
		//Creamos los objetos para establecer la conexion
		Connection conexion = null;
		PreparedStatement  statement = null;
		ResultSet resultset = null;
		
		//establecemos la conexion
		try {
			conexion = origenDatos.getConnection();
		

			//crear la sentencia sql 
			String sql = "SELECT * FROM actores_sakila.actores WHERE actor_id=?";
			statement = conexion.prepareStatement(sql);
			statement.setInt(1, idActor);
			resultset = statement.executeQuery();
			if(resultset.next()) {
				int id_Actor = resultset.getInt("actor_id");
				String nombreActor = resultset.getString("first_name");
				String apellidoActor = resultset.getString("last_name");
				String actualizacion = resultset.getString("last_update");
				actorBuscar = new Actor(id_Actor, nombreActor, apellidoActor,actualizacion);
				
			}else {
				throw new Exception("No se ha encontrado el actor con el id " + idActor);
			}
		
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			conexion.close();
			statement.close();
		}
		
		
		return actorBuscar;

	}
	
	
	/*public void actualizarActorBBDD(Actor nuevoActor) {
		// TODO Auto-generated method stub
		
		//Creamos los objetos para establecer la conexion
			Connection conexion = null;
			PreparedStatement  statement = null;
			
			//establecemos la conexion
			try {
				conexion = origenDatos.getConnection();
				String sql = "UPDATE  actores_sakila.actores SET first_name=?,last_name=?,"
						+ "last_update=? WHERE actor_id=?";
				  statement = conexion.prepareStatement(sql);
				  statement.setInt(1,nuevoActor.getActor_id());
				  statement.setString(2,nuevoActor.getFirst_name());
				  statement.setString(3,nuevoActor.getLast_name());
				  statement.setString(4,nuevoActor.getLast_update());
				  statement.executeUpdate();
				
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		

	}*/
	/**
	 * @param actualiza la BBDD
	 * **/
	
	
	public void actualizarActorBBDD(Actor nuevoActor) throws Exception {
	    // Creamos los objetos para establecer la conexión
	    Connection conexion = null;
	    PreparedStatement statement = null;

	    try {
	        // Establecemos la conexión
	        conexion = origenDatos.getConnection();
	        
	        // Crear la sentencia SQL
	        String sql = "UPDATE actores_sakila.actores SET first_name=?, last_name=?, last_update=? WHERE actor_id=?";
	        statement = conexion.prepareStatement(sql);
	        
	        // Configurar los parámetros del PreparedStatement
	        statement.setString(1, nuevoActor.getFirst_name());
	        statement.setString(2, nuevoActor.getLast_name());
	        statement.setString(3, nuevoActor.getLast_update());
	        statement.setInt(4, nuevoActor.getActor_id()); // El ID del actor debería ser el último parámetro
	        
	        // Ejecutar la actualización
	        statement.executeUpdate();

	    } catch (SQLException e) {
	        // Manejar la excepción
	        e.printStackTrace();
	    } finally {
	        // Cerrar recursos
	        try {
	            if (statement != null) statement.close();
	            if (conexion != null) conexion.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }finally {
				conexion.close();
				statement.close();
			}
	    }
	}

	
	/**
	 * @param actualiza la BBDD
	 * @throws SQLException 
	 * **/

	public void elimiarActorActorBBDD(int idActor) throws SQLException {
		// TODO Auto-generated method stub
		
		//Creamos los objetos para establecer la conexion
		Connection conexion = null;
		PreparedStatement  statement = null;
		
		//establecemos la conexion
		try {
			conexion = origenDatos.getConnection();
			String sql = "DELETE FROM  actores_sakila.actores WHERE actor_id=?";
			  statement = conexion.prepareStatement(sql);
			  statement.setInt(1,idActor);
			  statement.executeUpdate();
		
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			conexion.close();
			statement.close();
		}
	

		
	}
}
