package mx.com.qtx.torneo.serviciosTorneo;

import mx.com.qtx.torneo.NegocioException;

public class EquipoNoExisteException extends NegocioException {

	private static final long serialVersionUID = 1L;

	public EquipoNoExisteException(String message) {
		super(message);
	}

	public EquipoNoExisteException(String message, Throwable cause) {
		super(message, cause);
	}

}
