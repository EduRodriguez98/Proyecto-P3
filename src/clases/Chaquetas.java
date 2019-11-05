package clases;

public class Chaquetas extends Prendas {

	private Boolean larga;
	private Boolean lisa;

	public Chaquetas(String colorPrincipal, String colorSecundario, Boolean larga, Boolean lisa) {
		super(colorPrincipal, colorSecundario);
		this.larga = larga;
		this.lisa = lisa;
	}
	
	public Chaquetas() {
		super();
		this.larga = null;
		this.lisa = null;
	}

	public Boolean getLarga() {
		return larga;
	}

	public void setLarga(Boolean larga) {
		this.larga = larga;
	}

	public Boolean getLisa() {
		return lisa;
	}

	public void setLisa(Boolean lisa) {
		this.lisa = lisa;
	}

	@Override
	public String toString() {
		return "Chaquetas [id_prendas = " + getId_prendas() + ", color principal = " + getColorPrincipal() +
				", color secundario = " + getColorSecundario() + ", larga = " + larga + ", lisa = " + lisa + "]";
	}
	
	
	
}
