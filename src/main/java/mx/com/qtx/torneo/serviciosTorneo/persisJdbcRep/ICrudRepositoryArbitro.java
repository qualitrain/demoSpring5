package mx.com.qtx.torneo.serviciosTorneo.persisJdbcRep;

import org.springframework.data.repository.CrudRepository;

import mx.com.qtx.torneo.serviciosTorneo.jdbc.entidades.Arbitro;

public interface ICrudRepositoryArbitro extends CrudRepository<Arbitro, Integer> {

}
