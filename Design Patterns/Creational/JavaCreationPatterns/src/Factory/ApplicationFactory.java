package Factory;

import Factory.Creator.ExcelApp;
import Factory.Creator.OfficeApp;
import Factory.Creator.PPTApp;
import Factory.Creator.WordApp;

public class ApplicationFactory {
    public OfficeApp openApplication(String type){
        switch(type){
            case "PPT":
                return new PPTApp();
            case "EXCEL":
                return new ExcelApp();
        }
        return new WordApp();
    }
}
