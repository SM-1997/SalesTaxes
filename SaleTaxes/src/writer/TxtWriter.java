package writer;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class TxtWriter implements Writer {

	@Override
	public void writeOnFile(String path, String text) throws IOException {
		File file = new File(path);
		if (!file.exists())
			file.createNewFile();

		FileWriter fw = new FileWriter(file);
		fw.write(text);
		fw.close();
	}

}
