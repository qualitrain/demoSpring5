package mx.com.qtx.test.spel;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

public class VacunaPerro {
	private Perro perro;
	private Vacuna vacuna;
	private List<Date> aplicaciones;
	
	public VacunaPerro(Perro perro, Vacuna vacuna) {
		this.perro = perro;
		this.vacuna = vacuna;
		this.aplicaciones = new ArrayList<>();
	}
	
	public Perro getPerro() {
		return perro;
	}
	public void setPerro(Perro perro) {
		this.perro = perro;
	}
	public Vacuna getVacuna() {
		return vacuna;
	}
	public void setVacuna(Vacuna vacuna) {
		this.vacuna = vacuna;
	}
	public List<Date> getAplicaciones() {
		return aplicaciones;
	}
	public void setAplicaciones(List<Date> aplicaciones) {
		this.aplicaciones = aplicaciones;
	}

	@Override
	public String toString() {
		return "VacunaPerro [perro=" + perro.getNombre() + ", vacuna=" + vacuna + ", aplicaciones=" + aplicaciones + "]";
	}

	
}
