package mx.com.qtx.test.spel;

public class Vacuna {
	private String nombre;
	private int numDosis;

	public Vacuna(String nombre, int numDosis) {
		this.nombre = nombre;
		this.numDosis = numDosis;
	}

	public Vacuna() {
		super();
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getNumDosis() {
		return numDosis;
	}

	public void setNumDosis(int numDosis) {
		this.numDosis = numDosis;
	}

	@Override
	public String toString() {
		return "Vacuna [nombre=" + nombre + ", numDosis=" + numDosis + "]";
	}

}
