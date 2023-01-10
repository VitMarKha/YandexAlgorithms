package org.fngoc.finaltasks.sprintfive;

import java.io.*;
import java.util.*;

public class PyramidSort {

    private static class Intern implements Comparable<Intern> {

        private final String login;
        private final Integer solved;
        private final Integer fine;

        public Intern(String login, Integer solved, Integer fine) {
            this.login = login;
            this.solved = solved;
            this.fine = fine;
        }

        public String getLogin() {
            return login;
        }

        public Integer getSolved() {
            return solved;
        }

        public Integer getFine() {
            return fine;
        }

        @Override
        public int compareTo(Intern o) {
            if (!this.solved.equals(o.getSolved()))
                return o.getSolved() - this.solved;
            if (!this.fine.equals(o.getFine()))
                return this.fine - o.getFine();
            return this.login.compareTo(o.getLogin());
        }
    }

    private static Intern[] heap;
    private static int sizeHeap = 0;

    public static void main(String[] args) throws IOException {
        final int N;

        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            N = Integer.parseInt(reader.readLine()) + 1;
            StringTokenizer tokenizer;
            heap = new Intern[N];

            for (int i = 1; i < N; i++) {
                tokenizer = new StringTokenizer(reader.readLine());
                sizeHeap += 1;

                insert(new Intern(
                        tokenizer.nextToken(),
                        Integer.parseInt(tokenizer.nextToken()),
                        Integer.parseInt(tokenizer.nextToken())), sizeHeap);
                siftUp(heap, sizeHeap);
            }
        }
        output();
    }

    private static boolean isFirstBigger(Intern one, Intern two) {
        return one.compareTo(two) < 0;
    }

    public static void insert(Intern intern, int index) {
        heap[index] = intern;
    }

    public static int siftUp(Intern[] heap, int idx) {
        int root = idx / 2;

        if (idx == 1 || isFirstBigger(heap[root], heap[idx]))
            return idx;

        if (isFirstBigger(heap[idx], heap[root])) {
            Intern tmp = heap[idx];
            heap[idx] = heap[root];
            heap[root] = tmp;
        }
        return siftUp(heap, root);
    }

    public static int siftDown(Intern[] heap, int idx) {
        int left = idx * 2;
        int right = idx * 2 + 1;
        int large = left;

        if (left > sizeHeap)
            return idx;

        if (right <= sizeHeap && isFirstBigger(heap[right], heap[large]))
            large = right;

        if (isFirstBigger(heap[large], heap[idx])) {
            Intern tmp = heap[idx];
            heap[idx] = heap[large];
            heap[large] = tmp;
            return siftDown(heap, large);
        }
        return idx;
    }

    private static Intern popMax(Intern[] heap) {
        Intern result = heap[1];
        heap[1] = heap[sizeHeap];
        heap[sizeHeap] = null;
        sizeHeap -= 1;
        siftDown(heap, 1);
        return result;
    }

    private static void output() throws IOException {
        final StringBuilder output = new StringBuilder();
        final BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        for (int i = 1; i < heap.length; i++) {
            output.append(popMax(heap).getLogin()).append("\n");
        }
        writer.write(output.toString());
        writer.flush();
    }
}
