package mx.com.qtx.test;

import java.util.ArrayList;
import java.util.List;

import mx.com.qtx.servicio.IArbitro;

public class ArbitroBDMemoria implements IArbitro{
	private static List<ArbitroBDMemoria> arbitros = inicilizarArbitros();
	private String nombre;

	public ArbitroBDMemoria() { }
	public ArbitroBDMemoria(String nombre) {
		this.nombre = nombre;
	}
	private static List<ArbitroBDMemoria> inicilizarArbitros() {
		List<ArbitroBDMemoria> listArbitros = new ArrayList<>();
		listArbitros.add( new ArbitroBDMemoria("José Luis Basualdo Ramírez"));
		listArbitros.add( new ArbitroBDMemoria("Martín Reyes De la Paz"));
		listArbitros.add( new ArbitroBDMemoria("Oscar Pérez De la Cueva"));
		listArbitros.add( new ArbitroBDMemoria("Fernando Hierro Ramírez"));
		listArbitros.add( new ArbitroBDMemoria("Jesús Mora Martínez"));
		listArbitros.add( new ArbitroBDMemoria("Marco Antonio Ramírez Valle"));
		return listArbitros;
	}
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public static List<ArbitroBDMemoria> getArbitros() {
		return arbitros;
	}
	
}
