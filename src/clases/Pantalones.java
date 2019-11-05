package clases;

public class Pantalones extends Prendas{

	private String marca;
	private Boolean corto;
	
	
	public Pantalones(String colorPrincipal, String colorSecundario, String marca, Boolean corto) {
		super(colorPrincipal, colorSecundario);
		this.marca = marca;
		this.corto = corto;
	}

	public Pantalones() {
		super();
		this.marca = "";
		this.corto = null;
	}
	
	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public Boolean getCorto() {
		return corto;
	}

	public void setCorto(Boolean corto) {
		this.corto = corto;
	}

	@Override
	public String toString() {
		return "Pantalones [id_prendas = " + getId_prendas() + ", color principal = " + getColorPrincipal() + 
				", color secundario = " + getColorSecundario() + ", marca = " + marca + ", corto = " + corto + "]";
	}

	
	
	
}
