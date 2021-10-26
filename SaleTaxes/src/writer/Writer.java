package writer;

import java.io.IOException;

public interface Writer {

	public void writeOnFile(String path, String text) throws IOException;
}
