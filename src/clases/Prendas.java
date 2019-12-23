package clases;

public abstract class Prendas {

	private static int contador = 0;

	private int id_prendas;
	private Color colorPrincipal;
	private boolean genero;
	private int nivelFashion;
	private int nivelImpermeable;
	
	public Prendas(Color colorPrincipal, boolean genero, int nivelFashion, int nivelImpermeable) {
		super();
		this.id_prendas = contador;
		contador++;
		this.colorPrincipal = colorPrincipal;
		this.genero = genero;
		this.nivelFashion = nivelFashion;
		this.nivelImpermeable = nivelImpermeable;
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

	public int getNivelFashion() {
		return nivelFashion;
	}

	public void setNivelFashion(int nivelFashion) {
		this.nivelFashion = nivelFashion;
	}

	public int getNivelImpermeable() {
		return nivelImpermeable;
	}

	public void setNivelImpermeable(int nivelImpermeable) {
		this.nivelImpermeable = nivelImpermeable;
	}

	@Override
	public String toString() {
		return "Prendas [id_prendas = " + id_prendas + ", colorPrincipal = " + colorPrincipal + ", genero = " + genero
				+ ", nivelFashion = " + nivelFashion + ", nivelImpermeable = " + nivelImpermeable + "]";
	}

	
	
	
	

}
