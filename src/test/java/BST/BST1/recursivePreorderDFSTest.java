package BST.BST1;

import Exams.BST.BST1.BinaryTreeNode;
import org.junit.Test;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class recursivePreorderDFSTest {

    @Test
    public void testSimpleTree() {
        // Tree:
        //      A
        //     / \
        //    B   C
        BinaryTreeNode<String> root =
                new BinaryTreeNode<>("A",
                        new BinaryTreeNode<>("B", null, null),
                        new BinaryTreeNode<>("C", null, null));

        List<String> result = BinaryTreeNode.recursivePreorderDFS(root);
        assertEquals(Arrays.asList("A", "B", "C"), result);
    }

    @Test
    public void testLeftSkewedTree() {
        // Tree:
        //     A
        //    /
        //   B
        //  /
        // C
        BinaryTreeNode<String> root =
                new BinaryTreeNode<>("A",
                        new BinaryTreeNode<>("B",
                                new BinaryTreeNode<>("C", null, null),
                                null),
                        null);

        List<String> result = BinaryTreeNode.recursivePreorderDFS(root);
        assertEquals(Arrays.asList("A", "B", "C"), result);
    }

    @Test
    public void testRightSkewedTree() {
        // Tree:
        // A
        //  \
        //   B
        //    \
        //     C
        BinaryTreeNode<String> root =
                new BinaryTreeNode<>("A", null,
                        new BinaryTreeNode<>("B", null,
                                new BinaryTreeNode<>("C", null, null)));

        List<String> result = BinaryTreeNode.recursivePreorderDFS(root);
        assertEquals(Arrays.asList("A", "B", "C"), result);
    }

    @Test
    public void testEmptyTree() {
        assertNull(BinaryTreeNode.recursivePreorderDFS(null));
    }
}
