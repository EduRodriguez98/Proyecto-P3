package modise;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class TextFileCleaner {

	public static void main(String[] args) throws FileNotFoundException {

		File f1 = new File("Feedback.log"); // elegir fichero ej: .log ,...
		File f2 = new File("Usuario.log");
		File f3 = new File("pruebaLogger.xml");

		PrintWriter writer = new PrintWriter(f2);
		writer.print("");
		writer.close();

	}

}
