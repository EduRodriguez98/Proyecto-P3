package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;

public class Conexion {

	private static final String CONTROLADOR = "com.mysql.cj.jdbc.Driver";
	private static final String URL = "jdbc:mysql://localhost:3306/modise_schema?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	private static final String USUARIO = "root";
	private static final String CONTRASENA = "1234Abcd"; //

	static {
		try {
			Class.forName(CONTROLADOR);
		} catch (ClassNotFoundException e) {
			TestConexion.BDLogger.log(Level.INFO, "Error al cargar el controlador");
			// System.out.println("Error al cargar el controlador");
			e.printStackTrace();
		}
	}

	public static Connection conectar() {

		Connection conexion = null;

		try {

			conexion = DriverManager.getConnection(URL, USUARIO, CONTRASENA);
			// TestConexion.BDLogger.log(Level.INFO, "Se ha conectado");
			// System.out.println("Se ha conectado");

		} catch (SQLException e) {

			TestConexion.BDLogger.log(Level.INFO, "Error en la conexion");
			// System.out.println("Error en la conexion");
			e.printStackTrace();
		}

		return conexion;

	}

}
