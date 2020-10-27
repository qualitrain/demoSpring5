package mx.com.qtx.web.jpa;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.LocaleResolver;

import mx.com.qtx.torneo.IArbitro;
import mx.com.qtx.torneo.IEquipo;
import mx.com.qtx.torneo.IJugador;
import mx.com.qtx.torneo.IServicioTorneo;
import mx.com.qtx.torneo.NegocioException;
import mx.com.qtx.torneo.serviciosTorneo.JugadorYaExisteException;
import mx.com.qtx.torneo.serviciosTorneo.jpa.entidades.Jugador;

@RestController
public class TorneoRestController {
	
	@Autowired
	private IServicioTorneo servicioTorneo;
	
	@Autowired
	private MessageSource messageSource;
	
	@Autowired
	private LocaleResolver localeResolver;
	
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
	
	@PostMapping(path = "/jugadores/valid",
			consumes = MediaType.APPLICATION_JSON_VALUE, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public IJugador insertarJugadorCValid(@Valid @RequestBody Jugador jugador, BindingResult resulBinding) {
		System.out.println("***** TorneoRestController.insertarJugadorCValid("   + jugador + ") *****");
		if(resulBinding.hasErrors()) {
			System.out.println("***** TorneoController.insertarJugadorCValid()->Datos inválidos:"
					+ resulBinding.getErrorCount() + " *****");
			JugadorBindingException jbex =new  JugadorBindingException(resulBinding, jugador,"errores de conversión/validación");
			throw jbex;
		}
		return this.servicioTorneo.agregarJugador(jugador);
	}
	
	@ExceptionHandler
	public ResponseEntity<ErrorCte> manejarExcepcionBinding(JugadorBindingException jbex, HttpServletRequest req){
		Locale localidad = this.localeResolver.resolveLocale(req);
		ErrorCte errorCte = ErrorCte.crearErrorDeBinding(jbex.getResultValidacion(), this.messageSource, localidad);
		return new ResponseEntity<ErrorCte>(errorCte, HttpStatus.NOT_ACCEPTABLE);		
	}
	@ExceptionHandler
	public ResponseEntity<ErrorCte> manejarExcepcionParseo(HttpMessageNotReadableException hmnrex, HttpServletRequest req){
		Locale localidad = this.localeResolver.resolveLocale(req);
		ErrorCte errorCte = ErrorCte.crearErrorDeBinding(hmnrex, this.messageSource, localidad);
		return new ResponseEntity<ErrorCte>(errorCte, HttpStatus.BAD_REQUEST);		
	}
	@ExceptionHandler
	public ResponseEntity<ErrorCte> manejarExcepcionNegocio(NegocioException nex, HttpServletRequest req){
		Locale localidad = this.localeResolver.resolveLocale(req);
		ErrorCte errorCte = ErrorCte.crearErrorDeNegocio(nex, this.messageSource, localidad);
		return new ResponseEntity<ErrorCte>(errorCte, HttpStatus.PRECONDITION_REQUIRED);		
	}
		
}
