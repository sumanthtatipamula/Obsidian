----
## Problem
1. Different subtypes/subclasses of the `Creator` need to create different subtypes/subclasses of the `Product`

## Solution
1. The factory Method pattern defines an abstract method for creating an object, but lets subclasses decide which class to instantiate
2. The Factory Method lets a class defer instantiation to subclasses.
3. ![[Pasted image 20221108075952.png]]
	1.  `Creator` - Define an abstract method in the parent creator, this method is mean to return a reference to the client object.
	2. `product = FactoryMethod()` Call that abstract method instead of the product constructor whenever it's needed.
	3. `return new ConcreteProduct` The subclasses of the Creator implements the factory method with a call to the constructor of the Product subtype that it wishes to.