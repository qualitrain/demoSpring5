package mx.com.qtx.torneo.serviciosTorneo.persisMem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import mx.com.qtx.torneo.IArbitro;
import mx.com.qtx.torneo.IEquipo;
import mx.com.qtx.torneo.IJugador;
import mx.com.qtx.torneo.serviciosTorneo.IGestorDatos;
import mx.com.qtx.torneo.serviciosTorneo.entidades.Arbitro;
import mx.com.qtx.torneo.serviciosTorneo.entidades.Equipo;
import mx.com.qtx.torneo.serviciosTorneo.entidades.Jugador;
import mx.com.qtx.util.FechaUtil;

@Repository
public class GestorDatosMemoria implements IGestorDatos {
	private Map<Integer,IArbitro> bdArbitros;
	private Map<String,IEquipo> bdEquipos;
	private Map<String,IJugador> bdJugadores;
	
	public GestorDatosMemoria() {
		this.bdArbitros = this.cargarDatosMemArbitros();
		this.bdEquipos = this.cargarDatosMemEquipos();
	}

	private Map<String, IEquipo> cargarDatosMemEquipos() {
		HashMap<String, IEquipo> mapEquipos = new HashMap<>();
		HashMap<String, IJugador> mapJugadores = new HashMap<>();
		
		Equipo equipoI = new Equipo("Silao FC","coyotes");
		equipoI.setId("Silao");
		mapEquipos.put(equipoI.getId(), equipoI);
		
		Jugador jugadorI = new Jugador("990392345","Lorena Martínez Mendalde",1,"Portero",FechaUtil.getFecha(2000, 1, 5));
		jugadorI.setTitular(true);
		equipoI.agregarJugador(jugadorI);	
		mapJugadores.put(jugadorI.getId(), jugadorI);
		
		jugadorI = new Jugador("997792115","Francisca López García",2,"Defensa",FechaUtil.getFecha(2000, 2, 15));
		jugadorI.setTitular(true);
		equipoI.agregarJugador(jugadorI);	
		mapJugadores.put(jugadorI.getId(), jugadorI);
		
		jugadorI = new Jugador("990000091","Carmen Sosa Del Real",4,"Defensa",FechaUtil.getFecha(2000, 3, 18));
		jugadorI.setTitular(true);
		equipoI.agregarJugador(jugadorI);	
		mapJugadores.put(jugadorI.getId(), jugadorI);
		
		jugadorI = new Jugador("997792666","Isabel Ramos Mena",6,"Medio",FechaUtil.getFecha(2000, 4, 17));
		jugadorI.setTitular(true);
		equipoI.agregarJugador(jugadorI);	
		mapJugadores.put(jugadorI.getId(), jugadorI);
		
		jugadorI = new Jugador("996792321","Marina Romero Loza",7,"Medio",FechaUtil.getFecha(2001, 12, 1));
		jugadorI.setTitular(true);
		equipoI.agregarJugador(jugadorI);	
		mapJugadores.put(jugadorI.getId(), jugadorI);
		
		jugadorI = new Jugador("991182002","Brenda Herrera Valdés",9,"Delantero",FechaUtil.getFecha(2000, 6, 6));
		jugadorI.setTitular(true);
		equipoI.agregarJugador(jugadorI);	
		mapJugadores.put(jugadorI.getId(), jugadorI);
		
		jugadorI = new Jugador("991234115","Dominica Herrera Valdés",17,"Delantero",FechaUtil.getFecha(2001, 7, 7));
		jugadorI.setTitular(false);
		equipoI.agregarJugador(jugadorI);	
		mapJugadores.put(jugadorI.getId(), jugadorI);
		
		equipoI = new Equipo("Revolucion 2000","guerreras");
		equipoI.setId("Revln");
		mapEquipos.put(equipoI.getId(), equipoI);
		
		jugadorI = new Jugador("880392345","Jimena Mota Yaber",1,"Portero",FechaUtil.getFecha(2000, 6, 5));
		jugadorI.setTitular(true);
		equipoI.agregarJugador(jugadorI);	
		mapJugadores.put(jugadorI.getId(), jugadorI);
		
		jugadorI = new Jugador("887792115","Lucrecia Jara Villa",2,"Defensa",FechaUtil.getFecha(2000, 7, 15));
		jugadorI.setTitular(true);
		equipoI.agregarJugador(jugadorI);	
		mapJugadores.put(jugadorI.getId(), jugadorI);
		
		jugadorI = new Jugador("880000091","Rosa Isela Lima Díaz",3,"Defensa",FechaUtil.getFecha(2000, 8, 18));
		jugadorI.setTitular(true);
		equipoI.agregarJugador(jugadorI);	
		mapJugadores.put(jugadorI.getId(), jugadorI);
		
		jugadorI = new Jugador("887792666","Karina Septién Luna",5,"Medio",FechaUtil.getFecha(2000, 9, 17));
		jugadorI.setTitular(true);
		equipoI.agregarJugador(jugadorI);	
		mapJugadores.put(jugadorI.getId(), jugadorI);
		
		jugadorI = new Jugador("886792321","Alejandra Ibarra Caso",8,"Medio",FechaUtil.getFecha(2001, 10, 1));
		jugadorI.setTitular(true);
		equipoI.agregarJugador(jugadorI);	
		mapJugadores.put(jugadorI.getId(), jugadorI);
		
		jugadorI = new Jugador("881182002","Berenice Moyao Mina",11,"Delantero",FechaUtil.getFecha(2000, 2, 6));
		jugadorI.setTitular(true);
		equipoI.agregarJugador(jugadorI);	
		mapJugadores.put(jugadorI.getId(), jugadorI);
		
		jugadorI = new Jugador("881234115","Ludovica Ripstein Vera",15,"Delantero",FechaUtil.getFecha(2001, 9, 7));
		jugadorI.setTitular(false);
		equipoI.agregarJugador(jugadorI);	
		mapJugadores.put(jugadorI.getId(), jugadorI);
		
		this.bdJugadores = mapJugadores;
		return mapEquipos;
	}

	private Map<Integer, IArbitro> cargarDatosMemArbitros() {
		HashMap<Integer, IArbitro> mapArbitros = new HashMap<>();
		Arbitro arbitroI = new Arbitro(1,"Jorge Santos Mendiezola",FechaUtil.getFecha(2000, 1, 11));
		mapArbitros.put(arbitroI.getId(), arbitroI);
		arbitroI = new Arbitro(2,"Martín De la Paz Moreira",FechaUtil.getFecha(1997, 11, 5));
		mapArbitros.put(arbitroI.getId(), arbitroI);
		arbitroI = new Arbitro(3,"Esteban Suazo Madeiro",FechaUtil.getFecha(1990, 5, 25));
		mapArbitros.put(arbitroI.getId(), arbitroI);
		arbitroI = new Arbitro(4,"Hernán Morales Romero",FechaUtil.getFecha(1999, 3, 12));
		mapArbitros.put(arbitroI.getId(), arbitroI);
		arbitroI = new Arbitro(5,"Juan Carlos Torres Reyes",FechaUtil.getFecha(1998, 6, 7));
		mapArbitros.put(arbitroI.getId(), arbitroI);
		return mapArbitros;
	}

	@Override
	public List<IEquipo> cargarEquipos() {
		return new ArrayList<IEquipo>(this.bdEquipos.values());
	}

	@Override
	public IEquipo leerEquipoXID(String id) {
		return this.bdEquipos.get(id);
	}

	@Override
	public IEquipo actualizarEquipo(IEquipo equipo) {
		return this.bdEquipos.put(equipo.getID(), equipo);
	}
	
	public IEquipo insertarEquipo(IEquipo equipo) {
		if(this.bdEquipos.containsKey(equipo.getID()))
			return null;
		this.bdEquipos.put(equipo.getID(), equipo);
		return equipo;
	}

	@Override
	public List<IArbitro> cargarArbitros() {
		return new ArrayList<IArbitro>(this.bdArbitros.values());
	}

	@Override
	public IArbitro leerArbitroXID(int id) {
		return this.bdArbitros.get(id);
	}

	@Override
	public IArbitro actualizarArbitro(IArbitro arbitro) {
		return this.bdArbitros.put(arbitro.getId(), arbitro);
	}

	@Override
	public List<IJugador> cargarJugadores() {
		return new ArrayList<IJugador>(this.bdJugadores.values());
	}

	@Override
	public IJugador leerJugadorXID(String id) {
		return this.bdJugadores.get(id);
	}

	@Override
	public IJugador actualizarJugador(IJugador jugador) {
		return this.bdJugadores.put(jugador.getId(), jugador);
	}

	@Override
	public List<IJugador> leerJugadoresXEquipo(String idEquipo) {
		IEquipo equipo = this.bdEquipos.get(idEquipo);
		if(equipo == null)
			return null;
		return equipo.getListaJugadores();
	}

	@Override
	public IArbitro insertarArbitro(IArbitro iarbitro) {
		iarbitro.setId( this.bdArbitros.size() );
		this.bdArbitros.put(iarbitro.getId(), iarbitro);
		return null;
	}

	@Override
	public IJugador insertarJugador(IJugador ijugador) {
		if(this.bdJugadores.containsKey(ijugador.getId()))
			return null;
		this.bdJugadores.put(ijugador.getId(), ijugador);
		return ijugador;
	}

}
