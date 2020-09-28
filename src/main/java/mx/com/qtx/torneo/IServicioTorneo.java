package mx.com.qtx.torneo;

import java.util.List;
import java.util.Map;

import mx.com.qtx.torneo.entidades.Partido;

public interface IServicioTorneo {
		void inicializarTorneo();
		
		IEquipo crearEquipo(Map<String,Object> mapDatos);
		Map<String, IEquipo> getEquipos();
		IEquipo agregarEquipo(IEquipo equipo);
		IEquipo getEquipo(String id);
		IEquipo actualizarEquipo(IEquipo equipoIns);
		boolean yaExisteEquipo(IEquipo equipo);
		IEquipo eliminarEquipo(IEquipo eq);
		
		int generarPartidos();
		List<Partido> getPartidos();
		Partido getPartido(int numPartido);
		Partido actualizarPartido(Partido partido);
		Partido cerrarPartido(Partido partido); //Hacer de solo lectura el partido
		
		IArbitro crearArbitro(Map<String, Object> datosArbitro);
		Map<String,IArbitro> getArbitros();
		IArbitro getArbitro(int id);
		IArbitro agregarArbitro(IArbitro iarbitro);

		IJugador crearJugador(Map<String, Object> datosJugador);
		int agregarJugador(IEquipo equipoIns, IJugador jugador);
		Map<String, IJugador> getJugadores();

}
