package proyectPackage;

public class Gorros extends Prendas{

	Boolean Verano;
	Boolean Invierno;
	
	public Gorros(String colorPrincipal, String colorSecundario, Boolean verano, Boolean invierno) {
		super(colorPrincipal, colorSecundario);
		Verano = verano;
		Invierno = invierno;
	}

	public Boolean getVerano() {
		return Verano;
	}

	public void setVerano(Boolean verano) {
		Verano = verano;
	}

	public Boolean getInvierno() {
		return Invierno;
	}

	public void setInvierno(Boolean invierno) {
		Invierno = invierno;
	}

	@Override
	public String toString() {
		return "Gorros [Verano: " + Verano + ", Invierno: " + Invierno + ", Color Principal: " + getColorPrincipal()
				+ ", Color Secundario: " + getColorSecundario() + "]";
	}
	
	
}
