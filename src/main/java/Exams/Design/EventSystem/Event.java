package Exams.Design.EventSystem;

/**
 * Represents an event to be handled by an event handler function.
 * Events have a kind. Kinds are case-sensitive. Thus, e.g.,
 * "KEY_PRESSED" and "key_pressed" are considered to be different
 * event kinds. Events also hold data associated to the event.
 *
 *  Examples: Event("KEY_PRESSED", "F10")
 *            Event("MOUSE_LEFT_CLICK", "X:10 Y:20")
 *
 * @param kind The kind of the event
 * @param data The data associated to the event
 * @implSpec Invariants: once an event is instantiated,
 *                       its kind and data cannot be modified.
 */
public record Event(String kind, String data) {}

/*
You are required to complete the implementation of two classes, named EventHandler and EventsContainer, respectively.
Some scaffolding and associated documentation are provided below for each class.
We recommend to start with the implementation of EventHandler, followed by the one of EventsContainer.
In any case, before actually
implementing anything, we also recommend *to carefully read the documentation of both classes beforehand to better
grasp the relationship among these and what is required from you in this exercise.
Hint: A key decision in this exercise is which data structures (i.e., field members) to use in EventHandler and EventsContainer.
Given that this block of exercises is about Maps, you may imagine that a Map<K,V> may serve the purpose
for one of these two classes. Which would be the type of the keys (i.e. K type parameter) and the type of the values of the map
(i.e., V type parameter). For the other, there might be several choices, but as a hint, a data
structure which can be considered the most popular in this course so far may serve the purpose.
 */
