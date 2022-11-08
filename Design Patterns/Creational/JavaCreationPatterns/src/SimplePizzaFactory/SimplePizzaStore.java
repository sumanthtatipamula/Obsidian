package SimplePizzaFactory;

import SimplePizzaFactory.factories.SimplePizzaFactory;

public class SimplePizzaStore {
    SimplePizzaFactory factory;
    SimplePizzaStore(SimplePizzaFactory factory){
        this.factory = factory;
    }
    public Pizza orderPizza(String type){
        Pizza pizza;
        pizza = factory.createPizza(type);
        pizza.prepare();
        pizza.bake();
        pizza.cut();
        pizza.box();
        return pizza;
    }
}
