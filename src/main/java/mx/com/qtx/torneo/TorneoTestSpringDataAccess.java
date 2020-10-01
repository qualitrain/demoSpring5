package mx.com.qtx.torneo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import mx.com.qtx.ITorneo;
import mx.com.qtx.util.FechaUtil;

@Primary
@Component
public class TorneoTestSpringDataAccess implements ITorneo {
	
	@Autowired
	private IServicioTorneo servicioTorneo;

	public TorneoTestSpringDataAccess() {
		super();
		System.out.println("***** TorneoTestSpringDataAccess Instanciado *****");
	}

	@Override
	public void mostrarBienvenida() {
		// TODO Auto-generated method stub

	}

	@Override
	public void mostrarArbitros() {
		System.out.println("\n===== Árbitros =====");
		Map<String,IArbitro> mapArbitros = this.servicioTorneo.getArbitros();
		for(String id: mapArbitros.keySet()) {
			System.out.printf("%5s %40s\n", id, mapArbitros.get(id));
		}
		System.out.println("Probando servicio getArbitro(id)");
		for(IArbitro arbI: mapArbitros.values()) {
			int id = arbI.getId();
			System.out.printf("%5s %40s\n", id, this.servicioTorneo.getArbitro(id));
		}
	}

	@Override
	public void mostrarDatosGenerales() {
		System.out.println("\n===== Datos Generales =====");
		Map<String, IEquipo> mapEquipos = this.servicioTorneo.getEquipos();
		System.out.println("Num. Equipos:" + mapEquipos.size());
		System.out.println("\nEquipos y jugadores titulares\n");
		for(IEquipo equipoI:mapEquipos.values()) {
			System.out.println("Equipo:" + equipoI.getNombreEquipo());
			Map<Integer, String> mapJugadores = equipoI.getJugadoresTitulares();
			mapJugadores.forEach((k,v)-> System.out.println(v +  " (" + k + ")"));
			System.out.println();
		}
		System.out.print("\nJugadores (");
		Map<String, IJugador> mapJugadores = this.servicioTorneo.getJugadores();
		System.out.println( mapJugadores.size() + ")");
		mapJugadores.values().forEach(j ->System.out.println(j));
	}

	@Override
	public List<String> getJugadores(String idEquipo) {
		System.out.println("\n===== getJugadores(" + idEquipo
				+ ") =====");
		List<String> listJugadores = new ArrayList<>();
		IEquipo equipo = this.servicioTorneo.getEquipo(idEquipo);
		if(equipo == null) {
			System.out.println("El equipo con id " + idEquipo + " NO EXISTE");
			return listJugadores;
		}
		
		equipo.getListaJugadores()
		      .forEach(jug->listJugadores.add(jug.getNombre()));
		System.out.println(listJugadores);
		return listJugadores;
	}

	@Override
	public void agregarEquipo(IEquipo equipo) {
		System.out.println("\n===== Agregando Equipo =====");
		IEquipo equipoIns = this.servicioTorneo.agregarEquipo(equipo);
		if(equipoIns == null)
			System.out.println("El equipo con id " + equipo.getID() + " ya existe en BD");
		else
			System.out.println("Equipo agregado:" + equipoIns);
	}

	@Override
	public void generarPartidas() {
		// TODO Auto-generated method stub

	}

	@Override
	public void mostrarPartidas() {
		// TODO Auto-generated method stub

	}

	@Override
	public void mostrarJugadores() {
		System.out.println("\n===== mostrarJugadores =====");
		Map<String, IEquipo> mapEquipos = this.servicioTorneo.getEquipos();
		System.out.println("\nEquipos y jugadores\n");
		for(IEquipo equipoI:mapEquipos.values()) {
			System.out.println("Equipo:" + equipoI.getNombreEquipo());
			if(equipoI.getNumJugadores() == 0)
				continue;
			List<IJugador> listJugadores = equipoI.getListaJugadores();
			System.out.println(equipoI.getClass().getName());
			listJugadores.forEach((j)-> 
			                     System.out.println(j.getNombre() +  " (" 
			                        + j.getNumero() + "):" + j.getPosicion()));
			System.out.println();
		}

	}
	@Override
	public void testInserciones() {
		System.out.println("\n===== test Inserciones =====");
		Map<String,Object> datosEquipo = new HashMap<>();
		datosEquipo.put("id", "Oax2000");
		datosEquipo.put("nombre", "Real Oaxaca 2000 F.C.");
		datosEquipo.put("apodo", "ornitorrincos");
		IEquipo equipo =  this.servicioTorneo.crearEquipo(datosEquipo);
				
		Map<String,Object> datosJugador = new HashMap<>();
		datosJugador.put("id", "662000001");
		datosJugador.put("nombre", "Mario Velarde Mora");
		datosJugador.put("posicion", "Medio");
		datosJugador.put("numero", 6);
		datosJugador.put("fecNac", FechaUtil.getFecha(1999, 3, 13));
		IJugador jugador = this.servicioTorneo.crearJugador(datosJugador);
		equipo.agregarJugador(jugador);
		jugador.setEquipo(equipo);
		
		datosJugador = new HashMap<>();
		datosJugador.put("id", "662000077");
		datosJugador.put("nombre", "Jorge Valdano Pérez");
		datosJugador.put("posicion", "Volante");
		datosJugador.put("numero", 7);
		datosJugador.put("fecNac", FechaUtil.getFecha(2000, 4, 1));
		jugador = this.servicioTorneo.crearJugador(datosJugador);
		equipo.agregarJugador(jugador);
		jugador.setEquipo(equipo);
		
		datosJugador = new HashMap<>();
		datosJugador.put("id", "662000005");
		datosJugador.put("nombre", "Ramón Ramírez Galarza");
		datosJugador.put("posicion", "Lateral izquierdo");
		datosJugador.put("numero", 5);
		datosJugador.put("fecNac", FechaUtil.getFecha(2000, 10, 10));
		jugador = this.servicioTorneo.crearJugador(datosJugador);
		equipo.agregarJugador(jugador);
		jugador.setEquipo(equipo);
		
		try {
			IEquipo equipoIns = this.servicioTorneo.agregarEquipo(equipo);
			System.out.println("Se insertó el quipo " + equipoIns);
		}
		catch (NegocioException nex) {
			System.out.println("Excepción en la inserción de " + equipo
					+ ":\n " + nex.getMessage());
		}
		
		Map<String,Object> datosArbitro = new HashMap<>();
		datosArbitro = new HashMap<>();
		datosArbitro.put("nombre", "Mauricio Jara Limón");
		datosArbitro.put("fecNac", FechaUtil.getFecha(1992, 5, 5));
		IArbitro arbitro = this.servicioTorneo.crearArbitro(datosArbitro);
		IArbitro arbitroBD = this.servicioTorneo.agregarArbitro(arbitro);
		System.out.println("Se ha insertado al arbitro " + arbitroBD);
		
	}
	@Override
	public void testActualizaciones(){
		System.out.println("\n===== test Actualizaciones =====");
		try {
			Map<String,Object> datosEquipo = new HashMap<>();
			datosEquipo.put("id", "Tlalpan");
			datosEquipo.put("nombre", "Club Tlalpan F.C.");
			datosEquipo.put("apodo", "Coyotes locos");
			IEquipo equipo =  this.servicioTorneo.crearEquipo(datosEquipo);
			IEquipo equipoUpt = null;
			if(this.servicioTorneo.yaExisteEquipo(equipo) == false) {
				equipoUpt = this.servicioTorneo.agregarEquipo(equipo);
				System.out.println(equipoUpt);
			}
			else
				equipoUpt = equipo;
			
			System.out.println("\n... Modificando");
			equipoUpt.setNombreEquipo("Tlalpan Soccer FC");
			
			Map<String,Object> datosJugador = new HashMap<>();
			datosJugador.put("id", "252000007");
			datosJugador.put("nombre", "Juan Valle Rizo");
			datosJugador.put("posicion", "Medio");
			datosJugador.put("numero", 6);
			datosJugador.put("fecNac", FechaUtil.getFecha(1999, 2, 8));
			IJugador jugador = this.servicioTorneo.crearJugador(datosJugador);
			
			equipoUpt.agregarJugador(jugador);
			datosJugador = new HashMap<>();
			datosJugador.put("id", "252000008");
			datosJugador.put("nombre", "Armando Aranda Zuno");
			datosJugador.put("posicion", "Defensa");
			datosJugador.put("numero", 2);
			datosJugador.put("fecNac", FechaUtil.getFecha(1999, 2, 19));
			jugador = this.servicioTorneo.crearJugador(datosJugador);
			
			equipoUpt.agregarJugador(jugador);
			
			IEquipo equipoAct = this.servicioTorneo.actualizarEquipoAgregado(equipoUpt);
//			IEquipo equipoAct = this.servicioTorneo.actualizarEquipo(equipoUpt);
			System.out.println("Equipo actualizado:" + equipoAct);
		}
		catch(Exception ex) {
			System.out.println("Exception:" + ex.getClass().getName());
			System.out.println("Mensaje:" + ex.getMessage());
			if(ex.getCause()!=null) {
				System.out.println("Causa:" + ex.getClass().getName());
			}
		}
	}
	
	@Override
	public void testEliminaciones(){
		System.out.println("\n===== test Eliminaciones =====");
		try {
			Map<String,Object> datosEquipo = new HashMap<>();
			datosEquipo.put("id", "Tlalpan");
			IEquipo equipo =  this.servicioTorneo.crearEquipo(datosEquipo);
			if(this.servicioTorneo.yaExisteEquipo(equipo)) {
//				IEquipo equipoBorrado = this.servicioTorneo.eliminarEquipo(equipo);
				IEquipo equipoBorrado = this.servicioTorneo.eliminarEquipoAgregado(equipo);
				System.out.println("equipo eliminado: " + equipoBorrado);
			}
			Map<String,Object> datosArb = new HashMap<>();
			datosArb.put("id", 20);
			this.servicioTorneo.eliminarArbitro(this.servicioTorneo.crearArbitro(datosArb));
		}
		catch(Exception ex) {
			System.out.println("Exception:" + ex.getClass().getName());
			System.out.println("Mensaje:" + ex.getMessage());
			if(ex.getCause()!=null) {
				System.out.println("Causa:" + ex.getClass().getName());
			}
		}
	}
	@Override
	public void testConsultas() {
		System.out.println("\n===== test Consultas =====");
		String posicion = "Defensa";
		
		System.out.println("Jugadores por equipo que ocupan la posición de " + posicion);
		this.servicioTorneo.getJugadoresXeqEnPosicion(posicion)
		                   .forEach((k,v)->System.out.println("Eq:" + k + ", jugadores:" + v));
		
		String posicion2 = "Delantero";
		System.out.println("\nJugadores que juegan en posiciones de " + posicion + " o de " + posicion2);
		this.servicioTorneo.getJugadoresEnPosiciones(posicion, posicion2)
		                   .forEach(j -> System.out.println(j.getNombre() + "(" + j.getPosicion() + ")"));
		
		System.out.println("\nJugadores más jóvenes" );
		this.servicioTorneo.getJugadoresMasJovenes()
		                   .forEach(j -> System.out.println(j.getNombre() + "(" + j.getFecNac() + ")"));
		
		System.out.println("\nJugadores ordenados por fecha de nacimiento y nombre" );
		this.servicioTorneo.getJugadoresOrdenados()
		                   .forEach(j -> System.out.printf("%-35s %10s\n",j.getNombre(), j.getFecNac()));
		
		System.out.println("\nJugadores titulares ordenados por posición y nombre" );
		this.servicioTorneo.getJugadoresTitulares()
		                   .forEach(j -> System.out.printf("%-16s %-35s\n",j.getPosicion(), j.getNombre() ));
		
		int nPag=0;
		for(; ;nPag++) {
			List<IJugador> pagI = this.servicioTorneo.getPaginaJugadores(nPag);
			if (pagI.isEmpty())
				break;
			System.out.println("\nJugadores página " + nPag + " (c/pag es de 3 renglones)" );
			pagI.forEach(j -> System.out.printf("%10s %-16s %-35s\n", j.getId(), j.getPosicion(), j.getNombre() ));
		}
		
		nPag=0;
		for(; ;nPag++) {
			List<IJugador> pagI = this.servicioTorneo.getPaginaJugadoresTitulares(nPag);
			if (pagI.isEmpty())
				break;
			System.out.println("\nJugadores titular página " + nPag + " (c/slice es de 3 renglones)" );
			pagI.forEach(j -> System.out.printf("%10s %-16s %-35s\n", j.getId(), j.getPosicion(), j.getNombre() ));
		}
		nPag=0;
		int tamPag = 6;
		for(; ;nPag++) {
			List<IJugador> pagI = this.servicioTorneo.getPaginaJugadores(tamPag, nPag);
			if (pagI.isEmpty())
				break;
			System.out.println("\nJugadores página " + nPag + " (c/pag es de "
					+ tamPag + " renglones)" );
			pagI.forEach(j -> System.out.printf("%10s %-16s %-35s\n", j.getId(), j.getPosicion(), j.getNombre() ));
		}
		
		nPag=0;
		tamPag = 4;
		for(; ;nPag++) {
			List<IJugador> pagI = this.servicioTorneo.getPaginaJugadoresTitulares(tamPag, nPag);
			if (pagI.isEmpty())
				break;
			System.out.println("\nJugadores titulares página " + nPag + " (c/página es de "
					+ tamPag + " renglones)" );
			pagI.forEach(j -> System.out.printf("%10s %-16s %-35s\n", j.getId(), j.getPosicion(), j.getNombre() ));
		}

	}

}
