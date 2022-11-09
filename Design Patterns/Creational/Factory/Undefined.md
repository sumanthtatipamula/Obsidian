### Code Snippets
``` java
public class PizzaStore {  
    SimplePizzaFactory factory;  
    PizzaStore(SimplePizzaFactory factory){  
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
```

``` java

public class SimplePizzaFactory {  
    public Pizza createPizza(String type) {  
        Pizza pizza = null;  
        if(type.equals("cheese")){  
            pizza =  new CheesePizza();  
        }  
        else if(type.equals("pepperoni")){  
            pizza =  new PepperoniPizza();  
        }  
        else if(type.equals("clam")){  
            pizza = new ClamPizza();  
        }  
        else if(type.equals("veggie")){  
            pizza = new VeggiePizza();  
        }  
        return pizza;  
    }  
}
```
 
``` java  
public interface Pizza {  
    void prepare();  
    void bake();  
    void cut();  
    void box();  
}
```
