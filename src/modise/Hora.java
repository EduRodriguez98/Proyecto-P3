package modise;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Hora {

	public static void hora() {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		System.out.println(sdf.format(cal.getTime()));
	}
}
