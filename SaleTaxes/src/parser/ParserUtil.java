package parser;

import exception.FieldsNumberException;
import java.io.IOException;
import java.util.stream.Collectors;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.File;
import java.util.List;

public final class ParserUtil {
	public static List<String> readFile(final String path) throws IOException {
		final File file = new File(path);

		if (!file.exists())
			throw new FileNotFoundException("File " + path + " not exists");

		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);
		List<String> res = br.lines().collect(Collectors.toList());

		br.close();
		return res;
	}

	public static String[] splitFields(final int nFields, final String fieldSeparator, final String line)
			throws FieldsNumberException {

		String[] fields = line.split(fieldSeparator);

		if (fields.length != nFields)
			throw new FieldsNumberException(nFields, fields.length);

		return fields;
	}
}