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
import org.springframework.context.annotation.Scope;

import mx.com.qtx.servicio.IArbitro;
import mx.com.qtx.servicio.IEstrategiaEnfrentamientos;
import mx.com.qtx.test.ArbitroDummy;
import mx.com.qtx.test.ArbitroTest;
import mx.com.qtx.test.EstrategiaEnfrentamientosTest;

@Configuration
public class ConfiguracionTorneo {
	
	@Bean
	@Primary
	@Scope("prototype")
	public ArbitroTest proponerArbitro() {
		return new ArbitroTest();
	}
	@Bean(name = "arbitroDummy")
	public ArbitroDummy proponerArbitro2() {
		return new ArbitroDummy();
	}
	
	@Bean
	@Scope("prototype")
	@Qualifier("propuestos")
	public List<IArbitro> proponerArbitros(){
		List<IArbitro> listArbitros = new ArrayList<>();
		for(int i=0; i<5; i++)
		   listArbitros.add(new ArbitroTest());
		return listArbitros;
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
	@Description(value = "Mecánica de armado de partidos")
	public IEstrategiaEnfrentamientos getEstrategiaEnfrentamientos(){
		return new EstrategiaEnfrentamientosTest();
	}
}
