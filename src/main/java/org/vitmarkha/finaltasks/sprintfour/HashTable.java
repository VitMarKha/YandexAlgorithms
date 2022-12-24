package org.vitmarkha.finaltasks.sprintfour;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class HashTable {

    final static class Bucket {
        private final int key;
        private final int value;

        private Bucket next;
        private Bucket prev;

        public Bucket(int key, int value) {
            this.key = key;
            this.value = value;
            this.next = null;
            this.prev = null;
        }
    }

    private static final Bucket[] buckets = new Bucket[100_00];

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
                    get(Integer.parseInt(tokenizer.nextToken()));
                else
                    delete(Integer.parseInt(tokenizer.nextToken()));
            }
        }
    }

    private static void put(int key, int value) {
        int index = getIndexBucket(key);
        if (isEmptyBucket(index))
            buckets[index] = new Bucket(key, value);
        else
            putCollisions(index, key, value);
    }

    private static void get(int key) {
        int index = getIndexBucket(key);
        if (isEmptyBucket(index))
            System.out.println("None");
        else {
            int result = getValueByIndexAndKey(index, key);
            if (result == -1)
                System.out.println("None");
            else
                System.out.println(result);
        }
    }

    private static void delete(int key) {

    }

    private static int getValueByIndexAndKey(int index, int findKey) {
        Bucket head = buckets[index];
        Bucket iterator = head;
        while (iterator != null) {
            if (iterator.key == findKey)
                return iterator.value;
            iterator = iterator.next;
        }
        return -1;
    }

    private static void putCollisions(int index, int key, int value) {
        Bucket newBucket = new Bucket(key, value);
        Bucket head = buckets[index];
        head.prev = new Bucket(key, value);
        newBucket.next = head;
        buckets[index] = newBucket;
    }

    private static boolean isEmptyBucket(int index) {
        Bucket bucket = buckets[index];
        return bucket == null;
    }

    private static int getIndexBucket(int key) {
        return myHashCode(key) % 100_000;
    }

    private static int myHashCode(int integer) {
        return integer * 1997;
    }
}
