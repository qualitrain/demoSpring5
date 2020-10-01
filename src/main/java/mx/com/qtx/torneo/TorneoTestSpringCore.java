package mx.com.qtx.torneo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.context.MessageSource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import mx.com.qtx.ITorneo;
import mx.com.qtx.torneo.serviciosTorneo.IEstrategiaEnfrentamientos;

@Component
public class TorneoTestSpringCore implements ApplicationEventPublisherAware, ITorneo{
	@Autowired
	private MessageSource fteTextos;
	@Autowired
	private Locale localidad;
	
	private ApplicationEventPublisher publicadoEvt;
	@Autowired
	private ResourceLoader cargadorRecursos;
	
	@Value("classpath:jugadores_vam.txt")
	private Resource recListaJugadoresVam;
	
	private List<String> jugadoresVam;
	private List<String> jugadoresHor;
	
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
	
	@Qualifier("arbitroDummy")
	@Autowired
	private IArbitro arbitroSecundario;
	
	@Autowired
	@Qualifier("propuestos")
	private List<IArbitro> arbitrosRegistrados;
	
	@Autowired
	@Qualifier("propuestos")
	private Map<Integer, IArbitro> mapaArbitros;
	
	private Map<String, IEquipo> equipos;
	private IEstrategiaEnfrentamientos estrategiaEnfrentamientos;
	private Map<Integer, String[]> partidas;
	
	
	@Autowired
	public TorneoTestSpringCore(IEstrategiaEnfrentamientos estrategiaEnfrentamientos) {
		System.out.println("***** TorneoTestSpringCore instanciado *****");
		this.equipos = new HashMap<String,IEquipo>();
		this.estrategiaEnfrentamientos = estrategiaEnfrentamientos;
		
		this.jugadoresVam = new ArrayList<String>();
		this.jugadoresHor = new ArrayList<String>();
	}
	public void mostrarBienvenida() {
		String txtBienvenida = this.fteTextos.getMessage("saludo", null, this.localidad);
		System.out.println("\n=============================");
		System.out.println(txtBienvenida);
		System.out.println("=============================");
	}

	public void agregarEquipo(IEquipo equipo) {
		this.equipos.put(equipo.getNombreEquipo(), equipo);
	}
	public void generarPartidas() {
		for(String nomEquipoI : equipos.keySet()) {
			this.estrategiaEnfrentamientos.agregarEquipo(nomEquipoI);
		}
		this.partidas = this.estrategiaEnfrentamientos.generarPartidas();
		this.publicadoEvt.publishEvent(new EvtPartidasGeneradas(this.partidas));
	}
	public void mostrarArbitros() {
		String txtArbitros = this.fteTextos.getMessage("arbitros", null, this.localidad);
		String txtArbitroPrincipal = this.fteTextos.getMessage("arbitro.principal", null, this.localidad);
		String txtArbitroSecundario = this.fteTextos.getMessage("arbitro.suplente", null, this.localidad);
		String txtListaArbitros = this.fteTextos.getMessage("arbitros.list", null, this.localidad);
		String txtMapaArbitros = this.fteTextos.getMessage("arbitros.map", null, this.localidad);
		
		System.out.println(txtArbitros + ":");
		System.out.println(txtArbitroPrincipal + ":" + this.arbitroPrincipal.getNombre());
		System.out.println(txtArbitroSecundario + ":" + this.arbitroSecundario.getNombre());
		
		if(this.arbitrosRegistrados == null)
			return;
		System.out.println(txtListaArbitros	+ ":");
		this.arbitrosRegistrados.forEach(a -> System.out.println(a.getNombre()) );
		
		if(this.mapaArbitros == null)
			return;
		System.out.println(txtMapaArbitros + ":");
		for(Integer llaveI:this.mapaArbitros.keySet()) {
			System.out.println(llaveI + "-> " + this.mapaArbitros.get(llaveI).getNombre());
		}
		System.out.println();
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
	public void inicializarTorneo() {
		this.cargarJugadores(this.recListaJugadoresVam,this.jugadoresVam);
		Resource recListaJugadoresHor = this.cargadorRecursos.getResource("classpath:jugadores_hor.txt");
		this.cargarJugadores(recListaJugadoresHor, this.jugadoresHor);
		
//		System.out.println("*** El bean torneo ha quedado armado y está listo a operar ***");
	}
	@PreDestroy
	public void mostrarFinBean() {
//		System.out.println("*** El bean torneo está a punto de ser destruido ***");
	}
	@Override
	public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
		this.publicadoEvt = applicationEventPublisher;
	}
	private void cargarJugadoresVam() {
		try (BufferedReader bf = new BufferedReader(
				                 new InputStreamReader(
				                 this.recListaJugadoresVam.getInputStream())) ) {
				bf.lines().forEach(jug -> this.jugadoresVam.add(jug));
		}
		catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
	
	private void cargarJugadores(Resource recurso, List<String>listaEquipo) {
		try (BufferedReader bf = new BufferedReader(
				                 new InputStreamReader(
				                 recurso.getInputStream())) ) {
				bf.lines()
				  .forEach(jug -> listaEquipo.add(jug));
		}
		catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
	public void mostrarJugadores() {
		System.out.println("\n=============== Jugadores de Vampiros ===============");
		this.jugadoresVam.forEach(jug -> System.out.println(jug));
		System.out.println("\n=========== Jugadores de Hormigas atómicas ==========");
		this.jugadoresHor.forEach(jug -> System.out.println(jug));
	}
	public List<String> getJugadores(String equipo){
		switch (equipo) {
			case "Vampiros": return this.jugadoresVam;
			case "Hormigas atómicas": return this.jugadoresHor;
			default: return null;
		}
	}
	@Override
	public void testActualizaciones() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void testInserciones() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void testEliminaciones() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void testConsultas() {
		// TODO Auto-generated method stub
		
	}
}
