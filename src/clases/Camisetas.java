package clases;

public class Camisetas extends Prendas {
	
	private Boolean logotipo;
	private Boolean rayas;
	private Boolean cuadros;
	
	public Camisetas(String colorPrincipal, String colorSecundario, Boolean logotipo, Boolean rayas, Boolean cuadros) {
		super(colorPrincipal, colorSecundario);
		this.logotipo = logotipo;
		this.rayas = rayas;
		this.cuadros = cuadros;
	}
	
	public Camisetas() {
		super();
		this.logotipo = null;
		this.rayas = null;
		this.cuadros = null;
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
		return "Camisetas [id_prendas = " + getId_prendas() + ", id_color = " + getIdColor() + ", color principal = " + getColorPrincipal() + ", color secundario = " + getColorSecundario() +
				", logotipo = " + logotipo + ", rayas = " + rayas + ", cuadros = " + cuadros +  "]";
	}
	
	
}
