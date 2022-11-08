package Factory.Product;

public class WordDocument extends Document{
    @Override
    public void open() {
        System.out.println("A Word document opened");
    }
}
