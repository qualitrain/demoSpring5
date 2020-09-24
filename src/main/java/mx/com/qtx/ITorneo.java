package mx.com.qtx;

import java.util.List;

import mx.com.qtx.torneo.IEquipo;

public interface ITorneo {
	public void mostrarBienvenida();
	public void mostrarArbitros();
	public void mostrarDatosGenerales();
	public List<String> getJugadores(String equipo);
	public void agregarEquipo(IEquipo equipo);
	public void generarPartidas();
	public void mostrarPartidas();
	public void mostrarJugadores();
	
	public void testActualizaciones();
}
