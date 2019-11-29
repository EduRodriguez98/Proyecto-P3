package conexion;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BaseDatosModise {

	/*
	 * public static void CrearUsuario(int id_usuario, String nombre_usuario, String
	 * correo_usuario, boolean administrador, int edad, String contrasena, boolean
	 * genero) {
	 * 
	 * Conexion conexion = new Conexion(); Connection cn = null; Statement stm =
	 * null; ResultSet rs = null;
	 * 
	 * PreparedStatement ps;
	 * 
	 * try { cn = conexion.conectar(); stm = cn.createStatement(); rs =
	 * stm.executeQuery("SELECT * FROM usuario"); ps = cn.prepareStatement(
	 * "INSERT INTO usuario(nom_usuario, correo, administrador, edad, contrasena, genero) VALUES(?,?,?,?,?,?)"
	 * );
	 * 
	 * ps.setString(1, nombre_usuario); // ps.setString(2,
	 * txtCrearCorreo.getText());
	 * 
	 * int res = ps.executeUpdate();
	 * 
	 * if (res > 0) { JOptionPane.showMessageDialog(null,
	 * "Usuario creado correctamente"); } else { JOptionPane.showMessageDialog(null,
	 * "Error al crear el usuario"); }
	 * 
	 * } catch (SQLException e) { e.printStackTrace(); } finally { try { if (rs !=
	 * null) { rs.close(); }
	 * 
	 * if (stm != null) { stm.close(); }
	 * 
	 * if (cn != null) { cn.close(); }
	 * 
	 * } catch (Exception e2) { e2.printStackTrace(); } } }
	 */

	public static boolean logIn(Statement st, String user, String passw, int admin) {
		String SentSQL = "select * from usuario where correo = '" + user + "' and contrasena = '" + passw + "'";
		try {
			ResultSet rs = st.executeQuery(SentSQL);
			rs.next();

			int esAdmin = rs.getInt("administrador");
			System.out.println(esAdmin);
			if (esAdmin == 1) {
				admin = 1;
				System.out.println(true);
			} else if (esAdmin == 0) {
				admin = 0;
				System.out.println(false);
			}

			String a = rs.getString("contrasena");
			if (a.equals(passw)) {
				System.out.println(SentSQL);
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
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
			// e.printStackTrace();
			System.out.println("catch de -> BaseDatosModise.existeUsuario, por lo que NO EXISTE EL USUARIO");
			return true;
		}
	}

	public static void nuevoUsuario(Statement st, int id, String nom, String corr, int admin, Object ed, String contr,
			int gen) {
		String SentSQL = "insert into usuario values(" + id + ",'" + nom + "','" + corr + "'," + admin + "," + ed + ",'"
				+ contr + "'," + gen + ");";
		System.out.println(SentSQL);
		try {
			int val = st.executeUpdate(SentSQL);
			if (val != 1) { // Se tiene que añadir 1 - error si no
				System.out.println("BaseDatosModise.nuevoUsuario: val!=1");
			}
			// rs.next();
		} catch (SQLException e) {
		}
	}

	public static void cambiarContraseña(Statement st, String passw, int id) {
		String SentSQL = "UPDATE usuario SET contrasena = '" + passw + "' WHERE idusuario = " + id + ";";
		System.out.println(SentSQL);
		try {
			int val = st.executeUpdate(SentSQL);
		} catch (SQLException e) {
		}
	}

	public static void cambiarAdmin(Statement st, String corr, int admin, int id) {
		String SentSQL = "UPDATE usuario SET administrador = '" + admin + "' WHERE idusuario = " + id
				+ " and correo = '" + corr + "';";
		System.out.println(SentSQL);
		try {
			int val = st.executeUpdate(SentSQL);
		} catch (SQLException e) {
		}
	}

	public static void BuscarUsuario() {

		Conexion conexion = new Conexion();
		Connection cn = null;
		Statement stm = null;
		ResultSet rs = null;

		try {
			cn = conexion.conectar();
			stm = cn.createStatement();
			rs = stm.executeQuery("SELECT * FROM usuario");

			while (rs.next()) {
				int idUsuario = rs.getInt(1);
				String nom_Usuario = rs.getString(2);
				String correo = rs.getString(3);
				boolean administrador = rs.getBoolean(4);
				int edad = rs.getInt(5);
				String contrasena = rs.getString(6);
				boolean genero = rs.getBoolean(7);

				System.out.println(idUsuario + " - " + nom_Usuario + " - " + correo + " - " + administrador + " - "
						+ edad + " - " + contrasena + " - " + genero);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}

				if (stm != null) {
					stm.close();
				}

				if (cn != null) {
					cn.close();
				}

			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}

	}

}

class CrearOutfit {

	/*
	 * JFileChooser archivo = new JFileChooser();
	 * 
	 * int ventana = archivo.
	 * 
	 * 
	 * FileNameExtensionFilter filtro = new
	 * FileNameExtensionFilter("Formatos de archivos JPEG(*.JPG;*.JPEG)", "jpg",
	 * "jpeg");
	 * 
	 * 
	 * archivo.addChooseableFileFilter(filtro);
	 * archivo.setDialogTitle("Abrir archivo"); File ruta = new File
	 * ("la ruta en la que tengamos la foto"); archivo.setCurrentDirectory(ruta);
	 * int ventana = archivo.showOpenDialog(null); if(ventana ==
	 * JFileChooser.APPROVE_OPTION) { File file = archivo.getSelectedFile();
	 * txtnomimagen.setText(String.valueOf(file)); Image foto =
	 * getToolkit().getImage(txtnomimage.getText()); //importar Image foto =
	 * foto.getScaledInstance(110,110,Image.SCALE_DEFAULT); lblfoto.setIcon(new
	 * ImageIcon(foto));
	 * 
	 * }
	 */
}
