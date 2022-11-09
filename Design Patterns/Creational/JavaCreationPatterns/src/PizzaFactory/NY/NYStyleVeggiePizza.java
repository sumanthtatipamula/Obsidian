package PizzaFactory.NY;

import PizzaFactory.Pizza;

public class NYStyleVeggiePizza implements Pizza {
    @Override
    public void prepare() {
        System.out.println("Preparing Veggie pizza");
    }

    @Override
    public void bake() {
        System.out.println("Baking Veggie pizza");
    }

    @Override
    public void cut() {
        System.out.println("Sliding down Veggie pizza");
    }

    @Override
    public void box() {
        System.out.println("Packing Veggie pizza");
    }
}
