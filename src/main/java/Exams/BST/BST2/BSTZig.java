package Exams.BST.BST2;

import Exams.BST.BST1.BinaryTreeNode;

import java.util.*;

/**
 * Performs a Zigzag (Z-shaped) level order traversal
 * on a binary tree rooted at {@code root}.
 *
 * In a normal level order (BFS) traversal, nodes
 * are visited level by level from left to right.
 * In Zigzag order, the direction alternates at each level:
 * - Level 0: left → right
 * - Level 1: right → left
 * - Level 2: left → right
 * - and so on...
 *
 * Example:
 *          1
 *         / \
 *        2   3
 *       / \   \
 *      4   5   6
 *
 * Normal BFS: [[1], [2, 3], [4, 5, 6]]
 * Zigzag BFS: [[1], [3, 2], [4, 5, 6]]
 *
 * @param <T> the type of node values
 */
public class BSTZig {

    /**
     * Performs Zigzag level order traversal.
     *
     * @param root the root of the binary tree
     * @return a list of lists of node values, each representing one level,
     *         with traversal order alternating per level
     */
    public static <T> List<List<T>> zigzagLevelOrder(BinaryTreeNode<T> root) {
        // TODO: implement
        return null;
    }
}

