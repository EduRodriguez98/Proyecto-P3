package clases;

public class Camisetas extends Prendas {

	private Boolean logotipo;
	private Boolean rayas;
	private Boolean cuadros;

	public Camisetas(Color colorPrincipal, Boolean logotipo, Boolean rayas, Boolean cuadros) {
		super(colorPrincipal);
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
		return "Camisetas [Id_prendas = " + getId_prendas() + ", Color Principal = " + getColorPrincipal()
				+ "logotipo = " + logotipo + ", rayas = " + rayas + ", cuadros = " + cuadros + "]";
	}

}
