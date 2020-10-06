package mx.com.qtx.torneo.serviciosTorneo.persisJpaRep;

import org.springframework.data.repository.CrudRepository;

import mx.com.qtx.torneo.serviciosTorneo.jpa.entidades.Equipo;

public interface ICrudRepositoryEquipoJpa extends CrudRepository<Equipo, String> {

}
