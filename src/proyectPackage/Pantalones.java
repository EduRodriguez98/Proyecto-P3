package proyectPackage;

public class Pantalones extends Prendas{

	String marca;
	Boolean corto;
	Boolean largo;
	
	public Pantalones(String colorPrincipal, String colorSecundario, String marca, Boolean corto, Boolean largo) {
		super(colorPrincipal, colorSecundario);
		this.marca = marca;
		this.corto = corto;
		this.largo = largo;
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

	public Boolean getLargo() {
		return largo;
	}

	public void setLargo(Boolean largo) {
		this.largo = largo;
	}

	@Override
	public String toString() {
		return "Pantalones [Marca: " + marca + ", Corto: " + corto + ", Largo: " + largo + ", Color Principal: "
				+ getColorPrincipal() + ", Color Secundario: " + getColorSecundario() + "]";
	}
	
	
}
