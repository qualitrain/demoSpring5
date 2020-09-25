package mx.com.qtx.torneo.serviciosTorneo.persisJDBC;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import mx.com.qtx.torneo.IArbitro;
import mx.com.qtx.torneo.IEquipo;
import mx.com.qtx.torneo.IJugador;
import mx.com.qtx.torneo.serviciosTorneo.IGestorDatos;
import mx.com.qtx.torneo.serviciosTorneo.PersistenciaException;
import mx.com.qtx.torneo.serviciosTorneo.entidades.Arbitro;
import mx.com.qtx.torneo.serviciosTorneo.entidades.Equipo;
import mx.com.qtx.torneo.serviciosTorneo.entidades.Jugador;

@Primary
@Repository
public class GestorDatosJdbcTemplate implements IGestorDatos {
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;

	@Autowired	
	public GestorDatosJdbcTemplate(DataSource dataSource) {
		super();
		this.dataSource = dataSource;
		this.jdbcTemplate = new JdbcTemplate(this.dataSource);
	}

	@Override
	public List<IEquipo> cargarEquipos() {
		try {
			String sql = "select eq_id, eq_nombre, eq_apodo, eq_patrocinador, "
					      + "eq_jj, eq_jg, eq_je, eq_jp, eq_puntos, eq_entrenador "
					      + "from equipo";
			List<Equipo> listEquipos = this.jdbcTemplate.query(sql, new RowMapperEquipo());
			return new ArrayList<>(listEquipos); //Necesario porque List<Equipo> != List<IEquipo>
		}
		catch (Throwable ex) {
			throw new PersistenciaException("falla en cargarEquipos() por infraestructura subyacente",ex);
		}
	}

	@Override
	public IEquipo leerEquipoXID(String id) {
		try {
			String sql = "select eq_id, eq_nombre, eq_apodo, eq_patrocinador, "
				      + "eq_jj, eq_jg, eq_je, eq_jp, eq_puntos, eq_entrenador "
				      + "from equipo where eq_id = ?";
			
			Equipo equipo = this.jdbcTemplate.queryForObject(sql, new RowMapperEquipo(), id);
			return equipo; 
		}
		catch (EmptyResultDataAccessException erdax) { //No existe
			return null;
		}
		catch (Throwable ex) {
			throw new PersistenciaException("falla en leerEquipoXID("
					+ id + ") por infraestructura subyacente",ex);
		}
	}

	@Override
	public IEquipo actualizarEquipo(IEquipo equipo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IEquipo insertarEquipo(IEquipo equipo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<IArbitro> cargarArbitros() {
		try {
			String sql = "select ar_id, ar_nombre, ar_fecnac from arbitro";
			List<Arbitro> listArbitros = this.jdbcTemplate.query(sql, new RowMapperArbitro());
			return new ArrayList<>(listArbitros); //Necesario porque List<Arbitro> != List<IArbitro>
		}
		catch (Throwable ex) {
			throw new PersistenciaException("falla en cargarArbitros() por infraestructura subyacente",ex);
		}
	}

	@Override
	public IArbitro leerArbitroXID(int id) {
		try {
			String sql = "select ar_id, ar_nombre, ar_fecnac from arbitro where ar_id = ?";
			Arbitro arbitro = this.jdbcTemplate.queryForObject(sql, new RowMapperArbitro(), id);
			return arbitro; 
		}
		catch (EmptyResultDataAccessException erdax) { //No existe
			return null;
		}
		catch (Throwable ex) {
			throw new PersistenciaException("falla en leerArbitroXID("
					+ id + ") por infraestructura subyacente",ex);
		}
	}

	@Override
	public IArbitro actualizarArbitro(IArbitro arbitro) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<IJugador> cargarJugadores() {
		try {
			String sql = "select jug_id, jug_nombre, jug_numero, jug_posicion, jug_fecnac, "
					+ "jug_lesionado, jug_suspendido, jug_titular, jug_id_eq from jugador";
			List<Jugador> listJugadores = this.jdbcTemplate.query(sql, new RowMapperJugador());
			return new ArrayList<>(listJugadores); //Necesario porque List<Jugador> != List<IJugador>
		}
		catch (Throwable ex) {
			throw new PersistenciaException("falla en cargarJugadores() por infraestructura subyacente",ex);
		}
	}
	
	@Override
	public List<IJugador> leerJugadoresXEquipo(String idEquipo) {
		try {
			String sql = "select jug_id, jug_nombre, jug_numero, jug_posicion, jug_fecnac, "
					+ "jug_lesionado, jug_suspendido, jug_titular, jug_id_eq from jugador "
					+ "where jug_id_eq = ?";
			List<Jugador> listJugadores = this.jdbcTemplate.query(sql, new RowMapperJugador(), idEquipo);
			return new ArrayList<>(listJugadores); //Necesario porque List<Jugador> != List<IJugador>
		}
		catch (Throwable ex) {
			throw new PersistenciaException("falla en leerJugadoresXEquipo(" + idEquipo
					+ ") por infraestructura subyacente",ex);
		}
	}
	@Override
	public IJugador leerJugadorXID(String id) {
		try {
			String sql = "select jug_id, jug_nombre, jug_numero, jug_posicion, jug_fecnac, "
					+ "jug_lesionado, jug_suspendido, jug_titular, jug_id_eq from jugador"
					+ " where jug_id = ?";
			Jugador jugador = this.jdbcTemplate.queryForObject(sql, new Object[]{id},new RowMapperJugador());
			return jugador; 
		}
		catch (EmptyResultDataAccessException erdax) { //No existe
			return null;
		}
		catch (Throwable ex) {
			throw new PersistenciaException("falla en leerJugadorXID("
					+ id + ") por infraestructura subyacente",ex);
		}
	}

	@Override
	public IJugador actualizarJugador(IJugador jugador) {
		// TODO Auto-generated method stub
		return null;
	}

}
