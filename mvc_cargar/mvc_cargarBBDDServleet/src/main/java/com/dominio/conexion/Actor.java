package com.dominio.conexion;

public class Actor {
	//atributos
	private int actor_id;
	private String first_name;
	private String last_name;
	private String last_update;
	
	//coonstructores
	public Actor(int actor_id, String first_name, String last_name, String last_update) {
		this.actor_id = actor_id;
		this.first_name = first_name;
		this.last_name = last_name;
		this.last_update = last_update;
	}


	public Actor(String first_name, String last_name, String last_update) {
		this.first_name = first_name;
		this.last_name = last_name;
		this.last_update = last_update;
	}

	//getters y setters
	public int getActor_id() {
		return actor_id;
	}


	public void setActor_id(int actor_id) {
		this.actor_id = actor_id;
	}


	public String getFirst_name() {
		return first_name;
	}


	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}


	public String getLast_name() {
		return last_name;
	}


	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}


	public String getLast_update() {
		return last_update;
	}


	public void setLast_update(String last_update) {
		this.last_update = last_update;
	}

	//Método toString
	@Override
	public String toString() {
		return "Actor [actor_id=" + actor_id + ", first_name=" + first_name + ", last_name=" + last_name
				+ ", last_update=" + last_update  + "]" + "<br><br>" ;
	}
	
	
	
	
	

}
