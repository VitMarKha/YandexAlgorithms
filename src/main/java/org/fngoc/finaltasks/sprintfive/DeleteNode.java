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

    private static void test() {
//        Node node1 = new Node(null, null, 2);
//        Node node2 = new Node(node1, null, 3);
//        Node node3 = new Node(null, node2, 1);
//        Node node4 = new Node(null, null, 6);
//        Node node5 = new Node(node4, null, 8);
//        Node node6 = new Node(node5, null, 10);
//        Node node7 = new Node(node3, node6, 5);
//        Node newHead = remove(node7, 10);
//        System.out.println(newHead.getValue() == 5);
//        System.out.println(newHead.getRight() == node5);
//        System.out.println(newHead.getRight().getValue() == 8);

//        Node node10 = new Node(null, null, 8);
//        Node node9 = new Node(null, null, 6);
//
//        Node node8 = new Node(null, null, 4);
//        Node node7 = new Node(node9, node10, 7);
//
//        Node node6 = new Node(null, null, 10);
//        Node node5 = new Node(node8, node7, 5);
//
//        Node node4 = new Node(null, null, 1);
//        Node node2 = new Node(null, node4, 1);
//        Node node3 = new Node(node5, node6, 9);
//        Node node1 = new Node(node2, node3, 3);

//        Node node2 = new Node(null, null, 2);
//        Node node1 = new Node(null, node2, 1);

//        Node node3 = new Node(null, null, 7);
//        Node node2 = new Node(null, node3,5);
//        Node node1 = new Node(node2, null,10);

//        BTreePrinter.printNode(node1);
//        BTreePrinter.printNode(remove(node1, 3));
//        BTreePrinter.printNode(remove(node1, 6));
//        BTreePrinter.printNode(remove(node1, 4));

//        BTreePrinter.printNode(remove(node1, 4));
//        BTreePrinter.printNode(remove(node1, 5));
//        BTreePrinter.printNode(remove(node1, 3));
//        BTreePrinter.printNode(remove(node1, 6));
//        BTreePrinter.printNode(remove(node1, 7));
//        BTreePrinter.printNode(remove(node1, 8));
//        BTreePrinter.printNode(remove(node1, 9));
//        BTreePrinter.printNode(remove(node1, 10));
//        10
//        1 41 2 3
//        2 20 4 5
//        3 65 7 8
//        4 11 -1 -1
//        5 29 -1 6
//        6 32 -1 -1
//        7 50 -1 -1
//        8 91 9 10
//        9 72 -1 -1
//        10 99 -1 -1
//        41
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
//        BTreePrinter.printNode(remove(node1, 50));
//        BTreePrinter.printNode(remove(node1, 65));
//        BTreePrinter.printNode(remove(node1, 72));
//        BTreePrinter.printNode(remove(node1, 91));
//        BTreePrinter.printNode(remove(node1, 20));
//        BTreePrinter.printNode(remove(node1, 41));
//        BTreePrinter.printNode(remove(node1, 50));
//        BTreePrinter.printNode(remove(node1, 99));
//        BTreePrinter.printNode(remove(node1, 29));
    }

    public static void main(String[] args) {
        test();
    }

    private static boolean flagTurn;
    private static Node parent;
    private static Node head;

    public static Node remove(Node root, int key) {
        head = root;
        parent = root;

        deleteNode(searchNode(root, key));
        return head;
    }

    private static Node searchNode(Node root, int key) {
        if (root == null || key == root.getValue())
            return root;

        parent = root;
        if (key < root.getValue()) {
            flagTurn = false;
            return searchNode(root.getLeft(), key);
        }
        else {
            flagTurn = true;
            return searchNode(root.getRight(), key);
        }
    }

    private static void deleteNode(Node deleteRoot) {
        if (deleteRoot == null)
            return;

        if (deleteRoot.getLeft() == null && deleteRoot.getRight() == null)
            deleting(deleteRoot,null);
        else if (deleteRoot.getLeft() == null)
            deleting(deleteRoot, deleteRoot.getRight());
        else if (deleteRoot.getRight() == null)
            deleting(deleteRoot, deleteRoot.getLeft());
        else
            deletingWithTwoRoot(deleteRoot);
    }

    private static void deletingWithTwoRoot(Node deleteRoot) {
        Node mostLeftNode = getMostLeftNode(deleteRoot);

        if (mostLeftNode.getLeft() == null && mostLeftNode.getRight() == null) {
            deleteRoot.setValue(mostLeftNode.getValue());

            if (parent.getValue() > mostLeftNode.getValue())
                parent.setLeft(null);
            else
                parent.setRight(null);
        } else {
            deleteRoot.setValue(mostLeftNode.getValue());

            if (parent.getValue() < mostLeftNode.getValue())
                parent.setLeft(mostLeftNode.getRight());
            else
                parent.setRight(mostLeftNode.getRight());
        }
    }

    private static void deleting(Node deleteRoot, Node node) {
        if (head.getValue() == deleteRoot.getValue()) {
            if (head.getLeft() != null)
                head = head.getLeft();
            else
                head = head.getRight();
        } else {
            if (!flagTurn)
                parent.setLeft(node);
            else
                parent.setRight(node);
        }
    }

    private static Node getMostLeftNode(Node root) {
        parent = root;
        Node iterator = root.getRight();
        while (true) {
            if (iterator.getLeft() == null)
                return iterator;
            parent = iterator;
            iterator = iterator.getLeft();
        }
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
