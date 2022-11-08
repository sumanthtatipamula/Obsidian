package Factory.Creator;

import Factory.Creator.OfficeApp;
import Factory.Product.Document;
import Factory.Product.WordDocument;

public class WordApp extends OfficeApp {
    @Override
    public Document documentFactory() {
        return new WordDocument();
    }
}
