package purchase;

public class Product {

	private String name;
	private double price;
	private Boolean isImported;

	public Product(String name, double price, Boolean isImported) {
		this.name = name;
		this.price = price;
		this.isImported = isImported;
	}

	@Override
	public String toString() {
		return name;
	}

	public Boolean IsImported() {
		return isImported;
	}

	public double getPrice() {
		return price;
	}

	public String getName() {
		return name;
	}

}
