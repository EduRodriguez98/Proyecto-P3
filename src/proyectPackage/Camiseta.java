package proyectPackage;

public class Camiseta extends Prendas{

	Boolean logotipo;
	Boolean rayas;
	Boolean Cuadros;
	
	public Camiseta(String colorPrincipal, String colorSecundario, Boolean logotipo, Boolean rayas,
			Boolean cuadros) {
		super(colorPrincipal, colorSecundario);
		this.logotipo = logotipo;
		this.rayas = rayas;
		Cuadros = cuadros;
	}

	public Boolean getLogotipo() {
		return logotipo;
	}

	public void setLogotipo(Boolean logotipo) {
		this.logotipo = logotipo;
	}

	public Boolean getRayas() {
		return rayas;
	}

	public void setRayas(Boolean rayas) {
		this.rayas = rayas;
	}

	public Boolean getCuadros() {
		return Cuadros;
	}

	public void setCuadros(Boolean cuadros) {
		Cuadros = cuadros;
	}

	@Override
	public String toString() {
		return "Camiseta [logotipo: " + logotipo + ", rayas: " + rayas + ", Cuadros: " + Cuadros + ", Color Principal: "
				+ getColorPrincipal() + ", Color Secundario: " + getColorSecundario() + "]";
	}
	
	
	
}
