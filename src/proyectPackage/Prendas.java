package proyectPackage;

public abstract class Prendas {

	private String colorPrincipal;
	private String colorSecundario;
	
	
	public Prendas(String colorPrincipal, String colorSecundario) {
		super();
		this.colorPrincipal = colorPrincipal;
		this.colorSecundario = colorSecundario;
		
	}
	
	public Prendas(Prendas p) {
		super();
		this.colorPrincipal = p.colorPrincipal;
		this.colorSecundario = p.colorSecundario;

	}
	
	public Prendas() {
		super();
		this.colorPrincipal = "";
		this.colorSecundario = "";

	}

	public String getColorPrincipal() {
		return colorPrincipal;
	}

	public void setColorPrincipal(String colorPrincipal) {
		this.colorPrincipal = colorPrincipal;
	}

	public String getColorSecundario() {
		return colorSecundario;
	}

	public void setColorSecundario(String colorSecundario) {
		this.colorSecundario = colorSecundario;
	}


	@Override
	public String toString() {
		return "Prendas [Color Principal: " + colorPrincipal + ", Color Secundario: " + colorSecundario + " ]";
	}

	

}
