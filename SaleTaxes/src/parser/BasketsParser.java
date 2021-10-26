package parser;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.List;
import exception.BasketFileException;
import purchase.Product;
import java.util.LinkedHashMap;
import java.util.regex.Pattern;

public class BasketsParser implements BasketParser {

	private final String IMPORTED = "imported ";
	private final Pattern PATTERN = Pattern.compile("(?<quantity>\\d+)\\s+(?<name>.+)\\s+at\\s+(?<price>\\S+)");;

	public LinkedHashMap<Product, Integer> parse(final String path) throws BasketFileException, IOException {

		LinkedHashMap<Product, Integer> Products = new LinkedHashMap<Product, Integer>();
		List<String> lines = (List<String>) ParserUtil.readFile(path);

		lines.forEach(l -> {

			Boolean isImported = l.contains(IMPORTED);
			if (isImported)
				l = l.replace(IMPORTED, "");

			Matcher m = this.PATTERN.matcher(l);
			if (m.find()) {

				String name = null;
				double price = 0.0;
				int qnt = 0;

				try {
					name = calculateName(m);
					qnt = calculateQnt(m);
					price = calculatePrice(m);
					verifyPositivePrice(price);
				} catch (BasketFileException e) {
					throw e;
				}

				Products.put(new Product(name, price, Boolean.valueOf(isImported)), qnt);
			}
		});

		return Products;
	}

	private int calculateQnt(Matcher m) throws BasketFileException {
		try {
			return Integer.parseInt(m.group("quantity"));
		} catch (Exception e) {
			throw new BasketFileException("The first element of every line must be the product's quantity.");
		}
	}

	private double calculatePrice(Matcher m) throws BasketFileException {
		try {
			return Double.parseDouble(m.group("price"));
		} catch (Exception e) {
			throw new BasketFileException("The last element of every line must be the product's price.");
		}
	}

	private double verifyPositivePrice(double parsedDouble) throws BasketFileException {
		if (parsedDouble <= 0)
			throw new BasketFileException("The product's price can't be 0 or negative.");
		return parsedDouble;
	}

	private String calculateName(Matcher m) throws BasketFileException {
		if (m.group("name").isBlank())
			throw new BasketFileException("The product's name can't be blank.");
		return m.group("name");
	}
}