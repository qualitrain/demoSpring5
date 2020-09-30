package mx.com.qtx.torneo.serviciosTorneo.entidades;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;

import mx.com.qtx.torneo.IArbitro;

public class Arbitro implements IArbitro{
	@Id
	@Column("ar_id")
	private int id;
	@Column("ar_nombre")
	private String nombre;
	@Column("ar_fecNac")
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
	
	public Arbitro withId(int id) { //Se requiere porque id es una llave auto-generada por el DBMS
		return new Arbitro(id,this.nombre, this.fecNac);
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
