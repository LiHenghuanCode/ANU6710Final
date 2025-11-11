package BST.BST2;

import Exams.BST.BST1.BinaryTreeNode;
import org.junit.Test;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class iterativeBFSTest {

    @Test
    public void testBasicTree() {
        // Tree:
        //      1
        //     / \
        //    2   3
        //   / \
        //  4   5
        BinaryTreeNode<Integer> root =
                new BinaryTreeNode<>(1,
                        new BinaryTreeNode<>(2,
                                new BinaryTreeNode<>(4, null, null),
                                new BinaryTreeNode<>(5, null, null)),
                        new BinaryTreeNode<>(3, null, null));

        List<List<Integer>> result = BinaryTreeNode.iterativeBFS(root);
        assertEquals(Arrays.asList(
                Arrays.asList(1),
                Arrays.asList(2, 3),
                Arrays.asList(4, 5)
        ), result);
    }

    @Test
    public void testSingleNode() {
        BinaryTreeNode<String> root = new BinaryTreeNode<>("A", null, null);
        List<List<String>> result = BinaryTreeNode.iterativeBFS(root);
        assertEquals(Arrays.asList(Arrays.asList("A")), result);
    }

    @Test
    public void testEmptyTree() {
        assertNull(BinaryTreeNode.iterativeBFS(null));
    }

    @Test
    public void testUnbalancedTree() {
        // Tree:
        //    A
        //   / \
        //  B   C
        //     /
        //    D
        BinaryTreeNode<String> root =
                new BinaryTreeNode<>("A",
                        new BinaryTreeNode<>("B", null, null),
                        new BinaryTreeNode<>("C",
                                new BinaryTreeNode<>("D", null, null),
                                null));

        List<List<String>> result = BinaryTreeNode.iterativeBFS(root);
        assertEquals(Arrays.asList(
                Arrays.asList("A"),
                Arrays.asList("B", "C"),
                Arrays.asList("D")
        ), result);
    }
}

