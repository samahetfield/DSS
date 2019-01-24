package dao;

import java.util.HashMap;
import java.util.Map;

import modelo.Musico;

public enum MusicoDao {
	INSTANCE;
	
	private Map<String, Musico> proveedorContenidos = new HashMap<>();
	
	private MusicoDao() {
		Musico musico = new Musico("1", "JSLPD", "Guitarra");
		musico.setEdad("22");
		proveedorContenidos.put("1", musico);
		
		musico = new Musico("2", "PEPED", "Guitarra");
		musico.setEdad("25");
		proveedorContenidos.put("2", musico);
	}
	
	public Map<String, Musico> getModel(){
		return proveedorContenidos;
	}
}
