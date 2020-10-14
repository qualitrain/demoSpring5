package mx.com.qtx;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.env.Environment;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import mx.com.qtx.test.EquipoFutbol;

@SpringBootApplication
@EnableAspectJAutoProxy
@EnableJdbcRepositories
@EnableJpaRepositories
public class DemoApplication implements CommandLineRunner{
	@Autowired
	private ApplicationContext context;
	
	@Autowired
	private ITorneo torneo;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//		explorarContexto();
//		System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - - - - ");
//		TestTorneo();
	}
	private void explorarContexto(){
		String nomClaseCtx = context.getClass().getName();
		System.out.println("La clase de implementación es [" + nomClaseCtx + "]");
		String id = context.getId();
		System.out.println("Id:" + id);
		String displayName = context.getDisplayName();
		System.out.println("Display name:" + displayName);
		
		String appName = context.getApplicationName();
		System.out.println("Nombre de la aplicación:[" + appName + "]");
				
//		explorarBeans();
		
		explorarEnvironment();
		
		// Recuperando un bean del contexto
		DemoApplication miApp = context.getBean("demoApplication", DemoApplication.class);
		miApp.mostrarNombreApp();
	}

	private void explorarEnvironment() {
		//  Recuperando una propiedad del archivo application.properties
		Environment env = context.getEnvironment();
		String nombre = env.getProperty("nombre");
		System.out.println("Propiedad nombre=" + nombre);
		String[] perfilesActivos = env.getActiveProfiles();
		System.out.println("Perfiles activos (" + perfilesActivos.length
				+ ") :" + Arrays.toString(perfilesActivos));
	}

	private void explorarBeans() {
		// Explorando los nombres de los beans en el contexto
		int nBeans = context.getBeanDefinitionCount();
		System.out.println("Hay " + nBeans + " beans en el contexto");
		
		int i=1;
		String[] nombresBeans = context.getBeanDefinitionNames();
		System.out.println("Beans: ================================");
		for(String nomBeanI : nombresBeans) {
			System.out.println(i + ".-"+ nomBeanI);
			System.out.println("Aliases:" + Arrays.toString(context.getAliases(nomBeanI)) );
			System.out.println("Tipo:" + context.getType(nomBeanI).getName());
			System.out.println("hashCode:" + String.format("%x", context.getBean(nomBeanI).hashCode() ) + "\n");
			i++;
		}
		System.out.println("=======================================");
	}
	public void mostrarNombreApp() {
		System.out.println("***** Esta aplicación se llama demoSpring5 *****");
	}
	private void TestTorneo(){
//		torneo.mostrarBienvenida();
//		torneo.mostrarDatosGenerales();
//		torneo.mostrarArbitros();
		
//		testPartidas();

//		torneo.testInserciones();
//		torneo.mostrarJugadores();
		
//		torneo.testActualizaciones();
//		torneo.testEliminaciones();
		torneo.testConsultas();
	}

	private void testPartidas() {
		EquipoFutbol ame = new EquipoFutbol("Las aves amarillas");
		ame.agregarJugador("Juan ", "Paco", "Jorge", "Lionel", "Boris", "Efrén",
			"Rafa", "Ramón", "Diego", "Edson", "Vladimir");
		EquipoFutbol gorriones = new EquipoFutbol("Gorriones Salvajes");
		gorriones.agregarJugador("Martín ", "Miguel", "Jorge", "Brian", "Carlos", "Eduardo",
			"Rafael", "Guille", "David", "Héctor", "Lenin");
		EquipoFutbol vampis = new EquipoFutbol("Vampiros");
		torneo.getJugadores("Vampiros")
		      .forEach(jug -> vampis.agregarJugador(jug));
		EquipoFutbol hormis = new EquipoFutbol("Hormigas atómicas");
		torneo.getJugadores("Hormigas atómicas")
	          .forEach(jug -> hormis.agregarJugador(jug));
		
		hormis.agregarJugador("Joaquín", "Jesús", "Víctor", "Daniel", "Efrén", "Gonzalo",
			"Adalberto", "Osvaldo", "Martín", "Cristobal", "Hugo");
		torneo.agregarEquipo(ame);
		torneo.agregarEquipo(gorriones);
		torneo.agregarEquipo(vampis);
		torneo.agregarEquipo(hormis);
		
		torneo.generarPartidas();
		torneo.mostrarPartidas();
	}
}
