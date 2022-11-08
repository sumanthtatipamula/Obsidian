----

## Problem
1. We need to restrict the instantiation of a class to exactly one object 

## Examples
1. Configuration Classes
2. Logging Classes
3. Shared Resources

## What do we need ?
1. Exactly one object - restrictions are encapsulated in the product itself
2. Accessible by all clients

## Solution
1. The class ensures that no other instance can be created by intercepting requests to create new objects.
2. If the object is already created, a request for an instance would provide access to the existing object instead of creating one.
3. Static Members ?
	   1. When a member is declared as static, only one copy of the data is maintained for all objects of the class.
	   2. This member is accessed through the class name
	      `ClassName.memberName;`

4. UML Diagram ![[Pasted image 20221108072748.png]]

	   1. The static member representing the singleton should be private
	   2. Constructors are private
	   3. Access to the instance via a static public getter

