package fp.vinos.test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import fp.vino.Vino;

public class ExperimentosComparadores {

	public static void main(String[] args) {
		Vino vino1 = new Vino("US", "California", 96, 235.0, "Cabernet Sauvignon");
		Vino vino2 = new Vino("Spain", "Northern Spain", 96, 110.0, "Tinta de Toro");
		Vino vino3 = new Vino("US", "California", 96, 90.0, "Sauvignon Blanc");
		Vino vino4 = new Vino("US", "Oregon", 96, 65.0, "Pinot Noir");
		Vino vino5 = new Vino("France", "Provence", 95, 66.0, "Provence red blend");
		Vino vino6 = new Vino("Spain", "Northern Spain", 95, 73.0, "Tinta de Toro");
		Vino vino7 = new Vino("Spain", "Northern Spain", 95, 65.0, "Tinta de Toro");
		Vino vino8 = new Vino("Spain", "Northern Spain", 95, 110.0, "Tinta de Toro");
		Vino vino9 = new Vino("US", "Oregon", 95, 65.0, "Pinot Noir");
		Vino vino10 = new Vino("US", "California", 95, 60.0, "Pinot Noir");
		List<Vino> lista_vinos = List.of(vino1, vino2, vino3, vino4, vino5, vino6, vino7, vino8, vino9, vino10);
		List<Vino> vinos= new ArrayList<Vino> (lista_vinos);
		
		Comparator<Vino> c= Comparator.comparing(Vino::pais).thenComparing(Vino::region);
//      Comparator<Vino> c= Comparator.comparing(vino->vino.pais());
	    Collections.sort(vinos,c);
	    System.out.println("La lista de vinos ordenada es ");
	    for(Vino v: vinos) {
	    	System.out.println(v);
	    }

	}

}
