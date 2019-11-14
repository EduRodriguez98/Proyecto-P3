package modise;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class TextFileCleaner {

	public static void main(String[] args) throws FileNotFoundException {

		File f1 = new File("Usuario.log"); // elegir fichero ej: .log , .xml ,...
		File f2 = new File("BDLogger.xml");
		// ¡¡¡ NO limpies el Feedback.log !!!

		PrintWriter writer = new PrintWriter(f2);
		writer.print("");
		writer.close();

	}

}
