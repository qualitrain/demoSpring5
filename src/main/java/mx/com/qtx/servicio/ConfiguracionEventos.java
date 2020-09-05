package mx.com.qtx.servicio;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfiguracionEventos {
	@Bean("monitorTorneo")
	MonitorTorneo publicarMonitorTorneo() {
		return new MonitorTorneo();
	}

}
