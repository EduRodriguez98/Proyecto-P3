package clases;

public class Camiseta extends Prendas{

	Boolean logotipo;
	Boolean rayas;
	Boolean cuadros;
	
	public Camiseta(String colorPrincipal, String colorSecundario, Boolean logotipo, Boolean rayas,
			Boolean cuadros) {
		super(colorPrincipal, colorSecundario);
		this.logotipo = logotipo;
		this.rayas = rayas;
		this.cuadros = cuadros;
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
		return cuadros;
	}

	public void setCuadros(Boolean cuadros) {
		this.cuadros = cuadros;
	}

	@Override
	public String toString() {
		return "Camiseta [logotipo: " + logotipo + ", rayas: " + rayas + ", Cuadros: " + cuadros + ", Color Principal: "
				+ getColorPrincipal() + ", Color Secundario: " + getColorSecundario() + "]";
	}
	
	
	
}
