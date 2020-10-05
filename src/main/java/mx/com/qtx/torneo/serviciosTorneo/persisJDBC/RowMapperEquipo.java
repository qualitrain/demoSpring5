package mx.com.qtx.torneo.serviciosTorneo.persisJDBC;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import mx.com.qtx.torneo.serviciosTorneo.jdbc.entidades.Equipo;

public class RowMapperEquipo implements RowMapper<Equipo> {

	@Override
	public Equipo mapRow(ResultSet rs, int rowNum) throws SQLException {
		Equipo equipo = new Equipo();
		equipo.setId(rs.getString("eq_id"));
		equipo.setNombre(rs.getString("eq_nombre"));
		equipo.setApodo(rs.getString("eq_apodo"));
		equipo.setPatrocinador(rs.getString("eq_patrocinador"));
		equipo.setJueJugados(rs.getInt("eq_jj"));
		equipo.setJueGanados(rs.getInt("eq_jg"));
		equipo.setJueEmpatados(rs.getInt("eq_je"));
		equipo.setJuePerdidos(rs.getInt("eq_jp"));
		equipo.setPuntos(rs.getInt("eq_puntos"));
		equipo.setEntrenador(rs.getString("eq_entrenador"));
		return equipo;
	}

}
