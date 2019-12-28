package conexion;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class BaseDatosModiseTest {

	Connection conexion = BaseDatosModise.conectar();

	@Before
	public void conexionTest() {
		if (conexion != null) {

		} else {
			fail();
		}
	}

	@After
	public void closeConexionTest() {
		if (conexion != null) {

		} else {
			fail();
		}
	}

	@Test
	public void logInTest() {
		Statement st;
		try {
			st = conexion.createStatement();
			assertTrue(BaseDatosModise.logIn(st, "a", "a"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void esAdminTest() {
		Statement st;
		try {
			st = conexion.createStatement();
			assertTrue(BaseDatosModise.esAdmin(st, "edudor@gmail.com"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void existeUsuarioTest() {
		Statement st;
		try {
			st = conexion.createStatement();
			assertTrue(BaseDatosModise.existeUsuario(st, "lauram@gmail.com"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void nuevoUsuarioTest() {
		Statement st;
		try {
			st = conexion.createStatement();
			BaseDatosModise.nuevoUsuario(st, "eneko", "lauram@gmail.com", 0, 20, "123", 1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void eliminarUsuarioTest() {
		Statement st;
		try {
			st = conexion.createStatement();
			BaseDatosModise.eliminarUsuario(st, "lauram@gmail.com");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void cambiarContraseñaTest() {
		Statement st;
		try {
			st = conexion.createStatement();
			BaseDatosModise.cambiarContraseña(st, "123", "lauram@gmail.com");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void cambiarAdminTest() {
		Statement st;
		try {
			st = conexion.createStatement();
			BaseDatosModise.cambiarAdmin(st, "lauram@gmail.com", 0);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
