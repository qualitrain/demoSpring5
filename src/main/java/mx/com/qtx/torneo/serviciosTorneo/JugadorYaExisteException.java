package mx.com.qtx.torneo.serviciosTorneo;

import mx.com.qtx.torneo.IJugador;
import mx.com.qtx.torneo.NegocioException;

public class JugadorYaExisteException extends NegocioException {

	private static final long serialVersionUID = 1L;
	private IJugador jugador;

	public JugadorYaExisteException(String message, IJugador jugador) {
		super(message);
		this.jugador = jugador;
	}
	public IJugador getJugador() {
		return jugador;
	}

	public void setJugador(IJugador jugador) {
		this.jugador = jugador;
	}
	
	@Override
	public String getClave() {
		return "NEG101";
	}

}
