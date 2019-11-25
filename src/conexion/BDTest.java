package conexion;

import java.sql.Connection;

import org.junit.Before;

public class BDTest {

	@Before
	public static boolean comprobarConexion() {
		Connection cn = Conexion.conectar();
		if (cn == null) {
			return false;
		} else {
			return true;
		}
	}

}
