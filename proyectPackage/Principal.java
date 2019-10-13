package proyectPackage;
import java.awt.Component;
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
		g.setBounds(0, 0, 1000, 500);
	}
	
	public Principal() {
		
		JFrame frame = new JFrame();
		frame.setVisible(true);	
		frame.setSize(1000, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Principal.java");
		
		JPanel ventanaInicioSesion = new JPanel();
		JPanel panelB = new JPanel();
		
		CrearPanel(ventanaInicioSesion);
		CrearPanel(panelB);
		
		frame.getContentPane().add(ventanaInicioSesion);
		frame.getContentPane().add(panelB);	
		
		ventanaInicioSesion.setVisible(true);
		
		blank1 = new JLabel(" ");
		ventanaInicioSesion.add(blank1);
		label2 = new JLabel("Iniciar Sesion: ");
		ventanaInicioSesion.add(label2);
		blank2 = new JLabel(" ");
		ventanaInicioSesion.add(blank2);
		
		label3 = new JLabel("         Email ");
		ventanaInicioSesion.add(label3);
		txtEmail = new JTextField("example@gmail.com");
		ventanaInicioSesion.add(txtEmail);
		blank3 = new JLabel(" ");
		ventanaInicioSesion.add(blank3);
		
		label4 = new JLabel("         Contrasena ");
		ventanaInicioSesion.add(label4);
		txtContrasena = new JTextField("******");
		ventanaInicioSesion.add(txtContrasena);
		botonInicio = new JButton("Iniciar Sesion");
		ventanaInicioSesion.add(botonInicio);
		
	
		label5 = new JLabel("        Es tu primera vez?");
		ventanaInicioSesion.add(label5);
		label6 = new JLabel("Pulse este boton ->");
		ventanaInicioSesion.add(label6);
		botonCrear = new JButton("Crear Cuenta");
		ventanaInicioSesion.add(botonCrear);
		
		labelB = new JLabel();
		labelB.setText("labelB");
		labelB.setBounds(100, 100, 100, 50);
				
		
		panelB.add(labelB);
		
		botonCrear.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				CambiarPanel(ventanaInicioSesion, panelB);
			}
		});
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				new EjemploPrincipal();

			}

		});
	}

}