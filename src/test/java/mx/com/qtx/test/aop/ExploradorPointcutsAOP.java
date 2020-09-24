package mx.com.qtx.test.aop;

import java.lang.reflect.Field;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class ExploradorPointcutsAOP {
	private int n=0;
	
	public ExploradorPointcutsAOP() {
		System.out.println("***** ExploradorPointcutsAOP instanciado *****");
	}

//	@Before("this(simulador) && execution(void setValor(int)) ")
	@Before("target(simulador) && execution(void setValor(int)) ")
	public void explorarAntesDeSetter(JoinPoint pc, SimuladorOperaciones simulador){
		n++;
		System.out.println("***** " + n
				+ ".- ExploradorPointcutsAOP.explorarAntesDeSetter(" + simulador + ") *****");
		simulador.setCadena("Modificado por AOP");
		simulador.setValor(1); // NO SE CICLA porque no se intercepta ya que NO ES EL PROXY
		                       // Si usamos this en vez de target en la spec del pointcut SI SE CICLA
	}
	
	@Before("target(simulador) && execution(void setValor(int)) &&  args(valor)")
	public void explorarAntesSetter02(JoinPoint pc, SimuladorOperaciones simulador, int valor){
		n++;
		System.out.println("***** " + n
				+ ".- ExploradorPointcutsAOP.explorarAntesSetter02(" 
				+ simulador + ", " + valor
						+ ") *****");
		valor++;
		simulador.setCadena("Modificado por AOP 02");
	}
	
	@Pointcut("target(simulador) && execution(int getValor()) ")
	private void getValorEnSimulador(SimuladorOperaciones simulador) {}
	
	@After("getValorEnSimulador(simulador)")
	public void explorarTrasGetter(JoinPoint pc, SimuladorOperaciones simulador){
		n++;
		System.out.println("***** " + n
				+ ".- ExploradorPointcutsAOP.explorarTrasGetter(" 
				+ simulador + ") *****");
	}
	
	@AfterReturning(pointcut = "execution(String SimuladorOperaciones.borrarCadena())", returning = "resul")
	public void explorarAfterReturning(String resul) {
		n++;
		System.out.println("***** " + n
				+ ".- ExploradorPointcutsAOP.explorarAfterReturning(" 
				+ "):" + resul
				+ " *****");
	}
	
	@AfterThrowing(pointcut = "execution(String SimuladorOperaciones.borrarCadena())", throwing = "ex")
	public void explorarAfterThrowing(Exception ex) {
		n++;
		System.out.println("***** " + n
				+ ".- ExploradorPointcutsAOP.explorarAfterThrowing(" 
				+ "):" + ex.getMessage()
				+ " *****");
	}
	@After(value = "execution(String SimuladorOperaciones.borrarCadena())")
	public void explorarAfter_Finally() {
		n++;
		System.out.println("***** " + n
				+ ".- ExploradorPointcutsAOP.explorarAfter_Finally(" 
				+ ")"
				+ " *****");
	}
	
	@Around("target(SimuladorOperaciones) && execution(String getCadena()) ")
	private String explorarAround(ProceedingJoinPoint pjp) {
		n++;
		System.out.println("***** " + n
				+ ".- ExploradorPointcutsAOP.explorarAround(" 
				+ ")"
				+ " *****");
		try {
			String cad = (String) pjp.proceed(); //invoca al método
			if(cad == null)
				return "cadena nula";
			else
		    if(cad.trim().isEmpty())
		    	return "cadena vacía";
		    else
		    	return cad;
		} catch (Throwable e) {
			return e.getMessage();
		}
	}
	
	@Around("execution(String get*(int)) && @annotation(desbordable)" )
	private String getElemArrSinDesborde(ProceedingJoinPoint pjp, Desbordable desbordable) {
		String nomArr = desbordable.arr();
		Object target = pjp.getTarget();
		int idxBuscado =(Integer) pjp.getArgs()[0];
		
		n++;
		System.out.println("***** " + n
				+ ".- ExploradorPointcutsAOP.getElemArrSinDesborde(" + nomArr + "[" + idxBuscado + "])"
				+ " *****");
		try {
	        Field fArr = target.getClass().getDeclaredField(nomArr);
	        fArr.setAccessible(true);
	        
	        String[] arr = (String[]) fArr.get(target);
	        int idxMax = arr.length - 1;
	        
	        if (idxMax < 0) //Si el arreglo está vacío devuelve una cadena vcía
	        	return "";
	        if(idxBuscado > idxMax) // Si se busca un elemento que desborda el arreglo, se devuelve el último elem
	        	return arr[idxMax];
	        
			String cad = (String) pjp.proceed(); //Se invoca al método con certeza de que el idx usado no desbordará el arreglo
		    return cad;
		} 
		catch (Throwable e) {
			return "error:" + e.getClass().getName() + "->" +  e.getMessage();
		}
	}
}
