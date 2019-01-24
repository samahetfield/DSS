package cliente;

import java.net.URI;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import org.glassfish.jersey.client.ClientConfig;

import modelo.Musico;

public class Probador {
	public static void main (String[] args) {
		ClientConfig config = new ClientConfig();
		Client cliente = ClientBuilder.newClient(config);
		WebTarget servicio = cliente.target(getBaseURI());
		
		//Crear un nuevo músico
		Musico musico = new Musico("3", "Sama", "Bajo");
		Response respuesta = servicio.path("rest").path("musicos").path(musico.getId()).request(MediaType.APPLICATION_XML).put(Entity.entity(musico, MediaType.APPLICATION_XML), Response.class);
		
		System.out.print("Codigo devuleto: ");
		// El codigo devuelto deberia ser 201 == created
		System.out.println(respuesta.getStatus());
		
		// Mostrar el contenido del recurso Todos como texto XML
		System.out.println("Mostrar como Texto XML Plano");
		System.out.println(servicio.path("rest").path("musicos").request().accept(MediaType.TEXT_XML).get(String.class));
		
		// Ahora vamos a eliminar el "objeto" con id=1 del recurso
		servicio.path("rest").path("musicos/1").request().delete();
		// Mostramos el contenido del recurso Todos, el elemento con id=1
		// deberia haber sido eliminado
		System.out.println("El elemento con id = 1 del recurso se ha eliminado");
		System.out.println(servicio.path("rest").path("musicos").request().accept(MediaType.APPLICATION_XML).get(String.class));
		
		// Crear un cuarto recurso Todo con un formulario Web
		System.out.println("Creacion de 1 formulario");
		Form form = new Form();
		form.param("id", "4");
		form.param("nombre", "Eusta");
		form.param("rol", "Coros");
		respuesta = servicio.path("rest").path("musicos").request().post(Entity.entity(form,MediaType.APPLICATION_FORM_URLENCODED), Response.class);
		System.out.println("Respuesta con el formulario " + respuesta.getStatus());
		
		// Se ha debido crear el elemento con id = 4
	    System.out.println("Contenidos del recurso, despues de enviar el elemento id=4");
	    System.out.println(servicio.path("rest").path("musicos").request().accept(MediaType.APPLICATION_XML).get(String.class));
		
	}
	
	private static URI getBaseURI() {
		return UriBuilder.fromUri("http://localhost:8080/practica3-DSS").build();
	}
}
