package mx.com.qtx.servicio;

import org.springframework.context.ApplicationListener;

public class MonitorTorneo implements ApplicationListener<EvtPartidasGeneradas> {

	public MonitorTorneo() {		
	}
	@Override
	public void onApplicationEvent(EvtPartidasGeneradas event) {
		System.out.println("***** Se generaron " + event.getnPartidas() + " partidas nuevas *****");
	}

}
