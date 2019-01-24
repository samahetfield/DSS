package test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;

import facade.FarmaciaFacade;
import facade.OrderFacade;
import facade.ProductoFacade;
import facade.UsuarioFacade;
import servidor.Farmacia;
import servidor.Order;
import servidor.Producto;
import servidor.Usuario;

public class MainTest {
	
	public static void main(String[] args) {
		/*
		FarmaciaFacade farmafac = new FarmaciaFacade();
		Farmacia farma = new Farmacia(5,"Farmacia test", 1, 2);
		
		farmafac.newFarmacia(farma);
		
		ArrayList<Farmacia> farmacias = farmafac.getFarmacias();
		
		for(Farmacia f: farmacias)
			System.out.println("Farmacia -> " + f.getID()+ " " +   f.getNombre());
		
		farmafac.deleteFarmacia(farma);
		
		farmacias = farmafac.getFarmacias();
		System.out.println("After delete ---------");
		for(Farmacia f: farmacias)
			System.out.println("Farmacia -> " + f.getID()+ " " +   f.getNombre());
		
		
		Farmacia farmaupdated = new Farmacia(8, "Updated name", 3,4);
		farmafac.updateFarmacia(farmaupdated);
		
		farmacias = farmafac.getFarmacias();
		System.out.println("After update ---------");
		for(Farmacia f: farmacias)
			System.out.println("Farmacia -> " + f.getID()+ " " +   f.getNombre());
		
		farmafac.close();
		
		
		OrderFacade orfac = new OrderFacade();
		
		
		Order order = new Order(1,new Producto(123,"Paracetamol"), new Usuario("Pepe","PepeUser","correo", "contrasena"));
		
		orfac.newOrder(order);
		
		Order orderUpdated = new Order(2,new Producto(111,"Paracetamol"), new Usuario("Pepe","PepeUpdated","correo", "contrasena"));
		orfac.updateOrder(orderUpdated);
		
		Order orderDelete = new Order(3,new Producto(123,"Paracetamol"), new Usuario("Pepe","PepeUser","correo", "contrasena"));

		orfac.deleteOrder(orderDelete);
		
		ArrayList<Order> orders = orfac.getOrders();
		
		System.out.println("All orders ---------");
		for(Order o: orders)
			System.out.println("Order -> " + o.getOrderID() + " " + o.getProducto().getProductoID() + " " +o.getUsuario().getNombreUsuario());
		
		orfac.close();
		
		
		ProductoFacade profac = new ProductoFacade();
		Producto p = new Producto(123, "Paracetamol");
		
		profac.newProducto(p);
		
		Producto pDelete = new Producto(2,"");
		profac.deleteProducto(pDelete);
		
		Producto pUpdate = new Producto(4,"Paracetamol updated");
		
		profac.updateProducto(pUpdate);
		
		ArrayList<Producto> productos = profac.getProductos();
		System.out.println("Productos ----------------");
		for(Producto pr: productos)
			System.out.println(pr.getProductoID() + " " + pr.getNombre());
		
		profac.close();
		
		
		UsuarioFacade userfac = new UsuarioFacade();

		Usuario user1 = new Usuario("Pepe", "PepeUser","asdasd@asdad","psswrd");
		Usuario user2 = new Usuario("Pepe", "PepeUser2","asdasd@asdad","psswrd");
		Usuario user3 = new Usuario("Pepe", "PepeUser3","asdasd@asdad","psswrd");

		//userfac.newUsuario(user2);
		//userfac.newUsuario(user3);
		
		Usuario user2updated = new Usuario("PepeUpdated", "PepeUser2", "updatedemail@asdad","updatedpassword");
		userfac.updateUsuario(user2updated);
		
		Usuario user3deleted = new Usuario("","PepeUser3","","");
		userfac.deleteUsuario(user3deleted);
		
		ArrayList<Usuario> usuarios = userfac.getUsuarios();

		System.out.println("Usuarios-----------");
		for(Usuario u: usuarios)
			System.out.println(u.getNombre()+ " " + u.getNombreUsuario() + " " + u.getCorreo()+ " " + u.getContrasena());
		
		userfac.close();
		
		
		Producto p = new Producto(9999, "producto json");
		
		ClientConfig clientConfig = new DefaultClientConfig();
		clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
		Client client = Client.create(clientConfig);
		
		WebResource servicio = client.resource(UriBuilder.fromUri("http://localhost:8080/DSSJava/rest").build());

		ClientResponse respuesta = servicio.path("producto").accept("application/json").type("application/json").put(ClientResponse.class,p);			
		System.out.println(respuesta.getEntity(String.class));

		p = new Producto(11,"");
		respuesta = servicio.path("producto").accept("application/json").type("application/json").delete(ClientResponse.class,p);			
		System.out.println(respuesta.getEntity(String.class));
		
		p = new Producto(3,"Json updated");
		respuesta = servicio.path("producto").accept("application/json").type("application/json").post(ClientResponse.class,p);			
		System.out.println(respuesta.getEntity(String.class));
		
		
		ClientConfig clientConfig = new DefaultClientConfig();
		Client client = Client.create(clientConfig);
		WebResource servicio = client.resource(UriBuilder.fromUri("http://localhost:8080/DSSJava/rest").build());
		ClientResponse respuesta = servicio.path("producto").queryParam("id", "6").delete(ClientResponse.class);

		System.out.println(respuesta);
		*/
		
		ClientConfig clientConfig = new DefaultClientConfig();
		Client client = Client.create(clientConfig);
		WebResource servicio = client.resource(UriBuilder.fromUri("http://localhost:8080/DSSJava/rest").build());
		
		String respuesta = servicio.path("usuario").get(String.class);
		Gson gson = new Gson();
		ArrayList<Usuario> arr = gson.fromJson(respuesta, new TypeToken<ArrayList<Usuario>>(){}.getType());
		
		System.out.println("Usuarios: ");
		for(Usuario u : arr)
			System.out.println(u);
		
		
		ClientResponse respuesta2 = servicio.path("usuario").queryParam("username", "samaLastHope")
											.queryParam("nombre", "Sergio")
											.queryParam("correo", "sergio@correo")
											.queryParam("contrasena", "contrasena")
											.put(ClientResponse.class);
		
		System.out.println(respuesta2);
		
		respuesta = servicio.path("usuario").get(String.class);
		arr = gson.fromJson(respuesta, new TypeToken<ArrayList<Usuario>>(){}.getType());
		System.out.println("Usuarios: ");
		for(Usuario u : arr)
			System.out.println(u);
		
		respuesta2 = servicio.path("usuario").queryParam("username", "samaLastHope")
				.queryParam("nombre", "Sergio Samaniego")
				.queryParam("correo", "sergio@correo")
				.queryParam("contrasena", "contrasena")
				.post(ClientResponse.class);

		System.out.println(respuesta2);
		
		respuesta = servicio.path("usuario").get(String.class);
		arr = gson.fromJson(respuesta, new TypeToken<ArrayList<Usuario>>(){}.getType());
		System.out.println("Usuarios: ");
		for(Usuario u : arr)
		System.out.println(u);
		
		
		
		
	}
	
}
