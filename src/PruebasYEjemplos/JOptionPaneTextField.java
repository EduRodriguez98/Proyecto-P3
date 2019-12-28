package PruebasYEjemplos;

import javax.swing.JOptionPane;

public class JOptionPaneTextField {

	public static void main(String[] args) {

		String input = JOptionPane.showInputDialog(null, "Nueva contraseña:", "Cambiar contraseña.", 2);

		System.out.println(input);

	}
}
