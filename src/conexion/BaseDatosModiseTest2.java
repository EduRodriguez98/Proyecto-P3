package conexion;

import static org.junit.Assert.fail;

import java.sql.Connection;

import org.junit.Test;

public class BaseDatosModiseTest2 {

	// static Connection conexion = BaseDatosModise.conectar();
	Connection conexion = BaseDatosModise.conectar();

	@Test
	public void logInTest() {

		if (conexion != null) {

		} else {
			fail();
		}
	}

}
