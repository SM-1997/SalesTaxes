package exception;

public class CategoryNotFoundException extends Exception {
	private static final long serialVersionUID = 1L;

	public CategoryNotFoundException(String itemNotFound ) {
		super("Product " + itemNotFound + " not found in Categories File.");
	}
}
