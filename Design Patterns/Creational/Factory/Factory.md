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
4. Document Example![[Pasted image 20221108080457.png]]
5. Eg: Microsoft Office which contains Microsoft Excel, PPT, World
	1. Parent will be general application office
	2. Specific application will be word ppt etc.

### Applicability:
1.	Use the Factory Method when you want to provide users of your library or framework with a way to extend its internal components.
	1.	Eg : Consider Notification service that provides 3 different services say SMS, MAIL, PUSH. instead of exposing each of the concrete class we can expose a Notification Service Factory class and a 