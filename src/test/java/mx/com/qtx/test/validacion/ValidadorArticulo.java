package mx.com.qtx.test.validacion;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class ValidadorArticulo implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		if(clazz == Articulo.class)
			return true;
		return false;
	}

	@Override
	public void validate(Object target, Errors errors) {
		Articulo art = (Articulo)target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cve", "cve.vacia", "La clave es un campo obligatorio");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "descripcion", "descripcion.vacia", "La descripcion es un campo obligatorio");
		ValidationUtils.rejectIfEmpty(errors, "costo", "costo.nulo", "El costo es un campo obligatorio");
		ValidationUtils.rejectIfEmpty(errors, "precio", "precio.nulo", "El precio es un campo obligatorio");
		
		if(errors.hasFieldErrors("cve")==false) {
			if(art.getCve().trim().length() != art.getCve().length())
				errors.rejectValue("cve", "cve.espaciosExteriores", "La clave no debe iniciar o terminar con espacios");
			else
			if(art.getCve().length() < Articulo.LONG_MIN_CVE)
				errors.rejectValue("cve", "cve.muyCorta", "La clave no debe ser tan corta");
		}
		if(errors.hasFieldErrors("descripcion")==false) {
			if(art.getDescripcion().trim().length() != art.getDescripcion().length())
				errors.rejectValue("descripcion", "descripcion.espaciosExteriores", "La descripcion no debe iniciar o terminar con espacios");
			else
			if(art.getDescripcion().length() < Articulo.LONG_MIN_DESCRIPCION)
				errors.rejectValue("descripcion", "descripcion.muyCorta", "La descripcion no debe ser tan corta");
		}
		if(art.getExistencia() < 0)
			errors.rejectValue("existencia", "existencia.negativa","La existencia debe ser positiva");
		if(errors.hasFieldErrors("costo") == false)
			if(art.getCosto().intValue() < Articulo.COSTO_MIN)
				errors.rejectValue("costo", "costo.bajoMinimo", "El costo es demasiado bajo");
		if(errors.hasFieldErrors("precio") == false)
			if(art.getPrecio().intValue() <= ( art.getCosto().intValue() ) * Articulo.FACTOR_UTIL_MIN)
				errors.rejectValue("precio", "precio.SinUtilidad", "Precio demasiado bajo. No logra utilidad esperada");
	}

}
