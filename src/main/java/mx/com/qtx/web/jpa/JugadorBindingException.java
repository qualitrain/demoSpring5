package mx.com.qtx.web.jpa;

import org.springframework.validation.BindingResult;

import mx.com.qtx.torneo.serviciosTorneo.jpa.entidades.Jugador;

public class JugadorBindingException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	private BindingResult resultValidacion;
	private Jugador jugador;
	
	public JugadorBindingException(BindingResult resultValidacion, Jugador jugador, String mensaje) {
		super(mensaje);
		this.resultValidacion = resultValidacion;
		this.jugador = jugador;
	}

	public BindingResult getResultValidacion() {
		return resultValidacion;
	}

	public void setResultValidacion(BindingResult resultValidacion) {
		this.resultValidacion = resultValidacion;
	}

	public Jugador getJugador() {
		return jugador;
	}

	public void setJugador(Jugador jugador) {
		this.jugador = jugador;
	}
	
}
