package mx.com.qtx.web.jpa;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import mx.com.qtx.torneo.IEquipo;
import mx.com.qtx.torneo.IJugador;
import mx.com.qtx.torneo.IServicioTorneo;
import mx.com.qtx.torneo.NegocioException;
import mx.com.qtx.torneo.serviciosTorneo.JugadorYaExisteException;
import mx.com.qtx.torneo.serviciosTorneo.jpa.entidades.Jugador;
import mx.com.qtx.util.FechaUtil;

@Controller
public class TorneoController {
	private static int nInstancia=0;
	
	@Autowired
	private IServicioTorneo servicioTorneo;
	
	public TorneoController() {
		nInstancia++;
		System.out.println("***** TorneoController(): " + nInstancia + " *****");
	}
	
	@GetMapping("/menu")
	public String atenderGetMenu(){
		 return "guiMenuPrincipal";
	}
	 
	@GetMapping("/busquedaEquipo")
	public String enrutarABusquedaEquipoXid() {
		 return "guiBuscarEquipo";
	}
	 
	@GetMapping("/consuEquipo")
	public String enrutarAConsultaEquipoXid( @RequestParam(defaultValue = "INVALIDO") String id, Model mapModelo) {
		if(id.equals("INVALIDO") || id.trim().isEmpty()) {
//			mapModelo.addAttribute("error","El Id no debe estar vacío");
			mapModelo.addAttribute("error","idEquipo.vacio");
			return "guiBuscarEquipo";
		}
		IEquipo equipo = this.servicioTorneo.getEquipo(id);
		if(equipo == null) {
			mapModelo.addAttribute("error","equipo.noExiste");
			return "guiBuscarEquipo";			
		}
		mapModelo.addAttribute("equipo", equipo);
		mapModelo.addAttribute("hoy", new Date());
		return "guiConsuEquipo";
	}
	
	@GetMapping("/busquedaJugador")
	public String enrutarABusquedaJugadorXid() {
		 return "guiBuscarJugador";
	}
	
	@GetMapping("/consuJugador")
	public String enrutarAConsultaJugadorXid( @RequestParam(defaultValue = "INVALIDO") String id, Model mapModelo) {
		if(id.equals("INVALIDO") || id.trim().isEmpty()) {
			mapModelo.addAttribute("error","idJugador.vacio");
			return "guiBuscarJugador";
		}
		IJugador jugador = this.servicioTorneo.getJugador(id);
		if(jugador == null) {
			mapModelo.addAttribute("error","jugador.noExiste");
			return "guiBuscarJugador";			
		}
		mapModelo.addAttribute("jugador", jugador);
		return "guiConsuJugador";
	}
	 
	@GetMapping("/consultaEquiposTodos")
	public String enrutarAConsultaEquipos(Model mapModelo) {
		Map<String, IEquipo> mapEquipos = this.servicioTorneo.getEquipos();
		mapModelo.addAttribute("mapEquipos", mapEquipos);
		int totJug = mapEquipos.values().stream()
		                   .map(eq->eq.getNumJugadores() )
		                   .reduce( (acum,val)-> acum + val ).get();
		mapModelo.addAttribute("totJug",totJug);
		return "guiConsuEquiposTodos";
	}
	
	@GetMapping("/consu/{idEquipo}/jugadores")
	public String enrutarAConsultaJugXequipo(@PathVariable String idEquipo, Model mapModelo) {
		System.out.println("***** TorneoController.enrutarAConsultaJugXequipo(" + idEquipo + ", " + mapModelo + ") *****");
		IEquipo equipo = this.servicioTorneo.getEquipo(idEquipo);
		mapModelo.addAttribute("jugadoresEq",equipo.getListaJugadores());
		mapModelo.addAttribute("nombreEq",equipo.getNombreEquipo());
		return "guiConsuJugadoresEquipo";
	}
	
	@GetMapping("/insertJugador")
	public String enrutarInsertJugador(Model modelo) {
		IJugador jugador = this.getJugadorVacio();
		modelo.addAttribute("jugador", jugador);
		return "guiManttoJugador";
	}

	private IJugador getJugadorVacio() {
		Map<String,Object> datosJugador = new HashMap<>();
		datosJugador.put("id", "");
		datosJugador.put("titular", true);
		datosJugador.put("numero", 11);
		datosJugador.put("nombre", "");
		datosJugador.put("posicion", "");
		datosJugador.put("fecNac", FechaUtil.getFecha(1999, 1, 11));
		IJugador jugador = this.servicioTorneo.crearJugador(datosJugador);
		return jugador;
	}
	
	@ModelAttribute("mapEquipos") //Se invoca por petición y antes de atender la petición entrante. Publica en el Model
	public Map<String,String> publicarEquiposConClaves(){
		System.out.println("***** TorneoController.publicarEquiposConClaves() *****");
		Map<String,String> mapEquiposConClaves = new TreeMap<>();
		this.servicioTorneo.getEquipos()
		                   .forEach( (k,v)->mapEquiposConClaves.put(v.getNombreEquipo(),k) );
		return mapEquiposConClaves;
	}
	@PostMapping("/jugador/nuevo")
	public String insertarJugador(@Valid Jugador jugador, BindingResult resulBinding, Model modelo) {
		System.out.println("***** TorneoController.insertarJugador("   + jugador + ") *****");
		if(resulBinding.hasErrors()) {
			System.out.println("***** TorneoController.insertarJugador()->Datos inválidos:"
					+ resulBinding.getErrorCount() + " *****");
			modelo.addAttribute("error", "jugador.datosInvalidos");
			modelo.addAttribute("errores", resulBinding);
			modelo.addAttribute("jugador", jugador);
			return "guiManttoJugador";
		}
		try {
			this.servicioTorneo.agregarJugador(jugador);
		}
		catch(JugadorYaExisteException yaeex) {
			modelo.addAttribute("error", yaeex.getMessage());
			modelo.addAttribute("jugador", jugador);
			modelo.addAttribute("jugadorBD", yaeex.getJugador());
			return "guiManttoJugador";			
		}
		catch(NegocioException nex) {
			modelo.addAttribute("error", nex.getMessage());
			modelo.addAttribute("jugador", jugador);
			modelo.addAttribute("causa", nex.getCause());
			return "guiManttoJugador";			
		}		
		IJugador ijugador = this.getJugadorVacio();
		modelo.addAttribute("jugador", ijugador);
		return "guiManttoJugador";
	}
}
