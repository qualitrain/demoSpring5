package mx.com.qtx.test.validacion;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.validation.metadata.ConstraintDescriptor;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static mx.com.qtx.test.validacion.ArticulosTest.*;

@SpringBootTest
public class TestValidadorBeanValidation {
	@Autowired
	private Validator validador;

//	@Test
	public void testInyeccionValidador(){
		System.out.println("\n***** testInyeccionValidador() *****");
		if(validador == null) {
			System.out.println("No se inyectó validador");
			return;
		}
		else
			System.out.println("Se inyectó un validador " + validador.getClass().getName());
		
	}
	@Test
	public void testValidadorDeclarativo(){
		System.out.println("\n***** testValidadorDeclarativo() *****");
//		Articulo articulo = getArticuloVacio();
//		Articulo articulo = getArticuloConErrores();
//		Articulo articulo = getArticuloConErrores2();
//		Articulo articulo = getArticuloConErrores3();
//		Articulo articulo = getArticuloConErrores4();
//		Articulo articulo = getArticuloConErrores5();
		Articulo articulo = getArticuloCorrecto();
		Set<ConstraintViolation<Articulo>> errores = validador.validate(articulo);
		if(errores.isEmpty()) {
			System.out.println("Artículo correctamente inicializado " + articulo);
		}
		else
			mostrarErroresResumen(errores);
	}
	private void mostrarErroresExtendido(Set<ConstraintViolation<Articulo>> errores) {
		System.out.println("Se encontraron " + errores.size() + " validaciones fallidas\n");
		for(ConstraintViolation<Articulo> errArtI:errores) {
			System.out.println("Property Path:" + errArtI.getPropertyPath().toString() );
			System.out.println("Mensaje:" + errArtI.getMessage());
			System.out.println("Message Template:" + errArtI.getMessageTemplate());
			System.out.println("valor inválido:" + errArtI.getInvalidValue());
			
			Object leafBean = errArtI.getLeafBean();
			if(leafBean == null)
				System.out.println("Leaf Bean: Null" );
			else
			   System.out.println("Leaf Bean:" + leafBean + " [" + leafBean.getClass().getName() + "]");
			
			Object erv = errArtI.getExecutableReturnValue();
			if(erv == null)
				System.out.println("Executable Return Value: Null" );
			else
			   System.out.println("Executable Return Value:" + erv + " [" + erv.getClass().getName() + "]");
			ConstraintDescriptor<?> cd = errArtI.getConstraintDescriptor();
			System.out.println("Constraint Descriptor:" + cd.getClass().getName());
			System.out.println();
		}
	}
	private void mostrarErroresResumen(Set<ConstraintViolation<Articulo>> errores) {
		System.out.println("Se encontraron " + errores.size() + " validaciones fallidas\n");
		for(ConstraintViolation<Articulo> errArtI:errores) {
			System.out.println("Property Path:" + errArtI.getPropertyPath().toString() );
			System.out.println("Mensaje:" + errArtI.getMessage());
			System.out.println();
		}
	}
	
}
