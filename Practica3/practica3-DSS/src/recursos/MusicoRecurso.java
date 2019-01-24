package recursos;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.xml.bind.JAXBElement;

import dao.MusicoDao;
import modelo.Musico;

public class MusicoRecurso {
	@Context
	UriInfo uriInfo;
	@Context
	Request request;
	String id;
	
	public MusicoRecurso(UriInfo uriInfo, Request request, String id) {
		this.uriInfo = uriInfo;
		this.request = request;
		this.id = id;
	}
	
	@GET
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Musico getMusico() {
		Musico musico = MusicoDao.INSTANCE.getModel().get(id);
		
		if(musico == null) {
			throw new RuntimeException("Get: Musico con id: " + id + " no se ha encontrado");
		}
		
		return musico;
	}
	
	@GET
	@Produces(MediaType.TEXT_XML)
	public Musico getMusicoHTML() {
		Musico musico = MusicoDao.INSTANCE.getModel().get(id);
		
		if(musico == null) {
			throw new RuntimeException("Get: Musico con id: " + id + " no se ha encontrado");
		}
		
		return musico;
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_XML)
	public Response putTodo(JAXBElement<Musico> musico) {
		Musico mu = musico.getValue();
		return putAndGetResponse(mu);
	}
	
	@DELETE
	public void deleteMusico() {
		Musico mu = MusicoDao.INSTANCE.getModel().remove(id);
		if(mu == null) {
			throw new RuntimeException("Delete: Musico con id: " + id + " no encontrado");
		}
	}
	
	private Response putAndGetResponse(Musico musico) {
		Response res;
		if(MusicoDao.INSTANCE.getModel().containsKey(musico.getId())) {
			res = Response.noContent().build();
		}
		else {
			res = Response.created(uriInfo.getAbsolutePath()).build();
		}
		
		MusicoDao.INSTANCE.getModel().put(musico.getId(), musico);
		return res;
	}
	
}
