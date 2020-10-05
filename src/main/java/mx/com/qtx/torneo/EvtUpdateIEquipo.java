package mx.com.qtx.torneo;

import org.springframework.context.ApplicationEvent;

public class EvtUpdateIEquipo extends ApplicationEvent {
	private static final long serialVersionUID = 1L;
	
	private IEquipo eqUpdated;

	public EvtUpdateIEquipo(IEquipo eq) {
		super(eq);
		this.eqUpdated = eq;
	}

	public IEquipo getEqUpdated() {
		return eqUpdated;
	}

	public void setEqUpdated(IEquipo eqUpdated) {
		this.eqUpdated = eqUpdated;
	}


}
