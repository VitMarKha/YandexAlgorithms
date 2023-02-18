package org.fngoc.sprint_2;

/*
 * B. Список дел
 */

public class TodoList {

    private static class Node<V> {
        public V value;
        public Node<V> next;

        public Node(V value, Node<V> next) {
            this.value = value;
            this.next = next;
        }
    }

    public static void todoList(Node<String> head) {
        while (head != null) {
            System.out.println(head.value);
            head = head.next;
        }
    }
}
