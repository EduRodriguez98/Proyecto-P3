package clases;

public abstract class Prendas {

	private static int contador = 0;

	private int id_prendas;
	private Color colorPrincipal;

	public Prendas(Color colorPrincipal) {
		super();
		this.id_prendas = contador;
		contador++;
		this.colorPrincipal = colorPrincipal;
	}

	public Prendas() {
		super();
		this.id_prendas = contador;
		contador++;
		this.colorPrincipal = null;
	}

	public int getId_prendas() {
		return id_prendas;
	}

	public void setId_prendas(int id_prendas) {
		this.id_prendas = id_prendas;
	}

	public Color getColorPrincipal() {
		return colorPrincipal;
	}

	public void setColorPrincipal(Color colorPrincipal) {
		this.colorPrincipal = colorPrincipal;
	}

	@Override
	public String toString() {
		return "Prendas [id_prendas = " + id_prendas + ", colorPrincipal = " + colorPrincipal + "]";
	}

}
