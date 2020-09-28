package mx.com.qtx.torneo.serviciosTorneo.persisJDBC;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
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
	public IEquipo insertarEquipo(IEquipo iequipo) {
		System.out.println("***** insertarEquipo(" + iequipo + ") *****");
		try {
			if(iequipo instanceof Equipo) {
				Equipo equipo  = (Equipo) iequipo;
				return insertarEquipo(equipo);	
			}
			String sql = "insert into equipo (eq_id, eq_nombre) "
					+ "values (?, ?)";
			int nRows = this.jdbcTemplate.update(sql, iequipo.getID(), iequipo.getNombreEquipo());
			if(nRows > 0)
				return iequipo;
			else
			    return null;
			
		}
		catch (Throwable ex) {
			throw new PersistenciaException("falla en insertarEquipo(" + iequipo
					+ ") por infraestructura subyacente",ex);
		}
	}

	private IEquipo insertarEquipo(Equipo equipo) {
		String sql = "insert into equipo (eq_id, eq_nombre, eq_apodo, eq_patrocinador, "
				+ "eq_jj, eq_jg, eq_je, eq_jp, eq_puntos, eq_entrenador) "
				+ "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		int nRows = this.jdbcTemplate.update(sql, 
				            equipo.getId(), equipo.getNombre(), equipo.getApodo(), 
				            equipo.getPatrocinador(), equipo.getJueJugados(), equipo.getJueGanados(),
				            equipo.getJueEmpatados(), equipo.getJuePerdidos(), equipo.getPuntos(),
				            equipo.getEntrenador());
		if(nRows > 0)
			return equipo;
		else
		    return null;
	}
	@Override
	public IEquipo actualizarEquipo(IEquipo iequipo) {
		System.out.println("***** actualizarEquipo(" + iequipo + ") *****");
		try {
			if(iequipo instanceof Equipo) {
				Equipo equipo  = (Equipo) iequipo;
				return actualizarEquipo(equipo);	
			}
			String sql = "update equipo set eq_nombre=? "
					+ "where eq_id = ?";
			int nRows = this.jdbcTemplate.update(sql, iequipo.getNombreEquipo(), iequipo.getID());
			if(nRows > 0)
				return iequipo;
			else
			    return null;
		}
		catch (Throwable ex) {
			throw new PersistenciaException("falla en insertarEquipo(" + iequipo
					+ ") por infraestructura subyacente",ex);
		}
		
	}

	public IEquipo actualizarEquipo(Equipo equipo) {
		String sql = "update equipo set eq_nombre=?, eq_apodo=?, eq_patrocinador=?, "
				+ "eq_jj=?, eq_jg=?, eq_je=?, eq_jp=?, eq_puntos=?, eq_entrenador=? "
				+ "where eq_id=?";
		int nRows = this.jdbcTemplate.update(sql, 
				            equipo.getNombre(), equipo.getApodo(), 
				            equipo.getPatrocinador(), equipo.getJueJugados(), equipo.getJueGanados(),
				            equipo.getJueEmpatados(), equipo.getJuePerdidos(), equipo.getPuntos(),
				            equipo.getEntrenador(), equipo.getId() );
		if(nRows > 0)
			return equipo;
		else
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
	public IArbitro insertarArbitro(IArbitro iarbitro) {
		try {
			Arbitro arbitro  = (Arbitro) iarbitro;
			String sql = "insert into arbitro (ar_nombre, ar_fecnac) "
					+ "values (?, ?)";
			
			KeyHolder keyHolder = new GeneratedKeyHolder();
			CreadorPrepStatement crStmt = new CreadorPrepStatement(sql, arbitro.getNombre(), arbitro.getFecNac());
			int nRows  = this.jdbcTemplate.update(crStmt, keyHolder);
			
			if(nRows > 0) {
				arbitro.setId(keyHolder.getKey().intValue()); // Recuperando la llave auto-generada por la BD
				return arbitro;
			}
			else
			    return null;			
		}
		catch (Throwable ex) {
			throw new PersistenciaException("falla en insertarArbitro(" + iarbitro
					+ ") por infraestructura subyacente",ex);
		}
	}
	
	@Override
	public IArbitro actualizarArbitro(IArbitro iarbitro) {
		try {
			Arbitro arbitro  = (Arbitro) iarbitro;
			String sql = "update set ar_nombre=? , ar_fecnac=? "
					          + "where ar_id = ?";
			
			int nRows  = this.jdbcTemplate.update(sql, arbitro.getNombre(), arbitro.getFecNac(), arbitro.getId());
			
			if(nRows > 0) {
				return arbitro;
			}
			else
			    return null;			
		}
		catch (Throwable ex) {
			throw new PersistenciaException("falla en actualizarArbitro(" + iarbitro
					+ ") por infraestructura subyacente",ex);
		}
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
	public IJugador insertarJugador(IJugador ijugador) {
		System.out.println("***** insertarJugador(" + ijugador + ") *****");
		try {
			String sql = "insert into jugador (jug_id, jug_nombre, jug_numero, "
					+ "jug_posicion, jug_fecnac, jug_lesionado, "
					+ "jug_suspendido, jug_titular, jug_id_eq) "
					+ "values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
			String idEquipo = ijugador.getEquipo() == null ? null : ijugador.getEquipo().getID();

			int nRows = this.jdbcTemplate.update(sql, ijugador.getId(), ijugador.getNombre(), ijugador.getNumero(),
					ijugador.getPosicion(), ijugador.getFecNac(), ijugador.isLesionado(),
					ijugador.isSuspendido(), ijugador.isTitular(), idEquipo);
			if(nRows > 0)
				return ijugador;
			else
			    return null;
			
		}
		catch (Throwable ex) {
			throw new PersistenciaException("falla en insertarJugador(" + ijugador
					+ ") por infraestructura subyacente",ex);
		}
	}
	
	@Override
	public IJugador actualizarJugador(IJugador ijugador) {
		System.out.println("***** actualizarJugador(" + ijugador + ") *****");
		try {
			String sql = "update jugador set jug_nombre=?, jug_numero=?, "
					+ "jug_posicion=?, jug_fecnac=?, jug_lesionado=?, "
					+ "jug_suspendido=?, jug_titular=?, jug_id_eq=? "
					+ "where jug_id =?";
			String idEquipo = ijugador.getEquipo() == null ? null : ijugador.getEquipo().getID();

			int nRows = this.jdbcTemplate.update(sql, ijugador.getNombre(), ijugador.getNumero(),
					ijugador.getPosicion(), ijugador.getFecNac(), ijugador.isLesionado(),
					ijugador.isSuspendido(), ijugador.isTitular(), idEquipo, ijugador.getId());
			if(nRows > 0)
				return ijugador;
			else
			    return null;
			
		}
		catch (Throwable ex) {
			throw new PersistenciaException("falla en actualizarJugador(" + ijugador
					+ ") por infraestructura subyacente",ex);
		}
	}


}
