package mx.com.qtx.test.aop;

import org.springframework.stereotype.Component;

@Component
public class SimuladorOperaciones {
	private int valor;
	private String cadena;
	
	public SimuladorOperaciones() {
		this.cadena = "no inicializada";
		this.valor = 0;
	}
	public SimuladorOperaciones(int valor) {
		this.valor = valor;
		this.cadena = "no inicializada";
	}
	public SimuladorOperaciones(String cadena) {
		this.cadena = cadena;
		this.valor = 0;
	}
	public SimuladorOperaciones(int valor, String cadena) {
		this.valor = valor;
		this.cadena = cadena;
	}
	public int getValor() {
		return valor;
	}
	public void setValor(int valor) {
		this.valor = valor;
	}
	public String getCadena() {
		return cadena;
	}
	public void setCadena(String cadena) {
		this.cadena = cadena;
	}
	@Override
	public String toString() {
		return "SimuladorOperaciones [valor=" + valor + ", cadena=" + cadena + "]";
	}
	

}
