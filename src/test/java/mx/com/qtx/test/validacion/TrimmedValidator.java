package mx.com.qtx.test.validacion;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class TrimmedValidator implements ConstraintValidator<Trimmed, String> {

	@Override
	public boolean isValid(String cadena, ConstraintValidatorContext context) {
		if(cadena == null)
			return true;
		if( cadena.trim().length() == cadena.length())
			return true;
		return false;
	}

}
