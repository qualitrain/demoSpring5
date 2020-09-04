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
@Profile("desarrollo | pruebas")
public class ConfiguracionTorneoDesarrollo {
	@Bean
	@Primary
	@Scope("prototype")
	public ArbitroBDMemoria proponerArbitro() {
		System.out.println("ConfiguracionTorneoDesarrollo.proponerArbitro()");
		return ArbitroBDMemoria.getArbitros().get( 0 );
	}
	
	@Bean(name = "arbitroDummy")
	public ArbitroBDMemoria proponerArbitro2() {
		System.out.println("ConfiguracionTorneoDesarrollo.proponerArbitro2()");
		return ArbitroBDMemoria.getArbitros().get( 1 );
	}
	
	@Bean
	@Qualifier("propuestos")
	public List<IArbitro> proponerArbitros(){
		return new ArrayList<>(ArbitroBDMemoria.getArbitros());
	}
	
	@Bean
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
	public IEstrategiaEnfrentamientos getEstrategiaEnfrentamientos(){
		return new EstrategiaEnfrentamientosTest();
	}
}
