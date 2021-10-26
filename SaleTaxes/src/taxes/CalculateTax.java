package taxes;

import java.math.BigDecimal;

public interface CalculateTax {
	public static final double ONE_HOUNDRED = 100.0;

	double calculateTax(final double price);

	default double roundUp(final double value) {
		final BigDecimal result = new BigDecimal(Math.ceil(value * 20.0) / 20.0);
		return result.doubleValue();
	}

}