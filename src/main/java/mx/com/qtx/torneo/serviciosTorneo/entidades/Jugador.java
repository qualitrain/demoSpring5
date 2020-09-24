package mx.com.qtx.torneo.serviciosTorneo.entidades;

import java.util.Date;

import mx.com.qtx.torneo.IEquipo;
import mx.com.qtx.torneo.IJugador;

public class Jugador implements IJugador{
	private String id;
	private String nombre;
	private int numero;
	private String posicion;
	private Date fecNac;
	private boolean lesionado;
	private boolean suspendido;
	private boolean titular;
	private Equipo equipo;
	
	public Jugador() {
		super();
	}
	public Jugador(String id, String nombre, int numero, String posicion, Date fecNac) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.numero = numero;
		this.posicion = posicion;
		this.fecNac = fecNac;
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
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
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
	public boolean isLesionado() {
		return lesionado;
	}
	public void setLesionado(boolean lesionado) {
		this.lesionado = lesionado;
	}
	public boolean isSuspendido() {
		return suspendido;
	}
	public void setSuspendido(boolean suspendido) {
		this.suspendido = suspendido;
	}
	public boolean isTitular() {
		return titular;
	}
	public void setTitular(boolean titular) {
		this.titular = titular;
	}
	public Equipo getEquipo() {
		return equipo;
	}
	public void setEquipo(Equipo equipo) {
		this.equipo = equipo;
	}
	@Override
	public String toString() {
		return "Jugador [id=" + id + ", nombre=" + nombre + ", numero=" + numero + ", posicion=" + posicion
				+ ", fecNac=" + fecNac + ", lesionado=" + lesionado + ", suspendido=" + suspendido + ", titular="
				+ titular + "]";
	}
	@Override
	public void setEquipo(IEquipo equipo) {
		if (equipo instanceof Equipo)
			this.setEquipo((Equipo)equipo);
	}

}
