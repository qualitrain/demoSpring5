package mx.com.qtx.test.aop;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MonitoreoAOP {

	public MonitoreoAOP() {
		System.out.println("MonitoreoAOP instanciado");
	}
		
	@Before("execution(* mx.com.qtx.test.aop.Calculadora.calcular*(..)) and args(nums)")
	public void interceptarExec(JoinPoint jp, Integer... nums) {
		System.out.println("\n***** MonitoreoAOP.interceptarExec() *****");
//		exporarJoinPoint(jp);
		System.out.println("Parámetros entrada:" + Arrays.toString(nums));
		
		nums[0] =999; //Alterando primer parámetro
		System.out.println("Parámetros salida:" + Arrays.toString(nums));
		
		System.out.println("******************************************");
	}

	private void exporarJoinPoint(JoinPoint jp) {
		System.out.println("\nJoinPoint:");
		Object[] args = jp.getArgs();
		System.out.println("   args.length: " + args.length);
		for(Object argI:args) {
			System.out.println("   args[i].class.simpleName: " + argI.getClass().getSimpleName());
			if(argI instanceof Integer[]) {
				Integer[] numeros = (Integer[]) argI;
				System.out.println("   args[i]: "+ Arrays.toString(numeros));
			}
		}
		System.out.println("   kind: " + jp.getKind());
		System.out.println("   toLongString: " + jp.toLongString());
		System.out.println("   toShortString: " + jp.toShortString());
		System.out.println("   toString: " + jp.toString());
		System.out.println("   signature.declaringTypeName: " + jp.getSignature().getDeclaringTypeName());
		System.out.println("   signature.modifiers: " + jp.getSignature().getModifiers());
		System.out.println("   signature.name: " + jp.getSignature().getName());
		System.out.println("   signature.class.name: " + jp.getSignature().getClass().getName());
		System.out.println("   signature.declaringType.name: " + jp.getSignature().getDeclaringType().getName());
//		System.out.println("   sourceLocation.fileName: " + jp.getSourceLocation().getFileName()); // No soportada
//		System.out.println("   sourceLocation.line: " + jp.getSourceLocation().getLine()); // No soportada
		System.out.println("   sourceLocation.withinType.name: " + jp.getSourceLocation().getWithinType().getName());
		System.out.println("   staticPart.id: " + jp.getStaticPart().getId());
		System.out.println("   staticPart.kind: " + jp.getStaticPart().getKind());
		System.out.println("   staticPart.toLongString(): " + jp.getStaticPart().toLongString());
		System.out.println("   staticPart.toShortString(): " + jp.getStaticPart().toShortString());
		System.out.println("   staticPart.toString(): " + jp.getStaticPart().toString());
		System.out.println("   staticPart.signature.name: " + jp.getStaticPart().getSignature().getName());
		System.out.println("   target.class.name: " + jp.getTarget().getClass().getName());
		System.out.println("   this.class.name: " + jp.getThis().getClass().getName());
		System.out.println();
	}
}
