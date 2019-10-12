package proyectPackage;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import java.awt.*;
import javax.swing.*;

public class ventanaInicioSesion extends JFrame {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//Declarando componentes de la ventana
	JLabel label1, label2, label3, label4, label5, label6;
	JLabel blank1, blank2, blank3; //Utilizadas solo para meter espacios en blanco y estructurar ventana
	JTextField txtEmail, txtContraseña;
	JButton botonCrear, botonInicio;
	
	
	public ventanaInicioSesion() {

		Container cp = this.getContentPane();
		cp.setLayout(new GridLayout(4, 3));
		
		blank1 = new JLabel(" ");
		cp.add(blank1);
		label2 = new JLabel("Iniciar Sesión: ");
		cp.add(label2);
		blank2 = new JLabel(" ");
		cp.add(blank2);
		
		label3 = new JLabel("         Email ");
		cp.add(label3);
		txtEmail = new JTextField("example@gmail.com");
		cp.add(txtEmail);
		blank3 = new JLabel(" ");
		cp.add(blank3);
		
		label4 = new JLabel("         Contraseña ");
		cp.add(label4);
		txtContraseña = new JTextField("******");
		cp.add(txtContraseña);
		botonInicio = new JButton("Iniciar Sesión");
		cp.add(botonInicio);
		
	
		label5 = new JLabel("        Es tu primera vez?");
		cp.add(label5);
		label6 = new JLabel("Pulse este boton ->");
		cp.add(label6);
		botonCrear = new JButton("Crear Cuenta");
		cp.add(botonCrear);
		
		this.setVisible(true);
		this.setTitle("Wlecome to MODISE");
		this.setSize(450, 200);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {

		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				new ventanaInicioSesion();

			}
		});
	}

}
