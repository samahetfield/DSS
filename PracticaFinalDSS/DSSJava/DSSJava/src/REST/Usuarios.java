package REST;

import java.util.ArrayList;


import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.google.gson.Gson;


import facade.UsuarioFacade;
import servidor.Usuario;

@Path("/usuario")
public class Usuarios{

	UsuarioFacade userfac;
	
	public Usuarios() {
		userfac = new UsuarioFacade();
	}
	
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	public String getUsuarios(){
        
			
		ArrayList<Usuario> usuarios = userfac.getUsuarios();
		String jsonUsuarios = new Gson().toJson(usuarios);
		
		return jsonUsuarios;
		
	}
	
	@PUT
	public Response addUsuario(@QueryParam("username") String username,
							   @QueryParam("nombre") String nombre,
							   @QueryParam("correo") String correo,
							   @QueryParam("contrasena")String contrasena){
		
		Usuario usr = new Usuario(nombre, username, correo, contrasena);
		
		boolean added = false;
		
		if(username != null && nombre != null && correo != null && contrasena != null)
			added = userfac.newUsuario(usr);
			
		if (added)
			return Response.status(201).entity("Added").build();
		else
			return Response.status(409).entity("Could not add").build();

	}
	
	@POST
	public Response updateUsuario(@QueryParam("username") String username,
								  @QueryParam("nombre") String nombre,
								  @QueryParam("correo") String correo,
								  @QueryParam("contrasena")String contrasena) {
		
		Usuario usr = new Usuario(nombre, username, correo, contrasena);

		boolean updated = false;
		
		if(username != null && nombre != null && correo != null && contrasena != null)
			updated = userfac.updateUsuario(usr);
		
		 
		if (updated)
			return Response.status(200).entity("Updated").build();
		else
			return Response.status(400).entity("Could not update").build();

	}
	
	@DELETE
	public Response deleteUsuario(@QueryParam("username") String username) {
		
		Usuario usr = new Usuario("", username, "", "");

		boolean deleted = false;
		
		if(username != null)
			deleted = userfac.deleteUsuario(usr);
		
		if (deleted)
			return Response.status(200).entity("Deleted").build();
		else
			return Response.status(400).entity("Could not delete").build();

	}
	
}
