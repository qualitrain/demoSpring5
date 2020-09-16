package mx.com.qtx.test.aop;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TestCalculadora {
	
	@Autowired
	Calculadora calculadora;

	@Test
	public void testCalculadora(){
		int resultado = calculadora.calcularSuma(10,20,30,40,50);
		System.out.println(resultado);
		resultado = calculadora.calcularMultiplicacion(5,10,7,2,3,4,5);
		System.out.println(resultado);
	}
}
