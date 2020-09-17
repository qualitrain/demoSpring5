package mx.com.qtx.test.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class ExploradorPointcutsAOP {
	public ExploradorPointcutsAOP() {
		System.out.println("***** ExploradorPointcutsAOP instanciado *****");
	}

//	@Before("this(simulador) && execution(void setValor(int)) ")
	@Before("target(simulador) && execution(void setValor(int)) ")
	public void explorarSetter(JoinPoint pc, SimuladorOperaciones simulador){
		System.out.println("***** ExploradorPointcutsAOP.explorarSetter(" + simulador + ") *****");
		simulador.setCadena("Modificado por AOP");
		simulador.setValor(1); // NO SE CICLA porque no se intercepta ya que NO ES EL PROXY
		                       // Si usamos this en vez de target en la spec del pointcut SI SE CICLA
	}
	@Before("target(simulador) && execution(void setValor(int)) &&  args(valor)")
	public void explorarSetter02(JoinPoint pc, SimuladorOperaciones simulador, int valor){
		System.out.println("***** ExploradorPointcutsAOP.explorarSetter02(" 
				+ simulador + ", " + valor
						+ ") *****");
		valor++;
		simulador.setCadena("Modificado por AOP 02");
	}
	
	@Pointcut("target(simulador) && execution(int getValor()) ")
	private void getValorEnSimulador(SimuladorOperaciones simulador) {}
	
	@After("getValorEnSimulador(simulador)")
	public void explorarGetter(JoinPoint pc, SimuladorOperaciones simulador){
		System.out.println("***** ExploradorPointcutsAOP.explorarGetter(" 
				+ simulador + ") *****");
	}
}
