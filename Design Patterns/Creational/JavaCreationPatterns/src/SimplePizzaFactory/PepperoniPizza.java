package SimplePizzaFactory;

public class PepperoniPizza implements  Pizza{
    @Override
    public void prepare() {
        System.out.println("Preparing Pepperoni pizza");
    }

    @Override
    public void bake() {
        System.out.println("Baking Pepperoni pizza");
    }

    @Override
    public void cut() {
        System.out.println("Sliding down Pepperoni pizza");
    }

    @Override
    public void box() {
        System.out.println("Packing Pepperoni pizza");
    }
}
