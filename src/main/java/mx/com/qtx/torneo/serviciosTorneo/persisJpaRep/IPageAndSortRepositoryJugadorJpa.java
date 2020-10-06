package mx.com.qtx.torneo.serviciosTorneo.persisJpaRep;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import mx.com.qtx.torneo.serviciosTorneo.jpa.entidades.Jugador;

public interface IPageAndSortRepositoryJugadorJpa extends PagingAndSortingRepository<Jugador, String> {
	List<Jugador> findByPosicionAndIdEquipo(String posicion, String idEquipo);
	List<Jugador> findByPosicionOrPosicionOrderByPosicion(String pos1, String pos2);
	List<Jugador> findFirst5ByOrderByFecNacDesc();
	
	List<Jugador> findByTitularTrue(); 
	List<Jugador> findByTitular(boolean titular, Sort sort); //Recibe un clasificador dinámico
	List<Jugador> findByTitular(boolean titular, Pageable paginable); //Pagina 
	
	@Query("SELECT distinct j.idEquipo FROM Jugador j")
	List<String> findDistinctIdEquipoBy();
}
