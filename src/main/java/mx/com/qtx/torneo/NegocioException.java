package mx.com.qtx.torneo;

public abstract class NegocioException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public NegocioException(String message, Throwable cause) {
		super(message, cause);
	}

	public NegocioException(String message) {
		super(message);
	}

	abstract public String getClave();

	
}
