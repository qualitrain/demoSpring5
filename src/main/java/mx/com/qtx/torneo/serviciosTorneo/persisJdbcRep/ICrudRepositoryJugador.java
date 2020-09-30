package mx.com.qtx.torneo.serviciosTorneo.persisJdbcRep;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import mx.com.qtx.torneo.serviciosTorneo.entidades.Jugador;

public interface ICrudRepositoryJugador extends CrudRepository<Jugador, String> {
	List<Jugador> findByIdEquipo(String idEquipo);
}
