package modise;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import conexion.BaseDatosModise;

public class FileChooser {

	public static void Choose() {
		String userDir = System.getProperty("user.home");
		JFileChooser chooser = new JFileChooser(userDir + "/Pictures");
		FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG & GIF Images", "jpg",
				"gif", "*.png", "png"); 
		
		chooser.setFileFilter(filter);
		int returnVal = chooser.showOpenDialog(null);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			System.out.println("Has elejido abrir este archivo: " + chooser.getSelectedFile().getName());

			final String path = chooser.getSelectedFile().getPath();
			System.out.println("Path: " + path);

			 Principal.Usuariolog.println("Archivo de prenda añadida: " + chooser.getSelectedFile().getName());
		}

		// Nuevo para pruebas
		if (chooser.getSelectedFile().renameTo(new File(userDir
				+ "\\git\\Proyecto-P3\\src\\PruebasYEjemplos\\imgPruebas\\" + chooser.getSelectedFile().getName()))) {
			chooser.getSelectedFile().delete();
			System.out.println("El archivo se ha movido correctamente");
		} else {
			System.out.println("No se ha podido mover el archivo");
		}

		// BD
		Connection conexion = BaseDatosModise.conectar();
		Statement st = null;
		try {
			st = conexion.createStatement();
		} catch (SQLException e1) {

		}

		BaseDatosModise.subirFoto(st, chooser.getSelectedFile());

	}

	public static void main(String[] args) {
		Choose();
	}
	// Hasta aqui lo de las pruebas
}