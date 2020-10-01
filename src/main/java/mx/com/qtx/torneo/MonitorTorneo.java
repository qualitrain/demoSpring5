package mx.com.qtx.torneo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PreDestroy;

import org.springframework.context.event.EventListener;

public class MonitorTorneo  {
	private List<String> eventos;

	public MonitorTorneo() {	
		this.eventos = new ArrayList<>();
	}
	@EventListener
	public void atenderEvtPartidasGeneradas(EvtPartidasGeneradas event) {
		System.out.println("***** Se generaron " + event.getnPartidas() + " partidas nuevas *****");
		this.eventos.add("Partidas generadas (" + event.getnPartidas() + "): " + new Date());
	}
	@EventListener
	public void atenderEvtUpdateIEquipo(EvtUpdateIEquipo event) {
		System.out.println("***** Se actualizó un equipo (" + event.getEqUpdated() + ") *****");
		this.eventos.add("Equipo actualizado (" + event.getEqUpdated() + "): " + new Date());
	}
	
	@PreDestroy
	public void mostrarEventos() {
		System.out.println("\n***** Eventos monitoreados: *****");
		this.eventos.forEach(ev -> System.out.println(ev));
	}
	
	

}
