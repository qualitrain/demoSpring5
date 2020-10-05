package mx.com.qtx.torneo.serviciosTorneo;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.com.qtx.torneo.IArbitro;
import mx.com.qtx.torneo.IEquipo;
import mx.com.qtx.torneo.IJugador;
import mx.com.qtx.torneo.IServicioTorneo;
import mx.com.qtx.torneo.entidades.Partido;
//import mx.com.qtx.torneo.serviciosTorneo.jdbc.entidades.Jugador;

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
	public boolean yaExisteEquipo(IEquipo equipo) {
		if( this.gestorDatos.leerEquipoXID(equipo.getID()) == null) {
			return false;
		}
		return true;
	}

	@Override
	@Transactional(noRollbackForClassName = "JugadoresDuplicadosException")
//	@Transactional
	public IEquipo agregarEquipo(IEquipo equipo) {
		IEquipo equipoEnBD = this.gestorDatos.leerEquipoXID(equipo.getID());
		if( equipoEnBD != null) 
			throw new EquipoYaExisteException("Insercion rechazada: Ya existe un equipo con el mismo id ("
					+ equipo + ") en base de datos");
		
		equipoEnBD = this.gestorDatos.insertarEquipo( equipo );
		if(equipo.getNumJugadores() == 0) {
			return equipoEnBD;
		}
		
		List<IJugador> jugadoresDuplicados = new ArrayList<IJugador>();
		for(IJugador jugI : equipoEnBD.getListaJugadores()) {
			
			IJugador jugBD = this.gestorDatos.leerJugadorXID(jugI.getId());
			if(jugBD != null)
				jugadoresDuplicados.add(jugI);
			else
				this.gestorDatos.insertarJugador(jugI);
		}
		if(jugadoresDuplicados.size() == 0)
			return equipoEnBD;
		String idsDupli = jugadoresDuplicados.stream()
				                              .map(j->j.getId())
				                              .reduce((a,b)-> a + "," + b)
				                              .get();
		throw new JugadoresDuplicadosException("Insercion rechazada:"
				+ "Ids duplicados:[" + idsDupli + "]" , jugadoresDuplicados);
		
	}

	@Override
	public IEquipo getEquipo(String id) {
		return this.gestorDatos.leerEquipoXID(id);
	}
	
	@Override
	public IEquipo crearEquipo(Map<String, Object> mapDatos) {
		return this.gestorDatos.crearEquipo(mapDatos);
	}

	@Override
	@Transactional
	public IEquipo actualizarEquipo(IEquipo equipo) {
		IEquipo equipoEnBD = this.gestorDatos.leerEquipoXID(equipo.getID());
		if(equipoEnBD == null)
			throw new EquipoNoExisteException("Actualización rechazada: "
												+ "Equipo no existe (id=" + equipo.getID() + ")");
		equipo = this.gestorDatos.actualizarEquipo(equipo);
		for(IJugador ijug:equipo.getListaJugadores()) {
			IJugador jugBD = this.gestorDatos.leerJugadorXID(ijug.getId());
			if(jugBD == null)
				this.gestorDatos.insertarJugador(ijug);
			else
				this.gestorDatos.actualizarJugador(ijug);
		}
		return equipo;
	}
	@Override
	@Transactional
	public IEquipo actualizarEquipoAgregado(IEquipo equipo) {
		IEquipo equipoEnBD = this.gestorDatos.leerEquipoXID(equipo.getID());
		if(equipoEnBD == null)
			throw new EquipoNoExisteException("Actualización rechazada: "
												+ "Equipo no existe (id=" + equipo.getID() + ")");
		equipo = this.gestorDatos.actualizarEquipoAgregado(equipo);
		return equipo;
	}
	@Transactional
	@Override
	public IEquipo eliminarEquipo(IEquipo eq) {
		IEquipo eqBorrado = this.gestorDatos.leerEquipoXIDConJugadores(eq.getID());
		if(eqBorrado == null)
			return null;
		for(IJugador jugI : eqBorrado.getListaJugadores()) {
			this.gestorDatos.borrarJugador(jugI);
		}
		this.gestorDatos.borrarEquipo(eqBorrado);
		return eqBorrado;
	}
	@Transactional
	@Override
	public IEquipo eliminarEquipoAgregado(IEquipo eq) {
		IEquipo eqBorrado = this.gestorDatos.leerEquipoXIDConJugadores(eq.getID());
		if(eqBorrado == null)
			return null;
		this.gestorDatos.borrarEquipo(eqBorrado);
		return eqBorrado;
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
		this.gestorDatos.cargarArbitros()
		                .forEach(a->mapArbitros.put("" + a.getId(), a));
		return mapArbitros;
	}

	@Override
	public IArbitro getArbitro(int id) {
		return this.gestorDatos.leerArbitroXID(id);
	}
	@Override
	public IArbitro crearArbitro(Map<String, Object> datosArbitro) {
		return this.gestorDatos.crearArbitro(datosArbitro);
	}
	
	@Override
	public IArbitro agregarArbitro(IArbitro iarbitro) {
		IArbitro iarbitroBD = this.gestorDatos.insertarArbitro(iarbitro);
		return iarbitroBD;
	}
	@Override
	public IArbitro eliminarArbitro(IArbitro iarbitro) {
		return this.gestorDatos.borrarArbitro(iarbitro);
	}

//----------------------------------------------------------------------------------

	@Override
	public Map<String, IJugador> getJugadores() {
		HashMap<String,IJugador> mapJugadores = new HashMap<>();
		this.gestorDatos.cargarJugadores()
		    .forEach(j->mapJugadores.put("" + j.getId(), j));
		return mapJugadores;
	}
	
	@Override
	public IJugador crearJugador(Map<String, Object> datosJugador) {
		return this.gestorDatos.crearJugador(datosJugador);
	}

	@Override
	public int agregarJugador(IEquipo equipo, IJugador jugador) {
		if (equipo.getListaJugadores().contains(jugador.getId()) )
		  return 0;
		equipo.agregarJugador(jugador);
		jugador.setEquipo(equipo);
		return 1;
	}

	@Override
	public Map<String,List<String>> getJugadoresXeqEnPosicion(String posicion){
		Map<String,List<String>> mapEquipNomJugadores = new HashMap<>();
		Map<String, List<IJugador>> mapEquipJugadores = this.gestorDatos.getJugadoresXposYequipo(posicion);
		for(String idEqI : mapEquipJugadores.keySet()) {
			List<String> lstNomJugadores = new ArrayList<>();
			mapEquipJugadores.get(idEqI).forEach(j->lstNomJugadores.add(j.getNombre()));
			mapEquipNomJugadores.put(idEqI, lstNomJugadores);
		}
		return mapEquipNomJugadores;
	}

	@Override
	public List<IJugador> getJugadoresEnPosiciones(String pos1, String pos2){
		return this.gestorDatos.getJugadoresEnUnaUotraPosicion(pos1, pos2);
	}
	@Override
	public List<IJugador> getJugadoresMasJovenes(){
		return this.gestorDatos.getJugadoresMasJovenes();
	}
	@Override
	public List<IJugador> getJugadoresOrdenados(){
		return this.gestorDatos.getJugadoresOrdenados();
	}
	@Override
	public List<IJugador> getJugadoresTitulares(){
		return this.gestorDatos.getJugadoresXtitularidad(true);				
//		return this.gestorDatos.getJugadoresTitulares();				
	}
	@Override
	public List<IJugador> getPaginaJugadores(int nPag){
		return this.gestorDatos.getJugadoresPorPagina(nPag);	
	}
	@Override
	public List<IJugador> getPaginaJugadoresTitulares(int nPag){
		return this.gestorDatos.getJugadoresTitularesPorPagina(nPag);	
	}
	@Override
	public List<IJugador> getPaginaJugadores(int tamanioPag, int nPag){
		this.gestorDatos.setRegsXpagina(tamanioPag);
		return this.gestorDatos.getJugadoresPorPagina(nPag);	
	}
	@Override
	public List<IJugador> getPaginaJugadoresTitulares(int tamanioPag, int nPag){
		this.gestorDatos.setRegsXpagina(tamanioPag);
		return this.gestorDatos.getJugadoresTitularesPorPagina(nPag);	
	}

}
