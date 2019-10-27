package proyectPackage;

public class Zapatos extends Prendas {

	Boolean Deportivos;
	Boolean deVestir;
	
	public Zapatos(String colorPrincipal, String colorSecundario, Boolean deportivos, Boolean deVestir) {
		super(colorPrincipal, colorSecundario);
		Deportivos = deportivos;
		this.deVestir = deVestir;
	}

	public Boolean getDeportivos() {
		return Deportivos;
	}

	public void setDeportivos(Boolean deportivos) {
		Deportivos = deportivos;
	}

	public Boolean getDeVestir() {
		return deVestir;
	}

	public void setDeVestir(Boolean deVestir) {
		this.deVestir = deVestir;
	}

	@Override
	public String toString() {
		return "Zapatos [Deportivos: " + Deportivos + ", de Vestir: " + deVestir + ", Color Principal: "
				+ getColorPrincipal() + ", Color Secundario: " + getColorSecundario() + "]";
	}
	
	
	
}
