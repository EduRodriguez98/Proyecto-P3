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
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.logging.FileHandler;
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
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

import conexion.BDException;
import conexion.BaseDatosModise;

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


	// VentanaGenero
	JRadioButton radioMasculino, radioFemenino;
	JLabel labelEscogerGenero, errorGenero;
	JButton botonGeneroSiguiente, botonGeneroAtras;

	// VentanaPerfilGustosUnoM
	JLabel labelCrearNombreM, labelCrearEmailM, labelCrearContraseñaM, labelCrearEdadM, errorNombreM, errorEmailM,
	errorContraseñaM, labelEstiloFavoritoM;
	JTextField txtCrearNombreM, txtCrearEmailM, txtCrearContraseñaM;
	JSpinner spinCrearEdadM;
	SpinnerModel modelM;
	JButton botonCrearSiguienteM, botonCrearAtrasM;
	boolean escrito3M, escrito4M;
	JButton botonPerfilGustosUnoMSiguiente, botonPerfilGustosUnoMAtras;
	JRadioButton clasicoM, urbanaM, rockM, smartM, formalM, casualChickM;
	JLabel errorPerfilGustosUnoM, labelColorPreferidoM;
	JComboBox<String> comboColorPreferidoM;
	

	// VentanaPerfilGustosUnoF
	JLabel labelCrearNombreF, labelCrearEmailF, labelCrearContraseñaF, labelCrearEdadF, errorNombreF, errorEmailF,
	errorContraseñaF, labelEstiloFavoritoF;
	JTextField txtCrearNombreF, txtCrearEmailF, txtCrearContraseñaF;
	JSpinner spinCrearEdadF;
	SpinnerModel modelF;
	JButton botonCrearSiguienteF, botonCrearAtrasF;
	boolean escrito3F, escrito4F;
	JButton botonPerfilGustosUnoFSiguienteF, botonPerfilGustosUnoFAtrasF;
	
	JRadioButton clasicoF, urbanaF, rockF, bohoF, formalF, sportyChickF;
	JLabel errorPerfilGustosUnoF, labelColorPreferidoF;
	JComboBox<String> comboColorPreferidoF;


	// VentanaCarga
	JProgressBar progressCargando;
	JLabel labelCargando;
	private int counter = 0;
	private boolean stop = false;
	private final int MAX_STEPS = 100000;

	// ventanaMenuPrincipal
	JMenuBar mb;
	JMenu menu1;
	JMenuItem mi1, mi2, mi3, mi4; // mi1 = cerrar sesion
	JButton botonPideOutfit, botonAñadirVestimenta, botonMasMenosAdmin;

	// ventanaAñadirVestimenta1
	
	JLabel tipoLabelAñadirVestimenta, estilosLabelAñadirVestimenta, colorLabelAñadirVestimenta, errorVentanaAñadirVestimenta1, nivelFashionLabel, nivelImpermeableLabel;
	JComboBox<String> coloresComboBoxAñadirVestimenta;
	JComboBox<String> estilosComboBoxAñadirVestimenta;
	JComboBox<String> tipoComboBoxAñadirVestimenta;
	JSpinner nivelFashionSpin, nivelImpermeableSpin;
	JButton ventanaAñadirVestimenta1Atras, ventanaAñadirVestimenta1Siguiente, ventanaAñadirVestimenta1Cancelar;
	
	
	// ventanaAñadirCamisetas
	JLabel importarFotoCamisetas, camisetasLogotipoLabel, camisetasRayasLabel, camisetasCuadrosLabel, camisetaChooserPreview;
	JRadioButton camisetasRayasSiRB, camisetasRayasNoRB, camisetasCuadrosSiRB, camisetasCuadrosNoRB;
	JButton atrasAñadirCamisetas, siguienteAñadirCamisetas, importarFotoCamisetaChooser;
	JTextField logotipoCamisetaTextField;
	
	// ventanaAñadirChaquetas
	JLabel importarFotoChaquetas, chaquetasLargoLabel, chaquetasLisaLabel, chaquetasChooserPreview;
	JRadioButton chaquetasLargoSiRB, chaquetasLargoNoRB, chaquetasLisaSiRB, chaquetasLisaNoRB;
	JButton atrasAñadirChaquetas, siguienteAñadirChaquetas, importarFotoChaquetaChooser, importarFotoChaquetasChooser;
	
	// ventanaAñadirGorros
	JLabel importarFotoGorros, gorrosTemporadaLabel, gorrosChooserPreview;
	JRadioButton gorrosVeranoSiRB, gorrosVeranoNoRB;
	JButton atrasAñadirGorros, siguienteAñadirGorros, importarFotoGorrosChooser;
	
	// ventanaAñadirPantalones
	JLabel importarFotoPantalones, pantalonesMarcaLabel, pantalonesLargoLabel, pantalonesChooserPreview;
	JRadioButton pantalonesLargoSiRB, pantalonesLargoNoRB;
	JTextField pantalonesMarcaText;
	JButton atrasAñadirPantalones, siguienteAñadirPantalones, importarFotoPantalonesChooser;
	
	// ventanaAñadirZapatos
	JLabel importarFotoZapatos, zapatosTipoLabel, zapatosChooserPreview;
	JRadioButton zapatosDeportivosRB, zapatosVestirRB;
	JButton atrasAñadirZapatos, siguienteAñadirZapatos, importarFotoZapatosChooser;
	

	// ventanaPideOutfit
	JButton botonAtrasPideOutfit, botonBuscar;
	JRadioButton radioSol, radioLluvia, radioNublado, radioNo, generoOutfitM, generoOutfitF;
	JLabel preguntaEstilo, preguntaTiempo, errorPideOutfit, preguntaColor, generoPregunta;
	JComboBox<String> estilosComboBoxPideOutfit, colorMenteComboBox;
	Boolean escrito5;
	

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
	public static Logger BDLogger;
	
	//Metodo Cargar Datos a JPanel
	
	public static void cargarJTable(Object[][] arrLleno, Object[][] arrV, int i, int j) {
		
		Object aux;
		
		aux = arrLleno[i][j];
		arrV[i][j] = aux;

		System.out.println(arrV[i][j]);
		
		if(i != arrLleno.length -1 || j != arrLleno[i].length -1) {
			
			if(j == arrLleno[i].length -1) {
				i++;
				j=0;
			
			}else {
				j++;
			}
			
			cargarJTable(arrLleno,arrV,i,j);
			
		}
		
	}

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
	public static void setProp(String mail, String password) {
		File archivo = new File("account.properties");
		try {
			FileOutputStream fos = new FileOutputStream(archivo);
			Properties propConfig = new Properties();

			propConfig.setProperty("correo", mail);
			propConfig.setProperty("contrasena", password);
			propConfig.store(fos, "program Settings");
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String getProp1() {
		File archivo = new File("account.properties");
		try {
			FileInputStream fis = new FileInputStream(archivo);
			Properties propConfig = new Properties();
			propConfig.load(fis);
			// cojemos las properties
			String nombre = propConfig.getProperty("correo");
			return nombre;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String getProp2() {
		File archivo = new File("account.properties");
		try {
			FileInputStream fis = new FileInputStream(archivo);
			Properties propConfig = new Properties();
			propConfig.load(fis);
			// cojemos las properties
			String contr = propConfig.getProperty("contrasena");
			return contr;
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
		mi3 = new JMenuItem("Salir sin cerrar sesion");
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

		PanelFondo ventanaAñadirVestimenta1 = new PanelFondo();
		PanelFondo ventanaInicioSesion = new PanelFondo();
		PanelFondo ventanaGenero = new PanelFondo();
		PanelFondo ventanaPerfilGustosUnoM = new PanelFondo();
		PanelFondo ventanaPerfilGustosUnoF = new PanelFondo();
		PanelFondo ventanaCarga = new PanelFondo();
		PanelFondo ventanaAñadirCamisetas = new PanelFondo();
		PanelFondo ventanaAñadirChaquetas = new PanelFondo();
		PanelFondo ventanaAñadirGorros = new PanelFondo();
		PanelFondo ventanaAñadirPantalones = new PanelFondo();
		PanelFondo ventanaAñadirZapatos = new PanelFondo();
		PanelFondo ventanaPideOutfit = new PanelFondo();
		PanelFondo ventanaFeedback = new PanelFondo();
		JPanel ventanaMasMenosAdmin = new JPanel(); // dejadla asi. NO, le he cambiado el nombre

		// ventanas Emergentes
		JPanel ajustes = new JPanel(new GridLayout(3, 1));
		JPanel ventanaEmergenteOutfit = new JPanel();

		CrearPanel(ventanaInicioSesion);
		CrearPanel(ventanaGenero);
		CrearPanel(ventanaPerfilGustosUnoM);
		CrearPanel(ventanaPerfilGustosUnoF);
		CrearPanel(ventanaMenuPrincipal);
		CrearPanel(ventanaCarga);
		CrearPanel(ventanaAñadirVestimenta1);
		CrearPanel(ventanaAñadirCamisetas);
		CrearPanel(ventanaAñadirChaquetas);
		CrearPanel(ventanaAñadirGorros);
		CrearPanel(ventanaAñadirPantalones);
		CrearPanel(ventanaAñadirZapatos);
		CrearPanel(ventanaPideOutfit);
		CrearPanel(ventanaFeedback);
		CrearPanel(ventanaMasMenosAdmin);

		frame.getContentPane().add(ventanaInicioSesion);
		frame.getContentPane().add(ventanaGenero);
		frame.getContentPane().add(ventanaPerfilGustosUnoM);
		frame.getContentPane().add(ventanaPerfilGustosUnoF);
		frame.getContentPane().add(ventanaMenuPrincipal);
		frame.getContentPane().add(ventanaCarga);
		frame.getContentPane().add(ventanaAñadirVestimenta1);
		frame.getContentPane().add(ventanaAñadirCamisetas);
		frame.getContentPane().add(ventanaAñadirChaquetas);
		frame.getContentPane().add(ventanaAñadirGorros);
		frame.getContentPane().add(ventanaAñadirPantalones);
		frame.getContentPane().add(ventanaAñadirZapatos);
		frame.getContentPane().add(ventanaPideOutfit);
		frame.getContentPane().add(ventanaFeedback);
		frame.getContentPane().add(ventanaMasMenosAdmin);

		ventanaInicioSesion.setVisible(true); // la primera ventana visible

		// Añadiendo los componentes de ventanaInicioSesion
		labelEmail = new JLabel("Email: ");
		labelEmail.setFont(new Font("Monospace", Font.BOLD, 13));
		ventanaInicioSesion.add(labelEmail);
		labelEmail.setBounds(60, 62, 100, 40);

		// txtEmail = new JTextField("ejemplo@gmail.com");
		txtEmail = new JTextField();
		txtEmail.setText(getProp1());
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

		// contraseña = new JPasswordField("12345"); // cambiado
		contraseña = new JPasswordField();
		contraseña.setText(getProp2());
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
				CambiarPanel(ventanaInicioSesion, ventanaGenero);

			}
		});

		botonInicio.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String valorPass = new String(contraseña.getPassword());
				// setProp(txtEmail.getText(), valorPass);

				Connection conexion = BaseDatosModise.conectar();
				Statement st = null;
				try {
					st = conexion.createStatement();
				} catch (SQLException e1) {

				}
				// Pasa el valor del JPassword a String
				// String valorPass = new String(contraseña.getPassword()); UNAS LINEAS MAS
				// ARRIBA
				if (BaseDatosModise.logIn(st, txtEmail.getText(), valorPass) == true) {
					CambiarPanel(ventanaInicioSesion, ventanaMenuPrincipal);
					Usuariolog.println("Inicio de sesion: " + txtEmail.getText() + "	, " + (new Date()));
					mb.setVisible(true);
					mb.setEnabled(true);
					if (BaseDatosModise.esAdmin(st, txtEmail.getText()) == true) {
						botonMasMenosAdmin.setVisible(true);
						System.out.println("VA ADMIN");
					} else if (BaseDatosModise.esAdmin(st, txtEmail.getText()) == false) {
						botonMasMenosAdmin.setVisible(false);
						System.out.println("VA NO ADMIN");
					}
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
				CambiarPanel(ventanaGenero, ventanaInicioSesion);
				radioButtonsGenero.clearSelection();
				errorGenero.setText("");
				txtCrearContraseñaM.setText("");
			}
		});

		
		
		
		// Añadiendo los componentes de ventanaPerfilGustosUnoM
		
		labelCrearNombreM = new JLabel("Introduzca su nombre: ");
		labelCrearNombreM.setFont(new Font("Monospace", Font.BOLD, 11));
		ventanaPerfilGustosUnoM.add(labelCrearNombreM);
		labelCrearNombreM.setBounds(20, 10, 200, 30);

		txtCrearNombreM = new JTextField("nombre");
		ventanaPerfilGustosUnoM.add(txtCrearNombreM);
		txtCrearNombreM.setBounds(230, 10, 200, 30);
		escrito3M = false;
		txtCrearNombreM.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (escrito3M == false) {
					txtCrearNombreM.setText("");
					escrito3M = true;
				}
			}
		});

		labelCrearEmailM = new JLabel("Introduzca su email: ");
		labelCrearEmailM.setFont(new Font("Monospace", Font.BOLD, 11));
		ventanaPerfilGustosUnoM.add(labelCrearEmailM);
		labelCrearEmailM.setBounds(20, 45, 200, 30);

		txtCrearEmailM = new JTextField("ejemplo@gmail.com");
		ventanaPerfilGustosUnoM.add(txtCrearEmailM);
		txtCrearEmailM.setBounds(230, 45, 200, 30);
		escrito4M = false;
		txtCrearEmailM.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (escrito4M == false) {
					txtCrearEmailM.setText("");
					escrito4M = true;
				}
			}
		});

		labelCrearContraseñaM = new JLabel("Cree una contrasena: ");
		labelCrearContraseñaM.setFont(new Font("Monospace", Font.BOLD, 11));
		ventanaPerfilGustosUnoM.add(labelCrearContraseñaM);
		labelCrearContraseñaM.setBounds(20, 80, 200, 30);

		txtCrearContraseñaM = new JTextField("");
		ventanaPerfilGustosUnoM.add(txtCrearContraseñaM);
		txtCrearContraseñaM.setBounds(230, 80, 200, 30);

		labelCrearEdadM = new JLabel("Seleccione su edad: ");
		labelCrearEdadM.setFont(new Font("Monospace", Font.BOLD, 11));
		ventanaPerfilGustosUnoM.add(labelCrearEdadM);
		labelCrearEdadM.setBounds(20, 115, 200, 30);

		SpinnerModel model = new SpinnerNumberModel(18, 0, 99, 1); // default 18, min 0, max 99, +-1
		// spinCrearEdad.setValue(18);
		spinCrearEdadM = new JSpinner(model);
		ventanaPerfilGustosUnoM.add(spinCrearEdadM);
		spinCrearEdadM.setBounds(230, 115, 80, 30);

		errorNombreM = new JLabel();
		ventanaPerfilGustosUnoM.add(errorNombreM);
		errorNombreM.setFont(new Font("Monospace", Font.BOLD, 11));
		errorNombreM.setBounds(230, 10, 150, 30);
		errorNombreM.setForeground(Color.RED);

		errorEmailM = new JLabel();
		errorEmailM.setFont(new Font("Monospace", Font.BOLD, 11));
		ventanaPerfilGustosUnoM.add(errorEmailM);
		errorEmailM.setBounds(230, 45, 150, 30);
		errorEmailM.setForeground(Color.RED);

		errorContraseñaM = new JLabel();
		errorContraseñaM.setFont(new Font("Monospace", Font.BOLD, 11));
		ventanaPerfilGustosUnoM.add(errorContraseñaM);
		errorContraseñaM.setBounds(230, 80, 150, 30);
		errorContraseñaM.setForeground(Color.RED);
		
		
		labelEstiloFavoritoM = new JLabel("Selecciona tu estilo favorito:");
		labelEstiloFavoritoM.setFont(new Font("Monospace", Font.BOLD, 11));
		labelEstiloFavoritoM.setBounds(20, 150, 200, 30);
		ventanaPerfilGustosUnoM.add(labelEstiloFavoritoM);
		
		clasicoM = new JRadioButton("Clasico");
		ventanaPerfilGustosUnoM.add(clasicoM);
		clasicoM.setBounds(20, 185, 150, 30);
		urbanaM = new JRadioButton("Urbana");
		ventanaPerfilGustosUnoM.add(urbanaM);
		urbanaM.setBounds(170, 185, 150, 30);
		rockM = new JRadioButton("Rock");
		ventanaPerfilGustosUnoM.add(rockM);
		rockM.setBounds(320, 185, 150, 30);
		smartM = new JRadioButton("Smart");
		ventanaPerfilGustosUnoM.add(smartM);
		smartM.setBounds(20, 215, 150, 30);
		formalM = new JRadioButton("Formal");
		ventanaPerfilGustosUnoM.add(formalM);
		formalM.setBounds(170, 215, 150, 30);
		casualChickM = new JRadioButton("Casual Chick");
		ventanaPerfilGustosUnoM.add(casualChickM);
		casualChickM.setBounds(320, 215, 150, 30);
		
		ButtonGroup bgM = new ButtonGroup();
		bgM.add(clasicoM);
		bgM.add(urbanaM);
		bgM.add(rockM);
		bgM.add(smartM);
		bgM.add(formalM);
		bgM.add(casualChickM);
		
		
		labelColorPreferidoM = new JLabel("Seleccione su color preferido");
		labelColorPreferidoM.setFont(new Font("Monospace", Font.BOLD, 11));
		labelColorPreferidoM.setBounds(20, 250, 200, 40);
		ventanaPerfilGustosUnoM.add(labelColorPreferidoM);
		
		comboColorPreferidoM = new JComboBox<String>();
		comboColorPreferidoM.setBounds(20, 285, 100, 40);
		comboColorPreferidoM.addItem("Rojo");
		comboColorPreferidoM.addItem("Amarillo");
		comboColorPreferidoM.addItem("Verde");
		comboColorPreferidoM.addItem("Negro");
		comboColorPreferidoM.addItem("Rosa");
		comboColorPreferidoM.addItem("Multicolor");
		comboColorPreferidoM.addItem("Blanco");
		comboColorPreferidoM.addItem("Gris");
		comboColorPreferidoM.addItem("Marron");

		ventanaPerfilGustosUnoM.add(comboColorPreferidoM);
		
		botonPerfilGustosUnoMAtras = new JButton("Atras");
		ventanaPerfilGustosUnoM.add(botonPerfilGustosUnoMAtras);
		botonPerfilGustosUnoMAtras.setBounds(10, 340, 200, 30);

		botonPerfilGustosUnoMSiguiente = new JButton("Siguiente");
		ventanaPerfilGustosUnoM.add(botonPerfilGustosUnoMSiguiente);
		botonPerfilGustosUnoMSiguiente.setBounds(500, 340, 200, 30);

		errorPerfilGustosUnoM = new JLabel();
		errorPerfilGustosUnoM.setFont(new Font("Monospace", Font.BOLD, 11));
		ventanaPerfilGustosUnoM.add(errorPerfilGustosUnoM);
		errorPerfilGustosUnoM.setBounds(300, 340, 400, 40);
		errorPerfilGustosUnoM.setForeground(Color.RED);

		// Action Listeners
		botonPerfilGustosUnoMSiguiente.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				CambiarPanel(ventanaPerfilGustosUnoM, ventanaCarga);
				
				if (clasicoM.isSelected() || urbanaM.isSelected() || rockM.isSelected() || smartM.isSelected()
						|| formalM.isSelected() || casualChickM.isSelected()) {
					
					errorPerfilGustosUnoM.setText("");

				} else {
					errorPerfilGustosUnoM.setText("Selecciona 1 estilo");
				}
				
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
							
							try {
								
								int colorseleccionado = 0;
								String estiloseleccionado = null;
								
								if(comboColorPreferidoM.getSelectedItem().toString() == "Rojo") {
									colorseleccionado = 1;
								} else if (comboColorPreferidoM.getSelectedItem().toString() == "Azul") {
									colorseleccionado = 2;
								} else if (comboColorPreferidoM.getSelectedItem().toString() == "Amarillo") {
									colorseleccionado = 3;
								} else if(comboColorPreferidoM.getSelectedItem().toString() == "Verde") {
									colorseleccionado = 4;
								} else if(comboColorPreferidoM.getSelectedItem().toString() == "Negro") {
									colorseleccionado = 5;
								} else if (comboColorPreferidoM.getSelectedItem().toString() == "Rosa") {
									colorseleccionado = 6;
								} else if(comboColorPreferidoM.getSelectedItem().toString() == "Multicolor") {
									colorseleccionado = 7;
								} else if(comboColorPreferidoM.getSelectedItem().toString() == "Blanco") {
									colorseleccionado = 8;
								} else if(comboColorPreferidoM.getSelectedItem().toString() == "Gris") {
									colorseleccionado = 9;
								} else if (comboColorPreferidoM.getSelectedItem().toString() == "Marron") {
									colorseleccionado = 10;
								} else {
									JOptionPane.showMessageDialog(ventanaPerfilGustosUnoM, "Debes escoger un color para continuar");
								}
								
								if (clasicoM.isSelected()) {
									estiloseleccionado = "clasicoM";
								} else if (urbanaM.isSelected()) {
									estiloseleccionado = "urbanaM";
								} else if (rockM.isSelected()) {
									estiloseleccionado = "rockM";
								} else if (smartM.isSelected()) {
									estiloseleccionado = "smartM";
								} else if (formalM.isSelected()) {
									estiloseleccionado = "formalM";
								} else if (casualChickM.isSelected()) {
									estiloseleccionado = "casualChickM";
								} else {
									JOptionPane.showMessageDialog(ventanaPerfilGustosUnoM, "Debes seleccionar un estilo favorito");
								}
								BaseDatosModise.nuevoUsuario(txtCrearNombreM.getText(), txtCrearEmailM.getText(), 0, (int)spinCrearEdadM.getValue(), txtCrearContraseñaM.getText(), 0, colorseleccionado, estiloseleccionado);
								
							} catch (BDException e) {
								e.printStackTrace();
							}
							CambiarPanel(ventanaCarga, ventanaMenuPrincipal);
						}
					}
				});  
				
				t.start();
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

		labelCrearNombreF = new JLabel("Introduzca su nombre: ");
		labelCrearNombreF.setFont(new Font("Monospace", Font.BOLD, 11));
		ventanaPerfilGustosUnoF.add(labelCrearNombreF);
		labelCrearNombreF.setBounds(20, 10, 200, 30);

		txtCrearNombreF = new JTextField("nombre");
		ventanaPerfilGustosUnoF.add(txtCrearNombreF);
		txtCrearNombreF.setBounds(230, 10, 200, 30);
		escrito3F = false;
		txtCrearNombreF.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (escrito3F == false) {
					txtCrearNombreF.setText("");
					escrito3F = true;
				}
			}
		});

		labelCrearEmailF = new JLabel("Introduzca su email: ");
		labelCrearEmailF.setFont(new Font("Monospace", Font.BOLD, 11));
		ventanaPerfilGustosUnoF.add(labelCrearEmailF);
		labelCrearEmailF.setBounds(20, 45, 200, 30);

		txtCrearEmailF = new JTextField("ejemplo@gmail.com");
		ventanaPerfilGustosUnoF.add(txtCrearEmailF);
		txtCrearEmailF.setBounds(230, 45, 200, 30);
		escrito4F = false;
		txtCrearEmailF.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (escrito4F == false) {
					txtCrearEmailF.setText("");
					escrito4F = true;
				}
			}
		});

		labelCrearContraseñaF = new JLabel("Cree una contrasena: ");
		labelCrearContraseñaF.setFont(new Font("Monospace", Font.BOLD, 11));
		ventanaPerfilGustosUnoF.add(labelCrearContraseñaM);
		labelCrearContraseñaF.setBounds(20, 80, 200, 30);

		txtCrearContraseñaF = new JTextField("");
		ventanaPerfilGustosUnoF.add(txtCrearContraseñaF);
		txtCrearContraseñaF.setBounds(230, 80, 200, 30);

		labelCrearEdadF = new JLabel("Seleccione su edad: ");
		labelCrearEdadF.setFont(new Font("Monospace", Font.BOLD, 11));
		ventanaPerfilGustosUnoF.add(labelCrearEdadF);
		labelCrearEdadF.setBounds(20, 115, 200, 30);

		SpinnerModel model2 = new SpinnerNumberModel(18, 0, 99, 1); // default 18, min 0, max 99, +-1
		// spinCrearEdad.setValue(18);
		spinCrearEdadF = new JSpinner(model2);
		ventanaPerfilGustosUnoF.add(spinCrearEdadF);
		spinCrearEdadF.setBounds(230, 115, 80, 30);

		errorNombreF = new JLabel();
		ventanaPerfilGustosUnoF.add(errorNombreF);
		errorNombreF.setFont(new Font("Monospace", Font.BOLD, 11));
		errorNombreF.setBounds(230, 10, 150, 30);
		errorNombreF.setForeground(Color.RED);

		errorEmailF = new JLabel();
		errorEmailF.setFont(new Font("Monospace", Font.BOLD, 11));
		ventanaPerfilGustosUnoF.add(errorEmailF);
		errorEmailF.setBounds(230, 45, 150, 30);
		errorEmailF.setForeground(Color.RED);

		errorContraseñaF = new JLabel();
		errorContraseñaF.setFont(new Font("Monospace", Font.BOLD, 11));
		ventanaPerfilGustosUnoF.add(errorContraseñaF);
		errorContraseñaF.setBounds(230, 80, 150, 30);
		errorContraseñaF.setForeground(Color.RED);
		
		
		labelEstiloFavoritoF = new JLabel("Selecciona tu estilo favorito:");
		labelEstiloFavoritoF.setFont(new Font("Monospace", Font.BOLD, 11));
		labelEstiloFavoritoF.setBounds(20, 150, 200, 30);
		ventanaPerfilGustosUnoF.add(labelEstiloFavoritoF);
		
		clasicoF = new JRadioButton("Clasico");
		ventanaPerfilGustosUnoF.add(clasicoF);
		clasicoF.setBounds(20, 185, 150, 30);
		urbanaF = new JRadioButton("Urbana");
		ventanaPerfilGustosUnoF.add(urbanaF);
		urbanaF.setBounds(170, 185, 150, 30);
		rockF = new JRadioButton("Rock");
		ventanaPerfilGustosUnoF.add(rockF);
		rockF.setBounds(320, 185, 150, 30);
		bohoF = new JRadioButton("Smart");
		ventanaPerfilGustosUnoF.add(bohoF);
		bohoF.setBounds(20, 215, 150, 30);
		formalF = new JRadioButton("Formal");
		ventanaPerfilGustosUnoF.add(formalF);
		formalF.setBounds(170, 215, 150, 30);
		sportyChickF = new JRadioButton("Sporty Chick");
		ventanaPerfilGustosUnoF.add(sportyChickF);
		sportyChickF.setBounds(320, 215, 150, 30);
		
		ButtonGroup bgF = new ButtonGroup();
		bgF.add(clasicoF);
		bgF.add(urbanaF);
		bgF.add(rockF);
		bgF.add(bohoF);
		bgF.add(formalF);
		bgF.add(sportyChickF);
		
		
		labelColorPreferidoF = new JLabel("Seleccione su color preferido");
		labelColorPreferidoF.setFont(new Font("Monospace", Font.BOLD, 11));
		labelColorPreferidoF.setBounds(20, 250, 200, 40);
		ventanaPerfilGustosUnoF.add(labelColorPreferidoF);
		
		comboColorPreferidoF = new JComboBox<String>();
		comboColorPreferidoF.setBounds(20, 285, 100, 40);
		comboColorPreferidoF.addItem("Rojo");
		comboColorPreferidoF.addItem("Amarillo");
		comboColorPreferidoF.addItem("Verde");
		comboColorPreferidoF.addItem("Negro");
		comboColorPreferidoF.addItem("Rosa");
		comboColorPreferidoF.addItem("Multicolor");
		comboColorPreferidoF.addItem("Blanco");
		comboColorPreferidoF.addItem("Gris");
		comboColorPreferidoF.addItem("Marron");

		ventanaPerfilGustosUnoF.add(comboColorPreferidoF);
		
		botonPerfilGustosUnoFAtrasF = new JButton("Atras");
		ventanaPerfilGustosUnoF.add(botonPerfilGustosUnoFAtrasF);
		botonPerfilGustosUnoFAtrasF.setBounds(10, 340, 200, 30);

		botonPerfilGustosUnoFSiguienteF = new JButton("Siguiente");
		ventanaPerfilGustosUnoF.add(botonPerfilGustosUnoFSiguienteF);
		botonPerfilGustosUnoFSiguienteF.setBounds(500, 340, 200, 30);

		errorPerfilGustosUnoF = new JLabel();
		errorPerfilGustosUnoF.setFont(new Font("Monospace", Font.BOLD, 11));
		ventanaPerfilGustosUnoF.add(errorPerfilGustosUnoF);
		errorPerfilGustosUnoF.setBounds(300, 340, 400, 40);
		errorPerfilGustosUnoF.setForeground(Color.RED);
		
		//ActionListeners ventanaPerfilGustosUnoF

		botonPerfilGustosUnoFSiguienteF.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				CambiarPanel(ventanaPerfilGustosUnoF, ventanaCarga);
				
				if (clasicoF.isSelected() || urbanaF.isSelected() || rockF.isSelected() || bohoF.isSelected()
						|| formalF.isSelected() || sportyChickF.isSelected()) {
					
					errorPerfilGustosUnoF.setText("");
				} else {
					errorPerfilGustosUnoF.setText("Selecciona 1 estilo");
				}
				
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
							
							try {
								
								int colorseleccionado = 0;
								String estiloseleccionado = null;
								
								if(comboColorPreferidoF.getSelectedItem().toString() == "Rojo") {
									colorseleccionado = 1;
								} else if (comboColorPreferidoF.getSelectedItem().toString() == "Azul") {
									colorseleccionado = 2;
								} else if (comboColorPreferidoF.getSelectedItem().toString() == "Amarillo") {
									colorseleccionado = 3;
								} else if(comboColorPreferidoF.getSelectedItem().toString() == "Verde") {
									colorseleccionado = 4;
								} else if(comboColorPreferidoF.getSelectedItem().toString() == "Negro") {
									colorseleccionado = 5;
								} else if (comboColorPreferidoF.getSelectedItem().toString() == "Rosa") {
									colorseleccionado = 6;
								} else if(comboColorPreferidoF.getSelectedItem().toString() == "Multicolor") {
									colorseleccionado = 7;
								} else if(comboColorPreferidoF.getSelectedItem().toString() == "Blanco") {
									colorseleccionado = 8;
								} else if(comboColorPreferidoF.getSelectedItem().toString() == "Gris") {
									colorseleccionado = 9;
								} else if (comboColorPreferidoF.getSelectedItem().toString() == "Marron") {
									colorseleccionado = 10;
								}else {
									JOptionPane.showMessageDialog(ventanaPerfilGustosUnoF, "Debes escoger un color para continuar");
								}
								
								if (clasicoF.isSelected()) {
									estiloseleccionado = "clasicoF";
								} else if (urbanaF.isSelected()) {
									estiloseleccionado = "urbanaF";
								} else if (rockF.isSelected()) {
									estiloseleccionado = "rockF";
								} else if (bohoF.isSelected()) {
									estiloseleccionado = "bohoF";
								} else if (formalF.isSelected()) {
									estiloseleccionado = "formalF";
								} else if (sportyChickF.isSelected()) {
									estiloseleccionado = "sportyChickF";
								} else {
									JOptionPane.showMessageDialog(ventanaPerfilGustosUnoF, "Debes seleccionar un estilo favorito");
								}
								BaseDatosModise.nuevoUsuario(txtCrearNombreF.getText(), txtCrearEmailF.getText(), 0, (int)spinCrearEdadF.getValue(), txtCrearContraseñaF.getText(), 1, colorseleccionado, estiloseleccionado);
								
							} catch (BDException e) {
								e.printStackTrace();
							}
							CambiarPanel(ventanaCarga, ventanaMenuPrincipal);
						}
					}
				});  
				
				t.start();
			}
		});

		botonPerfilGustosUnoFAtrasF.addActionListener(new ActionListener() {

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
				CambiarPanel(ventanaMenuPrincipal, ventanaAñadirVestimenta1);
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
		preguntaTiempo.setBounds(30, 0, 300, 40);

		radioSol = new JRadioButton("Sol");
		radioSol.setFont(new Font("Monospace", Font.PLAIN, 12));
		ventanaPideOutfit.add(radioSol);
		radioSol.setBounds(30, 50, 100, 40);
		radioLluvia = new JRadioButton("Lluvia");
		radioLluvia.setFont(new Font("Monospace", Font.PLAIN, 12));
		ventanaPideOutfit.add(radioLluvia);
		radioLluvia.setBounds(30, 100, 100, 40);
		
		preguntaColor = new JLabel("Tienes algun color en mente?");
		preguntaColor.setFont(new Font("Monospace", Font.BOLD, 13));
		ventanaPideOutfit.add(preguntaColor);
		preguntaColor.setBounds(370, 0, 300, 40);
		ventanaPideOutfit.add(preguntaColor);
		
		colorMenteComboBox = new JComboBox<String>();
		colorMenteComboBox.addItem("rojo");
		colorMenteComboBox.addItem("azul");
		colorMenteComboBox.addItem("amarillo");
		colorMenteComboBox.addItem("verde");
		colorMenteComboBox.addItem("negro");
		colorMenteComboBox.addItem("rosa");
		colorMenteComboBox.addItem("multicolor");
		colorMenteComboBox.addItem("blanco");
		colorMenteComboBox.addItem("gris");
		colorMenteComboBox.addItem("marron");

		
		colorMenteComboBox.setBounds(370, 50, 190, 30);
		ventanaPideOutfit.add(colorMenteComboBox);
		
		ButtonGroup bgPideOutfit = new ButtonGroup();
		bgPideOutfit.add(radioSol);
		bgPideOutfit.add(radioLluvia);
		
		preguntaEstilo = new JLabel("Tienes algun estilo en mente para tu outfit?");
		preguntaEstilo.setFont(new Font("Monospace", Font.BOLD, 13));
		ventanaPideOutfit.add(preguntaEstilo);
		preguntaEstilo.setBounds(30, 200, 300, 30);

		radioNo = new JRadioButton("No");
		radioNo.setFont(new Font("Monospace", Font.PLAIN, 12));
		ventanaPideOutfit.add(radioNo);
		radioNo.setBounds(30, 240, 50, 30);

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
		estilosComboBoxPideOutfit.setBounds(120, 240, 180, 30);

		generoPregunta = new JLabel("Seleccione el genero");
		generoPregunta.setFont(new Font("Monospace", Font.BOLD, 13));
		generoPregunta.setBounds(370, 200, 200, 30);
		ventanaPideOutfit.add(generoPregunta);
		generoOutfitM = new JRadioButton("M");
		generoOutfitM.setFont(new Font("Monospace", Font.PLAIN, 12));
		generoOutfitM.setBounds(370, 240, 50, 30);
		generoOutfitF = new JRadioButton("F");
		generoOutfitF.setFont(new Font("Monospace", Font.PLAIN, 12));
		generoOutfitF.setBounds(420, 240, 50, 30);
		ButtonGroup bgGenero = new ButtonGroup();
		bgGenero.add(generoOutfitM);
		bgGenero.add(generoOutfitF);
		ventanaPideOutfit.add(generoOutfitM);
		ventanaPideOutfit.add(generoOutfitF);
		
		
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

				if (bgPideOutfit.getSelection() != null
						&& (radioNo.isSelected() || estilosComboBoxPideOutfit.getSelectedIndex() != -1) && bgGenero.getSelection() != null && colorMenteComboBox.getSelectedItem() != null) {

					Object l;
					if (radioNo.isSelected()) {
						l = "No";
					} else {
						l = estilosComboBoxAñadirVestimenta.getSelectedItem();
					}
					Usuariolog.println("Pide Outfit, tiempo: " + bgPideOutfit.getSelection().getActionCommand()
							+ ", estilo: " + l + "genero: " + bgGenero.getSelection().getActionCommand() + "color: " + colorMenteComboBox.getSelectedItem().toString());
					
					CambiarPanel(ventanaPideOutfit, ventanaFeedback);

					UIManager.put("OptionPane.minimumSize", new Dimension(500, 800));
					
					
					int color = 1;
					
					
					String nombreColorSeleccionadoPO = colorMenteComboBox.getSelectedItem().toString();
					
					if(nombreColorSeleccionadoPO == "rojo") {
						color = 1;
					} else if (nombreColorSeleccionadoPO == "azul") {
						color = 2;
					} else if (nombreColorSeleccionadoPO == "amarillo") {
						color = 3;
					} else if (nombreColorSeleccionadoPO == "verde") {
						color = 4;
					} else if (nombreColorSeleccionadoPO == "negro") {
						color = 5;
					} else if (nombreColorSeleccionadoPO == "rosa") {
						color = 6;
					} else if (nombreColorSeleccionadoPO == "multicolor") {
						color = 7;
					} else if (nombreColorSeleccionadoPO == "blanco") {
						color = 8;
					} else if (nombreColorSeleccionadoPO == "Gris") {
						color = 9;
					} else if (nombreColorSeleccionadoPO == "Marron") {
						color = 10;
					} 
					
						HashMap<Integer, byte[]> outfitSolMap=null;
					
					if(radioSol.isSelected() && !radioNo.isSelected()) {
						
						try {
							
							if (generoOutfitM.isSelected()) {
							outfitSolMap = BaseDatosModise.crearOutfitSoleado(estilosComboBoxPideOutfit.getSelectedItem().toString(), 0, color);
							} else if (generoOutfitF.isSelected()) {
								outfitSolMap = BaseDatosModise.crearOutfitSoleado(estilosComboBoxPideOutfit.getSelectedItem().toString(), 1, color);

							}
						
							
							//Comprobamos que el HashMap se cree correctamente!PASAR A LOG!!
							System.out.println(Collections.singletonList(outfitSolMap));
							
							
							Object[][] arrOutfitSol = new Object[outfitSolMap.size()][2];
							@SuppressWarnings("rawtypes")
							Set entries = outfitSolMap.entrySet();
							@SuppressWarnings("rawtypes")
							Iterator entriesIterator = entries.iterator();
							
							int i = 0;
							
							while(entriesIterator.hasNext()) {
								@SuppressWarnings("unchecked")
								Map.Entry<Integer, byte[]> mapping = (Map.Entry<Integer, byte[]>) entriesIterator.next();
								
								arrOutfitSol [i][0] = mapping.getKey();
								arrOutfitSol [i][1]= mapping.getValue();
								
								i++;
							}
							//ventanaEmergentePideOutfit
							
							JTable jt = new JTable();
								jt.setPreferredSize(new Dimension(400, 850));
								jt.setRowHeight(0, 50);
								jt.setRowHeight(1, 150);
								jt.setRowHeight(2, 150);
								jt.setRowHeight(3, 150);
								jt.setRowHeight(4, 150);
								jt.setRowHeight(5, 150);
								
								String [] cols = {"idprendas", "foto"};
								Object [][] rowsData = {};
								
								cargarJTable(arrOutfitSol,rowsData,0,0);
								
								DefaultTableModel dtm = (DefaultTableModel) jt.getModel();
								dtm.setDataVector(rowsData, cols);
								
								ventanaEmergenteOutfit.add(jt);
							
						} catch (BDException | SQLException e1) {
							
							e1.printStackTrace();
						}

						
					}	else if (radioLluvia.isSelected() && !radioNo.isSelected()) {
						HashMap<Integer, byte[]> outfitLluviaMap = null;
						try {
							
							if (generoOutfitM.isSelected()) {
								outfitLluviaMap = BaseDatosModise.crearOutfitLluvioso(estilosComboBoxPideOutfit.getSelectedItem().toString(), 0, color);
							} else if (generoOutfitF.isSelected()) {
								outfitLluviaMap = BaseDatosModise.crearOutfitLluvioso(estilosComboBoxPideOutfit.getSelectedItem().toString(), 1, color);
							} 
							
							Object[][] arrOutfitLluvia = new Object[outfitLluviaMap.size()][2];
							@SuppressWarnings("rawtypes")
							Set entries = outfitLluviaMap.entrySet();
							@SuppressWarnings("rawtypes")
							Iterator entriesIterator = entries.iterator();
							
							int i = 0;
							
							while(entriesIterator.hasNext()) {
								@SuppressWarnings("unchecked")
								Map.Entry<Integer, byte[]> mapping = (Map.Entry<Integer, byte[]>) entriesIterator.next();
								
								arrOutfitLluvia [i][0] = mapping.getKey();
								arrOutfitLluvia  [i][1]= mapping.getValue();
								
								i++;
							}
							
							//ventanaEmergentePideOutfit
							
							JTable jt = new JTable();
								jt.setPreferredSize(new Dimension(400, 850));
								jt.setRowHeight(0, 50);
								jt.setRowHeight(1, 150);
								jt.setRowHeight(2, 150);
								jt.setRowHeight(3, 150);
								jt.setRowHeight(4, 150);
								jt.setRowHeight(5, 150);
								
								String [] cols = {"idprendas", "foto"};
								Object [][] rowsData = {};
								
								
								cargarJTable(arrOutfitLluvia,rowsData,0,0);
								
								
								DefaultTableModel dtm = (DefaultTableModel) jt.getModel();
								dtm.setDataVector(rowsData, cols);
							
								ventanaEmergenteOutfit.add(jt);
							
						} catch (BDException | SQLException e1) {
							
							e1.printStackTrace();
						}
					}
						
					
					
					
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
		
		
		// Añadiendo los componentes de ventanaAñadirVestimenta1
		tipoLabelAñadirVestimenta = new JLabel("Selecione el tipo de prenda que desea añadir: ");
		tipoLabelAñadirVestimenta.setFont(new Font("Monospace", Font.BOLD, 13));
		estilosLabelAñadirVestimenta = new JLabel("Selecciona un estilo: (F para Femenino y M para masculino)");
		estilosLabelAñadirVestimenta.setFont(new Font("Monospace", Font.BOLD, 13));
		colorLabelAñadirVestimenta = new JLabel("Selecciona un color: ");
		colorLabelAñadirVestimenta.setFont(new Font("Monospace", Font.BOLD, 13));

		tipoComboBoxAñadirVestimenta = new JComboBox<String>();
		estilosComboBoxAñadirVestimenta = new JComboBox<String>();
		coloresComboBoxAñadirVestimenta = new JComboBox<String>();

		tipoComboBoxAñadirVestimenta.addItem("camisetas");
		tipoComboBoxAñadirVestimenta.addItem("chaquetas");
		tipoComboBoxAñadirVestimenta.addItem("gorros");
		tipoComboBoxAñadirVestimenta.addItem("pantalones");
		tipoComboBoxAñadirVestimenta.addItem("zapatos");
		
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
		coloresComboBoxAñadirVestimenta.addItem("Rosa");
		coloresComboBoxAñadirVestimenta.addItem("Multicolor");
		coloresComboBoxAñadirVestimenta.addItem("Blanco");
		coloresComboBoxAñadirVestimenta.addItem("Gris");
		coloresComboBoxAñadirVestimenta.addItem("Marron");


		tipoLabelAñadirVestimenta.setBounds(190, 50, 400, 40);
		estilosLabelAñadirVestimenta.setBounds(190, 100, 400, 40);
		colorLabelAñadirVestimenta.setBounds(190, 150, 400, 40);

		tipoComboBoxAñadirVestimenta.setBounds(40, 50, 150, 40);
		estilosComboBoxAñadirVestimenta.setBounds(40, 100, 150, 40);
		coloresComboBoxAñadirVestimenta.setBounds(40, 150, 150, 40);

		ventanaAñadirVestimenta1Atras = new JButton("Atras");
		ventanaAñadirVestimenta1.add(ventanaAñadirVestimenta1Atras);
		ventanaAñadirVestimenta1Atras.setBounds(10, 340, 200, 30);

		ventanaAñadirVestimenta1Siguiente = new JButton("Siguiente");
		ventanaAñadirVestimenta1.add(ventanaAñadirVestimenta1Siguiente);
		ventanaAñadirVestimenta1Siguiente.setBounds(500, 340, 200, 30);

		errorVentanaAñadirVestimenta1 = new JLabel();
		ventanaAñadirVestimenta1.add(errorVentanaAñadirVestimenta1);
		errorVentanaAñadirVestimenta1.setBounds(300, 340, 400, 40);
		errorVentanaAñadirVestimenta1.setForeground(Color.RED);

		ventanaAñadirVestimenta1Cancelar = new JButton("cancelar");
		ventanaAñadirVestimenta1Cancelar.setVisible(false);
		ventanaAñadirVestimenta1Cancelar.setBounds(10, 340, 200, 30);
		
		//NO OLVIDARSE ANYADIR LIMITE PARA LOS SPINNERS LUEGO!!
		nivelFashionLabel = new JLabel("Seleccione nivel de Fashion entre 0-100 (100 en tendencias, 0 no en tendencias)");
		nivelFashionLabel.setFont(new Font("Monospace", Font.BOLD, 11));
		nivelFashionLabel.setBounds(190, 200, 500, 40);
		nivelFashionSpin = new JSpinner();
		nivelFashionSpin.setValue(0);
		nivelFashionSpin.setBounds(40, 200, 150, 40);
		
		//NO OLVIDARSE ANYADIR LIMITE PARA LOS SPINNERS LUEGO!!
		nivelImpermeableLabel = new JLabel("Seleccione nivel de impermeabilidad entre 0-100 (100 impermeable, 0 no impermeable)");
		nivelImpermeableLabel.setFont(new Font("Monospace", Font.BOLD, 11));
		nivelImpermeableLabel.setBounds(190, 250, 500, 40);
		nivelImpermeableSpin = new JSpinner();
		nivelImpermeableSpin.setValue(0);
		nivelImpermeableSpin.setBounds(40, 250, 150, 40);
		
		ventanaAñadirVestimenta1.add(ventanaAñadirVestimenta1Cancelar);
		ventanaAñadirVestimenta1.add(tipoLabelAñadirVestimenta);
		ventanaAñadirVestimenta1.add(tipoComboBoxAñadirVestimenta);
		ventanaAñadirVestimenta1.add(estilosComboBoxAñadirVestimenta);
		ventanaAñadirVestimenta1.add(coloresComboBoxAñadirVestimenta);
		ventanaAñadirVestimenta1.add(estilosLabelAñadirVestimenta);
		ventanaAñadirVestimenta1.add(colorLabelAñadirVestimenta);
		ventanaAñadirVestimenta1.add(nivelFashionLabel);
		ventanaAñadirVestimenta1.add(nivelImpermeableLabel);
		ventanaAñadirVestimenta1.add(nivelFashionSpin);
		ventanaAñadirVestimenta1.add(nivelImpermeableSpin);
	

		// actionlisteners ventanaAñadirVestimenta
		ventanaAñadirVestimenta1Atras.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				CambiarPanel(ventanaAñadirVestimenta1, ventanaMenuPrincipal);
				errorVentanaAñadirVestimenta1.setText("");
				mb.setVisible(true);
				mb.setEnabled(true);
			}
		});

		ventanaAñadirVestimenta1Siguiente.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

			String nombreColorSeleccionado = coloresComboBoxAñadirVestimenta.getSelectedItem().toString();
			int idColorSeleccionado = 0;
			if (nombreColorSeleccionado == "Rojo") {
				idColorSeleccionado = 1;
			} else if (nombreColorSeleccionado == "Azul") {
				idColorSeleccionado = 2;
			} else if (nombreColorSeleccionado == "Amarillo") {
				idColorSeleccionado = 3;
			} else if (nombreColorSeleccionado == "Verde") {
				idColorSeleccionado = 4;
			} else if (nombreColorSeleccionado == "Negro") {
				idColorSeleccionado = 5;
			} else if (nombreColorSeleccionado == "Rosa") {
				idColorSeleccionado = 6;
			} else if (nombreColorSeleccionado == "Multicolor") {
				idColorSeleccionado = 7;
			} else if (nombreColorSeleccionado == "Blanco"){
				idColorSeleccionado = 8;
			} else if (nombreColorSeleccionado == "Gris"){
				idColorSeleccionado = 9;
			} else if (nombreColorSeleccionado == "Marron"){
				idColorSeleccionado = 10;
			} 
			
			int nivelFashionSeleccionado = (int)nivelFashionSpin.getValue();
			int nivelImpermeableSeleccionado = (int)nivelImpermeableSpin.getValue();
			
			String estiloPrendasSeleccionado = estilosComboBoxAñadirVestimenta.getSelectedItem().toString();
			Boolean generoPrendas = null;
			
			if (estiloPrendasSeleccionado == "ClasicoF" || estiloPrendasSeleccionado == "UrbanaF" || estiloPrendasSeleccionado == "RockF" ||
					estiloPrendasSeleccionado == "BohoF" || estiloPrendasSeleccionado == "FormalF" || estiloPrendasSeleccionado == "SportyChickF") {
				generoPrendas = true;
	
			} else {
				generoPrendas = false;
			} 
		
				
				try {
					
					//Metodo BD para anyadir prenda
					
					BaseDatosModise.añadirPrenda(idColorSeleccionado, estiloPrendasSeleccionado, generoPrendas, nivelFashionSeleccionado, nivelImpermeableSeleccionado);

					//Cambiar paneles
					String tipoPrenda = tipoComboBoxAñadirVestimenta.getSelectedItem().toString();
					if (tipoPrenda == "camisetas") {
						CambiarPanel(ventanaAñadirVestimenta1, ventanaAñadirCamisetas);
					} else if(tipoPrenda == "chaquetas") {
						CambiarPanel(ventanaAñadirVestimenta1, ventanaAñadirChaquetas);
					} else if(tipoPrenda == "gorros") {
						CambiarPanel(ventanaAñadirVestimenta1, ventanaAñadirGorros);
					} else if(tipoPrenda == "pantalones") {
						CambiarPanel(ventanaAñadirVestimenta1, ventanaAñadirPantalones);
					} else if (tipoPrenda == "zapatos"){
						CambiarPanel(ventanaAñadirVestimenta1, ventanaAñadirZapatos);
					}
			
					mb.setEnabled(true);
					mb.setVisible(true);
					errorVentanaAñadirVestimenta1.setText("");  
					
				} catch (BDException e1) {
					e1.printStackTrace();
				}
			
			
			}	 
		});
		
		ventanaAñadirVestimenta1Cancelar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				try {
					BaseDatosModise.eliminarUltimaPrenda();
				} catch (BDException e1) {
					e1.printStackTrace();
				}
				
				CambiarPanel(ventanaAñadirVestimenta1, ventanaMenuPrincipal);
			}
		});
		
		
		
		
		//Añadiendo los componentes de ventanaAñadirCamiseta
		
			//Importar foto
		
			
			importarFotoCamisetas = new JLabel("Seleccionar foto para importar de la camiseta: ");
			importarFotoCamisetas.setBounds(40, 50, 300, 40);
			
			//aqui hay que poner el filechooser y hacer el metodo de subida de fotos a la base de datos <<
			importarFotoCamisetaChooser = new JButton("Seleccionar Foto");
			importarFotoCamisetaChooser.setBounds(360, 60, 140, 30);
			
			camisetaChooserPreview = new JLabel(" ");
			camisetaChooserPreview.setBounds(40, 100, 550, 30);
			
			camisetasLogotipoLabel = new JLabel("Tiene logotipo la camiseta?: ");
			camisetasLogotipoLabel.setBounds(40, 130, 300, 40);
			logotipoCamisetaTextField = new JTextField();
			logotipoCamisetaTextField.setBounds(360, 140, 200, 30);
			
			
			camisetasRayasLabel = new JLabel("Tiene Rayas la camiseta?: ");
			camisetasRayasLabel.setBounds(40,190, 300, 40);
			camisetasRayasSiRB = new JRadioButton("Si");
			camisetasRayasSiRB.setBounds(360, 190, 50, 40);
			camisetasRayasNoRB = new JRadioButton("No");
			camisetasRayasNoRB.setBounds(430, 190, 50, 40);
			ButtonGroup radioRayasBG = new ButtonGroup();
			radioRayasBG.add(camisetasRayasSiRB);
			radioRayasBG.add(camisetasRayasNoRB);
			
			camisetasCuadrosLabel = new JLabel("La camiseta es a cuadros?");
			camisetasCuadrosLabel.setBounds(40, 240, 300, 40);
			camisetasCuadrosSiRB = new JRadioButton("Si");
			camisetasCuadrosSiRB.setBounds(360, 240, 50, 40);
			camisetasCuadrosNoRB = new JRadioButton("No");
			camisetasCuadrosNoRB.setBounds(430, 240, 50, 40);
			ButtonGroup radioCuadrosBG = new ButtonGroup();
			radioCuadrosBG.add(camisetasCuadrosSiRB);
			radioCuadrosBG.add(camisetasCuadrosNoRB);
			
			atrasAñadirCamisetas = new JButton("Atrás");
			atrasAñadirCamisetas.setBounds(10, 340, 200, 30);
			
			siguienteAñadirCamisetas = new JButton("Siguiente");
			siguienteAñadirCamisetas.setBounds(500, 340, 200, 30);
			
			ventanaAñadirCamisetas.add(importarFotoCamisetas);
			ventanaAñadirCamisetas.add(camisetaChooserPreview);
			ventanaAñadirCamisetas.add(camisetasLogotipoLabel);
			ventanaAñadirCamisetas.add(importarFotoCamisetaChooser);
			ventanaAñadirCamisetas.add(logotipoCamisetaTextField);
			ventanaAñadirCamisetas.add(camisetasRayasLabel);
			ventanaAñadirCamisetas.add(camisetasRayasSiRB);
			ventanaAñadirCamisetas.add(camisetasRayasNoRB);
			ventanaAñadirCamisetas.add(camisetasCuadrosLabel);
			ventanaAñadirCamisetas.add(camisetasCuadrosSiRB);
			ventanaAñadirCamisetas.add(camisetasCuadrosNoRB);
			ventanaAñadirCamisetas.add(atrasAñadirCamisetas);
			ventanaAñadirCamisetas.add(siguienteAñadirCamisetas);
			
			importarFotoCamisetaChooser.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					JFileChooser chooser = new JFileChooser();
					chooser.showOpenDialog(null);
					File f = chooser.getSelectedFile();
					String filename = f.getAbsolutePath();
					
					camisetaChooserPreview.setText(filename);
					
				}
			});
			
			atrasAñadirCamisetas.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {

					
					CambiarPanel(ventanaAñadirCamisetas, ventanaAñadirVestimenta1);
					ventanaAñadirVestimenta1Cancelar.setVisible(true);
					ventanaAñadirVestimenta1Atras.setVisible(false);
				}
			});
			
			siguienteAñadirCamisetas.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					
					Boolean rayasSiNo = null;
					if ( camisetasRayasSiRB.isSelected()) {
						rayasSiNo = true;
					} else if (camisetasRayasNoRB.isSelected()){
						rayasSiNo = false;
					} else {
						JOptionPane.showMessageDialog(ventanaAñadirCamisetas, "Debes seleccionar si tiene rayas o no la camiseta");
					}
					
					Boolean cuadrosSiNo = null;
					if (camisetasCuadrosSiRB.isSelected()) {
						cuadrosSiNo = true;
					} else if (camisetasCuadrosNoRB.isSelected()) {
						cuadrosSiNo = false;
					} else {
						JOptionPane.showMessageDialog(ventanaAñadirCamisetas, "Debes seleccionar si tiene cuadros o no la camiseta");

					}
					
						try {
							BaseDatosModise.añadirCamiseta(logotipoCamisetaTextField.getSelectedText(), rayasSiNo, cuadrosSiNo, camisetaChooserPreview.getText());
						} catch (FileNotFoundException | BDException e1) {
							e1.printStackTrace();
						}
				
						CambiarPanel(ventanaAñadirCamisetas, ventanaMenuPrincipal);
				}
			});
		
		//Añadiendo los componentes de ventanaAñadirChaquetas
			
			importarFotoChaquetas = new JLabel("Seleccionar foto para importar de la chaqueta: ");
			importarFotoChaquetas.setBounds(40, 50, 300, 40);
			
			//aqui hay que poner el filechooser y hacer el metodo de subida de fotos a la base de datos <<
			importarFotoChaquetasChooser = new JButton("Seleccionar Foto");
			importarFotoChaquetasChooser.setBounds(360, 60, 140, 30);
			
			chaquetasChooserPreview = new JLabel(" ");
			chaquetasChooserPreview.setBounds(40, 100, 550, 30);
	
			
			chaquetasLargoLabel = new JLabel("Es larga la chaqueta?: ");
			chaquetasLargoLabel.setBounds(40, 150, 300, 40);
			chaquetasLargoSiRB = new JRadioButton("Si");
			chaquetasLargoSiRB.setBounds(360, 150, 50, 40);
			chaquetasLargoNoRB = new JRadioButton("No");
			chaquetasLargoNoRB.setBounds(430, 150, 50, 40);
			ButtonGroup radioLargoBG = new ButtonGroup();
			radioLargoBG.add(chaquetasLargoSiRB);
			radioLargoBG.add(chaquetasLargoNoRB);
			
			chaquetasLisaLabel = new JLabel("La chaqueta es lisa?: ");
			chaquetasLisaLabel.setBounds(40,200, 300, 40);
			chaquetasLisaSiRB = new JRadioButton("Si");
			chaquetasLisaSiRB.setBounds(360, 200, 50, 40);
			chaquetasLisaNoRB = new JRadioButton("No");
			chaquetasLisaNoRB.setBounds(430, 200, 50, 40);
			ButtonGroup radioLisaBG = new ButtonGroup();
			radioLisaBG.add(chaquetasLisaSiRB);
			radioLisaBG.add(chaquetasLisaNoRB);
			
			atrasAñadirChaquetas = new JButton("Atrás");
			atrasAñadirChaquetas.setBounds(10, 340, 200, 30);
			
			siguienteAñadirChaquetas = new JButton("Siguiente");
			siguienteAñadirChaquetas.setBounds(500, 340, 200, 30);
			
			ventanaAñadirChaquetas.add(importarFotoChaquetas);
			ventanaAñadirChaquetas.add(importarFotoChaquetasChooser);
			ventanaAñadirChaquetas.add(chaquetasChooserPreview);
			ventanaAñadirChaquetas.add(chaquetasLargoLabel);
			ventanaAñadirChaquetas.add(chaquetasLargoSiRB);
			ventanaAñadirChaquetas.add(chaquetasLargoNoRB);
			ventanaAñadirChaquetas.add(chaquetasLisaLabel);
			ventanaAñadirChaquetas.add(chaquetasLisaSiRB);
			ventanaAñadirChaquetas.add(chaquetasLisaNoRB);
			ventanaAñadirChaquetas.add(atrasAñadirChaquetas);
			ventanaAñadirChaquetas.add(siguienteAñadirChaquetas);
			
			
			importarFotoChaquetasChooser.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					JFileChooser chooser = new JFileChooser();
					chooser.showOpenDialog(null);
					File f = chooser.getSelectedFile();
					String filename = f.getAbsolutePath();
					
					chaquetasChooserPreview.setText(filename);
					
				}
			});
			
			atrasAñadirChaquetas.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					CambiarPanel(ventanaAñadirChaquetas, ventanaAñadirVestimenta1);
					ventanaAñadirVestimenta1Cancelar.setVisible(true);
					ventanaAñadirVestimenta1Atras.setVisible(false);
					
				}
			});			
			
			
			siguienteAñadirChaquetas.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					
					
					Boolean largaSiNo = null;
					if ( chaquetasLargoSiRB.isSelected()) {
						largaSiNo = true;
					} else if (chaquetasLargoNoRB.isSelected()){
						largaSiNo = false;
					} else {
						JOptionPane.showMessageDialog(ventanaAñadirChaquetas, "Debes seleccionar si es larga o no la chaqueta");
					}
					
					Boolean lisaSiNo = null;
					if (chaquetasLisaSiRB.isSelected()) {
						lisaSiNo = true;
					} else if (chaquetasLisaNoRB.isSelected()) {
						lisaSiNo = false;
					} else {
						JOptionPane.showMessageDialog(ventanaAñadirChaquetas, "Debes seleccionar si es lisa o no la chaqueta");

					}
					
						try {
							BaseDatosModise.añadirChaquetas(largaSiNo, lisaSiNo, chaquetasChooserPreview.getText());
						} catch (FileNotFoundException | BDException e1) {
							e1.printStackTrace();
						}
					
					
					
					CambiarPanel(ventanaAñadirChaquetas, ventanaMenuPrincipal);
					
				}
			});
			
			
			
		//Añadiendo los componentes de ventanaAñadirGorros
			
			//Importar foto
			importarFotoGorros = new JLabel("Seleccionar foto para importar de la prenda: ");
			importarFotoGorros.setBounds(40, 50, 300, 40);
			
			//aqui hay que poner el filechooser y hacer el metodo de subida de fotos a la base de datos <<
			importarFotoGorrosChooser = new JButton("Seleccionar Foto");
			importarFotoGorrosChooser.setBounds(360, 60, 140, 30);
			
			gorrosChooserPreview = new JLabel(" ");
			gorrosChooserPreview.setBounds(40, 100, 550, 30);
			
			gorrosTemporadaLabel = new JLabel("Es de verano el gorro?: ");
			gorrosTemporadaLabel.setBounds(40,200, 300, 40);
			gorrosVeranoSiRB = new JRadioButton("Si");
			gorrosVeranoSiRB.setBounds(360, 200, 50, 40);
			gorrosVeranoNoRB = new JRadioButton("No");
			gorrosVeranoNoRB.setBounds(430, 200, 50, 40);
			ButtonGroup radioVeranoBG = new ButtonGroup();
			radioVeranoBG.add(gorrosVeranoSiRB);
			radioVeranoBG.add(gorrosVeranoNoRB);
			
			atrasAñadirGorros = new JButton("Atrás");
			atrasAñadirGorros.setBounds(10, 340, 200, 30);
			
			siguienteAñadirGorros = new JButton("Siguiente");
			siguienteAñadirGorros.setBounds(500, 340, 200, 30);
			
			ventanaAñadirGorros.add(importarFotoGorros);
			ventanaAñadirGorros.add(importarFotoGorrosChooser);
			ventanaAñadirGorros.add(gorrosChooserPreview);
			ventanaAñadirGorros.add(gorrosTemporadaLabel);
			ventanaAñadirGorros.add(gorrosVeranoSiRB);
			ventanaAñadirGorros.add(gorrosVeranoNoRB);
			ventanaAñadirGorros.add(atrasAñadirGorros);
			ventanaAñadirGorros.add(siguienteAñadirGorros);
			ventanaAñadirGorros.add(importarFotoGorros);
			
			importarFotoGorrosChooser.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
						JFileChooser chooser = new JFileChooser();
						chooser.showOpenDialog(null);
						File f = chooser.getSelectedFile();
						String filename = f.getAbsolutePath();
						
						gorrosChooserPreview.setText(filename);
					
				}
			});
			
			atrasAñadirGorros.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					CambiarPanel(ventanaAñadirGorros, ventanaAñadirVestimenta1);
					ventanaAñadirVestimenta1Cancelar.setVisible(true);
					ventanaAñadirVestimenta1Atras.setVisible(false);
					
				}
			});			
			
			
			siguienteAñadirGorros.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					
					Boolean veranoSiNo = null;
					if (chaquetasLisaSiRB.isSelected()) {
						veranoSiNo = true;
					} else if (chaquetasLisaNoRB.isSelected()) {
						veranoSiNo = false;
					} else {
						JOptionPane.showMessageDialog(ventanaAñadirChaquetas, "Debes seleccionar si es para verano o no el gorro");

					}
					
						try {
							BaseDatosModise.añadirGorros(veranoSiNo, gorrosChooserPreview.getText());
						} catch (FileNotFoundException | BDException e1) {
							e1.printStackTrace();
						}
					
					CambiarPanel(ventanaAñadirGorros, ventanaMenuPrincipal);
					
				}
			});
			
			
			
			
		//Añadiendo los componentes de ventanaAñadirPantalones
			
			//Importar foto
			importarFotoPantalones = new JLabel("Seleccionar foto para importar de la prenda: ");
			importarFotoPantalones.setBounds(40, 50, 300, 40);
			
			//aqui hay que poner el filechooser y hacer el metodo de subida de fotos a la base de datos <<
			importarFotoPantalonesChooser = new JButton("Seleccionar Foto");
			importarFotoPantalonesChooser.setBounds(360, 60, 140, 30);
			
			pantalonesChooserPreview = new JLabel(" ");
			pantalonesChooserPreview.setBounds(40, 100, 550, 30);
			
			pantalonesMarcaLabel = new JLabel("De que marca son los pantalones?: ");
			pantalonesMarcaLabel.setBounds(40, 150, 300, 40);
			pantalonesMarcaText = new JTextField();
			pantalonesMarcaText.setBounds(360, 160, 100, 30);
			
			pantalonesLargoLabel = new JLabel("Son largos los pantalones?: ");
			pantalonesLargoLabel.setBounds(40,200, 300, 40);
			pantalonesLargoSiRB = new JRadioButton("Si");
			pantalonesLargoSiRB.setBounds(360, 200, 50, 40);
			pantalonesLargoNoRB = new JRadioButton("No");
			pantalonesLargoNoRB.setBounds(430, 200, 50, 40);
			ButtonGroup pantalonesBG = new ButtonGroup();
			pantalonesBG.add(pantalonesLargoNoRB);
			pantalonesBG.add(pantalonesLargoSiRB);

			
			atrasAñadirPantalones = new JButton("Atrás");
			atrasAñadirPantalones.setBounds(10, 340, 200, 30);
			
			siguienteAñadirPantalones = new JButton("Siguiente");
			siguienteAñadirPantalones.setBounds(500, 340, 200, 30);
			
			ventanaAñadirPantalones.add(importarFotoPantalones);
			ventanaAñadirPantalones.add(importarFotoPantalonesChooser);
			ventanaAñadirPantalones.add(pantalonesChooserPreview);
			ventanaAñadirPantalones.add(pantalonesMarcaLabel);
			ventanaAñadirPantalones.add(pantalonesMarcaText);
			ventanaAñadirPantalones.add(pantalonesLargoLabel);
			ventanaAñadirPantalones.add(pantalonesLargoSiRB);
			ventanaAñadirPantalones.add(pantalonesLargoNoRB);
			ventanaAñadirPantalones.add(atrasAñadirPantalones);
			ventanaAñadirPantalones.add(siguienteAñadirPantalones);
			
			importarFotoPantalonesChooser.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					JFileChooser chooser = new JFileChooser();
					chooser.showOpenDialog(null);
					File f = chooser.getSelectedFile();
					String filename = f.getAbsolutePath();
					
					pantalonesChooserPreview.setText(filename);
					
				}
			});
			
			atrasAñadirPantalones.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					
					
					CambiarPanel(ventanaAñadirPantalones, ventanaAñadirVestimenta1);
					ventanaAñadirVestimenta1Cancelar.setVisible(true);
					ventanaAñadirVestimenta1Atras.setVisible(false);
					
				}
			});			
			
			
			siguienteAñadirPantalones.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					
					Boolean pantalonesLargoSiNo = null;
					if (pantalonesLargoSiRB.isSelected()) {
						pantalonesLargoSiNo = true;
					} else if (pantalonesLargoNoRB.isSelected()) {
						pantalonesLargoSiNo = false;
					} else {
						JOptionPane.showMessageDialog(ventanaAñadirChaquetas, "Debes seleccionar si es largo o no el pantalon");

					}
					
						try {
							BaseDatosModise.añadirPantalones(pantalonesMarcaText.getText() ,pantalonesLargoSiNo, pantalonesChooserPreview.getText());
						} catch (FileNotFoundException | BDException e1) {
							e1.printStackTrace();
						}
					
					
					CambiarPanel(ventanaAñadirPantalones, ventanaMenuPrincipal);
					
				}
			});
			
			
		//Añadiendo los componentes de ventanaAñadirZapatos

			//Importar foto
			importarFotoZapatos = new JLabel("Seleccionar foto para importar de la prenda: ");
			importarFotoZapatos.setBounds(40, 50, 300, 40);
			
			//aqui hay que poner el filechooser y hacer el metodo de subida de fotos a la base de datos <<
			importarFotoZapatosChooser = new JButton("Seleccionar Foto");
			importarFotoZapatosChooser.setBounds(360, 60, 140, 30);
			
			zapatosChooserPreview = new JLabel(" ");
			zapatosChooserPreview.setBounds(40, 100, 550, 30);
			
			zapatosTipoLabel = new JLabel("De que tipo son los zapatos?: ");
			zapatosTipoLabel.setBounds(40,150, 300, 40);
			zapatosDeportivosRB = new JRadioButton("Deportivos");
			zapatosDeportivosRB.setBounds(360, 150, 100, 40);
			zapatosVestirRB = new JRadioButton("De Vestir");
			zapatosVestirRB.setBounds(470, 150, 100, 40);
			ButtonGroup radioTipoZapatosBG = new ButtonGroup();
			radioTipoZapatosBG.add(zapatosDeportivosRB);
			radioTipoZapatosBG.add(zapatosVestirRB);
			
			atrasAñadirZapatos = new JButton("Atrás");
			atrasAñadirZapatos.setBounds(10, 340, 200, 30);
			
			siguienteAñadirZapatos = new JButton("Siguiente");
			siguienteAñadirZapatos.setBounds(500, 340, 200, 30);
			
			ventanaAñadirZapatos.add(importarFotoZapatosChooser);
			ventanaAñadirZapatos.add(zapatosChooserPreview);
			ventanaAñadirZapatos.add(importarFotoZapatos);
			ventanaAñadirZapatos.add(zapatosTipoLabel);
			ventanaAñadirZapatos.add(zapatosDeportivosRB);
			ventanaAñadirZapatos.add(zapatosVestirRB);
			ventanaAñadirZapatos.add(atrasAñadirZapatos);
			ventanaAñadirZapatos.add(siguienteAñadirZapatos);
			
			importarFotoZapatosChooser.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					JFileChooser chooser = new JFileChooser();
					chooser.showOpenDialog(null);
					File f = chooser.getSelectedFile();
					String filename = f.getAbsolutePath();
					
					zapatosChooserPreview.setText(filename);
					
				}
			});
			
			atrasAñadirZapatos.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					CambiarPanel(ventanaAñadirZapatos, ventanaAñadirVestimenta1);
					ventanaAñadirVestimenta1Cancelar.setVisible(true);
					ventanaAñadirVestimenta1Atras.setVisible(false);
					
				}
			});			
			
			
			siguienteAñadirZapatos.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					
					Boolean deportivoSiNo = null;
					if (zapatosDeportivosRB.isSelected()) {
						deportivoSiNo = true;
					} else {
						deportivoSiNo = false;
					}
					
					Boolean devestirSiNo = null;
					if (zapatosVestirRB.isSelected()) {
						devestirSiNo = true;
					} else {
						devestirSiNo = false;
					}
					
					
					
						try {
							BaseDatosModise.añadirZapatos(deportivoSiNo, devestirSiNo, zapatosChooserPreview.getText());
						} catch (FileNotFoundException | BDException e1) {
							e1.printStackTrace();
						} 
					
					CambiarPanel(ventanaAñadirZapatos, ventanaMenuPrincipal);
					
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

				Connection conexion = BaseDatosModise.conectar();
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
		comboMasMenosAdministrador.addItem("Hacer Administrador.");
		comboMasMenosAdministrador.addItem("Quitar privilegios de Administrador.");
		comboMasMenosAdministrador.addItem("Eliminar cuenta.");
		ventanaMasMenosAdmin.add(comboMasMenosAdministrador);
		comboMasMenosAdministrador.setBounds(375, 200, 300, 40);

		botonGuardarCambiosMasMenosAdmin = new JButton("Realizar cambios");
		ventanaMasMenosAdmin.add(botonGuardarCambiosMasMenosAdmin);
		botonGuardarCambiosMasMenosAdmin.setBounds(500, 350, 200, 40);

		botonatrasMasMenosAdmin = new JButton("Atras");
		ventanaMasMenosAdmin.add(botonatrasMasMenosAdmin);
		botonatrasMasMenosAdmin.setBounds(25, 350, 200, 40);

		labelErrorMasMenosAdmin = new JLabel();
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
				labelSuccessMasMenosAdmin.setText("");
				labelErrorMasMenosAdmin.setText("");

				Connection conexion = BaseDatosModise.conectar();
				Statement st = null;
				try {
					st = conexion.createStatement();

					if (!txtEmailMasMenosAdmin.getText().equals("a")) {
						if (BaseDatosModise.existeUsuario(st, txtEmailMasMenosAdmin.getText()) == false) {
							if (comboMasMenosAdministrador.getSelectedIndex() == 0) {
								BaseDatosModise.cambiarAdmin(st, txtEmailMasMenosAdmin.getText(), 1);
								labelSuccessMasMenosAdmin.setText("Cambio realizado con Exito!");
							} else if (comboMasMenosAdministrador.getSelectedIndex() == 1) {
								BaseDatosModise.cambiarAdmin(st, txtEmailMasMenosAdmin.getText(), 0);
								labelSuccessMasMenosAdmin.setText("Cambio realizado con Exito!");
							} else if (comboMasMenosAdministrador.getSelectedIndex() == 2) {
								BaseDatosModise.eliminarUsuario(st, txtEmailMasMenosAdmin.getText());
								labelSuccessMasMenosAdmin.setText("Cambio realizado con Exito!");
							}
						} else if (BaseDatosModise.existeUsuario(st, txtEmailMasMenosAdmin.getText()) == true) {
							labelErrorMasMenosAdmin
									.setText("Error, email no valido, porfavor reviselo e intentelo otra vez.");
						}
					} else {
						labelErrorMasMenosAdmin
								.setText("Error, email no valido, porfavor reviselo e intentelo otra vez.");

					}
				} catch (SQLException e1) {
				}
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
				labelErrorMasMenosAdmin.setText("");
				labelSuccessMasMenosAdmin.setText("");
			}
		});

		// actionlistener menu - cerrar sesion
		mi1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// propeties

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
				txtCrearNombreM.setText("nombre");
				txtCrearEmailM.setText("ejemplo@gmail.com");
				txtCrearContraseñaM.setText("");
				spinCrearEdadM.setValue(18);
				escrito3M = false;
				escrito4M = false;
				txtCrearNombreF.setText("nombre");
				txtCrearEmailF.setText("ejemplo@gmail.com");
				txtCrearContraseñaF.setText("");
				spinCrearEdadM.setValue(18);
				escrito3F = false;
				escrito4F = false;

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

				// "sobras"
				// quitar comentario y agregar aqui <-

				// Hasta aqui
				Usuariolog.println("Sesion cerrada.");
				CambiarPanel(ventanaMenuPrincipal, ventanaInicioSesion);
				System.out.println("Sesion cerrada.");
				setProp("ejemplo@gmail.com", "12345");

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

		mi3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String valorPass = new String(contraseña.getPassword());
				setProp(txtEmail.getText(), valorPass);
				System.out.println("X");
				Usuariolog.println("Fin del programa.\n");
				Usuariolog.close();
				frame.dispose();
			}
		});

		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				setProp("ejemplo@gmail.com", "12345");
				System.out.println("windowClosing");
				Usuariolog.println("Fin del programa.\n");
				Usuariolog.close();
			}

			@Override
			public void windowOpened(WindowEvent we) {
				System.out.println("windowOpened");
				File archivo = new File("account.properties");
				if (archivo.length() == 0) {
					System.out.println("File is empty ...");
					FileWriter writer;
					try {
						writer = new FileWriter(archivo);
						writer.write("#program Settings\r\n" + "#Sun Dec 08 18:32:29 CET 2019\r\n"
								+ "correo=ejemplo@gmail.com\r\n" + "contrasena=12345\r\n" + "");
						writer.flush();
						writer.close();
						frame.dispose();
						SwingUtilities.invokeLater(new Runnable() {
							@Override
							public void run() {
								new Principal();
							}
						});
					} catch (IOException e) {
						e.printStackTrace();
					}
				} else {
					System.out.println("File is not empty ...");
				}
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

		// logger1
		try {
			BDLogger = Logger.getLogger("BDLogger");
			BDLogger.addHandler(new FileHandler("BDLogger.xml", true));
		} catch (Exception e) {
		}
		// ->en esta misma clase
		// BDLogger.log(Level.X, " Message ");

		// -> en otra clase de este paquete
		// TestConexion.BDLogger.log(Level.X [Por ahora Level.INFO], "Message");

		// fin logger1

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new Principal();
			}
		});

		System.out.println(new Date());
	}

}
