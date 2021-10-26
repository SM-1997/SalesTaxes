package exception;

public class ArgumentsNumberException extends Exception {
	private static final long serialVersionUID = 1L;

	public ArgumentsNumberException(int argsLenght) {
		super("The number of arguments given is: " + argsLenght + ". \n" + "The arguments required are:\n"
				+ "1)The basket file path \n" + "2)The products-categories file path \n"
				+ "3)The categories-taxes file path \n"
				+ "4)The output file path [if not specified will use the same directory of Basket file].");
	}

}
