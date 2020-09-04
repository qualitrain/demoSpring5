package mx.com.qtx;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.Scope;

import mx.com.qtx.servicio.IArbitro;
import mx.com.qtx.servicio.IEstrategiaEnfrentamientos;
import mx.com.qtx.test.ArbitroBDMemoria;
import mx.com.qtx.test.ArbitroDummy;
import mx.com.qtx.test.ArbitroTest;
import mx.com.qtx.test.EstrategiaEnfrentamientosTest;

@Configuration
public class ConfiguracionTorneo {
	
	@Bean
	@Profile("default")
	@Primary
	@Scope("prototype")
	public ArbitroTest proponerArbitro() {
		return new ArbitroTest();
	}
	@Bean
	@Profile("desarrollo | pruebas")
	@Primary
	@Scope("prototype")
	public ArbitroBDMemoria proponerArbitro3() {
		return ArbitroBDMemoria.getArbitros().get( 0 );
	}
	@Bean(name = "arbitroDummy")
	@Profile("default")
	public ArbitroDummy proponerArbitro2() {
		return new ArbitroDummy();
	}
	@Bean(name = "arbitroDummy")
	@Profile("desarrollo | pruebas")
	public ArbitroBDMemoria proponerArbitro4() {
		return ArbitroBDMemoria.getArbitros().get( 1 );
	}
	
	@Bean
	@Profile("default")
	@Scope("prototype")
	@Qualifier("propuestos")
	public List<IArbitro> proponerArbitros(){
		List<IArbitro> listArbitros = new ArrayList<>();
		for(int i=0; i<5; i++)
		   listArbitros.add(new ArbitroTest());
		return listArbitros;
	}
	@Bean
	@Profile("desarrollo | pruebas")
	@Qualifier("propuestos")
	public List<IArbitro> proponerArbitrosTest(){
		return new ArrayList<>(ArbitroBDMemoria.getArbitros());
	}
	
	@Bean
	@Profile("default|desarrollo | pruebas")
	@Qualifier("propuestos")
	public Map<Integer,IArbitro> getMapArbitros(
			                  @Qualifier("propuestos")
			                  List<IArbitro> arbitros){
		Map<Integer,IArbitro> mapArbitros = new HashMap<>();
		for(int i=0; i<arbitros.size(); i++) 
			mapArbitros.put((i+ 1), arbitros.get(i));
		return mapArbitros;
	}

	@Bean
	@Profile("default|desarrollo | pruebas")
	@Description(value = "Mecánica de armado de partidos")
	public IEstrategiaEnfrentamientos getEstrategiaEnfrentamientos(){
		return new EstrategiaEnfrentamientosTest();
	}
}
