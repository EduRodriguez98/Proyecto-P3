package clases;

public class Chaquetas extends Prendas {

	private Boolean larga;
	private Boolean lisa;

	public Chaquetas(Color colorPrincipal, Boolean genero, Boolean larga, Boolean lisa) {
		super(colorPrincipal,genero);
		this.larga = larga;
		this.lisa = lisa;
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
		return "Chaquetas [larga = " + larga + ", lisa = " + lisa + ", color = " + getColorPrincipal()
				+ ", genero = " + isGenero() + "]";
	}

	

}
