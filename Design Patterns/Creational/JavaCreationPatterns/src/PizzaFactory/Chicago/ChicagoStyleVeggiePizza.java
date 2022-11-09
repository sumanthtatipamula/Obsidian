package PizzaFactory.Chicago;

import PizzaFactory.Pizza;

public class ChicagoStyleVeggiePizza implements Pizza {
    @Override
    public void prepare() {
        System.out.println("Preparing Chicago Veggie pizza");
    }

    @Override
    public void bake() {
        System.out.println("Baking Chicago Veggie pizza");
    }

    @Override
    public void cut() {
        System.out.println("Sliding down Chicago Veggie pizza");
    }

    @Override
    public void box() {
        System.out.println("Packing Chicago Veggie pizza");
    }
}
