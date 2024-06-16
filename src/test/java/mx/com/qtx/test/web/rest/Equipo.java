package mx.com.qtx.test.web.rest;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Equipo {
	private String id;
	@JsonProperty("nombreEquipo")
	private String nombre;
	private String entrenador;
	private int puntos;
	
	private List<JugadorEquipo> jugadores;
	private Map<String,String> jugadoresTitulares;
	
	private Equipo() {
		super();
	}

	private Equipo(String id, String nombre) {
		super();
		this.id = id;
		this.nombre = nombre;
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

	public String getEntrenador() {
		return entrenador;
	}

	public void setEntrenador(String entrenador) {
		this.entrenador = entrenador;
	}

	public int getPuntos() {
		return puntos;
	}

	public void setPuntos(int puntos) {
		this.puntos = puntos;
	}

	public List<JugadorEquipo> getJugadores() {
		return jugadores;
	}

	public void setJugadores(List<JugadorEquipo> jugadores) {
		this.jugadores = jugadores;
	}

	public Map<String, String> getJugadoresTitulares() {
		return jugadoresTitulares;
	}

	public void setJugadoresTitulares(Map<String, String> jugadoresTitulares) {
		this.jugadoresTitulares = jugadoresTitulares;
	}

	@Override
	public String toString() {
		return "Equipo [id=" + id + ", nombre=" + nombre + ", entrenador=" + entrenador + ", puntos=" + puntos
				+ ", jugadores=" + jugadores + ", jugadoresTitulares=" + jugadoresTitulares + "]";
	}

}
