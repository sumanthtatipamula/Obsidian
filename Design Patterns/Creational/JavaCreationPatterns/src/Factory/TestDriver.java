package Factory;

public class TestDriver {
    public static void  main(String args[]){
        ApplicationFactory factory = new ApplicationFactory();
        factory.openApplication("EXCEL").newDocument();
    }
}
