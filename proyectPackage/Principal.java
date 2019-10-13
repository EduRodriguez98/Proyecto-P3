package proyectPackage;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class Principal {

	JLabel labelB;

	JLabel label1, label2, label3, label4, label5, label6;
	JLabel blank1, blank2, blank3; //Utilizadas solo para meter espacios en blanco y estructurar ventana
	JTextField txtEmail, txtContrasena;
	JButton botonCrear, botonInicio;
	
	public void CambiarPanel(JPanel g, JPanel h) {
		g.setVisible(false);
		g.setEnabled(false);
		h.setVisible(true);
		h.setEnabled(true);
		for (Component cp : g.getComponents() ){
			cp.setEnabled(false);
			cp.setVisible(false);
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
		g.setBounds(0, 0, 720, 480);
	}
	
	public Principal() {
		
		JFrame frame = new JFrame();
		frame.setVisible(true);	
		frame.setSize(720, 480);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Principal.java");
		
		JPanel ventanaInicioSesion = new JPanel();
		ventanaInicioSesion.setName("ventanaInicioSesion"); //no va??
		JPanel ventanaCrearCuenta = new JPanel();
		ventanaCrearCuenta.setName("ventanaCrearCuenta"); //no va??
		
		CrearPanel(ventanaInicioSesion);
		CrearPanel(ventanaCrearCuenta);
		
		frame.getContentPane().add(ventanaInicioSesion);
		frame.getContentPane().add(ventanaCrearCuenta);	
		
		ventanaInicioSesion.setVisible(true); //la primera ventana??
		
		//componentes ventanaInicioSesion
		botonCrear = new JButton("Crear Cuenta");
		ventanaInicioSesion.add(botonCrear);
		botonCrear.setBounds(100, 100, 100, 50);
		
		
		//componentes ventanaCrearCuenta
		labelB = new JLabel("labelB");
		ventanaCrearCuenta.add(labelB);
		labelB.setBounds(100, 100, 100, 50);
		
		
		//Action listeners
		botonCrear.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				CambiarPanel(ventanaInicioSesion, ventanaCrearCuenta);
			}
		});
		
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