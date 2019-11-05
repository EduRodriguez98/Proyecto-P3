package clases;

public class Pantalones extends Prendas{

	private String marca;
	private Boolean corto;
	
	public Pantalones(Color colorPrincipal, String marca, Boolean corto) {
		super(colorPrincipal);
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
		return "Pantalones [Id_prendas = " + getId_prendas()
				+ ", ColorPrincipal = " + getColorPrincipal() + "marca = " + marca + ", corto = " + corto + "]";
	}
	
	
}
