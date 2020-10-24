package mx.com.qtx.torneo;

import java.util.Date;

public interface IJugador {
	String getId(); 
	void setId(String id);
	
	String getNombre();
	void setNombre(String nombre);
	
	int getNumero();
	void setNumero(int numero);
	
	String getPosicion(); 
	void setPosicion(String posicion);
	
	Date getFecNac();
	void setFecNac(Date fecNac);
	
	boolean isLesionado();
	void setLesionado(boolean lesionado);
	
	boolean isSuspendido();
	void setSuspendido(boolean suspendido);
	
	boolean isTitular();
	void setTitular(boolean titular);
	
	void setEquipo(IEquipo equipo);
	IEquipo getEquipo();
}
