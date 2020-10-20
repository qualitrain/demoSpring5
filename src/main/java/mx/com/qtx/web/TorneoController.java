package mx.com.qtx.web;

import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import mx.com.qtx.torneo.IEquipo;
import mx.com.qtx.torneo.IJugador;
import mx.com.qtx.torneo.IServicioTorneo;

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
	 
}
