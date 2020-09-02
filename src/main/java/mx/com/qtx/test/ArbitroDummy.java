package mx.com.qtx.test;

import org.springframework.stereotype.Component;

import mx.com.qtx.servicio.IArbitro;

@Component
public class ArbitroDummy implements IArbitro {

	@Override
	public String getNombre() {
		return "Arbitro Dummy";
	}

}
