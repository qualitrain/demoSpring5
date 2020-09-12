package mx.com.qtx.test.validacion;

import java.math.BigDecimal;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

@Redituable(message = "precio.SinUtilidad")
public class Articulo {
	public static final float FACTOR_UTIL_MIN = 1.2f;
	public static final int LONG_MIN_CVE = 2;
	public static final int LONG_MIN_DESCRIPCION = 3;
	public static final int COSTO_MIN = 20;
	
	@NotBlank(message = "cve.vacia")
	@Size(min = LONG_MIN_CVE, message="cve.muyCorta")
	@Trimmed(message = "cve.espaciosExteriores")
	private String cve;
	
	@NotBlank(message = "descripcion.vacia")
	@Size(min = LONG_MIN_DESCRIPCION, message="descripcion.muyCorta")
	@Trimmed(message = "descripcion.espaciosExteriores")
	private String descripcion;
	
	@NotNull(message = "costo.nulo")
	@Min(value=COSTO_MIN, message = "costo.bajoMinimo")
	private BigDecimal costo;
	
	@NotNull(message = "precio.nulo")
	private BigDecimal precio;
	
	@PositiveOrZero(message = "existencia.negativa")
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
