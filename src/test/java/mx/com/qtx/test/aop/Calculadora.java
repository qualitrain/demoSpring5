package mx.com.qtx.test.aop;

import java.util.Arrays;

import org.springframework.stereotype.Component;

@Component
public class Calculadora {
	public Calculadora() {
		System.out.println("Calculadora instanciada");
	}
	
	public int calcularSuma(Integer... nums) {
		System.out.println("===== Calculadora.calcularSuma(" + Arrays.toString(nums) + ") =====");
		return Arrays.asList(nums).stream().reduce((a,b)->a+b).get();
	}
	public int calcularMultiplicacion(Integer... nums) {
		System.out.println("===== Calculadora.calcularMultiplicacion(" + Arrays.toString(nums) + ") =====");
		return Arrays.asList(nums).stream().reduce((a,b)->a*b).get();
	}
}
