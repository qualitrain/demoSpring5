package mx.com.qtx.servicio;

public interface IEstrategiaPuntaje {
	public IEstrategiaPuntaje getTiposAnotacion();
	public int getValorAnotacion(int tipo);
	public String getDescAnotacion(int tipo);
}
