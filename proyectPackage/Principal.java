package proyectPackage;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Principal {

	//ventanaCrearCuenta
	JLabel label1, label2, label3, label4;
	JTextField txtNombre, txtEmail, txtEdad, txtContrasena;
	JSpinner edadSpinner;
	JLabel blank1, blank2, blank3, blank4, blank5, blank6, blank7;
	JButton botonSiguiente;
	
	//ventanaInicioSesion
		
	public void CambiarPanel(JPanel g, JPanel h) {
		g.setVisible(false);
		g.setEnabled(false);
		h.setVisible(true);
		h.setEnabled(true);
		for (Component ventanaCrearCuenta : g.getComponents() ){
			ventanaCrearCuenta.setEnabled(false);
			ventanaCrearCuenta.setVisible(false);
		}
		for (Component sp : h.getComponents() ){
	        sp.setEnabled(true);
	        sp.setVisible(true);
		}
	}
	
	public void CrearPanel(JPanel g) {
		g.setLayout(null);
		g.setVisible(false);
		g.setEnabled(false);
		g.setBounds(0, 0, 1000, 500);
	}
	
	public Principal() {
		
		JFrame frame = new JFrame();
		frame.setVisible(true);	
		frame.setSize(1000, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel ventanaCrearCuenta = new JPanel();
		ventanaCrearCuenta.setLayout(new GridLayout(8,2));
		ventanaCrearCuenta.setName("Crear Cuenta en MODISE");
		
		JPanel ventanaInicioSesion = new JPanel();
		ventanaInicioSesion.setLayout(new GridLayout(4, 3));
		ventanaInicioSesion.setName("Welcome to MODISE");
				
		CrearPanel(ventanaCrearCuenta);
		CrearPanel(ventanaInicioSesion);
				
		frame.getContentPane().add(ventanaCrearCuenta);
		frame.getContentPane().add(ventanaInicioSesion);		
		
		ventanaCrearCuenta.setVisible(true); //primera ventana que aparece??
		
		//componentes ventanaCrearCuenta
		label1 = new JLabel("   Nombre: ");
		ventanaCrearCuenta.add(label1);
		txtNombre = new JTextField();
		ventanaCrearCuenta.add(txtNombre);
		
		blank1 = new JLabel(" ");
		blank2 = new JLabel(" ");
		ventanaCrearCuenta.add(blank1);
		ventanaCrearCuenta.add(blank2);
		
		label2 = new JLabel("   Email: ");
		ventanaCrearCuenta.add(label2);
		txtEmail = new JTextField();
		ventanaCrearCuenta.add(txtEmail);
		
		blank3 = new JLabel(" ");
		blank4 = new JLabel(" ");
		ventanaCrearCuenta.add(blank3);
		ventanaCrearCuenta.add(blank4);
		
		label3 = new JLabel("   Edad: ");
		ventanaCrearCuenta.add(label3);
			//Recordar que la edad no puede ser menor de 12a�os!
		edadSpinner = new JSpinner();
		edadSpinner.setValue(12);
		ventanaCrearCuenta.add(edadSpinner);
		
		blank5 = new JLabel(" ");
		blank6 = new JLabel(" ");
		ventanaCrearCuenta.add(blank5);
		ventanaCrearCuenta.add(blank6);
		
		label4 = new JLabel("   Contrase�a: ");
		ventanaCrearCuenta.add(label4);
		txtContrasena = new JTextField();
		ventanaCrearCuenta.add(txtContrasena);
		
		blank7 = new JLabel(" ");
		ventanaCrearCuenta.add(blank7);
		botonSiguiente = new JButton("Siguiente");
		ventanaCrearCuenta.add(botonSiguiente);
		
		//componentes ventanaInicioSesion
		
		/*
		botonSiguiente.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				CambiarPanel(ventanaCrearCuenta, ventanaInicioSesion);	//por probar
			}
		});*/
				
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				new Principal();

			}

		});
	}

}
