package org.fngoc.sprint_5;

/*
 * J. Добавь узел
 */

public class InsertNode {

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

    public static Node insert(Node root, int key) {
        Node head = root;
        recurseInsert(root, key);
        return head;
    }

    private static void recurseInsert(Node root, int key) {
        if (root == null)
            return;

        if (key < root.getValue()) {
            if (root.getLeft() == null)
                root.setLeft(new Node(null, null, key));
            else
                recurseInsert(root.getLeft(), key);
        }

        if (key >= root.getValue()) {
            if (root.getRight() == null)
                root.setRight(new Node(null, null, key));
            else
                recurseInsert(root.getRight(), key);
        }
    }
}
