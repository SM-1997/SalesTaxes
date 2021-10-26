package exception;

public class TaxForCategoryNotFoundException extends Exception {
	private static final long serialVersionUID = 1L;
	
	public TaxForCategoryNotFoundException(String itemNotFound ) {
		super("Tax for category " + itemNotFound + " not found in Taxes File.");
	}
	
}
