package mx.com.qtx.torneo.serviciosTorneo.jpa.entidades;

import java.util.Date;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import mx.com.qtx.torneo.IArbitro;

@Entity
public class Arbitro implements IArbitro{
	@Id
	@Column(name = "ar_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Column(name = "ar_nombre")
	private String nombre;
	@Column(name = "ar_fecNac")
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
	
	public static IArbitro crearArbitro(Map<String, Object> datosArbitro) {
		Arbitro arbitro = new Arbitro();
		arbitro.setNombre((String) datosArbitro.getOrDefault("nombre", "indefinido"));
		arbitro.setFecNac((Date) datosArbitro.getOrDefault("fecNac", new Date()));
		return arbitro;
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
