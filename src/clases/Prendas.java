package clases;

public abstract class Prendas {

	private static int contador = 0;

	private int id_prendas;
	private Color colorPrincipal;
	private boolean genero;
	
	public Prendas(Color colorPrincipal, boolean genero) {
		super();
		this.id_prendas = contador;
		contador++;
		this.colorPrincipal = colorPrincipal;
		this.genero = genero;
	}

	public Color getColorPrincipal() {
		return colorPrincipal;
	}

	public void setColorPrincipal(Color colorPrincipal) {
		this.colorPrincipal = colorPrincipal;
	}

	public boolean isGenero() {
		return genero;
	}

	public void setGenero(boolean genero) {
		this.genero = genero;
	}

	@Override
	public String toString() {
		return "Prendas [id_prendas = " + id_prendas + ", colorPrincipal = " + colorPrincipal + ", genero = " + genero + "]";
	}

}
