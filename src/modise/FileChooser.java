package modise;

import java.io.FileOutputStream;
import java.io.PrintStream;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class FileChooser {

	static PrintStream Usuariolog;

	public static void Choose() {
		String userDir = System.getProperty("user.home");
		JFileChooser chooser = new JFileChooser(userDir + "/Pictures");
		FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG & GIF Images", "jpg",
				"gif"); /*
						 * La opcion "JPG & GIF Images" seLecciona jpg y gif, IGUAL hay que poner
						 * tambien png y mas archivos... TAMBIEN hay una opcion que es "Todos los
						 * archivos
						 */
		chooser.setFileFilter(filter);
		int returnVal = chooser.showOpenDialog(null);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			System.out.println("You chose to open this file: " + chooser.getSelectedFile().getName());

			// esto asi esta bien?
			try {
				Usuariolog = new PrintStream(new FileOutputStream("Usuario.log", true));
			} catch (Exception e) {
			}
			Usuariolog.println("Archivo de prenda añadida: " + chooser.getSelectedFile().getName());

		}
	}

	/*
	 * public static void main(String[] args) { Choose(); }
	 */
}