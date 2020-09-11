package mx.com.qtx;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@Configuration
public class ConfiguracionValidacion {
	@Bean(name = "validator")
	public Validator getValidador() {
		return new LocalValidatorFactoryBean();
	}
}
