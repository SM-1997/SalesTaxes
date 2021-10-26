package parser;

import java.io.IOException;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

public class CategoriesParser implements CategoryParser {
	private final int NFIELDS = 2;
	private final String FIELD_SEPARATOR = "-";

	public Map<String, String> parse(final String path) throws IOException {

		HashMap<String, String> Categories_Product = new HashMap<String, String>();
		List<String> lines = (List<String>) ParserUtil.readFile(path);

		lines.forEach(line -> {

			try {
				String[] fields = ParserUtil.splitFields(NFIELDS, FIELD_SEPARATOR, line);
				Categories_Product.put(fields[0], fields[1]);
			} catch (Exception e) {
				throw new IllegalArgumentException("Categories file is not correctly formatted.");
			}

		});
		
		return Categories_Product;
	}
}