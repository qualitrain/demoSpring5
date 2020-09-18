package mx.com.qtx.test.aop;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TestSimulador {
	@Autowired
	private SimuladorOperaciones simulador;
	@Autowired
	private Calculadora calculadora;
	
	@Test
	public void testSimulador01() {
		try {
			simulador.setValor(500);
			System.out.println(simulador);
			int x = simulador.getValor();
			System.out.println("x=" + x);
			String cadAnt = simulador.borrarCadena();
			System.out.println("Cadena borrada:" + cadAnt + ", simulador:" + simulador);
			cadAnt = simulador.borrarCadena();
			System.out.println("Cadena borrada:" + cadAnt + ", simulador:" + simulador);
		}
		catch(Exception ex) {
			System.out.println("Se presento la excepcion " + ex.getClass().getName());
			System.out.println("con mensaje:" + ex.getMessage());
		}
		System.out.println(simulador.getCadena());
		int nSala = 13;
		System.out.println("Sala " + nSala +": " + simulador.getSala(nSala));
		int nOper = 5;
		System.out.println("Operación " + nOper +": " + calculadora.getOperacion(nOper));
	}
}
