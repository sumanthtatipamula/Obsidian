----
1. Relational Model: Data is organized into _relations_ (called _tables_ in SQL), where each relation is an unordered collection of _tuples_ (_rows_ in SQL).
2. _NoSQL_  is considered as _Not Only SQL_ 
3. Reasons for NoSQL adoption
	1. A need for greater scalability than relational databases can easily achieve, including very large datasets or very high write throughput
	2.  A widespread preference for free and open source software over commercial database products.
	3. Specialized query operations that are not well supported by the relational model
	4. Frustration with the restrictiveness of relational schemas, and a desire for a more dynamic and expressive data model
4. _Polyglot persistence_  is a fancy term to mean that when storing data, it is best to use multiple data storage technologies, chosen based upon the way data is being used by individual applications or components of a single application.
5. _Impedance mismatch_ : used to refer to the problems that occurs due to differences between the database model and the programming language model. Problems could be
	1. The first problem that may occur is that is data type mismatch means the programming language attribute data type may differ from the attribute data type in the data model.
	2. The second problem that may occur is because the results of most queries are sets or multisets of tuples and each tuple is formed of a sequence of attribute values. In the program, it is necessary to access the individual data values within individual tuples for printing or processing.
6. Object-relational mapping (ORM) frameworks like ActiveRecord and Hibernate reduce the amount of boilerplate code required for this translation layer, but they can’t completely hide the differences between the two models
7. Later versions of the SQL added support for structured datatypes and XML data. i.e JSON or XML document is can be stored on a text column in the database and its application responsibility to interpret its structure and content.
8. The advantages of using ids in place where it is referred in other tables than actual text.
	1. Consistent style and spelling across profiles
	2. Avoiding ambiguity (e.g., if there are several cities with the same name)
	3. Avoiding duplication of  the human-meaningful information in every record that uses it.
	4. It never needs to change, even if the information it identifies changes
	5. 