package mx.com.qtx.torneo.serviciosTorneo.jdbc.entidades;

import java.util.Date;
import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import mx.com.qtx.torneo.IEquipo;
import mx.com.qtx.torneo.IJugador;

@Table
public class Jugador implements IJugador, Persistable<String>{
	@Id
	@Column("jug_id")
	private String id;
	@Column("jug_nombre")
	private String nombre;
	@Column("jug_numero")
	private int numero;
	@Column("jug_posicion")
	private String posicion;
	@Column("jug_fecNac")
	private Date fecNac;
	
	@Column("jug_lesionado")
	private boolean lesionado;
	@Column("jug_suspendido")
	private boolean suspendido;
	@Column("jug_titular")
	private boolean titular;
	@Column("jug_id_eq")
	private String idEquipo;
	
	@Column("eq_id")
	@Transient
	private Equipo equipo;
	@Transient
	private int nInserts = 0;
	
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
	public static IJugador crearJugador(Map<String, Object> datosJugador) {
		Jugador jugador = new Jugador();
		String id = (String) datosJugador.get("id");
		if(id == null)
			return null;
		jugador.setId(id);
		jugador.setNombre((String) datosJugador.getOrDefault("nombre", "indefinido"));
		jugador.setFecNac((Date) datosJugador.getOrDefault("fecNac", new Date()));
		jugador.setNumero((int) datosJugador.getOrDefault("numero", 1000));
		jugador.setPosicion((String) datosJugador.getOrDefault("posicion", "indefinida"));
		jugador.setTitular((boolean) datosJugador.getOrDefault("titular", false));
		jugador.setSuspendido((boolean) datosJugador.getOrDefault("suspendido", false));
		jugador.setLesionado((boolean) datosJugador.getOrDefault("lesionado", false));
		return jugador;
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
		if(equipo != null)
		  this.idEquipo = equipo.getId();
	}
	@Override
	public void setEquipo(IEquipo equipo) {
		if (equipo instanceof Equipo)
			this.setEquipo((Equipo)equipo);
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
	@Override
	public boolean isNew() {
		if (this.nInserts == 0) {
			this.nInserts++;
			return true;
		}
		return false;
	}

}
