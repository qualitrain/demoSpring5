package mx.com.qtx.test;

import java.util.Date;

import mx.com.qtx.torneo.IEquipo;
import mx.com.qtx.torneo.IJugador;

public class JugadorFutbol implements IJugador {

	private String id;
	private String nombre;
	private int numero;
	private String posicion;
	
	public JugadorFutbol(String id, String nombre, int numero, String posicion) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.numero = numero;
		this.posicion = posicion;
	}

	@Override
	public String getId() {
		return this.id;
	}

	@Override
	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String getNombre() {
		return this.nombre;
	}

	@Override
	public void setNombre(String nombre) {
		this.nombre=nombre;
	}

	@Override
	public int getNumero() {
		return this.numero;
	}

	@Override
	public void setNumero(int numero) {
		this.numero = numero;
	}

	@Override
	public String getPosicion() {
		return this.posicion;
	}

	@Override
	public void setPosicion(String posicion) {
		this.posicion =posicion;
	}

	@Override
	public Date getFecNac() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setFecNac(Date fecNac) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isLesionado() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setLesionado(boolean lesionado) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isSuspendido() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setSuspendido(boolean suspendido) {
		// TODO Auto-generated method stub

	}

	@Override
	public String toString() {
		return "JugadorFutbol [id=" + id + ", nombre=" + nombre + ", numero=" + numero + ", posicion=" + posicion + "]";
	}

	@Override
	public void setEquipo(IEquipo equipo) {
		// TODO Auto-generated method stub
		
	}

}
