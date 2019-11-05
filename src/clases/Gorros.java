package clases;

public class Gorros extends Prendas{

	Boolean verano;
	
	
	public Gorros(String colorPrincipal, String colorSecundario, Boolean verano) {
		super(colorPrincipal, colorSecundario);
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
		return "Gorros [id_prendas = " + getId_prendas() + ", id_color = " + getIdColor() + ", color principal = " + getColorPrincipal()
				+ ", color secundario = " + getColorSecundario() + ", verano = " + verano + "]";
	}

	
	
	
}
