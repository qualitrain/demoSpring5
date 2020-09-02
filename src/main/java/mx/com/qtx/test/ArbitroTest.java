package mx.com.qtx.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import mx.com.qtx.servicio.IArbitro;

@Primary
@Scope("prototype")
@Component
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
	
	@Bean
//	@Scope("prototype")
	@Qualifier("propuestos")
	public static List<IArbitro> proponerArbitros(){
		List<IArbitro> listArbitros = new ArrayList<>();
		for(int i=0; i<5; i++)
		   listArbitros.add(new ArbitroTest());
		return listArbitros;
	}
	
	@Bean
	@Qualifier("propuestos")
	public static Map<Integer,IArbitro> getMapArbitros(
			                  @Qualifier("propuestos")
			                  List<IArbitro> arbitros){
		Map<Integer,IArbitro> mapArbitros = new HashMap<>();
		for(int i=0; i<arbitros.size(); i++) 
			mapArbitros.put((i+ 1), arbitros.get(i));
		return mapArbitros;
	}

}
