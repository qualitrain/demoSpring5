package mx.com.qtx.util;

import java.util.Date;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ValidadorEdadMax implements ConstraintValidator<EdadMax, Date> {
	private int edadMax;
	
	@Override
	public void initialize(EdadMax anotacion) {
		this.edadMax = anotacion.edadMax();
	}
	@Override
	public boolean isValid(Date fecha, ConstraintValidatorContext context) {
		if(fecha == null)
			return false;
		if (FechaUtil.calcularEdad(fecha) <= this.edadMax)
			return true;
		return false;
	}

}
