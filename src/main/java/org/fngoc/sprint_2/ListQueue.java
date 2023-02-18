package org.fngoc.sprint_2;

import java.io.*;
import java.util.StringTokenizer;

/*
 * J. Списочная очередь
 */

public class ListQueue {

    private static class Node<T> {
        public T value;
        public Node<T> next;
        public Node<T> prev;

        public Node(T num, Node<T> prev, Node<T> next) {
            this.value = num;
            this.prev = prev;
            this.next = next;
        }
    }

    private static Integer size = 0;
    private static Node<Integer> first;
    private static Node<Integer> last;

    private static final StringBuilder output = new StringBuilder();

    public static void main(String[] args) throws IOException {
        //input and program
        int N;
        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            N = Integer.parseInt(reader.readLine());

            StringTokenizer tokenizer;
            String command;
            for (int i = 0; i < N; i++) {
                tokenizer = new StringTokenizer(reader.readLine());
                command = tokenizer.nextToken();

                if (command.equals("put"))
                    listQueuePut(Integer.parseInt(tokenizer.nextToken()));
                else if (command.equals("get"))
                    listQueueGet();
                else
                    listQueueSize();
            }

            //output
            final BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
            writer.write(output.toString());
            writer.flush();
        }
    }

    private static void listQueuePut(int num) {
        if (first == null) {
            first = new Node<>(num, null, null);
            last = new Node<>(null, null, null);
            first.next = last;
            last.prev = first;
        } else {
            last.value = num;
            last.next = new Node<>(null, last, null);
            last = last.next;
        }
        size += 1;
    }

    private static void listQueueGet() {
        if (size > 0) {
            output.append(first.value).append('\n');
            first = first.next;
            size -= 1;
            if (size == 0)
                first = null;
        } else
            output.append("error\n");
    }

    private static void listQueueSize() {
        output.append(size).append('\n');
    }
}
