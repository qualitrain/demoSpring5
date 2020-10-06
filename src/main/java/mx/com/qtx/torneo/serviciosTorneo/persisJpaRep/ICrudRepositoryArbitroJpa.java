package mx.com.qtx.torneo.serviciosTorneo.persisJpaRep;

import org.springframework.data.repository.CrudRepository;

import mx.com.qtx.torneo.serviciosTorneo.jpa.entidades.Arbitro;

public interface ICrudRepositoryArbitroJpa extends CrudRepository<Arbitro, Integer> {

}
