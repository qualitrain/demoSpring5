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
	}

	@Override
	public List<String> getJugadores(String idEquipo) {
		System.out.println("\n===== getJugadores(" + idEquipo
				+ ") =====");
		List<String> listJugadores = new ArrayList<>();
		IEquipo equipo = this.servicioTorneo.getEquipo(idEquipo);
		if(equipo == null)
			return listJugadores;
		
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
	public void testActualizaciones(){
		Map<String,Object> datosEquipo = new HashMap<>();
		datosEquipo.put("id", "Tlalpan");
		datosEquipo.put("nombre", "Club Tlalpan F.C.");
		datosEquipo.put("apodo", "Coyotes locos");
		IEquipo equipo =  this.servicioTorneo.crearEquipo(datosEquipo);
				
		IEquipo equipoIns = this.servicioTorneo.agregarEquipo(equipo);
		System.out.println(equipoIns);
		
		System.out.println("\n... Modificando");
		equipoIns.setNombreEquipo("Tlalpan Football Club");
		
		Map<String,Object> datosJugador = new HashMap<>();
		datosJugador.put("id", "202000001");
		datosJugador.put("nombre", "Jorge Zavala Ceballos");
		datosJugador.put("posicion", "Delantero");
		datosJugador.put("numero", 18);
		datosJugador.put("fecNac", FechaUtil.getFecha(1999, 1, 19));
		IJugador jugador = this.servicioTorneo.crearJugador(datosJugador);
		
		int nAgregados = this.servicioTorneo.agregarJugador(equipoIns,jugador);
		
		this.servicioTorneo.actualizarEquipo(equipoIns);
		IEquipo equipoAct = this.servicioTorneo.getEquipo("Tlalpan");
		System.out.println(equipoAct);
	}
	

}
