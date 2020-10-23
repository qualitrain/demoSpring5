package mx.com.qtx.util;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Retention(RUNTIME)
@Target(FIELD)
@Constraint(validatedBy = ValidadorEdadMax.class)
public @interface EdadMax {
	String message() default "edadInvalida";
	int edadMax();
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
}
