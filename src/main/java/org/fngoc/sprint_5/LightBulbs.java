package org.fngoc.sprint_5;

/*
 * A. Лампочки
 */

public class LightBulbs {

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

    public static int lightBulbs(Node head) {
        if (head.left != null && head.right != null)
            return Math.max(head.value, Math.max(lightBulbs(head.left), lightBulbs(head.right)));
        else if (head.left != null)
            return Math.max(head.value, lightBulbs(head.left));
        else if (head.right != null)
            return Math.max(head.value, lightBulbs(head.right));
        return head.value;
    }
}
