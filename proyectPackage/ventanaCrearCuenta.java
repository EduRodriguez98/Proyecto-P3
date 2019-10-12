package proyectPackage;
import java.awt.*;
import javax.swing.*;

public class ventanaCrearCuenta extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	JLabel label1, label2, label3, label4;
	JTextField txtNombre, txtEmail, txtEdad, txtContrasena;
	JSpinner edadSpinner;
	JLabel blank1, blank2, blank3, blank4, blank5, blank6, blank7;
	JButton botonSiguiente;
	
	
	public ventanaCrearCuenta() {
		Container cp = this.getContentPane();
		cp.setLayout(new GridLayout(8,2));
		
		label1 = new JLabel("   Nombre: ");
		cp.add(label1);
		txtNombre = new JTextField();
		cp.add(txtNombre);
		
		blank1 = new JLabel(" ");
		blank2 = new JLabel(" ");
		cp.add(blank1);
		cp.add(blank2);
		
		label2 = new JLabel("   Email: ");
		cp.add(label2);
		txtEmail = new JTextField();
		cp.add(txtEmail);
		
		blank3 = new JLabel(" ");
		blank4 = new JLabel(" ");
		cp.add(blank3);
		cp.add(blank4);
		
		label3 = new JLabel("   Edad: ");
		cp.add(label3);
		//Recordar que la edad no puede ser menor de 12a�os!
		edadSpinner = new JSpinner();
		edadSpinner.setValue(12);
		cp.add(edadSpinner);
		
		blank5 = new JLabel(" ");
		blank6 = new JLabel(" ");
		cp.add(blank5);
		cp.add(blank6);
		
		label4 = new JLabel("   Contrase�a: ");
		cp.add(label4);
		txtContrasena = new JTextField();
		cp.add(txtContrasena);
		
		blank7 = new JLabel(" ");
		cp.add(blank7);
		botonSiguiente = new JButton("Siguiente");
		cp.add(botonSiguiente);
		
		this.setTitle("Crear Cuenta en MODISE");
		this.setVisible(true);
		this.setSize(450,250);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				new ventanaCrearCuenta();
				
			}
		});
	}

}
