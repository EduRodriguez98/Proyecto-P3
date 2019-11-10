package clases;

public class Usuario {
	private static int contador = 0;

	private int id_usuario;
	private String nombre_usuario;
	private String correo_usuario;
	private Boolean administrador;
	private int edad;
	private String contrasena;
	private Boolean genero;

	public Usuario(String nombre_usuario, String correo_usuario, Boolean administrador, int edad, String contrasena,
			Boolean genero) {
		super();
		this.id_usuario = contador;
		contador++;
		this.nombre_usuario = nombre_usuario;
		this.correo_usuario = correo_usuario;
		this.administrador = administrador;
		this.edad = edad;
		this.contrasena = contrasena;
		this.genero = genero;
	}

	public Usuario() {
		super();
		this.id_usuario = contador;
		contador++;
		this.nombre_usuario = "";
		this.correo_usuario = "";
		this.administrador = null;
		this.edad = 0;
		this.contrasena = "";
		this.genero = null;
	}

	public int getId_usuario() {
		return id_usuario;
	}

	public void setId_usuario(int id_usuario) {
		this.id_usuario = id_usuario;
	}

	public String getNombre_usuario() {
		return nombre_usuario;
	}

	public void setNombre_usuario(String nombre_usuario) {
		this.nombre_usuario = nombre_usuario;
	}

	public String getCorreo_usuario() {
		return correo_usuario;
	}

	public void setCorreo_usuario(String correo_usuario) {
		this.correo_usuario = correo_usuario;
	}

	public Boolean getAdministrador() {
		return administrador;
	}

	public void setAdministrador(Boolean administrador) {
		this.administrador = administrador;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	public Boolean getGenero() {
		return genero;
	}

	public void setGenero(Boolean genero) {
		this.genero = genero;
	}

	@Override
	public String toString() {
		return "Usuario [id_usuario=" + id_usuario + ", nombre_usuario=" + nombre_usuario + ", correo_usuario="
				+ correo_usuario + ", administrador=" + administrador + ", edad=" + edad + ", contrasena=" + contrasena
				+ ", genero=" + genero + "]";
	}

}
