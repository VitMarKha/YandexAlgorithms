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
        public String toString() {
            return login + "-" + solved + "-" + fine;
        }

        @Override
        public int compareTo(Intern o) {
            if (!this.solved.equals(o.getSolved()))
                return o.getSolved() - this.solved;
            if (!this.fine.equals(o.getFine()))
                return this.fine - o.getFine();

//            String[] strings = new String[2];
//            strings[0] = this.getLogin();
//            strings[1] = o.getLogin();
//            Arrays.sort(strings);

//            System.out.println(Arrays.toString(strings));

//            return strings[0].compareTo(strings[1]);
            return this.login.compareTo(o.getLogin());
        }
    }

    private static Intern[] heap;

    public static void main(String[] args) throws IOException {
        final int N;
        final StringBuilder output = new StringBuilder();
        final BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            N = Integer.parseInt(reader.readLine());
            StringTokenizer tokenizer;
            heap = new Intern[(int) Math.pow(2, N) + 1];

            for (int i = 0; i < N; i++) {
                tokenizer = new StringTokenizer(reader.readLine());

                siftUp(heap, insert(new Intern(
                        tokenizer.nextToken(),
                        Integer.parseInt(tokenizer.nextToken()),
                        Integer.parseInt(tokenizer.nextToken())),1));

//                System.out.println(Arrays.toString(heap));
            }
        }

        for (Intern intern : heap) {
            if (intern != null)
                output.append(intern.getLogin()).append("\n");
        }
        writer.write(output.toString());
        writer.flush();
    }

    public static int insert(Intern intern, int index) {
        if (heap[index] == null) {
            heap[index] = intern;
            return index;
        }

        int left = index * 2;
        int right = index * 2 + 1;

        if (heap[index].compareTo(intern) < 0) {
            if (heap[left] == null) {
                heap[left] = intern;
                return left;
            }
            return insert(intern, left);
        } else {
            if (heap[right] == null) {
                heap[right] = intern;
                return right;
            }
            return insert(intern, right);
        }
    }

    public static int siftUp(Intern[] heap, int idx) {
        int root = idx / 2;

        if (idx == 1 || heap[root].compareTo(heap[idx]) < 0)
            return idx;

        if (heap[root].compareTo(heap[idx]) > 0) {
            Intern tmp = heap[idx];
            heap[idx] = heap[root];
            heap[idx / 2] = tmp;
        }
        return siftUp(heap, idx / 2);
    }
}
