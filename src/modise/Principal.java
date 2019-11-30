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
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
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
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.WindowConstants;

import conexion.BaseDatosModise;
import conexion.Conexion;

public class Principal {

	// ORDENES DE LAS VENTANAS!!!!!
	// (CASO DE PRIMERA VEZ EN MODISE) 1.Ventana Inicio Sesion || 2.Ventana Crear
	// Cuenta || 3.Ventana Genero || 4.Ventana Perfil Gustos 1 || 5.Ventana Perfil
	// Gustos 2 || || 6.Ventana de Carga || 7.Ventana Menu Principal
	// (CASO USUARIO YA REGISTRADO) 1.Ventana Inicio Sesion || 2.Ventana de Carga ||
	// 3.Ventana Menu Principal
	// Declarando componentes
	// VentanaInicioSesion
	JLabel labelBrand, labelEmail, labelContraseña, labelPregunta;
	JTextField txtEmail, txtContraseña;
	JButton botonCrear, botonInicio;
	JPasswordField contraseña;
	JCheckBox view;
	boolean escrito1, escrito2;

	private JLabel clockLabel;
	public final static int ONE_SECOND = 1000;
	private final SimpleDateFormat clockFormat = new SimpleDateFormat("H:mm:ss");

	// VentanaCrearCuenta
	JLabel labelCrearNombre, labelCrearEmail, labelCrearContraseña, labelCrearEdad, errorNombre, errorEmail,
			errorContraseña;
	JTextField txtCrearNombre, txtCrearEmail, txtCrearContraseña;
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
	/*
	 * String g1 = ""; String g7 = ""; String g2 = ""; String g8 = ""; String g3 =
	 * ""; String g9 = ""; String g4 = ""; String g10 = ""; String g5 = ""; String
	 * g11 = ""; String g6 = ""; String g12 = "";
	 */

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
	JButton botonPideOutfit, botonAñadirVestimenta, botonMasMenosAdmin;

	// ventanaAñadirVestimenta
	JRadioButton sol, lluvia, nublado;
	JLabel estilosLabelAñadirVestimenta, colorLabelAñadirVestimenta, tiempoLabelAñadirvestimenta,
			errorVentanaAñadirVestimenta;
	boolean escrito5;
	JButton ventanaAñadirVestimentaAtras, ventanaAñadirVestimentaAñadir;

	JComboBox<String> estilosComboBoxAñadirVestimenta;

	JComboBox<String> coloresComboBoxAñadirVestimenta;

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
	JButton cambiarContraseña, cambiarFecha, reiniciarPerfil;

	// ventanaEmergenteOutfit

	// ventanaMasMenosAdmin
	JLabel labelEmailMasMenosAdmin;
	JTextField txtEmailMasMenosAdmin;
	JLabel labelErrorMasMenosAdmin, labelSuccessMasMenosAdmin;
	JComboBox<String> comboMasMenosAdministrador;
	JLabel labelSelecionOperacionMasMenosAdmin;
	JButton botonGuardarCambiosMasMenosAdmin, botonatrasMasMenosAdmin;

	// mas
	static PrintStream Feedbacklog, Usuariolog;

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

	/**
	 * Guarda en un archivo properties el valor del username que ha entrado por
	 * ultima vez
	 * 
	 * @param Username nombre del usuario a escribir
	 */
	public static void setProp(String Username) {
		File archivo = new File("config.properties");
		try {
			FileOutputStream fos = new FileOutputStream(archivo);
			Properties propConfig = new Properties();

			propConfig.setProperty("username", Username);
			propConfig.store(fos, "program Settings");
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String getProp() {
		File archivo = new File("config.properties");
		try {
			FileInputStream fis = new FileInputStream(archivo);
			Properties propConfig = new Properties();
			propConfig.load(fis);
			// cojemos las properties
			String nombre = propConfig.getProperty("username");
			return nombre;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	public Principal() {

		// Propiedades y componentes del Frame
		JFrame frame = new JFrame();
		frame.setVisible(true);
		frame.setSize(720, 480);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
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

			@Override
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
		PanelFondo ventanaAñadirVestimenta = new PanelFondo();
		PanelFondo ventanaPideOutfit = new PanelFondo();
		PanelFondo ventanaFeedback = new PanelFondo();
		JPanel ventanaMasMenosAdmin = new JPanel(); // dejadla asi. NO, le he cambiado el nombre

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
		CrearPanel(ventanaAñadirVestimenta);
		CrearPanel(ventanaPideOutfit);
		CrearPanel(ventanaFeedback);
		CrearPanel(ventanaMasMenosAdmin);

		frame.getContentPane().add(ventanaInicioSesion);
		frame.getContentPane().add(ventanaCrearCuenta);
		frame.getContentPane().add(ventanaGenero);
		frame.getContentPane().add(ventanaPerfilGustosUnoM);
		frame.getContentPane().add(ventanaPerfilGustosUnoF);
		frame.getContentPane().add(ventanaPerfilGustosDos);
		frame.getContentPane().add(ventanaMenuPrincipal);
		frame.getContentPane().add(ventanaCarga);
		frame.getContentPane().add(ventanaAñadirVestimenta);
		frame.getContentPane().add(ventanaPideOutfit);
		frame.getContentPane().add(ventanaFeedback);
		frame.getContentPane().add(ventanaMasMenosAdmin);

		ventanaInicioSesion.setVisible(true); // la primera ventana visible

		// Añadiendo los componentes de ventanaInicioSesion
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

		labelContraseña = new JLabel("Contrasena: ");
		labelContraseña.setFont(new Font("Monospace", Font.BOLD, 13));
		ventanaInicioSesion.add(labelContraseña);
		labelContraseña.setBounds(60, 133, 100, 40);

		contraseña = new JPasswordField("12345"); // cambiado
		contraseña.setEchoChar('*'); // hacer checkbox isSelected para ver contraseña, HECHO
		ventanaInicioSesion.add(contraseña);
		contraseña.setBounds(160, 140, 300, 30);
		escrito2 = false;
		contraseña.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (escrito2 == false) {
					contraseña.setText("");
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
				Connection conexion = Conexion.conectar();
				Statement st = null;
				try {
					st = conexion.createStatement();
				} catch (SQLException e1) {

				}
				// Pasa el valor del JPassword a String
				String valorPass = new String(contraseña.getPassword());
				if (BaseDatosModise.logIn(st, txtEmail.getText(), valorPass, 0) == true) {
					System.out.println("VA NO ADMIN");
					CambiarPanel(ventanaInicioSesion, ventanaMenuPrincipal);
					Usuariolog.println("Inicio de sesion: " + txtEmail.getText() + "	, " + (new Date()));
					mb.setVisible(true);
					mb.setEnabled(true);
					botonMasMenosAdmin.setVisible(/* false */true);
				} else if (BaseDatosModise.logIn(st, txtEmail.getText(), valorPass, 1) == true) {
					System.out.println("VA SI ADMIN");
					CambiarPanel(ventanaInicioSesion, ventanaMenuPrincipal);
					Usuariolog.println("Inicio de sesion: " + txtEmail.getText() + "	, " + (new Date()));
					mb.setVisible(true);
					mb.setEnabled(true);
					botonMasMenosAdmin.setVisible(true);
				} else {
					System.out.println("no va");
					JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos.");
				}
			}
		});

		view.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (view.isSelected() == true) {
					contraseña.setEchoChar((char) 0);
				} else {
					contraseña.setEchoChar('*');
				}
			}
		});

		// Añadiendo los componentes de ventanaCrearCuenta
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

		labelCrearContraseña = new JLabel("Cree una contrasena: ");
		labelCrearContraseña.setFont(new Font("Monospace", Font.BOLD, 13));
		ventanaCrearCuenta.add(labelCrearContraseña);
		labelCrearContraseña.setBounds(80, 200, 200, 30);

		txtCrearContraseña = new JTextField("");
		ventanaCrearCuenta.add(txtCrearContraseña);
		txtCrearContraseña.setBounds(250, 200, 300, 30);

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

		errorContraseña = new JLabel();
		ventanaCrearCuenta.add(errorContraseña);
		errorContraseña.setBounds(250, 230, 150, 30);
		errorContraseña.setForeground(Color.RED);

		// Action Listeners
		botonCrearSiguiente.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String CrearNombre = txtCrearNombre.getText();
				String CrearEmail = txtCrearEmail.getText();
				String CrearContraseña = txtCrearContraseña.getText();
				String CrearEdad = spinCrearEdad.getValue().toString();
				int EdadSeleccionada = (int) spinCrearEdad.getValue();

				errorNombre.setText("");
				errorEmail.setText("");
				errorContraseña.setText("");

				Connection conexion = Conexion.conectar();
				Statement st = null;
				try {
					st = conexion.createStatement();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}

				if (CrearNombre.matches("^[a-zA-Z]*$") && !CrearNombre.isEmpty()
						&& CrearEmail.matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
								+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")
						&& !CrearEmail.isEmpty() && !CrearContraseña.isEmpty() && CrearEdad.matches("^[0-9]*$")
						&& BaseDatosModise.existeUsuario(st, txtCrearEmail.getText()) == true) {
					CambiarPanel(ventanaCrearCuenta, ventanaGenero);
					errorNombre.setText("");
					errorEmail.setText("");
					errorContraseña.setText("");
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
				} else if (!CrearEmail.matches(
						"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")
						|| CrearEmail.isEmpty()
						|| BaseDatosModise.existeUsuario(st, txtCrearEmail.getText()) == false) {
					errorEmail.setText("Email NO valido");
					spinCrearEdad.setValue(EdadSeleccionada);
					System.out.println("Edad marcado al crear cuenta:" + CrearEdad + ", Email NO valido");
				} else if (CrearContraseña.isEmpty()) {
					errorContraseña.setText("Contraseña NO valida");
					spinCrearEdad.setValue(EdadSeleccionada);
					System.out.println("Edad marcado al crear cuenta:" + CrearEdad + ", Contraseña NO valida");
				}
			}
		});

		// Crear Usuario

		// BaseDatosModise.CrearUsuario(txtCrearNombre.getText(),
		// txtCrearEmail.getText(), spinCrearEdad.getValue(),
		// txtCrearContraseña.getText());

		botonCrearAtras.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				CambiarPanel(ventanaCrearCuenta, ventanaInicioSesion);
				txtCrearNombre.setText("nombre");
				txtCrearEmail.setText("ejemplo@gmail.com");
				txtCrearContraseña.setText("");
				spinCrearEdad.setValue(18);
				errorNombre.setText("");
				escrito3 = false;
				escrito4 = false;
				errorEmail.setText("");
				errorContraseña.setText("");

				txtEmail.setText("ejemplo@gmail.com");
				contraseña.setText("12345");
				escrito1 = false;
				escrito2 = false;
			}
		});

		// Añadiendo los componentes de ventanaGenero
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
				txtCrearContraseña.setText("");
			}
		});

		// Añadiendo los componentes de ventanaPerfilGustosUnoM

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
			public void actionPerformed(ActionEvent e) {

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

		// Añadiendo los componentes de ventanaPerfilGustosUnoF

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

		// Añadiendo los componentes de ventanaPerfilGustosDos
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

								// bd nuevoUsuario
								// TIRA ADELANTE, PERO NO HACE EL INSERT EL LAS TABLAS
								Connection conexion = Conexion.conectar();
								Statement st = null;
								try {
									st = conexion.createStatement();
								} catch (SQLException e1) {
									e1.printStackTrace();
								}

								String valorPass = new String(contraseña.getPassword());

								if (radioMasculino.isSelected() == true) {
									BaseDatosModise.nuevoUsuario(st, txtCrearNombre.getText(), txtCrearEmail.getText(),
											0, spinCrearEdad.getValue(), valorPass, 1);
									System.out.println("nuevo chico");
									CambiarPanel(ventanaCarga, ventanaMenuPrincipal);
								} else if (radioFemenino.isSelected() == true) {
									BaseDatosModise.nuevoUsuario(st, txtCrearNombre.getText(), txtCrearEmail.getText(),
											0, spinCrearEdad.getValue(), valorPass, 0);
									CambiarPanel(ventanaCarga, ventanaMenuPrincipal);
									System.out.println("nueva chica");
								} // hasta aqui bd nuevoUsuario

								// CambiarPanel(ventanaCarga, ventanaMenuPrincipal);

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

								Usuariolog.println("Creacion de cuenta: " + (new Date()) + "\n" + "Nombre: "
										+ txtCrearNombre.getText() + ", Email: " + txtCrearEmail.getText() + ", Edad: "
										+ spinCrearEdad.getValue() + ", Genero: "
										+ radioButtonsGenero.getSelection().getActionCommand() + "\nGustos:" + g1 + g2
										+ g3 + g4 + g5 + g6 + g7 + g8 + g9 + g10 + g11 + g12);

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

		// Añadiendo los componentes de ventanaCarga
		labelCargando = new JLabel("Cargando");
		ventanaCarga.add(labelCargando);
		labelCargando.setBounds(320, 170, 200, 50);

		progressCargando = new JProgressBar(0, MAX_STEPS);
		progressCargando.setValue(75);
		ventanaCarga.add(progressCargando);
		progressCargando.setBounds(200, 220, 300, 40);

		// Añadiendo los componentes de ventanaMenuPrincipal
		botonPideOutfit = new JButton("Pide un Outfit!");
		botonPideOutfit.setBounds(250, 150, 200, 50);
		ventanaMenuPrincipal.add(botonPideOutfit);

		botonAñadirVestimenta = new JButton("Añade tu propia Vestimenta");
		botonAñadirVestimenta.setBounds(250, 250, 200, 50);
		ventanaMenuPrincipal.add(botonAñadirVestimenta);

		botonMasMenosAdmin = new JButton("Admin +/-");
		botonMasMenosAdmin.setBounds(550, 40, 110, 30);
		ventanaMenuPrincipal.add(botonMasMenosAdmin);
		// botonMasMenosAdmin.setVisible(true);

		// ActionListeners
		botonPideOutfit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				CambiarPanel(ventanaMenuPrincipal, ventanaPideOutfit);
				mb.setVisible(false);
				mb.setEnabled(false);
			}
		});

		botonAñadirVestimenta.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				CambiarPanel(ventanaMenuPrincipal, ventanaAñadirVestimenta);
				mb.setVisible(false);
				mb.setEnabled(false);
			}
		});

		botonMasMenosAdmin.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				mb.setVisible(false);
				mb.setEnabled(false);
				CambiarPanel(ventanaMenuPrincipal, ventanaMasMenosAdmin);
			}
		});

		// Añadiendo los compenentes de ventanaPideOutfit
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
						l = estilosComboBoxAñadirVestimenta.getSelectedItem();
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

					// LLamar a la clase Crear Outfit

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

		estilosComboBoxPideOutfit.addMouseListener(new MouseAdapter() {

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

		// Añadiendo los componentes de ventanaAñadirVestimenta
		estilosLabelAñadirVestimenta = new JLabel("Selecciona un estilo: (F para Femenino y M para masculino)");
		estilosLabelAñadirVestimenta.setFont(new Font("Monospace", Font.BOLD, 13));
		colorLabelAñadirVestimenta = new JLabel("Selecciona un color: ");
		colorLabelAñadirVestimenta.setFont(new Font("Monospace", Font.BOLD, 13));
		tiempoLabelAñadirvestimenta = new JLabel("Selecciona el tiempo: ");
		tiempoLabelAñadirvestimenta.setFont(new Font("Monospace", Font.BOLD, 13));

		sol = new JRadioButton("Sol");
		sol.setActionCommand("Sol");
		lluvia = new JRadioButton("Lluvia");
		lluvia.setActionCommand("Lluvia");
		nublado = new JRadioButton("Nublado");
		nublado.setActionCommand("Nublado");

		estilosComboBoxAñadirVestimenta = new JComboBox<String>();
		coloresComboBoxAñadirVestimenta = new JComboBox<String>();

		estilosComboBoxAñadirVestimenta.addItem("ClasicoF");
		estilosComboBoxAñadirVestimenta.addItem("ClasicoM");
		estilosComboBoxAñadirVestimenta.addItem("UrbanaF");
		estilosComboBoxAñadirVestimenta.addItem("UrbanaM");
		estilosComboBoxAñadirVestimenta.addItem("RockF");
		estilosComboBoxAñadirVestimenta.addItem("RockM");
		estilosComboBoxAñadirVestimenta.addItem("BohoF");
		estilosComboBoxAñadirVestimenta.addItem("SmartM");
		estilosComboBoxAñadirVestimenta.addItem("FormalF");
		estilosComboBoxAñadirVestimenta.addItem("FormalM");
		estilosComboBoxAñadirVestimenta.addItem("SportyChickF");
		estilosComboBoxAñadirVestimenta.addItem("CasualChickM");

		coloresComboBoxAñadirVestimenta.addItem("Rojo");
		coloresComboBoxAñadirVestimenta.addItem("Azul");
		coloresComboBoxAñadirVestimenta.addItem("Amarillo");
		coloresComboBoxAñadirVestimenta.addItem("Verde");
		coloresComboBoxAñadirVestimenta.addItem("Negro");

		estilosLabelAñadirVestimenta.setBounds(40, 200, 400, 40);
		colorLabelAñadirVestimenta.setBounds(450, 200, 200, 40);
		tiempoLabelAñadirvestimenta.setBounds(40, 50, 200, 40);

		sol.setBounds(40, 100, 100, 40);
		lluvia.setBounds(160, 100, 100, 40);
		nublado.setBounds(280, 100, 100, 40);

		estilosComboBoxAñadirVestimenta.setBounds(40, 250, 100, 40);
		coloresComboBoxAñadirVestimenta.setBounds(450, 250, 100, 40);

		ButtonGroup radioButtonsTiempo = new ButtonGroup();
		radioButtonsTiempo.add(sol);
		radioButtonsTiempo.add(lluvia);
		radioButtonsTiempo.add(nublado);

		ventanaAñadirVestimentaAtras = new JButton("Atras");
		ventanaAñadirVestimenta.add(ventanaAñadirVestimentaAtras);
		ventanaAñadirVestimentaAtras.setBounds(10, 340, 200, 30);

		ventanaAñadirVestimentaAñadir = new JButton("Añadir");
		ventanaAñadirVestimenta.add(ventanaAñadirVestimentaAñadir);
		ventanaAñadirVestimentaAñadir.setBounds(500, 340, 200, 30);

		errorVentanaAñadirVestimenta = new JLabel();
		ventanaAñadirVestimenta.add(errorVentanaAñadirVestimenta);
		errorVentanaAñadirVestimenta.setBounds(300, 340, 400, 40);
		errorVentanaAñadirVestimenta.setForeground(Color.RED);

		ventanaAñadirVestimenta.add(sol);
		ventanaAñadirVestimenta.add(lluvia);
		ventanaAñadirVestimenta.add(nublado);
		ventanaAñadirVestimenta.add(estilosComboBoxAñadirVestimenta);
		ventanaAñadirVestimenta.add(coloresComboBoxAñadirVestimenta);
		ventanaAñadirVestimenta.add(estilosLabelAñadirVestimenta);
		ventanaAñadirVestimenta.add(colorLabelAñadirVestimenta);
		ventanaAñadirVestimenta.add(tiempoLabelAñadirvestimenta);

		// actionlisteners ventanaAñadirVestimenta
		ventanaAñadirVestimentaAtras.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				CambiarPanel(ventanaAñadirVestimenta, ventanaMenuPrincipal);
				radioButtonsTiempo.clearSelection();
				errorVentanaAñadirVestimenta.setText("");
				mb.setVisible(true);
				mb.setEnabled(true);
			}
		});

		ventanaAñadirVestimentaAñadir.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (radioButtonsTiempo.isSelected(null)) {
					errorVentanaAñadirVestimenta.setText("Selecciona el tiempo.");
				} else {
					Usuariolog
							.println("Añade vestimenta, tiempo: " + radioButtonsTiempo.getSelection().getActionCommand()
									+ ", estilo: " + estilosComboBoxAñadirVestimenta.getSelectedItem() + ", color: "
									+ coloresComboBoxAñadirVestimenta.getSelectedItem());
					// CambiarPanel(ventanaAñadirVestimenta, ???);
					FileChooser.Choose();
					CambiarPanel(ventanaAñadirVestimenta, ventanaMenuPrincipal);
					mb.setEnabled(true);
					mb.setVisible(true);
					radioButtonsTiempo.clearSelection();
					errorVentanaAñadirVestimenta.setText("");
				}
			}
		});

		// Añadiendo los componentes de ventanaFeedback
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

		// Añadiendo los componentes de ajustes
		cambiarContraseña = new JButton("Cambiar contraseña");
		cambiarContraseña.setBounds(50, 20, 50, 50);
		ajustes.add(cambiarContraseña);

		cambiarFecha = new JButton("Cambiar fecha");
		cambiarFecha.setBounds(50, 100, 50, 50);
		ajustes.add(cambiarFecha);

		reiniciarPerfil = new JButton("Reiniciar perfil");
		reiniciarPerfil.setBounds(50, 180, 50, 50);
		ajustes.add(reiniciarPerfil);

		// Action listeners OJO ESTO SOLO PARA PROBAR
		cambiarContraseña.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				String input = JOptionPane.showInputDialog(null, "Nueva contraseña:", "Cambiar contraseña.", 2);

				Connection conexion = Conexion.conectar();
				Statement st = null;
				try {
					st = conexion.createStatement();
				} catch (SQLException e1) {

				}
				if (!input.equals("")) {
					BaseDatosModise.cambiarContraseña(st, input, txtEmail.getText());
					System.out.println("Cambiar contraseña - nueva contraseña: " + input);
				} else {
					JOptionPane.showMessageDialog(null, "No se acepta contraseña vacia.", "Error",
							JOptionPane.ERROR_MESSAGE);
					System.out.println("Contraseña vacia o null, NO CAMBIADA");
				}
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

		// VentanaMasMenosAdmin

		labelEmailMasMenosAdmin = new JLabel("Introduzca el email de la cuenta que desea modificar: ");
		ventanaMasMenosAdmin.add(labelEmailMasMenosAdmin);
		labelEmailMasMenosAdmin.setBounds(25, 100, 350, 40);

		txtEmailMasMenosAdmin = new JTextField("");
		ventanaMasMenosAdmin.add(txtEmailMasMenosAdmin);
		txtEmailMasMenosAdmin.setBounds(375, 100, 300, 40);

		labelSelecionOperacionMasMenosAdmin = new JLabel("Selecione como quiere modificar esta cuenta: ");
		ventanaMasMenosAdmin.add(labelSelecionOperacionMasMenosAdmin);
		labelSelecionOperacionMasMenosAdmin.setBounds(25, 200, 300, 40);

		comboMasMenosAdministrador = new JComboBox<String>();
		comboMasMenosAdministrador.addItem("Hacer Administrador");
		comboMasMenosAdministrador.addItem("Quitar privilegios de Administrador");
		ventanaMasMenosAdmin.add(comboMasMenosAdministrador);
		comboMasMenosAdministrador.setBounds(375, 200, 300, 40);

		botonGuardarCambiosMasMenosAdmin = new JButton("Realizar cambios");
		ventanaMasMenosAdmin.add(botonGuardarCambiosMasMenosAdmin);
		botonGuardarCambiosMasMenosAdmin.setBounds(500, 350, 200, 40);

		botonatrasMasMenosAdmin = new JButton("Atras");
		ventanaMasMenosAdmin.add(botonatrasMasMenosAdmin);
		botonatrasMasMenosAdmin.setBounds(25, 350, 200, 40);

		labelErrorMasMenosAdmin = new JLabel("Error, email no valido, porfavor reviselo e intentelo otra vez.");
		ventanaMasMenosAdmin.add(labelErrorMasMenosAdmin);
		labelErrorMasMenosAdmin.setVisible(false);
		labelErrorMasMenosAdmin.setBounds(25, 400, 350, 40);
		labelErrorMasMenosAdmin.setForeground(Color.red);

		labelSuccessMasMenosAdmin = new JLabel();
		ventanaMasMenosAdmin.add(labelSuccessMasMenosAdmin);
		labelSuccessMasMenosAdmin.setVisible(false);
		labelSuccessMasMenosAdmin.setBounds(25, 400, 300, 40);

		// Action Lsiteners
		botonGuardarCambiosMasMenosAdmin.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("botonGuardarCambiosMasMenosAdmin");

				Connection conexion = Conexion.conectar();
				Statement st = null;
				try {
					st = conexion.createStatement();
				} catch (SQLException e1) {

				}

				if (comboMasMenosAdministrador.getSelectedIndex() == 0) {
					BaseDatosModise.cambiarAdmin(st, txtEmailMasMenosAdmin.getText(), 1);
					labelSuccessMasMenosAdmin.setText("Cambio realizado con Exito!");
				} else if (comboMasMenosAdministrador.getSelectedIndex() == 1) {
					BaseDatosModise.cambiarAdmin(st, txtEmailMasMenosAdmin.getText(), 0);
					labelSuccessMasMenosAdmin.setText("Cambio realizado con Exito!");
				}

				/*
				 * if (editarEmail.matches( "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" +
				 * "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$") &&
				 * !editarEmail.isEmpty()) {
				 * 
				 * // if (escrito1) { // comprobacion de que el email exista en la base de
				 * datos!
				 * 
				 * if (comboMasMenosAdministrador.getSelectedItem() == "Hacer Administrador") {
				 * // hacer el usuario en la base de datos y en la clase de usuarios
				 * administrador // (dejarlo en true el boolean admin)
				 * labelSuccessMasMenosAdmin.setVisible(true);
				 * 
				 * } else if (comboMasMenosAdministrador.getSelectedItem() ==
				 * "Quitar privilegios de Administrado") { // Hacer el boolean Admin de la BD y
				 * clase False labelSuccessMasMenosAdmin.setVisible(true); } // } } else {
				 * labelErrorMasMenosAdmin.setVisible(true); }
				 */

			}
		});

		botonatrasMasMenosAdmin.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				CambiarPanel(ventanaMasMenosAdmin, ventanaMenuPrincipal);
				labelSuccessMasMenosAdmin.setVisible(false);
				labelErrorMasMenosAdmin.setVisible(false);
				mb.setVisible(true);
				mb.setEnabled(true);
				txtEmailMasMenosAdmin.setText("");
			}
		});

		// actionlistener menu - cerrar sesion
		mi1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// Menu
				mb.setVisible(false);
				mb.setEnabled(false);

				// ventanaInicioSesion
				txtEmail.setText("ejemplo@gmail.com");
				contraseña.setText("12345");
				// view.setSelected(false); dejarlo asi
				escrito1 = false;
				escrito2 = false;

				// ventanaCrearCuenta
				txtCrearNombre.setText("nombre");
				txtCrearEmail.setText("ejemplo@gmail.com");
				txtCrearContraseña.setText("");
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

				// ventanaAñadirVestimenta
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
				UIManager.put("OptionPane.minimumSize", new Dimension(200, 200)); // este tamaño es solo para esta
																					// ventana emergente
				JOptionPane.showMessageDialog(null, ajustes, "Ajustes", JOptionPane.DEFAULT_OPTION);

			}
		});

		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				Usuariolog.println("Fin del programa.\n");
				Usuariolog.close();
			}
		});

		// Reloj
		clockLabel = new JLabel();
		clockLabel.setFont(new Font(clockLabel.getFont().getName(), Font.PLAIN, 15));
		clockLabel.setBounds(640, 0, 100, 20);

		ventanaMenuPrincipal.add(clockLabel);
		// lo ponemos en mas?

		Timer timer = new Timer(ONE_SECOND, new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				clockLabel.setText(clockFormat.format(new Date()));
				clockLabel.repaint();
			}
		});
		clockLabel.setText(clockFormat.format(new Date()));
		timer.start();
	}

	public static void main(String[] args) throws RWException {

		// log 1
		try {
			Usuariolog = new PrintStream(new FileOutputStream("Usuario.log", true));
		} catch (IOException e) {
			throw new RWException("Error de Input/Output", e);
		}
		Usuariolog.println("Inicio del programa.");
		// fin de log1

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new Principal();

			}
		});
		System.out.println(new Date());

	}

}
