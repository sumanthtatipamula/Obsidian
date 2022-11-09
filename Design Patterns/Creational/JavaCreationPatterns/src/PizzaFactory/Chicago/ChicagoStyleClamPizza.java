package PizzaFactory.Chicago;

import PizzaFactory.Pizza;

public class ChicagoStyleClamPizza implements Pizza {
    @Override
    public void prepare() {
        System.out.println("Preparing Chicago Clam pizza");
    }

    @Override
    public void bake() {
        System.out.println("Baking Chicago Clam pizza");
    }

    @Override
    public void cut() {
        System.out.println("Sliding down Chicago Clam pizza");
    }

    @Override
    public void box() {
        System.out.println("Packing Chicago Clam pizza");
    }
}
