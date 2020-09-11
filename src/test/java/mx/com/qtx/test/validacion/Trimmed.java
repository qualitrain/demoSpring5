package mx.com.qtx.test.validacion;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Retention(RUNTIME)
@Target(FIELD)
@Constraint(validatedBy = TrimmedValidator.class)
public @interface Trimmed {
	String message() default "No debe iniciar o terminar con espacios";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };
}
