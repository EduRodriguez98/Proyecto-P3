package PruebasYEjemplos;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Properties;

public class EjemploProperties {
	public static void main(String[] args) throws IOException {
		Properties prop = new Properties();
		InputStream is = null;

		try {
			is = new FileInputStream("/Proyecto-P3/config.properties");
			prop.load(is);
		} catch (IOException e) {
			System.out.println(e.toString());
		}

		// Acceder a las propiedades por su nombre
		System.out.println("Propiedades por nombre:");
		System.out.println("-----------------------");
		System.out.println(prop.getProperty("servidor.nombre"));
		System.out.println(prop.getProperty("servidor.password"));
		System.out.println(prop.getProperty("servidor.usuario"));

		// Recorrer todas sin conocer los nombres de las propiedades
		System.out.println("Recorrer todas las propiedades:");
		System.out.println("-------------------------------");

		for (Enumeration e = prop.keys(); e.hasMoreElements();) {
			// Obtenemos el objeto
			Object obj = e.nextElement();
			System.out.println(obj + ": " + prop.getProperty(obj.toString()));
		}
	}
}