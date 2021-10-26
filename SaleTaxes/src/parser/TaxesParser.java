package parser;

import java.io.IOException;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

public class TaxesParser implements TaxParser {
	private final int NFIELDS = 2;
	private final String FIELD_SEPARATOR = "/";

	public Map<String, Boolean> parse(String path) throws IOException {

		HashMap<String, Boolean> categoriesTaxes = new HashMap<String, Boolean>();
		List<String> lines = ParserUtil.readFile(path);

		lines.forEach(line -> {

			try {
				String[] fields = ParserUtil.splitFields(NFIELDS, FIELD_SEPARATOR, line);
				categoriesTaxes.put(fields[0], Boolean.parseBoolean(fields[1]));
			} catch (Exception e) {
				throw new IllegalArgumentException("Taxes file is not correctly formatted.");
			}

		});

		return categoriesTaxes;
	}
}