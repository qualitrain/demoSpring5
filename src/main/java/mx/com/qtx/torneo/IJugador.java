package mx.com.qtx.torneo;

import java.util.Date;

public interface IJugador {
	public String getId(); 
	public void setId(String id);
	public String getNombre();
	public void setNombre(String nombre);
	public int getNumero();
	public void setNumero(int numero);
	public String getPosicion(); 
	public void setPosicion(String posicion);
	public Date getFecNac();
	public void setFecNac(Date fecNac);
	public boolean isLesionado();
	public void setLesionado(boolean lesionado);
	public boolean isSuspendido();
	public void setSuspendido(boolean suspendido);
	public boolean isTitular();
	public void setTitular(boolean titular);
	public void setEquipo(IEquipo equipo);
	public IEquipo getEquipo();

}
