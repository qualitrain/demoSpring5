package mx.com.qtx.web.jpa;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import mx.com.qtx.torneo.NegocioException;

public class ErrorCte {
	private int tipo;
	private String cveError;
	private String mensaje;
	private Map<String,List<String>> erroresValidacion;
	private String mensajeOriginal;
	private String causa;
	
	private ErrorCte() {
		super();
	}

	public static ErrorCte crearErrorDeBinding(BindingResult br, MessageSource ms, Locale localidad){
		ErrorCte errorCte = new ErrorCte();
		errorCte.tipo = 1;
		errorCte.cveError= "BND000";
		errorCte.mensaje=ms.getMessage("errorGral.datInvalidos", null,  localidad);
		errorCte.erroresValidacion = new HashMap<>();
		errorCte.mensajeOriginal= null;
		
		for(FieldError errI : br.getFieldErrors()) {
			String campo = errI.getField();
			List<String> lstErrsCampo = errorCte.erroresValidacion.getOrDefault(campo, new ArrayList<String>());
			lstErrsCampo.add(ms.getMessage(errI.getDefaultMessage(), null, errI.getCode(), localidad));
			errorCte.erroresValidacion.put(campo, lstErrsCampo);
		}
		return errorCte;		
	}

	public static ErrorCte crearErrorDeBinding(HttpMessageNotReadableException hmnrex, MessageSource ms,
			Locale localidad) {
		ErrorCte errorCte = new ErrorCte();
		errorCte.tipo = 1;
		errorCte.cveError= "EST000";
		errorCte.mensaje=ms.getMessage("errorGral.estructura", null,  localidad);
		errorCte.erroresValidacion = null;
		errorCte.mensajeOriginal = hmnrex.getMessage();
		return errorCte;
	}
	public static ErrorCte crearErrorDeNegocio(NegocioException nex, MessageSource ms, Locale localidad) {
		ErrorCte errorCte = new ErrorCte();
		errorCte.tipo = 2;
		errorCte.cveError = nex.getClave();
		errorCte.mensaje=ms.getMessage(nex.getMessage(), null,  localidad);
		errorCte.erroresValidacion = null;
		errorCte.mensajeOriginal = null;
		if(nex.getCause() != null) {
			errorCte.causa = errorCte.getCadCausas(nex.getCause(), ms, localidad);
		}
		return errorCte;
	}
	
	private String getCadCausas(Throwable ex, MessageSource ms, Locale localidad) {
		StringBuilder sb = new StringBuilder();
		do {
			sb.append("-> ");
			sb.append(ex.getClass().getName());
			sb.append(":");
			String msj = getMensajeLocalizadoSiExiste(ex.getMessage(),ms,localidad);
			sb.append(msj);
			ex = ex.getCause();
		}
		while(ex != null);
		
		return sb.toString();
	}
	
	private String getMensajeLocalizadoSiExiste(String message, MessageSource ms, Locale localidad) {
		String mensaje;
		try {
			mensaje = ms.getMessage(message, null, localidad);
		}catch(NoSuchMessageException nsmx) {
			return message;
		}
		return mensaje;
	}

	public int getTipo() {
		return tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public Map<String, List<String>> getErroresValidacion() {
		return erroresValidacion;
	}

	public void setErroresValidacion(Map<String, List<String>> erroresValidacion) {
		this.erroresValidacion = erroresValidacion;
	}

	public String getMensajeOriginal() {
		return mensajeOriginal;
	}

	public void setMensajeOriginal(String mensajeOriginal) {
		this.mensajeOriginal = mensajeOriginal;
	}

	public String getCveError() {
		return cveError;
	}

	public void setCveError(String cveError) {
		this.cveError = cveError;
	}

	public String getCausa() {
		return causa;
	}

	public void setCausa(String causa) {
		this.causa = causa;
	}
}
