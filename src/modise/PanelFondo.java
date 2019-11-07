package modise;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class PanelFondo extends JPanel {

	private static final long serialVersionUID = 1L;
	ImageIcon imagenInicio;

	public PanelFondo() {
		super();
		this.imagenInicio = new ImageIcon(this.getClass().getClassLoader().getResource("modise/fondo.jpg"));
	}

	public void paintComponent(Graphics g) {
		Image bufferImage = this.createImage(this.getSize().width, this.getSize().height);
		Graphics bufferGraphics = bufferImage.getGraphics();
		bufferGraphics.drawImage(imagenInicio.getImage(), 0, -18, 720, 460, null);
		g.drawImage(bufferImage, 0, 0, this);
	}
}
