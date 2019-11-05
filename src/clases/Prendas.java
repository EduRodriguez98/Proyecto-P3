package clases;

public abstract class Prendas extends Color {

	private static int contador = 0;
	
	private int id_prendas;

	public Prendas(String colorPrincipal, String colorSecundario) {
		super(colorPrincipal, colorSecundario);
		this.id_prendas = contador;
		contador++;
	}
	
	public Prendas() {
		super();
		this.id_prendas = contador;
	}

	public int getId_prendas() {
		return id_prendas;
	}

	public void setId_prendas(int id_prendas) {
		this.id_prendas = id_prendas;
	}

	@Override
	public String toString() {
		return "Prendas [id_prendas = " + id_prendas + ", color principal = "
				+ getColorPrincipal() + ", color secundario = " + getColorSecundario() + "]";
	}
	
	
}
