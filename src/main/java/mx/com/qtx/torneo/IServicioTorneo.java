package mx.com.qtx.torneo;

import java.util.List;
import java.util.Map;

import mx.com.qtx.torneo.entidades.Partido;

public interface IServicioTorneo {
		public void inicializarTorneo();
		
		public IEquipo crearEquipo(Map<String,Object> mapDatos);
		public Map<String, IEquipo> getEquipos();
		public IEquipo agregarEquipo(IEquipo equipo);
		public IEquipo getEquipo(String id);
		public IEquipo actualizarEquipo(IEquipo equipoIns);
		
		public int generarPartidos();
		public List<Partido> getPartidos();
		public Partido getPartido(int numPartido);
		public Partido actualizarPartido(Partido partido);
		public Partido cerrarPartido(Partido partido); //Hacer de solo lectura el partido
		
		public Map<String,IArbitro> getArbitros();
		public IArbitro getArbitro(int id);

		public IJugador crearJugador(Map<String, Object> datosJugador);

		public int agregarJugador(IEquipo equipoIns, IJugador jugador);

		
}
