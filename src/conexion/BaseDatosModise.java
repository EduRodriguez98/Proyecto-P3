package conexion;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;

import com.mysql.cj.util.StringUtils;

public class BaseDatosModise {

	private static final String CONTROLADOR = "com.mysql.cj.jdbc.Driver";
	private static final String URL = "jdbc:mysql://localhost:3306/modise_schema?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	private static final String USUARIO = "root";
	private static final String CONTRASENA = "1234Abcd";

	public static Connection conectar() {

		try {
			Class.forName(CONTROLADOR);
			Connection conexion = DriverManager.getConnection(URL, USUARIO, CONTRASENA);
			// modise.Principal.BDLogger.log(Level.INFO, "Se ha conectado");
			// HAY QUE MIRAR PORK ESTO DA ERROR!!!!!!!!!!!!!!!!!!!!!!
			return conexion;
		} catch (ClassNotFoundException | SQLException e) {
			modise.Principal.BDLogger.log(Level.SEVERE, "Error en la conexion", e);
			e.printStackTrace();
			return null;
		}
	}

	public static void cerrarBD(final Connection con, final Statement st) {
		try {
			if (st != null)
				st.close();
			if (con != null)
				con.close();
		} catch (SQLException e) {
			modise.Principal.BDLogger.log(Level.SEVERE, "Error al cerrar la base de datos.", e);
			e.printStackTrace();
		}
	}

	// por ahora prueba
	public static void subirFoto(Statement st, File archivo) {
		String sentSQL = "insert into pruebaimagenes(imagen) values('" + archivo + "');";
		System.out.println(sentSQL);
		try {
			st.executeUpdate(sentSQL);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static boolean logIn(Statement st, String user, String passw) {
		String SentSQL = "select * from usuario where correo = '" + user + "' and contrasena = '" + passw + "'";
		try {
			ResultSet rs = st.executeQuery(SentSQL);
			rs.next();

			String a = rs.getString("contrasena");
			if (a.equals(passw)) {
				System.out.println(SentSQL);
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			modise.Principal.BDLogger.log(Level.SEVERE, "Error logIn\n" + SentSQL, e);
			e.printStackTrace();
			return false;
		}

	}

	public static boolean esAdmin(Statement st, String corr) {
		String SentSQL = "select administrador from usuario where correo = '" + corr + "';";
		try {
			ResultSet rs = st.executeQuery(SentSQL);
			rs.next();

			String a = rs.getString("administrador");
			System.out.println("esAdmin: " + SentSQL + a);
			if (a.equals("1")) {
				return true;
			} else if (a.equals("0")) {
				return false;
			} else {
				return false;
			}
		} catch (SQLException e) {
			modise.Principal.BDLogger.log(Level.SEVERE, "Error esAdmin\n" + SentSQL, e);
			e.printStackTrace();
			return false;
		}
	}

	public static boolean existeUsuario(Statement st, String corr) {
		String SentSQL = "select correo from usuario where correo = " + "'" + corr + "';";
		System.out.println(SentSQL);

		try {
			ResultSet rs = st.executeQuery(SentSQL);

			rs.next();

			String b = rs.getString("correo");
			System.out.println(b);
			if (b.equals(corr)) {
				System.out.println("YA EXISTE EL USUARIO");
				return false;
			} else {
				System.out.println("NO EXISTE USUARIO");
				return true;
			}
		} catch (SQLException e) {
			// aqui NO es un error, NO ponemos logger de error ni "e.printStackTrace();"
			System.out.println("catch de -> BaseDatosModise.existeUsuario, por lo que NO EXISTE EL USUARIO");
			return true;
		}
	}

	public static void nuevoUsuario(String nom, String corr, int admin, int ed, String contr, int gen, int colorFavorito, String estilo) throws BDException {
		String forLog = null;
		
		try {
			
		Connection conn = DriverManager.getConnection(URL, USUARIO, CONTRASENA);
		PreparedStatement Stmt = conn.prepareStatement("INSERT INTO USUARIO (nom_usuario, correo, administrador, edad, contrasena, genero, colorFav, estiloFav) values(?,?,?,?,?,?,?,?)");
		
		Stmt.setString(1, nom);
		Stmt.setString(2, corr);
		Stmt.setInt(3, admin);
		Stmt.setInt(4, ed);
		Stmt.setString(5, contr);
		Stmt.setInt(6, gen);
		Stmt.setInt(7, colorFavorito);
		Stmt.setString(8, estilo);
		
		Stmt.executeUpdate();
		
		forLog = Stmt.toString();
			
		} catch (SQLException e) {
			modise.Principal.BDLogger.log(Level.SEVERE, "Error nuevoUsuario\n" + forLog , e);
			throw new BDException ("error en el codigo SQL al ejecutar update", e);
		}
	}

	public static void eliminarUsuario(Statement st, String corr) {
		String SentSQL = "delete from usuario where correo = '" + corr + "';";
		System.out.println(SentSQL);
		try {
			st.executeUpdate(SentSQL);
		} catch (SQLException e) {
			System.out.println(e);
			System.out.println("Catch BaseDatosModise.eliminarUsuario");
			modise.Principal.BDLogger.log(Level.SEVERE, "Error eliminarUsuario\n" + SentSQL, e);
			e.printStackTrace();
		}
	}

	public static void cambiarContraseña(Statement st, String passw, String corr) {
		String SentSQL = "UPDATE usuario SET contrasena = '" + passw + "' WHERE correo = '" + corr + "';";
		System.out.println(SentSQL);
		try {
			st.executeUpdate(SentSQL);
		} catch (SQLException e) {
			modise.Principal.BDLogger.log(Level.SEVERE, "Error cambiarContraseña\n" + SentSQL, e);
			e.printStackTrace();
		}
	}

	public static void cambiarAdmin(Statement st, String corr, int admin) {
		String SentSQL = "UPDATE usuario SET administrador = '" + admin + "' WHERE correo = '" + corr + "';";
		System.out.println(SentSQL);
		try {
			st.executeUpdate(SentSQL);
		} catch (SQLException e) {
			modise.Principal.BDLogger.log(Level.SEVERE, "Error cambiarAdmin\n" + SentSQL, e);
			e.printStackTrace();
		}
	}
	
	public static void añadirPrenda(int idcolor, String estiloPrendas, Boolean genero, int nivelFashion, int nivelImpermeable) throws BDException {
		
		try {
		// 1.PrepareStatement
		Connection conn = DriverManager.getConnection(URL, USUARIO, CONTRASENA);
		
		PreparedStatement Stmt = conn.prepareStatement("INSERT INTO prendas (id_color, estiloPrendas, genero, nivelFash, nivelImp) VALUES ('" + idcolor + "','" + estiloPrendas
				+ "'," +  genero + ",'" + nivelFashion + "','" + nivelImpermeable + "')");
		
		// 2.Execute SQL query
		Stmt.executeUpdate();
		
		} catch (SQLException e) {
			
			throw new BDException("Error al ejecutar SQL Stmt para anyadir prenda", e);
		}
	}
	
	
	public static void eliminarUltimaPrenda() throws BDException {
		
		try {
			// 1.PrepareStatement
			Connection conn = DriverManager.getConnection(URL, USUARIO, CONTRASENA);
			
			PreparedStatement Stmt = conn.prepareStatement("DELETE FROM prendas ORDER BY idprendas DESC LIMIT 1");
			
			// 2.Execute SQL Update
			Stmt.executeUpdate();
		} catch (Exception e) {
			
			 throw new BDException ("Error al ejecutar SQL Stmt para eliminar la ULTIMA prenda", e);
		}		
		
		
	}	
		
	public static void añadirCamiseta(String logotipo, Boolean rayas, Boolean cuadros, String pathCamiseta) throws BDException, FileNotFoundException {
		int idprend = 0;
		
		try {
			// 1.PrepareStatement
			Connection conn = DriverManager.getConnection(URL, USUARIO, CONTRASENA);
			
			PreparedStatement Stmt = conn.prepareStatement("SELECT * FROM prendas ORDER BY idprendas DESC LIMIT 1");
			
			// 2.Execute SQL query and return value of id_prendas to idprend to use, allowing a link between prendas and camisetas to be created
			ResultSet rs = Stmt.executeQuery();
			while (rs.next()) {
				idprend = rs.getInt("idprendas");
			}
			
			modise.Principal.BDLogger.log(Level.FINE, "Codigo ejecutado SQL: " + Stmt + ", idprendas obtenido: " + rs);
			} catch (SQLException e) {
				
				throw new BDException("Error al ejecutar SQL Stmt para seleccionar idprendas en la tabla prendas", e);
			}
		
		try {
		// 1.PrepareStatement
		Connection conn = DriverManager.getConnection(URL, USUARIO, CONTRASENA);
		
		PreparedStatement Stmt = conn.prepareStatement("INSERT INTO camisetas (idprendas, logotipo, rayas, cuadros, fotocamiseta) values (?,?,?,?,?)");
		
		Stmt.setInt(1, idprend);
		Stmt.setString(2, logotipo);
		Stmt.setBoolean(3, rayas);
		Stmt.setBoolean(4, cuadros);
		InputStream fotoCamisetaIS = new FileInputStream(new File(pathCamiseta));
		Stmt.setBlob(5, fotoCamisetaIS);
		
		Stmt.executeUpdate();
		} catch (SQLException e2) {
			throw new BDException("Error en el codigo SQL al insertar los datos en la Base de Datos", e2);
		}
	}
	
	public static void añadirChaquetas(Boolean larga, Boolean lisa, String pathChaquetas) throws BDException, FileNotFoundException {
		int idprend = 0;
			
		try {
			// 1.PrepareStatement
			Connection conn = DriverManager.getConnection(URL, USUARIO, CONTRASENA);
			
			PreparedStatement Stmt = conn.prepareStatement("SELECT * FROM prendas ORDER BY idprendas DESC LIMIT 1");
			
			// 2.Execute SQL query and return value of id_prendas to idprend to use, allowing a link between prendas and chaquetas to be created
			ResultSet rs = Stmt.executeQuery();
			while (rs.next()) {
				idprend = rs.getInt("idprendas");
			}
			
			modise.Principal.BDLogger.log(Level.FINE, "Codigo ejecutado SQL: " + Stmt + ", idprendas obtenido: " + rs);
			} catch (SQLException e) {
			
				throw new BDException("Error al ejecutar SQL Stmt para seleccionar idprendas en la tabla prendas", e);
			}
			
		try {
			// 1.PrepareStatement
			Connection conn = DriverManager.getConnection(URL, USUARIO, CONTRASENA);
			
			PreparedStatement Stmt = conn.prepareStatement("INSERT INTO chaquetas (idprendas, larga, lisa, fotochaqueta) values (?,?,?,?)");
			
			Stmt.setInt(1, idprend);
			Stmt.setBoolean(2, larga);
			Stmt.setBoolean(3, lisa);
			InputStream fotoChaquetaIS = new FileInputStream(new File(pathChaquetas));
			Stmt.setBlob(4, fotoChaquetaIS);
			
			Stmt.executeUpdate();
			} catch (SQLException e2) {
				throw new BDException("Error en el codigo SQL al insertar los datos en la Base de Datos", e2);
			}
		}	
			
	public static void añadirGorros(Boolean verano, String pathGorros) throws BDException, FileNotFoundException {
		int idprend = 0;
		
		try {
			// 1.PrepareStatement
			Connection conn = DriverManager.getConnection(URL, USUARIO, CONTRASENA);
			
			PreparedStatement Stmt = conn.prepareStatement("SELECT * FROM prendas ORDER BY idprendas DESC LIMIT 1");
			
			// 2.Execute SQL query and return value of id_prendas to idprend to use, allowing a link between prendas and gorros to be created
			ResultSet rs = Stmt.executeQuery();
			while (rs.next()) {
				idprend = rs.getInt("idprendas");
			}
			
			modise.Principal.BDLogger.log(Level.FINE, "Codigo ejecutado SQL: " + Stmt + ", idprendas obtenido: " + rs);
			} catch (SQLException e) {
				
				throw new BDException("Error al ejecutar SQL Stmt para seleccionar idprendas en la tabla prendas", e);
			}
			
		try {
			// 1.PrepareStatement
			Connection conn = DriverManager.getConnection(URL, USUARIO, CONTRASENA);
			
			PreparedStatement Stmt = conn.prepareStatement("INSERT INTO gorros (idprendas, verano, fotogorros) values (?,?,?)");
			
			Stmt.setInt(1, idprend);
			Stmt.setBoolean(2, verano);
			InputStream fotoGorrosIS = new FileInputStream(new File(pathGorros));
			Stmt.setBlob(3, fotoGorrosIS);
			
			Stmt.executeUpdate();
			} catch (SQLException e2) {
				throw new BDException("Error en el codigo SQL al insertar los datos en la Base de Datos", e2);
			}
		}
	
	public static void añadirPantalones(String marca, Boolean corto, String pathPantalones) throws BDException, FileNotFoundException {
		int idprend = 0;
		
		try {
			// 1.PrepareStatement
			Connection conn = DriverManager.getConnection(URL, USUARIO, CONTRASENA);
			
			PreparedStatement Stmt = conn.prepareStatement("SELECT * FROM prendas ORDER BY idprendas DESC LIMIT 1");
			
			// 2.Execute SQL query and return value of id_prendas to idprend to use, allowing a link between prendas and pantalones to be created
			ResultSet rs = Stmt.executeQuery();
			while (rs.next()) {
				idprend = rs.getInt("idprendas");
			}
			
			modise.Principal.BDLogger.log(Level.FINE, "Codigo ejecutado SQL: " + Stmt + ", idprendas obtenido: " + rs);
			} catch (SQLException e) {
				
				throw new BDException("Error al ejecutar SQL Stmt para seleccionar idprendas en la tabla prendas", e);
			}
		
		try {
			// 1.PrepareStatement
			Connection conn = DriverManager.getConnection(URL, USUARIO, CONTRASENA);
			
			PreparedStatement Stmt = conn.prepareStatement("INSERT INTO pantalones (idprendas, marca, corto, fotopantalones) values (?,?,?,?)");
			
			Stmt.setInt(1, idprend);
			Stmt.setString(2, marca);
			Stmt.setBoolean(3, corto);
			InputStream fotoPantalonesIS = new FileInputStream(new File(pathPantalones));
			Stmt.setBlob(4, fotoPantalonesIS);
			
			Stmt.executeUpdate();
			} catch (SQLException e2) {
				throw new BDException("Error en el codigo SQL al insertar los datos en la Base de Datos", e2);
			}
		}
	
	
	public static void añadirZapatos(Boolean deportivos, Boolean devestir, String pathZapatos) throws BDException, FileNotFoundException {
		int idprend = 0;
		
		try {
			// 1.PrepareStatement
			Connection conn = DriverManager.getConnection(URL, USUARIO, CONTRASENA);
			
			PreparedStatement Stmt = conn.prepareStatement("SELECT * FROM prendas ORDER BY idprendas DESC LIMIT 1");
			
			// 2.Execute SQL query and return value of id_prendas to idprend to use, allowing a link between prendas and zapatos to be created
			ResultSet rs = Stmt.executeQuery();
			while (rs.next()) {
				idprend = rs.getInt("idprendas");
			}
			
			
			modise.Principal.BDLogger.log(Level.FINE, "Codigo ejecutado SQL: " + Stmt + ", idprendas obtenido: " + rs);
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		
		try {
			// 1.PrepareStatement
			Connection conn = DriverManager.getConnection(URL, USUARIO, CONTRASENA);
			
			PreparedStatement Stmt = conn.prepareStatement("INSERT INTO zapatos (idprendas, deportivos, deVestir, fotozapatos) values (?,?,?,?)");
			
			Stmt.setInt(1, idprend);
			Stmt.setBoolean(2, deportivos);
			Stmt.setBoolean(3, devestir);
			InputStream fotoZapatoIS = new FileInputStream(new File(pathZapatos));
			Stmt.setBlob(4, fotoZapatoIS);
			
			Stmt.executeUpdate();
			} catch (SQLException e2) {
				throw new BDException("Error en el codigo SQL al insertar los datos en la Base de Datos", e2);
			}
		}


	
	
	public static HashMap<Integer, byte[]> crearOutfitSoleado(String estiloj, Boolean generoj, int colorj) throws BDException, SQLException{
		//Utilizamos un HashMap para almacenar el resultado de la Query, y procedemos a meterlo en una JTable para mostrar el resultado en el panel!
		//Al usar idprendas como key, nos aseguramos de que no hay duplicados ya que es una primary key de prendas, y tipos de prendas heredan de ella!
		
		HashMap<Integer, byte[]> mapOutfitSol = new HashMap<Integer, byte[]>();
		
		List<Integer> listaColoresUsados = new ArrayList<Integer>();
		
		List<Integer> listaColoresDisponibles = new ArrayList<Integer>();
		listaColoresDisponibles.add(1);
		listaColoresDisponibles.add(2);
		listaColoresDisponibles.add(3);
		listaColoresDisponibles.add(4);
		listaColoresDisponibles.add(5);
		listaColoresDisponibles.add(6);
		listaColoresDisponibles.add(7);
		listaColoresDisponibles.add(8);
		listaColoresDisponibles.add(9);
		
		Connection conn = DriverManager.getConnection(URL, USUARIO, CONTRASENA);
		try {
			
			PreparedStatement Stmt = conn.prepareStatement("'SELECT fotocamiseta, idprendas FROM camisetasol ORDER BY rand() LIMIT 1 WHERE (estiloPrendas = " + estiloj + ", AND genero = " + generoj + ", AND id_color IN " + StringUtils.joinWithSerialComma(listaColoresDisponibles)+ ")");
			
			ResultSet rs = Stmt.executeQuery();
			
			while(rs.next()) {
				listaColoresUsados.add(rs.getInt("idcolor"));
				
				for (Integer integer : listaColoresUsados) {
					int currentColor = listaColoresUsados.get(integer);
					
					if (currentColor == 1) {
							listaColoresDisponibles.remove(3);
							listaColoresDisponibles.remove(4);
							listaColoresDisponibles.remove(6);

					} else if (currentColor == 2) {
						listaColoresDisponibles.remove(3);
						listaColoresDisponibles.remove(6);

					} else if (currentColor == 3) {
						listaColoresDisponibles.remove(1);
						listaColoresDisponibles.remove(2);
						listaColoresDisponibles.remove(4);

					} else if (currentColor == 4) {
						listaColoresDisponibles.remove(6);
						listaColoresDisponibles.remove(1);
						listaColoresDisponibles.remove(3);

					} else if (currentColor == 6) {
						listaColoresDisponibles.remove(2);
						listaColoresDisponibles.remove(4);
						listaColoresDisponibles.remove(1);
						
					} else if (currentColor == 7) {
						listaColoresDisponibles.remove(9);		

					} else if (currentColor == 9) {
						listaColoresDisponibles.remove(7);
						
					} else {
						//no hace nada si no hay ninguno de estos colores ya que no hace falta actualizar la lista!
					}
				}
				
				Integer idCamiseta = rs.getInt("idprendas");
				byte[] fotobytesCamisetas = rs.getBytes("fotocamiseta");
				
				mapOutfitSol.put(idCamiseta, fotobytesCamisetas);
			}
			rs.close();
			Stmt.close();
			
			
			
			
			Stmt = conn.prepareStatement("'SELECT fotochaqueta, idprendas FROM chaquetasol ORDER BY rand() LIMIT 1 WHERE (estiloPrendas = " + estiloj + ", AND genero = " + generoj + ", AND id_color IN " + StringUtils.joinWithSerialComma(listaColoresDisponibles)+ ")");
			
			rs = Stmt.executeQuery();
			
			while(rs.next()) {
				
				listaColoresUsados.add(rs.getInt("idcolor"));
				
				for (Integer integer : listaColoresUsados) {
					int currentColor = listaColoresUsados.get(integer);
					
					if (currentColor == 1) {
							listaColoresDisponibles.remove(3);
							listaColoresDisponibles.remove(4);
							listaColoresDisponibles.remove(6);

					} else if (currentColor == 2) {
						listaColoresDisponibles.remove(3);
						listaColoresDisponibles.remove(6);

					} else if (currentColor == 3) {
						listaColoresDisponibles.remove(1);
						listaColoresDisponibles.remove(2);
						listaColoresDisponibles.remove(4);

					} else if (currentColor == 4) {
						listaColoresDisponibles.remove(6);
						listaColoresDisponibles.remove(1);
						listaColoresDisponibles.remove(3);

					} else if (currentColor == 6) {
						listaColoresDisponibles.remove(2);
						listaColoresDisponibles.remove(4);
						listaColoresDisponibles.remove(1);
						
					} else if (currentColor == 7) {
						listaColoresDisponibles.remove(9);		

					} else if (currentColor == 9) {
						listaColoresDisponibles.remove(7);
						
					} else {
						//no hace nada si no hay ninguno de estos colores ya que no hace falta actualizar la lista!
					}
				}
				
				Integer idChaqueta = rs.getInt("idprendas");
				byte[] fotobytesChaquetas = rs.getBytes("fotochaqueta");
				
				mapOutfitSol.put(idChaqueta, fotobytesChaquetas);
			}
			rs.close();
			conn.close();
			
			
			
			Stmt = conn.prepareStatement("'SELECT fotogorros, idprendas FROM gorrosol ORDER BY rand() LIMIT 1 WHERE (estiloPrendas = " + estiloj + ", AND genero = " + generoj + ", AND id_color IN " + StringUtils.joinWithSerialComma(listaColoresDisponibles)+ ")");
			
			rs = Stmt.executeQuery();
			
			while(rs.next()) {
				
				listaColoresUsados.add(rs.getInt("idcolor"));
				
				for (Integer integer : listaColoresUsados) {
					int currentColor = listaColoresUsados.get(integer);
					
					if (currentColor == 1) {
							listaColoresDisponibles.remove(3);
							listaColoresDisponibles.remove(4);
							listaColoresDisponibles.remove(6);

					} else if (currentColor == 2) {
						listaColoresDisponibles.remove(3);
						listaColoresDisponibles.remove(6);

					} else if (currentColor == 3) {
						listaColoresDisponibles.remove(1);
						listaColoresDisponibles.remove(2);
						listaColoresDisponibles.remove(4);

					} else if (currentColor == 4) {
						listaColoresDisponibles.remove(6);
						listaColoresDisponibles.remove(1);
						listaColoresDisponibles.remove(3);

					} else if (currentColor == 6) {
						listaColoresDisponibles.remove(2);
						listaColoresDisponibles.remove(4);
						listaColoresDisponibles.remove(1);
						
					} else if (currentColor == 7) {
						listaColoresDisponibles.remove(9);		

					} else if (currentColor == 9) {
						listaColoresDisponibles.remove(7);
						
					} else {
						//no hace nada si no hay ninguno de estos colores ya que no hace falta actualizar la lista!
					}
				}
				
				Integer idGorros = rs.getInt("idprendas");
				byte[] fotobytesGorros = rs.getBytes("fotogorros");
				
				mapOutfitSol.put(idGorros, fotobytesGorros);
			}
			rs.close();
			conn.close();
			
			
			
			Stmt = conn.prepareStatement("'SELECT fotopantalones, idprendas FROM pantalonsol ORDER BY rand() LIMIT 1 WHERE (estiloPrendas = " + estiloj + ", AND genero = " + generoj + ", AND id_color IN " + StringUtils.joinWithSerialComma(listaColoresDisponibles)+ ")");
			
			rs = Stmt.executeQuery();
			
			while(rs.next()) {
				listaColoresUsados.add(rs.getInt("idcolor"));
				
				for (Integer integer : listaColoresUsados) {
					int currentColor = listaColoresUsados.get(integer);
					
					if (currentColor == 1) {
							listaColoresDisponibles.remove(3);
							listaColoresDisponibles.remove(4);
							listaColoresDisponibles.remove(6);

					} else if (currentColor == 2) {
						listaColoresDisponibles.remove(3);
						listaColoresDisponibles.remove(6);

					} else if (currentColor == 3) {
						listaColoresDisponibles.remove(1);
						listaColoresDisponibles.remove(2);
						listaColoresDisponibles.remove(4);

					} else if (currentColor == 4) {
						listaColoresDisponibles.remove(6);
						listaColoresDisponibles.remove(1);
						listaColoresDisponibles.remove(3);

					} else if (currentColor == 6) {
						listaColoresDisponibles.remove(2);
						listaColoresDisponibles.remove(4);
						listaColoresDisponibles.remove(1);
						
					} else if (currentColor == 7) {
						listaColoresDisponibles.remove(9);		

					} else if (currentColor == 9) {
						listaColoresDisponibles.remove(7);
						
					} else {
						//no hace nada si no hay ninguno de estos colores ya que no hace falta actualizar la lista!
					}
				}
				
				Integer idPantalones = rs.getInt("idprendas");
				byte[] fotobytesPantalones = rs.getBytes("fotopantalones");
				
				mapOutfitSol.put(idPantalones, fotobytesPantalones);
			}
			rs.close();
			conn.close();
			
			
			
			Stmt = conn.prepareStatement("'SELECT fotozapatos FROM zapatosol ORDER BY rand() LIMIT 1 WHERE (estiloPrendas = " + estiloj + ", AND genero = " + generoj + ", AND id_color IN " + StringUtils.joinWithSerialComma(listaColoresDisponibles)+ ")");
			
			rs = Stmt.executeQuery();
			
			while(rs.next()) {
				
				listaColoresUsados.add(rs.getInt("idcolor"));
				
				for (Integer integer : listaColoresUsados) {
					int currentColor = listaColoresUsados.get(integer);
					
					if (currentColor == 1) {
							listaColoresDisponibles.remove(3);
							listaColoresDisponibles.remove(4);
							listaColoresDisponibles.remove(6);

					} else if (currentColor == 2) {
						listaColoresDisponibles.remove(3);
						listaColoresDisponibles.remove(6);

					} else if (currentColor == 3) {
						listaColoresDisponibles.remove(1);
						listaColoresDisponibles.remove(2);
						listaColoresDisponibles.remove(4);

					} else if (currentColor == 4) {
						listaColoresDisponibles.remove(6);
						listaColoresDisponibles.remove(1);
						listaColoresDisponibles.remove(3);

					} else if (currentColor == 6) {
						listaColoresDisponibles.remove(2);
						listaColoresDisponibles.remove(4);
						listaColoresDisponibles.remove(1);
						
					} else if (currentColor == 7) {
						listaColoresDisponibles.remove(9);		

					} else if (currentColor == 9) {
						listaColoresDisponibles.remove(7);
						
					} else {
						//no hace nada si no hay ninguno de estos colores ya que no hace falta actualizar la lista!
					}
				}
				
				Integer idZapatos = rs.getInt("idprendas");
				byte[] fotobytesZapatos = rs.getBytes("fotozapatos");
				
				mapOutfitSol.put(idZapatos, fotobytesZapatos);
			}
			rs.close();
			conn.close();
			
		} catch (SQLException e) {
			throw new BDException ("error al conectar con la BD", e);
		}
		return mapOutfitSol;
	}
	
		
	public static HashMap<Integer, byte[]> crearOutfitLluvioso(String estiloj, Boolean generoj, int colorj) throws BDException, SQLException{
		//Utilizamos un HashMap para almacenar el resultado de la Query, y procedemos a meterlo en una JTable para mostrar el resultado en el panel!
		//Al usar idprendas como key, nos aseguramos de que no hay duplicados ya que es una primary key de prendas, y tipos de prendas heredan de ella!
		
		HashMap<Integer, byte[]> mapOutfitLluvioso = new HashMap<Integer, byte[]>();
		
		List<Integer> listaColoresUsados = new ArrayList<Integer>();
		
		List<Integer> listaColoresDisponibles = new ArrayList<Integer>();
		listaColoresDisponibles.add(1);
		listaColoresDisponibles.add(2);
		listaColoresDisponibles.add(3);
		listaColoresDisponibles.add(4);
		listaColoresDisponibles.add(5);
		listaColoresDisponibles.add(6);
		listaColoresDisponibles.add(7);
		listaColoresDisponibles.add(8);
		listaColoresDisponibles.add(9);
		try {
			Connection conn = DriverManager.getConnection(URL, USUARIO, CONTRASENA);
			
			PreparedStatement Stmt = conn.prepareStatement("'SELECT fotocamiseta FROM camisetalluvia ORDER BY rand() LIMIT 1 WHERE (estiloPrendas = " + estiloj + ", AND genero = " + generoj + ", AND id_color IN " + StringUtils.joinWithSerialComma(listaColoresDisponibles)+ ")");

			
			ResultSet rs = Stmt.executeQuery();
			
			while(rs.next()) {
				
				listaColoresUsados.add(rs.getInt("idcolor"));
				
				for (Integer integer : listaColoresUsados) {
					int currentColor = listaColoresUsados.get(integer);
					
					if (currentColor == 1) {
							listaColoresDisponibles.remove(3);
							listaColoresDisponibles.remove(4);
							listaColoresDisponibles.remove(6);

					} else if (currentColor == 2) {
						listaColoresDisponibles.remove(3);
						listaColoresDisponibles.remove(6);

					} else if (currentColor == 3) {
						listaColoresDisponibles.remove(1);
						listaColoresDisponibles.remove(2);
						listaColoresDisponibles.remove(4);

					} else if (currentColor == 4) {
						listaColoresDisponibles.remove(6);
						listaColoresDisponibles.remove(1);
						listaColoresDisponibles.remove(3);

					} else if (currentColor == 6) {
						listaColoresDisponibles.remove(2);
						listaColoresDisponibles.remove(4);
						listaColoresDisponibles.remove(1);
						
					} else if (currentColor == 7) {
						listaColoresDisponibles.remove(9);		

					} else if (currentColor == 9) {
						listaColoresDisponibles.remove(7);
						
					} else {
						//no hace nada si no hay ninguno de estos colores ya que no hace falta actualizar la lista!
					}
				}
				
				Integer idCamiseta = rs.getInt("idprendas");
				byte[] fotobytesCamisetas = rs.getBytes("fotocamiseta");
				
				mapOutfitLluvioso.put(idCamiseta, fotobytesCamisetas);
			}
			rs.close();
			Stmt.close();
			
			
			
			Stmt = conn.prepareStatement("'SELECT fotochaqueta FROM chaquetalluvia ORDER BY rand() LIMIT 1 WHERE (estiloPrendas = " + estiloj + ", AND genero = " + generoj + ", AND id_color IN " + StringUtils.joinWithSerialComma(listaColoresDisponibles)+ ")");
			
			rs = Stmt.executeQuery();
			
			while(rs.next()) {
				
				listaColoresUsados.add(rs.getInt("idcolor"));
				
				for (Integer integer : listaColoresUsados) {
					int currentColor = listaColoresUsados.get(integer);
					
					if (currentColor == 1) {
							listaColoresDisponibles.remove(3);
							listaColoresDisponibles.remove(4);
							listaColoresDisponibles.remove(6);

					} else if (currentColor == 2) {
						listaColoresDisponibles.remove(3);
						listaColoresDisponibles.remove(6);

					} else if (currentColor == 3) {
						listaColoresDisponibles.remove(1);
						listaColoresDisponibles.remove(2);
						listaColoresDisponibles.remove(4);

					} else if (currentColor == 4) {
						listaColoresDisponibles.remove(6);
						listaColoresDisponibles.remove(1);
						listaColoresDisponibles.remove(3);

					} else if (currentColor == 6) {
						listaColoresDisponibles.remove(2);
						listaColoresDisponibles.remove(4);
						listaColoresDisponibles.remove(1);
						
					} else if (currentColor == 7) {
						listaColoresDisponibles.remove(9);		

					} else if (currentColor == 9) {
						listaColoresDisponibles.remove(7);
						
					} else {
						//no hace nada si no hay ninguno de estos colores ya que no hace falta actualizar la lista!
					}
				}
				
				Integer idChaqueta = rs.getInt("idprendas");
				byte[] fotobytesChaquetas = rs.getBytes("fotochaqueta");
				
				mapOutfitLluvioso.put(idChaqueta, fotobytesChaquetas);
			}
			rs.close();
			conn.close();
			
			
			
			Stmt = conn.prepareStatement("'SELECT fotogorros FROM gorrolluvia ORDER BY rand() LIMIT 1 WHERE (estiloPrendas = " + estiloj + ", AND genero = " + generoj + ", AND id_color IN " + StringUtils.joinWithSerialComma(listaColoresDisponibles)+ ")");
			
			rs = Stmt.executeQuery();
			
			while(rs.next()) {
				
				listaColoresUsados.add(rs.getInt("idcolor"));
				
				for (Integer integer : listaColoresUsados) {
					int currentColor = listaColoresUsados.get(integer);
					
					if (currentColor == 1) {
							listaColoresDisponibles.remove(3);
							listaColoresDisponibles.remove(4);
							listaColoresDisponibles.remove(6);

					} else if (currentColor == 2) {
						listaColoresDisponibles.remove(3);
						listaColoresDisponibles.remove(6);

					} else if (currentColor == 3) {
						listaColoresDisponibles.remove(1);
						listaColoresDisponibles.remove(2);
						listaColoresDisponibles.remove(4);

					} else if (currentColor == 4) {
						listaColoresDisponibles.remove(6);
						listaColoresDisponibles.remove(1);
						listaColoresDisponibles.remove(3);

					} else if (currentColor == 6) {
						listaColoresDisponibles.remove(2);
						listaColoresDisponibles.remove(4);
						listaColoresDisponibles.remove(1);
						
					} else if (currentColor == 7) {
						listaColoresDisponibles.remove(9);		

					} else if (currentColor == 9) {
						listaColoresDisponibles.remove(7);
						
					} else {
						//no hace nada si no hay ninguno de estos colores ya que no hace falta actualizar la lista!
					}
				}
				
				Integer idGorros = rs.getInt("idprendas");
				byte[] fotobytesGorros = rs.getBytes("fotogorros");
				
				mapOutfitLluvioso.put(idGorros, fotobytesGorros);
				
			}
			rs.close();
			conn.close();
			
			
			
			Stmt = conn.prepareStatement("'SELECT fotopantalones FROM pantalonlluvia ORDER BY rand() LIMIT 1 WHERE (estiloPrendas = " + estiloj + ", AND genero = " + generoj + ", AND id_color IN " + StringUtils.joinWithSerialComma(listaColoresDisponibles)+")");
			
			rs = Stmt.executeQuery();
			
			while(rs.next()) {
				
				listaColoresUsados.add(rs.getInt("idcolor"));
				
				for (Integer integer : listaColoresUsados) {
					int currentColor = listaColoresUsados.get(integer);
					
					if (currentColor == 1) {
							listaColoresDisponibles.remove(3);
							listaColoresDisponibles.remove(4);
							listaColoresDisponibles.remove(6);

					} else if (currentColor == 2) {
						listaColoresDisponibles.remove(3);
						listaColoresDisponibles.remove(6);

					} else if (currentColor == 3) {
						listaColoresDisponibles.remove(1);
						listaColoresDisponibles.remove(2);
						listaColoresDisponibles.remove(4);

					} else if (currentColor == 4) {
						listaColoresDisponibles.remove(6);
						listaColoresDisponibles.remove(1);
						listaColoresDisponibles.remove(3);

					} else if (currentColor == 6) {
						listaColoresDisponibles.remove(2);
						listaColoresDisponibles.remove(4);
						listaColoresDisponibles.remove(1);
						
					} else if (currentColor == 7) {
						listaColoresDisponibles.remove(9);		

					} else if (currentColor == 9) {
						listaColoresDisponibles.remove(7);
						
					} else {
						//no hace nada si no hay ninguno de estos colores ya que no hace falta actualizar la lista!
					}
				}
				
				Integer idPantalones = rs.getInt("idprendas");
				byte[] fotobytesPantalones = rs.getBytes("fotopantalones");
				
				mapOutfitLluvioso.put(idPantalones, fotobytesPantalones);
				
			}
			rs.close();
			conn.close();
			
			
			
			Stmt = conn.prepareStatement("'SELECT fotozapatos FROM zapatolluvia ORDER BY rand() LIMIT 1 WHERE (estiloPrendas = " + estiloj + ", AND genero = " + generoj + ", AND id_color IN " + StringUtils.joinWithSerialComma(listaColoresDisponibles)+ ")");
			
			rs = Stmt.executeQuery();
			
			while(rs.next()) {
				
				listaColoresUsados.add(rs.getInt("idcolor"));
				
				for (Integer integer : listaColoresUsados) {
					int currentColor = listaColoresUsados.get(integer);
					
					if (currentColor == 1) {
							listaColoresDisponibles.remove(3);
							listaColoresDisponibles.remove(4);
							listaColoresDisponibles.remove(6);

					} else if (currentColor == 2) {
						listaColoresDisponibles.remove(3);
						listaColoresDisponibles.remove(6);

					} else if (currentColor == 3) {
						listaColoresDisponibles.remove(1);
						listaColoresDisponibles.remove(2);
						listaColoresDisponibles.remove(4);

					} else if (currentColor == 4) {
						listaColoresDisponibles.remove(6);
						listaColoresDisponibles.remove(1);
						listaColoresDisponibles.remove(3);

					} else if (currentColor == 6) {
						listaColoresDisponibles.remove(2);
						listaColoresDisponibles.remove(4);
						listaColoresDisponibles.remove(1);
						
					} else if (currentColor == 7) {
						listaColoresDisponibles.remove(9);		

					} else if (currentColor == 9) {
						listaColoresDisponibles.remove(7);
						
					} else {
						//no hace nada si no hay ninguno de estos colores ya que no hace falta actualizar la lista!
					}
				}
				
				Integer idZapatos = rs.getInt("idprendas");
				byte[] fotobytesZapatos = rs.getBytes("fotozapatos");
				
				mapOutfitLluvioso.put(idZapatos, fotobytesZapatos);
				//Una vez sacado las 5 prendas y metidas en el hashmap, se vacian estas listas
				listaColoresUsados.clear();
				listaColoresDisponibles.clear();
				
			}
			rs.close();
			conn.close();
			
		} catch (SQLException e) {
			throw new BDException ("error al conectar con la BD", e);
		}
		return mapOutfitLluvioso;
		
		
	}
	
	//falta el metodo outfitguay que va a ser recursivo con nivelimp y nivelfash.
	
}
