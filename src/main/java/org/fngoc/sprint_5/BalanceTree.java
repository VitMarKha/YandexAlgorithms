package org.fngoc.sprint_5;

/*
 * B. Сбалансированное дерево
 */

public class BalanceTree {

    private static class Node {
        int value;
        Node left;
        Node right;

        Node(int value) {
            this.value = value;
            this.left = null;
            this.right = null;
        }
    }

    private static boolean flag = true;

    public static boolean isBalanceTree(Node head) {
        recursiveIsBalanceTree(head);
        return flag;
    }

    private static int recursiveIsBalanceTree(Node root) {
        if (root == null || !flag)
            return 0;

        int leftRootCount = recursiveIsBalanceTree(root.left);
        int rightRootCount = recursiveIsBalanceTree(root.right);

        if (Math.abs(leftRootCount - rightRootCount) > 1)
            flag = false;

        return Math.max(leftRootCount, rightRootCount) + 1;
    }
}
