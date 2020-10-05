package mx.com.qtx.torneo.serviciosTorneo.persisJdbcRep;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import mx.com.qtx.torneo.serviciosTorneo.jdbc.entidades.Jugador;

public interface IPageAndSortRepositoryJugador extends PagingAndSortingRepository<Jugador, String> {
	List<Jugador> findByPosicionAndIdEquipo(String posicion, String idEquipo);
	List<Jugador> findByPosicionOrPosicionOrderByPosicion(String pos1, String pos2);
	List<Jugador> findFirst5ByOrderByFecNacDesc();
	
	List<Jugador> findByTitularTrue(); 
	List<Jugador> findByTitular(boolean titular, Sort sort); //Recibe un clasificador dinámico
	List<Jugador> findByTitular(boolean titular, Pageable paginable); //Pagina 
	
	@Query("SELECT distinct jug_id_eq FROM jugador")
	List<String> findDistinctIdEquipoBy();
}
