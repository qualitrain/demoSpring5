package mx.com.qtx.torneo.serviciosTorneo;

import java.util.List;

import mx.com.qtx.torneo.IArbitro;
import mx.com.qtx.torneo.IEquipo;
import mx.com.qtx.torneo.IJugador;

public interface IGestorDatos {
	public List<IEquipo> cargarEquipos();
	public IEquipo leerEquipoXID(String id);
	public IEquipo actualizarEquipo(IEquipo equipo);
	public IEquipo insertarEquipo(IEquipo equipo);
	
	public List<IArbitro> cargarArbitros();
	public IArbitro leerArbitroXID(int id);
	public IArbitro actualizarArbitro(IArbitro arbitro);

	public List<IJugador> cargarJugadores();
	public IJugador leerJugadorXID(String id);
	public IJugador actualizarJugador(IJugador jugador);
	public List<IJugador> leerJugadoresXEquipo(String idEquipo);

}
