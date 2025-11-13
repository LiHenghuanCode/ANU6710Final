package Exams.Graphic.Practice1;

import java.util.*;
import static java.util.Arrays.asList;

/**
 * Represents a node in a weighted undirected graph.
 * @param <T> a type for data stored in a graph node
 * @param neighbours the neighbouring nodes (connected via an edge) of
 *                   the graph node
 * @param neighboursCosts   a map from the neighbouring nodes (connected via an edge) of
 *                          the graph node to the cost of the corresponding edge. Edge
 *                          costs are assumed to be non-negative integer numbers.
 *
 * @implSpec Invariants:
 *    - neighbours does not contain repeated elements.
 *    - for every GraphNode<T> in neighbours, the result of calling
 *      getNeighbours() contains this
 *    - neighbours contains the same elements as the key set of the
 *      neighboursCosts map
 *    - all the values in neighboursCosts are non-negative
 */
public class GraphNode<T> {
    private final T value;
    private final List<GraphNode<T>> neighbours = new ArrayList<>();
    private final Map<GraphNode<T>, Integer> neighboursCosts = new HashMap<>();

    /**
     * Creates a new GraphNode<T> given the value, neighbours, and associated costs.
     * @param value The value to be stored within the newly created graph node.
     * @param neighbours A list of neighbours of the newly created graph node.
     * @param neighboursCosts A list with the costs associated to the edges that connect the newly
     *                        created node with its neighbours. All costs have to be non-negative.
     *
     * Effects:
     *          - the newly created node is added to neighbours.
     *          - Besides, a new key-value pair is added
     *            to neighboursCosts with the key being the newly created node,
     *            and the value the cost of the corresponding connection.
     *
     * @implSpec Precondition: The size of neighbours and neighboursCosts must match.
     * @implSpec Precondition: neighboursCosts[i] must contain the cost of the edge
     *                         that connects the newly created node and neighbours[i] for all i.
     * @implSpec Postcondition: All invariants of GraphNode<T> are satisfied for the
     *                          newly created node
     */

    public GraphNode(T value, List<GraphNode<T>> neighbours, List<Integer> neighboursCosts) {
        this.value = value;
        for (int i=0; i<neighbours.size();i++) {
            var neighbour = neighbours.get(i);
            var cost = neighboursCosts.get(i);

            // Add neighbour to this and the reciprocal connection
            this.neighbours.add(neighbour);
            neighbour.neighbours.add(this);

            // Add cost to the connection among this and neighbour
            // and to the reciprocal connection
            this.neighboursCosts.put(neighbour, cost);
            neighbour.neighboursCosts.put(this, cost);
        }
    }

    /**
     * @return the value associated with the current graph node
     */
    T getValue() {
        return value;
    }

    /**
     * @return the neighbouring nodes (connected via an edge) of
     * the graph node
     * @implSpec Postcondition: the returned Collection contains the same
     *   elements as the key set of the map returned by getCostedNeighbours
     * @implSpec Postcondition: for every GraphNode<T> in the result,
     *   the result of calling getNeighbours() on that node contains this
     */
    Collection<GraphNode<T>> getNeighbours() {
        return neighbours;
    }

    /**
     * @return a map from the neighbouring nodes (connected via an edge) of
     * the graph node to the cost of the corresponding edge
     * @implSpec Postcondition: the key set of the returned map contains
     *   the same elements as the Collection returned by getNeighbours
     * @implSpec Postcondition: for every GraphNode<T> in the key set of
     *   the result, the key set of calling getCostedNeighbours on that
     *   node contains this
     */
    Map<GraphNode<T>, Integer> getNeighboursCosts() {
        return neighboursCosts;
    }

    @Override
    public String toString() {
        return value.toString();
    }

    /**
     * Returns a list of graph node values resulting from an iterative
     * DFS traversal of the graph starting from the node on which the method
     * is invoked. The order in which adjacent nodes of a node are explored and
     * enumerated should be based on the order in the Collection returned by
     * getNeighbours().
     * For the 8 graphs provided, the output of iterativeDFS should be:
     * graph1: [5]
     * graph2: ["A", "B"]
     * graph3: [1, 2, 3]
     * graph4: [3, 1, 2]
     * graph5: ["A", "B", "C", "E", "D"]
     * graph6: [111, 222, 555, 666, 777, 333, 888, 999, 101, 444]
     * graph7: ["A", "B", "E", "F" ,"G", "I", "C", "H", "J", "D"]
     * graph8: ["A", "B", "C", "D", "E", "F" ]
     * Check how the output node returned out of the generateExampleGraphN methods affects to the
     * generated output.
     * For example, if the output of generateExampleGraph7 is changed from
     * nodeA to nodeI then the output would become ["I", "C", "A", "B", "E", "F", "D", "H", "J", "G"].
     * Following the design recipe (step 6), Write comprehensive JUnit tests for this method.
     */
    public List<T> iterativeDFS(){
        return null;
    }


    /**
     * Returns a list of graph node values resulting from an iterative
     * BFS traversal of the graph starting from the node on which the method
     * is invoked. The order in which adjacent nodes of a node are explored and
     * enumerated should be based on the order in the Collection returned by
     * getNeighbours().
     * Implement the following instance method of the GraphNode<T> class:Implement the following instance method of the GraphNode<T> class:
     *
     * For the 8 graphs provided, the output of iterativeBFS should be:
     * graph1: [5]
     * graph2: ["A", "B"]
     * graph3: [1, 2, 3]
     * graph4: [3, 1, 2]
     * graph5: ["A", "B", "D", "C", "E"]
     * graph6: [111, 222, 333, 444, 555, 666 ,777, 888, 999, 101]
     * graph7: ["A", "B", "C", "D", "E", "F" ,"G", "H", "I", "J"]
     * graph8: ["A", "B", "C", "D", "F", "E" ]
     * Check how the output node returned out of the generateExampleGraphN methods affects to the
     * generated output.
     * For example, if the output of generateExampleGraph7 is changed from
     * nodeA to nodeI then the output would become ["I", "C", "G", "A", "H", "J", "B", "D", "E", "F"].
     * Following the design recipe (step 6), write comprehensive JUnit tests for this method.
     */
    public List<T> iterativeBFS(){
        return null;
    }


















    public static GraphNode<Integer> generateExampleGraph1() {
        return new GraphNode<>(5, asList(), asList());
    }

    public static GraphNode<String> generateExampleGraph2() {
        var nodeA = new GraphNode<>("A", asList(), asList());
        var nodeB = new GraphNode<>("B", asList(nodeA), asList(1));
        return nodeA;
    }

    public static GraphNode<Integer> generateExampleGraph3() {
        var node1 = new GraphNode<>(1, asList(), asList());
        var node2 = new GraphNode<>(2, asList(node1), asList(2));
        var node3 = new GraphNode<>(3, asList(node1), asList(1));
        return node1;
    }

    public static GraphNode<Integer> generateExampleGraph4() {
        var node1 = new GraphNode<>(1, asList(), asList());
        var node2 = new GraphNode<>(2, asList(node1), asList(2));
        var node3 = new GraphNode<>(3, asList(node1,node2), asList(4, 1));
        return node3;
    }

    public static GraphNode<String> generateExampleGraph5() {
        var nodeA = new GraphNode<>("A", asList(), asList());
        var nodeB = new GraphNode<>("B", asList(nodeA), asList(15));
        var nodeC = new GraphNode<>("C", asList(nodeB), asList(20));
        var nodeD = new GraphNode<>("D", asList(nodeA,nodeC), asList(10,5));
        var nodeE = new GraphNode<>("E", asList(nodeD,nodeB), asList(11,7));
        return nodeA;
    }

    public static GraphNode<Integer> generateExampleGraph6() {
        var node111 = new GraphNode<>(111, asList(), asList());
        var node222 = new GraphNode<>(222, asList(node111), asList(4));
        var node333 = new GraphNode<>(333, asList(node111), asList(5));
        var node444 = new GraphNode<>(444, asList(node111), asList(17));
        var node555 = new GraphNode<>(555, asList(node222), asList(5));
        var node666 = new GraphNode<>(666, asList(node222), asList(12));
        var node777 = new GraphNode<>(777, asList(node222), asList(6));
        var node888 = new GraphNode<>(888, asList(node333), asList(11));
        var node999 = new GraphNode<>(999, asList(node333), asList(21));
        var node101 = new GraphNode<>(101, asList(node333), asList(19));
        return node111;
    }

    public static GraphNode<String> generateExampleGraph7() {
        var nodeA = new GraphNode<>("A", asList(), asList());
        var nodeB = new GraphNode<>("B", asList(nodeA), asList(4));
        var nodeC = new GraphNode<>("C", asList(nodeA), asList(5));
        var nodeD = new GraphNode<>("D", asList(nodeA), asList(17));
        var nodeE = new GraphNode<>("E", asList(nodeB), asList(5));
        var nodeF = new GraphNode<>("F", asList(nodeB), asList(12));
        var nodeG = new GraphNode<>("G", asList(nodeB), asList(16));
        var nodeH = new GraphNode<>("H", asList(nodeC,nodeD), asList(11,1));
        var nodeI = new GraphNode<>("I", asList(nodeC,nodeG), asList(21,4));
        var nodeJ = new GraphNode<>("J", asList(nodeC,nodeH), asList(19,3));
        return nodeA;
    }

    public static GraphNode<String> generateExampleGraph8() {
        var nodeA = new GraphNode<>("A", asList(), asList());
        var nodeB = new GraphNode<>("B", asList(nodeA), asList(4));
        var nodeC = new GraphNode<>("C", asList(nodeB), asList(5));
        var nodeD = new GraphNode<>("D", asList(nodeC), asList(17));
        var nodeE = new GraphNode<>("E", asList(nodeD), asList(5));
        var nodeF = new GraphNode<>("F", asList(nodeC,nodeE), asList(12,2));
        return nodeA;
    }
}
// CODE TEMPLATE
// ... value ... neighbours ... neighboursCosts ...
