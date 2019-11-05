package conexionEdu;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
//import java.sql.Statement;

public class conexion {
	
	private Connection conexion = null;
	
	public void conectar() throws BDException {
		
		try {
			Class.forName("org.sqlite.JDBC");
			conexion = DriverManager.getConnection("jdbc:sqlite://localhost:3306/modise_schema");
		
		} catch (ClassNotFoundException e) {
			throw new BDException("No se pudo cargar el driver", e);
			
		} catch (SQLException e) {
			throw new BDException ("No se ha podido conectar a la Base de Datos", e);
		}
	}

	
	public void desconectar() throws BDException {
		
		try {
			if (conexion !=null) {
				conexion.close();
			}
		} catch (SQLException e) {
			throw new BDException("No se ha podido cerrar la conexion a la Base de Datos", e);
		}
	}
	
	
	/*Ejemplos para sqlite
	
	public void crearTablaUsuario() throws BDException {
		
		try {
			Statement stmt = conexion.createStatement();
			String sql = "CREATE TABLE usuario (id INTEGER PRIMARY KEY, nombre VARCHAR, apellido VARCHAR)";
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			throw new BDException("No se pudo crear la tabla 'Usuario' ", e);
		}
		
	
			
		
	} */
}
