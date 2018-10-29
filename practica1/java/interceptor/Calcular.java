package interceptor;

public class Calcular implements Filtro{
	private final double INTERVALO = 3600;
	
	
	public Calcular() {}
	
	public double ejecutar(double o) {
		double distancia =  (double) o;
		double velocidad = distancia*3600/INTERVALO;
		System.out.println("Filtro Calcular: velocidad = " + velocidad + " m/h");
		return velocidad;
	}


}