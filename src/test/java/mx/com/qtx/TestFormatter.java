package mx.com.qtx;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.datetime.DateFormatter;

@SpringBootTest
public class TestFormatter {
	@Test
	public void testDateFormatter() {
		DateFormatter df = new DateFormatter();
		df.setIso(DateTimeFormat.ISO.DATE);
		df.setTimeZone(TimeZone.getDefault());
		String cadFecha = "    2021-2-11    ";
		testParseoEimpresionFecha(df, cadFecha);
		
		df = new DateFormatter();
		df.setPattern("dd/MM/yyyy");
		df.setTimeZone(TimeZone.getDefault());
		cadFecha = "    29/2/2012    ";
		testParseoEimpresionFecha(df, cadFecha);
		
		df = new DateFormatter();
		df.setStyle(DateFormat.SHORT);
		df.setTimeZone(TimeZone.getDefault());
		cadFecha = "    25/12/2018    ";
		testParseoEimpresionFecha(df, cadFecha);
			
		df = new DateFormatter();
		df.setStylePattern("LS");
		df.setTimeZone(TimeZone.getDefault());
//		System.out.println( df.print(new Date(), Locale.getDefault()) );
		cadFecha = "    18 de enero de 2020 08:25 PM    ";
		testParseoEimpresionFecha(df, cadFecha);
		
		df = new DateFormatter();
		df.setStylePattern("MM");
		df.setTimeZone(TimeZone.getDefault());
//		System.out.println( df.print(new Date(), Locale.getDefault()) );
		cadFecha = "    18/09/2020 08:29:32 PM    ";
		testParseoEimpresionFecha(df, cadFecha);
		
		df = new DateFormatter();
		df.setStylePattern("FF");
		df.setTimeZone(TimeZone.getDefault());
		System.out.println( df.print(new Date(), Locale.getDefault()) );
		cadFecha = "martes 8 de septiembre de 2020 08:31:20 PM CDT";
		testParseoEimpresionFecha(df, cadFecha);
	}

	private void testParseoEimpresionFecha(DateFormatter df, String cadFecha) {
		System.out.println("----- testParseoEimpresionFecha -----");
		try {
			Date fechaDate = df.parse(cadFecha, Locale.getDefault());
			System.out.println("fecha(formato java.util.Date):" + fechaDate);
			System.out.println("fecha(formateado MX):" + df.print(fechaDate, Locale.getDefault()));
			System.out.println("fecha(formateado EEUU):" + df.print(fechaDate, Locale.US));
		}
		catch (ParseException pex) {
			System.out.println("La cadena no coincide con el formato ");
			System.out.println(pex.getMessage());
		}
	}

}
