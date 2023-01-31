package org.fngoc.finaltasks.sprint_2;
/*
-- ПРИНЦИП РАБОТЫ --
Я реализовал Dec в основе которого лежит кольцевой буфер.
Кольцевой буфер реализован на двусвязном списке.

При добавлении элемента я создаю новую ноду и пробрасываю на нее ссылки,
при удалении элемента я отвязываю ссылки от ноды и зануляю value,
после чего, в случае с Java, ее зачищает Garbage Collector.

-- ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ --
В static блоке я инициализирую ноды: голову и хвост,
которые начинают ссылаться друг на друга, их value = null.
Так же инициализирую текущий size = 0.

В каждой из команд есть проверка на size. При добавлении
проверяется не превышен ли maxSize, при удалении элемента
проверяется не равен ли текущий размер 0.

При push_back я устанавливаю в хвост значение value,
после чего создаю новую ноду с value = null, и теперь она является хвостом.
Меняю ссылки от головы к новому хвосту, что бы был кольцевой буфер.

По аналогии делаю с push_front, только теперь создаю новую ноду в голове
и меняю ссылки в хвосте на новую голову.

-- ВРЕМЕННАЯ СЛОЖНОСТЬ --
Добавление в Dec стоит всегда O(1), потому что при добавлении
мы лишь создаем новую ноду и пробрасываем ссылки.

Извлечение из Dec стоит всегда O(1), потому, что бы извлечь элемент,
нам достаточно перестать на него ссылаться.

-- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
Dec, содержащий k элементов, занимает O(k) памяти.

Почему я считаю что моя реализация Dec на двусвязном кольцевом буфере
более эффективна, чем на кольцевом массиве из примера в теоретических материалах:
    1. Нам не нужно сразу выделять много памяти если например maxSize большой.
        Выделение памяти будет происходить постепенно.
    2. Можно усовершенствовать программу и добавить команду, которая увеличит maxSize в рантайме.
        Программе не потребуется перезаписывать все уже имеющиеся данные в новый блок памяти,
        она продолжит работать. Получается расширение допустимого кол-ва элементов будет так же за О(1).
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.OptionalInt;
import java.util.StringTokenizer;

public class ListDec {

    private static class Node<T> {
        private T value;
        private Node<T> next;
        private Node<T> prev;

        public Node(T num, Node<T> prev, Node<T> next) {
            this.value = num;
            this.prev = prev;
            this.next = next;
        }

        public T getValue() { return value;}

        public void setValue(T value) { this.value = value; }

        public Node<T> getNext() { return next; }

        public void setNext(Node<T> next) { this.next = next; }

        public Node<T> getPrev() { return prev; }

        public void setPrev(Node<T> prev) { this.prev = prev;}
    }

    private static int N;
    private static int maxSize;
    private static int currentSize;

    private static Node<Integer> head;
    private static Node<Integer> tail;

    static {
        head = new Node<>(null, null, null);
        tail = new Node<>(null, null, null);

        head.setNext(tail);
        head.setPrev(tail);

        tail.setNext(head);
        tail.setPrev(head);

        currentSize = 0;
    }

    public static void main(String[] args) throws IOException {
        StringTokenizer tokenizer;
        String command;

        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            N = Integer.parseInt(reader.readLine());
            maxSize = Integer.parseInt(reader.readLine());

            for (int i = 0; i < N; i++) {
                tokenizer = new StringTokenizer(reader.readLine());
                command = tokenizer.nextToken();

                if (command.equals("push_back"))
                    printCheckError(push_back(Integer.parseInt(tokenizer.nextToken())));
                else if (command.equals("push_front"))
                    printCheckError(push_front(Integer.parseInt(tokenizer.nextToken())));
                else if (command.equals("pop_front"))
                    printValueCheckError(pop_front());
                else
                    printValueCheckError(pop_back());
            }
        }
    }

    private static void printValueCheckError(OptionalInt value) {
        System.out.println(value.isPresent() ? value.getAsInt() : "error");
    }

    private static void printCheckError(boolean isGood) {
        if (!isGood)
            System.out.println("error");
    }

    private static boolean push_back(int value) {
        if (currentSize >= maxSize)
            return false;
        else {
            tail.setValue(value);
            tail.setNext(new Node<>(null, tail, head));
            tail = tail.getNext();
            head.setPrev(tail);
            currentSize += 1;
        }
        return true;
    }

    private static boolean push_front(int value) {
        if (currentSize >= maxSize)
            return false;
        else {
            head.setValue(value);
            head.setPrev(new Node<>(null, tail, head));
            head = head.getPrev();
            tail.setNext(head);
            currentSize += 1;
        }
        return true;
    }

    private static OptionalInt pop_front() {
        int value;
        
        if (currentSize <= 0)
            return OptionalInt.empty();
        else {
            value = head.getNext().getValue();
            head = head.getNext();
            head.setValue(null);
            head.setPrev(tail);
            tail.setNext(head);
            currentSize -= 1;
        }
        return OptionalInt.of(value);
    }

    private static OptionalInt pop_back() {
        int value;
        
        if (currentSize <= 0)
            return OptionalInt.empty();
        else {
            value = tail.getPrev().getValue();
            tail = tail.getPrev();
            tail.setValue(null);
            tail.setNext(head);
            head.setPrev(tail);
            currentSize -= 1;
        }
        return OptionalInt.of(value);
    }
}
