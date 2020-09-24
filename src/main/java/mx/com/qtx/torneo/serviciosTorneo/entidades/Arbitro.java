package mx.com.qtx.torneo.serviciosTorneo.entidades;

import java.util.Date;

import mx.com.qtx.torneo.IArbitro;

public class Arbitro implements IArbitro{
	private int id;
	private String nombre;
	private Date fecNac;

	public Arbitro() {
		super();
	}

	public Arbitro(int id, String nombre, Date fecNac) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.fecNac = fecNac;
	}

	@Override
	public String getNombre() {
		return this.nombre;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getFecNac() {
		return fecNac;
	}

	public void setFecNac(Date fecNac) {
		this.fecNac = fecNac;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return "Arbitro [id=" + id + ", nombre=" + nombre + ", fecNac=" + fecNac + "]";
	}

}
