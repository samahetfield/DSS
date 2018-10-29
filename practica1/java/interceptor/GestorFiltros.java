package interceptor;

import java.io.IOException;
import java.net.URISyntaxException;

public class GestorFiltros{
	private CadenaFiltros cadenaFiltros;
	Interfaz objetivo;
	
	public GestorFiltros(Interfaz i) {
		cadenaFiltros = new CadenaFiltros();
		objetivo = i;
		cadenaFiltros.setObjetivo(i);
	}
	
	public void setFiltro(Filtro filtro) {
		cadenaFiltros.addFiltro(filtro);
	}
	
	public void ejecutar(double peticion) throws IOException, URISyntaxException {
		cadenaFiltros.ejecutar(peticion);
	}
}