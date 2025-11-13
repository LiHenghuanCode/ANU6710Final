package BST.NaryTreeTraversalTest;

import Exams.BST.MutiBST.NaryTreeNode;
import Exams.BST.MutiBST.NaryTreeTraversal;
import org.junit.Test;
import java.util.*;

import static org.junit.Assert.*;

public class NaryTreeTraversalTest {

    /**
     * root = null → return empty list
     */
    @Test
    public void testNullRoot() {
        List<List<Integer>> result =
                NaryTreeTraversal.levelOrderTraversal(null);

        assertTrue(result.isEmpty());
    }

    /**
     * Single node only
     *      A
     */
    @Test
    public void testSingleNode() {
        NaryTreeNode<String> root = new NaryTreeNode<>("A");

        List<List<String>> result =
                NaryTreeTraversal.levelOrderTraversal(root);

        assertEquals(1, result.size());
        assertEquals(Arrays.asList("A"), result.get(0));
    }

    /**
     * Example from the problem:
     *
     *          A
     *       /  |  \
     *      B   C   D
     *         / \
     *        E   F
     *
     * Expected: [[A], [B, C, D], [E, F]]
     */
    @Test
    public void testNormalTree() {
        NaryTreeNode<String> e = new NaryTreeNode<>("E");
        NaryTreeNode<String> f = new NaryTreeNode<>("F");
        NaryTreeNode<String> b = new NaryTreeNode<>("B");
        NaryTreeNode<String> c = new NaryTreeNode<>("C", Arrays.asList(e, f));
        NaryTreeNode<String> d = new NaryTreeNode<>("D");

        NaryTreeNode<String> root =
                new NaryTreeNode<>("A", Arrays.asList(b, c, d));

        List<List<String>> result =
                NaryTreeTraversal.levelOrderTraversal(root);

        List<List<String>> expected = Arrays.asList(
                Arrays.asList("A"),
                Arrays.asList("B", "C", "D"),
                Arrays.asList("E", "F")
        );

        assertEquals(expected, result);
    }

    /**
     * Tree with uneven children count
     *
     *        1
     *      / | \
     *     2  3  4
     *       / \
     *      5   6
     *         /
     *        7
     */
    @Test
    public void testUnbalanced() {
        NaryTreeNode<Integer> n7 = new NaryTreeNode<>(7);
        NaryTreeNode<Integer> n6 = new NaryTreeNode<>(6, Arrays.asList(n7));
        NaryTreeNode<Integer> n5 = new NaryTreeNode<>(5);
        NaryTreeNode<Integer> n3 = new NaryTreeNode<>(3, Arrays.asList(n5, n6));
        NaryTreeNode<Integer> n2 = new NaryTreeNode<>(2);
        NaryTreeNode<Integer> n4 = new NaryTreeNode<>(4);

        NaryTreeNode<Integer> root =
                new NaryTreeNode<>(1, Arrays.asList(n2, n3, n4));

        List<List<Integer>> result =
                NaryTreeTraversal.levelOrderTraversal(root);

        List<List<Integer>> expected = Arrays.asList(
                Arrays.asList(1),
                Arrays.asList(2, 3, 4),
                Arrays.asList(5, 6),
                Arrays.asList(7)
        );

        assertEquals(expected, result);
    }

    /**
     * Tree with one child per node (a linked list)
     *
     *    A
     *    |
     *    B
     *    |
     *    C
     */
    @Test
    public void testDeepChain() {
        NaryTreeNode<String> c = new NaryTreeNode<>("C");
        NaryTreeNode<String> b = new NaryTreeNode<>("B", Arrays.asList(c));
        NaryTreeNode<String> a = new NaryTreeNode<>("A", Arrays.asList(b));

        List<List<String>> result =
                NaryTreeTraversal.levelOrderTraversal(a);

        List<List<String>> expected = Arrays.asList(
                Arrays.asList("A"),
                Arrays.asList("B"),
                Arrays.asList("C")
        );

        assertEquals(expected, result);
    }
}
