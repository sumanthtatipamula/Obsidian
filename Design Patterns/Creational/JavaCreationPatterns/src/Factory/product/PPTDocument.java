package Factory.Product;

public class PPTDocument extends Document{
    @Override
    public void open() {
        System.out.println("A ppt document opened");
    }
}
