package PizzaFactory;

public class CheesePizza implements Pizza{
    @Override
    public void prepare() {
        System.out.println("Preparing cheese pizza");
    }

    @Override
    public void bake() {
        System.out.println("Baking cheese pizza");
    }

    @Override
    public void cut() {
        System.out.println("Sliding down Cheese pizza");
    }

    @Override
    public void box() {
        System.out.println("Packing cheese pizza");
    }
}
