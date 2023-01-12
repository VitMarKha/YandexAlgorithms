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

    private static boolean flag;
    private static Node relativeNodeRemoved;

    public static Node remove(Node root, int key) {
        Node head = root;
        deleteNode(root, key);
        return head;
    }

    private static void deleteNode(Node root, int key) {
        if (root == null)
            return;

        if (key < root.getValue()) {
            relativeNodeRemoved = root;
            flag = false;
            deleteNode(root.getLeft(), key);
        } else if (key > root.getValue()) {
            flag = true;
            relativeNodeRemoved = root;
            deleteNode(root.getRight(), key);
        }

        if (root.getValue() == key) {

            if (root.getLeft() == null && root.getLeft() == null)
                root = null;
            else if (root.getLeft() != null && root.getRight() == null) {
                if (flag)
                    relativeNodeRemoved.setRight(root.getLeft());
                else
                    relativeNodeRemoved.setLeft(root.getLeft());
            } else if (root.getLeft() == null && root.getRight() != null) {
                if (flag)
                    relativeNodeRemoved.setRight(root.getRight());
                else
                    relativeNodeRemoved.setLeft(root.getRight());
            } else if (root.getLeft() != null && root.getRight() != null) {
                Node node = getLeftMost(root.getRight());

                if (node.getRight() != null)
                    relativeNodeRemoved.setLeft(root.getRight());

                node.setLeft(root.getLeft());
                node.setRight(root.getRight());
                root = null;

                if (key < relativeNodeRemoved.getValue())
                    relativeNodeRemoved.setLeft(node);
                else
                    relativeNodeRemoved.setRight(node);
            }
        }
    }

    private static Node getLeftMost(Node root) {
        if (root.getLeft() == null)
            return root;
        return getLeftMost(root.getLeft());
    }

    private static Node getRightMost(Node root) {
        if (root.getRight() == null)
            return root;
        return getRightMost(root.getRight());
    }
}
