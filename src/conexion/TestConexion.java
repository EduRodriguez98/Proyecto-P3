package conexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class TestConexion {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Conexion conexion = new Conexion();
		Connection cn = null;
		Statement stm = null;
		ResultSet rs = null;

		PreparedStatement ps;

		try {
			cn = conexion.conectar();
			stm = cn.createStatement();
			rs = stm.executeQuery("SELECT * FROM usuario");
			ps = cn.prepareStatement(
					"INSERT INTO usuario(nom_usuario, correo, administrador, edad, contrasena, genero) VALUES(?,?,?,?,?,?)");

			// ps.setString(1, txtCrearNombre.getText());

			int res = ps.executeUpdate();

			if (res > 0) {
				JOptionPane.showMessageDialog(null, "Usuario creado correctamente");
			} else {
				JOptionPane.showMessageDialog(null, "Error al crear el usuario");
			}

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
			// TODO Auto-generated catch block
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
