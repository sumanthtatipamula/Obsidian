package PizzaFactory;

import PizzaFactory.Pizza;

public class ClamPizza implements Pizza {
    @Override
    public void prepare() {
        System.out.println("Preparing Clam pizza");
    }

    @Override
    public void bake() {
        System.out.println("Baking Clam pizza");
    }

    @Override
    public void cut() {
        System.out.println("Sliding down Clam pizza");
    }

    @Override
    public void box() {
        System.out.println("Packing Clam pizza");
    }
}
