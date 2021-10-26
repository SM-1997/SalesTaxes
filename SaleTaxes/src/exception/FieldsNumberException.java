package exception;

public class FieldsNumberException extends Exception {
	private static final long serialVersionUID = 1L;

	public FieldsNumberException(int nFieldsAspected, int nFieldsFound) {
		super("The number of fields found (" + nFieldsFound + ") is different from the expected one (" + nFieldsAspected
				+ ")");
	}
}
