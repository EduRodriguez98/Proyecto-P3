	package clases;

public class Zapatos extends Prendas {

	private Boolean deportivos;
	private Boolean deVestir;
	
	public Zapatos(Color colorPrincipal, Boolean deportivos, Boolean deVestir) {
		super(colorPrincipal);
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
		return "Zapatos [Id_prendas = " + getId_prendas() + ", ColorPrincipal = " + getColorPrincipal() + "deportivos=" + deportivos + ", deVestir=" + deVestir + "]";
	}
	
	
}
