package clases;

public class Color {
	private static int contador = 0;
	
	private int idColor;
	private String colorPrincipal;
	private String ColorSecundario;
	
	
	public Color(String colorPrincipal, String colorSecundario) {
		super();
		this.idColor = contador;
		contador++;
		this.colorPrincipal = colorPrincipal;
		ColorSecundario = colorSecundario;
		
	}
	
	public Color() {
		super();
		this.idColor = contador;
		contador++;
		this.colorPrincipal = "";
		ColorSecundario = "";
		
	}

	public int getIdColor() {
		return idColor;
	}

	public void setIdColor(int idColor) {
		this.idColor = idColor;
	}

	public String getColorPrincipal() {
		return colorPrincipal;
	}

	public void setColorPrincipal(String colorPrincipal) {
		this.colorPrincipal = colorPrincipal;
	}

	public String getColorSecundario() {
		return ColorSecundario;
	}

	public void setColorSecundario(String colorSecundario) {
		ColorSecundario = colorSecundario;
	}

	@Override
	public String toString() {
		return "Color [idColor = " + idColor + ", colorPrincipal = " + colorPrincipal + ", ColorSecundario = "
				+ ColorSecundario + "]";
	}
	
	
}
