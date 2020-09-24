package mx.com.qtx.test;

import org.springframework.stereotype.Component;

import mx.com.qtx.torneo.IArbitro;

public class ArbitroDummy implements IArbitro {

	@Override
	public String getNombre() {
		return "Arbitro Dummy ";
	}

	@Override
	public int getId() {
		return 0;
	}

}
