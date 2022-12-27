package org.vitmarkha.finaltasks.sprintfour;

import java.io.*;
import java.util.*;

public class HashTable {

    final static class Bucket {
        private final Integer key;
        private Integer value;

        public Bucket(Integer key, Integer value) {
            this.key = key;
            this.value = value;
        }
    }

    private static final int SIMPLE_LEN = 10_000_019;
    private static final int SIMPLE_INT = 9973;

    private static final Bucket[] table = new Bucket[SIMPLE_LEN];

    private static final StringBuilder output = new StringBuilder();

    public static void main(String[] args) throws IOException {
        final int N;

        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            N = Integer.parseInt(reader.readLine());
            StringTokenizer tokenizer;

            for (int i = 0; i < N; i++) {
                tokenizer = new StringTokenizer(reader.readLine());
                String command = tokenizer.nextToken();
                if (command.equals("put")) {
                    int key = Integer.parseInt(tokenizer.nextToken());
                    int value = Integer.parseInt(tokenizer.nextToken());
                    put(key, value);
                } else if (command.equals("get"))
                    addAnswerInLine(get(Integer.parseInt(tokenizer.nextToken())));
                else
                    addAnswerInLine(delete(Integer.parseInt(tokenizer.nextToken())));
            }
        }
        output();
    }

    private static void put(int key, int value) {
        int index = hash(key);

        if (isEmptyBucket(index))
            table[index] = new Bucket(key, value);
        else if (isCellFound(index, key))
            table[index].value = value;
        else {
            index = getEmptyBucket(index, key);

            if (isEmptyBucket(index))
                table[index] = new Bucket(key, value);
            else if (isCellFound(index, key))
                table[index].value = value;
        }
    }

    private static int get(int key) {
        int index = hash(key);

        if (isCellFound(index, key))
            return table[index].value;

        index = getEmptyBucket(index, key);

        if (isCellFound(index, key))
            return table[index].value;
        return -1;
    }

    private static int delete(int key) {
        int index = hash(key);

        if (isCellFound(index, key)) {
            int resultValue = table[index].value;
            table[index] = null;
            return resultValue;
        }

        index = getEmptyBucket(index, key);

        if (isCellFound(index, key)) {
            int resultValue = table[index].value;
            table[index] = null;
            return resultValue;
        }
        return -1;
    }

    private static int getEmptyBucket(int index, int key) {
        while (!isEmptyBucket(index) && !table[index].key.equals(key))
            index += 1;
        return index;
    }

    private static boolean isCellFound(int index, int key) {
        return !isEmptyBucket(index) && table[index].key.equals(key);
    }

    private static boolean isEmptyBucket(int index) {
        return table[index] == null;
    }

    private static int hash(Integer key) {
        return Math.abs((key * SIMPLE_INT) % SIMPLE_LEN);
    }

    private static void output() throws IOException {
        final BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        writer.write(output.toString());
        writer.flush();
    }

    private static void addAnswerInLine(int integer) {
        if (integer == -1)
            output.append("None");
        else
            output.append(integer);
        output.append('\n');
    }
}
