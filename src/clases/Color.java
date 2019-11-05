package clases;

public class Color {
	private static int contador = 0;
	
	private int idColor;
	protected String nombre_color;
	
	public Color(String color) {
		super();
		this.idColor = contador;
		contador++;
		this.nombre_color = color;
	}
	
	public Color(int idColor, String color) {
		super();
		this.idColor = contador;
		contador++;
		this.nombre_color = null;
	}

	public int getIdColor() {
		return idColor;
	}

	public void setIdColor(int idColor) {
		this.idColor = idColor;
	}

	public String getColor() {
		return nombre_color;
	}

	public void setColor(String color) {
		this.nombre_color = color;
	}

	@Override
	public String toString() {
		return "Color [id color = " + idColor + ", color = " + nombre_color + "]";
	}
	
	
	
}
