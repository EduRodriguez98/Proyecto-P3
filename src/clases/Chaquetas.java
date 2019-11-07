package clases;

public class Chaquetas extends Prendas {

	private Boolean larga;
	private Boolean lisa;

	public Chaquetas(Color colorPrincipal, Boolean larga, Boolean lisa) {
		super(colorPrincipal);
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
		return "Chaquetas [Id_prendas = " + getId_prendas() + ", ColorPrincipal = " + getColorPrincipal() + "larga = "
				+ larga + ", lisa = " + lisa + "]";

	}

}
