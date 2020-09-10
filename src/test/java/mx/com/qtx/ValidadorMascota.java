package mx.com.qtx;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

public class ValidadorMascota extends LocalValidatorFactoryBean {
	@Override
	public boolean supports(Class<?> clazz) {
		if(clazz == Mascota.class)
			return true;
		return super.supports(clazz);
	}
	@Override
	public void validate(Object target, Errors errors) {
		System.out.println("ValidadorMascota.validate() target Class="+target.getClass().getName());
		System.out.println("ValidadorMascota.validate() target=" + target);
		Mascota mascota = (Mascota)target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nombre", "nombre.vacio","El nombre es obligatorio");
		if(errors.hasFieldErrors("nombre") == false) {
			if(mascota.getNombre().trim().length() < 3)
				errors.rejectValue("nombre", "demasiadoCorto","el nombre es demasiado corto");
		}
			
		if(mascota.getPeso() <= 0) {
			errors.rejectValue("peso","menorIgualCero");
		}
		else
		if(mascota.getPeso() > 80) {
		   errors.rejectValue("peso", "excesivo","el peso del perro no puede ser mayor a 80 Kg");
	    }			
	}

}
