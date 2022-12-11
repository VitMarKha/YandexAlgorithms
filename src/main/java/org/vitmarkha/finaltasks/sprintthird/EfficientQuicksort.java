package org.vitmarkha.finaltasks.sprintthird;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class EfficientQuicksort {

    private static final class Intern implements Comparable<Intern> {

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
            return login;
        }

        @Override
        public int compareTo(Intern o) {
            if (this.login.equals(o.login) && this.fine.equals(o.fine) && this.solved.equals(o.solved))
                return 0;

            if (this.solved > o.getSolved())
                return -1;
            else if (this.solved.equals(o.getSolved())) {
                if (this.fine < o.getFine())
                    return -1;
                else if (this.fine > o.getFine())
                    return 1;
                else
                    return this.login.compareTo(o.getLogin());
            }
            return 1;
        }
    }

    public static void main(String[] args) throws IOException {
        List<Intern> array = input();
        quickSortInPlace(array, 0, array.size() - 1);
        output(array);
    }

    private static void quickSortInPlace(List<Intern> array, int start, int end) {
        if (start > end)
            return;

        Intern pivot = getRandomPivot(array, start, end);
        int left = start;
        int right = end;

        while (left <= right) {
            while (compare(pivot, array.get(left)))
                left += 1;
            while (compare(array.get(right), pivot))
                right -= 1;
            if (left <= right) {
                swap(array, left, right);
                left += 1;
                right -= 1;
            }
        }
        quickSortInPlace(array, start, right);
        quickSortInPlace(array, left, end);
    }

    private static Intern getRandomPivot(List<Intern> array, int start, int end) {
        return array.get(start + (int) (Math.random() * (end - start)));
    }

    private static boolean compare(Intern first, Intern second) {
        return first.compareTo(second) < 0;
    }

    private static void swap(List<Intern> array, int left, int right) {
        Intern tmp = array.get(left);
        array.set(left, array.get(right));
        array.set(right, tmp);
    }

    private static List<Intern> input() throws IOException {
        final int N;
        final List<Intern> array = new ArrayList<>();
        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            N = Integer.parseInt(reader.readLine());
            StringTokenizer tokenizer;

            for (int i = 0; i < N; i++) {
                tokenizer = new StringTokenizer(reader.readLine());
                Intern intern = new Intern(tokenizer.nextToken(),
                        Integer.parseInt(tokenizer.nextToken()),
                        Integer.parseInt(tokenizer.nextToken()));
                array.add(intern);
            }
        }
        return array;
    }

    private static void output(List<Intern> array) throws IOException {
        final StringBuilder output = new StringBuilder();
        final BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        for (int i = array.size() - 1; i >= 0; i--)
            output.append(array.get(i)).append('\n');
        writer.write(output.toString());
        writer.flush();
    }
}
