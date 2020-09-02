package mx.com.qtx.servicio;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Torneo {
	private Map<String, IEquipo> equipos;
	private IEstrategiaEnfrentamientos estrategiaEnfrentamientos;
	private Map<Integer, String[]> partidas;
	
	@Autowired
	private IArbitro arbitroPrincipal;
	
	@Autowired
	private IArbitro arbitroSuplente;
	
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
		System.out.println("Principal:" + this.arbitroPrincipal.getNombre());
		System.out.println("Suplente:" + this.arbitroSuplente.getNombre());
	}
	public void mostrarPartidas() {
		for(Integer numPartido : this.partidas.keySet()) {
			String[] equipos = this.partidas.get(numPartido);
			System.out.println("Partido " + numPartido + ".-"
					            + equipos[0] + " VS " + equipos[1] );
		}
	}
	
}
