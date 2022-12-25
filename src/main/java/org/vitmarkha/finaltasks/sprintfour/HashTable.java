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

    private static List<Bucket>[] table = new LinkedList[100_000];

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

        if (isEmptyBucket(index))
            table[index] = new LinkedList<>(Collections.singletonList(new Bucket(key, value)));
        else
            putCollisions(index, key, value);
    }

    private static void putCollisions(int index, int key, int value) {
        LinkedList<Bucket> list = (LinkedList<Bucket>) table[index];

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).key.equals(key)) {
                list.get(i).value = value;
                return;
            }
        }
        list.addFirst(new Bucket(key, value));
    }

    private static int get(int key) {
        int index = getIndexBucket(key);

        if (isEmptyBucket(index))
            return -1;
        return getValueByIndexAndKey(index, key);
    }

    private static int getValueByIndexAndKey(int index, int findKey) {
        LinkedList<Bucket> list = (LinkedList<Bucket>) table[index];

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).key.equals(findKey))
                return list.get(i).value;
        }
        return -1;
    }

    private static int delete(int key) {
        int index = getIndexBucket(key);

        if (isEmptyBucket(index))
            return -1;
        return getAndDeleteBucket(index, key);
    }

    private static int getAndDeleteBucket(int index, int findKey) {
        LinkedList<Bucket> list = (LinkedList<Bucket>) table[index];

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).key.equals(findKey)) {
                int resultValue = list.get(i).value;
                list.remove(i);
                return resultValue;
            }
        }
        return -1;
    }

    private static boolean isEmptyBucket(int index) {
        return table[index] == null;
    }

    private static void print(int integer) {
        if (integer == -1)
            System.out.println("None");
        else
            System.out.println(integer);
    }

    private static int getIndexBucket(int key) {
        return Math.abs(myHashCode(key) % 100_000);
    }

    private static int myHashCode(int integer) {
        return integer * 1997;
    }
}
