package REST;

import java.io.IOException;
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

import facade.OrderFacade;
import servidor.Order;
import servidor.Usuario;

@Path("/order")
public class Orders{

	private OrderFacade orfac;
	
	public Orders() {
		orfac = new OrderFacade();
	}
	
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	public String getOrders(){
        
		ArrayList<Order> orders = orfac.getOrders();
		String jsonOrders = new Gson().toJson(orders);
		
		return jsonOrders;
	}
	
	@PUT
	public Response addProducto(@QueryParam("id") String ID,
								@QueryParam("productoID") String productoID, 
								@QueryParam("username") String username) {
		
		Order or = new Order(Integer.parseInt(ID),Integer.parseInt(productoID), username);
		
		boolean added = false;
		
		if(ID != null && productoID != null && username != null)
			added = orfac.newOrder(or);
		
		if (added)
			return Response.status(201).entity("Added").build();
		else
			return Response.status(409).entity("Could not add").build();

	}
	
	@POST
	public Response updateProducto(@QueryParam("id") String ID,
								   @QueryParam("productoID") String productoID, 
								   @QueryParam("username") String username) {
		
		Order or = new Order(Integer.parseInt(ID),Integer.parseInt(productoID), username);
		boolean updated = false;
		
		if(ID != null && productoID != null && username != null)
			updated = orfac.updateOrder(or);
		
		if (updated)
			return Response.status(200).entity("Updated").build();
		else
			return Response.status(400).entity("Could not update").build();

	}
	
	@DELETE
	public Response deleteProducto(@QueryParam("id") String ID,
								   @QueryParam("productoID") String productoID, 
								   @QueryParam("username") String username)  {
									
		Order or = new Order(Integer.parseInt(ID),Integer.parseInt(productoID), username);
		
		boolean deleted = false;
		
		if(ID != null)
			deleted = orfac.deleteOrder(or);
		
		if (deleted)
			return Response.status(200).entity("Deleted").build();
		else
			return Response.status(400).entity("Could not delete").build();

	}
	
	
}
