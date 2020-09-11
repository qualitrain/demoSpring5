package mx.com.qtx.test.validacion;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class RedituableValidator implements ConstraintValidator<Redituable, Articulo>{
	@Override
	public boolean isValid(Articulo art, ConstraintValidatorContext context) {
		if(art == null || art.getCosto() == null || art.getPrecio() == null)
			return true; // Recomendado por la Jakarta Bean Validation Spec
	    if(art.getCosto().intValue() <= Articulo.COSTO_MIN )
	    	return false;
		if (art.getPrecio().intValue() >= ( art.getCosto().intValue() * Articulo.FACTOR_UTIL_MIN ))
			return true;
		return false;
	}

}
