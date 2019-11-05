package conexionEdu;

public class BDException extends Exception{
//Clase para Excepcion propia
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BDException(String mensaje, Throwable e) {
		super(mensaje, e);
	}
}
