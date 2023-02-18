package org.fngoc.sprint_5;

/*
 * E. Дерево поиска
 */

public class SearchTree {

    private static class Node {
        int value;
        Node left;
        Node right;

        Node(int value) {
            this.value = value;
            this.left = null;
            this.right = null;
        }

        Node(int value, Node left, Node right) {
            this.value = value;
            this.left = left;
            this.right = right;
        }
    }

    public static boolean searchTree(Node head) {
        return check(head, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private static boolean check(Node root, int min, int max) {
        if (root == null)
            return true;

        if (root.value <= min ||root.value >= max)
            return false;
        return check(root.left, min, root.value) && check(root.right, root.value, max);
    }
}
