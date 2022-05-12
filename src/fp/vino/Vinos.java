package fp.vino;

import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import fp.utiles.Checkers;

public class Vinos {
	private Set<Vino> vinos;
	
	public Vinos() {
		vinos= new HashSet<Vino>();
	}
	
	public Vinos(Collection<Vino> vinos) {
		this.vinos = new HashSet<Vino>(vinos);
	}
	
	public Vinos(Stream<Vino> vinos) {
		this.vinos = vinos.collect(Collectors.toSet());
	}
	
	public Integer getNumeroVinos() {
		return vinos.size();
	}
	
	public void agregarVino(Vino vino) {
		vinos.add(vino);
	}
	
	public void agregarVinos(Collection<Vino> vinos) {
		for(Vino vino:vinos) {
			agregarVino(vino);
		}
	}
	public void eliminarVino(Vino v) {
		Checkers.check("Vino inexistente " + v, vinos.remove(v));
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((vinos == null) ? 0 : vinos.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Vinos))
			return false;
		Vinos other = (Vinos) obj;
		if (vinos == null) {
			if (other.vinos != null)
				return false;
		} else if (!vinos.equals(other.vinos))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return String.format("Num. vinos: %d\n", vinos.size());
//		String.format("Num. vinos: %d\n", obtenerNumeroVinos());
//		return obtenerNumeroVinos().toString();
	}
	
	public Boolean contieneVino(Vino vino) {
		return vinos.contains(vino);
	}
	
	public Boolean contieneVinos(Collection<Vino> vinos) {
		return this.vinos.containsAll(vinos);
	}
	
	public Boolean hayVino(String vinazo) {
		boolean esCierto= false;
		for(Vino v: vinos) {
			if(v.uva().equals(vinazo)) {
				esCierto=true;
				
			}else {
				esCierto=false;
			
		}
	}return esCierto;
 }
	public Integer calcularNumeroVinosDePais(String pais) {
		Long res=vinos.stream()
				.filter(vino->vino.pais().equals(pais))
				.count();
		return res.intValue();
	}
	
	public Set<Vino> obtenerVinosBaratos(Double precio) {
		return vinos.stream()
				.filter(vino-> vino.precio()<precio)
				.collect(Collectors.toSet());
		
	}
	
	public Boolean existeVinoDeUvaEnRegion(String region, String uva) {
		return vinos.stream()
				.filter(vino->vino.region().equals(region))
				.anyMatch(vino->vino.uva().equals(uva));
				
			}
	public Set<String> calcularUvasDeRegion(String region){
		return vinos.stream()
				.filter(vino->vino.region().equals(region))
				.map(Vino::uva)
				.collect(Collectors.toSet());
		//.collect(Collectors.mapping(Vino::uva,Collectors.toSet()));
	}
		
	public Vino  obtenerVinoMejorPuntuado(String region) {
		return vinos.stream()
				.max(Comparator.comparing(Vino::puntos))
				.get();
		
	  }
	
	public Vino obtenerVinoMejorPuntuadoDePais(String region) {
		return vinos.stream()
				.filter(vino->vino.region().equals(region))
				.max(Comparator.comparing(Vino::puntos))
				.get();	
	}
	
	public List<Vino> obtenerNVinosRegionOrdenadosPrecio(String region,Integer n) {
		return vinos.stream()
				.filter(vino->vino.region().equals(region))
				.sorted(Comparator.comparing(Vino::precio).reversed())
				.limit(n)
				.collect(Collectors.toList());
			
	}
	
		
	
		
	

}
