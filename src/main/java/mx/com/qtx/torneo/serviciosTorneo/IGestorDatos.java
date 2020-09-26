package mx.com.qtx.torneo.serviciosTorneo;

import java.util.List;

import mx.com.qtx.torneo.IArbitro;
import mx.com.qtx.torneo.IEquipo;
import mx.com.qtx.torneo.IJugador;

public interface IGestorDatos {
	List<IEquipo> cargarEquipos();
	IEquipo leerEquipoXID(String id);
	IEquipo actualizarEquipo(IEquipo equipo);
	IEquipo insertarEquipo(IEquipo equipo);
	
	List<IArbitro> cargarArbitros();
	IArbitro leerArbitroXID(int id);
	IArbitro insertarArbitro(IArbitro iarbitro);
	IArbitro actualizarArbitro(IArbitro arbitro);

	List<IJugador> cargarJugadores();
	IJugador leerJugadorXID(String id);
	IJugador actualizarJugador(IJugador jugador);
	List<IJugador> leerJugadoresXEquipo(String idEquipo);
	IJugador insertarJugador(IJugador ijugador);

}
