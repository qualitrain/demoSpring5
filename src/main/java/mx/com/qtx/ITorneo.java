package mx.com.qtx;

import java.util.List;

import mx.com.qtx.torneo.IEquipo;

public interface ITorneo {
	void mostrarBienvenida();
	void mostrarArbitros();
	void mostrarDatosGenerales();
	List<String> getJugadores(String equipo);
	void agregarEquipo(IEquipo equipo);
	void generarPartidas();
	void mostrarPartidas();
	void mostrarJugadores();
	
	void testActualizaciones();
	void testInserciones();
	void testEliminaciones();
}
