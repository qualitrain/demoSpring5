package mx.com.qtx.test.validacion;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

@SpringBootTest
public class TestValidador {
	
	@Test
	public void testValidadorBasico(){
//		Articulo articulo = getArticuloVacio();
//		Articulo articulo = getArticuloConErrores();
//		Articulo articulo = getArticuloConErrores2();
//		Articulo articulo = getArticuloConErrores3();
//		Articulo articulo = getArticuloConErrores4();
		Articulo articulo = getArticuloCorrecto();
		ValidadorArticulo validador = new ValidadorArticulo();
		Errors errores = new BeanPropertyBindingResult(articulo,"articulo");
		validador.validate(articulo, errores );
		if(errores.hasErrors()) {
			mostrarErrores(errores);
		}
		else
			System.out.println("Artículo correctamente inicializado " + articulo);
	}
	private Articulo getArticuloVacio() {
		return new Articulo();
	}
	private Articulo getArticuloConErrores() {
		return new Articulo("X","X",new BigDecimal(0.45), null,-1);
	}
	private Articulo getArticuloConErrores2() {
		return new Articulo("X-1","X-1",new BigDecimal(0.45), new BigDecimal(0),5);
	}
	private Articulo getArticuloConErrores3() {
		return new Articulo("X-1","Bujía", new BigDecimal(1.45), new BigDecimal(1.5),5);
	}
	private Articulo getArticuloConErrores4() {
		return new Articulo("X-1","Bujía", new BigDecimal(30.45), new BigDecimal(1.5),5);
	}
	private Articulo getArticuloCorrecto() {
		return new Articulo("X-1","Bujía", new BigDecimal(30.45), new BigDecimal(50.5),5);
	}

	private void mostrarErrores(Errors errores) {
		System.out.println("=== Errores encontrados en " + errores.getObjectName() + " ===");
		errores.getAllErrors()
		       .forEach(err -> System.out.println(err.getCode() + ":" + err.getDefaultMessage()));
	}
	
}
