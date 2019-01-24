package modelo;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Musico {
	private String id;
	private String nombre;
	private String edad;
	private String rol;
	
	public Musico() {}
	
	public Musico(String id, String nombre, String rol) {
		this.id = id;
		this.nombre = nombre;
		this.rol = rol;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEdad() {
		return edad;
	}

	public void setEdad(String edad) {
		this.edad = edad;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}
	
	
}
