package mx.com.qtx;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.support.DefaultConversionService;

@SpringBootTest
public class TestAPIConversionService {

	@Test
	public void testConversion() throws Exception{
		System.out.println("===== Probando ConversionService con DefaultConversionService =====");
		
		ConversionService convertidor = new DefaultConversionService();
		
		System.out.println("\n==== De String a int ====");
		int num = 0;
		num = convertidor.convert("1500", Integer.class);
		System.out.println("num=" + num);
		
		System.out.println("\n==== De String a double ====");
		double doble = 0.0;
		doble = convertidor.convert("3.141592654", Double.class);
		System.out.println("doble=" + doble);
		
		System.out.println("\n==== De String a List<Integer> ====");
		List<Integer> listInts = convertidor.convert("5,2,4,5", List.class);
		System.out.println("listInts=" + listInts);
		
		System.out.println("\n==== De String a Date y java.sql.Date ====");
		Date fecha = convertidor.convert("1991/01/11", Date.class);
		System.out.println("fecha=" + fecha);
		java.sql.Date fecha2 = convertidor.convert("2011-05-31", java.sql.Date.class);
		System.out.println("fecha2=" + fecha2);
		
		System.out.println("\n==== De String a Integer[] ====");
		Integer[] arrInts = convertidor.convert("25,23,12,199", Integer[].class);
		System.out.println("arrInts=" + Arrays.toString(arrInts));
		
		System.out.println("\n==== De List<Integer> a List<String> ====");
		List<String> lstCadenas = (List<String>) convertidor.convert(listInts, 
				             TypeDescriptor.forObject(listInts),
				             TypeDescriptor.collection(List.class, TypeDescriptor.valueOf(String.class)));
		System.out.println("lstCadenas=" + lstCadenas);
	}
}
