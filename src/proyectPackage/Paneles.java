package proyectPackage;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class Paneles extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	ImageIcon imagenInicio; 
	String nombre;
	Font fuente;
	
	public Paneles() {
		super();
		this.nombre = " ";
		this.imagenInicio= new ImageIcon(this.getClass().getClassLoader().getResource("proyectPackage/jorge.jpg"));
		
		/*InputStream cogerFuente;
		 try {
	          cogerFuente = this.getClass().getResource("Stingray.otf").openStream();
	       }
	       catch (IOException e1) {
	           e1.printStackTrace();
	           return;
	       }
		 try {
	           this.fuente = Font.createFont(0, cogerFuente);
	       }
	       catch (FontFormatException | IOException e) {
	           return;
	       }*/
		 
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void paintComponent(Graphics g) {
		Image bufferImage = this.createImage(this.getSize().width, this.getSize().height);
		Graphics bufferGraphics = bufferImage.getGraphics();
		bufferGraphics.drawImage(imagenInicio.getImage(), 0, 0, 1080, 720, null);
		g.drawImage(bufferImage, 0, 0, this);

	}
	
	

	
}
