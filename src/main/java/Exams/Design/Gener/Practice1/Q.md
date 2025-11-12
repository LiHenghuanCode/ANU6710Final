# Subtyping and Generics Exercises

## Exercise 1 - Shape Class Hierarchy with Generics

In this exercise, you will create a hierarchy of geometric shapes and use generics to create a container that can hold a specific type of shape.

Perform the following tasks:

* Define an **abstract** class named `Shape`. This class should have an **abstract** instance method `double area()` and a concrete method `void display()`. The `display` method should just print a message like `"This is a shape."`
* Design two subclasses of `Shape`, named `Circle` and `Rectangle`. Decide which instance fields are required in each class in order to implement the `area` instance method.
* Implement (i.e., override) the `area` instance method for both classes. Don't forget to use the `@Override` decorator.
* Implement a generic class  `ShapeContainer<T extends Shape>`. This class should have a private field, a `List<T>`, to store shapes. In this class:
    * Implement a method `void addShape(T shape)` to add a shape to the container of shapes.
    * Implement a method `double getTotalArea()` that iterates through the list and calculates the sum of the areas of all the shapes in the container.
    * After implementing these two methods, figure out what happens if you replace `T extends Shape` by `T` in the definition of the `ShapeContainer` class. Why?
* Create a `Main` class with a `main` method. The method should create 3 instances of `Circle` and 3 instances of `Rectangle`. Besides, it should create one instance of `ShapeContainer<Circle>` and another instance of `ShapeContainer<Rectangle>`, add the appropriate
  shapes to each container and print the total area of each container.
    * What happens if you try to create an instance of `ShapeContainer` of element type not extending `Shape`? Why?
    * What happens if you try to add a `Circle` instance to the container of type `ShapeContainer<Rectangle>` ? Why?
    * Is it possible to create an instance of `ShapeContainer<Shape>`? Why/Why not?
      If yes, can it hold instances of `Circle` and `Rectangle` at the same time? Why/Why not? If yes, which OO mechanism enables the `getTotalArea` method of `ShapeContainer<Shape>` to provide the correct result?
