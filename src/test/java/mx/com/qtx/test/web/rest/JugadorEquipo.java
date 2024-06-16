package mx.com.qtx.test.web.rest;

import java.util.Date;

public class JugadorEquipo {
	private String id;
	private String nombre;
	private String posicion;
	private Date fecNac;

	private JugadorEquipo(String id, String nombre, String posicion, Date fecNac) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.posicion = posicion;
		this.fecNac = fecNac;
	}

	private JugadorEquipo() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPosicion() {
		return posicion;
	}

	public void setPosicion(String posicion) {
		this.posicion = posicion;
	}

	public Date getFecNac() {
		return fecNac;
	}

	public void setFecNac(Date fecNac) {
		this.fecNac = fecNac;
	}

	@Override
	public String toString() {
		return "JugadorEquipo [id=" + id + ", nombre=" + nombre + ", posicion=" + posicion + ", fecNac=" + fecNac + "]";
	}

	
}
