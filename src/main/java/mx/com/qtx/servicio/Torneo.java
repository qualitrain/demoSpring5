package mx.com.qtx.servicio;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Torneo {
	private Map<String, IEquipo> equipos;
	private IEstrategiaEnfrentamientos estrategiaEnfrentamientos;
	private Map<Integer, String[]> partidas;
	
	@Value("${torneo.deporte:ninguno}")
	private String deporte;
	
	@Value("${torneo.partido.duracion}")
	private int duracionPartido;
	
	@Value("${torneo.partido.periodos}")
	private int periodos;
	
	@Value("#{{'Deportivo Oceanía','Ciudad Deportiva pta 5','Deportivo Chapultepec'}}")
	private List<String> campos;
	
	@Autowired
	private IArbitro arbitroPrincipal;
	
	@Resource(name="arbitroDummy")
	private IArbitro arbitroSecundario;
	
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
		System.out.println("Principal:" + this.arbitroPrincipal.getNombre());
		System.out.println("Suplente:" + this.arbitroSecundario.getNombre());
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
	public void mostrarDatosGenerales() {
		System.out.println("Deporte:" + this.deporte);
		System.out.print("Duración de cada partido:" + this.duracionPartido + " minutos, ");
		System.out.println("repartidos en " + this.periodos + " periodos");
		System.out.println("Campos:" + this.campos);
	}
	@PostConstruct
	public void mostrarFinArmadoBean() {
		System.out.println("*** El bean torneo ha quedado armado y está listo a operar ***");
	}
	@PreDestroy
	public void mostrarFinBean() {
		System.out.println("*** El bean torneo está a punto de ser destruido ***");
	}
}
