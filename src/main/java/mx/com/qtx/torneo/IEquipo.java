package mx.com.qtx.torneo;

import java.util.List;
import java.util.Map;

import mx.com.qtx.torneo.serviciosTorneo.jdbc.entidades.Jugador;

public interface IEquipo {
	
	public String getID();
	public void setID(String id);
	public String getNombreEquipo();
	public void setNombreEquipo(String nombre);
	
	public int getNumJugadores();
	public int agregarJugador(IJugador jugador);
	public Map<Integer,String> getJugadoresTitulares();
	public String getPosicionJugador(int numJugador);
	public List<IJugador>getListaJugadores();
}
