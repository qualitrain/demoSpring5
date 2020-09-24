package mx.com.qtx.torneo;

import org.springframework.context.event.EventListener;

public class MonitorTorneo  {

	public MonitorTorneo() {		
	}
	@EventListener
	public void atenderEvtPartidasGeneradas(EvtPartidasGeneradas event) {
		System.out.println("***** Se generaron " + event.getnPartidas() + " partidas nuevas *****");
	}

}
