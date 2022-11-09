package PizzaFactory.Chicago;

import PizzaFactory.Pizza;

public class ChicagoStylePepperoniPizza implements Pizza {
    @Override
    public void prepare() {
        System.out.println("Preparing  Chicago Pepperoni pizza");
    }

    @Override
    public void bake() {
        System.out.println("Baking  Chicago Pepperoni pizza");
    }

    @Override
    public void cut() {
        System.out.println("Sliding down  Chicago Pepperoni pizza");
    }

    @Override
    public void box() {
        System.out.println("Packing  Chicago Pepperoni pizza");
    }
}
