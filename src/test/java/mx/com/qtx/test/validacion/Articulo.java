package mx.com.qtx.test.validacion;

import java.math.BigDecimal;

public class Articulo {
	private String cve;
	private String descripcion;
	private BigDecimal costo;
	private BigDecimal precio;
	private int existencia;
	
	public Articulo() {
		super();
	}

	public Articulo(String cve, String descripcion, BigDecimal costo, BigDecimal precio, int existencia) {
		super();
		this.cve = cve;
		this.descripcion = descripcion;
		this.costo = costo;
		this.precio = precio;
		this.existencia = existencia;
	}

	public String getCve() {
		return cve;
	}

	public void setCve(String cve) {
		this.cve = cve;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public BigDecimal getCosto() {
		return costo;
	}

	public void setCosto(BigDecimal costo) {
		this.costo = costo;
	}

	public BigDecimal getPrecio() {
		return precio;
	}

	public void setPrecio(BigDecimal precio) {
		this.precio = precio;
	}

	public int getExistencia() {
		return existencia;
	}

	public void setExistencia(int existencia) {
		this.existencia = existencia;
	}

	@Override
	public String toString() {
		return "Articulo [cve=" + cve + ", descripcion=" + descripcion + ", costo=" + costo + ", precio=" + precio
				+ ", existencia=" + existencia + "]";
	}

}
