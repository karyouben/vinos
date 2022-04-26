package fp.vino;

import fp.utiles.Checkers;

public record Vino(String pais, String region, Integer puntos, Double precio,
		String uva) {
	
	//Chekeador de errores
	public Vino{
		Checkers.check("Los puntos deben estar entre cero y cien ",
				puntos>=0 && puntos<= 100);
		Checkers.check("El precio debe ser mayor que cerio ", precio>0);
		
	}
	
	public Double getCalidadPrecio() {
		return puntos/precio;
	}
	


}
