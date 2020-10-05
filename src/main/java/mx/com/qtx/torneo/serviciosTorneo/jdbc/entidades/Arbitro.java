package mx.com.qtx.torneo.serviciosTorneo.jdbc.entidades;

import java.util.Date;
import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import mx.com.qtx.torneo.IArbitro;

@Table
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
