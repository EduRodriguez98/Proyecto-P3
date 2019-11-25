package modise;

public class RWException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RWException(String mensaje, Throwable e) {
		super(mensaje, e);
	}
}
