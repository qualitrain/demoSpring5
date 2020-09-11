package mx.com.qtx.test.validacion;

import java.math.BigDecimal;

public class ArticulosTest {
	public static Articulo getArticuloVacio() {
		return new Articulo();
	}
	public static Articulo getArticuloConErrores() {
		return new Articulo("X","X",new BigDecimal(0.45), null,-1);
	}
	public static Articulo getArticuloConErrores2() {
		return new Articulo("X-1","X-1",new BigDecimal("0.45"), new BigDecimal(0),5);
	}
	public static Articulo getArticuloConErrores3() {
		return new Articulo("X-1","Bujía", new BigDecimal(1.45), new BigDecimal(1.5),5);
	}
	public static Articulo getArticuloConErrores4() {
		return new Articulo("X-1","Bujía", new BigDecimal(30.45), new BigDecimal(1.5),5);
	}
	public static Articulo getArticuloConErrores5() {
		return new Articulo(" X-1","Bujía ", new BigDecimal(30.45), new BigDecimal(1.5),5);
	}
	public static Articulo getArticuloCorrecto() {
		return new Articulo("X-1","Bujía", new BigDecimal(30.45), new BigDecimal(50.5),5);
	}

}
