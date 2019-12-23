package clases;

public class Camisetas extends Prendas {

	private Boolean logotipo;
	private Boolean rayas;
	private Boolean cuadros;
	

	public Camisetas(Color colorPrincipal, boolean genero, int nivelFashion, int nivelImpermeable, Boolean logotipo,
			Boolean rayas, Boolean cuadros) {
		super(colorPrincipal, genero, nivelFashion, nivelImpermeable);
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

	@Override
	public String toString() {
		return "Camisetas [logotipo = " + logotipo + ", rayas = " + rayas + ", cuadros = " + cuadros
				+ ", ColorPrincipal = " + getColorPrincipal() + ", isGenero = " + isGenero() + ", NivelFashion = "
				+ getNivelFashion() + ", NivelImpermeable = " + getNivelImpermeable() + "]";
	}

	public void setCuadros(Boolean cuadros) {
		this.cuadros = cuadros;
	}

	

	
	


}
