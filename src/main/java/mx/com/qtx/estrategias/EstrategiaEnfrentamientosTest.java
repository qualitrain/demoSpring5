package mx.com.qtx.estrategias;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import mx.com.qtx.servicio.IEstrategiaEnfrentamientos;

@Component
public class EstrategiaEnfrentamientosTest implements IEstrategiaEnfrentamientos{
	private List<String> equipos;
	
	public EstrategiaEnfrentamientosTest() {
		this.equipos = new ArrayList<>();
	}

	@Override
	public void agregarEquipo(String nomEquipo) {
		this.equipos.add(nomEquipo);
	}

	@Override
	public Map<Integer, String[]> generarPartidas() {
		Map<Integer, String[]> partidos = new HashMap<>();
		partidos.put(1, new String[] { this.equipos.get(0), this.equipos.get(1)});
		partidos.put(2, new String[] { this.equipos.get(1), this.equipos.get(0)});
		partidos.put(3, new String[] { this.equipos.get(2), this.equipos.get(3)});
		partidos.put(4, new String[] { this.equipos.get(3), this.equipos.get(2)});
		return partidos;
	}

}
