package mx.com.qtx.servicio;

import java.util.Map;

public interface IEquipo {
	public String getNombreEquipo();
	public int getNumJugadores();
	public Map<Integer,String> getJugadoresTitulares();
	public String getPosicionJugador(int numJugador);
}
