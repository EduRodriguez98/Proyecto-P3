package proyectPackage;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class ventanaPerfilGustosUno extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JLabel blank1, blank2, blank3;
	JButton botonSiguiente, botonSiguienteP2, botonAtrasP2;
	JCheckBox clasicoF, clasicoM, urbanaF, urbanaM, rockF, rockM, bohoF, smartM, formalF, formalM, sportyChickF, casualChickM;
	
	JLabel escogeSexo;
	JRadioButton Masculino, Femenino;
	public ventanaPerfilGustosUno() {
		
		Container cp = this.getContentPane();
		cp.setLayout(new GridLayout(2,1));
	
		//Primera ventana
		JPanel panelGenero = new JPanel(new GridLayout(1,2));
		escogeSexo = new JLabel("Que genero eres?: ");
		panelGenero.add(escogeSexo);
		
		Masculino = new JRadioButton("Masculino");
		Femenino = new JRadioButton("Femenino");
		ButtonGroup bg = new ButtonGroup();
		bg.add(Masculino);
		bg.add(Femenino);
		panelGenero.add(Masculino);
		panelGenero.add(Femenino);
		
		JPanel panelSiguiente = new JPanel(new GridLayout(1,3));
		blank1 = new JLabel(" ");
		panelSiguiente.add(blank1);
		blank2 = new JLabel(" ");
		panelSiguiente.add(blank2);
		botonSiguiente = new JButton("Siguiente");
		panelSiguiente.add(botonSiguiente);		
		
		JPanel panelSiguienteYAtras = new JPanel(new GridLayout(1,3));
		blank3 = new JLabel(" ");
		botonAtrasP2 = new JButton("Atras");
		botonSiguienteP2 = new JButton("Siguiente");

		panelSiguienteYAtras.add(botonAtrasP2); //el P2 significa de la pagina 2!
		panelSiguienteYAtras.add(botonSiguienteP2);
		cp.add(panelGenero);
		cp.add(panelSiguiente);
		
		JPanel panelMasculino = new JPanel(new GridLayout(3,2));
		clasicoM = new JCheckBox("Clasico");
		panelMasculino.add(clasicoM);
		urbanaM = new JCheckBox("Urbana");
		panelMasculino.add(urbanaM);
		rockM = new JCheckBox("Rock");
		panelMasculino.add(rockM);
		smartM = new JCheckBox("Smart");
		panelMasculino.add(smartM);
		formalM = new JCheckBox("Formal");
		panelMasculino.add(formalM);
		casualChickM = new JCheckBox("Casual Chick");
		panelMasculino.add(casualChickM); 
		
		JPanel panelFemenino = new JPanel(new GridLayout(3,2));
		clasicoF = new JCheckBox("Clasico");
		panelFemenino.add(clasicoF);
		urbanaF = new JCheckBox("Urbana");
		panelFemenino.add(urbanaF);
		rockF = new JCheckBox("Rock");
		panelFemenino.add(rockF);
		bohoF = new JCheckBox("Boho");
		panelFemenino.add(bohoF);
		formalF = new JCheckBox("Formal");
		panelFemenino.add(formalF);
		sportyChickF = new JCheckBox("Sporty Chick");
		panelFemenino.add(sportyChickF);
		
		botonSiguiente.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if (Masculino.isSelected()) {
					cp.remove(panelGenero);
					panelGenero.setVisible(false);
					cp.remove(panelSiguiente);
					panelSiguiente.setVisible(false);
					cp.add(panelMasculino);
					panelMasculino.setVisible(true);
					cp.add(panelSiguienteYAtras);
					panelSiguienteYAtras.setVisible(true);
					
				} else if (Femenino.isSelected()) {
					cp.remove(panelGenero);
					panelGenero.setVisible(false);
					cp.remove(panelSiguiente);
					panelSiguiente.setVisible(false);
					cp.add(panelFemenino);
					panelFemenino.setVisible(true);
					cp.add(panelSiguienteYAtras);
					panelSiguienteYAtras.setVisible(true);
					
				} else {
					//Dialogo diciendo que se necesita seleccionar un genero para continuar
				}
				
				
			}
		});
		
		botonSiguienteP2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(panelFemenino.isVisible() && clasicoF.isSelected() || urbanaF.isSelected() || rockF.isSelected() || bohoF.isSelected() || formalF.isSelected() || sportyChickF.isSelected()) {
					cp.remove(panelFemenino);
					panelFemenino.setVisible(false);
					cp.remove(panelSiguienteYAtras);
					panelSiguienteYAtras.setVisible(false);
					//add new pannel
				} else if (panelMasculino.isVisible() && clasicoM.isSelected() || urbanaM.isSelected() || rockM.isSelected() || smartM.isSelected() || formalM.isSelected() || casualChickM.isSelected()){
					cp.remove(panelMasculino);
					panelMasculino.setVisible(false);
					cp.remove(panelSiguienteYAtras);
					panelSiguienteYAtras.setVisible(false);
				} else {
					//dialogo indicando al usuario que se debe seleccionar al menos un estilo para continuar
				}
				
			}
		});
		
		botonAtrasP2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(panelMasculino.isVisible()) {
					cp.remove(panelMasculino);
					panelMasculino.setVisible(false);
					cp.remove(panelSiguienteYAtras);
					panelSiguienteYAtras.setVisible(false);
					cp.add(panelGenero);
					panelGenero.setVisible(true);
					cp.add(panelSiguiente);
					panelSiguiente.setVisible(true);
				} else {
					cp.remove(panelFemenino);
					panelFemenino.setVisible(false);
					cp.remove(panelSiguienteYAtras);
					panelSiguienteYAtras.setVisible(false);
					cp.add(panelGenero);
					panelGenero.setVisible(true);
					cp.add(panelSiguiente);
					panelSiguiente.setVisible(true);
				}
				
			}
		});
		
		this.pack();
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setTitle("MODISE - perfil de gustos");
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				new ventanaPerfilGustosUno();
				
			}
		});

	}

}
