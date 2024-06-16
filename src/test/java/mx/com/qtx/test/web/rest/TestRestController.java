package mx.com.qtx.test.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;

@SpringBootTest
public class TestRestController {
	@Test
	public void testGetJugadorPorObjeto() {
		System.out.println("\n===== TestRestController.testGetPorObjeto() =====");
		RestTemplate restTemplate = new RestTemplate();
		JugadorDto jugador = restTemplate.getForObject("http://localhost:8080/jugadores/{id}", JugadorDto.class, "3456-789");
		System.out.println("Jugador recuperado:" + jugador);
	}
	
	@Test
	public void testGetJugadorPorEntidad() {
		System.out.println("\n===== TestRestController.testGetPorEntidad() =====");
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<JugadorDto> eJugador	= restTemplate.getForEntity("http://localhost:8080/jugadores/{id}", JugadorDto.class, "202000012");
		System.out.println("Jugador recuperado:" + eJugador.getBody());
		System.out.println("status:" + eJugador.getStatusCodeValue());
		System.out.println("Headers:" );		
		eJugador.getHeaders().forEach( (x,v)->System.out.println(x + ":" + v ) );
	}
	
	@Test
	public void testGetEquipoPorEntidad() { // Equipo definido localmente
		System.out.println("\n===== TestRestController.testGetEquipoPorEntidad() =====");
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Equipo> eEquipo	= restTemplate.getForEntity("http://localhost:8080/equipos/{id}", Equipo.class, "UnamOro");
		System.out.println("Equipo recuperado:" + eEquipo.getBody());
		System.out.println("status:" + eEquipo.getStatusCodeValue());
		System.out.println("Headers:" );		
		eEquipo.getHeaders().forEach( (x,v)->System.out.println(x + ":" + v ) );
	}
	@Test
	public void testPostJugadorForEntity() {
		System.out.println("\n===== TestRestController.testPostJugadorForEntity() =====");
		
		RestTemplate restTemplate = new RestTemplate();
		
		JugadorDto jugador = getNuevoJugador();
		
		//  Innecesarios ? : Se agregan automaticamente con base en los HttpMessageConverter registrados
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		
		HttpEntity<JugadorDto> entityJugador = new HttpEntity<>(jugador, headers);
		try {
			ResponseEntity<JugadorDto> resp = restTemplate.postForEntity("http://localhost:8080/jugadores/valid", entityJugador, JugadorDto.class);
		    System.out.println("Resultados:");
			System.out.println("Jugador recuperado:" + resp.getBody());
			System.out.println("status:" + resp.getStatusCodeValue());
			System.out.println("Headers:" );		
			resp.getHeaders().forEach( (x,v)->System.out.println(x + ":" + v ) );
		}
		catch(RestClientResponseException rcrex) { 
			ErrorJugador err = extraerError(rcrex);
			System.out.println("ErrorJugador:"+ err);
		}
	}

	@Test
	public void testPostJugadorForObject() {
		System.out.println("\n===== TestRestController.testPostJugadorForObject() =====");
		
		RestTemplate restTemplate = new RestTemplate();
		
		JugadorDto jugador = getNuevoJugador();
		//  Innecesarios ? : Se agregan automaticamente con base en los HttpMessageConverter registrados
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		
		HttpEntity<JugadorDto> entityJugador = new HttpEntity<>(jugador, headers);
		
		try {
		    JugadorDto jugPost = restTemplate.postForObject("http://localhost:8080/jugadores/valid", entityJugador, JugadorDto.class);
			System.out.println("Jugador recuperado:" + jugPost);
		}
		catch(RestClientResponseException rcex) { 
			ErrorJugador err = extraerError(rcex);
			System.out.println("ErrorJugador:"+ err);
		}
	}
	
	@Test
	public void testPostJugadorForLocation() {
		System.out.println("\n===== TestRestController.testPostJugadorForLocation() =====");
		
		RestTemplate restTemplate = new RestTemplate();
		
		JugadorDto jugador = getNuevoJugador();
		//  Innecesarios ? : Se agregan automaticamente con base en los HttpMessageConverter registrados
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		
		HttpEntity<JugadorDto> entityJugador = new HttpEntity<>(jugador, headers);
		
		try {
		    URI uri = restTemplate.postForLocation("http://localhost:8080/jugadores/valid", entityJugador);
			System.out.println("Location recuperada:" + uri);
		}
		catch(RestClientResponseException rcex) { // Si hay demasiados errores, el json en el mensaje se trunca y falla el parseo
			ErrorJugador err = extraerError(rcex);
			System.out.println("ErrorJugador:"+ err);
		}
	}
	
	@Test
	public void testExchangePostJugador() throws URISyntaxException {
		System.out.println("\n===== TestRestController.testExchangePostJugador() =====");
		
		RestTemplate restTemplate = new RestTemplate();	
		JugadorDto jugador = getNuevoJugador();
		RequestEntity<JugadorDto> request = RequestEntity
			     .post(new URI("http://localhost:8080/jugadores/valid"))
			     .accept(MediaType.APPLICATION_JSON)
			     .contentType(MediaType.APPLICATION_JSON)
			     .body(jugador);
		try {
			ResponseEntity<String> resp = restTemplate.exchange(request, String.class);
			System.out.println(resp.getBody());		
		}
		catch(RestClientResponseException rcrex) {
			System.out.println(rcrex.getResponseBodyAsString());
		}
	}
	
	@Test
	public void testExecutePostJugador() throws URISyntaxException {
		System.out.println("\n===== TestRestController.testExecutePostJugador() =====");
		RestTemplate restTemplate = new RestTemplate();	
		JugadorDto jugador = getNuevoJugador();
		
		URI uri = new URI("http://localhost:8080/jugadores/valid");
		RequestCallback requestCallback = 
				req -> {
					req.getHeaders()
				       .setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
					req.getHeaders().setContentType(MediaType.APPLICATION_JSON);
					
					Jsonb binderJson = JsonbBuilder.create();
					binderJson.toJson(jugador, req.getBody());					
				};
			
		ResponseExtractor<JugadorDto> responseExtractor =
				resp -> { 
					Jsonb binderJson = JsonbBuilder.create();
					return binderJson.fromJson(resp.getBody(), JugadorDto.class);
				};
				
		try {
			JugadorDto jugResp = restTemplate.execute(uri, HttpMethod.POST, requestCallback, responseExtractor);
			System.out.println("Jugador agregado:" + jugResp);		
		}
		catch(RestClientResponseException rcrex) {
			System.out.println(rcrex.getResponseBodyAsString());
		}
	}
	
	private ErrorJugador extraerError(RestClientResponseException rcex) {
		Jsonb binderJson = JsonbBuilder.create();
		ErrorJugador err= binderJson.fromJson(rcex.getResponseBodyAsString(), ErrorJugador.class);
		return err;
	}
	
	private JugadorDto getNuevoJugador() {
		JugadorDto jugador = new JugadorDto();
		jugador.setId("test0006");
		jugador.setNombre("Ramiro Marentes Muñoz");
		jugador.setNumero(7);
		jugador.setPosicion("Medio");
		jugador.setFecNac(LocalDate.of(1991, 3, 11));
		jugador.setIdEquipo("Oax000");
		jugador.setLesionado(false);
		jugador.setSuspendido(false);
		jugador.setTitular(true);
		return jugador;		
	}
}
