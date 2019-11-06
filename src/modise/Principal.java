package modise;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Logger;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JProgressBar;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

//import com.sun.java.util.jar.pack.Package.File;

public class Principal {

	// Metodo Cambiar Paneles
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

	// Metodo Crear Paneles
	public void CrearPanel(JPanel g) {
		g.setLayout(null);
		g.setVisible(false);
		g.setEnabled(false);
		g.setBounds(0, 0, 720, 480);
		// g.setBackground(Color.GRAY); //color de todos los paneles (NO de las ventanas
		// emergentes), a no ser que queramos cambiar alguno
	}

	// ORDENES DE LAS VENTANAS!!!!!
	// (CASO DE PRIMERA VEZ EN MODISE) 1.Ventana Inicio Sesion || 2.Ventana Crear
	// Cuenta || 3.Ventana Genero || 4.Ventana Perfil Gustos 1 || 5.Ventana Perfil
	// Gustos 2 || || 6.Ventana de Carga || 7.Ventana Menu Principal
	// (CASO USUARIO YA REGISTRADO) 1.Ventana Inicio Sesion || 2.Ventana de Carga ||
	// 3.Ventana Menu Principal
	// Declarando componentes
	// VentanaInicioSesion
	JLabel labelBrand, labelEmail, labelContrasenya, labelPregunta;
	JTextField txtEmail, txtContrasenya;
	JButton botonCrear, botonInicio;
	JPasswordField contrasenya;
	JCheckBox view;
	boolean escrito1, escrito2;

	// VentanaCrearCuenta
	JLabel labelCrearNombre, labelCrearEmail, labelCrearContrasenya, labelCrearEdad, errorNombre, errorEmail,
			errorContrasenya;
	JTextField txtCrearNombre, txtCrearEmail, txtCrearContrasenya;
	JSpinner spinCrearEdad;
	SpinnerModel model;
	JButton botonCrearSiguiente, botonCrearAtras;
	boolean escrito3, escrito4;

	// VentanaGenero
	JRadioButton radioMasculino, radioFemenino;
	JLabel labelEscogerGenero, errorGenero;
	JButton botonGeneroSiguiente, botonGeneroAtras;

	// VentanaPerfilGustosUnoM
	JButton botonPerfilGustosUnoMSiguiente, botonPerfilGustosUnoMAtras;
	JCheckBox clasicoM, urbanaM, rockM, smartM, formalM, casualChickM;
	JLabel errorPerfilGustosUnoM;

	// VentanaPerfilGustosUnoF
	JButton botonPerfilGustosUnoFSiguiente, botonPerfilGustosUnoFAtras;
	JCheckBox clasicoF, urbanaF, rockF, bohoF, formalF, sportyChickF;
	JLabel errorPerfilGustosUnoF;

	// VentanaPerfilGustosUno
	String g1 = "";
	String g7 = "";
	String g2 = "";
	String g8 = "";
	String g3 = "";
	String g9 = "";
	String g4 = "";
	String g10 = "";
	String g5 = "";
	String g11 = "";
	String g6 = "";
	String g12 = "";

	// VentanaPerfilGustos2
	JLabel labelEscoge, errorPerfilGustosDos;
	JButton botonPerfilGustosDosAtras, botonPerfilGustosDosSiguiente;
	JRadioButton radioPrendaIzq, radioPrendaDer;

	// VentanaCarga
	JProgressBar progressCargando;
	JLabel labelCargando;
	private int counter = 0;
	private boolean stop = false;
	private final int MAX_STEPS = 100000;

	// ventanaMenuPrincipal
	JMenuBar mb;
	JMenu menu1;
	JMenuItem mi1, mi2, mi3; // mi1 = cerrar sesion
	JButton botonPideOutfit, botonAnyadirVestimenta, botonMasMenosAdmin;

	// ventanaAnyadirVestimenta
	JRadioButton sol, lluvia, nublado;
	JLabel estilosLabelAnyadirVestimenta, colorLabelAnyadirVestimenta, tiempoLabelAnyadirvestimenta,
			errorVentanaAnyadirVestimenta;
	boolean escrito5;
	JButton ventanaAnyadirVestimentaAtras, ventanaAnyadirVestimentaAnyadir;

	JComboBox<String> estilosComboBoxAnyadirVestimenta;

	JComboBox<String> coloresComboBoxAnyadirVestimenta;

	// ventanaPideOutfit
	JButton botonAtrasPideOutfit, botonBuscar;
	JRadioButton radioSol, radioLluvia, radioNublado, radioNo;
	JLabel preguntaEstilo, preguntaTiempo, errorPideOutfit;
	JComboBox<String> estilosComboBoxPideOutfit;

	// ventanaFeedback
	JLabel nivelSatisfaccion, gustoColores, errorFeedback;
	JRadioButton estrella1, estrella2, estrella3, estrella4, estrella5, si, no;
	JButton botonInicioFeedback;

	// Ajustes
	JButton cambiarContrasenya, cambiarFecha, reiniciarPerfil;

	// ventanaEmergenteOutfit

	// ventanaProx
	JLabel prox;

	// mas
	static PrintStream Feedbacklog, Usuariolog;
	static Logger logger;

	public Principal() {

		// Propiedades y componentes del Frame
		JFrame frame = new JFrame();
		frame.setVisible(true);
		frame.setSize(720, 480);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Principal.java");
		frame.setResizable(false);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation(dim.width / 2 - frame.getSize().width / 2, dim.height / 2 - frame.getSize().height / 2);
		frame.setIconImage(new ImageIcon(getClass().getResource("modise1.png")).getImage()); // Icono de frame
		// para el color haced " ctrl + F " y buscad " g.setBackground "

		mb = new JMenuBar();
		menu1 = new JMenu("Menu");
		mi1 = new JMenuItem("Cerrar sesion");
		mi2 = new JMenuItem("Ajustes");
		mi3 = new JMenuItem("mi3 - ¿Algo mas?");
		menu1.add(mi1);
		menu1.add(mi2);
		menu1.add(mi3);
		mb.add(menu1);
		frame.setJMenuBar(mb);

		// Imagen de fondo
		ImageIcon imagenInicio = new ImageIcon(this.getClass().getClassLoader().getResource("modise/fondo.jpg"));

		JPanel ventanaMenuPrincipal = new JPanel() {
			private static final long serialVersionUID = 1L;

			public void paintComponent(Graphics g) {
				Image bufferImage = this.createImage(this.getSize().width, this.getSize().height);
				Graphics bufferGraphics = bufferImage.getGraphics();

				// probando para ver si arreglamos lo de la imagen descentrada de fondo, en mi
				// MAC hace lo que le da la gana los graficos :) pero probadlo en windows a ver
				// como sale la ventana.
				if (mb.isVisible() && mb.isEnabled()) {
					bufferGraphics.drawImage(imagenInicio.getImage(), 0, -3, 720, 440, null);
				} else {
					bufferGraphics.drawImage(imagenInicio.getImage(), 0, -23, 720, 440, null);
				}

				bufferGraphics.drawImage(imagenInicio.getImage(), 0, -23, 720, 440, null);

				g.drawImage(bufferImage, 0, 0, this);
			}
		};

		PanelFondo ventanaInicioSesion = new PanelFondo();
		PanelFondo ventanaCrearCuenta = new PanelFondo();
		PanelFondo ventanaGenero = new PanelFondo();
		PanelFondo ventanaPerfilGustosUnoM = new PanelFondo();
		PanelFondo ventanaPerfilGustosUnoF = new PanelFondo();
		PanelFondo ventanaPerfilGustosDos = new PanelFondo();
		PanelFondo ventanaCarga = new PanelFondo();
		PanelFondo ventanaAnyadirVestimenta = new PanelFondo();
		PanelFondo ventanaPideOutfit = new PanelFondo();
		PanelFondo ventanaFeedback = new PanelFondo();
		JPanel ventanaProx = new JPanel(); // dejadla asi

		// ventanas Emergentes
		JPanel ajustes = new JPanel(new GridLayout(3, 1));
		JPanel ventanaEmergenteOutfit = new JPanel();

		CrearPanel(ventanaInicioSesion);
		CrearPanel(ventanaCrearCuenta);
		CrearPanel(ventanaGenero);
		CrearPanel(ventanaPerfilGustosUnoM);
		CrearPanel(ventanaPerfilGustosUnoF);
		CrearPanel(ventanaPerfilGustosDos);
		CrearPanel(ventanaMenuPrincipal);
		CrearPanel(ventanaCarga);
		CrearPanel(ventanaAnyadirVestimenta);
		CrearPanel(ventanaPideOutfit);
		CrearPanel(ventanaFeedback);
		CrearPanel(ventanaProx);

		frame.getContentPane().add(ventanaInicioSesion);
		frame.getContentPane().add(ventanaCrearCuenta);
		frame.getContentPane().add(ventanaGenero);
		frame.getContentPane().add(ventanaPerfilGustosUnoM);
		frame.getContentPane().add(ventanaPerfilGustosUnoF);
		frame.getContentPane().add(ventanaPerfilGustosDos);
		frame.getContentPane().add(ventanaMenuPrincipal);
		frame.getContentPane().add(ventanaCarga);
		frame.getContentPane().add(ventanaAnyadirVestimenta);
		frame.getContentPane().add(ventanaPideOutfit);
		frame.getContentPane().add(ventanaFeedback);
		frame.getContentPane().add(ventanaProx);

		ventanaInicioSesion.setVisible(true); // la primera ventana visible

		// Anyadiendo los componentes de ventanaInicioSesion
		labelEmail = new JLabel("Email: ");
		labelEmail.setFont(new Font("Monospace", Font.BOLD, 13));
		ventanaInicioSesion.add(labelEmail);
		labelEmail.setBounds(60, 62, 100, 40);

		txtEmail = new JTextField("ejemplo@gmail.com");
		ventanaInicioSesion.add(txtEmail);
		txtEmail.setBounds(160, 70, 300, 30);
		escrito1 = false;
		txtEmail.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (escrito1 == false) {
					txtEmail.setText("");
					escrito1 = true;
				}
			}
		});

		labelContrasenya = new JLabel("Contrasena: ");
		labelContrasenya.setFont(new Font("Monospace", Font.BOLD, 13));
		ventanaInicioSesion.add(labelContrasenya);
		labelContrasenya.setBounds(60, 133, 100, 40);

		contrasenya = new JPasswordField("12345"); // cambiado
		contrasenya.setEchoChar('*'); // hacer checkbox isSelected para ver contraseña, HECHO
		ventanaInicioSesion.add(contrasenya);
		contrasenya.setBounds(160, 140, 300, 30);
		escrito2 = false;
		contrasenya.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (escrito2 == false) {
					contrasenya.setText("");
					escrito2 = true;
				}
			}
		});

		botonInicio = new JButton("Iniciar Sesion");
		botonInicio.setFont(new Font("Monospace", Font.BOLD, 12));
		ventanaInicioSesion.add(botonInicio);
		botonInicio.setBounds(500, 140, 200, 30);

		botonCrear = new JButton("Crear Cuenta");
		ventanaInicioSesion.add(botonCrear);
		botonCrear.setBounds(500, 300, 200, 30);
		botonCrear.setFont(new Font("Monospace", Font.BOLD, 12));

		view = new JCheckBox("Visualizar contraseña");
		view.setFont(new Font("Monospace", Font.PLAIN, 12));
		ventanaInicioSesion.add(view);
		view.setBounds(160, 190, 150, 30);

		labelPregunta = new JLabel("Es tu primera vez en Modise? Pulsa el boton Crear Cuenta para empezar!");
		labelPregunta.setFont(new Font("Monospace", Font.PLAIN, 12));
		ventanaInicioSesion.add(labelPregunta);
		labelPregunta.setBounds(60, 295, 450, 40);

		mb.setVisible(false);
		mb.setEnabled(false);

		// Action listeners
		botonCrear.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				CambiarPanel(ventanaInicioSesion, ventanaCrearCuenta);

			}
		});

		botonInicio.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				CambiarPanel(ventanaInicioSesion, ventanaMenuPrincipal);
				Usuariolog.println("Inicio de sesion: " + txtEmail.getText() + " " + (new Date()));

				mb.setVisible(true);
				mb.setEnabled(true);
			}
		});

		view.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (view.isSelected() == true) {
					contrasenya.setEchoChar((char) 0);
				} else {
					contrasenya.setEchoChar('*');
				}
			}
		});

		// Anyadiendo los componentes de ventanaCrearCuenta
		labelCrearNombre = new JLabel("Introduzca su nombre: ");
		labelCrearNombre.setFont(new Font("Monospace", Font.BOLD, 13));
		ventanaCrearCuenta.add(labelCrearNombre);
		labelCrearNombre.setBounds(80, 50, 200, 30);

		txtCrearNombre = new JTextField("nombre");
		ventanaCrearCuenta.add(txtCrearNombre);
		txtCrearNombre.setBounds(250, 50, 300, 30);
		escrito3 = false;
		txtCrearNombre.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (escrito3 == false) {
					txtCrearNombre.setText("");
					escrito3 = true;
				}
			}
		});

		labelCrearEmail = new JLabel("Introduzca su email: ");
		labelCrearEmail.setFont(new Font("Monospace", Font.BOLD, 13));
		ventanaCrearCuenta.add(labelCrearEmail);
		labelCrearEmail.setBounds(80, 125, 200, 30);

		txtCrearEmail = new JTextField("ejemplo@gmail.com");
		ventanaCrearCuenta.add(txtCrearEmail);
		txtCrearEmail.setBounds(250, 125, 300, 30);
		escrito4 = false;
		txtCrearEmail.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (escrito4 == false) {
					txtCrearEmail.setText("");
					escrito4 = true;
				}
			}
		});

		labelCrearContrasenya = new JLabel("Cree una contrasena: ");
		labelCrearContrasenya.setFont(new Font("Monospace", Font.BOLD, 13));
		ventanaCrearCuenta.add(labelCrearContrasenya);
		labelCrearContrasenya.setBounds(80, 200, 200, 30);

		txtCrearContrasenya = new JTextField("");
		ventanaCrearCuenta.add(txtCrearContrasenya);
		txtCrearContrasenya.setBounds(250, 200, 300, 30);

		labelCrearEdad = new JLabel("Seleccione su edad: ");
		labelCrearEdad.setFont(new Font("Monospace", Font.BOLD, 13));
		ventanaCrearCuenta.add(labelCrearEdad);
		labelCrearEdad.setBounds(80, 275, 200, 30);

		SpinnerModel model = new SpinnerNumberModel(18, 0, 99, 1); // default 18, min 0, max 99, +-1
		// spinCrearEdad.setValue(18);
		spinCrearEdad = new JSpinner(model);
		ventanaCrearCuenta.add(spinCrearEdad);
		spinCrearEdad.setBounds(250, 275, 80, 30);

		botonCrearSiguiente = new JButton("Siguiente");
		ventanaCrearCuenta.add(botonCrearSiguiente);
		botonCrearSiguiente.setBounds(500, 340, 200, 30);

		botonCrearAtras = new JButton("Atras");
		ventanaCrearCuenta.add(botonCrearAtras);
		botonCrearAtras.setBounds(10, 340, 200, 30);

		errorNombre = new JLabel();
		ventanaCrearCuenta.add(errorNombre);
		errorNombre.setBounds(250, 80, 150, 30);
		errorNombre.setForeground(Color.RED);

		errorEmail = new JLabel();
		ventanaCrearCuenta.add(errorEmail);
		errorEmail.setBounds(250, 155, 150, 30);
		errorEmail.setForeground(Color.RED);

		errorContrasenya = new JLabel();
		ventanaCrearCuenta.add(errorContrasenya);
		errorContrasenya.setBounds(250, 230, 150, 30);
		errorContrasenya.setForeground(Color.RED);

		// Action Listeners
		botonCrearSiguiente.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String CrearNombre = txtCrearNombre.getText();
				String CrearEmail = txtCrearEmail.getText();
				String CrearContrasenya = txtCrearContrasenya.getText();
				String CrearEdad = spinCrearEdad.getValue().toString();
				int EdadSeleccionada = (int) spinCrearEdad.getValue();

				errorNombre.setText("");
				errorEmail.setText("");
				errorContrasenya.setText("");

				if (CrearNombre.matches("^[a-zA-Z]*$") && !CrearNombre.isEmpty()
						&& CrearEmail.matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" // Dos lineas para validar si es
								+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$") // un correo o no, FUNCIONA 100%
																						// ??
						&& !CrearEmail.isEmpty() && !CrearContrasenya.isEmpty() && CrearEdad.matches("^[0-9]*$")) {
					CambiarPanel(ventanaCrearCuenta, ventanaGenero);
					errorNombre.setText("");
					errorEmail.setText("");
					errorContrasenya.setText("");
					System.out.println("Edad marcado al crear cuenta:" + CrearEdad); // para comporbar que guarda
					/*
					 * } else { errorCrearCuenta.setText("Error al insertar datos.");
					 * spinCrearEdad.setValue(EdadSeleccionada);
					 * System.out.println("Edad marcado al crear cuenta:"+CrearEdad); }
					 */
				} else if (!CrearNombre.matches("^[a-zA-Z]*$") || CrearNombre.isEmpty()) {
					errorNombre.setText("Nombre NO valido");
					spinCrearEdad.setValue(EdadSeleccionada);
					System.out.println("Edad marcado al crear cuenta:" + CrearEdad + ", Nombre NO valido");
				} else if (!CrearEmail.matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" // Dos lineas para validar si
																							// es
						+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$") // un correo o no, FUNCIONA 100% ??
						|| CrearEmail.isEmpty()) {
					errorEmail.setText("Email NO valido");
					spinCrearEdad.setValue(EdadSeleccionada);
					System.out.println("Edad marcado al crear cuenta:" + CrearEdad + ", Email NO valido");
				} else if (CrearContrasenya.isEmpty()) {
					errorContrasenya.setText("Contraseña NO valida");
					spinCrearEdad.setValue(EdadSeleccionada);
					System.out.println("Edad marcado al crear cuenta:" + CrearEdad + ", Contraseña NO valida");
				}
			}
		});

		botonCrearAtras.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				CambiarPanel(ventanaCrearCuenta, ventanaInicioSesion);
				txtCrearNombre.setText("nombre");
				txtCrearEmail.setText("ejemplo@gmail.com");
				txtCrearContrasenya.setText("");
				spinCrearEdad.setValue(18);
				errorNombre.setText("");
				escrito3 = false;
				escrito4 = false;
				errorEmail.setText("");
				errorContrasenya.setText("");

				txtEmail.setText("ejemplo@gmail.com");
				contrasenya.setText("12345");
				escrito1 = false;
				escrito2 = false;
			}
		});

		// Anyadiendo los componentes de ventanaGenero
		labelEscogerGenero = new JLabel("Seleccione su genero: ");
		ventanaGenero.add(labelEscogerGenero);
		labelEscogerGenero.setBounds(115, 145, 200, 40);
		labelEscogerGenero.setFont(new Font("Monospace", Font.BOLD, 13));

		radioMasculino = new JRadioButton("Masculino");
		radioMasculino.setActionCommand("Masculino");
		radioMasculino.setFont(new Font("Monospace", Font.PLAIN, 12));
		radioFemenino = new JRadioButton("Femenino");
		radioFemenino.setActionCommand("Femenino");
		radioFemenino.setFont(new Font("Monospace", Font.PLAIN, 12));
		ButtonGroup radioButtonsGenero = new ButtonGroup();
		radioButtonsGenero.add(radioFemenino);
		radioButtonsGenero.add(radioMasculino);
		ventanaGenero.add(radioMasculino);
		ventanaGenero.add(radioFemenino);
		radioMasculino.setBounds(300, 150, 100, 30);
		radioFemenino.setBounds(400, 150, 100, 30);

		botonGeneroSiguiente = new JButton("Siguiente");
		ventanaGenero.add(botonGeneroSiguiente);
		botonGeneroSiguiente.setBounds(500, 340, 200, 30);

		botonGeneroAtras = new JButton("Atras");
		ventanaGenero.add(botonGeneroAtras);
		botonGeneroAtras.setBounds(10, 340, 200, 30);

		errorGenero = new JLabel();
		ventanaGenero.add(errorGenero);
		errorGenero.setBounds(220, 340, 400, 30);
		errorGenero.setForeground(Color.RED);

		// Action Listeners
		botonGeneroSiguiente.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// Dependiendo de lo escogido en ventanaGenero apareceran diferentes checkboxes!

				if (radioMasculino.isSelected()) {

					CambiarPanel(ventanaGenero, ventanaPerfilGustosUnoM);
					errorGenero.setText("");
				} else if (radioFemenino.isSelected()) {
					CambiarPanel(ventanaGenero, ventanaPerfilGustosUnoF);
					errorGenero.setText("");
				} else {
					errorGenero.setText("Se necesita seleccionar 1 genero para continuar.");
					System.out.println("Se necesita seleccionar 1 genero para continuar."); // Hacer dialogo mas
																							// adelante
				}
			}
		});

		botonGeneroAtras.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				CambiarPanel(ventanaGenero, ventanaCrearCuenta);
				radioButtonsGenero.clearSelection();
				errorGenero.setText("");
				txtCrearContrasenya.setText("");
			}
		});

		// Anyadiendo los componentes de ventanaPerfilGustosUnoM

		clasicoM = new JCheckBox("Clasico");
		ventanaPerfilGustosUnoM.add(clasicoM);
		clasicoM.setBounds(200, 70, 150, 40);
		urbanaM = new JCheckBox("Urbana");
		ventanaPerfilGustosUnoM.add(urbanaM);
		urbanaM.setBounds(400, 70, 150, 40);
		rockM = new JCheckBox("Rock");
		ventanaPerfilGustosUnoM.add(rockM);
		rockM.setBounds(200, 140, 150, 40);
		smartM = new JCheckBox("Smart");
		ventanaPerfilGustosUnoM.add(smartM);
		smartM.setBounds(400, 140, 150, 40);
		formalM = new JCheckBox("Formal");
		ventanaPerfilGustosUnoM.add(formalM);
		formalM.setBounds(200, 210, 150, 40);
		casualChickM = new JCheckBox("Casual Chick");
		ventanaPerfilGustosUnoM.add(casualChickM);
		casualChickM.setBounds(400, 210, 150, 40);

		botonPerfilGustosUnoMAtras = new JButton("Atras");
		ventanaPerfilGustosUnoM.add(botonPerfilGustosUnoMAtras);
		botonPerfilGustosUnoMAtras.setBounds(10, 340, 200, 30);

		botonPerfilGustosUnoMSiguiente = new JButton("Siguiente");
		ventanaPerfilGustosUnoM.add(botonPerfilGustosUnoMSiguiente);
		botonPerfilGustosUnoMSiguiente.setBounds(500, 340, 200, 30);

		errorPerfilGustosUnoM = new JLabel();
		ventanaPerfilGustosUnoM.add(errorPerfilGustosUnoM);
		errorPerfilGustosUnoM.setBounds(300, 340, 400, 40);
		errorPerfilGustosUnoM.setForeground(Color.RED);

		// Action Listeners
		botonPerfilGustosUnoMSiguiente.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) { // qwe

				if (clasicoM.isSelected() || urbanaM.isSelected() || rockM.isSelected() || smartM.isSelected()
						|| formalM.isSelected() || casualChickM.isSelected()) {
					CambiarPanel(ventanaPerfilGustosUnoM, ventanaPerfilGustosDos);
					errorPerfilGustosUnoM.setText("");

					// Esto es para comprobar que y como funciona
					if (clasicoM.isSelected()) {
						System.out.println("clasicoM");
					}
					if (urbanaM.isSelected()) {
						System.out.println("urbanaM");
					}
					if (rockM.isSelected()) {
						System.out.println("rockM");
					}
					if (smartM.isSelected()) {
						System.out.println("smartM");
					}
					if (formalM.isSelected()) {
						System.out.println("formalM");
					}
					if (casualChickM.isSelected()) {
						System.out.println("casualChickM");
					}
					System.out.println("- - - - -");

				} else {
					errorPerfilGustosUnoM.setText("Selecciona al menos 1.");
				}
			}
		});

		botonPerfilGustosUnoMAtras.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// Asegurarse de que los checkboxes que aparezcan se borran y se vuelven a
				// generar al darle a siguiente en caso de cambio de genero al volver atras
				clasicoM.setSelected(false);
				urbanaM.setSelected(false);
				rockM.setSelected(false);
				smartM.setSelected(false);
				formalM.setSelected(false);
				casualChickM.setSelected(false);

				errorPerfilGustosUnoM.setText("");

				CambiarPanel(ventanaPerfilGustosUnoM, ventanaGenero);
			}
		});

		// Anyadiendo los componentes de ventanaPerfilGustosUnoF

		clasicoF = new JCheckBox("Clasico");
		ventanaPerfilGustosUnoF.add(clasicoF);
		clasicoF.setBounds(200, 70, 150, 40);
		urbanaF = new JCheckBox("Urbana");
		ventanaPerfilGustosUnoF.add(urbanaF);
		urbanaF.setBounds(400, 70, 150, 40);
		rockF = new JCheckBox("Rock");
		ventanaPerfilGustosUnoF.add(rockF);
		rockF.setBounds(200, 140, 150, 40);
		bohoF = new JCheckBox("Boho");
		ventanaPerfilGustosUnoF.add(bohoF);
		bohoF.setBounds(400, 140, 150, 40);
		formalF = new JCheckBox("Formal");
		ventanaPerfilGustosUnoF.add(formalF);
		formalF.setBounds(200, 210, 150, 40);
		sportyChickF = new JCheckBox("Sporty Chick");
		ventanaPerfilGustosUnoF.add(sportyChickF);
		sportyChickF.setBounds(400, 210, 150, 40);

		botonPerfilGustosUnoFAtras = new JButton("Atras");
		ventanaPerfilGustosUnoF.add(botonPerfilGustosUnoFAtras);
		botonPerfilGustosUnoFAtras.setBounds(10, 340, 200, 30);

		botonPerfilGustosUnoFSiguiente = new JButton("Siguiente");
		ventanaPerfilGustosUnoF.add(botonPerfilGustosUnoFSiguiente);
		botonPerfilGustosUnoFSiguiente.setBounds(500, 340, 200, 30);

		errorPerfilGustosUnoF = new JLabel();
		ventanaPerfilGustosUnoF.add(errorPerfilGustosUnoF);
		errorPerfilGustosUnoF.setBounds(300, 340, 400, 40);
		errorPerfilGustosUnoF.setForeground(Color.RED);

		botonPerfilGustosUnoFSiguiente.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) { // qwe
				if (clasicoF.isSelected() || urbanaF.isSelected() || rockF.isSelected() || bohoF.isSelected()
						|| formalF.isSelected() || sportyChickF.isSelected()) {
					CambiarPanel(ventanaPerfilGustosUnoF, ventanaPerfilGustosDos);
					errorPerfilGustosUnoF.setText("");

					// Esto es para comprobar que y como funciona
					if (clasicoF.isSelected()) {
						System.out.println("clasicoF");
					}
					if (urbanaF.isSelected()) {
						System.out.println("urbanaF");
					}
					if (rockF.isSelected()) {
						System.out.println("rockF");
					}
					if (bohoF.isSelected()) {
						System.out.println("bohoF");
					}
					if (formalF.isSelected()) {
						System.out.println("formalF");
					}
					if (sportyChickF.isSelected()) {
						System.out.println("sportyChickF");
					}
					System.out.println("- - - - -");

				} else {
					errorPerfilGustosUnoF.setText("Selecciona al menos 1.");
				}
			}
		});

		botonPerfilGustosUnoFAtras.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// Asegurarse de que los checkboxes que aparezcan se borran y se vuelven a
				// generar al darle a siguiente en caso de cambio de genero al volver atras
				clasicoF.setSelected(false);
				urbanaF.setSelected(false);
				rockF.setSelected(false);
				bohoF.setSelected(false);
				formalF.setSelected(false);
				sportyChickF.setSelected(false);

				errorPerfilGustosUnoM.setText("");

				CambiarPanel(ventanaPerfilGustosUnoF, ventanaGenero);
			}
		});

		// Anyadiendo los componentes de ventanaPerfilGustosDos
		labelEscoge = new JLabel("Cual de las siguientes prendas te gusta mas para ti?");
		labelEscoge.setFont(new Font("Monospace", Font.BOLD, 13));
		ventanaPerfilGustosDos.add(labelEscoge);
		labelEscoge.setBounds(200, 30, 350, 40);

		radioPrendaIzq = new JRadioButton("Opcion 1");
		ventanaPerfilGustosDos.add(radioPrendaIzq);
		radioPrendaIzq.setBounds(200, 280, 100, 30);

		radioPrendaDer = new JRadioButton("Opcion 2");
		ventanaPerfilGustosDos.add(radioPrendaDer);
		radioPrendaDer.setBounds(420, 280, 100, 30);

		ButtonGroup bgPerfilGustosDos = new ButtonGroup();
		bgPerfilGustosDos.add(radioPrendaIzq);
		bgPerfilGustosDos.add(radioPrendaDer);

		botonPerfilGustosDosAtras = new JButton("Atras");
		ventanaPerfilGustosDos.add(botonPerfilGustosDosAtras);
		botonPerfilGustosDosAtras.setBounds(10, 340, 200, 30);

		botonPerfilGustosDosSiguiente = new JButton("Siguiente");
		ventanaPerfilGustosDos.add(botonPerfilGustosDosSiguiente);
		botonPerfilGustosDosSiguiente.setBounds(500, 340, 200, 30);

		errorPerfilGustosDos = new JLabel();
		ventanaPerfilGustosDos.add(errorPerfilGustosDos);
		errorPerfilGustosDos.setBounds(300, 340, 400, 40);
		errorPerfilGustosDos.setForeground(Color.RED);

		// Action Listeners
		botonPerfilGustosDosSiguiente.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (bgPerfilGustosDos.getSelection() != null) {

					CambiarPanel(ventanaPerfilGustosDos, ventanaCarga);
					errorPerfilGustosDos.setText("");

					// JProgressBar

					Thread t = new Thread(new Runnable() {

						@Override
						public void run() {

							for (counter = 0; !stop && counter <= MAX_STEPS; counter++) {
								System.out.println(counter);

								SwingUtilities.invokeLater(new Runnable() {

									@Override
									public void run() {
										progressCargando.setValue(counter);

									}
								});
							}

							if (stop = true) {
								CambiarPanel(ventanaCarga, ventanaMenuPrincipal);

								// log crear cuenta
								if (clasicoM.isSelected()) {
									g1 = " clasicoM ";
								}
								if (urbanaM.isSelected()) {
									g2 = " urbanaM ";
								}
								if (rockM.isSelected()) {
									g3 = " rockM ";
								}
								if (smartM.isSelected()) {
									g4 = " smartM ";
								}
								if (formalM.isSelected()) {
									g5 = " formalM ";
								}
								if (casualChickM.isSelected()) {
									g6 = " casualChickM ";
								}

								if (clasicoF.isSelected()) {
									g7 = " clasicoF ";
								}
								if (urbanaF.isSelected()) {
									g8 = " urbanF ";
								}
								if (rockF.isSelected()) {
									g9 = " rockF ";
								}
								if (bohoF.isSelected()) {
									g10 = " bohoF ";
								}
								if (formalF.isSelected()) {
									g11 = " formalF ";
								}
								if (sportyChickF.isSelected()) {
									g12 = " sportyChickF ";
								}

								Usuariolog.println("Creacion de cuenta: " + (new Date()) + "\n" + "Nombre:"
										+ txtCrearNombre.getText() + ", Email:" + txtCrearEmail.getText() + ", Edad:"
										+ spinCrearEdad.getValue() + ", Genero:"
										+ radioButtonsGenero.getSelection().getActionCommand() + "\nGustos: " + g1 + g2
										+ g3 + g4 + g5 + g6 + g7 + g8 + g9 + g10 + g11 + g12);

								String g1 = "";
								String g7 = "";
								String g2 = "";
								String g8 = "";
								String g3 = "";
								String g9 = "";
								String g4 = "";
								String g10 = "";
								String g5 = "";
								String g11 = "";
								String g6 = "";
								String g12 = "";

								// se cambia? SI GUD JOB
								mb.setVisible(true);
								mb.setEnabled(true);
								UIManager.put("OptionPane.minimumSize", new Dimension(600, 700));
								JOptionPane.showMessageDialog(null, ventanaEmergenteOutfit, "¡Aqui esta tu outfit!",
										JOptionPane.DEFAULT_OPTION);
							}
						}
					});

					t.start();

				} else {
					errorPerfilGustosDos.setText("Selecciona al menos 1.");
				}

			}
		});

		botonPerfilGustosDosAtras.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (radioMasculino.isSelected()) {
					CambiarPanel(ventanaPerfilGustosDos, ventanaPerfilGustosUnoM);
				} else if (radioFemenino.isSelected()) {
					CambiarPanel(ventanaPerfilGustosDos, ventanaPerfilGustosUnoF);
				}

				errorPerfilGustosUnoM.setText("");
				errorPerfilGustosUnoF.setText("");
				errorPerfilGustosDos.setText("");
				bgPerfilGustosDos.clearSelection();
			}
		});

		// Anyadiendo los componentes de ventanaCarga
		labelCargando = new JLabel("Cargando");
		ventanaCarga.add(labelCargando);
		labelCargando.setBounds(320, 170, 200, 50);

		progressCargando = new JProgressBar(0, MAX_STEPS);
		progressCargando.setValue(75);
		ventanaCarga.add(progressCargando);
		progressCargando.setBounds(200, 220, 300, 40);

		// Anyadiendo los componentes de ventanaMenuPrincipal
		botonPideOutfit = new JButton("Pide un Outfit!");
		botonPideOutfit.setBounds(250, 150, 200, 50);
		ventanaMenuPrincipal.add(botonPideOutfit);

		botonAnyadirVestimenta = new JButton("Anyade tu propia Vestimenta");
		botonAnyadirVestimenta.setBounds(250, 250, 200, 50);
		ventanaMenuPrincipal.add(botonAnyadirVestimenta);

		// Este boton solo puede ser visible cuando se hace log in con una cuenta
		// administradora, para que solo los
		// administradores puedan gestionar a los administradores.
		// De momento la dejamos ahi y ya le haremos el if admin = true .setvisible
		// luego
		botonMasMenosAdmin = new JButton("Admin +/-");
		botonMasMenosAdmin.setBounds(550, 40, 110, 30);
		ventanaMenuPrincipal.add(botonMasMenosAdmin);

		// ActionListeners

		botonPideOutfit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				CambiarPanel(ventanaMenuPrincipal, ventanaPideOutfit);
				mb.setVisible(false);
				mb.setEnabled(false);

			}
		});

		botonAnyadirVestimenta.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				CambiarPanel(ventanaMenuPrincipal, ventanaAnyadirVestimenta);
				mb.setVisible(false);
				mb.setEnabled(false);
			}
		});

		botonMasMenosAdmin.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				mb.setVisible(false);
				mb.setEnabled(false);
				CambiarPanel(ventanaMenuPrincipal, ventanaProx);
			}
		});

		// Anyadiendo los compenentes de ventanaPideOutfit
		preguntaTiempo = new JLabel("Que tiempo hace hoy?");
		preguntaTiempo.setFont(new Font("Monospace", Font.BOLD, 13));
		ventanaPideOutfit.add(preguntaTiempo);
		preguntaTiempo.setBounds(50, 0, 300, 100);

		radioSol = new JRadioButton("Soleado");
		radioSol.setActionCommand("Sol");
		radioSol.setFont(new Font("Monospace", Font.PLAIN, 12));
		ventanaPideOutfit.add(radioSol);
		radioSol.setBounds(50, 80, 100, 40);
		radioLluvia = new JRadioButton("Llueve");
		radioLluvia.setActionCommand("Llueve");
		radioLluvia.setFont(new Font("Monospace", Font.PLAIN, 12));
		ventanaPideOutfit.add(radioLluvia);
		radioLluvia.setBounds(50, 120, 100, 40);
		radioNublado = new JRadioButton("Nublado");
		radioNublado.setActionCommand("Nublado");
		radioNublado.setFont(new Font("Monospace", Font.PLAIN, 12));
		ventanaPideOutfit.add(radioNublado);
		radioNublado.setBounds(50, 160, 100, 40);

		ButtonGroup bgPideOutfit = new ButtonGroup();
		bgPideOutfit.add(radioSol);
		bgPideOutfit.add(radioLluvia);
		bgPideOutfit.add(radioNublado);

		preguntaEstilo = new JLabel("Tienes algun estilo en mente para tu outfit?");
		preguntaEstilo.setFont(new Font("Monospace", Font.BOLD, 13));
		ventanaPideOutfit.add(preguntaEstilo);
		preguntaEstilo.setBounds(50, 210, 300, 60);

		radioNo = new JRadioButton("No");
		radioNo.setFont(new Font("Monospace", Font.PLAIN, 12));
		ventanaPideOutfit.add(radioNo);
		radioNo.setBounds(50, 260, 50, 30);

		estilosComboBoxPideOutfit = new JComboBox<String>();
		ventanaPideOutfit.add(estilosComboBoxPideOutfit);
		estilosComboBoxPideOutfit.addItem(null); // mantener esta opcion como primero!!! se supone que es -1
		estilosComboBoxPideOutfit.addItem("ClasicoF"); // 1
		estilosComboBoxPideOutfit.addItem("ClasicoM"); // 2
		estilosComboBoxPideOutfit.addItem("UrbanaF");
		estilosComboBoxPideOutfit.addItem("UrbanaM"); // 4
		estilosComboBoxPideOutfit.addItem("RockF");
		estilosComboBoxPideOutfit.addItem("RockM");
		estilosComboBoxPideOutfit.addItem("BohoF");
		estilosComboBoxPideOutfit.addItem("SmartM"); // 8
		estilosComboBoxPideOutfit.addItem("FormalF");
		estilosComboBoxPideOutfit.addItem("FormalM");
		estilosComboBoxPideOutfit.addItem("SportyChickF");
		estilosComboBoxPideOutfit.addItem("CasualChickM"); // 12
		estilosComboBoxPideOutfit.setBounds(150, 260, 200, 30);

		botonAtrasPideOutfit = new JButton("Atras");
		ventanaPideOutfit.add(botonAtrasPideOutfit);
		botonAtrasPideOutfit.setBounds(10, 340, 200, 30);

		botonBuscar = new JButton("Buscar!");
		ventanaPideOutfit.add(botonBuscar);
		botonBuscar.setBounds(500, 340, 200, 30);

		errorPideOutfit = new JLabel();
		ventanaPideOutfit.add(errorPideOutfit);
		errorPideOutfit.setBounds(250, 340, 400, 40);
		errorPideOutfit.setForeground(Color.RED);

		// Action Listeners
		radioNo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (radioNo.isSelected()) {
					estilosComboBoxPideOutfit.setSelectedIndex(0); // dejarlo en 0
					System.out.println("radioNo , " + estilosComboBoxPideOutfit.getSelectedIndex());
				}
			}
		});

		botonBuscar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println(estilosComboBoxPideOutfit.getSelectedIndex());

				if (bgPideOutfit.getSelection() != null
						&& (radioNo.isSelected() || estilosComboBoxPideOutfit.getSelectedIndex() != -1)) {

					Object l;
					if (radioNo.isSelected()) {
						l = "No";
					} else {
						l = estilosComboBoxAnyadirVestimenta.getSelectedItem();
					}
					Usuariolog.println("Pide Outfit, tiempo: " + bgPideOutfit.getSelection().getActionCommand()
							+ ", estilo: " + l);

					CambiarPanel(ventanaPideOutfit, ventanaFeedback);

					UIManager.put("OptionPane.minimumSize", new Dimension(600, 700));
					JOptionPane.showMessageDialog(null, ventanaEmergenteOutfit, "¡Aqui esta tu outfit!",
							JOptionPane.DEFAULT_OPTION);
					escrito5 = false;
					bgPideOutfit.clearSelection();
					radioNo.setSelected(false);
					errorPideOutfit.setText("");
					estilosComboBoxPideOutfit.setSelectedIndex(0);
					
					//Probando cargar imagen de pideOutfit. Nah no sirve porque esta hecho con la mierda del Netbeans pero igual nos sirve como referencia, 
					//porque los comandos si debieran ser parecidos, y he buscado mas videos por youtube y no encuentro ninguno que suban imagenes a mysql son todo panchis y todos con netbeans
					
					/*
					 FileNameExtensionFilter filtro = new FileNameExtensionFilter("Formatos de archivos JPEG(*.JPG;*.JPEG)", "jpg", "jpeg");
					 JFileChooser archivo = new JFileChooser();
					 
					 archivo.addChooseableFileFilter(filtro);
					 archivo.setDialogTitle("Abrir archivo");
					 File ruta = new File ("la ruta en la que tengamos la foto");
					 archivo.setCurrentDirectory(ruta);
					int ventana = archivo.showOpenDialog(null);
					if(ventana == JFileChooser.APPROVE_OPTION) {
						File file = archivo.getSelectedFile();
						txtnomimagen.setText(String.valueOf(file))
						Image foto = getToolkit().getImage(txtnomimage.getText()); /importar Image
						foto = foto.getScaledInstance(110,110,Image.SCALE_DEFAULT);
						lblfoto.setIcon(new ImageIcon(foto));
						
					}*/
					
					
					
				} else {
					errorPideOutfit.setText("Rellena todos los campos requeridos.");
				}
			}
		});

		botonAtrasPideOutfit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				CambiarPanel(ventanaPideOutfit, ventanaMenuPrincipal);
				mb.setVisible(true);
				mb.setEnabled(true);
				errorPideOutfit.setText("");
				bgPideOutfit.clearSelection();
				radioNo.setSelected(false);
			}
		});

		estilosComboBoxPideOutfit.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {

			}

			@Override
			public void mousePressed(MouseEvent e) {

			}

			@Override
			public void mouseExited(MouseEvent e) {

			}

			@Override
			public void mouseEntered(MouseEvent e) {

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				if (estilosComboBoxPideOutfit.getSelectedIndex() != 0) {
					radioNo.setSelected(false);
				} else if (estilosComboBoxPideOutfit.getSelectedIndex() == 0) {
					radioNo.setSelected(true);
				}
			}
		});

		// Action Listeners

		// Anyadiendo los componentes de ventanaAnyadirVestimenta
		estilosLabelAnyadirVestimenta = new JLabel("Selecciona un estilo: (F para Femenino y M para masculino)");
		estilosLabelAnyadirVestimenta.setFont(new Font("Monospace", Font.BOLD, 13));
		colorLabelAnyadirVestimenta = new JLabel("Selecciona un color: ");
		colorLabelAnyadirVestimenta.setFont(new Font("Monospace", Font.BOLD, 13));
		tiempoLabelAnyadirvestimenta = new JLabel("Selecciona el tiempo: ");
		tiempoLabelAnyadirvestimenta.setFont(new Font("Monospace", Font.BOLD, 13));

		sol = new JRadioButton("Sol");
		sol.setActionCommand("Sol");
		lluvia = new JRadioButton("Lluvia");
		lluvia.setActionCommand("Lluvia");
		nublado = new JRadioButton("Nublado");
		nublado.setActionCommand("Nublado");

		estilosComboBoxAnyadirVestimenta = new JComboBox<String>();
		coloresComboBoxAnyadirVestimenta = new JComboBox<String>();

		estilosComboBoxAnyadirVestimenta.addItem("ClasicoF");
		estilosComboBoxAnyadirVestimenta.addItem("ClasicoM");
		estilosComboBoxAnyadirVestimenta.addItem("UrbanaF");
		estilosComboBoxAnyadirVestimenta.addItem("UrbanaM");
		estilosComboBoxAnyadirVestimenta.addItem("RockF");
		estilosComboBoxAnyadirVestimenta.addItem("RockM");
		estilosComboBoxAnyadirVestimenta.addItem("BohoF");
		estilosComboBoxAnyadirVestimenta.addItem("SmartM");
		estilosComboBoxAnyadirVestimenta.addItem("FormalF");
		estilosComboBoxAnyadirVestimenta.addItem("FormalM");
		estilosComboBoxAnyadirVestimenta.addItem("SportyChickF");
		estilosComboBoxAnyadirVestimenta.addItem("CasualChickM");

		coloresComboBoxAnyadirVestimenta.addItem("Rojo");
		coloresComboBoxAnyadirVestimenta.addItem("Azul");
		coloresComboBoxAnyadirVestimenta.addItem("Amarillo");
		coloresComboBoxAnyadirVestimenta.addItem("Verde");
		coloresComboBoxAnyadirVestimenta.addItem("Negro");

		estilosLabelAnyadirVestimenta.setBounds(40, 200, 400, 40);
		colorLabelAnyadirVestimenta.setBounds(450, 200, 200, 40);
		tiempoLabelAnyadirvestimenta.setBounds(40, 50, 200, 40);

		sol.setBounds(40, 100, 100, 40);
		lluvia.setBounds(160, 100, 100, 40);
		nublado.setBounds(280, 100, 100, 40);

		estilosComboBoxAnyadirVestimenta.setBounds(40, 250, 100, 40);
		coloresComboBoxAnyadirVestimenta.setBounds(450, 250, 100, 40);

		ButtonGroup radioButtonsTiempo = new ButtonGroup();
		radioButtonsTiempo.add(sol);
		radioButtonsTiempo.add(lluvia);
		radioButtonsTiempo.add(nublado);

		ventanaAnyadirVestimentaAtras = new JButton("Atras");
		ventanaAnyadirVestimenta.add(ventanaAnyadirVestimentaAtras);
		ventanaAnyadirVestimentaAtras.setBounds(10, 340, 200, 30);

		ventanaAnyadirVestimentaAnyadir = new JButton("Anyadir");
		ventanaAnyadirVestimenta.add(ventanaAnyadirVestimentaAnyadir);
		ventanaAnyadirVestimentaAnyadir.setBounds(500, 340, 200, 30);

		errorVentanaAnyadirVestimenta = new JLabel();
		ventanaAnyadirVestimenta.add(errorVentanaAnyadirVestimenta);
		errorVentanaAnyadirVestimenta.setBounds(300, 340, 400, 40);
		errorVentanaAnyadirVestimenta.setForeground(Color.RED);

		ventanaAnyadirVestimenta.add(sol);
		ventanaAnyadirVestimenta.add(lluvia);
		ventanaAnyadirVestimenta.add(nublado);
		ventanaAnyadirVestimenta.add(estilosComboBoxAnyadirVestimenta);
		ventanaAnyadirVestimenta.add(coloresComboBoxAnyadirVestimenta);
		ventanaAnyadirVestimenta.add(estilosLabelAnyadirVestimenta);
		ventanaAnyadirVestimenta.add(colorLabelAnyadirVestimenta);
		ventanaAnyadirVestimenta.add(tiempoLabelAnyadirvestimenta);

		// actionlisteners ventanaAnyadirVestimenta
		ventanaAnyadirVestimentaAtras.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				CambiarPanel(ventanaAnyadirVestimenta, ventanaMenuPrincipal);
				radioButtonsTiempo.clearSelection();
				errorVentanaAnyadirVestimenta.setText("");
				mb.setVisible(true);
				mb.setEnabled(true);
			}
		});

		ventanaAnyadirVestimentaAnyadir.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (radioButtonsTiempo.isSelected(null)) {
					errorVentanaAnyadirVestimenta.setText("Selecciona el tiempo.");
				} else {
					Usuariolog
							.println("Anyade vestimenta, tiempo " + radioButtonsTiempo.getSelection().getActionCommand()
									+ ", estilo: " + estilosComboBoxAnyadirVestimenta.getSelectedItem() + ", color: "
									+ coloresComboBoxAnyadirVestimenta.getSelectedItem());
					CambiarPanel(ventanaAnyadirVestimenta, ventanaProx); // prox
					radioButtonsTiempo.clearSelection();
					errorVentanaAnyadirVestimenta.setText("");
				}
			}
		});

		// Anyadiendo los componentes de ventanaFeedback
		nivelSatisfaccion = new JLabel("Nivel de satisfaccion: ");
		nivelSatisfaccion.setFont(new Font("Monospace", Font.BOLD, 13));
		ventanaFeedback.add(nivelSatisfaccion);
		gustoColores = new JLabel("Te han gustado los colores?");
		gustoColores.setFont(new Font("Monospace", Font.BOLD, 13));
		ventanaFeedback.add(gustoColores);

		estrella1 = new JRadioButton("*");
		estrella1.setActionCommand("1");
		estrella2 = new JRadioButton("* *");
		estrella2.setActionCommand("2");
		estrella3 = new JRadioButton("* * *");
		estrella3.setActionCommand("3");
		estrella4 = new JRadioButton("* * * *");
		estrella4.setActionCommand("4");
		estrella5 = new JRadioButton("* * * * *");
		estrella5.setActionCommand("5");
		si = new JRadioButton("Si");
		si.setActionCommand("si");
		si.setFont(new Font("Monospace", Font.PLAIN, 12));
		no = new JRadioButton("No");
		no.setActionCommand("no");
		no.setFont(new Font("Monospace", Font.PLAIN, 12));

		ButtonGroup radioButtonsEstrellas = new ButtonGroup();
		radioButtonsEstrellas.add(estrella1);
		radioButtonsEstrellas.add(estrella2);
		radioButtonsEstrellas.add(estrella3);
		radioButtonsEstrellas.add(estrella4);
		radioButtonsEstrellas.add(estrella5);
		ButtonGroup radioButtonsSiNo = new ButtonGroup();
		radioButtonsSiNo.add(si);
		radioButtonsSiNo.add(no);

		ventanaFeedback.add(estrella1);
		ventanaFeedback.add(estrella2);
		ventanaFeedback.add(estrella3);
		ventanaFeedback.add(estrella4);
		ventanaFeedback.add(estrella5);
		ventanaFeedback.add(si);
		ventanaFeedback.add(no);

		nivelSatisfaccion.setBounds(100, 50, 200, 50);
		gustoColores.setBounds(100, 175, 200, 50);

		estrella1.setBounds(100, 100, 80, 50);
		estrella2.setBounds(200, 100, 80, 50);
		estrella3.setBounds(300, 100, 80, 50);
		estrella4.setBounds(400, 100, 80, 50);
		estrella5.setBounds(500, 100, 80, 50);
		si.setBounds(100, 220, 100, 50);
		no.setBounds(200, 220, 100, 50);

		botonInicioFeedback = new JButton("Mandar e inicio");
		ventanaFeedback.add(botonInicioFeedback);
		botonInicioFeedback.setBounds(260, 330, 200, 30);

		errorFeedback = new JLabel();
		ventanaFeedback.add(errorFeedback);
		errorFeedback.setBounds(275, 300, 200, 30);
		errorFeedback.setForeground(Color.RED);

		// Action Listeners
		botonInicioFeedback.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (!radioButtonsEstrellas.isSelected(null) && (si.isSelected() || no.isSelected())) {
					CambiarPanel(ventanaFeedback, ventanaMenuPrincipal);
					mb.setVisible(true);
					mb.setEnabled(true);
					System.out.println("Mandando feedback... (?)");

					// Feedback.log
					try {
						Feedbacklog = new PrintStream(new FileOutputStream("Feedback.log", true));
					} catch (Exception e1) {
					}
					Feedbacklog.println("Puntuacion: " + radioButtonsEstrellas.getSelection().getActionCommand() + "\n"
							+ "Si/No: " + radioButtonsSiNo.getSelection().getActionCommand());
					// hasta aqui

					radioButtonsEstrellas.clearSelection();
					radioButtonsSiNo.clearSelection();
					errorFeedback.setText("");
				} else {
					errorFeedback.setText("Contesta todas las preguntas.");
				}
			}
		});

		// Anyadiendo los componentes de ajustes
		cambiarContrasenya = new JButton("Cambiar contrasenya");
		cambiarContrasenya.setBounds(50, 20, 50, 50);
		ajustes.add(cambiarContrasenya);

		cambiarFecha = new JButton("Cambiar fecha");
		cambiarFecha.setBounds(50, 100, 50, 50);
		ajustes.add(cambiarFecha);

		reiniciarPerfil = new JButton("Reiniciar perfil");
		reiniciarPerfil.setBounds(50, 180, 50, 50);
		ajustes.add(reiniciarPerfil);

		// Action listeners OJO ESTO SOLO PARA PROBAR
		cambiarContrasenya.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Cambiar contrasenya");
			}
		});

		cambiarFecha.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Cambiar fecha");
			}
		});

		reiniciarPerfil.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Reiniciar perfil de gustos");
			}
		});

		// VentanaProximamente
		prox = new JLabel("PROXIMAMENTE");
		prox.setBounds(100, 100, 100, 50);
		ventanaProx.add(prox);

		// actionlistener menu - cerrar sesion
		mi1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// Menu
				mb.setVisible(false);
				mb.setEnabled(false);

				// ventanaInicioSesion
				txtEmail.setText("ejemplo@gmail.com");
				contrasenya.setText("12345");
				// view.setSelected(false); dejarlo asi
				escrito1 = false;
				escrito2 = false;

				// ventanaCrearCuenta
				txtCrearNombre.setText("nombre");
				txtCrearEmail.setText("ejemplo@gmail.com");
				txtCrearContrasenya.setText("");
				spinCrearEdad.setValue(18);
				escrito3 = false;
				escrito4 = false;

				// ventanaGenero
				radioButtonsGenero.clearSelection();
				errorGenero.setText("");

				// ventanaPerfilGustosUnoM
				clasicoM.setSelected(false);
				urbanaM.setSelected(false);
				rockM.setSelected(false);
				smartM.setSelected(false);
				formalM.setSelected(false);
				casualChickM.setSelected(false);

				errorPerfilGustosUnoM.setText("");

				// ventanaPerfilGustosUnoF
				clasicoF.setSelected(false);
				urbanaF.setSelected(false);
				rockF.setSelected(false);
				bohoF.setSelected(false);
				formalF.setSelected(false);
				sportyChickF.setSelected(false);

				errorPerfilGustosUnoF.setText("");

				// ventanaPerfilGustosDos
				bgPerfilGustosDos.clearSelection();
				errorPerfilGustosDos.setText("");

				// ventanaCarga
				counter = 0;

				// ventanaMenuPrincipal
				// hace falta reiniciar algo?

				// ventanaPideOutfit
				bgPideOutfit.clearSelection();
				radioNo.setSelected(false);
				escrito5 = false;
				errorPideOutfit.setText("");

				// ventanaFeedback
				radioButtonsEstrellas.clearSelection();
				radioButtonsSiNo.clearSelection();

				// ventanaAnyadirVestimenta
				radioButtonsTiempo.clearSelection();

				// "sobras"
				// quitar comentario y agregar aqui <-

				// Hasta aqui
				Usuariolog.println("Sesion cerrada.");
				CambiarPanel(ventanaMenuPrincipal, ventanaInicioSesion);
				System.out.println("Sesion cerrada.");

			}
		});

		mi2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				UIManager.put("OptionPane.minimumSize", new Dimension(200, 200)); // este tamanyo es solo para esta
																					// ventana emergente
				JOptionPane.showMessageDialog(null, ajustes, "Ajustes", JOptionPane.DEFAULT_OPTION);

			}
		});

	}

	public static void main(String[] args) {

		// prueba
		try {
			Usuariolog = new PrintStream(new FileOutputStream("Usuario.log", true));
		} catch (Exception e) {
		}
		Usuariolog.println("Inicio del programa.");

		/*
		 * try { logger = Logger.getLogger("prueba-logger"); logger.addHandler(new
		 * FileHandler("pruebaLogger.xml", true)); } catch (Exception e) { }
		 * logger.log(Level.INFO, "Inicio de programa con logger: ");
		 */
		// fin de prueba

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new Principal();
			}
		});

		// Lo de la hora
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		System.out.println(sdf.format(cal.getTime()));
	}

}