package Exams.BST.BST1;

import java.util.List;

/**
 * ... Application of design recipe goes here ...
 * @param <T>
 */

public class BinaryTreeNode<T> {
    T value;
    BinaryTreeNode<T> left;
    BinaryTreeNode<T> right;
    public BinaryTreeNode(T value, BinaryTreeNode<T> left, BinaryTreeNode<T> right) {
        this.value = value;
        this.left = left;
        this.right = right;
    }

    public static <T> List<T> recursivePreorderDFS(BinaryTreeNode<T> root) {
        return null;
    }

    public static<T> List<List<T>> iterativeBFS(BinaryTreeNode<T> root) {
        // this queue is going to help us in preserving the
        // BFS traversal order
        return null;
    }

}
