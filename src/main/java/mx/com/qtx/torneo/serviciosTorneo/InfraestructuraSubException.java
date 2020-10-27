package mx.com.qtx.torneo.serviciosTorneo;

import mx.com.qtx.torneo.NegocioException;

public class InfraestructuraSubException extends NegocioException {

	public InfraestructuraSubException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String getClave() {
		return "INF000";
	}

}
