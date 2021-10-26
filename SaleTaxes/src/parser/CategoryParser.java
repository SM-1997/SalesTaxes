package parser;

import java.io.IOException;
import java.util.Map;

public interface CategoryParser{
	
    Map<String, String> parse(final String p0) throws IOException;
    
}