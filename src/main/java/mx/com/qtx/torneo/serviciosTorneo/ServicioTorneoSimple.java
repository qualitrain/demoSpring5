package mx.com.qtx.torneo.serviciosTorneo;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.qtx.torneo.IArbitro;
import mx.com.qtx.torneo.IEquipo;
import mx.com.qtx.torneo.IJugador;
import mx.com.qtx.torneo.IServicioTorneo;
import mx.com.qtx.torneo.entidades.Partido;
import mx.com.qtx.torneo.serviciosTorneo.entidades.Equipo;
import mx.com.qtx.torneo.serviciosTorneo.entidades.Jugador;

@Service
public class ServicioTorneoSimple implements IServicioTorneo {
	
	@Autowired
	private IGestorDatos gestorDatos;

	@Override
	public void inicializarTorneo() {
		// TODO Auto-generated method stub
		
	}
//----------------------------------------------------------------------------------
	@Override
	public Map<String, IEquipo> getEquipos() {
		Map<String,IEquipo> mapEquipos = new HashMap<String,IEquipo>();
		this.gestorDatos.cargarEquipos()
		                .forEach(e->mapEquipos.put(e.getNombreEquipo(),e));
		for(IEquipo equipoI : mapEquipos.values()) {
			if(equipoI.getListaJugadores() == null || equipoI.getListaJugadores().size() == 0) {
				this.gestorDatos.leerJugadoresXEquipo(equipoI.getID())
				                .forEach(j -> equipoI.agregarJugador(j));
			}
		}
		return mapEquipos;
	}

	@Override
	public IEquipo agregarEquipo(IEquipo equipo) {
		return this.gestorDatos.insertarEquipo(equipo);
	}

	@Override
	public IEquipo getEquipo(String id) {
		return this.gestorDatos.leerEquipoXID(id);
	}
	
	@Override
	public IEquipo crearEquipo(Map<String, Object> mapDatos) {
		Equipo equipo = new Equipo();
		String id = (String) mapDatos.get("id");
		if(id == null)
			return null;
		equipo.setId(id);
		equipo.setApodo((String) mapDatos.getOrDefault("apodo",""));
		equipo.setNombre((String) mapDatos.getOrDefault("nombre","no especificado"));
		equipo.setEntrenador((String) mapDatos.getOrDefault("entrenador", "no especificado"));
		equipo.setPatrocinador((String) mapDatos.getOrDefault("patrocinador", "no especificado"));
		equipo.setJugadores((Set<Jugador>) mapDatos.getOrDefault("jugadores", new HashSet<Jugador>()));
		return equipo;
	}

	public IEquipo actualizarEquipo(IEquipo equipo) {
		IEquipo equipoAnt = this.gestorDatos.actualizarEquipo(equipo);
		return equipoAnt;
	}

//----------------------------------------------------------------------------------
	@Override
	public int generarPartidos() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Partido> getPartidos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Partido getPartido(int numPartido) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Partido actualizarPartido(Partido partido) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Partido cerrarPartido(Partido partido) {
		// TODO Auto-generated method stub
		return null;
	}
//----------------------------------------------------------------------------------
	@Override
	public Map<String, IArbitro> getArbitros() {
		HashMap<String,IArbitro> mapArbitros = new HashMap<>();
		this.gestorDatos.cargarArbitros().forEach(a->mapArbitros.put("" + a.getId(), a));
		return mapArbitros;
	}

	@Override
	public IArbitro getArbitro(int id) {
		return this.gestorDatos.leerArbitroXID(id);
	}

//----------------------------------------------------------------------------------

	@Override
	public Map<String, IJugador> getJugadores() {
		HashMap<String,IJugador> mapJugadores = new HashMap<>();
		this.gestorDatos.cargarJugadores().forEach(j->mapJugadores.put("" + j.getId(), j));
		return mapJugadores;
	}
	
	@Override
	public IJugador crearJugador(Map<String, Object> datosJugador) {
		Jugador jugador = new Jugador();
		String id = (String) datosJugador.get("id");
		if(id == null)
			return null;
		jugador.setId(id);
		jugador.setNombre((String) datosJugador.getOrDefault("nombre", "indefinido"));
		jugador.setFecNac((Date) datosJugador.getOrDefault("fecNac", new Date()));
		jugador.setNumero((int) datosJugador.getOrDefault("numero", 1000));
		jugador.setPosicion((String) datosJugador.getOrDefault("posicion", "indefinida"));
		jugador.setTitular((boolean) datosJugador.getOrDefault("titular", false));
		jugador.setSuspendido((boolean) datosJugador.getOrDefault("suspendido", false));
		jugador.setLesionado((boolean) datosJugador.getOrDefault("lesionado", false));
		return jugador;
	}

	@Override
	public int agregarJugador(IEquipo equipo, IJugador jugador) {
		if (equipo.getListaJugadores().contains(jugador.getId()) )
		  return 0;
		equipo.agregarJugador(jugador);
		jugador.setEquipo(equipo);
		return 1;
	}



}
