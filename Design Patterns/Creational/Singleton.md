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
2. If the object is already created a request for an instance would provide access to the existing object i

