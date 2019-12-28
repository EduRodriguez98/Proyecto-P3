package clases;

public class Gorros extends Prendas {

	private Boolean verano;

	public Gorros(Color colorPrincipal, boolean genero, int nivelFashion, int nivelImpermeable, Boolean verano) {
		super(colorPrincipal, genero, nivelFashion, nivelImpermeable);
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
		return "Gorros [verano = " + verano + ", Id_prendas = " + getId_prendas() + ", ColorPrincipal = "
				+ getColorPrincipal() + ", isGenero = " + isGenero() + ", NivelFashion = " + getNivelFashion()
				+ ", NivelImpermeable = " + getNivelImpermeable() + "]";
	}
	
	
}
