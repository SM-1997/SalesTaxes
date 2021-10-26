package parser;

import java.io.IOException;
import exception.BasketFileException;
import purchase.Product;
import java.util.LinkedHashMap;

public interface BasketParser{
	
    LinkedHashMap<Product, Integer> parse(final String path) throws BasketFileException, IOException;
    
}