package mx.com.qtx.servicio;

import org.springframework.beans.factory.annotation.Autowired;

public class Partido {
	private IEquipo local;
	private IEquipo visitante;
	@Autowired  // Funcionará ?
	private IArbitro arbitro;
	
	public Partido(IEquipo local, IEquipo visitante) {
		super();
		this.local = local;
		this.visitante = visitante;
	}
	public void mostrarPartido() {
		System.out.println(this.local.getNombreEquipo() + " VS " + this.visitante.getNombreEquipo());
		System.out.println("Arbitro:" + this.arbitro);
	}
	
}
