package format;

import java.text.DecimalFormat;

public class FormatterTwoDecimalPlaces implements Formatter {

	private String pattern = "###,###,##0.00";

	public String format(double value) {
		DecimalFormat df = new DecimalFormat(pattern);
		return df.format(value).replace(",", ".");
	}
	
}