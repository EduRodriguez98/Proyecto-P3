package proyectPackage;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

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
			
	public Principal() {
		
		//Propiedades del Frame
		JFrame frame = new JFrame();
		frame.setVisible(true);	
		frame.setSize(720, 480);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Principal.java");
		
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
		
		CrearPanel(ventanaInicioSesion);
		CrearPanel(ventanaCrearCuenta);
		CrearPanel(ventanaGenero);
		CrearPanel(ventanaPerfilGustosUno);
		CrearPanel(ventanaPerfilGustosDos);
		CrearPanel(ventanaMenuPrincipal);
		CrearPanel(ventanaCarga);
		
		
		frame.getContentPane().add(ventanaInicioSesion);
		frame.getContentPane().add(ventanaCrearCuenta);	
		frame.getContentPane().add(ventanaGenero);
		frame.getContentPane().add(ventanaPerfilGustosUno);
		frame.getContentPane().add(ventanaPerfilGustosDos);
		frame.getContentPane().add(ventanaMenuPrincipal);
		frame.getContentPane().add(ventanaCarga);
		
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
		
		txtContrasenya = new JTextField("*******");
		ventanaInicioSesion.add(txtContrasenya);
		txtContrasenya.setBounds(160, 225, 300, 50);
		
		labelPregunta = new JLabel("Es tu primera vez en Modise? Pulsa el boton Crear Cuenta para empezar!");
		ventanaInicioSesion.add(labelPregunta);
		labelPregunta.setBounds(60, 350, 450, 50);
			
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
		labelEscoge = new JLabel("Cual de las siguientes prendas te gusta m�s para t�?");
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
		
		//Action Listeners
		botonPerfilGustosDosSiguiente.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				CambiarPanel(ventanaPerfilGustosDos, ventanaCarga);
				//Hacer que se empieze a llenar la progressBar cuando se cambia a la ventanaCarga
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
			
		progressCargando = new JProgressBar();
		ventanaCarga.add(progressCargando);
		progressCargando.setBounds(200, 220, 300, 40);
		if (progressCargando.getValue() == progressCargando.getMaximum()) {
			CambiarPanel(ventanaCarga, ventanaMenuPrincipal);
		}
			
		//Anyadiendo los componentes de ventanaMenuPrincipal
		//aqui	
			
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				new Principal();
			}
		});
		
		//Lo de la hora
		Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        System.out.println( sdf.format(cal.getTime()) );
	}

}