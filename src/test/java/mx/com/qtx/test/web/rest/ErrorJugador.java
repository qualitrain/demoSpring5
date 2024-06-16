package mx.com.qtx.test.web.rest;

import java.util.List;
import java.util.Map;

public class ErrorJugador {
	private int tipo;
	private String cveError;
	private String mensaje;
	private Map<String, List<String>> erroresXcampo;
	private String mensajeOriginal;
	private String causa;
	
	public ErrorJugador() {
		super();
	}
	public int getTipo() {
		return tipo;
	}
	public void setTipo(int tipo) {
		this.tipo = tipo;
	}
	public String getCveError() {
		return cveError;
	}
	public void setCveError(String cveError) {
		this.cveError = cveError;
	}
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	public Map<String, List<String>> getErroresXcampo() {
		return erroresXcampo;
	}
	public void setErroresXcampo(Map<String, List<String>> erroresXcampo) {
		this.erroresXcampo = erroresXcampo;
	}
	public String getMensajeOriginal() {
		return mensajeOriginal;
	}
	public void setMensajeOriginal(String mensajeOriginal) {
		this.mensajeOriginal = mensajeOriginal;
	}
	public String getCausa() {
		return causa;
	}
	public void setCausa(String causa) {
		this.causa = causa;
	}
	@Override
	public String toString() {
		return "ErrorJugador [tipo=" + tipo + ", cveError=" + cveError + ", mensaje=" + mensaje + ", erroresValidacion="
				+ erroresXcampo + ", mensajeOriginal=" + mensajeOriginal + ", causa=" + causa + "]";
	}

}
