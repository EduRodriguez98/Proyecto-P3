package conexion;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.FileHandler;
import java.util.logging.Logger;


public class TestConexion {

	static Logger BDLogger;

	public static void main(String[] args) {

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

				System.out.println(
						idUsuario + " - " + nom_Usuario + " - " + correo + " - " + administrador + " - " + edad);
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
