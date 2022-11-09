package PizzaFactory.Chicago;

import PizzaFactory.Pizza;

public class ChicagoStyleCheesePizza implements Pizza {
    @Override
    public void prepare() {
        System.out.println("Preparing Chicago cheese pizza");
    }

    @Override
    public void bake() {
        System.out.println("Baking Chicago cheese pizza");
    }

    @Override
    public void cut() {
        System.out.println("Sliding down Chicago Cheese pizza");
    }

    @Override
    public void box() {
        System.out.println("Packing Chicago cheese pizza");
    }
}
