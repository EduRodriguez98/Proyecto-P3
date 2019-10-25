package proyectPackage;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
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
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

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
		g.setBackground(Color.white);
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
		JLabel labelCrearNombre, labelCrearEmail, labelCrearContrasenya, labelCrearEdad;
		JTextField txtCrearNombre, txtCrearEmail, txtCrearContrasenya;
		JSpinner spinCrearEdad;
		JButton botonCrearSiguiente;
		
		// VentanaGenero
		JRadioButton radioMasculino, radioFemenino;
		JLabel labelEscogerGenero;
		JButton botonGeneroSiguiente;
		
		// VentanaPerfilGustosUno
		JButton botonPerfilGustosUnoSiguiente, botonPerfilGustosUnoAtras;
		JCheckBox clasicoF, clasicoM, urbanaF, urbanaM, rockF, rockM, bohoF, smartM, formalF, formalM, sportyChickF, casualChickM;
		
		// VentanaPerfilGustos2
		JLabel labelEscoge;
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
		frame.setIconImage (new ImageIcon(getClass().getResource("jorge.jpg")).getImage());		
		
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
		ventanaInicioSesion.setName("ventanaInicioSesion"); //no va??
		JPanel ventanaCrearCuenta = new JPanel();
		ventanaCrearCuenta.setName("ventanaCrearCuenta"); //no va??
		JPanel ventanaGenero = new JPanel(); 
		ventanaGenero.setName("VentanaGenero");  //No va??
		JPanel ventanaPerfilGustosUno = new JPanel();
		ventanaPerfilGustosUno.setName("ventanaPerfilGustosUno"); //no va??
		JPanel ventanaPerfilGustosDos = new JPanel();
		ventanaPerfilGustosDos.setName("ventanaPerfilGustosDos"); //no va??
		JPanel ventanaMenuPrincipal = new JPanel();
		ventanaMenuPrincipal.setName("ventanaMenuPrincipal"); //No va??
		JPanel ventanaCarga = new JPanel();
		ventanaCarga.setName("ventana de Carga"); //No va?
		JPanel ventanaAnyadirVestimenta = new JPanel();
		JPanel ventanaPideOutfit = new JPanel();
		JPanel ventanaFeedback = new JPanel();
		
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
		contrasenya.setEchoChar('*');					//hacer checkbox isSelected para ver contraseña, HECHO
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
		
		spinCrearEdad = new JSpinner();
		spinCrearEdad.setValue(12);
		ventanaCrearCuenta.add(spinCrearEdad);
		spinCrearEdad.setBounds(250, 275, 80, 50);
		
		botonCrearSiguiente = new JButton("Siguiente");
		ventanaCrearCuenta.add(botonCrearSiguiente);
		botonCrearSiguiente.setBounds(500, 380, 200, 40);
		
		//mb.setVisible(false);
		//mb.setEnabled(false);
		
		//Action Listeners
		botonCrearSiguiente.addActionListener(new ActionListener() {
				
			@Override
			public void actionPerformed(ActionEvent e) {
				CambiarPanel(ventanaCrearCuenta, ventanaGenero);
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
				} else {
					System.out.println("Se necesita seleccionar al menos 1 genero para continuar");   //Hacer dialogo mas adelante
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
		
		//mb.setVisible(false);
		//mb.setEnabled(false);
			
		//Action Listeners
		botonPerfilGustosUnoSiguiente.addActionListener(new ActionListener() {
				
			@Override
			public void actionPerformed(ActionEvent e) {
				CambiarPanel(ventanaPerfilGustosUno, ventanaPerfilGustosDos);
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
		
		//mb.setVisible(false);
		//mb.setEnabled(false);
		
		//Action Listeners
		botonPerfilGustosDosSiguiente.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				CambiarPanel(ventanaPerfilGustosDos, ventanaCarga);
				
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
				try {
					Thread.sleep(2000);
					UIManager.put("OptionPane.minimumSize",new Dimension(600, 700)); 
					JOptionPane.showMessageDialog(null, ventanaEmergenteOutfit, "¡Aqui esta tu outfit!", JOptionPane.DEFAULT_OPTION);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
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
		
		//Anyadiendo los componentes de ventanaFeedback
		nivelSatisfaccion = new JLabel("Nivel de satisfaccion: ");
		ventanaFeedback.add(nivelSatisfaccion);
		gustoColores = new JLabel("¿Te han gustado los colores?");
		ventanaFeedback.add(gustoColores);
		
		//eclipse es una puta mierda, no me deja poner un dibujito de una estrella bien, yo lo intente gente
	
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
				try {
					Thread.sleep(2000);
					mb.setVisible(true);
					mb.setEnabled(true);
					System.out.println("Mandando feedback... (?)");
				} catch (InterruptedException e1) {
					e1.printStackTrace();
					System.out.println("Error feedback... (?)");
				}
				
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
				try {
					Thread.sleep(3000);
					//Escribir aqui lo que pasa
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
					spinCrearEdad.setValue(12);
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
					
					//Hasta aqui
					CambiarPanel(ventanaMenuPrincipal, ventanaInicioSesion);
					System.out.println("Sesion cerrada.");
				} catch (InterruptedException e1) {
					e1.printStackTrace();
					System.out.println("ERROR al cerrar sesion.");
				}
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