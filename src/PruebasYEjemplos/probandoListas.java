package PruebasYEjemplos;


import java.awt.*;
import java.util.ArrayList;

import clases.Prendas;
import clases.Color;
import javax.swing.*;

public class probandoListas extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	JScrollPane scrollpane;
	JLabel scrolllabel;

	public probandoListas() {
	
		Container cp = this.getContentPane();
		cp.setLayout(new GridLayout (1,2));
	
		
		
		//Creo defaultlistmodel y la jlist
		DefaultListModel<Prendas> modeloLista = new DefaultListModel<Prendas>();
		JList<Prendas> listaprendas = new JList<Prendas>(modeloLista);
	
		//Creo Colores
		Color c1 = new Color(1, "Black");
		Color c2 = new Color(2, "Red");
		Color c3 = new Color(3, "Blue");
		
		//Creo prendas
		Prendas p1 = new Prendas(c1, false, 50, 50 );
		Prendas p2 = new Prendas(c2, true, 70, 0 );
		Prendas p3 = new Prendas(c3, false, 20, 100 );
		Prendas p4 = new Prendas(c2, false, 30, 30 );
		Prendas p5 = new Prendas(c3, false, 57, 8 );
		
		//Creo un array de prendas
		ArrayList<Prendas> arrayPrendas = new ArrayList<Prendas>();
		arrayPrendas.add(p1);
		arrayPrendas.add(p2);
		arrayPrendas.add(p3);
		arrayPrendas.add(p4);
		arrayPrendas.add(p5);
		
		for (Prendas p : arrayPrendas) {
			modeloLista.addElement(p);
		}
		
		
		//ScrollPanel donde metere la lista de personas
		scrolllabel = new JLabel("aqui esta el scrollpane: ");
		scrollpane = new JScrollPane(listaprendas);
		
		
		//Anyadiendo componentes a ventana y etc
		cp.add(scrolllabel);
		cp.add(scrollpane);
		
		
		this.setSize(400,400);
		this.setTitle("ventanaPruebaJlist");
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setVisible(true);
	}
	
	
	public static void main(String[] args) {
		
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				new probandoListas();
				
			}
		});

	}

}
