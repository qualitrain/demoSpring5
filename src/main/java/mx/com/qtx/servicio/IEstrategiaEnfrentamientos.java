package mx.com.qtx.servicio;

import java.util.Map;

public interface IEstrategiaEnfrentamientos {
	public void agregarEquipo(String nomEquipo);
	public Map<Integer,String[]> generarPartidas();
}
