package mx.com.qtx.test.validacion;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Retention(RUNTIME)
@Target(TYPE)
@Constraint(validatedBy = RedituableValidator.class)
public @interface Redituable {
	String message() default "Precio demasiado bajo. No logra utilidad esperada";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };
}
