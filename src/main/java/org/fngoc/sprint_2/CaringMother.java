package org.fngoc.sprint_2;

/*
 * D. Заботливая мама
 */

public class CaringMother {

    private static class Node<V> {
        public V value;
        public Node<V> next;

        public Node(V value, Node<V> next) {
            this.value = value;
            this.next = next;
        }
    }

    public static int caringMother(Node<String> head, String elem) {
        int index = 0;

        while (head != null) {
            if (head.value.equals(elem))
                return index;
            head = head.next;
            index += 1;
        }
        return -1;
    }
}
