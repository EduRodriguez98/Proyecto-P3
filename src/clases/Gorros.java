package clases;

public class Gorros extends Prendas {

	private Boolean verano;

	public Gorros(Color colorPrincipal, Boolean verano) {
		super(colorPrincipal);
		this.verano = verano;
	}

	public Gorros() {
		super();
		this.verano = null;
	}

	public Boolean getVerano() {
		return verano;
	}

	public void setVerano(Boolean verano) {
		this.verano = verano;
	}

	@Override
	public String toString() {
		return "Gorros [Id_prendas = " + getId_prendas() + ", ColorPrincipal = " + getColorPrincipal() + "verano = "
				+ verano + "]";
	}

}
