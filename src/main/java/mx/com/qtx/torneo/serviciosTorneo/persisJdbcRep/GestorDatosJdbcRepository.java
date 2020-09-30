package mx.com.qtx.torneo.serviciosTorneo.persisJdbcRep;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import mx.com.qtx.torneo.IArbitro;
import mx.com.qtx.torneo.IEquipo;
import mx.com.qtx.torneo.IJugador;
import mx.com.qtx.torneo.serviciosTorneo.IGestorDatos;
import mx.com.qtx.torneo.serviciosTorneo.entidades.Arbitro;
import mx.com.qtx.torneo.serviciosTorneo.entidades.Equipo;
import mx.com.qtx.torneo.serviciosTorneo.entidades.Jugador;

@Primary
@Repository
public class GestorDatosJdbcRepository implements IGestorDatos {
	
	@Autowired
	private ICrudRepositoryEquipo repEquipo;
	
	@Autowired
	private ICrudRepositoryJugador repJugador;

	@Autowired
	private ICrudRepositoryArbitro repArbitro;

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
			equipo.get().isNew(); // Marcar como no-insertable solo modificable
			return equipo.get();
		}
		return null;
	}

	@Override
	public IEquipo leerEquipoXIDConJugadores(String id) {
		return this.leerEquipoXID(id);
	}

	@Override
	public IEquipo actualizarEquipo(IEquipo iequipo) { //Actualización lineal
		Equipo equipoUpt = null;
		if(!(iequipo instanceof Equipo)) 
			return null;
		equipoUpt = (Equipo) iequipo;
		equipoUpt.isNew(); // asegura que se haga un update en vez de un insert accidental
		
		Equipo equipoAct = (Equipo) this.leerEquipoXIDConJugadores(equipoUpt.getId());
		Set<Jugador> jugadoresAct = equipoAct.getJugadores(); 
		Set<Jugador> jugadoresUpt = equipoUpt.getJugadores();
		equipoUpt.setJugadores(jugadoresAct); // Agregamos jugadores originales para que no los borre !
		
		Equipo nvoEquipo = this.repEquipo.save(equipoUpt); // Borrará e insertará los jugadores originales
		
		nvoEquipo.setJugadores(jugadoresUpt); // Regresa los jugadores que venían en el objeto original
		return nvoEquipo;
	}
	@Override
	public IEquipo actualizarEquipoAgregado(IEquipo iequipo) {
		Equipo equipoUpt = null;
		if(!(iequipo instanceof Equipo)) 
			return null;
		equipoUpt = (Equipo) iequipo;
		equipoUpt.isNew(); // asegura que se haga un update en vez de un insert accidental
		
		Equipo nvoEquipo = this.repEquipo.save(equipoUpt); // Borrará e insertará los jugadores originales
		return nvoEquipo;
	}

	@Override
	public IEquipo insertarEquipo(IEquipo iequipo) { // Inserción lineal
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
	public IEquipo insertarEquipoAgregado(IEquipo iequipo) { // Inserta equipo y jugadores !!
		Equipo equipoIns = null;
		if(iequipo instanceof Equipo) {
			equipoIns = (Equipo) iequipo;
			Equipo nvoEquipo = this.repEquipo.save(equipoIns);
			return nvoEquipo;
		}
		return null;
	}

	@Override
	public IEquipo borrarEquipo(IEquipo ieq) {
		if(!(ieq instanceof Equipo))
			return null;
		Equipo eq = (Equipo) ieq;
		this.repEquipo.delete(eq);
		return eq;
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
	public IArbitro actualizarArbitro(IArbitro iarbitro) {
		return this.insertarArbitro(iarbitro); //Al tener llaves auto generadas si el objeto tiene id nula será insert y caso contrario será update
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
		jugUpt.isNew(); //Asegura update en vez de insert
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
		if(!(ijug instanceof IJugador))
			return null;
		Jugador jug = (Jugador) ijug;
		this.repJugador.delete(jug);
		return jug;
	}

}
