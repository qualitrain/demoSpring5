package mx.com.qtx.test.spel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.SimpleEvaluationContext;

//@SpringBootTest
public class TestSpel {
	
//	@Test
	public void ejecutarPruebas() {
//		testParseoExpresionMinima();
//		testParseoExpresionConOperadorPunto01();
//		testRootObjectVsExprPropiedad();
//		testEvaluationContext();
//		testLiterales();
//		testInlineLists();
//		testInlineMaps();
//		testInlineArreglos();
//		testSeleccion();
//		testOperadorSafeNavigation();
		testProyeccion();
	}

	public void testParseoExpresionMinima(){
		System.out.println("\n === Invocación del Parser de expresiones ===");
		ExpressionParser parser = new SpelExpressionParser();
		Expression exp = parser.parseExpression("'Hola a todos! '"); 
		String mensaje = (String) exp.getValue();
		System.out.println(mensaje);
	}
	
	public void testParseoExpresionConOperadorPunto01(){
		System.out.println("\n === Dado un objeto, se invocan sus métodos ===");
		ExpressionParser parser = new SpelExpressionParser();
		
		Expression exp = parser.parseExpression("'hola'.toUpperCase().codePointAt(0)"); 
		int unicode  = (Integer) exp.getValue();
		System.out.println(unicode);

		exp = parser.parseExpression("'hola'.toUpperCase().codePointAt(1)"); 
		System.out.println("Dado un objeto, se extrae el valor de la expresión sin cast");
		unicode  = exp.getValue(Integer.class);
		System.out.println(unicode);
	}
	
	public void testRootObjectVsExprPropiedad(){
		System.out.println("\n === expresion vs objeto particular (root object) ===");
		ExpressionParser parser = new SpelExpressionParser();
		
		Perro firu = new Perro("Firualis","Husky Siberiano");
		
		// Invocando a un getter de un root object
		Expression exp = parser.parseExpression("raza"); 
		String raza = exp.getValue(firu,String.class);

		System.out.println("La raza del perro evaluado es " + raza);
	}
	public void testLiterales(){
		System.out.println("\n === Literales ===");
		ExpressionParser parser = new SpelExpressionParser();
		
		Expression exp = parser.parseExpression("'hola ''amiguitos'' '"); 
		System.out.println(exp.getValue(String.class));

		exp = parser.parseExpression("'hola \"amiguitos\" '"); 
		System.out.println(exp.getValue(String.class));
		
		exp = parser.parseExpression("2500.00");
		System.out.println(exp.getValue(Double.class));
		
		exp = parser.parseExpression("false");
		System.out.println(exp.getValue(Boolean.class));
	}
	
	public void testEvaluationContext() {
		System.out.println("\n === Evaluation Context ===");
		EvaluationContext contexto = getTestEvaluationContext();
		
		ExpressionParser parser = new SpelExpressionParser();
		
		Expression exp = parser.parseExpression("#Fido.raza"); 
		String raza = exp.getValue(contexto,String.class);
		System.out.println("La raza de Fido es " + raza);
		
		exp = parser.parseExpression("#Fido"); 
		Perro perris = exp.getValue(contexto,Perro.class);
		System.out.println("El perro es " + perris);
		
		perris.aplicarVacuna(new Vacuna("rabia",1), FechaUtil.getFecha(2020, 6, 25));
		
		exp = parser.parseExpression("#mascotas[0]"); 
		perris = exp.getValue(contexto,Perro.class);
		System.out.println("El perro es " + perris);
		
		Vacuna moquillo = new Vacuna("moquillo",2);
		perris.aplicarVacuna(moquillo, FechaUtil.getFecha(2020, 7, 10));
		perris.aplicarVacuna(moquillo, FechaUtil.getFecha(2020, 7, 17));
		
		exp = parser.parseExpression("#Fido.vacunas['rabia'].aplicaciones[0]"); 
		Date fecVacRabiaFido = exp.getValue(contexto,Date.class);
		System.out.println("El perro se vacunó en " + fecVacRabiaFido);
		
		exp = parser.parseExpression("#Fido.vacunas['rabia'].vacuna.numDosis"); 
		int nDosis = exp.getValue(contexto,Integer.class);
		System.out.println("Esa vacuna requiere  " + nDosis + " dosis");
		
		exp = parser.parseExpression("vacunas['moquillo'].aplicaciones.size()"); 
		int nAplicaciones = exp.getValue(perris,Integer.class);
		System.out.println("Aplicaciones Vacuna Moquillo: " + nAplicaciones);

		exp = parser.parseExpression("#Fido.raza"); 
		exp.setValue(contexto,"Dálmata");
		Perro fido = parser.parseExpression("#Fido").getValue(contexto, Perro.class);
		System.out.println("fido:" + fido);
		
	}

	private EvaluationContext getTestEvaluationContext() {
		List<Perro> mascotas = new ArrayList<>();
		mascotas.add(new Perro("Nubecita","French Poodle"));
		mascotas.add(new Perro("Campeón","Boxer"));
		mascotas.add(new Perro("Ralf","Pastor Inglés"));
		mascotas.add(new Perro("Peluzas","French Poodle"));
		mascotas.add(new Perro("Killer","Boxer"));
		
		Perro perris = mascotas.get(2);	
		Vacuna parvo = new Vacuna("parvovirus",2);
		perris.aplicarVacuna(parvo, FechaUtil.getFecha(2019, 12, 10));
		perris.aplicarVacuna(parvo, FechaUtil.getFecha(2020, 1, 17));
		Vacuna rabia = new Vacuna("rabia",3);
		perris.aplicarVacuna(rabia, FechaUtil.getFecha(2020, 2, 8));
		perris.aplicarVacuna(rabia, FechaUtil.getFecha(2020, 2, 19));
		perris.aplicarVacuna(rabia, FechaUtil.getFecha(2020, 2, 28));
		perris.setPropietario(new Persona("Juan Domingo Perales Díaz",'M',31));
		
		EvaluationContext contexto = SimpleEvaluationContext.forReadWriteDataBinding().build();
		contexto.setVariable("Fido", new Perro("Fido","Pitbull"));
		contexto.setVariable("ahora", new Date());
		contexto.setVariable("saludo", "Buenos días chamaquitos!!");
		contexto.setVariable("mascotas", mascotas);
		return contexto;
	}
	public void testInlineLists(){
		System.out.println("\n === Inline Lists ===");
		EvaluationContext contexto = getTestEvaluationContext();
		
		ExpressionParser parser = new SpelExpressionParser();
		
		// Este tipo de expresiones podrían ser útiles para inyectar listas constantes
		List<Integer> numeros = (List<Integer>) parser.parseExpression("{10,22,31,400}")
				                                      .getValue(contexto);
		System.out.println(numeros);
	}
	public void testInlineMaps(){
		System.out.println("\n === Inline Maps ===");
		EvaluationContext contexto = getTestEvaluationContext();
		
		ExpressionParser parser = new SpelExpressionParser();
		
		Expression exp = parser.parseExpression("{'Yucatán':800000, Oaxaca:765600, Chiapas:345900, 'Quintana Roo':567430 }");
		Map<String,Integer> poblaciones = (Map<String,Integer>) exp.getValue(contexto);
		System.out.println(poblaciones);
	}
	public void testInlineArreglos(){
		System.out.println("\n === Inline Arrays ===");
		EvaluationContext contexto = getTestEvaluationContext();
		
		ExpressionParser parser = new SpelExpressionParser();
		
		Expression exp = parser.parseExpression("new int[]{31,28,31,30,31,30,31,31,30,31,30,31}");
		int[] diasXmes = (int[]) exp.getValue(contexto);
		System.out.println(Arrays.toString(diasXmes));
	}
	public void testSeleccion() {
		System.out.println("\n === Seleccion ===");
		EvaluationContext contexto = getTestEvaluationContext();
		
		ExpressionParser parser = new SpelExpressionParser();
		Expression exp = parser.parseExpression("#mascotas.?[#this.raza=='Boxer']");
		List<Perro> boxers = exp.getValue(contexto, List.class );
		System.out.println(boxers);
	}
	public void testProyeccion() {
		System.out.println("\n === Proyeccion ===");
		EvaluationContext contexto = getTestEvaluationContext();
		
		ExpressionParser parser = new SpelExpressionParser();
		Expression exp = parser.parseExpression("#mascotas.![raza]");
		List<String> razas = exp.getValue(contexto, List.class );
		System.out.println(razas);
		
		exp = parser.parseExpression("#mascotas.?[propietario!=null].![propietario]");
		List<Persona> propietarios = exp.getValue(contexto, List.class );
		System.out.println(propietarios);
		
		exp = parser.parseExpression("#mascotas[2].vacunas.![key]");
		List<String> nomVacunas = exp.getValue(contexto, List.class );
		System.out.println(nomVacunas);
	}
	public void testOperadorSafeNavigation() {
		System.out.println("\n === Operador SafeNavigation ===");
		EvaluationContext contexto = getTestEvaluationContext();
		
		ExpressionParser parser = new SpelExpressionParser();
		
		// Si el propietario es null no genera excepción al intentar acceder a una de sus propiedades
		Expression exp = parser.parseExpression("#mascotas.?[#this.raza!='Labrador'][2].propietario?.nombre");
		String nombrePropietario = exp.getValue(contexto, String.class );
		System.out.println("propietario:" + nombrePropietario);
		
		exp = parser.parseExpression("#mascotas.?[#this.raza!='Labrador'][1].propietario?.nombre");
		nombrePropietario = exp.getValue(contexto, String.class );
		System.out.println("propietario:" + nombrePropietario);
	}
	
}
