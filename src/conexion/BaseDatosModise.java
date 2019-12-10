package conexion;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;

import PruebasYEjemplos.Conexion;

public class BaseDatosModise {

	private static final String CONTROLADOR = "com.mysql.cj.jdbc.Driver";
	private static final String URL = "jdbc:mysql://localhost:3306/modise_schema?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	private static final String USUARIO = "root";
	private static final String CONTRASENA = "1234Abcd";

	public static Connection conectar() {

		try {
			Class.forName(CONTROLADOR);
			Connection conexion = DriverManager.getConnection(URL, USUARIO, CONTRASENA);
			modise.Principal.BDLogger.log(Level.INFO, "Se ha conectado");
			
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
		String SentSQL = "insert into pruebaimagenes(imagen) values('" + archivo + "');";
		System.out.println(SentSQL);
		try {
			st.executeUpdate(SentSQL);
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
			System.out.println("catch de -> BaseDatosModise.existeUsuario, por lo que NO EXISTE EL USUARIO");
			return true;
		}
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

	
	public static void añadirPrenda(Statement st, int idcolor, String estiloPrendas) {
		String SentSQL ="INSERT INTO prendas (id_color, estiloprendas) VALUES ('" + idcolor + "','" + estiloPrendas + ")";
				System.out.println(SentSQL);
		try {
			st.executeUpdate(SentSQL);
		} catch (SQLException e) {
			modise.Principal.BDLogger.log(Level.SEVERE, "Error añadriVestimenta Query1\n" + SentSQL, e);
			e.printStackTrace();
		}
	}
	
	
	public static void eliminarPrenda(Statement st, int idcolor, String estiloPrendas) {
		//para borrar la prenda en caso de haber cancelado en la ventana Añadir Vestimenta
	}
	
	public static void añadirVestimenta(Statement st, String corr, String nombreTabla, String[] valores) {
		String SentSQL = "select * from usuario where correo = '" + corr + "';";
		int a = 0;
		try {
			ResultSet rs = st.executeQuery(SentSQL);
			a = rs.getInt("idusuario");
		} catch (SQLException e) {
			modise.Principal.BDLogger.log(Level.SEVERE, "Error añadriVestimenta Query1\n" + SentSQL, e);
			e.printStackTrace();
		}

		String SentSQL2 = "insert into " + nombreTabla + " values(" + a + "," + valores + ");";
		try {
			st.executeUpdate(SentSQL2);
		} catch (SQLException e) {
			modise.Principal.BDLogger.log(Level.SEVERE, "Error añadirVestimenta Query2\n" + SentSQL2, e);
			e.printStackTrace();
		}
	}

class CrearOutfit {

	/*
	  JFileChooser archivo = new JFileChooser();
	  
	  int ventana = archivo.
	  
	  
	  FileNameExtensionFilter filtro = new
	  FileNameExtensionFilter("Formatos de archivos JPEG(*.JPG;*.JPEG)", "jpg",
	  "jpeg");
	  
	  
	  archivo.addChooseableFileFilter(filtro);
	  archivo.setDialogTitle("Abrir archivo"); File ruta = new File
	  ("la ruta en la que tengamos la foto"); archivo.setCurrentDirectory(ruta);
	  int ventana = archivo.showOpenDialog(null); if(ventana ==
	  JFileChooser.APPROVE_OPTION) { File file = archivo.getSelectedFile();
	  txtnomimagen.setText(String.valueOf(file)); Image foto =
	  getToolkit().getImage(txtnomimage.getText()); //importar Image foto =
	  foto.getScaledInstance(110,110,Image.SCALE_DEFAULT); lblfoto.setIcon(new
	  ImageIcon(foto));
	  */
	  }
	
}
