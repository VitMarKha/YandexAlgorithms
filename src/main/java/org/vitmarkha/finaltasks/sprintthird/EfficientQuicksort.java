package org.vitmarkha.finaltasks.sprintthird;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class EfficientQuicksort {

    private static class Intern {

        private String login;
        private Integer countSolvedTasks;
        private Integer fine;

        public Intern(String login, Integer countSolvedTasks, Integer fine) {
            this.login = login;
            this.countSolvedTasks = countSolvedTasks;
            this.fine = fine;
        }

        public String getLogin() {
            return login;
        }

        public Integer getCountSolvedTasks() {
            return countSolvedTasks;
        }

        public Integer getFine() {
            return fine;
        }

        @Override
        public String toString() {
            return login;
        }
    }

    private static final List<Intern> array = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        //input
        input();

        //program

        //output
        System.out.println(array);
    }

    private static void input() throws IOException {
        final int N;
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
    }
}
