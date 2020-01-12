package conexion;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
			assertTrue(true);
		} else {
			fail();
		}
	}

	@After
	public void closeConexionTest() {
		if (conexion != null) {
			assertTrue(true);
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

		try {
			BaseDatosModise.nuevoUsuario("eneka", "lauram@gmail.com", 0, 20, "123", 1, 2, "rockF");

			PreparedStatement Stmt = conexion.prepareStatement(
					"SELECT * FROM usuario WHERE (nom_usuario = eneko AND correo = lauram@gmail.com)");
			ResultSet rs = Stmt.executeQuery();

			String nombre;
			String correo;
			int admin;
			int edad;
			String contraseña;
			Boolean genero;
			int colorFav;
			String estiloFav;

			while (rs.next()) {
				nombre = rs.getString("nom_usuario");
				correo = rs.getString("correo");
				admin = rs.getInt("administrador");
				edad = rs.getInt("edad");
				contraseña = rs.getString("contrasena");
				genero = rs.getBoolean("edad");
				colorFav = rs.getInt("colorFav");
				estiloFav = rs.getString("estiloFav");

				assertEquals("eneka", nombre);
				assertEquals("lauram@gmail.com", correo);
				assertEquals(0, admin);
				assertEquals(20, edad);
				assertEquals("123", contraseña);
				assertEquals(true, genero);
				assertEquals(2, colorFav);
				assertEquals("rockF", estiloFav);
			}
		} catch (Exception e) {
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
