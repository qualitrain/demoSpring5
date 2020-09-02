package mx.com.qtx.servicio;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Torneo {
	private Map<String, IEquipo> equipos;
	private IEstrategiaEnfrentamientos estrategiaEnfrentamientos;
	private Map<Integer, String[]> partidas;
	
	@Autowired
	private IArbitro arbitroTest;
	
	@Autowired
	private IArbitro arbitroDummy;
	
	@Autowired(required=false)
	private Map<String,IArbitro> arbitros;
	
	@Autowired
	public Torneo(IEstrategiaEnfrentamientos estrategiaEnfrentamientos) {
		super();
		this.equipos = new HashMap<String,IEquipo>();
		this.estrategiaEnfrentamientos = estrategiaEnfrentamientos;
	}

	public void agregarEquipo(IEquipo equipo) {
		this.equipos.put(equipo.getNombreEquipo(), equipo);
	}
	public void generarPartidas() {
		for(String nomEquipoI : equipos.keySet()) {
			this.estrategiaEnfrentamientos.agregarEquipo(nomEquipoI);
		}
		this.partidas = this.estrategiaEnfrentamientos.generarPartidas();
	}
	public void mostrarArbitros() {
		System.out.println("Árbitros disponibles:");
		System.out.println("Principal:" + this.arbitroTest.getNombre());
		System.out.println("Suplente:" + this.arbitroDummy.getNombre());
		if(this.arbitros == null)
			return;
		System.out.println("Mapa árbitros:");
		for(String llaveI:this.arbitros.keySet()) {
			System.out.println(llaveI + ":" + this.arbitros.get(llaveI).getNombre());
		}
		
	}
	public void mostrarPartidas() {
		for(Integer numPartido : this.partidas.keySet()) {
			String[] equipos = this.partidas.get(numPartido);
			System.out.println("Partido " + numPartido + ".-"
					            + equipos[0] + " VS " + equipos[1] );
		}
	}
	
}
