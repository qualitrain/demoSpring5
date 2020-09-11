package mx.com.qtx.test.validacion;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

import static mx.com.qtx.test.validacion.ArticulosTest.*;

@SpringBootTest
public class TestValidadorSpring5 {
	
	@Test
	public void testValidadorProgramatico(){
		System.out.println("\n***** testValidadorProgramatico() *****");
//		Articulo articulo = getArticuloVacio();
//		Articulo articulo = getArticuloConErrores();
//		Articulo articulo = getArticuloConErrores2();
//		Articulo articulo = getArticuloConErrores3();
//		Articulo articulo = getArticuloConErrores4();
//		Articulo articulo = getArticuloConErrores5();
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

	private void mostrarErrores(Errors errores) {
		System.out.println("=== " + errores.getErrorCount()
				+ " errores encontrados en " + errores.getObjectName() + " ===");
		errores.getAllErrors()
		       .forEach(err -> System.out.println(err.getCode() + ":" + err.getDefaultMessage()));
	}
	
}
