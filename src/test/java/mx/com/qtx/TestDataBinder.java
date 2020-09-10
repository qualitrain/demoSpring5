package mx.com.qtx;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.core.convert.ConversionService;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.validation.Validator;

@SpringBootTest
public class TestDataBinder {
//	@Test
	public void testDataBinder(){
		Mascota mascota = new Mascota();
		
		DataBinder mascotaBinder = new DataBinder(mascota);
		mascotaBinder.setValidator(new ValidadorMascota());
		
		MutablePropertyValues propiedadesMascota = new MutablePropertyValues();
		propiedadesMascota.add("nombre", "   P   ");
		propiedadesMascota.add("peso", "200");
		
		mascotaBinder.bind(propiedadesMascota);
		mascotaBinder.validate();
		
		BindingResult resultadoBinding = mascotaBinder.getBindingResult();
		explorarResultadoBinding(resultadoBinding);
	
		this.explorarDataBinding(mascotaBinder);
	}
	private void explorarResultadoBinding(BindingResult resBinding) {
		if(resBinding.hasErrors()) {
			System.out.println("Hubo " + resBinding.getErrorCount()
					+ " errores en el binding");
			List<ObjectError> errores = resBinding.getAllErrors();
			for(ObjectError errorI:errores) {
				explorarError(errorI);
			}
			return;
		}
		System.out.println("Binding exitoso");
		Object target = resBinding.getTarget();
		System.out.println("Target:" + target.getClass().getName());
		System.out.println(target.toString());
	}
	
	public void explorarDataBinding(DataBinder binder) {
		System.out.println("\n====== Explorando DataBinder: ======");
		System.out.println("Object Name:" + binder.getObjectName());
		System.out.println("Target: " + binder.getTarget());
		Validator validador = binder.getValidator();
		if(validador == null)
			System.out.println("Validador:No hay validador en el DataBinder");
		else
		    System.out.println("Validador:" + validador.getClass().getName());
		ConversionService servicioConversion = binder.getConversionService();
		if(servicioConversion == null)
			System.out.println("Servicio Conversión:No hay servicio de conversión en el DataBinder");
		else
		    System.out.println("Servicio Conversión:" + servicioConversion.getClass().getName());
	}
	
	public void explorarError(ObjectError error) {
		System.out.println("\n  " + error);
		System.out.println("  Clase Objeto Error :" + error.getClass().getName());
		System.out.println("  Code :" + error.getCode());
		System.out.println("  Default Message:" + error.getDefaultMessage());
		System.out.println("  Object Name:" + error.getObjectName());
		System.out.println("  Codes:" + Arrays.toString( error.getCodes() ) );
		if(error instanceof FieldError) {
			FieldError errorCampo = (FieldError) error;
			System.out.println("  Campo:" + errorCampo.getField());
			System.out.println("  Rejected Value:" + errorCampo.getRejectedValue());
		}
		if(error.contains(Exception.class)) {
			Exception ex = error.unwrap(Exception.class);
			System.out.println("  Exception:" + ex.getClass().getName());
			Throwable causa = ex.getCause();
			if(causa != null) {
				System.out.println("  Causa:" + causa.getClass().getName());
				System.out.println("  Mensaje causa:" + causa.getMessage());
			}
		}
		explorarArgumentosError(error);
	}
	private void explorarArgumentosError(ObjectError error) {
		Object[] argumentos = error.getArguments();
		if(argumentos != null) {
			System.out.println("  Argumentos del error ("
					+ argumentos.length + ") :");
			for(Object argI:argumentos) {
				if(argI instanceof DefaultMessageSourceResolvable) {
					explorarMessageSourceResolvable((DefaultMessageSourceResolvable)argI);
				}
				else
				System.out.println(argI);
			}
		}
	}
	private void explorarMessageSourceResolvable(DefaultMessageSourceResolvable argI) {
		System.out.println("\tCode:" + argI.getCode());
		if(argI.getArguments() != null){
			Object[] argumentos = argI.getArguments();
			System.out.println("\tTiene " + argumentos.length + " sub-argumentos");
		}
		System.out.println("\tdefaultMessage: "+ argI.getDefaultMessage());

	}

}
