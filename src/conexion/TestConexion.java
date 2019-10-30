package conexion;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestConexion {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Conexion conexion = new Conexion();
		Connection cn = null;
		Statement stm = null;
		ResultSet rs = null;
		
		
		try {
			cn = conexion.conectar();
			cn.createStatement();
			rs = stm.executeQuery("SELECT * FROM usuario");
			
			while(rs.next()) {
				int idUsuario = rs.getInt(1);
				String nom_Usuario = rs.getString(2);
				String correo = rs.getString(3);
				boolean administrador = rs.getBoolean(4);
				int edad = rs.getInt(5);
				int id_camisetas = rs.getInt(6);
				int id_chaquetas = rs.getInt(7);
				int id_gorros = rs.getInt(8);
				int id_pantalones = rs.getInt(9);
				int id_zapatos = rs.getInt(10);
				
				System.out.println(idUsuario + " - " + nom_Usuario + " - " + correo + " - " + administrador + " - " + edad + " - " + id_camisetas + " - " + id_chaquetas + " - " + id_gorros + " - " + id_pantalones + " - " + id_zapatos);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				if(rs!= null) {
					rs.close();
				}
				
				if(stm!= null) {
					stm.close();
				}
				
				if(cn != null) {
					cn.close();
				}
				
			} catch(Exception e2) {
				e2.printStackTrace();
			}
		}
		
	}

}
