package mx.com.qtx.servicio;

public class Partido {
	private IEquipo local;
	private IEquipo visitante;
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
