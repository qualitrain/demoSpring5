package mx.com.qtx.test.aop;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TestSimulador {
	@Autowired
	private SimuladorOperaciones simulador;
	
	@Test
	public void testSimulador01() {
		simulador.setValor(500);
		System.out.println(simulador);
		int x = simulador.getValor();
		System.out.println("x=" + x);
	}
}
