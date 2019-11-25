package conexion;

import java.sql.Connection;

import org.junit.Before;

public class BDTest {

	@Before
	public static boolean comprobarConexion() {
		Connection conexion = Conexion.conectar();
		if (conexion == null) {
			return false;
		} else {
			return true;
		}
	}

}
