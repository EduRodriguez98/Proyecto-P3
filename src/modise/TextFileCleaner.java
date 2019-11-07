package modise;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class TextFileCleaner {

	public static void main(String[] args) throws FileNotFoundException {

		File f = new File("Usuario.log"); // elegir fichero ej: .log , .xml ,...

		PrintWriter writer = new PrintWriter(f);
		writer.print("");
		writer.close();

	}

}
