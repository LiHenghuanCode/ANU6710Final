package Exams.Graphic.Example;
import java.util.*;

public class GraphNode<T> {
    T value;
    List<GraphNode<T>> neighbours;

    GraphNode(T value) {
        this.value = value;
        this.neighbours = new ArrayList<>();
    }

    T getValue() {
        return value;
    }

    List<GraphNode<T>> getNeighbours() {
        return neighbours;
    }

    void addNeighbour(GraphNode<T> neighbour) {
        this.neighbours.add(neighbour);
    }

    public static <T> Map<GraphNode<T>,GraphNode<T>> iterativeBFS(GraphNode<T> initialVertex)
    {
        Queue<GraphNode<T>> pending = new LinkedList<>();
        Set<GraphNode<T>> touched = new HashSet<>();
        Map<GraphNode<T>,GraphNode<T>> visitedFrom = new HashMap<>();


        pending.add(initialVertex);
        touched.add(initialVertex);
        visitedFrom.put(initialVertex, null);

        // Loop termination argument goes here!
        while (!pending.isEmpty())
        {
            var current = pending.poll();
            System.out.print(current.getValue() + " ");
            for (var neighbour : current.getNeighbours()) {
                if (!touched.contains(neighbour)) {
                    pending.add(neighbour);
                    touched.add(neighbour);
                    visitedFrom.put(neighbour, current);
                }
            }
        }
        System.out.println();
        return visitedFrom;
    }

    public static <T> List<GraphNode<T>> aMinimalPath(
            Map<GraphNode<T>,GraphNode<T>> visitedFrom,
            GraphNode<T> node) {
        List<GraphNode<T>> path = new ArrayList<>();

        while (visitedFrom.get(node) != null) {
            path.add(node);
            node = visitedFrom.get(node);
        }
        path.add(node);
        return path.reversed();
    }

    public static void main(String[] args) {
        // Let us first create the nodes
        var node0 = new GraphNode<Integer>(0);
        var node1 = new GraphNode<Integer>(1);
        var node2 = new GraphNode<Integer>(2);
        var node3 = new GraphNode<Integer>(3);
        var node4 = new GraphNode<Integer>(4);
        var node5 = new GraphNode<Integer>(5);
        var node6 = new GraphNode<Integer>(6);
        var node7 = new GraphNode<Integer>(7);

        // Define adjacencies among nodes

        // Node 0 neighbours
        node0.addNeighbour(node1);
        node0.addNeighbour(node2);
        node0.addNeighbour(node3);

        // Node 1 neighbours
        node1.addNeighbour(node2);
        node1.addNeighbour(node5);

        // Node 2 neighbours
        node2.addNeighbour(node0);
        node2.addNeighbour(node4);

        // Node 3 neighbours
        node3.addNeighbour(node5);
        node3.addNeighbour(node7);

        // Node 4 neighbours
        node4.addNeighbour(node6);

        // Node 5 neighbours
        node5.addNeighbour(node1);

        // Node 6 neighbours
        node6.addNeighbour(node2);
        node6.addNeighbour(node4);
        node6.addNeighbour(node5);

        // Node 7 neighbours
        node7.addNeighbour(node5);

        /*
        for (var key : visitedFrom.keySet()) {
            if (visitedFrom.get(key)!=null) {
                System.out.println("Node " + key.getValue() + " visited from " + visitedFrom.get(key).getValue());
            }
        }
        */

        var visitedFrom = iterativeBFS(node0);
        var path = aMinimalPath(visitedFrom, node6);
        for (var node : path) {
            if (node != path.getLast()){
                System.out.print(node.getValue() + "->" );
            }
            else {
                System.out.println(node.getValue());
            }
        }

    }
}

