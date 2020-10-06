package mx.com.qtx.torneo.serviciosTorneo.persisJpaRep;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import mx.com.qtx.torneo.IArbitro;
import mx.com.qtx.torneo.IEquipo;
import mx.com.qtx.torneo.IJugador;
import mx.com.qtx.torneo.serviciosTorneo.IGestorDatos;
import mx.com.qtx.torneo.serviciosTorneo.jpa.entidades.Arbitro;
import mx.com.qtx.torneo.serviciosTorneo.jpa.entidades.Equipo;
import mx.com.qtx.torneo.serviciosTorneo.jpa.entidades.Jugador;
import mx.com.qtx.torneo.serviciosTorneo.persisJdbcRep.GestorDatosJdbcRepository;

@Primary
@Repository
public class GestorDatosJpaRepository implements IGestorDatos {
	private static int regsXpagina = 3;

	@Autowired
	private ICrudRepositoryEquipoJpa repEquipo;
	
	@Autowired
	private ICrudRepositoryJugadorJpa repJugador;

	@Autowired
	private ICrudRepositoryArbitroJpa repArbitro;
	
	@Autowired
	private IPageAndSortRepositoryJugadorJpa repPsJugador;
	
	@Override
	public List<IEquipo> cargarEquipos() {
		List<IEquipo> iequipos = new ArrayList<>();
		this.repEquipo.findAll()
		              .forEach(eq-> iequipos.add(eq));
		return iequipos;
	}

	@Override
	public IEquipo leerEquipoXID(String id) {
		Optional<Equipo> equipo = this.repEquipo.findById(id);
		if(equipo.isPresent()) {
			return equipo.get();
		}
		return null;
	}

	@Override
	public IEquipo actualizarEquipo(IEquipo iequipo) { // distinto de versión jdbc
		if(!(iequipo instanceof Equipo)) 
			return null;
		Equipo equipoUpt = (Equipo) iequipo;
		
		Set<Jugador> setJugs = equipoUpt.getJugadores();
		equipoUpt.setJugadores(null);
		
		equipoUpt = this.repEquipo.save(equipoUpt); 
		
		equipoUpt.setJugadores(setJugs);
		return equipoUpt;		
	}

	@Override
	public IEquipo insertarEquipo(IEquipo iequipo) {
		Equipo equipoIns = null;
		if(!(iequipo instanceof Equipo)) 
			return null;
		equipoIns = (Equipo) iequipo;
		Set<Jugador> jugadores = equipoIns.getJugadores();
		equipoIns.setJugadores(null);
		Equipo nvoEquipo = this.repEquipo.save(equipoIns);
		nvoEquipo.setJugadores(jugadores);
		return nvoEquipo;
	}

	@Override
	public IEquipo borrarEquipo(IEquipo ieq) { // Funciona distinto que para jdbc: Aquí no borra agregados!!
		System.out.println("   ***** " + this.getClass().getName() + ".borrarEquipo(" + ieq + ") *****");
		if(!(ieq instanceof Equipo))
			return null;
		Equipo eq = (Equipo) ieq;
		this.repEquipo.delete(eq);
		return eq;
	}

	@Override
	public IEquipo leerEquipoXIDConJugadores(String id) {
		return this.leerEquipoXID(id);
	}

	@Override
	public IEquipo insertarEquipoAgregado(IEquipo iequipo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IEquipo actualizarEquipoAgregado(IEquipo iequipo) { // Es distinta de la versión jdbc!!
		Equipo equipoUpt = null;
		if(!(iequipo instanceof Equipo)) 
			return null;
		equipoUpt = (Equipo) iequipo;
		
		for(Jugador jugI :equipoUpt.getJugadores()) {
			this.repJugador.save(jugI);
		}
				
		Equipo nvoEquipo = this.repEquipo.save(equipoUpt); 
		return nvoEquipo;
	}

	@Override
	public List<IArbitro> cargarArbitros() {
		List<IArbitro> listArbitros = new ArrayList<>();
		this.repArbitro.findAll()
		               .forEach(ar->listArbitros.add(ar));
		return listArbitros;
	}

	@Override
	public IArbitro leerArbitroXID(int id) {
		Optional<Arbitro> arb = this.repArbitro.findById(id);
		if(arb.isPresent())
			return arb.get();
		return null;
	}

	@Override
	public IArbitro insertarArbitro(IArbitro iarbitro) {
		if(!(iarbitro instanceof Arbitro))
			return null;
		Arbitro arbitro = (Arbitro) iarbitro;
		return this.repArbitro.save(arbitro);
	}

	@Override
	public IArbitro actualizarArbitro(IArbitro arbitro) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IArbitro borrarArbitro(IArbitro iarb) {
		if(!(iarb instanceof Arbitro))
			return null;
		Arbitro arb = (Arbitro) iarb;
		this.repArbitro.delete(arb);
		return arb;
	}

	@Override
	public List<IJugador> cargarJugadores() {
		List<IJugador> listaIJugadores = new ArrayList<>();
		this.repJugador.findAll()
		               .forEach(jug -> listaIJugadores.add(jug));
		return listaIJugadores;
	}

	@Override
	public IJugador leerJugadorXID(String id) {
		Optional<Jugador> jugador = this.repJugador.findById(id);
		if (jugador.isPresent())
			return jugador.get();
		return null;
	}

	@Override
	public IJugador actualizarJugador(IJugador ijugador) {
		Jugador jugUpt = null;
		if(!(ijugador instanceof Jugador)) 
			return null;
		jugUpt = (Jugador) ijugador;
		Jugador nvoJugador = this.repJugador.save(jugUpt);
		return nvoJugador;
	}

	@Override
	public List<IJugador> leerJugadoresXEquipo(String idEquipo) {
		List<IJugador> listaIJugadores = new ArrayList<>();
		this.repJugador.findByIdEquipo(idEquipo).forEach(jug -> listaIJugadores.add(jug));
		return listaIJugadores;
	}

	@Override
	public IJugador insertarJugador(IJugador ijugador) {
		Jugador jugIns = null;
		if(!(ijugador instanceof Jugador)) 
			return null;
		jugIns = (Jugador) ijugador;
		Jugador nvoJugador = this.repJugador.save(jugIns);
		return nvoJugador;
	}

	@Override
	public IJugador borrarJugador(IJugador ijug) {
		System.out.println("   ***** " + this.getClass().getName() + ".borrarJugador(" + ijug + ") *****");
		if(!(ijug instanceof IJugador))
			return null;
		Jugador jug = (Jugador) ijug;
		this.repJugador.delete(jug);
		return jug;
	}

	@Override
	public Map<String, List<IJugador>> getJugadoresXposYequipo(String pos) {
		Map<String,List<IJugador>> mapEquipoJugadoresEnPos = new HashMap<>();
		List<String> idsEquipos = this.repPsJugador.findDistinctIdEquipoBy();

		for(String idEquipoI : idsEquipos) {
			List<IJugador> listJugadores = new ArrayList<>();
			this.repPsJugador.findByPosicionAndIdEquipo(pos, idEquipoI)
			                 .forEach(j->listJugadores.add(j));
			mapEquipoJugadoresEnPos.put(idEquipoI, listJugadores);
		}
		return mapEquipoJugadoresEnPos;
	}

	@Override
	public List<IJugador> getJugadoresEnUnaUotraPosicion(String pos1, String pos2) {
		List<IJugador> lstJugadores = new ArrayList<>();
		this.repPsJugador.findByPosicionOrPosicionOrderByPosicion(pos1, pos2)
		                 .forEach(j->lstJugadores.add(j));
		return lstJugadores;
	}

	@Override
	public List<IJugador> getJugadoresMasJovenes() {
		List<IJugador> lstJugadores = new ArrayList<>();
		this.repPsJugador.findFirst5ByOrderByFecNacDesc()
		                 .forEach(j->lstJugadores.add(j));
		return lstJugadores;
	}

	@Override
	public List<IJugador> getJugadoresOrdenados() {
		List<IJugador> lstJugadores = new ArrayList<>();
		Sort ordenamiento = Sort.by("fecNac").descending()
				                .and(Sort.by("nombre").ascending());
		this.repPsJugador.findAll(ordenamiento)
		                 .forEach(j->lstJugadores.add(j));
		return lstJugadores;		
	}

	@Override
	public List<IJugador> getJugadoresXtitularidad(boolean esTitular) {
		List<IJugador> lstJugadores = new ArrayList<>();
		Sort ordenamiento = Sort.by("posicion","nombre");
		this.repPsJugador.findByTitular(true, ordenamiento)
		                 .forEach(j->lstJugadores.add(j));
		return lstJugadores;		
	}

	@Override
	public List<IJugador> getJugadoresPorPagina(int nPag) {
		List<IJugador> lstJugadores = new ArrayList<>();
		Sort ordenamiento = Sort.by("nombre");
		Pageable paginable = PageRequest.of(nPag, GestorDatosJpaRepository.regsXpagina, ordenamiento);
		this.repPsJugador.findAll(paginable)
		                 .toList().forEach(j->lstJugadores.add(j));
		return lstJugadores;		
	}

	@Override
	public List<IJugador> getJugadoresTitularesPorPagina(int nPag) {
		List<IJugador> lstJugadores = new ArrayList<>();
		Sort ordenamiento = Sort.by("nombre");
		Pageable paginable = PageRequest.of(nPag, GestorDatosJpaRepository.regsXpagina, ordenamiento);
		List<Jugador> lstJugadoresPag = this.repPsJugador.findByTitular( true, paginable );
		lstJugadoresPag.forEach(j->lstJugadores.add(j));
		                 
		return lstJugadores;		
	}

	@Override
	public int getRegsXpagina() {
		return GestorDatosJpaRepository.regsXpagina;
	}

	@Override
	public void setRegsXpagina(int regsXpagina) {
		GestorDatosJpaRepository.regsXpagina = regsXpagina;

	}

	@Override
	public List<IJugador> getJugadoresTitulares() {
		List<IJugador> lstJugadores = new ArrayList<>();
		this.repPsJugador.findByTitularTrue()
		                 .forEach(j->lstJugadores.add(j));
		return lstJugadores;		
	}

	@Override
	public IArbitro crearArbitro(Map<String, Object> datosArbitro) {
		return Arbitro.crearArbitro(datosArbitro);
	}

	@Override
	public IEquipo crearEquipo(Map<String, Object> mapDatos) {
		return Equipo.crearEquipo(mapDatos);
	}

	@Override
	public IJugador crearJugador(Map<String, Object> datosJugador) {
		return Jugador.crearJugador(datosJugador);
	}

}
