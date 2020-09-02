package mx.com.qtx.test;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import mx.com.qtx.servicio.IArbitro;

@Qualifier("arbitroOficial")
@Component
//("arbitroAleatorio")
public class ArbitroTest implements IArbitro{
	private static String[] nombres= {"Ramiro","Juan Carlos","Miguel Ángel","Mariano","Lucas"}; 
	private static String[] apellidos = {"Yañez","Jiménez","Mora","Gutiérrez", "López","Martínez"};
	
	private String nombre;

	public ArbitroTest() {
		int numAleatorio = (int) (Math.random() * 10000);
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

}
