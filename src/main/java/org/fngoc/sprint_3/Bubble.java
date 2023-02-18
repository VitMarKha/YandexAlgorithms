package org.fngoc.sprint_3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * J. Пузырёк
 */

public class Bubble {

    public static void main(String[] args) throws IOException {
        //input
        final int N;
        int[] array;
        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            N = Integer.parseInt(reader.readLine());
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());

            array = new int[N];
            for (int i = 0; i < N; i++)
                array[i] = Integer.parseInt(tokenizer.nextToken());
        }

        //program and output
        if (isArraySorted(array)) {
            System.out.println(array2String(array));
            return;
        }

        for (int j = 0; j < N - 1; j++) {
            if (isArraySorted(array))
                break;
            for (int i = 0; i < array.length; i++) {
                if (i + 1 < array.length && array[i] > array[i + 1]) {
                    int tmp = array[i + 1];
                    array[i + 1] = array[i];
                    array[i] = tmp;
                }
            }
            System.out.println(array2String(array));
        }
    }

    private static String array2String(int[] array) {
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < array.length; i++) {
            stringBuilder.append(array[i]).append(" ");
        }
        return stringBuilder.toString();
    }

    private static boolean isArraySorted(int[] array) {
        for (int i = 0; i < array.length; i++) {
            if (i + 1 < array.length && array[i] > array[i + 1])
                return false;
        }
        return true;
    }
}
