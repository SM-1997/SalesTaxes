
package main;

import writer.Writer;
import parser.TaxParser;
import parser.CategoryParser;
import purchase.Product;
import parser.BasketParser;
import writer.TxtWriter;
import java.util.LinkedHashMap;
import java.util.Map;
import parser.TaxesParser;
import parser.CategoriesParser;
import java.io.IOException;
import parser.BasketsParser;
import java.nio.file.Paths;
import exception.ArgumentsNumberException;

public class Main {
	private static String TXT_BASKET;
	private static String TXT_CATEGORIES;
	private static String TXT_TAXES;
	private static String TXT_OUTPUT;
	private static String TXT_OUTPUT_DEFAULT = "Reiceive_";

	public static void main(final String[] args) throws ArgumentsNumberException {

		if (args.length < 3 || args.length > 4) {
			throw new ArgumentsNumberException(args.length);
		}

		TXT_BASKET = args[0];
		TXT_CATEGORIES = args[1];
		TXT_TAXES = args[2];

		if (args.length > 3) {
			TXT_OUTPUT = args[3];
		} else {
			TXT_OUTPUT = TXT_BASKET.replace(Paths.get(TXT_BASKET).getFileName().toString(),
					TXT_OUTPUT_DEFAULT + Paths.get(TXT_BASKET).getFileName().toString());
		}

		BasketParser bParser = new BasketsParser();
		LinkedHashMap<Product, Integer> ShoppingBasket = null;
		try {
			ShoppingBasket = bParser.parse(TXT_BASKET);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.exit(0);
		}

		CategoryParser cParser = new CategoriesParser();
		Map<String, String> Categories_Products = null;
		try {
			Categories_Products = cParser.parse(TXT_CATEGORIES);
		} catch (IOException e) {
			System.out.println(e.getMessage());
			System.exit(0);
		}

		TaxParser tParser = new TaxesParser();
		Map<String, Boolean> Categories_BaseTaxed = null;
		try {
			Categories_BaseTaxed = tParser.parse(TXT_TAXES);
		} catch (IOException e) {
			System.out.println(e.getMessage());
			System.exit(0);
		}

		ReceiptWriter st = new ReceiptWriter(Categories_BaseTaxed, Categories_Products);
		String receive = null;
		try {
			receive = st.createReceive(ShoppingBasket);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.exit(0);
		}

		Writer w = (Writer) new TxtWriter();
		try {
			w.writeOnFile(TXT_OUTPUT, receive);
		} catch (IOException e) {
			System.out.println(e.getMessage());
			System.exit(0);
		}

		System.out.println("Reiceve written in " + TXT_OUTPUT);
		System.exit(1);
	}

}