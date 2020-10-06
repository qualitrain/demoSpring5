package mx.com.qtx.torneo.serviciosTorneo.persisJpaRep;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import mx.com.qtx.torneo.serviciosTorneo.jpa.entidades.Jugador;

public interface ICrudRepositoryJugadorJpa extends CrudRepository<Jugador, String> {
	List<Jugador> findByIdEquipo(String idEquipo);
}
