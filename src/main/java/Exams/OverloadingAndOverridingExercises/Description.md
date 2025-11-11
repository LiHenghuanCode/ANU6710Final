# Method Overloading and Overriding Exercises

Overloading and overriding are two distinct concepts in Java's object-oriented programming paradigm.

**Overloading** refers to having multiple class methods with the same name but different number of arguments, or same number of arguments but different argument types (for at least one of the arguments). The compiler decides which method to call **at compile time** based on the types of the arguments in the method's call.

**Overriding** occurs when a subclass provides a specific implementation for a method that is already defined in its superclass (a.k.a. base class, or parent class). The method signature must be the same in both the superclass and the subclass. (The subclass implementation must also result in a subclass that complies with the Liskov substitution principle.) The decision of which method to call is made **at runtime** based on the run-time type of the variable from which the method is called (in contrast to the compiled type of the variable).

## Exercise 1 - A simple game entity system

The goal of this exercise is to design a simple game system that uses both overloading and overriding to manage different types of game entities.

Perform the following tasks:

* Design an **abstract** class `GameEntity`. The class should keep track of the horizontal and vertical coordinates of game entities within a 2D space coordinate system. You can represent these coordinates using `int`.
* Add an appropriate constructor to the `GameEntity` class.
* Add an abstract instance method to `GameEntity`, called `render`, which returns `void`, and has no arguments.
  The `render` method should be overridden by the subclasses. It simulates the rendering of the game item by just printing a specialized message on screen (see below for details.).
* In the `GameEntity` class, overload the `move` instance method with three different variants:
    * `move(int x, int y)`, which moves the entity to the given coordinates.
    * `move(Direction direction, int distance)`, which moves the entity a certain distance in a specific direction (e.g., `Direction.NORTH`, or `Direction.SOUTH`). You can assume there are 8 possible directions.
    * `move(GameItem target)`, which moves the entity towards to `target` entity.
* Design two subclasses of `GameEntity`, namely, `Player`, and `Enemy`. The system handles the health associated to a `Player` (represented as an `int` between 0 and 100), and a `threatLevel` associated to an `Enemy` (also represented an `int` between 0 and 100).
* Add appropriate constructors to these two subclasses. These constructors should allow to set the value of the fields that the subclasses add to the `GameItem` class.
* Override the `render` method in both `Player` and `Enemy` (what happens if you do not override it?). Do not forget to use the optional `@Override` decorator. The `render` method should print messages on screen such as:
    * `"Rendering Player at (100,200) with health: 98"`
    * `"Rendering Enemy at (-50,38) with threat level: 5"`
* Add a `Main` class with a `main` method. Perform the following tasks in sequence:
    * Create an instance of a `Player` and another instance of an `Enemy`. The compiled type of the variables from which these two instances are referred to should be declared as `Player` and `Enemy`, respectively.
    * Call the `render` method on both instances and check that they print the expected message on screen.
    * Create an instance of a class that implements the `List<GameEntity>` interface, and add the instances created in the first step to the list. Why such instances can be flawlessly added to the list?
    * Iterate over the entities in the list using an "enhanced" for loop, and call the `render` method again within the loop. Which are the static and dynamic types of the loop variable at each iteration? Which implementation of the `render` method is called at each
      iteration? Why?
    * Finally, call the three variants of `move` with both the `Player` and `Enemy` instances, and check that they have the expected effects.
      Could we also call the `move` method through a variable of compiled type `GameItem` and dynamic type `Player` and `Enemy` (e.g., in an enhanced "for" loop)? Why/Why not?


## Exercise 2 - A simple payment processor system

This exercise uses an abstract class and concrete subclasses to model a simple payment system. Overriding handles the different payment methods, while overloading provides different ways to call the same method within each processor type.

* Design an **abstract** class named `PaymentProcessor` with no instance fields.
* Add an abstract instance method to `PaymentProcessor`, called `processPayment`, which returns `void`, and has a single argument of type `double`, representing the amount to be processed in a pay transaction, measured in AUD.
* Design a first a subclass of `PaymentProcessor`, called  `PayPalProcessor`. The class keeps track of the e-mail address of the PayPal account associated to the payment transaction as a `String`.
* Add an appropriate constructor to the `PayPalProcessor` class, and override the `processPayment` method. The overridden method should simulate the payment process by printing a message on screen
  along the following lines: `"Processing 300 AUD via PayPal. User email: user@example.com"`.
* Design a second a subclass of `PaymentProcessor`, called  `CreditCardProcessor`. The class keeps track of the credit card number and expiry date, both represented as `String`s.
* Add an appropriate constructor to the `CreditCardProcessor` class, and override the `processPayment` method. The overridden method should simulate the payment process by printing a message on screen
  along the following lines: `"Processing 250 AUD via Credit Card. Card number: 1234-5678-9012-3456"`.
* In the `CreditCardProcessor`, overload the `processPayment` method with a method with the following signature: `void processPayment(double amount, String transactionID)`, where `transactionID` is a unique identifier associated to the
  credit card payment transaction (In a real-world system, this would allow to check for a duplicate payment transaction request).  This method should simulate the payment process by printing a message on screen
  along the following lines: `"Processing 25 AUD via Credit Card. Card number: 1234-5678-9012-3456. Transaction ID: TXN12345"`
* Create a `Main` class with a `main` method. Perform the following tasks in sequence:
    * Create an instance of a `PayPalProcessor` and another instance of a `CreditCardProcessor`. The compiled type of the variables referencing to these two instances must be declared as `PaymentProcessor`.
    * Call the `processPayment(double amount)` method on both instances and check that they print the expected message on screen.
    * Try to call the `processPayment(double amount, String transactionID)` method on the instance of dynamic type `CreditCardProcessor`. Did your code compile? If not, what can you do in order to avoid the compiler error and call the overloaded variant of the method in `CreditCardProcessor`?
    * Try to call the `processPayment(double amount, String transactionID)` method on the instance of dynamic type `PayPalProcessor`. Did your code compile? If not, is there anyway to bypass the error? Why/Why not?

**Question**: could this system also be designed using a Java interface for `PaymentProcessor` instead of an abstract class? Why/Why not?



  





