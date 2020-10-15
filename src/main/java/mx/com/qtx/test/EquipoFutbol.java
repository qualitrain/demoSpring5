package mx.com.qtx.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mx.com.qtx.torneo.IEquipo;
import mx.com.qtx.torneo.IJugador;

public class EquipoFutbol implements IEquipo {
	private static int[] nums = {1,2,3,4,5,6,7,8,9,10,11};
	private static String[] posiciones = {"Portero","Defensa Central","Defensa Central","Lateral Izq",
			                              "Lateral Der","Medio Contención","Volante","Volante",
			                              "Extremo Izq","Extremo Der","Centro Delantero"};	
	private String nombreEquipo;
	private List<String> jugadoresEquipo;
	

	public EquipoFutbol(String nombreEquipo) {
		this.nombreEquipo = nombreEquipo;
		this.jugadoresEquipo = new ArrayList<>();
	}

	@Override
	public String getNombreEquipo() {
		return this.nombreEquipo;
	}

	@Override
	public int getNumJugadores() {
		return this.jugadoresEquipo.size();
	}

	@Override
	public Map<Integer, String> getJugadoresTitulares() {
		Map<Integer,String> titulares = new HashMap<>();
		int numJugador = 0;
		for(String jugadorI: this.jugadoresEquipo) {
			titulares.put(nums[numJugador], jugadorI);
			numJugador++;
		}
		return titulares;
	}

	@Override
	public String getPosicionJugador(int numJugador) {
		return posiciones[ numJugador%11 ];
	}
	
	public void agregarJugador(String... jugadores) {
		this.jugadoresEquipo.addAll(Arrays.asList(jugadores));
	}

	@Override
	public String getID() {
		return this.nombreEquipo;
	}

	@Override
	public void setID(String id) {
		this.setNombreEquipo(id);
	}

	@Override
	public void setNombreEquipo(String nombre) {
			this.nombreEquipo = nombre;
	}

	@Override
	public List<IJugador> getListaJugadores() {
		List<IJugador> listJugadores = new ArrayList<>();
		int i=1;
		int aleatorio = (int) (Math.random() * 100000);
		for(String nomI: this.jugadoresEquipo) {
			listJugadores.add(new JugadorFutbol(""+(i+aleatorio), nomI, i , posiciones[(i-1)%posiciones.length]));
			i++;
		}
		return listJugadores;
	}

	@Override
	public String toString() {
		return "EquipoFutbol [nombreEquipo=" + nombreEquipo + ", jugadoresEquipo=" + jugadoresEquipo + "]";
	}

	@Override
	public int agregarJugador(IJugador jugador) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Object getCampo(String nombre) {
		// TODO Auto-generated method stub
		return null;
	}

}
