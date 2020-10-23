package mx.com.qtx.util;

import java.util.Date;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ValidadorEdadMin implements ConstraintValidator<EdadMin, Date> {
	private int edadMin;
	
	@Override
	public void initialize(EdadMin anotacion) {
		this.edadMin = anotacion.edadMin();
	}
	@Override
	public boolean isValid(Date fecha, ConstraintValidatorContext context) {
		if (FechaUtil.calcularEdad(fecha) >= this.edadMin)
			return true;
		return false;
	}

}
