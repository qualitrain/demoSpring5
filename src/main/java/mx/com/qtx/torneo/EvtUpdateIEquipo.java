package mx.com.qtx.torneo;

import org.springframework.context.ApplicationEvent;

import mx.com.qtx.torneo.serviciosTorneo.entidades.Equipo;

public class EvtUpdateIEquipo extends ApplicationEvent {
	private static final long serialVersionUID = 1L;
	
	private IEquipo eqUpdated;

	public EvtUpdateIEquipo(Equipo eq) {
		super(eq);
		this.eqUpdated = eq;
	}

	public IEquipo getEqUpdated() {
		return eqUpdated;
	}

	public void setEqUpdated(Equipo eqUpdated) {
		this.eqUpdated = eqUpdated;
	}


}
