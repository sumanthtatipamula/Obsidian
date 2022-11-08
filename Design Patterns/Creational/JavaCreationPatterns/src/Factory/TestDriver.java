package Factory;

import Factory.Creator.ExcelApp;
import Factory.Creator.OfficeApp;
import Factory.Creator.PPTApp;
import Factory.Creator.WordApp;

public class TestDriver {
    public static void  main(String args[]){
        OfficeApp app = new WordApp();
        app.newDocument();
        app = new PPTApp();
        app.newDocument();
        app = new ExcelApp();
        app.newDocument();
    }
}
