package Factory.Creator;

import Factory.Product.Document;

public abstract class OfficeApp {
    public abstract Document documentFactory();

    public void  newDocument(){
        Document doc = documentFactory();
        doc.open();
    }
}
