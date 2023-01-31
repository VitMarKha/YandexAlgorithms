package org.fngoc.finaltasks.sprint_5;

/*
-- ПРИНЦИП РАБОТЫ --
Реализовал функцию удаления ноды из бинарного дерева поиска.

Проход по дереву происходит рекурсивно, поиск удаляемого и
самого левого элемента тоже рекурсивно.

Есть 3 основных варианта исполнения программы.
1)Если удаляемый узел не имеет потомков.
2)Удаляемый узел имеет два потомка.
3)У удаляемого узла один потомок.

-- ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ --
1)Если у узла нет потомков - зануляем ноду в зависимости от parentPointer.
2)Если есть 2 узла - то мы рекурсивно находим самый левый элемент правого дочернего узла.
Так как самый левый узел либо не имеет узлов, либо имеет только один узел,
мы запускаем функцию удаления узла рекурсивно, попадая в 3 пункт.
3)Если есть только один потомок - устанавливаю на место удаляемой ноды его потомка.

Если удаляемая нода является головой без потомков, возвращаем null.

-- ВРЕМЕННАЯ СЛОЖНОСТЬ --
Так как каждый раз при поиске элемента (или при поиске самого левого элемента)
мы спускаемся по дереву вниз, то в среднем случае скорость поиска может быть
O(h), где h - высота дерева.

Само удаление происходит за O(1). В худшем случае если дерево лист,
удаление будет происходить за O(N), где N - кол-во элементов.
При сбалансированном дереве, среднее выполнение функции O(h).

-- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
N - кол-во нод в дереве.

Дерево занимает O(N) постоянной памяти, при удалении
ноды нам потребуется O(logN)`дополнительной памяти на рекурсии
при условии, что дерево сбалансировано. В худшем случае,
если дерево является листом, на рекурсии мы можем потратить O(N).

Постоянная средняя пространственная сложность программы O(N) + O(logN).
*/

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
        if (deleteNode.getValue() < parentPointer.getValue())
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
}
