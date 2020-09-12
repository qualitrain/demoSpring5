package mx.com.qtx.test.validacion;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;

import static mx.com.qtx.test.validacion.ArticulosTest.*;

import java.util.Locale;

@SpringBootTest
public class TestValidadorSpring5 {
	@Autowired
	private MessageSource fteTextos;
	@Autowired
	private Locale localidad;
	
//	@Test
	public void testInyeccionMessageSource() {
		System.out.println("\n***** testInyeccionMessageSource() *****");
		if(this.fteTextos == null)
			System.out.println("No se inyectó el MessageSource");
		else 
			System.out.println("MessageSource inyectado correctamente");
	}
	@Test
	public void testValidadorProgramatico(){
		System.out.println("\n***** testValidadorProgramatico() *****");
//		Articulo articulo = getArticuloVacio();
//		Articulo articulo = getArticuloConErrores();
		Articulo articulo = getArticuloConErrores2();
//		Articulo articulo = getArticuloConErrores3();
//		Articulo articulo = getArticuloConErrores4();
//		Articulo articulo = getArticuloConErrores5();
//		Articulo articulo = getArticuloCorrecto();
		ValidadorArticulo validador = new ValidadorArticulo();
		Errors errores = new BeanPropertyBindingResult(articulo,"articulo");
		validador.validate(articulo, errores );
		if(errores.hasErrors())
			mostrarErroresMS(errores);
		else
			System.out.println("Artículo correctamente inicializado " + articulo);
	}

	private void mostrarErrores(Errors errores) {
		System.out.println("=== " + errores.getErrorCount()
				+ " errores encontrados en " + errores.getObjectName() + " ===");
		errores.getAllErrors()
		       .forEach(err -> System.out.println(err.getCode() + ":" + err.getDefaultMessage()));
	}
	private void mostrarErroresMS(Errors errores) {
		System.out.println("=== " + errores.getErrorCount()
				+ " errores encontrados en " + errores.getObjectName() + " ===");
		for(ObjectError err:errores.getAllErrors()) {
			System.out.println(err.getCode() + ": " 
					+ this.fteTextos.getMessage(err.getCode(), null, this.localidad));			
		}
	}
}
