package mx.com.qtx;

import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

public class ValidadorMascota extends LocalValidatorFactoryBean {
	@Override
	public boolean supports(Class<?> clazz) {
		if(clazz == Mascota.class)
			return true;
		return super.supports(clazz);
	}

}
