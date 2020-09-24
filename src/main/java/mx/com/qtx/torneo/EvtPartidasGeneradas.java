package mx.com.qtx.torneo;

import java.util.Map;

import org.springframework.context.ApplicationEvent;

public class EvtPartidasGeneradas extends ApplicationEvent {
	private int nPartidas;

	public EvtPartidasGeneradas(Map<Integer, String[]> partidas) {
		super(partidas);
		this.nPartidas = partidas.size();
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public int getnPartidas() {
		return nPartidas;
	}

}
