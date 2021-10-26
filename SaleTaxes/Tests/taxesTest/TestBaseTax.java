package taxesTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import taxes.BaseTax;
import taxes.CalculateTax;

class TestBaseTax {

	@Test
	void tenPercentCorrectlyCalculated() {
		double price = 10;
		CalculateTax baseTax = new BaseTax();
		assertEquals(1, baseTax.calculateTax(price));
	}

}
