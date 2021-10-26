package exception;

public class UnconsistentDataException extends Exception {
	private static final long serialVersionUID = 1L;
	
	public UnconsistentDataException(String Value) {
		super(Value + " not found in Tax File.");
	}

}
