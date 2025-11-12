## Exercise 2 -  Generic Data Processor

In this exercise, you will define a generic interface and a couple of implementations of the (specialized) interface to process data of different types. Another class provides an static method that can work with different data types.

Perform the following tasks:

* Define a **generic** interface called `DataProcessor<T>`. This interface should have a single method with (Java) signature `T process(T data)`. Does it make sense/is it allowed to implement the method in the definition of the interface? Why/Why not?
* Design a class `IntegerMultiplier` that implements the `DataProcessor<Integer>` interface. This class should implement the `process` method to double the input integer.
* Design a class `StringReverser` that implements the `DataProcessor<String>` interface. This class should implement the `process` method to reverse the input string.
* Design a class named `DataHandler`. This class should have a **generic** static method `T handleData(T data, DataProcessor<T> processor)`. This method should take a piece of data and a `DataProcessor` and return the processed data.
  (Why the `DataHandler` class can be non-generic despite having a generic static method?)
* Create a `Main` class with a `main` method. Use the `DataHandler`'s static method to process an integer with an instance of `IntegerMultiplier` and a string with an instance of `StringReverser`.
  Print the original and processed data in both cases.

s
