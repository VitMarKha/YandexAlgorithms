package org.fngoc.sprint_2;

/*
 * C. Нелюбимое дело
 */

public class UnlovedBusiness {

    private static class Node<V> {
        public V value;
        public Node<V> next;

        public Node(V value, Node<V> next) {
            this.value = value;
            this.next = next;
        }
    }

    public static Node<String> unlovedBusiness(Node<String> head, int idx) {
        Node<String> pointer = head;
        int delete = 1;

        if (idx == 0)
            return head.next;

        if (idx == lenList(head))
            delete = 2;

        for (int i = 0; i < idx - delete; i++)
            pointer = pointer.next;

        pointer.next = pointer.next.next;
        return head;
    }

    private static int lenList(Node<String> head) {
        int len = 0;

        while (head != null) {
            head = head.next;
            len += 1;
        }
        return len;
    }
}
