package clases;

public class Gorros extends Prendas{

	Boolean verano;
	Boolean invierno;
	
	public Gorros(String colorPrincipal, String colorSecundario, Boolean verano, Boolean invierno) {
		super(colorPrincipal, colorSecundario);
		this.verano = verano;
		this.invierno = invierno;
	}

	public Boolean getVerano() {
		return verano;
	}

	public void setVerano(Boolean verano) {
		this.verano = verano;
	}

	public Boolean getInvierno() {
		return invierno;
	}

	public void setInvierno(Boolean invierno) {
		this.invierno = invierno;
	}

	@Override
	public String toString() {
		return "Gorros [Verano: " + verano + ", Invierno: " + invierno + ", Color Principal: " + getColorPrincipal()
				+ ", Color Secundario: " + getColorSecundario() + "]";
	}
	
	
}
