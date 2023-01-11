package org.fngoc.finaltasks.sprintfive;

public class DeleteNode {

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

    private static void test() {
        Node node1 = new Node(null, null, 2);
        Node node2 = new Node(node1, null, 3);
        Node node3 = new Node(null, node2, 1);
        Node node4 = new Node(null, null, 6);
        Node node5 = new Node(node4, null, 8);
        Node node6 = new Node(node5, null, 10);
        Node node7 = new Node(node3, node6, 5);
        Node newHead = remove(node7, 10);
        System.out.println(newHead.getValue() == 5);
        System.out.println(newHead.getRight() == node5);
        System.out.println(newHead.getRight().getValue() == 8);
    }

    public static void main(String[] args) {
        test();
    }

    private static Node relative;
    private static Node relativeFind;

    public static Node remove(Node root, int key) {
        Node head = root;
        deleteNode(root, key);
        return head;
    }

    private static void deleteNode(Node root, int key) {
        if (root == null)
            return;

        if (key < root.getValue()) {
            relative = root;
            deleteNode(root.getLeft(), key);
        } else if (key > root.getValue()) {
            relative = root;
            deleteNode(root.getRight(), key);
        }

        if (root.getValue() == key) {
            Node node = getRightMost(root.getLeft());

            if (node.getLeft() != null)
                relativeFind.setRight(node.getLeft());

            node.setLeft(root.getLeft());
            node.setRight(root.getRight());
            root = null;
            if (key < relative.getValue())
                relative.setLeft(node);
            else
                relative.setRight(node);
        }
    }

    private static Node getRightMost(Node root) {
        if (root.getRight() == null) {
            relativeFind = root;
            Node tmp = root;
            root = null;
            return tmp;
        }
        return getRightMost(root.getRight());
    }
}
