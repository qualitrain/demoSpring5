package mx.com.qtx.torneo.serviciosTorneo.jpa.entidades;

import java.util.Date;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import mx.com.qtx.torneo.IEquipo;
import mx.com.qtx.torneo.IJugador;
import mx.com.qtx.util.EdadMax;
import mx.com.qtx.util.EdadMin;

@Entity
public class Jugador implements IJugador{
	public static final int LONG_MIN_ID = 5;
	public static final int LONG_MAX_ID = 15;
	public static final int LONG_MIN_NOMBRE = 12;
	public static final int LONG_MAX_NOMBRE = 45;
	public static final int LONG_MIN_POSICION = 3;
	public static final int LONG_MAX_POSICION = 20;
	public static final int NUMERO_MIN = 2;
	public static final int NUMERO_MAX = 120;
	
	@Id
	@Column(name="jug_id")	
	@NotBlank(message = "idJugador.vacio")
	@Size(min = LONG_MIN_ID, message="jugador.tamIdErr", max = LONG_MAX_ID )
	private String id;

	@Column(name="jug_nombre")	
	@NotBlank(message = "jugador.NomVacio")
	@Size(min = LONG_MIN_NOMBRE, message="jugador.tamNomErr", max = LONG_MAX_NOMBRE )
	private String nombre;
	
	@Column(name="jug_numero")	
	@Min(message = "jugador.numBajoMin", value=NUMERO_MIN)
	@Max(message = "jugador.numSobreMax", value=NUMERO_MAX)
	private int numero;
	
	@Column(name="jug_posicion")
	@Size(min = LONG_MIN_POSICION, message="jugador.tamPosErr", max = LONG_MAX_POSICION )
	private String posicion;
	
	@Column(name="jug_fecNac")               // JPA
	@DateTimeFormat(pattern = "yyyy-MM-dd")  // Para empatar el Binding vs el formato usado por navegador
	@EdadMax(edadMax = 40, message = "jugador.muyMayor")
	@EdadMin(edadMin = 15, message = "jugador.muyMenor")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern= "yyyy-MM-dd")	// Jackson Bind
	private Date fecNac;
	
	@Column(name="jug_lesionado")
	private boolean lesionado;
	@Column(name="jug_suspendido")
	private boolean suspendido;
	@Column(name="jug_titular")
	private boolean titular;
	
	@Column(name="jug_id_eq", insertable = false, updatable = false)
	@NotBlank(message = "jugador.idEqVacio")
	private String idEquipo;
	
	@ManyToOne
	@JoinColumn(name="jug_id_eq", referencedColumnName= "eq_id")
	@JsonIgnore // Jackson Binding: generación JSon
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
	public static IJugador crearJugador(Map<String, Object> datosJugador) {
		Jugador jugador = new Jugador();
		String id = (String) datosJugador.get("id");
		if(id == null)
			return null;
		jugador.setId(id);
		jugador.setNombre((String) datosJugador.getOrDefault("nombre", "indefinido"));
		jugador.setFecNac((Date) datosJugador.getOrDefault("fecNac", new Date()));
		jugador.setNumero((int) datosJugador.getOrDefault("numero", 10));
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

}
