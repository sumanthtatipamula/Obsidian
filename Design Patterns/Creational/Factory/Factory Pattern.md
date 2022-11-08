-----

# Chapter 4. Baking with OO Goodness: The Factory Pattern


# Identifying the aspects that vary

Let’s say you have a pizza shop, and as a cutting-edge pizza store owner in Objectville you might end up writing some code like this:

![Images](https://learning.oreilly.com/api/v2/epubs/urn:orm:book:9781492077992/files/assets/f0112-01.png)

![Images](https://learning.oreilly.com/api/v2/epubs/urn:orm:book:9781492077992/files/assets/f0112-02.png)

**But you need more than one type of pizza...**

So then you’d add some code that _determines_ the appropriate type of pizza and then goes about _making_ the pizza:

![Images](https://learning.oreilly.com/api/v2/epubs/urn:orm:book:9781492077992/files/assets/f0112-03.png)

# But the pressure is on to add more pizza types

You realize that all of your competitors have added a couple of trendy pizzas to their menus: the Clam Pizza and the Veggie Pizza. Obviously you need to keep up with the competition, so you’ll add these items to your menu. And you haven’t been selling many Greek pizzas lately, so you decide to take that off the menu:

![Images](https://learning.oreilly.com/api/v2/epubs/urn:orm:book:9781492077992/files/assets/f0113-01.png)

Clearly, dealing with _which_ concrete class is instantiated is really messing up our orderPizza() method and preventing it from being closed for modification. But now that we know what is varying and what isn’t, it’s probably time to encapsulate it.

# Encapsulating object creation

So now we know we’d be better off moving the object creation out of the orderPizza() method. But how? Well, what we’re going to do is take the creation code and move it out into another object that is only going to be concerned with creating pizzas.

![Images](https://learning.oreilly.com/api/v2/epubs/urn:orm:book:9781492077992/files/assets/f0114-01.png)

**We’ve got a name for this new object: we call it a Factory.**

Factories handle the details of object creation. Once we have a SimplePizzaFactory, our orderPizza() method becomes a client of that object. Anytime it needs a pizza, it asks the pizza factory to make one. Gone are the days when the orderPizza() method needs to know about Greek versus Clam pizzas. Now the orderPizza() method just cares that it gets a pizza that implements the Pizza interface so that it can call prepare(), bake(), cut(), and box().

We’ve still got a few details to fill in here; for instance, what does the orderPizza() method replace its creation code with? Let’s implement a simple factory for the pizza store and find out...

# Building a simple pizza factory

We’ll start with the factory itself. What we’re going to do is define a class that encapsulates the object creation for all pizzas. Here it is...

![Images](https://learning.oreilly.com/api/v2/epubs/urn:orm:book:9781492077992/files/assets/f0115-01.png)

# There are no Dumb Questions

**Q: What’s the advantage of this? It looks like we’re just pushing the problem off to another object.**

**A:** One thing to remember is that the SimplePizzaFactory may have many clients. We’ve only seen the orderPizza() method; however, there may be a PizzaShopMenu class that uses the factory to get pizzas for their current description and price. We might also have a HomeDelivery class that handles pizzas in a different way than our PizzaShop class but is also a client of the factory.

So, by encapsulating the pizza creating in one class, we now have only one place to make modifications when the implementation changes.

And, don’t forget, we’re also just about to remove the concrete instantiations from our client code.

**Q: I’ve seen a similar design where a factory like this is defined as a static method. What’s the difference?**

**A:** Defining a simple factory as a static method is a common technique and is often called a static factory. Why use a static method? Because you don’t need to instantiate an object to make use of the create method. But it also has the disadvantage that you can’t subclass and change the behavior of the create method.

# Reworking the Pizza Store class

Now it’s time to fix up our client code. What we want to do is rely on the factory to create the pizzas for us. Here are the changes:

![Images](https://learning.oreilly.com/api/v2/epubs/urn:orm:book:9781492077992/files/assets/f0116-01.png)

##### ![Images](https://learning.oreilly.com/api/v2/epubs/urn:orm:book:9781492077992/files/assets/brain.png) BRAIN POWER

We know that object composition allows us to change behavior dynamically at runtime (among other things) because we can swap in and out implementations. How might we be able to use that in the PizzaStore? What factory implementations might we be able to swap in and out?

# The Simple Factory defined

<mark style="background: #FF5582A6;">The Simple Factory isn’t actually a Design Pattern; it’s more of a programming idiom. </mark>But it is commonly used, so we’ll give it a Head First Pattern Honorable Mention. Some developers do mistake this idiom for the Factory Pattern, but the next time that happens you can subtly show you know your stuff; just don’t strut as you educate them on the distinction.

![Images](https://learning.oreilly.com/api/v2/epubs/urn:orm:book:9781492077992/files/assets/f0117-01.png)

Just because Simple Factory isn’t a REAL pattern doesn’t mean we shouldn’t check out how it’s put together. Let’s take a look at the class diagram of our new Pizza Store:

![Images](https://learning.oreilly.com/api/v2/epubs/urn:orm:book:9781492077992/files/assets/f0117-02.png)

Think of Simple Factory as a warm-up. Next, we’ll explore two heavy-duty patterns that are both factories. But don’t worry, there’s more pizza to come!

### Codes
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


###### NOTE

*Just another reminder: in design patterns, the phrase “implement an interface” does NOT always mean “write a class that implements a Java interface, by using the ‘implements’ keyword in the class declaration.” In the general use of the phrase, a concrete class implementing a method from a supertype (which could be a abstract class OR interface) is still considered to be “implementing the interface” of that supertype.

# Franchising the pizza store

Your Objectville Pizza Store has done so well that you’ve trounced the competition and now everyone wants a Pizza Store in their own neighborhood. As the franchiser, you want to ensure the quality of the franchise operations and so you want them to use your time-tested code.

But what about regional differences? Each franchise might want to offer different styles of pizzas (New York, Chicago, and California, to name a few), depending on where the franchise store is located and the tastes of the local pizza connoisseurs.

###### NOTE

Yes, different areas of the US serve very different styles of pizza—from the deep-dish pizzas of Chicago, to the thin crust of New York, to the cracker-like pizza of California (some would say topped with fruits and nuts).

![Images](https://learning.oreilly.com/api/v2/epubs/urn:orm:book:9781492077992/files/assets/f0118-01.png)

## We’ve seen one approach...

If we take out SimplePizzaFactory and create three different factories—NYPizzaFactory, ChicagoPizzaFactory, and CaliforniaPizzaFactory—then we can just compose the PizzaStore with the appropriate factory and a franchise is good to go. That’s one approach.

Let’s see what that would look like...

![Images](https://learning.oreilly.com/api/v2/epubs/urn:orm:book:9781492077992/files/assets/f0119-01.png)

## But you’d like a little more quality control...

So you test-marketed the SimpleFactory idea, and what you found was that the franchises were using your factory to create pizzas, but starting to employ their own home-grown procedures for the rest of the process: they’d bake things a little differently, they’d forget to cut the pizza, and they’d use third-party boxes.

Rethinking the problem a bit, you see that what you’d really like to do is create a framework that ties the store and the pizza creation together, yet still allows things to remain flexible.

In our early code, before the SimplePizzaFactory, we had the pizza-making code tied to the PizzaStore, but it wasn’t flexible. So, how can we have our pizza and eat it too?

![Images](https://learning.oreilly.com/api/v2/epubs/urn:orm:book:9781492077992/files/assets/f0119-02.png)

# A framework for the pizza store

There _is_ a way to localize all the pizza-making activities to the PizzaStore class, _and_ to give the franchises freedom to have their own regional style.

What we’re going to do is put the createPizza() method back into PizzaStore, but this time as an abstract method, and then create a PizzaStore subclass for each regional style.

First, let’s look at the changes to the PizzaStore:

![Images](https://learning.oreilly.com/api/v2/epubs/urn:orm:book:9781492077992/files/assets/f0120-01.png)

Now we’ve got a store waiting for subclasses; we’re going to have a subclass for each regional type (NYPizzaStore, ChicagoPizzaStore, CaliforniaPizzaStore) and each subclass is going to make the decision about what makes up a pizza. Let’s take a look at how this is going to work.

# Allowing the subclasses to decide

Remember, the Pizza Store already has a well-honed order system in the orderPizza() method and you want to ensure that it’s consistent across all franchises.

What varies among the regional Pizza Stores is the style of pizzas they make—New York pizza has thin crust, Chicago pizza has thick, and so on—and we are going to push all these variations into the createPizza() method and make it responsible for creating the right kind of pizza. The way we do this is by letting each subclass of Pizza Store define what the createPizza() method looks like. So, we’ll have a number of concrete subclasses of Pizza Store, each with its own pizza variations, all fitting within the Pizza Store framework and still making use of the well-tuned orderPizza() method.

![Images](https://learning.oreilly.com/api/v2/epubs/urn:orm:book:9781492077992/files/assets/f0121-01.png)

![Images](https://learning.oreilly.com/api/v2/epubs/urn:orm:book:9781492077992/files/assets/f0122-01.png)

Well, think about it from the point of view of the PizzaStore’s orderPizza() method: it is defined in the abstract PizzaStore, but concrete types are only created in the subclasses.

![Images](https://learning.oreilly.com/api/v2/epubs/urn:orm:book:9781492077992/files/assets/f0122-02.png)

Now, to take this a little further, the orderPizza() method does a lot of things with a Pizza object (like prepare, bake, cut, box), but because Pizza is abstract, orderPizza() has no idea what real concrete classes are involved. In other words, it’s decoupled!

![Images](https://learning.oreilly.com/api/v2/epubs/urn:orm:book:9781492077992/files/assets/f0122-03.png)

When orderPizza() calls createPizza(), one of your subclasses will be called into action to create a pizza. Which kind of pizza will be made? Well, that’s decided by the choice of pizza store you order from, NYStylePizzaStore or ChicagoStylePizzaStore.

![Images](https://learning.oreilly.com/api/v2/epubs/urn:orm:book:9781492077992/files/assets/f0122-04.png)

So, is there a real-time decision that subclasses make? No, but from the perspective of orderPizza(), if you chose a NYStylePizzaStore, that subclass gets to determine which pizza is made. So the subclasses aren’t really “deciding”—it was _you_ who decided by choosing which store you wanted—but they do determine which kind of pizza gets made.

# Let’s make a Pizza Store

Being a franchise has its benefits. You get all the PizzaStore functionality for free. All the regional stores need to do is subclass PizzaStore and supply a createPizza() method that implements their style of pizza. We’ll take care of the big three pizza styles for the franchisees.

Here’s the New York regional style:

![Images](https://learning.oreilly.com/api/v2/epubs/urn:orm:book:9781492077992/files/assets/f0123-01.png)

###### NOTE

* Note that the orderPizza() method in the superclass has no clue which Pizza we are creating; it just knows it can prepare, bake, cut, and box it!

Once we’ve got our PizzaStore subclasses built, it will be time to see about ordering up a pizza or two. But before we do that, why don’t you take a crack at building the Chicago-style and California-style pizza stores on the next page?

##### ![Inline](https://learning.oreilly.com/api/v2/epubs/urn:orm:book:9781492077992/files/assets/pencil.png) SHARPEN YOUR PENCIL

We’ve knocked out the NYPizzaStore; just two more to go and we’ll be ready to franchise! Write the Chicago-style and California-style PizzaStore implementations here:

# Declaring a factory method

With just a couple of transformations to the PizzaStore class, we’ve gone from having an object handle the instantiation of our concrete classes to a set of subclasses that are now taking on that responsibility. Let’s take a closer look:

![Images](https://learning.oreilly.com/api/v2/epubs/urn:orm:book:9781492077992/files/assets/f0125-01.png)

##### ![Inline](https://learning.oreilly.com/api/v2/epubs/urn:orm:book:9781492077992/files/assets/sarch.png) CODE UP CLOSE

A factory method handles object creation and encapsulates it in a subclass. This decouples the client code in the superclass from the object creation code in the subclass.

![Images](https://learning.oreilly.com/api/v2/epubs/urn:orm:book:9781492077992/files/assets/f0125-02.png)

## Let’s see how it works: ordering pizzas with the pizza factory method

![Images](https://learning.oreilly.com/api/v2/epubs/urn:orm:book:9781492077992/files/assets/f0126-01.png)

## So how do they order?

1.  First, Joel and Ethan need an instance of a PizzaStore. Joel needs to instantiate a ChicagoPizzaStore and Ethan needs a NYPizzaStore.
    
2.  With a PizzaStore in hand, both Ethan and Joel call the orderPizza() method and pass in the type of pizza they want (cheese, veggie, and so on).
    
3.  To create the pizzas, the createPizza() method is called, which is defined in the two subclasses NYPizzaStore and ChicagoPizzaStore. As we defined them, the NYPizzaStore instantiates a NY-style pizza, and the ChicagoPizzaStore instantiates a Chicago-style pizza. In either case, the Pizza is returned to the orderPizza() method.
    
4.  The orderPizza() method has no idea what kind of pizza was created, but it knows it is a pizza and it prepares, bakes, cuts, and boxes it for Ethan and Joel.
    

## Let’s check out how these pizzas are really made to order...

![Images](https://learning.oreilly.com/api/v2/epubs/urn:orm:book:9781492077992/files/assets/scens.png)

![Images](https://learning.oreilly.com/api/v2/epubs/urn:orm:book:9781492077992/files/assets/f0127-01.png)

# We’re just missing one thing: Pizzas!

## Our Pizza Store isn’t going to be very popular without some pizzas, so let’s implement them

![Images](https://learning.oreilly.com/api/v2/epubs/urn:orm:book:9781492077992/files/assets/f0128-01.png)

![Images](https://learning.oreilly.com/api/v2/epubs/urn:orm:book:9781492077992/files/assets/f0128-02.png)

###### NOTE

REMEMBER: we don’t provide import and package statements in the code listings. Get the complete source code from the wickedlysmart website at [https://wickedlysmart.com/head-first-design-patterns](https://wickedlysmart.com/head-first-design-patterns)

If you lose this URL, you can always quickly find it in the Intro section.

## Now we just need some concrete subclasses...how about defining New York and Chicago-style cheese pizzas?

![Images](https://learning.oreilly.com/api/v2/epubs/urn:orm:book:9781492077992/files/assets/f0129-01.png)

# You’ve waited long enough. Time for some pizzas!

![Images](https://learning.oreilly.com/api/v2/epubs/urn:orm:book:9781492077992/files/assets/f0130-01.png)

![Images](https://learning.oreilly.com/api/v2/epubs/urn:orm:book:9781492077992/files/assets/f0130-02.png)

# It’s finally time to meet the Factory Method Pattern

All factory patterns encapsulate object creation. The Factory Method Pattern encapsulates object creation by letting subclasses decide what objects to create. Let’s check out these class diagrams to see who the players are in this pattern:

## The Creator classes

![Images](https://learning.oreilly.com/api/v2/epubs/urn:orm:book:9781492077992/files/assets/f0131-01.png)

## The Product classes

![Images](https://learning.oreilly.com/api/v2/epubs/urn:orm:book:9781492077992/files/assets/f0131-02.png)

# View Creators and Products in Parallel

For every concrete Creator, there’s typically a whole set of products that it creates. Chicago pizza creators create different types of Chicago-style pizza, New York pizza creators create different types of New York—style pizza, and so on. In fact, we can view our sets of Creator classes and their corresponding Product classes as _parallel hierarchies_.

Let’s look at the two parallel class hierarchies and see how they relate:

![Images](https://learning.oreilly.com/api/v2/epubs/urn:orm:book:9781492077992/files/assets/f0132-01.png)

###### NOTE

The factory method is the key to encapsulating this knowledge.

# ![Inline](https://learning.oreilly.com/api/v2/epubs/urn:orm:book:9781492077992/files/assets/design.png) Design Puzzle

We need another kind of pizza for those crazy Californians (crazy in a _good_ way, of course). Draw another parallel set of classes that you’d need to add a new California region to our PizzaStore.

![Images](https://learning.oreilly.com/api/v2/epubs/urn:orm:book:9781492077992/files/assets/f0133-01.png)

Okay, now write the five _most bizarre_ things you can think of to put on a pizza. Then, you’ll be ready to go into business making pizza in California!

_____________________________________________________________

_____________________________________________________________

_____________________________________________________________

_____________________________________________________________

_____________________________________________________________

# Factory Method Pattern defined

It’s time to roll out the official definition of the Factory Method Pattern:

###### NOTE

**The Factory Method Pattern** defines an interface for creating an object, but lets subclasses decide which class to instantiate. Factory Method lets a class defer instantiation to subclasses.

As with every factory, the Factory Method Pattern gives us a way to encapsulate the instantiations of concrete types. Looking at the class diagram below, you can see that the abstract Creator class gives you an interface with a method for creating objects, also known as the “factory method.” Any other methods implemented in the abstract Creator are written to operate on products produced by the factory method. Only subclasses actually implement the factory method and create products.

As in the official definition, you’ll often hear developers say, “the Factory Method pattern lets subclasses decide which class to instantiate.” Because the Creator class is written without knowledge of the actual products that will be created, we say “decide” not because the pattern allows subclasses _themselves_ to decide, but rather, because the decision actually comes down to _which subclass_ is used to create the product.

###### NOTE

You could ask them what “decides” means, but we bet you now understand this better than they do!

![Images](https://learning.oreilly.com/api/v2/epubs/urn:orm:book:9781492077992/files/assets/f0134-01.png)

# there are no Dumb Questions

**Q: What’s the advantage of the Factory Method Pattern when you only have one ConcreteCreator?**

**A:** The Factory Method Pattern is useful if you’ve only got one concrete creator because you are decoupling the implementation of the product from its use. If you add additional products or change a product’s implementation, it will not affect your Creator (because the Creator is not tightly coupled to any ConcreteProduct).

**Q: Would it be correct to say that our NY and Chicago stores are implemented using Simple Factory? They look just like it.**

**A:** They’re similar, but used in different ways. Even though the implementation of each concrete store looks a lot like the SimplePizzaFactory, remember that the concrete stores are extending a class that has defined createPizza() as an abstract method. It is up to each store to define the behavior of the createPizza() method. In Simple Factory, the factory is another object that is composed with the PizzaStore.

**Q: Are the factory method and the Creator class always abstract?**

**A:** No, you can define a default factory method to produce some concrete product. Then you always have a means of creating products even if there are no subclasses of the Creator class.

**Q: Each store can make four different kinds of pizzas based on the type passed in. Do all concrete creators make multiple products, or do they sometimes just make one?**

**A:** We implemented what is known as the parameterized factory method. It can make more than one object based on a parameter passed in, as you noticed. Often, however, a factory just produces one object and is not parameterized. Both are valid forms of the pattern.

**Q: Your parameterized types don’t seem “type-safe.” I’m just passing in a String! What if I asked for a “CalmPizza”?**

**A:** You are certainly correct, and that would cause what we call in the business a “runtime error.” There are several other more sophisticated techniques that can be used to make parameters more “type safe”—in other words, to ensure errors in parameters can be caught at compile time. For instance, you can create objects that represent the parameter types, use static constants, or use enums.

**Q: I’m still a bit confused about the difference between Simple Factory and Factory Method. They look very similar, except that in Factory Method, the class that returns the pizza is a subclass. Can you explain?**

**A:** You’re right that the subclasses do look a lot like Simple Factory; however, think of Simple Factory as a one-shot deal, while with Factory Method you are creating a framework that lets the subclasses decide which implementation will be used. For example, the orderPizza() method in the Factory Method Pattern provides a general framework for creating pizzas that relies on a factory method to actually create the concrete classes that go into making a pizza. By subclassing the PizzaStore class, you decide what concrete products go into making the pizza that orderPizza() returns. Compare that with Simple Factory, which gives you a way to encapsulate object creation, but doesn’t give you the flexibility of Factory Method because there is no way to vary the products you’re creating.

![Images](https://learning.oreilly.com/api/v2/epubs/urn:orm:book:9781492077992/files/assets/woman.png)

**_Guru and Student..._**

**_Guru:_** _Tell me about your training._

**_Student:_** _Guru, I have taken my study of “encapsulate what varies” further._

**_Guru:_** _Go on..._

**_Student:_** _I have learned that one can encapsulate the code that creates objects. When you have code that instantiates concrete classes, this is an area of frequent change. I’ve learned a technique called “factories” that allows you to encapsulate this behavior of instantiation._

**_Guru:_** _And these “factories,” of what benefit are they?_

**_Student:_** _There are many. By placing all my creation code in one object or method, I avoid duplication in my code and provide one place to perform maintenance. That also means clients depend only upon interfaces rather than the concrete classes required to instantiate objects. As I have learned in my studies, this allows me to program to an interface, not an implementation, and that makes my code more flexible and extensible in the future._

**_Guru:_** _Yes, your OO instincts are growing. Do you have any questions for your guru today?_

**_Student:_** _Guru, I know that by encapsulating object creation I am coding to abstractions and decoupling my client code from actual implementations. But my factory code must still use concrete classes to instantiate real objects. Am I not pulling the wool over my own eyes?_

**_Guru:_** _Object creation is a reality of life; we must create objects or we will never create a single Java application. But, with knowledge of this reality, we can design our code so that we have corralled this creation code like the sheep whose wool you would pull over your eyes. Once corralled, we can protect and care for the creation code. If we let our creation code run wild, then we will never collect its “wool.”_

**_Student:_** _Guru, I see the truth in this._

**_Guru:_** _As I knew you would. Now, please go and meditate on object dependencies._

##### ![Inline](https://learning.oreilly.com/api/v2/epubs/urn:orm:book:9781492077992/files/assets/pencil.png) SHARPEN YOUR PENCIL

Let’s pretend you’ve never heard of an OO factory. Here’s a “very dependent” version of PizzaStore that doesn’t use a factory. We need you to make a count of the number of concrete pizza classes this class is dependent on. If you added California-style pizzas to this PizzaStore, how many classes would it be dependent on then?

![Images](https://learning.oreilly.com/api/v2/epubs/urn:orm:book:9781492077992/files/assets/f0137-01.png)

# Looking at object dependencies

When you directly instantiate an object, you are depending on its concrete class. Take a look at our very dependent PizzaStore one page back. It creates all the pizza objects right in the PizzaStore class instead of delegating to a factory.

If we draw a diagram representing that version of the PizzaStore and all the objects it depends on, here’s what it looks like:

![Images](https://learning.oreilly.com/api/v2/epubs/urn:orm:book:9781492077992/files/assets/f0138-01.png)

# The Dependency Inversion Principle

It should be pretty clear that reducing dependencies to concrete classes in our code is a “good thing.” In fact, we’ve got an OO design principle that formalizes this notion; it even has a big, formal name: _Dependency Inversion Principle_.

###### NOTE

Yet another phrase you can use to impress the execs in the room! Your raise will more than offset the cost of this book, and you’ll gain the admiration of your fellow developers.

Here’s the general principle:

![Images](https://learning.oreilly.com/api/v2/epubs/urn:orm:book:9781492077992/files/assets/f0139-01.png)

At first, this principle sounds a lot like “Program to an interface, not an implementation,” right? It is similar; however, the Dependency Inversion Principle makes an even stronger statement about abstraction. It suggests that our high-level components should not depend on our low-level components; rather, they should _both_ depend on abstractions.

###### NOTE

A “high-level” component is a class with behavior defined in terms of other, “low-level” components.

For example, PizzaStore is a high-level component because its behavior is defined in terms of pizzas — it creates all the different pizza objects, and prepares, bakes, cuts, and boxes them, while the pizzas it uses are low-level components.

But what the heck does that mean?

Well, let’s start by looking again at the pizza store diagram on the previous page. PizzaStore is our “high-level component” and the pizza implementations are our “low-level components,” and clearly PizzaStore is dependent on the concrete pizza classes.

Now, this principle tells us we should instead write our code so that we are depending on abstractions, not concrete classes. That goes for both our high-level modules and our low-level modules.

But how do we do this? Let’s think about how we’d apply this principle to our very dependent PizzaStore implementation...

# Applying the Principle

Now, the main problem with the very dependent PizzaStore is that it depends on every type of pizza because it actually instantiates concrete types in its orderPizza() method.

While we’ve created an abstraction, Pizza, we’re nevertheless creating concrete Pizzas in this code, so we don’t get a lot of leverage out of this abstraction.

How can we get those instantiations out of the orderPizza() method? Well, as we know, the Factory Method Pattern allows us to do just that.

So, after we’ve applied the Factory Method Pattern, our diagram looks like this:

![Images](https://learning.oreilly.com/api/v2/epubs/urn:orm:book:9781492077992/files/assets/f0140-01.png)

After applying Factory Method, you’ll notice that our high-level component, the PizzaStore, and our low-level components, the pizzas, both depend on Pizza, the abstraction. Factory Method is not the only technique for adhering to the Dependency Inversion Principle, but it is one of the more powerful ones.

**Where’s the “inversion” in Dependency Inversion Principle?**

![Images](https://learning.oreilly.com/api/v2/epubs/urn:orm:book:9781492077992/files/assets/f0141-01.png)

The “inversion” in the name Dependency Inversion Principle is there because it inverts the way you typically might think about your OO design. Look at the diagram on the previous page. Notice that the low-level components now depend on a higher-level abstraction. Likewise, the high-level component is also tied to the same abstraction. So, the top-to-bottom dependency chart we drew a couple of pages back has inverted itself, with both high-level and low-level modules now depending on the abstraction.

Let’s also walk through the thinking behind the typical design process and see how introducing the principle can invert the way we think about the design...

# Inverting your thinking...

Okay, so you need to implement a Pizza Store. What’s the first thought that pops into your head?

![Images](https://learning.oreilly.com/api/v2/epubs/urn:orm:book:9781492077992/files/assets/f0142-01.png)

Right, you start at the top and follow things down to the concrete classes. But, as you’ve seen, you don’t want your pizza store to know about the concrete pizza types, because then it’ll be dependent on all those concrete classes!

Now, let’s “invert” your thinking...instead of starting at the top, start at the Pizzas and think about what you can abstract.

![Images](https://learning.oreilly.com/api/v2/epubs/urn:orm:book:9781492077992/files/assets/f0142-02.png)

Right! You are thinking about the abstraction _Pizza._ So now, go back and think about the design of the Pizza Store again.

![Images](https://learning.oreilly.com/api/v2/epubs/urn:orm:book:9781492077992/files/assets/f0142-03.png)

Close. But to do that you’ll have to rely on a factory to get those concrete classes out of your Pizza Store. Once you’ve done that, your different concrete pizza types depend only on an abstraction, and so does your store. We’ve taken a design where the store depended on concrete classes and inverted those dependencies (along with your thinking).

# A few guidelines to help you follow the Principle...

The following guidelines can help you avoid OO designs that violate the Dependency Inversion Principle:

-   No variable should hold a reference to a concrete class.
    
    ###### NOTE
    
    If you use **new**, you’ll be holding a reference to a concrete class. Use a factory to get around that!
    
-   No class should derive from a concrete class.
    
    ###### NOTE
    
    If you derive from a concrete class, you’re depending on a concrete class. Derive from an abstraction, like an interface or an abstract class.
    
-   No method should override an implemented method of any of its base classes.
    
    ###### NOTE
    
    But wait, aren’t these guidelines impossible to follow? If I follow these, I’ll never be able to write a single program!
    

You’re exactly right! Like many of our principles, this is a guideline you should strive for, rather than a rule you should follow all the time. Clearly, every single Java program ever written violates these guidelines!

But, if you internalize these guidelines and have them in the back of your mind when you design, you’ll know when you are violating the principle and you’ll have a good reason for doing so. For instance, if you have a class that isn’t likely to change, and you know it, then it’s not the end of the world if you instantiate a concrete class in your code. Think about it; we instantiate String objects all the time without thinking twice. Does that violate the principle? Yes. Is that okay? Yes. Why? Because String is very unlikely to change.

If, on the other hand, a class you write is likely to change, you have some good techniques like Factory Method to encapsulate that change.

![Images](https://learning.oreilly.com/api/v2/epubs/urn:orm:book:9781492077992/files/assets/f0143-02.png)

# Meanwhile, back at the Pizza Store...

The design for the Pizza Store is really shaping up: it’s got a flexible framework and it does a good job of adhering to design principles.

![Images](https://learning.oreilly.com/api/v2/epubs/urn:orm:book:9781492077992/files/assets/f0144-01.png)

Now, the key to Objectville Pizza’s success has always been fresh, quality ingredients, and what you’ve discovered is that with the new framework your franchises have been following your _procedures_, but a few franchises have been substituting inferior ingredients in their pizzas to lower costs and increase their margins. You know you’ve got to do something, because in the long term this is going to hurt the Objectville brand!

###### NOTE

That is, the baking, the cutting, the boxing, and so on...

## Ensuring consistency in your ingredients

So how are you going to ensure each franchise is using quality ingredients? You’re going to build a factory that produces them and ships them to your franchises!

Now there’s only one problem with this plan: the franchises are located in different regions and what is red sauce in New York is not red sauce in Chicago. So, you have one set of ingredients that needs to be shipped to New York and a _different_ set that needs to be shipped to Chicago. Let’s take a closer look:

![Images](https://learning.oreilly.com/api/v2/epubs/urn:orm:book:9781492077992/files/assets/f0144-02.png)

# Families of ingredients...

New York uses one set of ingredients and Chicago another. Given the popularity of Objectville Pizza, it won’t be long before you also need to ship another set of regional ingredients to California, and what’s next? Austin?

For this to work, you’re going to have to figure out how to handle families of ingredients.

![Images](https://learning.oreilly.com/api/v2/epubs/urn:orm:book:9781492077992/files/assets/f0145-01.png)

###### NOTE

In total, these three regions make up ingredient families, with each region implementing a complete family of ingredients.

# Building the ingredient factories

Now we’re going to build a factory to create our ingredients; the factory will be responsible for creating each ingredient in the ingredient family. In other words, the factory will need to create dough, sauce, cheese, and so on... You’ll see how we are going to handle the regional differences shortly.

Let’s start by defining an interface for the factory that is going to create all our ingredients:

![Images](https://learning.oreilly.com/api/v2/epubs/urn:orm:book:9781492077992/files/assets/f0146-01.png)

**With that interface, here’s what we’re going to do:**

1.  Build a factory for each region. To do this, you’ll create a subclass of PizzaIngredientFactory that implements each create method.
    
2.  Implement a set of ingredient classes to be used with the factory, like ReggianoCheese, RedPeppers, and ThickCrustDough. These classes can be shared among regions where appropriate.
    
3.  Then we still need to hook all this up by working our new ingredient factories into our old PizzaStore code.
    

# Building the New York ingredient factory

Okay, here’s the implementation for the New York ingredient factory. This factory specializes in Marinara Sauce, Reggiano Cheese, Fresh Clams, etc.

![Images](https://learning.oreilly.com/api/v2/epubs/urn:orm:book:9781492077992/files/assets/f0147-01.png)

##### ![Inline](https://learning.oreilly.com/api/v2/epubs/urn:orm:book:9781492077992/files/assets/pencil.png) SHARPEN YOUR PENCIL

Write the ChicagoPizzaIngredientFactory. You can reference the classes below in your implementation:

![Images](https://learning.oreilly.com/api/v2/epubs/urn:orm:book:9781492077992/files/assets/f0148-01.png)

# Reworking the pizzas...

We’ve got our factories all fired up and ready to produce quality ingredients; now we just need to rework our Pizzas so they only use factory-produced ingredients. We’ll start with our abstract Pizza class:

![Images](https://learning.oreilly.com/api/v2/epubs/urn:orm:book:9781492077992/files/assets/f0149-01.png)

# Reworking the pizzas, continued...

Now that you’ve got an abstract Pizza class to work from, it’s time to create the New York— and Chicago-style Pizzas—only this time around, they’ll get their ingredients straight from the factory. The franchisees’ days of skimping on ingredients are over!

When we wrote the Factory Method code, we had a NYCheesePizza and a ChicagoCheesePizza class. If you look at the two classes, the only thing that differs is the use of regional ingredients. The pizzas are made just the same (dough + sauce + cheese). The same goes for the other pizzas: Veggie, Clam, and so on. They all follow the same preparation steps; they just have different ingredients.

So, what you’ll see is that we really don’t need two classes for each pizza; the ingredient factory is going to handle the regional differences for us.

Here’s the CheesePizza:

![Images](https://learning.oreilly.com/api/v2/epubs/urn:orm:book:9781492077992/files/assets/f0150-01.png)

##### ![Inline](https://learning.oreilly.com/api/v2/epubs/urn:orm:book:9781492077992/files/assets/sarch.png) CODE UP CLOSE

The Pizza code uses the factory it has been composed with to produce the ingredients used in the pizza. The ingredients produced depend on which factory we’re using. The Pizza class doesn’t care; it knows how to make pizzas. Now, it’s decoupled from the differences in regional ingredients and can be easily reused when there are factories for the Austin, the Nashville, and beyond.

![Images](https://learning.oreilly.com/api/v2/epubs/urn:orm:book:9781492077992/files/assets/f0151-01.png)

Let’s check out the ClamPizza as well:

![Images](https://learning.oreilly.com/api/v2/epubs/urn:orm:book:9781492077992/files/assets/f0151-02.png)

# Revisiting our pizza stores

We’re almost there; we just need to make a quick trip to our franchise stores to make sure they are using the correct Pizzas. We also need to give them a reference to their local ingredient factories:

![Images](https://learning.oreilly.com/api/v2/epubs/urn:orm:book:9781492077992/files/assets/f0152-01.png)

##### ![Images](https://learning.oreilly.com/api/v2/epubs/urn:orm:book:9781492077992/files/assets/brain.png) BRAIN POWER

Compare this version of the createPizza() method to the one in the Factory Method implementation earlier in the chapter.

# What have we done?

That was quite a series of code changes; what exactly did we do?

We provided a means of creating a family of ingredients for pizzas by introducing a new type of factory called an _Abstract Factory_.

An Abstract Factory gives us an interface for creating a family of products. By writing code that uses this interface, we decouple our code from the actual factory that creates the products. That allows us to implement a variety of factories that produce products meant for different contexts—such as different regions, different operating systems, or different look and feels.

Because our code is decoupled from the actual products, we can substitute different factories to get different behaviors (like getting marinara instead of plum tomatoes).

An Abstract Factory provides an interface for a family of products. What’s a family? In our case, it’s all the things we need to make a pizza: dough, sauce, cheese, meats, and veggies.

From the abstract factory, we derive one or more concrete factories that produce the same products, but with different implementations.

We then write our code so that it uses the factory to create products. By passing in a variety of factories, we get a variety of implementations of those products. But our client code stays the same.

![Images](https://learning.oreilly.com/api/v2/epubs/urn:orm:book:9781492077992/files/assets/f0153-01.png)

# More pizza for Ethan and Joel...

![Images](https://learning.oreilly.com/api/v2/epubs/urn:orm:book:9781492077992/files/assets/scens.png)

## Ethan and Joel can’t get enough Objectville Pizza! What they don’t know is that now their orders are making use of the new ingredient factories. So now when they order...

![Images](https://learning.oreilly.com/api/v2/epubs/urn:orm:book:9781492077992/files/assets/f0154-01.png)

The first part of the order process hasn’t changed at all. Let’s follow Ethan’s order again:

![Images](https://learning.oreilly.com/api/v2/epubs/urn:orm:book:9781492077992/files/assets/f0154-02.png)

## From here things change, because we are using an ingredient factory

![Images](https://learning.oreilly.com/api/v2/epubs/urn:orm:book:9781492077992/files/assets/scens.png)

![Images](https://learning.oreilly.com/api/v2/epubs/urn:orm:book:9781492077992/files/assets/f0155-01.png)

# Abstract Factory Pattern defined

We’re adding yet another factory pattern to our pattern family, one that lets us create families of products. Let’s check out the official definition for this pattern:

###### NOTE

**The Abstract Factory Pattern** provides an interface for creating families of related or dependent objects without specifying their concrete classes.

We’ve certainly seen that Abstract Factory allows a client to use an abstract interface to create a set of related products without knowing (or caring) about the concrete products that are actually produced. In this way, the client is decoupled from any of the specifics of the concrete products. Let’s look at the class diagram to see how this all holds together:

![Images](https://learning.oreilly.com/api/v2/epubs/urn:orm:book:9781492077992/files/assets/f0156-01.png)

**That’s a fairly complicated class diagram; let’s look at it all in terms of our PizzaStore:**

![Images](https://learning.oreilly.com/api/v2/epubs/urn:orm:book:9781492077992/files/assets/f0157-01.png)

![Images](https://learning.oreilly.com/api/v2/epubs/urn:orm:book:9781492077992/files/assets/f0158-01.png)

**Is that a factory method lurking inside the Abstract Factory?**

Good catch! Yes, often the methods of an Abstract Factory are implemented as factory methods. It makes sense, right? The job of an Abstract Factory is to define an interface for creating a set of products. Each method in that interface is responsible for creating a concrete product, and we implement a subclass of the Abstract Factory to supply those implementations. So, factory methods are a natural way to implement your product methods in your abstract factories.

##### ![Inline](https://learning.oreilly.com/api/v2/epubs/urn:orm:book:9781492077992/files/assets/f0158-02.png) PATTERNS EXPOSED

**This** week’s interview: **Factory Method and Abstract Factory, on each other**

**HeadFirst:** Wow, an interview with two patterns at once! This is a first for us.

**Factory Method:** Yeah, I’m not so sure I like being lumped in with Abstract Factory, you know. Just because we’re both factory patterns doesn’t mean we shouldn’t get our own interviews.

**HeadFirst:** Don’t be miffed, we wanted to interview you together so we could help clear up any confusion about who’s who for the readers. You do have similarities, and I’ve heard that people sometimes get you confused.

**Abstract Factory:** It’s true, there have been times I’ve been mistaken for Factory Method, and I know you’ve had similar issues, Factory Method. We’re both really good at decoupling applications from specific implementations; we just do it in different ways. So I can see why people might sometimes get us confused.

**Factory Method:** Well, it still ticks me off. After all, I use classes to create and you use objects; that’s totally different!

**HeadFirst:** Can you explain more about that, Factory Method?

**Factory Method:** Sure. Both Abstract Factory and I create objects—that’s our job. But I do it through inheritance...

**Abstract Factory:** ...and I do it through object composition.

**Factory Method:** Right. So that means, to create objects using Factory Method, you need to extend a class and provide an implementation for a factory method.

**HeadFirst:** And that factory method does what?

**Factory Method:** It creates objects, of course! I mean, the whole point of the Factory Method Pattern is that you’re using a subclass to do your creation for you. In that way, clients only need to know the abstract type they are using; the subclass worries about the concrete type. So, in other words, I keep clients decoupled from the concrete types.

**Abstract Factory:** And I do too, only I do it in a different way.

**HeadFirst:** Go on, Abstract Factory...you said something about object composition?

**Abstract Factory:** I provide an abstract type for creating a family of products. Subclasses of this type define how those products are produced. To use the factory, you instantiate one and pass it into some code that is written against the abstract type. So, like Factory Method, my clients are decoupled from the actual concrete products they use.

**HeadFirst:** Oh, I see, so another advantage is that you group together a set of related products.

**Abstract Factory:** That’s right.

**HeadFirst:** What happens if you need to extend that set of related products to, say, add another one? Doesn’t that require changing your interface?

**Abstract Factory:** That’s true; my interface has to change if new products are added, which I know people don’t like to do....

**Factory Method:** snicker

**Abstract Factory:** What are you snickering at, Factory Method?

**Factory Method:** Oh, come on, that’s a big deal! Changing your interface means you have to go in and change the interface of every subclass! That sounds like a lot of work.

**Abstract Factory:** Yeah, but I need a big interface because I am used to creating entire families of products. You’re only creating one product, so you don’t really need a big interface, you just need one method.

**HeadFirst:** Abstract Factory, I heard that you often use factory methods to implement your concrete factories?

**Abstract Factory:** Yes, I’ll admit it, my concrete factories often implement a factory method to create their products. In my case, they are used purely to create products...

**Factory Method:** ...while in my case I usually implement code in the abstract creator that makes use of the concrete types the subclasses create.

**HeadFirst:** It sounds like you both are good at what you do. I’m sure people like having a choice; after all, factories are so useful, they’ll want to use them in all kinds of different situations. You both encapsulate object creation to keep applications loosely coupled and less dependent on implementations, which is really great, whether you’re using Factory Method or Abstract Factory. May I allow you each a parting word?

**Abstract Factory:** Thanks. Remember me, Abstract Factory, and use me whenever you have families of products you need to create and you want to make sure your clients create products that belong together.

**Factory Method:** And I’m Factory Method; use me to decouple your client code from the concrete classes you need to instantiate, or if you don’t know ahead of time all the concrete classes you are going to need. To use me, just subclass me and implement my factory method!

# Factory Method and Abstract Factory compared

![Images](https://learning.oreilly.com/api/v2/epubs/urn:orm:book:9781492077992/files/assets/f0160-01.png)

![Images](https://learning.oreilly.com/api/v2/epubs/urn:orm:book:9781492077992/files/assets/f0161-01.png)

###### NOTE

The product subclasses create parallel sets of product families. Here we have a New York ingredient family and a Chicago family.

# ![Images](https://learning.oreilly.com/api/v2/epubs/urn:orm:book:9781492077992/files/assets/tools.png) Tools for your Design Toolbox

In this chapter, we added two more tools to your toolbox: Factory Method and Abstract Factory. Both patterns encapsulate object creation and allow you to decouple your code from concrete types.

![Images](https://learning.oreilly.com/api/v2/epubs/urn:orm:book:9781492077992/files/assets/f0162-02.png)

##### ![Images](https://learning.oreilly.com/api/v2/epubs/urn:orm:book:9781492077992/files/assets/bullet.png) BULLET POINTS

-   All factories encapsulate object creation.
    
-   Simple Factory, while not a bona fide design pattern, is a simple way to decouple your clients from concrete classes.
    
-   Factory Method relies on inheritance: object creation is delegated to subclasses, which implement the factory method to create objects.
    
-   Abstract Factory relies on object composition: object creation is implemented in methods exposed in the factory interface.
    
-   All factory patterns promote loose coupling by reducing the dependency of your application on concrete classes.
    
-   The intent of Factory Method is to allow a class to defer instantiation to its subclasses.
    
-   The intent of Abstract Factory is to create families of related objects without having to depend on their concrete classes.
    
-   The Dependency Inversion Principle guides us to avoid dependencies on concrete types and to strive for abstractions.
    
-   Factories are a powerful technique for coding to abstractions, not concrete classes.
    

# ![Inline](https://learning.oreilly.com/api/v2/epubs/urn:orm:book:9781492077992/files/assets/design.png) Design Patterns Crossword

It’s been a long chapter. Grab a slice of Pizza and relax while doing this crossword; all of the solution words are from this chapter.

![Images](https://learning.oreilly.com/api/v2/epubs/urn:orm:book:9781492077992/files/assets/f0163-02.png)

**ACROSS**

1. In Factory Method, each franchise is a ________.

4. In Factory Method, who decides which class to instantiate?

6. Role of PizzaStore in the Factory Method Pattern.

7. All New York—style pizzas use this kind of cheese.

8. In Abstract Factory, each ingredient factory is a _______.

9. When you use **new**, you are programming to an ___________.

11. createPizza() is a ____________.

12. Joel likes this kind of pizza.

13. In Factory Method, the PizzaStore and the concrete Pizzas all depend on this abstraction.

14. When a class instantiates an object from a concrete class, it’s ___________ on that object.

15. All factory patterns allow us to __________ object creation.

**DOWN**

2. We used ___________ in Simple Factory and Abstract Factory, and inheritance in Factory Method.

3. Abstract Factory creates a ___________ of products.

5. Not a REAL factory pattern, but handy nonetheless.

10. Ethan likes this kind of pizza.

##### ![Inline](https://learning.oreilly.com/api/v2/epubs/urn:orm:book:9781492077992/files/assets/pencil.png) SHARPEN YOUR PENCIL SOLUTION

We’ve knocked out the NYPizzaStore; just two more to go and we’ll be ready to franchise! Write the Chicago-style and California-style PizzaStore implementations here:

![Images](https://learning.oreilly.com/api/v2/epubs/urn:orm:book:9781492077992/files/assets/f0164-01.png)

# ![Inline](https://learning.oreilly.com/api/v2/epubs/urn:orm:book:9781492077992/files/assets/design.png) Design Puzzle Solution

We need another kind of pizza for those crazy Californians (crazy in a _good_ way, of course). Draw another parallel set of classes that you’d need to add a new California region to our PizzaStore.

![Images](https://learning.oreilly.com/api/v2/epubs/urn:orm:book:9781492077992/files/assets/f0165-01.png)

Okay, now write the five silliest things you can think of to put on a pizza. Then, you’ll be ready to go into business making pizza in California!

![Images](https://learning.oreilly.com/api/v2/epubs/urn:orm:book:9781492077992/files/assets/f0165-02.png)

##### ![Inline](https://learning.oreilly.com/api/v2/epubs/urn:orm:book:9781492077992/files/assets/pencil.png) SHARPEN YOUR PENCIL SOLUTION

Let’s pretend you’ve never heard of an OO factory. Here’s a “very dependent” version of PizzaStore that doesn’t use a factory. We need for you to make a count of the number of concrete pizza classes this class is dependent on. If you added California-style pizzas to PizzaStore, how many classes would it be dependent on then? Here’s our solution.

![Images](https://learning.oreilly.com/api/v2/epubs/urn:orm:book:9781492077992/files/assets/f0166-01.png)

##### ![Inline](https://learning.oreilly.com/api/v2/epubs/urn:orm:book:9781492077992/files/assets/pencil.png) SHARPEN YOUR PENCIL SOLUTION

Go ahead and write the ChicagoPizzaIngredientFactory; you can reference the classes below in your implementation:

``` java
public class ChicagoPizzaIngredientFactory
    implements PizzaIngredientFactory
{

    public Dough createDough() {
        return new ThickCrustDough();
    }

    public Sauce createSauce() {
        return new PlumTomatoSauce();
    }

    public Cheese createCheese() {
        return new MozzarellaCheese();
    }

    public Veggies[] createVeggies() {
        Veggies veggies[] = { new BlackOlives(),
                              new Spinach(),
                              new Eggplant() };
        return veggies;
    }

    public Pepperoni createPepperoni() {
        return new SlicedPepperoni();
    }

    public Clams createClam() {
        return new FrozenClams();
    }
}
```

![Images](https://learning.oreilly.com/api/v2/epubs/urn:orm:book:9781492077992/files/assets/f0167-01.png)

# ![Inline](https://learning.oreilly.com/api/v2/epubs/urn:orm:book:9781492077992/files/assets/design.png) Design Patterns Crossword Solution

It’s been a long chapter. Grab a slice of Pizza and relax while doing this crossword; all of the solution words are from this chapter. Here’s the solution.

![Images](https://learning.oreilly.com/api/v2/epubs/urn:orm:book:9781492077992/files/assets/f0168-01.png)