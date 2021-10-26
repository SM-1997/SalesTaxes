package parser;

import java.io.IOException;
import java.util.Map;

public interface TaxParser{
	
    Map<String, Boolean> parse( String path) throws IOException;
    
}