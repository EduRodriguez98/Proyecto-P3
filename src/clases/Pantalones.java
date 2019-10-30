package clases;

public class Pantalones extends Prendas{

	String marca;
	Boolean corto;
	
	
	public Pantalones(String colorPrincipal, String colorSecundario, String marca, Boolean corto) {
		super(colorPrincipal, colorSecundario);
		this.marca = marca;
		this.corto = corto;
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
		return "Pantalones [Marca: " + marca + ", Corto: " + corto + ", Color Principal: "
				+ getColorPrincipal() + ", Color Secundario: " + getColorSecundario() + "]";
	}
	
	
}
