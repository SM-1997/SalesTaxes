package purchase;

import format.Formatter;
import format.FormatterTwoDecimalPlaces;

public class Purchase {

	private static final String IMPORTED = "Imported ";
	private double taxedPrice;
	private double taxes;
	private int quantity;
	private Product product;

	public Purchase(Product product, int quantity, double taxedPrice, double taxes) {
		this.taxedPrice = taxedPrice;
		this.taxes = taxes;
		this.product = product;
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		Formatter f = new FormatterTwoDecimalPlaces();
		return quantity + " " + (product.IsImported() ? IMPORTED : "") + product.toString() + ": "
				+ f.format(taxedPrice);
	}

	public double getTaxes() {
		return taxes;
	}

	public double getTaxedPrice() {
		return taxedPrice;
	}

}
