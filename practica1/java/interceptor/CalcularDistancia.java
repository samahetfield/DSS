package interceptor;


public class CalcularDistancia implements Filtro{
	private double RADIO = 1;
	private double revolAnt = 0.0;
	
	
	public double ejecutar(double o) {
		double revoluciones = (double) o;
		double distancia = (revoluciones - revolAnt)*2*RADIO*3.1416;
		System.out.println("Filtro CalcularDistancia: distancia = " + distancia + " m");
		return distancia;
	}
}