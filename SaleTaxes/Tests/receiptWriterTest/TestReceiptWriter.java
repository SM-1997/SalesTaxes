package receiptWriterTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.HashMap;
import java.util.LinkedHashMap;

import org.junit.jupiter.api.Test;

import exception.CategoryNotFoundException;
import exception.TaxForCategoryNotFoundException;
import exception.UnconsistentDataException;
import main.ReceiptWriter;
import purchase.Product;

class TestReceiptWriter {

	private HashMap<String, String> Categories_Product = new HashMap<>();
	private HashMap<String, Boolean> Categories_Taxes = new HashMap<>();
	private LinkedHashMap<Product, Integer> Basket = new LinkedHashMap<>();

	@Test
	void CheckDataConsistentlyTest() {

		Categories_Product.put("Chocolate", "food");
		Categories_Taxes.put("books", false);
		Basket.put(new Product("Chocolate", 1.50, false), 1);

		ReceiptWriter rw = new ReceiptWriter(Categories_Taxes, Categories_Product);
		try {
			rw.createReceive(Basket);
			fail("Expected exception not thrown!");
		} catch (UnconsistentDataException e) {
			assertEquals("food not found in Tax File.", e.getMessage());
		} catch (CategoryNotFoundException e) {
			// not expected
		} catch (TaxForCategoryNotFoundException e) {
			// not expected
		}
	}

	@Test
	void ProductNotFoundTest() {

		Categories_Product.put("Chocolate", "food");
		Categories_Taxes.put("food", false);
		Basket.put(new Product("book", 1.50, false), 1);

		ReceiptWriter rw = new ReceiptWriter(Categories_Taxes, Categories_Product);
		try {
			rw.createReceive(Basket);
			fail("Expected exception not thrown!");
		} catch (UnconsistentDataException e) {
			// not expected
		} catch (CategoryNotFoundException e) {
			assertEquals("Product book not found in Categories File.", e.getMessage());
		} catch (TaxForCategoryNotFoundException e) {
			// not expected
		}
	}

	@Test
	void CategoryNotFoundTest() {

		Categories_Product.put("Chocolate", "food");
		Categories_Taxes.put("books", false);
		Basket.put(new Product("Chocolate", 1.50, false), 1);

		ReceiptWriter rw = new ReceiptWriter(Categories_Taxes, Categories_Product);
		try {
			rw.createReceive(Basket);
			fail("Expected exception not thrown!");
		} catch (UnconsistentDataException e) {
			// not expected
		} catch (CategoryNotFoundException e) {
			// not expected
		} catch (TaxForCategoryNotFoundException e) {
			assertEquals("Tax for category food not found in Taxes File.", e.getMessage());
		}
	}

}
