package proyectPackage;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.Calendar;

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
import javax.swing.UIManager;

//import ventana.Paneles;

public class Principal {

	//Metodo Cambiar Paneles
	public void CambiarPanel(JPanel g, JPanel h) {
		g.setVisible(false);
		g.setEnabled(false);
		h.setVisible(true);
		h.setEnabled(true);
		for (Component cp : g.getComponents() ){
			cp.setEnabled(false);
			cp.setVisible(false);
		}
		for (Component sp : h.getComponents() ){
	        sp.setEnabled(true);
	        sp.setVisible(true);
		}
	}
	
	//Metodo Crear Paneles
	public void CrearPanel(JPanel g) {
		g.setLayout(null);
		g.setVisible(false);
		g.setEnabled(false);
		g.setBounds(0, 0, 720, 480);
		//g.setBackground(Color.GRAY);	//color de todos los paneles (NO de las ventanas emergentes), a no ser que queramos cambiar alguno
	}
		
	//ORDENES DE LAS VENTANAS!!!!!
		//(CASO DE PRIMERA VEZ EN MODISE) 1.Ventana Inicio Sesion || 2.Ventana Crear Cuenta || 3.Ventana Genero || 4.Ventana Perfil Gustos 1 || 5.Ventana Perfil Gustos 2 || || 6.Ventana de Carga || 7.Ventana Menu Principal
		//(CASO USUARIO YA REGISTRADO) 1.Ventana Inicio Sesion || 2.Ventana de Carga  || 3.Ventana Menu Principal
		
	//Declarando componentes
		// VentanaInicioSesion
		JLabel labelBrand, labelEmail, labelContrasenya, labelPregunta;
		JTextField txtEmail, txtContrasenya;
		JButton botonCrear, botonInicio;
		JPasswordField contrasenya;
		JCheckBox view;
		boolean escrito1, escrito2;
		
		// VentanaCrearCuenta
		JLabel labelCrearNombre, labelCrearEmail, labelCrearContrasenya, labelCrearEdad, errorCrearCuenta;
		JTextField txtCrearNombre, txtCrearEmail, txtCrearContrasenya;
		JSpinner spinCrearEdad;
		SpinnerModel model;
		JButton botonCrearSiguiente;
		
		// VentanaGenero
		JRadioButton radioMasculino, radioFemenino;
		JLabel labelEscogerGenero, errorGenero;
		JButton botonGeneroSiguiente;
		
		// VentanaPerfilGustosUno
		JButton botonPerfilGustosUnoSiguiente, botonPerfilGustosUnoAtras;
		JCheckBox clasicoF, clasicoM, urbanaF, urbanaM, rockF, rockM, bohoF, smartM, formalF, formalM, sportyChickF, casualChickM;
		JLabel errorPerfilGustosUno;
		
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
		
		
		//ventanaMenuPrincipal
		JMenuBar mb;
		JMenu menu1;
		JMenuItem mi1, mi2, mi3;
		JButton botonPideOutfit, botonAnyadirVestimenta, botonMasMenosAdmin;
		
		//ventanaAnyadirVestimenta
		JRadioButton sol, lluvia, nublado;
		JLabel estilosLabelAnyadirVestimenta, colorLabelAnyadirVestimenta, tiempoLabelAnyadirvestimenta;
		
		JComboBox<String> estilosComboBoxAnyadirVestimenta;
		
		JComboBox<String> coloresComboBoxAnyadirVestimenta;
		
		
		//ventanaPideOutfit
		JButton botonAtrasPideOutfit, botonBuscar;
		JRadioButton radioSol, radioLluvia, radioNublado, radioNo;
		JLabel preguntaEstilo, preguntaTiempo;
		JTextField txtEstilo;
		//ventanaFeedback
		
		//ventanaFeedback
				JLabel nivelSatisfaccion;
				JLabel gustoColores;
				JRadioButton estrella1, estrella2, estrella3, estrella4,estrella5, si, no;
				JButton botonInicioFeedback;
		
		//Ajustes
		JButton cambiarContrasenya, cambiarFecha, reiniciarPerfil;
		
		//ventanaEmergenteOutfit
			
	public Principal() {
		
		//Propiedades y componentes del Frame
		JFrame frame = new JFrame();
		frame.setVisible(true);	
		frame.setSize(720, 480);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Principal.java");
		frame.setResizable(false);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);
		frame.setIconImage (new ImageIcon(getClass().getResource("modise1.png")).getImage());	//Icono de frame
		//para el color haced " ctrl + F " y buscad " g.setBackground "
		
		mb = new JMenuBar();
		menu1 = new JMenu("Menu");	
		mi1 = new JMenuItem("Cerrar sesion");
		mi2 = new JMenuItem("Ajustes");
		mi3 = new JMenuItem("mi3 - ¿Algo mas?");
		menu1.add(mi1);
		menu1.add(mi2);
		menu1.add(mi3);
		mb.add(menu1);
		//mb.setVisible(true);
		//mb.setEnabled(true);
		frame.setJMenuBar(mb);
		
		//action listeners del menu DEBAJO del todo, ANTES del main!!!
		
		//Creando y Anyadiendo Paneles al Frame
		JPanel ventanaInicioSesion = new JPanel();
		JPanel ventanaCrearCuenta = new JPanel();
		JPanel ventanaGenero = new JPanel(); 
		JPanel ventanaPerfilGustosUno = new JPanel();
		JPanel ventanaPerfilGustosDos = new JPanel();
		JPanel ventanaMenuPrincipal = new JPanel();
		JPanel ventanaCarga = new JPanel();
		JPanel ventanaAnyadirVestimenta = new JPanel();
		JPanel ventanaPideOutfit = new JPanel();
		JPanel ventanaFeedback = new JPanel();		
		
		/*	ESTA PARTE COMENTADA ES PARA PONER UNA FOTO DE FONDO, LO DEJO COMENTADO POR SI ACASO
		//Imagen de fondo
		ImageIcon imagenInicio = new ImageIcon(this.getClass().getClassLoader().getResource("proyectPackage/jorge.jpg"));
		
		JPanel ventanaInicioSesion = new JPanel() {
			public void paintComponent(Graphics g) {
		        Image bufferImage = this.createImage(this.getSize().width, this.getSize().height);
		        Graphics bufferGraphics = bufferImage.getGraphics();
		        bufferGraphics.drawImage(imagenInicio.getImage(), 0, 0, 720, 480, null);
		        g.drawImage(bufferImage, 0, 0, this);
		       
		    }
		};
		ventanaInicioSesion.setName("ventanaInicioSesion"); //no va??
		PanelFondo ventanaCrearCuenta = new PanelFondo();
		ventanaCrearCuenta.setName("ventanaCrearCuenta"); //no va??
		PanelFondo ventanaGenero = new PanelFondo(); 
		ventanaGenero.setName("VentanaGenero");  //No va??
		PanelFondo ventanaPerfilGustosUno = new PanelFondo();
		ventanaPerfilGustosUno.setName("ventanaPerfilGustosUno"); //no va??
		PanelFondo ventanaPerfilGustosDos = new PanelFondo();
		ventanaPerfilGustosDos.setName("ventanaPerfilGustosDos"); //no va??
		PanelFondo ventanaMenuPrincipal = new PanelFondo();
		ventanaMenuPrincipal.setName("ventanaMenuPrincipal"); //No va??
		PanelFondo ventanaCarga = new PanelFondo();
		ventanaCarga.setName("ventana de Carga"); //No va?
		PanelFondo ventanaAnyadirVestimenta = new PanelFondo();
		PanelFondo ventanaPideOutfit = new PanelFondo();
		PanelFondo ventanaFeedback = new PanelFondo();
		*/
		
		//ventanas	Emergentes
		JPanel ajustes = new JPanel(new GridLayout(3, 1));	
		JPanel ventanaEmergenteOutfit = new JPanel();
		
		CrearPanel(ventanaInicioSesion);
		CrearPanel(ventanaCrearCuenta);
		CrearPanel(ventanaGenero);
		CrearPanel(ventanaPerfilGustosUno);
		CrearPanel(ventanaPerfilGustosDos);
		CrearPanel(ventanaMenuPrincipal);
		CrearPanel(ventanaCarga);
		CrearPanel(ventanaAnyadirVestimenta);
		CrearPanel(ventanaPideOutfit);
		CrearPanel(ventanaFeedback);
		
		frame.getContentPane().add(ventanaInicioSesion);
		frame.getContentPane().add(ventanaCrearCuenta);	
		frame.getContentPane().add(ventanaGenero);
		frame.getContentPane().add(ventanaPerfilGustosUno);
		frame.getContentPane().add(ventanaPerfilGustosDos);
		frame.getContentPane().add(ventanaMenuPrincipal);
		frame.getContentPane().add(ventanaCarga);
		frame.getContentPane().add(ventanaAnyadirVestimenta);
		frame.getContentPane().add(ventanaPideOutfit);
		frame.getContentPane().add(ventanaFeedback);
		
		ventanaInicioSesion.setVisible(true); //la primera ventana visible
		
		//Anyadiendo los componentes de ventanaInicioSesion
		botonCrear = new JButton("Crear Cuenta");
		ventanaInicioSesion.add(botonCrear);
		botonCrear.setBounds(500, 350, 200, 50);
			
		botonInicio = new JButton("Iniciar Sesion");
		ventanaInicioSesion.add(botonInicio);
		botonInicio.setBounds(500, 225, 200, 50);
		
		labelBrand = new JLabel("MODISE");
		ventanaInicioSesion.add(labelBrand);
		labelBrand.setBounds(320, 50, 100, 50);
		
		labelEmail = new JLabel("Email: ");
		ventanaInicioSesion.add(labelEmail);
		labelEmail.setBounds(60, 150, 100, 50);
		
		labelContrasenya = new JLabel("Contrasena: ");
		ventanaInicioSesion.add(labelContrasenya);
		labelContrasenya.setBounds(60, 225, 100, 50);
		
		txtEmail = new JTextField("ejemplo@gmail.com");
		ventanaInicioSesion.add(txtEmail);
		txtEmail.setBounds(160, 150, 300, 50);
		escrito1 = false;
		txtEmail.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                if (escrito1 == false) {
                	txtEmail.setText("");
                	escrito1 = true;
                }
            }
        });
		
		contrasenya = new JPasswordField("12345");	//cambiado
		contrasenya.setEchoChar('*');				//hacer checkbox isSelected para ver contraseña, HECHO
		ventanaInicioSesion.add(contrasenya);
		contrasenya.setBounds(160, 225, 300, 50);
		escrito2 = false;
		contrasenya.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                if (escrito2 == false) {
                	contrasenya.setText("");
                	escrito2 = true;
                }
            }
        });
		
		view = new JCheckBox("Visualizar contraseña");
		ventanaInicioSesion.add(view);
		view.setBounds(160, 280, 150, 30);
				
		labelPregunta = new JLabel("Es tu primera vez en Modise? Pulsa el boton Crear Cuenta para empezar!");
		ventanaInicioSesion.add(labelPregunta);
		labelPregunta.setBounds(60, 350, 450, 50);
		
		mb.setVisible(false);
		mb.setEnabled(false);
			
		//Action listeners
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
				mb.setVisible(true);
				mb.setEnabled(true);
			}
		});
		
		view.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {				
				if (view.isSelected() == true) {
					contrasenya.setEchoChar((char)0);
				} else {
					contrasenya.setEchoChar('*');
				}
			}
		});
			
		//Anyadiendo los componentes de ventanaCrearCuenta
		labelCrearNombre = new JLabel("Introduzca su nombre: ");
		ventanaCrearCuenta.add(labelCrearNombre);
		labelCrearNombre.setBounds(80, 50, 200, 50);
			
		txtCrearNombre = new JTextField("");
		ventanaCrearCuenta.add(txtCrearNombre);
		txtCrearNombre.setBounds(250, 50, 300, 50);
		
		labelCrearEmail = new JLabel("Introduzca su email: ");
		ventanaCrearCuenta.add(labelCrearEmail);
		labelCrearEmail.setBounds(80, 125, 200, 50);
			
		txtCrearEmail = new JTextField("");
		ventanaCrearCuenta.add(txtCrearEmail);
		txtCrearEmail.setBounds(250, 125, 300, 50);
		
		labelCrearContrasenya = new JLabel("Cree una contrasena: ");
		ventanaCrearCuenta.add(labelCrearContrasenya);
		labelCrearContrasenya.setBounds(80, 200, 200, 50);
		
		txtCrearContrasenya = new JTextField("");
		ventanaCrearCuenta.add(txtCrearContrasenya);
		txtCrearContrasenya.setBounds(250, 200, 300, 50);
		
		labelCrearEdad = new JLabel("Seleccione su edad: ");
		ventanaCrearCuenta.add(labelCrearEdad);
		labelCrearEdad.setBounds(80, 275, 200, 50);
		
				SpinnerModel model = new SpinnerNumberModel(18, 0, 99, 1); //default 18, min 0, max 99, +-1
		//spinCrearEdad.setValue(18);
		spinCrearEdad = new JSpinner(model);
		ventanaCrearCuenta.add(spinCrearEdad);
		spinCrearEdad.setBounds(250, 275, 80, 50);
		
		botonCrearSiguiente = new JButton("Siguiente");
		ventanaCrearCuenta.add(botonCrearSiguiente);
		botonCrearSiguiente.setBounds(500, 380, 200, 40);
		
		errorCrearCuenta = new JLabel();
		ventanaCrearCuenta.add(errorCrearCuenta);
		errorCrearCuenta.setBounds(80, 380, 150, 40);
		errorCrearCuenta.setForeground(Color.RED);
		
		//mb.setVisible(false);
		//mb.setEnabled(false);
		
		//Action Listeners
		botonCrearSiguiente.addActionListener(new ActionListener() {
				
			@Override
			public void actionPerformed(ActionEvent e) {
				//CambiarPanel(ventanaCrearCuenta, ventanaGenero);			En el IF de abajo
				
				String CrearNombre = txtCrearNombre.getText();
				String CrearEmail = txtCrearEmail.getText();				
				String CrearContrasenya = txtCrearContrasenya.getText();
				String CrearEdad = spinCrearEdad.getValue().toString();
				int EdadSeleccionada = (int) spinCrearEdad.getValue();
				
				if (CrearNombre.matches("^[a-zA-Z]*$") && !CrearNombre.isEmpty() && 
				CrearEmail.matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"	//Dos lineas para validar si es
				+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")			//un correo o no, FUNCIONA 100% ??
				&& !CrearEmail.isEmpty() && !CrearContrasenya.isEmpty() && 
				CrearEdad.matches("^[0-9]*$")) {
					CambiarPanel(ventanaCrearCuenta, ventanaGenero);
					errorCrearCuenta.setText("");
					System.out.println("Edad marcado al crear cuenta:"+CrearEdad);
				} else {
					errorCrearCuenta.setText("Error al insertar datos.");
					spinCrearEdad.setValue(EdadSeleccionada);
					System.out.println("Edad marcado al crear cuenta:"+CrearEdad);
				}
			}
		});
			
		//Anyadiendo los componentes de ventanaGenero
		labelEscogerGenero = new JLabel("Seleccione su genero: ");
		ventanaGenero.add(labelEscogerGenero);
		labelEscogerGenero.setBounds(100, 150, 200, 50);
		
		radioMasculino = new JRadioButton("Masculino");
		radioFemenino = new JRadioButton("Femenino");
		ButtonGroup radioButtonsGenero = new ButtonGroup();
		radioButtonsGenero.add(radioFemenino);
		radioButtonsGenero.add(radioMasculino);
		ventanaGenero.add(radioMasculino);
		ventanaGenero.add(radioFemenino);
		radioMasculino.setBounds(300, 150, 100, 50);
		radioFemenino.setBounds(400, 150, 100, 50);
		
		botonGeneroSiguiente = new JButton("Siguiente");
		ventanaGenero.add(botonGeneroSiguiente);
		botonGeneroSiguiente.setBounds(500, 380, 200, 40);
		
		errorGenero = new JLabel();
		ventanaGenero.add(errorGenero);
		errorGenero.setBounds(80, 380, 400, 40);
		errorGenero.setForeground(Color.RED);
		
		//mb.setVisible(false);
		//mb.setEnabled(false);
		
		//Action Listeners
		botonGeneroSiguiente.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//Dependiendo de lo escogido en ventanaGenero apareceran diferentes checkboxes!
				if(radioMasculino.isSelected()) {
					clasicoM = new JCheckBox("Clasico");
					ventanaPerfilGustosUno.add(clasicoM);
					clasicoM.setBounds(200, 70, 150, 50);
					urbanaM = new JCheckBox("Urbana");
					ventanaPerfilGustosUno.add(urbanaM);
					urbanaM.setBounds(400, 70, 150, 50);
					rockM = new JCheckBox("Rock");
					ventanaPerfilGustosUno.add(rockM);
					rockM.setBounds(200, 170 , 150, 50);
					smartM = new JCheckBox("Smart");
					ventanaPerfilGustosUno.add(smartM);
					smartM.setBounds(400, 170, 150, 50);
					formalM = new JCheckBox("Formal");
					ventanaPerfilGustosUno.add(formalM);
					formalM.setBounds(200, 270, 150, 50);
					casualChickM = new JCheckBox("Casual Chick");
					ventanaPerfilGustosUno.add(casualChickM);
					casualChickM.setBounds(400, 270, 150, 50);
					
					CambiarPanel(ventanaGenero, ventanaPerfilGustosUno);
				} else if (radioFemenino.isSelected()) {
					clasicoF = new JCheckBox("Clasico");
					ventanaPerfilGustosUno.add(clasicoF);
					clasicoF.setBounds(200, 70, 150, 50);
					urbanaF = new JCheckBox("Urbana");
					ventanaPerfilGustosUno.add(urbanaF);
					urbanaF.setBounds(400, 70, 150, 50);
					rockF = new JCheckBox("Rock");
					ventanaPerfilGustosUno.add(rockF);
					rockF.setBounds(200, 170 , 150, 50);
					bohoF = new JCheckBox("Boho");
					ventanaPerfilGustosUno.add(bohoF);
					bohoF.setBounds(400, 170, 150, 50);
					formalF = new JCheckBox("Formal");
					ventanaPerfilGustosUno.add(formalF);
					formalF.setBounds(200, 270, 150, 50);
					sportyChickF = new JCheckBox("Sporty Chick");
					ventanaPerfilGustosUno.add(sportyChickF);
					sportyChickF.setBounds(400, 270, 150, 50);
						
					CambiarPanel(ventanaGenero, ventanaPerfilGustosUno);
					errorGenero.setText("");
				} else {
					errorGenero.setText("Se necesita seleccionar 1 genero para continuar.");
					System.out.println("Se necesita seleccionar 1 genero para continuar.");   //Hacer dialogo mas adelante
				}
					//CambiarPanel(ventanaGenero, ventanaPerfilGustosUno);
			}
		});
			
		//Anyadiendo los componentes de ventanaPerfilGustosUno
		botonPerfilGustosUnoAtras = new JButton("Atras");
		ventanaPerfilGustosUno.add(botonPerfilGustosUnoAtras);
		botonPerfilGustosUnoAtras.setBounds(10, 380, 200, 40);
			
		botonPerfilGustosUnoSiguiente = new JButton("Siguiente");
		ventanaPerfilGustosUno.add(botonPerfilGustosUnoSiguiente);
		botonPerfilGustosUnoSiguiente.setBounds(500, 380, 200, 40);
		
		errorPerfilGustosUno = new JLabel();
		ventanaPerfilGustosUno.add(errorPerfilGustosUno);
		errorPerfilGustosUno.setBounds(300, 380, 400, 40);
		errorPerfilGustosUno.setForeground(Color.RED);
		
		//mb.setVisible(false);
		//mb.setEnabled(false);
			
		//Action Listeners
		botonPerfilGustosUnoSiguiente.addActionListener(new ActionListener() {
				
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if (radioMasculino.isSelected()) {
					if (clasicoM.isSelected() || urbanaM.isSelected() || rockM.isSelected() ||
					smartM.isSelected() || formalM.isSelected() || casualChickM.isSelected()) {
						CambiarPanel(ventanaPerfilGustosUno, ventanaPerfilGustosDos);
						errorPerfilGustosUno.setText("");
					} else {
						errorPerfilGustosUno.setText("Selecciona al menos 1.");
					}
				} else if (radioFemenino.isSelected()) {
					if (clasicoF.isSelected() || urbanaF.isSelected() || rockF.isSelected() ||
					bohoF.isSelected() || formalF.isSelected() || sportyChickF.isSelected()) {
						CambiarPanel(ventanaPerfilGustosUno, ventanaPerfilGustosDos);
						errorPerfilGustosUno.setText("");
					} else {
						errorPerfilGustosUno.setText("Selecciona al menos 1.");
					}
				}
				
				//CambiarPanel(ventanaPerfilGustosUno, ventanaPerfilGustosDos);
			}
		});
			
		botonPerfilGustosUnoAtras.addActionListener(new ActionListener() {
				
			@Override
			public void actionPerformed(ActionEvent e) {
				//Asegurarse de que los checkboxes que aparezcan se borran y se vuelven a generar al darle a siguiente en caso de cambio de genero al volver atras
				if(radioMasculino.isSelected()) {
					ventanaPerfilGustosUno.remove(clasicoM);
					ventanaPerfilGustosUno.remove(urbanaM);
					ventanaPerfilGustosUno.remove(rockM);
					ventanaPerfilGustosUno.remove(smartM);
					ventanaPerfilGustosUno.remove(formalM);
					ventanaPerfilGustosUno.remove(casualChickM);
				} else if (radioFemenino.isSelected()) {
					ventanaPerfilGustosUno.remove(clasicoF);
					ventanaPerfilGustosUno.remove(urbanaF);
					ventanaPerfilGustosUno.remove(rockF);
					ventanaPerfilGustosUno.remove(bohoF);
					ventanaPerfilGustosUno.remove(formalF);
					ventanaPerfilGustosUno.remove(sportyChickF);
				}	
						
			CambiarPanel(ventanaPerfilGustosUno, ventanaGenero);
			}
		});
			
		//Anyadiendo los componentes de ventanaPerfilGustosDos
		labelEscoge = new JLabel("Cual de las siguientes prendas te gusta mas para ti?");
		ventanaPerfilGustosDos.add(labelEscoge);
		labelEscoge.setBounds(200, 30, 350, 50);
			
		radioPrendaIzq = new JRadioButton("Opcion 1");
		ventanaPerfilGustosDos.add(radioPrendaIzq);
		radioPrendaIzq.setBounds(200, 300, 100, 50);
			
		radioPrendaDer = new JRadioButton("Opcion 2");
		ventanaPerfilGustosDos.add(radioPrendaDer);
		radioPrendaDer.setBounds(420, 300, 100, 50);
		
		ButtonGroup bgPerfilGustosDos = new ButtonGroup();
		bgPerfilGustosDos.add(radioPrendaIzq);
		bgPerfilGustosDos.add(radioPrendaDer);
		
		botonPerfilGustosDosAtras = new JButton("Atras");
		ventanaPerfilGustosDos.add(botonPerfilGustosDosAtras);
		botonPerfilGustosDosAtras.setBounds(10, 380, 200, 40);
		
		botonPerfilGustosDosSiguiente = new JButton("Siguiente");
		ventanaPerfilGustosDos.add(botonPerfilGustosDosSiguiente);
		botonPerfilGustosDosSiguiente.setBounds(500, 380, 200, 40);	
		
		errorPerfilGustosDos = new JLabel();
		ventanaPerfilGustosDos.add(errorPerfilGustosDos);
		errorPerfilGustosDos.setBounds(300, 380, 400, 40);
		errorPerfilGustosDos.setForeground(Color.RED);
		
		//mb.setVisible(false);
		//mb.setEnabled(false);
		
		//Action Listeners
		botonPerfilGustosDosSiguiente.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if (bgPerfilGustosDos.getSelection() != null) {
					
					CambiarPanel(ventanaPerfilGustosDos, ventanaCarga);
					errorPerfilGustosDos.setText("");
					
					//JProgressBar
					
					Thread t = new Thread(new Runnable() {
						
						@Override
						public void run() {
						
							for (counter = 0; !stop && counter <= MAX_STEPS ; counter++) {
								System.out.println(counter);
								
								SwingUtilities.invokeLater(new Runnable() {
									
									@Override
									public void run() {
										progressCargando.setValue(counter);
										
									}
								});
							}

							if(stop = true) {
								CambiarPanel(ventanaCarga, ventanaMenuPrincipal);
								//se cambia? SI GUD JOB
								mb.setVisible(true);
								mb.setEnabled(true);
								UIManager.put("OptionPane.minimumSize",new Dimension(600, 700)); 
								JOptionPane.showMessageDialog(null, ventanaEmergenteOutfit, "¡Aqui esta tu outfit!", JOptionPane.DEFAULT_OPTION);
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
				CambiarPanel(ventanaPerfilGustosDos, ventanaPerfilGustosUno);
					
			}
		});
			
		//Anyadiendo los componentes de ventanaCarga
		labelCargando = new JLabel("Cargando");
		ventanaCarga.add(labelCargando);
		labelCargando.setBounds(320, 170, 200, 50);
		
			
		progressCargando = new JProgressBar(0, MAX_STEPS);
		progressCargando.setValue(75);
		ventanaCarga.add(progressCargando);
		progressCargando.setBounds(200, 220, 300, 40);
				
		//mb.setVisible(false);
		//mb.setEnabled(false);
			
		//Anyadiendo los componentes de ventanaMenuPrincipal
		botonPideOutfit = new JButton("Pide un Outfit!");
		botonPideOutfit.setBounds(250, 150, 200, 50);
		ventanaMenuPrincipal.add(botonPideOutfit);
		
		botonAnyadirVestimenta = new JButton("Anyade tu propia Vestimenta");
		botonAnyadirVestimenta.setBounds(250,250, 200, 50);
		ventanaMenuPrincipal.add(botonAnyadirVestimenta);
		
		
		//Este botï¿½n solo puede ser visible cuando se hace log in con una cuenta administradora, para que solo los
		//administradores puedan gestionar a los administradores.
		//De momento la dejamos ahï¿½ y ya le haremos el if admin = true .setvisible luego
		botonMasMenosAdmin = new JButton("Admin +/-");
		botonMasMenosAdmin.setBounds(550,40, 110, 30);
		ventanaMenuPrincipal.add(botonMasMenosAdmin);
		
		//ActionListeners
		
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
			}
		});
		
		//Anyadiendo los compenentes de ventanaPideOutfit
		preguntaTiempo = new JLabel("Que tiempo hace hoy?");
		ventanaPideOutfit.add(preguntaTiempo);
		preguntaTiempo.setBounds(50, 0, 300, 100);
		
		radioSol = new JRadioButton("Soleado");
		ventanaPideOutfit.add(radioSol);
		radioSol.setBounds(50, 80, 100, 40);
		radioLluvia = new JRadioButton("Llueve");
		ventanaPideOutfit.add(radioLluvia);
		radioLluvia.setBounds(50, 120, 100, 40);
		radioNublado = new JRadioButton("Nublado");
		ventanaPideOutfit.add(radioNublado);
		radioNublado.setBounds(50, 160, 100, 40);
		
		ButtonGroup bgPideOutfit = new ButtonGroup();
		bgPideOutfit.add(radioSol);
		bgPideOutfit.add(radioLluvia);
		bgPideOutfit.add(radioNublado);
		
		preguntaEstilo = new JLabel("Tienes algun estilo en mente para tu outfit?");
		ventanaPideOutfit.add(preguntaEstilo);
		preguntaEstilo.setBounds(50, 210, 300, 60);
		
		radioNo = new JRadioButton("No");
		ventanaPideOutfit.add(radioNo);
		radioNo.setBounds(50, 260, 50, 30);
		
		txtEstilo = new JTextField("ej: Clasico");
		ventanaPideOutfit.add(txtEstilo);
		txtEstilo.setBounds(150, 260, 200, 30);
		
		botonAtrasPideOutfit = new JButton("Atras");
		ventanaPideOutfit.add(botonAtrasPideOutfit);
		botonAtrasPideOutfit.setBounds(50, 350, 80, 30);
		
		botonBuscar = new JButton("Buscar!");
		ventanaPideOutfit.add(botonBuscar);
		botonBuscar.setBounds(550, 350, 80, 30);
		
		
		//Action Listeners
		
		botonBuscar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//CambiarPanel(ventanaPideOutfit, ventanaCarga);
				CambiarPanel(ventanaPideOutfit, ventanaFeedback);
				UIManager.put("OptionPane.minimumSize",new Dimension(600, 700)); 
				JOptionPane.showMessageDialog(null, ventanaEmergenteOutfit, "¡Aqui esta tu outfit!", JOptionPane.DEFAULT_OPTION);
			}
		});
		
		botonAtrasPideOutfit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				CambiarPanel(ventanaPideOutfit, ventanaMenuPrincipal);
				mb.setVisible(true);
				mb.setEnabled(true);
			}
		});
		//Action Listeners
		
		//Anyadiendo los componentes de ventanaAnyadirVestimenta
		estilosLabelAnyadirVestimenta = new JLabel("Selecciona un estilo: (F para Femenino y M para masculino)");
		colorLabelAnyadirVestimenta = new JLabel("Selecciona un color: ");
		tiempoLabelAnyadirvestimenta = new JLabel("Selecciona el tiempo: ");
		
		sol = new JRadioButton("Sol");
		lluvia = new JRadioButton("Lluvia");
		nublado = new JRadioButton("Nublado");
		
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
		
		estilosLabelAnyadirVestimenta.setBounds(40,200,400,40);
		colorLabelAnyadirVestimenta.setBounds(400,200,200,40);
		tiempoLabelAnyadirvestimenta.setBounds(40,50,200,40);
		
		sol.setBounds(40,100,100,40);
		lluvia.setBounds(160,100,100,40);
		nublado.setBounds(280,100,100,40);
		
		estilosComboBoxAnyadirVestimenta.setBounds(40,250,100,40);
		coloresComboBoxAnyadirVestimenta.setBounds(400,250,100,40);
		
		ButtonGroup radioButtonsTiempo = new ButtonGroup();
		radioButtonsTiempo.add(sol);
		radioButtonsTiempo.add(lluvia);
		radioButtonsTiempo.add(nublado);
		
		ventanaAnyadirVestimenta.add(sol);
		ventanaAnyadirVestimenta.add(lluvia);
		ventanaAnyadirVestimenta.add(nublado);
		ventanaAnyadirVestimenta.add(estilosComboBoxAnyadirVestimenta);
		ventanaAnyadirVestimenta.add(coloresComboBoxAnyadirVestimenta);
		ventanaAnyadirVestimenta.add(estilosLabelAnyadirVestimenta);
		ventanaAnyadirVestimenta.add(colorLabelAnyadirVestimenta);
		ventanaAnyadirVestimenta.add(tiempoLabelAnyadirvestimenta);
		
		//Anyadiendo los componentes de ventanaFeedback
		nivelSatisfaccion = new JLabel("Nivel de satisfaccion: ");
		ventanaFeedback.add(nivelSatisfaccion);
		gustoColores = new JLabel("Te han gustado los colores?");
		ventanaFeedback.add(gustoColores);
		
		estrella1 = new JRadioButton("⭐");
		estrella2 = new JRadioButton("⭐⭐");
		estrella3 = new JRadioButton("⭐⭐⭐");
		estrella4 = new JRadioButton("⭐⭐⭐⭐");
		estrella5 = new JRadioButton("⭐⭐⭐⭐⭐");
		si = new JRadioButton("Si");
		no = new JRadioButton("No");
		
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
		
		//probando si funciona el pito git
		nivelSatisfaccion.setBounds(100,50,200,50);
		gustoColores.setBounds(100,170,200,50);
		
		estrella1.setBounds(100,100,80,50);
		estrella2.setBounds(200,100,80,50);
		estrella3.setBounds(300,100,80,50);
		estrella4.setBounds(400,100,80,50);
		estrella5.setBounds(500,100,80,50);
		si.setBounds(100,220,100,50);
		no.setBounds(200,220,100,50);
		
		botonInicioFeedback = new JButton("Inicio");
		ventanaFeedback.add(botonInicioFeedback);
		botonInicioFeedback.setBounds(310, 350, 100, 50);
		
		//Action Listeners
		botonInicioFeedback.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				CambiarPanel(ventanaFeedback, ventanaMenuPrincipal);
				mb.setVisible(true);
				mb.setEnabled(true);
				System.out.println("Mandando feedback... (?)");
				radioButtonsEstrellas.clearSelection();
				radioButtonsSiNo.clearSelection();			
			}
		});
		
		//Anyadiendo los componentes de ajustes
		cambiarContrasenya = new JButton("Cambiar contrasenya");
		cambiarContrasenya.setBounds(50, 20, 50, 50);
		ajustes.add(cambiarContrasenya);
		
		cambiarFecha = new JButton("Cambiar fecha");
		cambiarFecha.setBounds(50, 100, 50, 50);
		ajustes.add(cambiarFecha);
		
		reiniciarPerfil = new JButton("Reiniciar perfil");
		reiniciarPerfil.setBounds(50, 180, 50, 50);
		ajustes.add(reiniciarPerfil);
		
		//Action listeners	OJO ESTO SOLO PARA PROBAR
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
		
		mi1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				mb.setVisible(false);
				mb.setEnabled(false);
				
				txtEmail.setText("ejemplo@gmail.com");
				contrasenya.setText("12345");
				view.setSelected(false);
				bgPideOutfit.clearSelection();
				radioNo.setSelected(false);
				txtEstilo.setText("ej: Clasico");
				radioButtonsEstrellas.clearSelection();
				radioButtonsSiNo.clearSelection();
				
				txtCrearNombre.setText("");
				txtCrearEmail.setText("");
				txtCrearContrasenya.setText("");
				spinCrearEdad.setValue(18);
				radioButtonsGenero.clearSelection();
				
				if (radioMasculino.isSelected() == true ) {
					clasicoM.setSelected(false);
					urbanaM.setSelected(false);
					rockM.setSelected(false);
					smartM.setSelected(false);
					formalM.setSelected(false);
					casualChickM.setSelected(false);
				} else if (radioFemenino.isSelected() == true) {
					clasicoF.setSelected(false);
					urbanaF.setSelected(false);
					rockF.setSelected(false);
					bohoF.setSelected(false);
					formalF.setSelected(false);
					sportyChickF.setSelected(false);
				}
				
				bgPerfilGustosDos.clearSelection();
				
				//Hasta aqui
				CambiarPanel(ventanaMenuPrincipal, ventanaInicioSesion);
				System.out.println("Sesion cerrada.");
				
			}
		});
		
		mi2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				UIManager.put("OptionPane.minimumSize",new Dimension(200, 200)); //este tamanyo es solo para esta ventana emergente
				JOptionPane.showMessageDialog(null, ajustes, "Ajustes", JOptionPane.DEFAULT_OPTION);
				
			}
		});
		
		}
			
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				new Principal();
			}
		});
		
		
		
		//Lo de la hora
		Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        System.out.println( sdf.format(cal.getTime()) );
	}

}