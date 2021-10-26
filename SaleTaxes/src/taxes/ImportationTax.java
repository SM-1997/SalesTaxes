package taxes;

public class ImportationTax implements CalculateTax {

	private static final int IMPORTATION_TAX = 5;

	public double calculateTax(final double price) {
		return roundUp(price / ONE_HOUNDRED * IMPORTATION_TAX);
	}

}
