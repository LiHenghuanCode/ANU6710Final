package Exams.BST.MutiBST;

import java.util.*;

/**
 * Represents a generic N-ary tree node.
 * @param <T> The type of the value stored in the node.
 */
public class NaryTreeNode<T> {
    T value;
    List<NaryTreeNode<T>> children;

    public NaryTreeNode(T value) {
        this.value = value;
        this.children = new ArrayList<>();
    }

    public NaryTreeNode(T value, List<NaryTreeNode<T>> children) {
        this.value = value;
        this.children = children;
    }
}

