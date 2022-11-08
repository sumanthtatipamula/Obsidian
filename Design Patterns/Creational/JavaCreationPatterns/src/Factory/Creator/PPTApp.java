package Factory.Creator;

import Factory.Creator.OfficeApp;
import Factory.Product.Document;
import Factory.Product.PPTDocument;

public class PPTApp extends OfficeApp {
    @Override
    public Document documentFactory() {
        return new PPTDocument();
    }
}
