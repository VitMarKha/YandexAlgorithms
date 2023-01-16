package org.fngoc.finaltasks.sprintfive;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

    public static void main(String[] args) {
        Node node10 = new Node(null, null, 99);
        Node node9 = new Node(null, null, 72);
        Node node8 = new Node(node9, node10, 91);
        Node node7 = new Node(null, null, 50);
        Node node6 = new Node(null, null, 32);
        Node node5 = new Node(null, node6, 29);
        Node node4 = new Node(null, null, 11);
        Node node3 = new Node(node7, node8, 65);
        Node node2 = new Node(node4, node5, 20);
        Node node1 = new Node(node2, node3, 41);

        BTreePrinter.printNode(node1);
        BTreePrinter.printNode(remove(node1, 41));
    }

    private static Node parentPointer;

    public static Node remove(Node root, int key) {
        parentPointer = null;
        Node deleteNode = searchNode(root, key);

        if (deleteNode == null)
            return root;

        if (deleteNode.getLeft() == null && deleteNode.getRight() == null) {
            if (deleteNode != root)
                deleting(deleteNode, null);
            else
                root = null;
        } else if (deleteNode.getLeft() != null && deleteNode.getRight() != null) {
            Node mostLeftNodePointer = getMostLeftNode(deleteNode.getRight());

            int tmp = mostLeftNodePointer.getValue();
            remove(root, tmp);
            deleteNode.setValue(tmp);
        } else {
            Node pointerTmp;

            if (deleteNode.getLeft() != null)
                pointerTmp = deleteNode.getLeft();
            else
                pointerTmp = deleteNode.getRight();

            if (deleteNode != root)
                deleting(deleteNode, pointerTmp);
            else
                root = pointerTmp;
        }
        return root;
    }

    private static void deleting(Node deleteNode, Node node) {
        if (deleteNode == parentPointer.getLeft())
            parentPointer.setLeft(node);
        else
            parentPointer.setRight(node);
    }

    private static Node searchNode(Node root, int key) {
        if (root == null || key == root.getValue())
            return root;

        parentPointer = root;
        if (key < root.getValue())
            return searchNode(root.getLeft(), key);
        else
            return searchNode(root.getRight(), key);
    }

    private static Node getMostLeftNode(Node root) {
        if (root.getLeft() == null)
            return root;
        return getMostLeftNode(root.getLeft());
    }

    private static class BTreePrinter {

        public static void printNode(Node root) {
            int maxLevel = BTreePrinter.maxLevel(root);
            printNodeInternal(Collections.singletonList(root), 1, maxLevel);
        }

        private static void printNodeInternal(List<Node> nodes, int level, int maxLevel) {
            if (nodes.isEmpty() || BTreePrinter.isAllElementsNull(nodes))
                return;

            int floor = maxLevel - level;
            int endgeLines = (int) Math.pow(2, (Math.max(floor - 1, 0)));
            int firstSpaces = (int) Math.pow(2, (floor)) - 1;
            int betweenSpaces = (int) Math.pow(2, (floor + 1)) - 1;

            BTreePrinter.printWhitespaces(firstSpaces);

            List<Node> newNodes = new ArrayList<>();
            for (Node node : nodes) {
                if (node != null) {
                    System.out.print(node.value);
                    newNodes.add(node.left);
                    newNodes.add(node.right);
                } else {
                    newNodes.add(null);
                    newNodes.add(null);
                    System.out.print(" ");
                }
                BTreePrinter.printWhitespaces(betweenSpaces);
            }
            System.out.println("");

            for (int i = 1; i <= endgeLines; i++) {
                for (int j = 0; j < nodes.size(); j++) {
                    BTreePrinter.printWhitespaces(firstSpaces - i);
                    if (nodes.get(j) == null) {
                        BTreePrinter.printWhitespaces(endgeLines + endgeLines + i + 1);
                        continue;
                    }

                    if (nodes.get(j).left != null)
                        System.out.print("/");
                    else
                        BTreePrinter.printWhitespaces(1);

                    BTreePrinter.printWhitespaces(i + i - 1);

                    if (nodes.get(j).right != null)
                        System.out.print("\\");
                    else
                        BTreePrinter.printWhitespaces(1);

                    BTreePrinter.printWhitespaces(endgeLines + endgeLines - i);
                }
                System.out.println("");
            }
            printNodeInternal(newNodes, level + 1, maxLevel);
        }

        private static void printWhitespaces(int count) {
            for (int i = 0; i < count; i++)
                System.out.print(" ");
        }

        private static <T extends Comparable<?>> int maxLevel(Node node) {
            if (node == null)
                return 0;
            return Math.max(BTreePrinter.maxLevel(node.left), BTreePrinter.maxLevel(node.right)) + 1;
        }

        private static <T> boolean isAllElementsNull(List<T> list) {
            for (Object object : list) {
                if (object != null)
                    return false;
            }
            return true;
        }
    }
}
