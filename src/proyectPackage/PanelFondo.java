package proyectPackage;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class PanelFondo extends JPanel{
	
	private static final long serialVersionUID = 1L;
	ImageIcon imagenInicio; 
	String nombre;
	
	public PanelFondo() {
		super();
		this.nombre = " ";
		this.imagenInicio= new ImageIcon(this.getClass().getClassLoader().getResource("proyectPackage/modise.png"));
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
		bufferGraphics.drawImage(imagenInicio.getImage(), 0, -23, 720, 480, null);
		g.drawImage(bufferImage, 0, 0, this);
	}
}
