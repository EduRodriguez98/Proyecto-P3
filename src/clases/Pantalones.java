package clases;

public class Pantalones extends Prendas {

	private String marca;
	private Boolean corto;


	public Pantalones(Color colorPrincipal, boolean genero, int nivelFashion, int nivelImpermeable, String marca,
			Boolean corto) {
		super(colorPrincipal, genero, nivelFashion, nivelImpermeable);
		this.marca = marca;
		this.corto = corto;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public Boolean getCorto() {
		return corto;
	}

	public void setCorto(Boolean corto) {
		this.corto = corto;
	}

	@Override
	public String toString() {
		return "Pantalones [marca = " + marca + ", corto = " + corto + ", ColorPrincipal = " + getColorPrincipal()
				+ ", isGenero = " + isGenero() + ", NivelFashion = " + getNivelFashion() + ", NivelImpermeable = "
				+ getNivelImpermeable() + "]";
	}


	

}
