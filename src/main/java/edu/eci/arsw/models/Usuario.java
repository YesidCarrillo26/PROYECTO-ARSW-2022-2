package edu.eci.arsw.models;

//import java.sql.Date;

public class Usuario {

	private int id=1111;
	private String name;
	private String fechaN;
	private int telefono;
	private String correo;
	private String password;
	private int points = 5000;

	public Usuario(int id, String name, String fechaN, int telefono, String correo, String password) {
		this.id = id;
		this.name = name;
		this.fechaN = fechaN;
		this.telefono = telefono;
		this.correo = correo;
		this.password = password;
	}

	public Usuario(String name, String fechaN, int telefono, String correo, String password) {
		this.name = name;
		this.fechaN = fechaN;
		this.telefono = telefono;
		this.correo = correo;
		this.password = password;
	}

	public String getFechaN() {
		return fechaN;
	}

	public void setFechaN(String fechaN) {
		this.fechaN = fechaN;
	}

	public int getTelefono() {
		return telefono;
	}

	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Usuario getUser() {
		return this;
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}


}
