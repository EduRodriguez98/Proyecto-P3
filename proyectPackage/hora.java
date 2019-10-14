package proyectPackage;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class hora {
	
	public static void hora() {
		Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        System.out.println( sdf.format(cal.getTime()) );
	}
	
	
	/*
	public static void main(String[] args) {

		Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        System.out.println( sdf.format(cal.getTime()) );
	}
	*/
}
