package BST.BSTZig;

import Exams.BST.BST1.BinaryTreeNode;
import Exams.BST.BST2.BSTZig;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;

public class BSTZigTest {

    // Helper: build a simple binary tree easily
    private BinaryTreeNode<Integer> node(Integer val, BinaryTreeNode<Integer> left, BinaryTreeNode<Integer> right) {
        return new BinaryTreeNode<>(val, left, right);
    }

    /**
     * Example tree:
     *        1
     *       / \
     *      2   3
     *     / \   \
     *    4  5    6
     *
     * Expected Zigzag: [[1], [3, 2], [4, 5, 6]]
     */
    @Test
    public void testBasicExample() {
        BinaryTreeNode<Integer> root =
                node(1,
                        node(2, node(4, null, null), node(5, null, null)),
                        node(3, null, node(6, null, null))
                );

        List<List<Integer>> result = BSTZig.zigzagLevelOrder(root);

        List<List<Integer>> expected = Arrays.asList(
                Arrays.asList(1),
                Arrays.asList(3, 2),
                Arrays.asList(4, 5, 6)
        );

        assertEquals(expected, result);
    }

    /**
     * Single node tree → only one level.
     * Expected: [[42]]
     */
    @Test
    public void testSingleNode() {
        BinaryTreeNode<Integer> root = node(42, null, null);

        List<List<Integer>> result = BSTZig.zigzagLevelOrder(root);

        assertEquals(Arrays.asList(Arrays.asList(42)), result);
    }

    /**
     * Empty tree → empty list.
     */
    @Test
    public void testEmptyTree() {
        List<List<Integer>> result = BSTZig.zigzagLevelOrder(null);
        assertTrue(result.isEmpty());
    }

    /**
     * Tree with two levels only.
     *       10
     *      /  \
     *     5    20
     *
     * Expected Zigzag: [[10], [20, 5]]
     */
    @Test
    public void testTwoLevels() {
        BinaryTreeNode<Integer> root = node(10, node(5, null, null), node(20, null, null));

        List<List<Integer>> result = BSTZig.zigzagLevelOrder(root);

        List<List<Integer>> expected = Arrays.asList(
                Arrays.asList(10),
                Arrays.asList(20, 5)
        );
        assertEquals(expected, result);
    }

    /**
     * Left skewed tree:
     *     1
     *    /
     *   2
     *  /
     * 3
     * Expected Zigzag: [[1], [2], [3]]
     */
    @Test
    public void testLeftSkewedTree() {
        BinaryTreeNode<Integer> root = node(1, node(2, node(3, null, null), null), null);

        List<List<Integer>> result = BSTZig.zigzagLevelOrder(root);

        List<List<Integer>> expected = Arrays.asList(
                Arrays.asList(1),
                Arrays.asList(2),
                Arrays.asList(3)
        );
        assertEquals(expected, result);
    }

    /**
     * Right skewed tree:
     * 1
     *  \
     *   2
     *    \
     *     3
     * Expected Zigzag: [[1], [2], [3]]
     */
    @Test
    public void testRightSkewedTree() {
        BinaryTreeNode<Integer> root = node(1, null, node(2, null, node(3, null, null)));

        List<List<Integer>> result = BSTZig.zigzagLevelOrder(root);

        List<List<Integer>> expected = Arrays.asList(
                Arrays.asList(1),
                Arrays.asList(2),
                Arrays.asList(3)
        );
        assertEquals(expected, result);
    }

    /**
     * Mixed larger tree for stress test.
     *           10
     *          /  \
     *         6    15
     *        / \   / \
     *       3  8  12  18
     *          \      /
     *           9    16
     *
     * Zigzag expected:
     * [[10], [15, 6], [3, 8, 12, 18], [16, 9]]
     */
    @Test
    public void testLargerTree() {
        BinaryTreeNode<Integer> root =
                node(10,
                        node(6, node(3, null, null), node(8, null, node(9, null, null))),
                        node(15, node(12, null, null), node(18, node(16, null, null), null))
                );

        List<List<Integer>> result = BSTZig.zigzagLevelOrder(root);

        List<List<Integer>> expected = Arrays.asList(
                Arrays.asList(10),
                Arrays.asList(15, 6),
                Arrays.asList(3, 8, 12, 18),
                Arrays.asList(16, 9)
        );
        assertEquals(expected, result);
    }
}
