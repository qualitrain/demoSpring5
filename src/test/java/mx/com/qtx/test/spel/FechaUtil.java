package mx.com.qtx.test.spel;

import java.util.Date;
import java.util.GregorianCalendar;

public class FechaUtil {
	public static Date getFecha(int aaaa,int mm,int dd) {
		GregorianCalendar cal = new GregorianCalendar(aaaa,mm -1,dd);
		return cal.getTime();
	}

}
