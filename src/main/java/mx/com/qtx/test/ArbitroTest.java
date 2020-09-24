package mx.com.qtx.test;

import mx.com.qtx.torneo.IArbitro;

public class ArbitroTest implements IArbitro{
	private static String[] nombres= {"Ramiro","Juan Carlos","Miguel Ángel","Mariano","Lucas","Jorge","Seferino"}; 
	private static String[] apellidos = {"Yañez","Jiménez","Mora","Gutiérrez", "López","Martínez","Ocaña","Morales"};
	
	private String nombre;

	public ArbitroTest() {
		int numAleatorio = (int) (Math.random() * 100000);
		String nombreArbitro = nombres[numAleatorio % nombres.length] + " ";
		numAleatorio = (int) (Math.random() * 10000);
		this.nombre = nombreArbitro + apellidos[numAleatorio % apellidos.length];
	}
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	@Override
	public int getId() {
		return 0;
	}
	
}
