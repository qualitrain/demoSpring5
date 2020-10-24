package mx.com.qtx.web.jpa;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import mx.com.qtx.torneo.IArbitro;
import mx.com.qtx.torneo.IEquipo;
import mx.com.qtx.torneo.IJugador;
import mx.com.qtx.torneo.IServicioTorneo;
import mx.com.qtx.torneo.serviciosTorneo.jpa.entidades.Jugador;

@RestController
public class TorneoRestController {
	
	@Autowired
	private IServicioTorneo servicioTorneo;
	
	@GetMapping(path = "/jugadores/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
	public Jugador getJugadorXid(@PathVariable String id) {
		System.out.println("***** TorneoRestController.getJugadorXid(" + id + ") *****");
		Jugador jugador = (Jugador) this.servicioTorneo.getJugador(id);
		return jugador;
	}
	@GetMapping(path = "/jugadores/{id}",produces = MediaType.APPLICATION_XML_VALUE) // No hay convertidor registrado java->Xml
	public Jugador getJugadorXidXml(@PathVariable String id) {
		System.out.println("***** TorneoRestController.getJugadorXidXml(" + id + ") *****");
		Jugador jugador = (Jugador) this.servicioTorneo.getJugador(id);
		return jugador;
	}
	
	@GetMapping(path = "/ijugadores/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
	public IJugador getIJugadorXid(@PathVariable String id) {
		System.out.println("***** TorneoRestController.igetJugadorXid(" + id + ") *****");
		return this.servicioTorneo.getJugador(id);
	}
	
	@GetMapping(path = "/equipos/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
	public IEquipo getIEquipoXid(@PathVariable String id) {
		System.out.println("***** TorneoRestController.getIEquipoXid(" + id + ") *****");
		return this.servicioTorneo.getEquipo(id);
	}
	
	@GetMapping(path = "/iarbitros/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
	public IArbitro getIArbitroXid(@PathVariable int id) {
		System.out.println("***** TorneoRestController.getIArbitroXid(" + id + ") *****");
		return this.servicioTorneo.getArbitro(id);
	}
	
	@GetMapping(path = "/arbitros/{id}/test",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<IArbitro> getIArbitroXidRE(@PathVariable int id) {
		System.out.println("***** TorneoRestController.getIArbitroXidRE(" + id + ") *****");
		IArbitro arbitro =  this.servicioTorneo.getArbitro(id);
		ResponseEntity<IArbitro> response = ResponseEntity.ok(arbitro);
		return  response;
	}
	
	@GetMapping(path = "/jugadores",produces = MediaType.APPLICATION_JSON_VALUE)
	public List<IJugador> getJugadores(){
		List<IJugador> listJugadores = new ArrayList<>();
		this.servicioTorneo.getJugadores()
		                   .values()
		                   .forEach(v->listJugadores.add(v));
		return listJugadores;
	}
	
	@PostMapping(path = "/jugadores", 
			     consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, 
			     produces = MediaType.APPLICATION_JSON_VALUE)
	public IJugador insertarJugador(Jugador jugador) {
		System.out.println("***** TorneoRestController.insertarJugador(" + jugador + ") *****");
		IJugador jugadorBD = this.servicioTorneo.agregarJugador(jugador);
		return jugadorBD;
	}
	@PostMapping(path = "/jugadores", 
		     consumes = MediaType.APPLICATION_JSON_VALUE, 
		     produces = MediaType.APPLICATION_JSON_VALUE)
    public IJugador insertarJugadorJson(@RequestBody Jugador jugador) {
	   System.out.println("***** TorneoRestController.insertarJugadorJson(" + jugador + ") *****");
	   IJugador jugadorBD = this.servicioTorneo.agregarJugador(jugador);
	   return jugadorBD;
    }
	@PostMapping(path = "/jugadores/test", 
		     consumes = MediaType.APPLICATION_JSON_VALUE, 
		     produces = MediaType.APPLICATION_JSON_VALUE)
   public IJugador insertarJugadorJson(HttpEntity<Jugador> heJugador) {
	   System.out.println("***** TorneoRestController.insertarJugadorJson(" + heJugador.getBody() + ") *****");
	   IJugador jugadorBD = this.servicioTorneo.agregarJugador(heJugador.getBody());
	   return jugadorBD;
   }
	
}
