package conexion;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Before;
import org.junit.Test;

public class BDTest {

	static Connection conexion = BaseDatosModise.conectar();

	@Before
	public void conexionTest() {
		if (conexion != null) {

		} else {
			fail();
		}
	}

	/*
	 * @After public boolean closeConexionTest() { if (conexion == null) { return
	 * true; } else { return false; } }
	 */

	@Test
	public void logInTest() {
		Statement st;
		try {
			st = conexion.createStatement();
			assertTrue(BaseDatosModise.logIn(st, "eneko.perez@opendeusto.es", "12345"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void esAdminTest() {
		Statement st;
		try {
			st = conexion.createStatement();
			assertTrue(BaseDatosModise.esAdmin(st, "eneko.perez@opendeusto.es"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void existeUsuarioTest() {
		Statement st;
		try {
			st = conexion.createStatement();
			assertTrue(BaseDatosModise.existeUsuario(st, "eneko.perez@opendeusto.es"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void nuevoUsuarioTest() {
		Statement st;
		try {
			st = conexion.createStatement();
			BaseDatosModise.nuevoUsuario(st, "eneko", "eneko.perez@opendeusto.es", 0, 20, "12345", 1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void eliminarUsuarioTest() {
		Statement st;
		try {
			st = conexion.createStatement();
			BaseDatosModise.eliminarUsuario(st, "eneko.perez@opendeusto.es");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void cambiarContraseñaTest() {
		Statement st;
		try {
			st = conexion.createStatement();
			BaseDatosModise.cambiarContraseña(st, "12345", "eneko.perez@opendeusto.es");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void cambiarAdminTest() {
		Statement st;
		try {
			st = conexion.createStatement();
			BaseDatosModise.cambiarAdmin(st, "eneko.perez@opendeusto.es", 0);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
