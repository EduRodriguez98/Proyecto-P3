package clases;

public class Camisetas extends Prendas {

	private Boolean logotipo;
	private Boolean rayas;
	private Boolean cuadros;
	
	public Camisetas(Color colorPrincipal, boolean genero, Boolean logotipo, Boolean rayas, Boolean cuadros) {
		super(colorPrincipal, genero);
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
		return "Camisetas [logotipo = " + logotipo + ", rayas = " + rayas + ", cuadros = " + cuadros
				+ ", color = " + getColorPrincipal() + ", genero = " + isGenero() + "]";
	}

	
	


}
