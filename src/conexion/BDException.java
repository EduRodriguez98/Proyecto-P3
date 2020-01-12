package conexion;

public class BDException extends Exception {
	/**
	 * clase para una excepcion propia
	 */
	private static final long serialVersionUID = 1L;

	public BDException(String mensaje, Throwable e) {
		super(mensaje, e);
	}

}
