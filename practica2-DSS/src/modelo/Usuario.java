package modelo;

import java.io.Serializable;
import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
public class Usuario implements Serializable {
	private static final long finalVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private long id;
	private String name;
	private String apellidos;
	private String email;
	
	
	public Usuario(){}
	
	public Usuario(Usuario us) {
		
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getApellidos() {
		return apellidos;
	}

	public String getEmail() {
		return email;
	}
	
	public String toString(){
		return name + " " + apellidos + "-" + email;
	}
	
	
	
	
}