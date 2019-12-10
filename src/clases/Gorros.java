package clases;

public class Gorros extends Prendas {

	private Boolean verano;

	public Gorros(Color colorPrincipal, Boolean genero, Boolean verano) {
		super(colorPrincipal, genero);
		this.verano = verano;
	}

	public Boolean getVerano() {
		return verano;
	}

	public void setVerano(Boolean verano) {
		this.verano = verano;
	}

	@Override
	public String toString() {
		return "Gorros [verano = " + verano + ", color = " + getColorPrincipal() + ", genero = "
				+ isGenero() + "]";
	}

	

}
