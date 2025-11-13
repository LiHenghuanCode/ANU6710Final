# Thematic graph exercises

In Exercises 1-3 of this block of exercises, we will work with directed simple graphs.
In directed graphs, edges have a direction, i.e., a source vertex from which the edge
departs, and a target vertex to which the edge points.
Simple graphs do not have multiple edges from the same source to the same target,
or self-edges from some vertex to itself.
No data is associated with edges (in particular, there are no weights), but vertices
have data associated with them (e.g. the name of a vertex).

## Exercise 1 - Modelling simple directed graphs and read such graphs from files

*In a file `src/Graph.java`*


In this exercise, you will have to read simple directed graphs from files, whose format consists of three sections:
- The first section is a single line (the first line of the file), which contains an integer indicating number of vertices in the graph.
- After the first line, we have a second section of the file with as many lines as vertices (given by the integer in the first line).
  Each of these lines contains a string representation of the data that the corresponding vertex of the graph stores.  
  Each vertex is implicitly assigned a unique integer identifier starting from `0` up to
  `numVertices-1`. The vertex identifiers are assigned following the order of the lines
  in the file. Thus, the second line in the file corresponds to the vertex with identifier `0`, the
  third line of the file to the vertex with identifier `1`, and so on.
- After the vertices, we have a third and last section of the file with some number of additional lines,
  until the end of the file.
  Each line contains two integer numbers separated by a single space. These integer numbers
  denote the vertex identifiers of the two vertices that the edge connects.
  The first number indicates the source vertex, whereas the second number indicates the target vertex of the edge.

You can assume that:

1. The first line contains an integer, i.e., `Integer.parseInt` won't throw an exception when processing the characters of the first line.
2. The second section of the file must have as many lines as the number provided in the first line.
3. The lines of the second section of the file must have at least one character different from the `\n` character, i.e.,
   they cannot be empty.
4. There cannot be self-edges, i.e., edges where the two connecting vertex identifiers match.
5. There cannot be repeated edges in the file.
6. The edges in the file always refer to vertex identifiers within valid bounds.
7. The edges in the file might be listed in an arbitrary order.
8. Two different vertices may have the same value, and thus there might be different edges that connect
   vertices with the same value.

An example simple directed graph, along with its file representation, is provided below:

![alt text](./unweighted_directed_graph_example.png)

```
9
A
B
C
L
D
X
M
G
A
1 0
2 1
3 0
1 4
5 2
3 1
5 1 
3 4
4 5
3 6
4 7
8 5
3 7
5 7
7 6
7 8
```

Design a **generic** class called `Graph<T>` to model simple directed graphs,
according to the specifications below. The type parameter `T` is used to denote the type of the values
stored within each graph vertex. You **must** design at least the following members:

```java
/**
 * Returns the number of vertices in the graph.
 */
public int getNumVertices()

/**
 * Returns a List<Vertex<T>> with the vertices of 
 * the graph. Vertex<T> is the name of the class that 
 * you need to design to represent graph vertices. 
 * More details on Vertex<T> below. The position
 * of a vertex within this list must match with its
 * vertex identifier.
 */
public List<Vertex<T>> getVertices()

/**
 * Returns the number of edges in the graph.
 */
public int getNumEdges()

/**
 * Returns a List<Edge<T>> with the edges of 
 * the graph. Edge<T> is the name of the class that 
 * you need to design to represent graph edges. 
 * More details on Edge<T> below.
 */
public List<Edge<T>> getEdges()

/**
 * Given a text file in the above format,
 * and a function that converts Strings
 * to values of type T, returns a 
 * Graph<T> instance that holds
 * the information stored in the file.
 */ 
public static <T> Graph<T> readGraph(
   File file, 
   Function<String, T> stringToT);

/**
 * Returns a list with the identifiers of all vertices that 
 * have maximum outgoing degree in the graph. The outgoing 
 * degree of a vertex is defined as the number of edges that
 * depart a vertex. The vertices should be ordered by increasing 
 * identifier in the returned list.
 */
public List<Integer> maxOutgoingDegreeVerticesIDs();


/**
 * Returns a list with the identifiers of all vertices that 
 * have maximum incoming degree in the graph. The incoming 
 * degree of a vertex is  defined as the number of edges in 
 * the graph that point to the vertex.The vertices should be 
 * ordered by increasing identifier in the returned list.
 */
public List<Integer> maxIncomingDegreeVerticesIDs();


/**
 * Returns how many vertices in the graph have the given value
 * stored within them.
 */
public int countVerticesWithValue(T value);

/**
 * Returns how many edges in the graph connect vertices storing 
 * the given values. sourceVertexValue denotes the value of the
 * vertex from which the edge departs, while targetVertexValue
 * the value of the vertex to which the edge points to.
 */
public int countEdgesWithValues(T sourceVertexValue, 
                                T targetVertexValue);
```


You must also design the `Vertex<T>` and `Edge<T>` classes.
These might be either in the same file as `Graph<T>`, or in separate
files `Vertex.java` and `Edge.java`, respectively.

For `Vertex<T>` you must design at least the following members:


```java
/**
 * Returns the vertex identifier.
 */
public int getID()


/**
 * Returns the value stored within the vertex.
 */
public T getValue()

/**
 * Returns a List<Edge<T>> with all the edges
 * the graph that depart from the vertex.
 */
public List<Edge<T>> getOutgoingEdges()

/**
 * Returns a List<Edge<T>> with all the edges
 * the graph that point to the vertex.
 */
public List<Edge<T>> getIncomingEdges()
```


For `Edge<T>` you must design at least the following members:

```java
/**
 * Returns the vertex from which the edge
 * departs.
 */
public Vertex<T> getSourceVertex()

/**
 * Returns the vertex to which the edge
 * points.
 */
public Vertex<T> getTargetVertex()
```
