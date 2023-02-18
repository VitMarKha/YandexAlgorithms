package org.fngoc.sprint_5;

import java.io.BufferedWriter;
import java.io.IOException;

/*
 * K. Выведи диапазон
 */

public class PrintRange {

    private static class Node {
        private int value;
        private Node left;
        private Node right;

        Node(Node left, Node right, int value) {
            this.left = left;
            this.right = right;
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        public Node getRight() {
            return right;
        }

        public void setRight(Node right) {
            this.right = right;
        }

        public Node getLeft() {
            return left;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public void setValue(int value) {
            this.value = value;
        }
    }

    public static void printRange(Node root, int L, int R, BufferedWriter writer) throws IOException {
        if (root == null)
            return;

        if (root.getLeft() != null)
            printRange(root.getLeft(), L, R, writer);

        if (inRange(root.getValue(), L, R))
            writer.append(String.valueOf(root.getValue())).append("\n");

        if (root.getRight() != null)
            printRange(root.getRight(), L, R, writer);
    }

    private static boolean inRange(int value, int L, int R) {
        return L <= value && value <= R;
    }
}
