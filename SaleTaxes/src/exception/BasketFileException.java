package exception;

public class BasketFileException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public BasketFileException(String err) {
		super("Basket file is not correctly formatted. " + err);
	}
	
}
