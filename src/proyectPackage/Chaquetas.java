package proyectPackage;

public class Chaquetas extends Prendas {

	Boolean larga;
	Boolean lisa;
	
	public Chaquetas(String colorPrincipal, String colorSecundario, Boolean larga, Boolean lisa) {
		super(colorPrincipal, colorSecundario);
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
		return "Chaquetas [larga: " + larga + ", lisa: " + lisa + ", Color Principal:" + getColorPrincipal()
				+ ", Color Secundario: " + getColorSecundario() + "]";
	}
	
	
}
