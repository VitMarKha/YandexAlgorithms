package org.vitmarkha.finaltasks.sprintfour;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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

    private static final Integer LEN = 10000019;
    private static final Integer С = 199;
    private static final Bucket[] table = new Bucket[LEN];

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
                    print(get(Integer.parseInt(tokenizer.nextToken())));
                else
                    print(delete(Integer.parseInt(tokenizer.nextToken())));
            }
        }
    }

    private static void put(int key, int value) {
        int index = getIndexBucket(key);

        if (isEmptyBucket(index)) {
            table[index] = new Bucket(key, value);
        } else if (!isEmptyBucket(index) && table[index].key.equals(key)) {
            table[index].value = value;
        } else {
            index = searchEmptyBucket(index, key);

            if (isEmptyBucket(index))
                table[index] = new Bucket(key, value);
            else if (!isEmptyBucket(index) && table[index].key.equals(key))
                table[index].value = value;
        }
    }

    private static int get(int key) {
        int index = getIndexBucket(key);

        if (!isEmptyBucket(index) && table[index].key.equals(key))
            return table[index].value;

        index = searchEmptyBucket(index, key);

        if (!isEmptyBucket(index) && table[index].key.equals(key))
            return table[index].value;
        return -1;
    }

    private static int delete(int key) {
        int index = getIndexBucket(key);

        if (!isEmptyBucket(index) && table[index].key.equals(key)) {
            int resultValue = table[index].value;
            table[index] = null;
            return resultValue;
        }

        index = searchEmptyBucket(index, key);

        if (!isEmptyBucket(index) && table[index].key.equals(key)) {
            int resultValue = table[index].value;
            table[index] = null;
            return resultValue;
        }
        return -1;
    }

    private static boolean isEmptyBucket(int index) {
        return table[index] == null;
    }

    private static int searchEmptyBucket(int index, int key) {
        while (!isEmptyBucket(index) && !table[index].key.equals(key))
            index = getIndexNextBucket(key, index);
        return index;
    }

    private static int getIndexNextBucket(Integer key, int index) {
        return Math.abs((key.hashCode() + С * index) % LEN);
    }

    private static int getIndexBucket(Integer key) {
        return Math.abs(key.hashCode() % LEN);
    }

    private static void print(int integer) {
        if (integer == -1)
            System.out.println("None");
        else
            System.out.println(integer);
    }
}
