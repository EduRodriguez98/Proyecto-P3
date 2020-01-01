package conexion;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import com.mysql.cj.jdbc.Blob;

import modise.RWException;

public class BaseDatosModise {

	private static final String CONTROLADOR = "com.mysql.cj.jdbc.Driver";
	private static final String URL = "jdbc:mysql://localhost:3306/modise_schema?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	private static final String USUARIO = "root";
	private static final String CONTRASENA = "1234Abcd";

	public static Connection conectar() {

		try {
			Class.forName(CONTROLADOR);
			Connection conexion = DriverManager.getConnection(URL, USUARIO, CONTRASENA);
			// modise.Principal.BDLogger.log(Level.INFO, "Se ha conectado");
			// HAY QUE MIRAR PORK ESTO DA ERROR!!!!!!!!!!!!!!!!!!!!!!
			return conexion;
		} catch (ClassNotFoundException | SQLException e) {
			modise.Principal.BDLogger.log(Level.SEVERE, "Error en la conexion", e);
			e.printStackTrace();
			return null;
		}
	}

	public static void cerrarBD(final Connection con, final Statement st) {
		try {
			if (st != null)
				st.close();
			if (con != null)
				con.close();
		} catch (SQLException e) {
			modise.Principal.BDLogger.log(Level.SEVERE, "Error al cerrar la base de datos.", e);
			e.printStackTrace();
		}
	}

	// por ahora prueba
	public static void subirFoto(Statement st, File archivo) {
		String sentSQL = "insert into pruebaimagenes(imagen) values('" + archivo + "');";
		System.out.println(sentSQL);
		try {
			st.executeUpdate(sentSQL);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static boolean logIn(Statement st, String user, String passw) {
		String SentSQL = "select * from usuario where correo = '" + user + "' and contrasena = '" + passw + "'";
		try {
			ResultSet rs = st.executeQuery(SentSQL);
			rs.next();

			String a = rs.getString("contrasena");
			if (a.equals(passw)) {
				System.out.println(SentSQL);
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			modise.Principal.BDLogger.log(Level.SEVERE, "Error logIn\n" + SentSQL, e);
			e.printStackTrace();
			return false;
		}

	}

	public static boolean esAdmin(Statement st, String corr) {
		String SentSQL = "select administrador from usuario where correo = '" + corr + "';";
		try {
			ResultSet rs = st.executeQuery(SentSQL);
			rs.next();

			String a = rs.getString("administrador");
			System.out.println("esAdmin: " + SentSQL + a);
			if (a.equals("1")) {
				return true;
			} else if (a.equals("0")) {
				return false;
			} else {
				return false;
			}
		} catch (SQLException e) {
			modise.Principal.BDLogger.log(Level.SEVERE, "Error esAdmin\n" + SentSQL, e);
			e.printStackTrace();
			return false;
		}
	}

	public static boolean existeUsuario(Statement st, String corr) {
		String SentSQL = "select correo from usuario where correo = " + "'" + corr + "';";
		System.out.println(SentSQL);

		try {
			ResultSet rs = st.executeQuery(SentSQL);

			rs.next();

			String b = rs.getString("correo");
			System.out.println(b);
			if (b.equals(corr)) {
				System.out.println("YA EXISTE EL USUARIO");
				return false;
			} else {
				System.out.println("NO EXISTE USUARIO");
				return true;
			}
		} catch (SQLException e) {
			// aqui NO es un error, NO ponemos logger de error ni "e.printStackTrace();"
			System.out.println("catch de -> BaseDatosModise.existeUsuario, por lo que NO EXISTE EL USUARIO");
			return true;
		}
	}
	
	public static void cancelarNuevoUsuario (Statement st, int id, String nom, String corr, int admin, Object ed, String contr,
			int gen){
		
		String sql = "delete usuario where ";
		
	}

	public static void nuevoUsuario(Statement st, String nom, String corr, int admin, Object ed, String contr,
			int gen) {
		String SentSQL = "insert into usuario(nom_usuario,correo,administrador,edad,contrasena,genero) values('" + nom
				+ "','" + corr + "'," + admin + "," + ed + ",'" + contr + "'," + gen + ");";
		System.out.println(SentSQL);
		try {
			st.executeUpdate(SentSQL);
		} catch (SQLException e) {
			modise.Principal.BDLogger.log(Level.SEVERE, "Error nuevoUsuario\n" + SentSQL, e);
			e.printStackTrace();
		}
	}

	public static void eliminarUsuario(Statement st, String corr) {
		String SentSQL = "delete from usuario where correo = '" + corr + "';";
		System.out.println(SentSQL);
		try {
			st.executeUpdate(SentSQL);
		} catch (SQLException e) {
			System.out.println(e);
			System.out.println("Catch BaseDatosModise.eliminarUsuario");
			modise.Principal.BDLogger.log(Level.SEVERE, "Error eliminarUsuario\n" + SentSQL, e);
			e.printStackTrace();
		}
	}

	public static void cambiarContraseña(Statement st, String passw, String corr) {
		String SentSQL = "UPDATE usuario SET contrasena = '" + passw + "' WHERE correo = '" + corr + "';";
		System.out.println(SentSQL);
		try {
			st.executeUpdate(SentSQL);
		} catch (SQLException e) {
			modise.Principal.BDLogger.log(Level.SEVERE, "Error cambiarContraseña\n" + SentSQL, e);
			e.printStackTrace();
		}
	}

	public static void cambiarAdmin(Statement st, String corr, int admin) {
		String SentSQL = "UPDATE usuario SET administrador = '" + admin + "' WHERE correo = '" + corr + "';";
		System.out.println(SentSQL);
		try {
			st.executeUpdate(SentSQL);
		} catch (SQLException e) {
			modise.Principal.BDLogger.log(Level.SEVERE, "Error cambiarAdmin\n" + SentSQL, e);
			e.printStackTrace();
		}
	}
	
	public static void añadirPrenda(int idcolor, String estiloPrendas, Boolean genero, int nivelFashion, int nivelImpermeable) throws BDException {
		
		try {
		// 1.PrepareStatement
		Connection conn = DriverManager.getConnection(URL, USUARIO, CONTRASENA);
		
		PreparedStatement Stmt = conn.prepareStatement("INSERT INTO prendas (id_color, estiloPrendas, genero, nivelFash, nivelImp) VALUES ('" + idcolor + "','" + estiloPrendas
				+ "'," +  genero + ",'" + nivelFashion + "','" + nivelImpermeable + "')");
		
		// 2.Execute SQL query
		Stmt.executeUpdate();
		
		} catch (SQLException e) {
			
			throw new BDException("Error al ejecutar SQL Stmt para anyadir prenda", e);
		}
	}
	
	
	public static void eliminarUltimaPrenda() throws BDException {
		
		try {
			// 1.PrepareStatement
			Connection conn = DriverManager.getConnection(URL, USUARIO, CONTRASENA);
			
			PreparedStatement Stmt = conn.prepareStatement("DELETE FROM prendas ORDER BY idprendas DESC LIMIT 1");
			
			// 2.Execute SQL Update
			Stmt.executeUpdate();
		} catch (Exception e) {
			
			 throw new BDException ("Error al ejecutar SQL Stmt para eliminar la ULTIMA prenda", e);
		}		
		
		
	}	
		
	public static void añadirCamiseta(String logotipo, Boolean rayas, Boolean cuadros, String pathCamiseta) throws BDException, FileNotFoundException {
		int idprend = 0;
		
		try {
			// 1.PrepareStatement
			Connection conn = DriverManager.getConnection(URL, USUARIO, CONTRASENA);
			
			PreparedStatement Stmt = conn.prepareStatement("SELECT * FROM prendas ORDER BY idprendas DESC LIMIT 1");
			
			// 2.Execute SQL query and return value of id_prendas to idprend to use, allowing a link between prendas and camisetas to be created
			ResultSet rs = Stmt.executeQuery();
			while (rs.next()) {
				idprend = rs.getInt("idprendas");
			}
			
			modise.Principal.BDLogger.log(Level.FINE, "Codigo ejecutado SQL: " + Stmt + ", idprendas obtenido: " + rs);
			} catch (SQLException e) {
				
				throw new BDException("Error al ejecutar SQL Stmt para seleccionar idprendas en la tabla prendas", e);
			}
		
		try {
		// 1.PrepareStatement
		Connection conn = DriverManager.getConnection(URL, USUARIO, CONTRASENA);
		
		PreparedStatement Stmt = conn.prepareStatement("INSERT INTO camisetas (idprendas, logotipo, rayas, cuadros, fotocamiseta) values (?,?,?,?,?)");
		
		Stmt.setInt(1, idprend);
		Stmt.setString(2, logotipo);
		Stmt.setBoolean(3, rayas);
		Stmt.setBoolean(4, cuadros);
		InputStream fotoCamisetaIS = new FileInputStream(new File(pathCamiseta));
		Stmt.setBlob(5, fotoCamisetaIS);
		
		Stmt.executeUpdate();
		} catch (SQLException e2) {
			throw new BDException("Error en el codigo SQL al insertar los datos en la Base de Datos", e2);
		}
	}
	
	public static void añadirChaquetas(Boolean larga, Boolean lisa, String pathChaquetas) throws BDException, FileNotFoundException {
		int idprend = 0;
			
		try {
			// 1.PrepareStatement
			Connection conn = DriverManager.getConnection(URL, USUARIO, CONTRASENA);
			
			PreparedStatement Stmt = conn.prepareStatement("SELECT * FROM prendas ORDER BY idprendas DESC LIMIT 1");
			
			// 2.Execute SQL query and return value of id_prendas to idprend to use, allowing a link between prendas and chaquetas to be created
			ResultSet rs = Stmt.executeQuery();
			while (rs.next()) {
				idprend = rs.getInt("idprendas");
			}
			
			modise.Principal.BDLogger.log(Level.FINE, "Codigo ejecutado SQL: " + Stmt + ", idprendas obtenido: " + rs);
			} catch (SQLException e) {
			
				throw new BDException("Error al ejecutar SQL Stmt para seleccionar idprendas en la tabla prendas", e);
			}
			
		try {
			// 1.PrepareStatement
			Connection conn = DriverManager.getConnection(URL, USUARIO, CONTRASENA);
			
			PreparedStatement Stmt = conn.prepareStatement("INSERT INTO chaquetas (idprendas, larga, lisa, fotochaqueta) values (?,?,?,?)");
			
			Stmt.setInt(1, idprend);
			Stmt.setBoolean(2, larga);
			Stmt.setBoolean(3, lisa);
			InputStream fotoChaquetaIS = new FileInputStream(new File(pathChaquetas));
			Stmt.setBlob(4, fotoChaquetaIS);
			
			Stmt.executeUpdate();
			} catch (SQLException e2) {
				throw new BDException("Error en el codigo SQL al insertar los datos en la Base de Datos", e2);
			}
		}	
			
	public static void añadirGorros(Boolean verano, String pathGorros) throws BDException, FileNotFoundException {
		int idprend = 0;
		
		try {
			// 1.PrepareStatement
			Connection conn = DriverManager.getConnection(URL, USUARIO, CONTRASENA);
			
			PreparedStatement Stmt = conn.prepareStatement("SELECT * FROM prendas ORDER BY idprendas DESC LIMIT 1");
			
			// 2.Execute SQL query and return value of id_prendas to idprend to use, allowing a link between prendas and gorros to be created
			ResultSet rs = Stmt.executeQuery();
			while (rs.next()) {
				idprend = rs.getInt("idprendas");
			}
			
			modise.Principal.BDLogger.log(Level.FINE, "Codigo ejecutado SQL: " + Stmt + ", idprendas obtenido: " + rs);
			} catch (SQLException e) {
				
				throw new BDException("Error al ejecutar SQL Stmt para seleccionar idprendas en la tabla prendas", e);
			}
			
		try {
			// 1.PrepareStatement
			Connection conn = DriverManager.getConnection(URL, USUARIO, CONTRASENA);
			
			PreparedStatement Stmt = conn.prepareStatement("INSERT INTO gorros (idprendas, verano, fotogorros) values (?,?,?)");
			
			Stmt.setInt(1, idprend);
			Stmt.setBoolean(2, verano);
			InputStream fotoGorrosIS = new FileInputStream(new File(pathGorros));
			Stmt.setBlob(3, fotoGorrosIS);
			
			Stmt.executeUpdate();
			} catch (SQLException e2) {
				throw new BDException("Error en el codigo SQL al insertar los datos en la Base de Datos", e2);
			}
		}
	
	public static void añadirPantalones(String marca, Boolean corto, String pathPantalones) throws BDException, FileNotFoundException {
		int idprend = 0;
		
		try {
			// 1.PrepareStatement
			Connection conn = DriverManager.getConnection(URL, USUARIO, CONTRASENA);
			
			PreparedStatement Stmt = conn.prepareStatement("SELECT * FROM prendas ORDER BY idprendas DESC LIMIT 1");
			
			// 2.Execute SQL query and return value of id_prendas to idprend to use, allowing a link between prendas and pantalones to be created
			ResultSet rs = Stmt.executeQuery();
			while (rs.next()) {
				idprend = rs.getInt("idprendas");
			}
			
			modise.Principal.BDLogger.log(Level.FINE, "Codigo ejecutado SQL: " + Stmt + ", idprendas obtenido: " + rs);
			} catch (SQLException e) {
				
				throw new BDException("Error al ejecutar SQL Stmt para seleccionar idprendas en la tabla prendas", e);
			}
		
		try {
			// 1.PrepareStatement
			Connection conn = DriverManager.getConnection(URL, USUARIO, CONTRASENA);
			
			PreparedStatement Stmt = conn.prepareStatement("INSERT INTO pantalones (idprendas, marca, corto, fotopantalones) values (?,?,?,?)");
			
			Stmt.setInt(1, idprend);
			Stmt.setString(2, marca);
			Stmt.setBoolean(3, corto);
			InputStream fotoPantalonesIS = new FileInputStream(new File(pathPantalones));
			Stmt.setBlob(4, fotoPantalonesIS);
			
			Stmt.executeUpdate();
			} catch (SQLException e2) {
				throw new BDException("Error en el codigo SQL al insertar los datos en la Base de Datos", e2);
			}
		}
	
	
	public static void añadirZapatos(Boolean deportivos, Boolean devestir, String pathZapatos) throws BDException, FileNotFoundException {
		int idprend = 0;
		
		try {
			// 1.PrepareStatement
			Connection conn = DriverManager.getConnection(URL, USUARIO, CONTRASENA);
			
			PreparedStatement Stmt = conn.prepareStatement("SELECT * FROM prendas ORDER BY idprendas DESC LIMIT 1");
			
			// 2.Execute SQL query and return value of id_prendas to idprend to use, allowing a link between prendas and zapatos to be created
			ResultSet rs = Stmt.executeQuery();
			while (rs.next()) {
				idprend = rs.getInt("idprendas");
			}
			
			
			modise.Principal.BDLogger.log(Level.FINE, "Codigo ejecutado SQL: " + Stmt + ", idprendas obtenido: " + rs);
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		
		try {
			// 1.PrepareStatement
			Connection conn = DriverManager.getConnection(URL, USUARIO, CONTRASENA);
			
			PreparedStatement Stmt = conn.prepareStatement("INSERT INTO zapatos (idprendas, deportivos, deVestir, fotozapatos) values (?,?,?,?)");
			
			Stmt.setInt(1, idprend);
			Stmt.setBoolean(2, deportivos);
			Stmt.setBoolean(3, devestir);
			InputStream fotoZapatoIS = new FileInputStream(new File(pathZapatos));
			Stmt.setBlob(4, fotoZapatoIS);
			
			Stmt.executeUpdate();
			} catch (SQLException e2) {
				throw new BDException("Error en el codigo SQL al insertar los datos en la Base de Datos", e2);
			}
		}

	//Sin terminar, necesito el CODIGO SQL para hacer el metodo de extracción de fotos

		// 1. Tiene que mirar en la BD en tabla prendas, que camiseta, chaqueta, gorro, pantalon, zapatos tiene como valor nivelImp < 50 para outfit soleado y usar el id para sacar cada TIPO

				// booleans 0 = false, 1 = true para genero! 0 male 1 female
	
				//luego una vez obtenido las tablas join, sacar 1 foto de cada una de estas para poder ir añadiendo una por una a la ventana JAVA en principal.java
				//hay un String estilo y Boolean genero para poder hacer condiciones de seleccionar WHERE genero = 0,1 y estiloPrendas = ""
				//Si hacemos un estilo IN = estiloseleccionado ya lo tendriamos, pero si se selecciona no tengo estilo en mente hacemos 1 metodo mas para prendas que no miren 1 estilo especifico
				// que son los sql que ya tenemos arriba. Eso cubriría todas las condiciones que nos hacen falta.
		
		// 2. Tiene que devolver la foto de cada una de estas prendas como un objeto que podamos añadir a la ventana en Principal.java
		// 3. Mi recomendacion es hacer varias conexiones en el metodo (5, una para cada prenda) para ir sacando foto por foto con PreparedStatements
		// 4. Habrá que hacer 1 metodo outfitsoleado, 1 metodo outfitLluvia
		// 5. las condiciones (soleado nivelImp < 40) (nublado pantalones = largos ^ chaquetas largas)
		// 6. Hacemos metodos para cada estilo, o creamos 1 metodo para cada tiempo? res: hacemos 1 metodo para cada tiempo en un principio, y también el mismo metodo para Masculino y Femenino

		// 7. Para el metodo recursivo no hacer join
	
	public static void crearOutfitSoleado(String estilo, Boolean genero) throws BDException{
	
		try {
			Connection conn = DriverManager.getConnection(URL, USUARIO, CONTRASENA);
			
			PreparedStatement Stmt = conn.prepareStatement(" ");
			
			ResultSet rs = Stmt.executeQuery();
			
			while(rs.next()) {
				java.sql.Blob b = rs.getBlob(2);
				byte bt[] = b.getBytes(1, (int)b.length());
				Image img = Toolkit.getDefaultToolkit().createImage(bt);
				//photoL.setIcon(new ImageIcon(img));
			}
			
			
		} catch (SQLException e) {
			throw new BDException ("error al conectar con la BD", e);
		}
		
		
	}

}
