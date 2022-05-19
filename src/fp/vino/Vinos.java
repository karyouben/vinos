package fp.vino;

import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collector;
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
		return vinos.stream() //tranformar de conjunto a stream set<vino>->Stream<vino>
				.filter(vino-> vino.precio()<precio)
				.collect(Collectors.toSet());//Stream<vino>->set<vino>
		
	}
	
	public Boolean existeVinoDeUvaEnRegion(String region, String uva) {
		return vinos.stream()
				.filter(vino->vino.region().equals(region))
				.anyMatch(vino->vino.uva().equals(uva));
		//.anyMatch(vino->vino.uva().equals(uva) && vino->vino.region().equals(region));
				
			}
	public Set<String> calcularUvasDeRegion(String region){
		return vinos.stream()
				.filter(vino->vino.region().equals(region)) //Stream<vino>
				.map(Vino::uva)//Stream<String>
				.collect(Collectors.toSet());//Stream<String>->set<vino>
		//.collect(Collectors.mapping(Vino::uva,Collectors.toSet())); --> 2º forma
	}
		
	public Vino  obtenerVinoMejorPuntuado(String region) {
		return vinos.stream() //Stream<Vino>
				.max(Comparator.comparing(Vino::puntos)) //Optional
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
	
	public Map<String,List<Vino>> agruparVinosPorPais(){
		return vinos.stream()
				.collect(Collectors.groupingBy(Vino::pais));
		
	}
	
	public Map<String,Set<String>> agruparUvasPorPais(){
		return vinos.stream()
				.collect(Collectors.groupingBy(Vino::pais,
						Collectors.mapping(Vino::uva,Collectors.toSet())));
	}
		
	public Map<String, Long> calcularCalidadPrecioPorRegionMayorDe(Double umbral) {
		return vinos.stream()
				.filter(vino->vino.getCalidadPrecio()>umbral)
				.collect(Collectors.groupingBy(Vino::region,Collectors.counting()));
		//.collect(Collectors.groupingBy(Vino::region,
		//Collectors.collectingAndThen(Collectors.counting(),v->v.IntValue())
	
		
	}
	
}
	
		
	


