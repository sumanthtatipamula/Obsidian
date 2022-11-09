package PizzaFactory.stores;

import PizzaFactory.NY.NYStyleCheesePizza;
import PizzaFactory.NY.NYStylePepperoniPizza;
import PizzaFactory.NY.NYStyleVeggiePizza;
import PizzaFactory.NY.NyStyleClamPizza;
import PizzaFactory.Pizza;

public class NYPizzaStore extends PizzaStore{
    @Override
    Pizza createPizza(String type) {
        Pizza pizza = null;
        if(type.equals("cheese")){
            pizza =  new NYStyleCheesePizza();
        }
        else if(type.equals("pepperoni")){
            pizza = new NYStylePepperoniPizza();
        }
        else if(type.equals("clam")){
            pizza = new NyStyleClamPizza();
        }
        else if(type.equals("veggie")){
            pizza = new NYStyleVeggiePizza();
        }
        return pizza;
    }
}
