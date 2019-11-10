package modise;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

public class EjemploPrincipal {

	JLabel labelA, labelB;
	JButton botonA;

	public void CambiarPanel(JPanel g, JPanel h) {
		g.setVisible(false);
		g.setEnabled(false);
		h.setVisible(true);
		h.setEnabled(true);
		for (Component cp : g.getComponents()) {
			cp.setEnabled(false);
			cp.setVisible(false);
		}
		for (Component sp : h.getComponents()) {
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

	public EjemploPrincipal() {

		JFrame frame = new JFrame();
		frame.setVisible(true);
		frame.setSize(1000, 500);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setTitle("EjemploPrincipal.java");

		JPanel panelA = new JPanel();
		JPanel panelB = new JPanel();

		CrearPanel(panelA);
		CrearPanel(panelB);

		frame.getContentPane().add(panelA);
		frame.getContentPane().add(panelB);

		panelA.setVisible(true);

		labelA = new JLabel();
		labelA.setText("labelA");
		labelA.setBounds(100, 100, 100, 50);

		botonA = new JButton();
		botonA.setText("botonA");
		botonA.setBounds(500, 300, 100, 50);

		labelB = new JLabel();
		labelB.setText("labelB");
		labelB.setBounds(100, 100, 100, 50);

		panelA.add(labelA);
		panelA.add(botonA);
		panelB.add(labelB);

		botonA.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				CambiarPanel(panelA, panelB);
			}
		});

	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				new EjemploPrincipal();

			}

		});
	}

}
