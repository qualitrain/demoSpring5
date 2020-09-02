package mx.com.qtx.test;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import mx.com.qtx.servicio.IArbitro;

@Qualifier("arbitroTemporal")
@Component
//("arbitroDummy")
public class ArbitroDummy implements IArbitro {

	@Override
	public String getNombre() {
		return "Arbitro Dummy";
	}

}
