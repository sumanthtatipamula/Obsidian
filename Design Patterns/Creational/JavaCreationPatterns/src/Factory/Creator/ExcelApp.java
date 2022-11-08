package Factory.Creator;

import Factory.Product.Document;
import Factory.Product.ExcelDocument;

public class ExcelApp extends OfficeApp {
    @Override
    public Document documentFactory() {
        return new ExcelDocument();
    }
}
