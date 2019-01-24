package REST;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

import facade.FarmaciaFacade;
import servidor.Producto;
import servidor.Usuario;


@Path("/farmacia")
public class Farmacia{
	
	private FarmaciaFacade farmafac;
	
	public Farmacia() {
		farmafac = new FarmaciaFacade();
	}
	
	
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	public String getFarmacias(){
		
		ArrayList<servidor.Farmacia> farmacias = farmafac.getFarmacias();
		String jsonFarmacias = new Gson().toJson(farmacias);
		
		
		return jsonFarmacias;
	}
	
	@PUT
	public Response addFarmacia(@QueryParam("id") String ID,
								@QueryParam("nombre") String nombre, 
								@QueryParam("latitud") String latitud,
								@QueryParam("longitud") String longitud) {
		
		servidor.Farmacia far = new servidor.Farmacia(Integer.parseInt(ID), nombre, Float.parseFloat(latitud), Float.parseFloat(longitud));
		boolean added = false;
		
		if(ID != null && nombre != null && latitud != null && longitud != null)
			added = farmafac.newFarmacia(far);
			
		if (added)
			return Response.status(201).entity("Added").build();
		else
			return Response.status(409).entity("Could not add").build();

	}
	
	@POST
	public Response updateFarmacia(@QueryParam("id") String ID,
								   @QueryParam("nombre") String nombre, 
								   @QueryParam("latitud") String latitud,
								   @QueryParam("longitud") String longitud) {
		
		servidor.Farmacia far = new servidor.Farmacia(Integer.parseInt(ID), nombre, Float.parseFloat(latitud), Float.parseFloat(longitud));
		boolean updated = false;
		
		if(ID != null && nombre != null && latitud != null && longitud != null)
			updated = farmafac.updateFarmacia(far);
		
		if (updated)
			return Response.status(200).entity("Updated").build();
		else
			return Response.status(400).entity("Could not update").build();

	}
	
	@DELETE
	public Response deleteFarmacia(@QueryParam("id") String ID,
								   @QueryParam("nombre") String nombre, 
								   @QueryParam("latitud") String latitud,
								   @QueryParam("longitud") String longitud) {
				
		servidor.Farmacia far = new servidor.Farmacia(Integer.parseInt(ID), nombre, Float.parseFloat(latitud), Float.parseFloat(longitud));
		
		boolean deleted = false;
		
		if(ID != null)
			deleted = farmafac.deleteFarmacia(far);
		
		if (deleted)
			return Response.status(200).entity("Deleted").build();
		else
			return Response.status(400).entity("Could not delete").build();

	}

}
