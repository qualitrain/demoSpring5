package mx.com.qtx.test.web.rest;

import java.time.LocalDate;

import javax.json.bind.annotation.JsonbDateFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

public class JugadorDto {
	
	private String id;
	private String nombre;
	private int numero;
	private String posicion;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern= "yyyy-MM-dd")	// Jackson Bind
	@JsonbDateFormat("yyyy-MM-dd")
	private LocalDate fecNac;
	
	private boolean lesionado;
	private boolean suspendido;
	private boolean titular;
	
	private String idEquipo;
		
	public JugadorDto() {
		super();
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

	public LocalDate getFecNac() {
		return fecNac;
	}

	public void setFecNac(LocalDate fecNac) {
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

	public String getIdEquipo() {
		return idEquipo;
	}

	public void setIdEquipo(String idEquipo) {
		this.idEquipo = idEquipo;
	}

	@Override
	public String toString() {
		return "Jugador [id=" + id + ", nombre=" + nombre + ", numero=" + numero + ", posicion=" + posicion
				+ ", fecNac=" + fecNac + ", lesionado=" + lesionado + ", suspendido=" + suspendido + ", titular="
				+ titular + ", idEquipo=" + idEquipo + "]";
	}

}
