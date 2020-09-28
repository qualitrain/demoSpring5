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
		datosEquipo.put("id", "Morelos");
		datosEquipo.put("nombre", "Club José María Morelos y Pavón");
		datosEquipo.put("apodo", "juglares");
		IEquipo equipo =  this.servicioTorneo.crearEquipo(datosEquipo);
				
		Map<String,Object> datosJugador = new HashMap<>();
		datosJugador.put("id", "202000001");
		datosJugador.put("nombre", "Jorge Zavala Ceballos");
		datosJugador.put("posicion", "Delantero");
		datosJugador.put("numero", 18);
		datosJugador.put("fecNac", FechaUtil.getFecha(1999, 1, 19));
		IJugador jugador = this.servicioTorneo.crearJugador(datosJugador);
		equipo.agregarJugador(jugador);
		jugador.setEquipo(equipo);
		
		datosJugador = new HashMap<>();
		datosJugador.put("id", "202000003");
		datosJugador.put("nombre", "Martín Zavala Ceballos");
		datosJugador.put("posicion", "Medio");
		datosJugador.put("numero", 5);
		datosJugador.put("fecNac", FechaUtil.getFecha(2000, 1, 6));
		jugador = this.servicioTorneo.crearJugador(datosJugador);
		equipo.agregarJugador(jugador);
		jugador.setEquipo(equipo);
		
		datosJugador = new HashMap<>();
		datosJugador.put("id", "202000005");
		datosJugador.put("nombre", "Leopoldo García Cantú");
		datosJugador.put("posicion", "Defensa");
		datosJugador.put("numero", 3);
		datosJugador.put("fecNac", FechaUtil.getFecha(2001, 11, 16));
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
		datosArbitro.put("nombre", "Jeremías Jara Jiménez");
		datosArbitro.put("fecNac", FechaUtil.getFecha(1990, 4, 29));
		IArbitro arbitro = this.servicioTorneo.crearArbitro(datosArbitro);
		IArbitro arbitroBD = this.servicioTorneo.agregarArbitro(arbitro);
		System.out.println("Se ha insertado al arbitro " + arbitroBD);
		
	}
	public void testActualizaciones(){
		System.out.println("\n===== test Actualizaciones =====");
		try {
			Map<String,Object> datosEquipo = new HashMap<>();
			datosEquipo.put("id", "Tlalpan");
			datosEquipo.put("nombre", "Club Tlalpan F.C.");
			datosEquipo.put("apodo", "Coyotes locos");
			IEquipo equipo =  this.servicioTorneo.crearEquipo(datosEquipo);
			IEquipo equipoIns = null;
			if(this.servicioTorneo.yaExisteEquipo(equipo) == false) {
				equipoIns = this.servicioTorneo.agregarEquipo(equipo);
				System.out.println(equipoIns);
			}
			else
				equipoIns = equipo;
			
			System.out.println("\n... Modificando");
			equipoIns.setNombreEquipo("Tlalpan Football Club");
			
			Map<String,Object> datosJugador = new HashMap<>();
			datosJugador.put("id", "202000001");
			datosJugador.put("nombre", "Jorge Zavala Ceballos");
			datosJugador.put("posicion", "Delantero");
			datosJugador.put("numero", 18);
			datosJugador.put("fecNac", FechaUtil.getFecha(1999, 1, 19));
			IJugador jugador = this.servicioTorneo.crearJugador(datosJugador);
			
			equipoIns.agregarJugador(jugador);
			
			this.servicioTorneo.actualizarEquipo(equipoIns);
			IEquipo equipoAct = this.servicioTorneo.getEquipo("Tlalpan");
			System.out.println(equipoAct);
		}
		catch(Exception ex) {
			System.out.println("Exception:" + ex.getClass().getName());
			System.out.println("Mensaje:" + ex.getMessage());
			if(ex.getCause()!=null) {
				System.out.println("Causa:" + ex.getClass().getName());
			}
		}
	}
	

}
