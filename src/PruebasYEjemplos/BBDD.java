package PruebasYEjemplos;

import java.awt.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class BBDD {
	
	static Connection c = null;
	static Statement stmt = null;
	
	public static void startBD() {
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:Deusmon.db");
			c.setAutoCommit(false);
			System.out.println("Opened database successfully");
		}catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public void create () {
		try { 
			startBD();
			stmt = c.createStatement();
			String sql = "CREATE TABLE USUARIO(NOM VARCHAR(30) NOT NULL PRIMARY KEY, PASSWORD VARCHAR(30) NOT NULL)"; 
			stmt.executeUpdate(sql);
			stmt.close();
			c.commit();
			c.close();
		} catch ( Exception e ) {
			System.out.println(  e.getClass().getName() + ": " + e.getMessage() );
			System.exit(0);
		}
		System.out.println("Table created successfully");
	}
	
	public String select(String code) { 
		String i = "No hay nada";
		try { 
			startBD();
			PreparedStatement stmt2 = c.prepareStatement("SELECT NOM,PASSWORD FROM USUARIO WHERE NOM=? AND PASSWORD=?;");
			stmt2.executeUpdate();
			stmt2.close();
			stmt.close();
			c.close();
		} catch ( Exception e ) {
			System.out.println( e.getClass().getName() + ": " + e.getMessage() );
			System.exit(0);
		}
		System.out.println( "Operation done successfully");

		return i;
	}
	
	public ArrayList<Usuario> obtenerUsuarios() throws SQLException{
		stmt = c.prepareStatement("SELECT NOM,PASSWORD FROM USUARIO");
		
		ResultSet rs = stmt.executeQuery(null);
		
		ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
		
		while (rs.next()) {
			Usuario usuario = new Usuario();
			usuario.setNombre(rs.getString("NOM"));
			usuario.setContrase�a(rs.getString("PASSWORD"));
			
			usuarios.add(usuario);
		}
		stmt.close();
		return usuarios;
		
	}
	
	public void actualizar(Usuario usuario) throws SQLException{
		
		String sql = "UPDATE usuario SET PASSWORD = ? WHERE NOM = ?";
		PreparedStatement stmt = c.prepareStatement(sql);
		
		stmt.setString(1, usuario.getContrase�a());
		stmt.setString(2, usuario.getNombre());
		
		stmt.executeUpdate();
		stmt.close();
	}
	
	public void delete(Usuario usuario) throws SQLException{
		
		String sql = "DELETE FROM usuario WHERE NOM = ?";
		PreparedStatement stmt = c.prepareStatement(sql);
		
		stmt.setString(1, usuario.getNombre());
		
		stmt.executeUpdate();
		stmt.close();
	}
	
	public Usuario obtener(String nom) throws SQLException {
		String sql = "SELECT NOM,PASSWORD FROM USUARIO WHERE NOM = ?";
		Usuario usuario = new Usuario();
		try {
			PreparedStatement stmt = c.prepareStatement(sql);
			stmt.setString(1, nom);
			
			ResultSet rs = stmt.executeQuery();
			
			if(rs.next()) {
				usuario.setNombre(rs.getString("NOM"));
				usuario.setContrase�a(rs.getString("PASSWORD"));
				
			}
		} catch (SQLException e) {
			// TODO: handle exception
		}finally {
			stmt.close();
		}
		return usuario;
		
	}
	
	public void guardar(Usuario usuario) throws SQLException{
		
		String sql = "INSERT INTO usuario (login, password) VALUES(?,?)";
		PreparedStatement stmt = c.prepareStatement(sql);
		
		stmt.setString(1, usuario.getNombre());
		stmt.setString(2, usuario.getContrase�a());
		
		stmt.executeUpdate();
		stmt.close();
	}
	
	public static void main(String[] args) {
		
	}

}
