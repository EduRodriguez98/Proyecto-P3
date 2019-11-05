	package clases;

public class Zapatos extends Prendas {

	Boolean deportivos;
	Boolean deVestir;
	
	public Zapatos(String colorPrincipal, String colorSecundario, Boolean deportivos, Boolean deVestir) {
		super(colorPrincipal, colorSecundario);
		this.deportivos = deportivos;
		this.deVestir = deVestir;
	}
	
	public Zapatos() {
		super();
		this.deportivos = null;
		this.deVestir = null;
	}

	public Boolean getDeportivos() {
		return deportivos;
	}

	public void setDeportivos(Boolean deportivos) {
		this.deportivos = deportivos;
	}

	public Boolean getDeVestir() {
		return deVestir;
	}

	public void setDeVestir(Boolean deVestir) {
		this.deVestir = deVestir;
	}

	@Override
	public String toString() {
		return "Zapatos [id_prendas = " + getId_prendas() + ", id_color = " + getIdColor() + ", color principal = " + getColorPrincipal()
				+ ", color secundario = " + getColorSecundario() + "deportivos = " + deportivos + "deVestir = " + deVestir +"]";
	}

	
	
	
	
}
