package mx.com.qtx.torneo.serviciosEquipos;

import java.util.List;

public interface IReglasArmado {
	public int getMaxJugadores();
	public List<String> getPosicionesValidas();
	public boolean esPosicionValida(String posicion);
}
