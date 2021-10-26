package main;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import exception.CategoryNotFoundException;
import exception.TaxForCategoryNotFoundException;
import exception.UnconsistentDataException;
import format.Formatter;
import format.FormatterTwoDecimalPlaces;
import purchase.Product;
import purchase.Purchase;
import taxes.BaseTax;
import taxes.CalculateTax;
import taxes.ImportationTax;

public class ReceiptWriter {
	private static final String TOTAL = "Total: ";
	private static final String SALES_TAXES = "Sales Taxes: ";
	private Map<String, Boolean> categories_BaseTaxed;
	private Map<String, String> categories_Products;

	public ReceiptWriter(Map<String, Boolean> categories_BaseTaxed, Map<String, String> categories_Products) {
		this.categories_BaseTaxed = categories_BaseTaxed;
		this.categories_Products = categories_Products;
	}

	public String createReceive(LinkedHashMap<Product, Integer> shoppingBasket)
			throws UnconsistentDataException, CategoryNotFoundException, TaxForCategoryNotFoundException {

		checkConsistentData();

		ArrayList<Purchase> purchases = buildPurchasesList(shoppingBasket);

		String Receipt = buildReceipt(purchases);

		return Receipt;
	}

	private ArrayList<Purchase> buildPurchasesList(LinkedHashMap<Product, Integer> shoppingBasket)
			throws CategoryNotFoundException, TaxForCategoryNotFoundException {

		ArrayList<Purchase> purchases = new ArrayList<Purchase>();

		for (Entry<Product, Integer> pair : shoppingBasket.entrySet()) {

			double tax = 0;
			int quantity = pair.getValue();
			Product p = pair.getKey();

			if (mustBeBaseTaxed(p)) {
				CalculateTax baseTax = new BaseTax();
				tax += baseTax.calculateTax(p.getPrice());
			}

			if (p.IsImported()) {
				CalculateTax importationTax = new ImportationTax();
				tax += importationTax.calculateTax(p.getPrice());
			}

			tax = tax * quantity;
			double taxedPrice = (quantity * p.getPrice()) + tax;

			purchases.add(new Purchase(p, quantity, taxedPrice, tax));
		}

		return purchases;
	}

	private String buildReceipt(ArrayList<Purchase> purchases) {

		String Receipt = "";

		for (Purchase p : purchases)
			Receipt += p.toString() + " \n";

		Double TotalTaxes = purchases.stream().mapToDouble(p -> p.getTaxes()).sum();
		Double Total = purchases.stream().mapToDouble(p -> p.getTaxedPrice()).sum();

		Formatter f = new FormatterTwoDecimalPlaces();
		Receipt += SALES_TAXES + f.format(TotalTaxes);
		Receipt += " \n";
		Receipt += TOTAL + f.format(Total);

		return Receipt;
	}

	private boolean mustBeBaseTaxed(Product p) throws CategoryNotFoundException, TaxForCategoryNotFoundException {

		if (!categories_Products.containsKey(p.getName()))
			throw new CategoryNotFoundException(p.getName());

		String category = categories_Products.get(p.getName());

		if (!categories_BaseTaxed.containsKey(category))
			throw new TaxForCategoryNotFoundException(category);

		return categories_BaseTaxed.get(category);
	}

	private void checkConsistentData() throws UnconsistentDataException {
		for (Entry<String, String> pair : categories_Products.entrySet()) {
			if (!categories_BaseTaxed.containsKey(pair.getValue()))
				throw new UnconsistentDataException(pair.getValue());
		}
	}

}
