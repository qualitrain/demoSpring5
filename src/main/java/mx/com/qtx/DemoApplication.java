package mx.com.qtx;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;

import mx.com.qtx.servicio.Partido;
import mx.com.qtx.servicio.Torneo;
import mx.com.qtx.test.EquipoFutbol;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner{
	@Autowired
	private ApplicationContext context;
	
	@Autowired
	private Torneo torneo;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		explorarContexto();
		System.out.println("-------------------------------------------------------------\n");
		TestTorneo();
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
		
		// Explorando los nombres de los beans en el contexto
		int nBeans = context.getBeanDefinitionCount();
		System.out.println("Hay " + nBeans + " beans en el contexto");
		
		int i=1;
		String[] nombresBeans = context.getBeanDefinitionNames();
		System.out.println("Beans: ================================");
		for(String nomBeanI : nombresBeans) {
			System.out.println(i + ".-"+ nomBeanI);
			System.out.println("Aliases:" + Arrays.toString(context.getAliases(nomBeanI)) );
			System.out.println("hashCode:" + String.format("%x", context.getBean(nomBeanI).hashCode() ));
			i++;
		}
		System.out.println("=======================================");
		
		//  Recuperando una propiedad del archivo application.properties
		Environment env = context.getEnvironment();
		String nombre = env.getProperty("nombre");
		System.out.println("Propiedad nombre=" + nombre);
		
		// Recuperando un bean del contexto
		DemoApplication miApp = context.getBean("demoApplication", DemoApplication.class);
		miApp.mostrarNombreApp();
	}
	public void mostrarNombreApp() {
		System.out.println("***** Esta aplicación se llama demoSpring5 *****");
	}
	private void TestTorneo(){
		EquipoFutbol ame = new EquipoFutbol("Las aves amarillas");
		ame.agregarJugador("Juan ", "Paco", "Jorge", "Lionel", "Boris", "Efrén",
			"Rafa", "Ramón", "Diego", "Edson", "Vladimir");
		EquipoFutbol gorriones = new EquipoFutbol("Gorriones Salvajes");
		gorriones.agregarJugador("Martín ", "Miguel", "Jorge", "Brian", "Carlos", "Eduardo",
			"Rafael", "Guille", "David", "Héctor", "Lenin");
		EquipoFutbol vampis = new EquipoFutbol("Vampiros");
		vampis.agregarJugador("Memo ", "Lalo", "George", "Conrado", "Donaldo", "Marcial",
			"Alex", "Gus", "Gastón", "Hilario", "Lucas");
		EquipoFutbol hormis = new EquipoFutbol("Hormigas atómicas");
		hormis.agregarJugador("Joaquín", "Jesús", "Víctor", "Daniel", "Efrén", "Gonzalo",
			"Adalberto", "Osvaldo", "Martín", "Cristobal", "Hugo");
		torneo.agregarEquipo(ame);
		torneo.agregarEquipo(gorriones);
		torneo.agregarEquipo(vampis);
		torneo.agregarEquipo(hormis);
		
		torneo.generarPartidas();
		torneo.mostrarPartidas();
		
		torneo.mostrarArbitros();
	}
}
