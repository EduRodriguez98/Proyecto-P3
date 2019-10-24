package proyectPackage;

public class Prendas {

	private String colorPrincipal;
	private String colorSecundario;
	private String tipoDePrenda;
	
	public Prendas(String colorPrincipal, String colorSecundario, String tipoDePrenda) {
		super();
		this.colorPrincipal = colorPrincipal;
		this.colorSecundario = colorSecundario;
		this.tipoDePrenda = tipoDePrenda;
	}
	
	public Prendas(Prendas p) {
		super();
		this.colorPrincipal = p.colorPrincipal;
		this.colorSecundario = p.colorSecundario;
		this.tipoDePrenda = p.tipoDePrenda;
	}
	
	public Prendas() {
		super();
		this.colorPrincipal = "";
		this.colorSecundario = "";
		this.tipoDePrenda = "";
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

	public String getTipoDePrenda() {
		return tipoDePrenda;
	}

	public void setTipoDePrenda(String tipoDePrenda) {
		this.tipoDePrenda = tipoDePrenda;
	}

	@Override
	public String toString() {
		return "Prendas [tipoDePrenda = "+ tipoDePrenda + ", colorPrincipal = " + colorPrincipal + ", colorSecundario = " + colorSecundario + " ]";
	}

	

}
