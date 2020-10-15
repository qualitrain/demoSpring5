package mx.com.qtx.torneo;

import java.util.List;
import java.util.Map;

public interface IEquipo {
	
	String getID();
	void setID(String id);
	String getNombreEquipo();
	void setNombreEquipo(String nombre);
	
	Object getCampo(String nombre);
	
	int getNumJugadores();
	int agregarJugador(IJugador jugador);
	Map<Integer,String> getJugadoresTitulares();
	String getPosicionJugador(int numJugador);
	List<IJugador>getListaJugadores();
}
