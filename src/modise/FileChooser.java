package modise;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
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
			JOptionPane.showMessageDialog(chooser, "Archivo seleccionado: " + chooser.getSelectedFile().getName());

			Principal.Usuariolog.println("Archivo de prenda añadida: " + chooser.getSelectedFile().getName());
		}
	}
}