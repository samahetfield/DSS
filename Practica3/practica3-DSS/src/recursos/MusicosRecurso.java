package recursos;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;

import dao.MusicoDao;
import modelo.Musico;

@Path("/musicos")
public class MusicosRecurso {
	@Context
	UriInfo uriInfo;
	@Context
	Request request;
	
	@GET
	@Produces(MediaType.TEXT_XML)
	public List<Musico> getMusicoBrowser(){
		List<Musico> musicos = new ArrayList<Musico>();
		musicos.addAll(MusicoDao.INSTANCE.getModel().values());
		return musicos;
	}
	
	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public List<Musico> getMusicos(){
		List<Musico> musicos = new ArrayList<Musico>();
		musicos.addAll(MusicoDao.INSTANCE.getModel().values());
		return musicos;
	}
	
	@GET
	@Path("count")
	@Produces(MediaType.TEXT_PLAIN)
	public String getCount() {
		int count = MusicoDao.INSTANCE.getModel().size();
		return String.valueOf(count);
	}
	
	
	@POST
	@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public void newMusico(
			@FormParam("id") String id, 
			@FormParam("nombre") String nombre, 
			@FormParam("edad") String edad, 
			@FormParam("rol") String rol, 
			@Context HttpServletResponse servletResponse) throws IOException {
		
		Musico musico = new Musico(id, nombre, rol);
		
		if(edad != null) {
			musico.setEdad(edad);
		}
		
		MusicoDao.INSTANCE.getModel().put(id, musico);
		
		servletResponse.sendRedirect("../crear-musico.html");
	}
	
	@Path("{musico}")
	public MusicoRecurso getMusico(@PathParam("musico") String id) {
		return new MusicoRecurso(uriInfo, request, id);
	}
	
}
