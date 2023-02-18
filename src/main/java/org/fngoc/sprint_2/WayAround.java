package org.fngoc.sprint_2;

/*
 * E. Всё наоборот
 */

public class WayAround {

    private static class Node<V> {
        public V value;
        public Node<V> next;
        public Node<V> prev;

        public Node(V value, Node<V> next, Node<V> prev) {
            this.value = value;
            this.next = next;
            this.prev = prev;
        }
    }

    public static Node<String> wayAround(Node<String> head) {
        Node<String> pointer = head;
        int len = 0;

        while (pointer.next != null) {
            pointer = pointer.next;
            len += 1;
        }

        Node<String> resutlt = new Node<>(pointer.value, null, null);
        Node<String> headResutlt = resutlt;
        for (int i = 0; i < len; i++) {

            if (pointer.prev != null)
                resutlt.next = new Node<>(pointer.prev.value, pointer.prev, pointer.next);
            if (pointer.next != null)
                resutlt.prev = new Node<>(pointer.next.value, pointer.next, pointer.prev);

            resutlt = resutlt.next;
            pointer = pointer.prev;
            if (i + 1 == len)
                resutlt.next = null;
        }
        return headResutlt;
    }
}
