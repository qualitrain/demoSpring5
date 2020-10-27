package mx.com.qtx.torneo.serviciosTorneo;

import mx.com.qtx.torneo.NegocioException;

public class EquipoYaExisteException extends NegocioException {

	private static final long serialVersionUID = 1L;

	public EquipoYaExisteException(String message, Throwable cause) {
		super(message, cause);
	}

	public EquipoYaExisteException(String message) {
		super(message);
	}

	@Override
	public String getClave() {
		return "NEG002";
	}

}
