package clases;

public class Zapatos extends Prendas {

	private Boolean deportivos;
	private Boolean deVestir;

	public Zapatos(Color colorPrincipal,Boolean genero, Boolean deportivos, Boolean deVestir) {
		super(colorPrincipal, genero);
		this.deportivos = deportivos;
		this.deVestir = deVestir;
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
		return "Zapatos [deportivos = " + deportivos + ", de Vestir =" + deVestir + ", color = "
				+ getColorPrincipal() + ", genero = " + isGenero() + "]";
	}

	

}
