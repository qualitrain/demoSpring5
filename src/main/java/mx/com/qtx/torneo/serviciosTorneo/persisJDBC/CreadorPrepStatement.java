package mx.com.qtx.torneo.serviciosTorneo.persisJDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.jdbc.core.PreparedStatementCreator;

public class CreadorPrepStatement implements PreparedStatementCreator {
	private String sql;
	private Object[] args;
	
	
	public CreadorPrepStatement(String sql, Object... args) {
		this.sql = sql;
		this.args = args;
	}

	@Override
	public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
		PreparedStatement pStmt = con.prepareStatement(this.sql, Statement.RETURN_GENERATED_KEYS );
		for(int i=0; i<this.args.length; i++) {
			pStmt.setObject(i + 1, this.args[i]);
		}
		return pStmt;
	}

}
