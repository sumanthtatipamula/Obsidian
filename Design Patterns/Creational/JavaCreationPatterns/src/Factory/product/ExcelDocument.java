package Factory.Product;

public class ExcelDocument extends Document{
    @Override
    public void open() {
        System.out.println("A excel document opened");
    }
}
