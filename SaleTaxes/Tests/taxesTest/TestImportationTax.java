package taxesTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import taxes.CalculateTax;
import taxes.ImportationTax;

class TestImportationTax {

	@Test
	void fivePercentCorrectlyCalculated() {
		double price = 10;
		CalculateTax baseTax = new ImportationTax();
		assertEquals(0.5, baseTax.calculateTax(price));
	}

}
