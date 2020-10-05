package mx.com.qtx.torneo.serviciosTorneo.persisJdbcRep;

import org.springframework.data.repository.CrudRepository;

import mx.com.qtx.torneo.serviciosTorneo.jdbc.entidades.Equipo;

public interface ICrudRepositoryEquipo extends CrudRepository<Equipo, String> {

}
