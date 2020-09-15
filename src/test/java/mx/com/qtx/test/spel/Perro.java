package mx.com.qtx.test.spel;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Perro {
	private String nombre;
	private String raza;
	private Map<String,VacunaPerro> vacunas;
	private Persona propietario;
	
	public Perro() {
		super();
		this.vacunas = new HashMap<>();
	}
	public Perro(String nombre, String raza) {
		this.nombre = nombre;
		this.raza = raza;
		this.vacunas = new HashMap<>();
	}
	public void aplicarVacuna(Vacuna vacuna, Date fecha) {
		VacunaPerro vacunaPerro = this.vacunas.get(vacuna.getNombre());
		if (vacunaPerro == null) {
			vacunaPerro = new VacunaPerro(this,vacuna);
			this.vacunas.put(vacuna.getNombre(),vacunaPerro );
		}
		vacunaPerro.getAplicaciones()
		           .add(fecha);
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getRaza() {
		return raza;
	}
	public void setRaza(String raza) {
		this.raza = raza;
	}
	public Map<String, VacunaPerro> getVacunas() {
		return vacunas;
	}
	public void setVacunas(Map<String, VacunaPerro> vacunas) {
		this.vacunas = vacunas;
	}
	public Persona getPropietario() {
		return propietario;
	}
	public void setPropietario(Persona propietario) {
		this.propietario = propietario;
	}
	@Override
	public String toString() {
		return "Perro [nombre=" + nombre + ", raza=" + raza + ", vacunas=" + vacunas + ", propietario=" + propietario
				+ "]";
	}
	

}
