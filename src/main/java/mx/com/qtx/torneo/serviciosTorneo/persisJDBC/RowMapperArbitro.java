package mx.com.qtx.torneo.serviciosTorneo.persisJDBC;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import mx.com.qtx.torneo.serviciosTorneo.jdbc.entidades.Arbitro;

public class RowMapperArbitro implements RowMapper<Arbitro>{

	@Override
	public Arbitro mapRow(ResultSet rs, int rowNum) throws SQLException {
		Arbitro arb = new Arbitro();
		arb.setId(rs.getInt("ar_id"));
		arb.setNombre(rs.getString("ar_nombre"));
		arb.setFecNac(rs.getDate("ar_fecnac"));
		return arb;
	}

}
