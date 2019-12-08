package modise;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class FileChooser {

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

			final String path = chooser.getSelectedFile().getPath();
			System.out.println("Path: " + path);

			Principal.Usuariolog.println("Archivo de prenda añadida: " + chooser.getSelectedFile().getName());
		}

		// Nuevo para pruebas
		if (chooser.getSelectedFile().renameTo(new File(userDir
				+ "\\git\\Proyecto-P3\\src\\PruebasYEjemplos\\imgPruebas\\" + chooser.getSelectedFile().getName()))) {
			chooser.getSelectedFile().delete();
			System.out.println("File moved successfully");
		} else {
			System.out.println("Failed to move the file");
		} // Hasta aqui lo de las pruebas
	}
}