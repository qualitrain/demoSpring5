package mx.com.qtx.torneo.serviciosTorneo.persisJDBC;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import mx.com.qtx.torneo.serviciosTorneo.entidades.Equipo;
import mx.com.qtx.torneo.serviciosTorneo.entidades.Jugador;

public class RowMapperJugador implements RowMapper<Jugador> {

	@Override
	public Jugador mapRow(ResultSet rs, int rowNum) throws SQLException {
		Jugador jug = new Jugador();
		jug.setId(rs.getString("jug_id"));
		jug.setNombre(rs.getString("jug_nombre"));
		jug.setNumero(rs.getInt("jug_numero"));
		jug.setPosicion(rs.getString("jug_posicion"));
		jug.setFecNac(rs.getDate("jug_fecnac"));
		jug.setLesionado(rs.getBoolean("jug_lesionado"));
		jug.setSuspendido(rs.getBoolean("jug_suspendido"));
		jug.setTitular(rs.getBoolean("jug_titular"));
		Equipo equipoJug = new Equipo();
		equipoJug.setId(rs.getString("jug_id_eq"));
		jug.setEquipo(equipoJug);
		return jug;
	}

}
