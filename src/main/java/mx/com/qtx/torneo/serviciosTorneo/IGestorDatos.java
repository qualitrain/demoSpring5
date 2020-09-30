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
	IEquipo borrarEquipo(IEquipo eq);
	IEquipo leerEquipoXIDConJugadores(String id);
	IEquipo insertarEquipoAgregado(IEquipo iequipo);
	IEquipo actualizarEquipoAgregado(IEquipo equipo);
	
	List<IArbitro> cargarArbitros();
	IArbitro leerArbitroXID(int id);
	IArbitro insertarArbitro(IArbitro iarbitro);
	IArbitro actualizarArbitro(IArbitro arbitro);
	IArbitro borrarArbitro(IArbitro arb);

	List<IJugador> cargarJugadores();
	IJugador leerJugadorXID(String id);
	IJugador actualizarJugador(IJugador jugador);
	List<IJugador> leerJugadoresXEquipo(String idEquipo);
	IJugador insertarJugador(IJugador ijugador);
	IJugador borrarJugador(IJugador jug);

}
