package fp.vino;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import fp.utiles.Checkers;

public class FactoriaVinos {
	public static Vinos leerVinos(String rutaFicheroVinos) {
		Vinos res= null;
		try {
			Stream<Vino> sv=
					Files.lines(Paths.get(rutaFicheroVinos))
					.skip(1)
					.map(linea->parsearVino(linea));
			res= new Vinos(sv);
		}catch(IOException e) {
			System.out.println("No se ha encontrado el fichero " + rutaFicheroVinos);
			e.printStackTrace();
		}return res;
	}

	private static Vino parsearVino(String linea) {
		Checkers.checkNoNull(linea);
		String[] trozos=linea.split(",");
		Checkers.check("Fomrato no valido " + linea, trozos.length==5);
		String pais= trozos[0].trim();
		String region = trozos[1].trim();
		Integer puntos= Integer.parseInt(trozos[2].trim());
		Double precio= Double.parseDouble(trozos[3].trim());
		String uva= trozos[4].trim();
		return new Vino(pais, region,puntos ,precio, uva);
	}

}
