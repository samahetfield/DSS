package interceptor;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

public class CadenaFiltros{
	
	private ArrayList<Filtro> filtros = new ArrayList();
	private Interfaz objetivo;
	
	public CadenaFiltros() {}
	
	public void addFiltro(Filtro filtro) {
		filtros.add(filtro);
	}
	
	public void ejecutar(double peticion) throws IOException, URISyntaxException {
		double resultadoFiltro = peticion;
		for(Filtro filtro:filtros){
			resultadoFiltro = filtro.ejecutar(resultadoFiltro);
		}
		
		objetivo.ejecutar(peticion);
	}
	
	public void setObjetivo(Interfaz objetivo) {
		this.objetivo = objetivo;
	}
	
}