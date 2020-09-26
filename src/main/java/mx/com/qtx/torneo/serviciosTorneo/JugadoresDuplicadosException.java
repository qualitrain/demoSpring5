package mx.com.qtx.torneo.serviciosTorneo;

import mx.com.qtx.torneo.NegocioException;

import java.util.List;

import mx.com.qtx.torneo.IJugador;

public class JugadoresDuplicadosException extends NegocioException {
	private static final long serialVersionUID = 1L;
	 private List<IJugador> jugDuplicados;

	public JugadoresDuplicadosException(String message, List<IJugador> jugDuplicados) {
		super(message);
		this.jugDuplicados =jugDuplicados;
	}

	public List<IJugador> getJugDuplicados() {
		return jugDuplicados;
	}

	public void setJugDuplicados(List<IJugador> jugDuplicados) {
		this.jugDuplicados = jugDuplicados;
	}


}
