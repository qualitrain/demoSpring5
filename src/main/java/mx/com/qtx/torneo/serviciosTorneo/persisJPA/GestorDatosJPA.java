package mx.com.qtx.torneo.serviciosTorneo.persisJPA;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import mx.com.qtx.torneo.IArbitro;
import mx.com.qtx.torneo.IEquipo;
import mx.com.qtx.torneo.IJugador;
import mx.com.qtx.torneo.serviciosTorneo.IGestorDatos;
import mx.com.qtx.torneo.serviciosTorneo.jpa.entidades.Arbitro;
import mx.com.qtx.torneo.serviciosTorneo.jpa.entidades.Equipo;
import mx.com.qtx.torneo.serviciosTorneo.jpa.entidades.Jugador;

@Primary
@Repository
public class GestorDatosJPA implements IGestorDatos {
	@PersistenceContext
	EntityManager em;

	@Override
	@Transactional(readOnly = true)
	public List<IEquipo> cargarEquipos() {
		String consulta = "SELECT e FROM Equipo e";

		List<IEquipo> lstIequipos = new ArrayList<>();		
		em.createQuery(consulta)
		  .getResultStream()
		  .forEach(eq->lstIequipos.add( (IEquipo)eq) );
		lstIequipos.forEach(eq->eq.getListaJugadores().size());
		return lstIequipos;
	}

	@Override
	@Transactional(readOnly = true)
	public IEquipo leerEquipoXID(String id) {
		return em.find(Equipo.class, id);
	}

	@Override
	@Transactional
	public IEquipo actualizarEquipo(IEquipo iequipo) { // Ignorando a sus jugadores!
//		System.out.println("   ***** " + this.getClass().getName()
//				+ ".actualizarEquipo(" + iequipo + ")");
		if( !(iequipo instanceof Equipo) )
			return null;
		
		Equipo equipoUpt = (Equipo) iequipo;
		
		Set<Jugador> setJugs = equipoUpt.getJugadores();
		equipoUpt.setJugadores(null);
		
		em.merge(equipoUpt);
		
		equipoUpt.setJugadores(setJugs);
		return equipoUpt;
	}
	
	@Override
	@Transactional
	public IEquipo actualizarEquipoAgregado(IEquipo iequipo) {
		if( !(iequipo instanceof Equipo) )
			return null;
		Equipo equipoUpt = (Equipo) iequipo;
		for(Jugador jugI :equipoUpt.getJugadores()) {
			if( em.find(Jugador.class, jugI.getId()) == null )
				em.persist(jugI);
			else
				em.merge(jugI);
		}
//		System.out.println("   ***** equipoUpt = em.merge(" + equipoUpt + ") *****");
		em.merge(equipoUpt);
//		System.out.println("   ***** equipoUpt = " + equipoUpt + " *****");
		return equipoUpt;
	}

	@Override
	@Transactional
	public IEquipo insertarEquipo(IEquipo iequipo) {
		System.out.println("   ===== GestorDatosJPA.insertarEquipo(" + iequipo
				+ ") =====");
		if(  !(iequipo instanceof Equipo) ) {
			System.out.println("No es la instancia del tipo correcto de Equipo: " + iequipo);
			return null;
		}
		Equipo equipo = (Equipo) iequipo;
		em.persist(equipo);
		return equipo;
	}

	@Override
	@Transactional
	public IEquipo borrarEquipo(IEquipo ieq) {
		if( !(ieq instanceof Equipo) )
			return null;
		Equipo equipoDel = (Equipo) ieq;
		equipoDel.getJugadores()
		         .forEach(jug -> em.remove(jug));
		em.remove(equipoDel);
		return equipoDel;
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
	@Transactional(readOnly = true)
	public List<IArbitro> cargarArbitros() {
		String consulta = "SELECT a FROM Arbitro a";

		List<IArbitro> lstIarbitros = new ArrayList<>();		
		em.createQuery(consulta)
		  .getResultStream()
		  .forEach(ar->lstIarbitros.add((IArbitro)ar));
		return lstIarbitros;
	}

	@Override
	@Transactional(readOnly = true)
	public IArbitro leerArbitroXID(int id) {
		return em.find(Arbitro.class, id);
	}

	@Override
	@Transactional(readOnly = false)
	public IArbitro insertarArbitro(IArbitro iarbitro) {
		if( !(iarbitro instanceof Arbitro) ) {
			System.out.println(iarbitro.getClass().getName());
			return null;
		}
		Arbitro arbitro = (Arbitro) iarbitro;
		em.persist(arbitro);
		
		System.out.println("arbitro tras persist:" + arbitro);
		return arbitro;
	}

	@Override
	public IArbitro actualizarArbitro(IArbitro arbitro) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public IArbitro borrarArbitro(IArbitro iarb) {
		System.out.println("  ***** "+ this.getClass().getName() + ".borrarArbitro(" + iarb + ")");
		if( !(iarb instanceof Arbitro) )
			return null;
		Arbitro arbitroDel = (Arbitro) iarb;
		arbitroDel = em.find(Arbitro.class, arbitroDel.getId());
		em.remove(arbitroDel);
		return arbitroDel;
	}

	@Override
	@Transactional(readOnly = true)
	public List<IJugador> cargarJugadores() {
		String consulta = "SELECT j FROM Jugador j";

		List<IJugador> lstIjugadores = new ArrayList<>();		
		em.createQuery(consulta)
		  .getResultStream()
		  .forEach(jug->lstIjugadores.add( (IJugador)jug) );
		return lstIjugadores; 
	}

	@Override
	@Transactional(readOnly = true)
	public IJugador leerJugadorXID(String id) {
		return em.find(Jugador.class, id);
	}

	@Override
	public IJugador actualizarJugador(IJugador ijugador) {
		System.out.println("   ***** " + this.getClass().getName()
				+ ".actualizarJugador(" + ijugador + ")");
		if( !(ijugador instanceof Jugador) )
			return null;
		Jugador jugadorUpt = (Jugador) ijugador;
		em.merge(jugadorUpt);
		return jugadorUpt;
	}

	@Override
	@Transactional(readOnly = true)
	public List<IJugador> leerJugadoresXEquipo(String idEquipo) {
		String consulta = "SELECT j FROM Jugador j WHERE j.idEquipo = :idEquipo";

		List<IJugador> lstJugadores = new ArrayList<>();		
		em.createQuery(consulta)
		   .setParameter("idEquipo", idEquipo)
		  .getResultStream()
		  .forEach(jug->lstJugadores.add( (IJugador)jug) );
		return lstJugadores;
	}

	@Override
	@Transactional
	public IJugador insertarJugador(IJugador ijugador) {
		System.out.println("   ***** " + this.getClass().getName()
				+ ".insertarJugador(" + ijugador + ")");
		if( !(ijugador instanceof Jugador) )
			return null;
		Jugador jugador = (Jugador) ijugador;
		em.persist(jugador);
		return jugador;
	}

	@Override
	public IJugador borrarJugador(IJugador ijug) {
		if( !(ijug instanceof Jugador))
			return null;
		Jugador jug = (Jugador) ijug;
		if(em.contains(jug) == false)
			jug = em.find(Jugador.class, jug.getId());
		em.remove(jug);
		return jug;
	}

	@Override
	public Map<String, List<IJugador>> getJugadoresXposYequipo(String pos) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<IJugador> getJugadoresEnUnaUotraPosicion(String pos1, String pos2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<IJugador> getJugadoresMasJovenes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<IJugador> getJugadoresOrdenados() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<IJugador> getJugadoresXtitularidad(boolean esTitular) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<IJugador> getJugadoresPorPagina(int nPag) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<IJugador> getJugadoresTitularesPorPagina(int nPag) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getRegsXpagina() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setRegsXpagina(int regsXpagina) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<IJugador> getJugadoresTitulares() {
		// TODO Auto-generated method stub
		return null;
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
