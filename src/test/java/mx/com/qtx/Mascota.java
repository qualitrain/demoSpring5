package mx.com.qtx;

public class Mascota {
	private String nombre;
	private float peso;
	public Mascota() {
		super();
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public float getPeso() {
		return peso;
	}
	public void setPeso(float peso) {
		this.peso = peso;
	}
	@Override
	public String toString() {
		return "Mascota [nombre=" + nombre + ", peso=" + peso + "]";
	}

}
