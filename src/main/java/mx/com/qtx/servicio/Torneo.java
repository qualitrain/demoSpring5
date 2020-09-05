package mx.com.qtx.servicio;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

@Component
public class Torneo implements ApplicationEventPublisherAware{
	@Autowired
	private MessageSource fteTextos;
	@Autowired
	private Locale localidad;
	private ApplicationEventPublisher publicadoEvt;
	
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
	
	@Autowired
	@Qualifier("propuestos")
	private List<IArbitro> arbitrosRegistrados;
	
	@Autowired
	@Qualifier("propuestos")
	private Map<Integer, IArbitro> mapaArbitros;
	
	@Autowired
	public Torneo(IEstrategiaEnfrentamientos estrategiaEnfrentamientos) {
		super();
		this.equipos = new HashMap<String,IEquipo>();
		this.estrategiaEnfrentamientos = estrategiaEnfrentamientos;
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
	public void mostrarFinArmadoBean() {
		System.out.println("*** El bean torneo ha quedado armado y está listo a operar ***");
	}
	@PreDestroy
	public void mostrarFinBean() {
		System.out.println("*** El bean torneo está a punto de ser destruido ***");
	}
	@Override
	public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
		this.publicadoEvt = applicationEventPublisher;
	}
}
