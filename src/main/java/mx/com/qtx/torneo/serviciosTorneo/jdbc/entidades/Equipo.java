package mx.com.qtx.torneo.serviciosTorneo.jdbc.entidades;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.AfterDomainEventPublication;
import org.springframework.data.domain.DomainEvents;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import mx.com.qtx.torneo.EvtUpdateIEquipo;
import mx.com.qtx.torneo.IEquipo;
import mx.com.qtx.torneo.IJugador;

@Table
public class Equipo implements IEquipo, Persistable<String>{
	@Id
	@Column("eq_id")
	private String id;
	@Column("eq_nombre")
	private String nombre;
	@Column("eq_apodo")
	private String apodo;
	@Column("eq_patrocinador")
	private String patrocinador;
	@Column("eq_jj")
	private int jueJugados;
	@Column("eq_jg")
	private int jueGanados;
	@Column("eq_je")
	private int jueEmpatados;
	@Column("eq_jp")
	private int juePerdidos;
	@Column("eq_puntos")
	private int puntos;
	@Column("eq_entrenador")
	private String entrenador;

	@MappedCollection(idColumn = "jug_id_eq")
	private Set<Jugador> jugadores;
	
	@Transient
	private int nInserts = 0;
	
	public Equipo() {
		super();
		this.jugadores = new HashSet<>();
		this.patrocinador = "vacante";
		this.entrenador = "vacante";
	}

	public Equipo(String nombre, String apodo) {
		super();
		this.nombre = nombre;
		this.apodo = apodo;
		this.jugadores = new HashSet<>();
		this.patrocinador = "vacante";
		this.entrenador = "vacante";
	}

	public static IEquipo crearEquipo(Map<String, Object> mapDatos) {
		Equipo equipo = new Equipo();
		String id = (String) mapDatos.get("id");
		if(id == null)
			return null;
		equipo.setId(id);
		equipo.setApodo((String) mapDatos.getOrDefault("apodo",""));
		equipo.setNombre((String) mapDatos.getOrDefault("nombre","no especificado"));
		equipo.setEntrenador((String) mapDatos.getOrDefault("entrenador", "no especificado"));
		equipo.setPatrocinador((String) mapDatos.getOrDefault("patrocinador", "no especificado"));
		equipo.setJugadores((Set<Jugador>) mapDatos.getOrDefault("jugadores", new HashSet<Jugador>()));
		return equipo;
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

	public String getApodo() {
		return apodo;
	}

	public void setApodo(String apodo) {
		this.apodo = apodo;
	}

	public String getPatrocinador() {
		return patrocinador;
	}

	public void setPatrocinador(String patrocinador) {
		this.patrocinador = patrocinador;
	}

	public int getJueJugados() {
		return jueJugados;
	}

	public void setJueJugados(int jueJugados) {
		this.jueJugados = jueJugados;
	}

	public int getJueGanados() {
		return jueGanados;
	}

	public void setJueGanados(int jueGanados) {
		this.jueGanados = jueGanados;
	}

	public int getJueEmpatados() {
		return jueEmpatados;
	}

	public void setJueEmpatados(int jueEmpatados) {
		this.jueEmpatados = jueEmpatados;
	}

	public int getJuePerdidos() {
		return juePerdidos;
	}

	public void setJuePerdidos(int juePerdidos) {
		this.juePerdidos = juePerdidos;
	}

	public int getPuntos() {
		return puntos;
	}

	public void setPuntos(int puntos) {
		this.puntos = puntos;
	}

	public String getEntrenador() {
		return entrenador;
	}

	public void setEntrenador(String entrenador) {
		this.entrenador = entrenador;
	}

	public Set<Jugador> getJugadores() {
		return jugadores;
	}

	public void setJugadores(Set<Jugador> jugadores) {
		this.jugadores = jugadores;
	}
	public void agregarJugador(Jugador jugador) {
		this.jugadores.add(jugador);
		jugador.setEquipo(this);
	}

	@Override
	public String getID() {
		return this.getId();
	}

	@Override
	public void setID(String id) {
		this.setId(id);
	}

	@Override
	public String getNombreEquipo() {
		return this.getApodo() +  " de " + this.getNombre();
	}

	@Override
	public void setNombreEquipo(String nombre) {
		this.setNombre(nombre);
	}

	@Override
	public int getNumJugadores() {
		return this.jugadores.size();
	}

	@Override
	public Map<Integer, String> getJugadoresTitulares() {
		Map<Integer, String> titulares = new HashMap<>();
	    this.jugadores.stream()
				      .filter(j->j.isTitular())
				      .forEach(j-> titulares.put(j.getNumero(), j.getNombre()));
	    return titulares;
	}

	@Override
	public String getPosicionJugador(int numJugador) {
		Optional<Jugador> jugador = this.jugadores
				                        .stream()
		                                .filter(j->(j.getNumero()==numJugador))
		                                .findFirst();
		return jugador.isPresent() ? jugador.get().getPosicion() : "indefinida";
	}

	@Override
	public List<IJugador> getListaJugadores() {
		return new ArrayList<IJugador> (this.getJugadores()) ;
	}

	@Override
	public String toString() {
		return "Equipo [id=" + id + ", nombre=" + nombre + ", apodo=" + apodo + ", patrocinador=" + patrocinador
				+ ", jueJugados=" + jueJugados + ", jueGanados=" + jueGanados + ", jueEmpatados=" + jueEmpatados
				+ ", juePerdidos=" + juePerdidos + ", puntos=" + puntos + ", entrenador=" + entrenador + ", jugadores="
				+ jugadores + "]";
	}

	@Override
	public int agregarJugador(IJugador jugador) {
		if(this.jugadores.contains(jugador))
			return 0;
		if(jugador instanceof Jugador)
			this.agregarJugador((Jugador)jugador);
		else {
			Jugador unJugador = new Jugador();
			unJugador.setId(jugador.getId());
			unJugador.setNombre(jugador.getNombre());
			unJugador.setNumero(jugador.getNumero());
			unJugador.setPosicion(jugador.getPosicion());
			unJugador.setFecNac(jugador.getFecNac());
			unJugador.setLesionado(jugador.isLesionado());
			unJugador.setTitular(false);
			unJugador.setSuspendido(jugador.isSuspendido());
			this.agregarJugador(unJugador);
		}
		return 1;
	}

	@Override
	public boolean isNew() {
		if (this.nInserts == 0) {
			this.nInserts++;
			return true;
		}
		return false;
	}
	@DomainEvents
	public EvtUpdateIEquipo avisarSave() {
		return new EvtUpdateIEquipo(this);
	}
	@AfterDomainEventPublication
	public void postProcesoEvtUpdateEquipo() {
		System.out.println("***** Equipo.postProcesoEvtUpdateEquipo() *****");
	}


}
