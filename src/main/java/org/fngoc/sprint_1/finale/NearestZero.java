package org.fngoc.sprint_1.finale;

import java.io.*;
import java.util.*;

public class NearestZero {

    public static void main(String[] args) throws IOException {
        //input
        final int N;
        final int[] street;
        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            N = readIntValue(reader);
            street = readIntArray(reader, N);
        }

        //program
        int[] result = new int[N];
        int indexZero = result.length - 1;

        for (int i = 0; i < N; i++) { //проход слева на право по массиву, с указанием расстояния от левого нуля
            if (street[i] == 0) {
                result[i] = 0;
                indexZero = i;
            }
            else
                result[i] = Math.abs(i - indexZero); //установка расстояния до нуля слева
        }

        for (int i = N - 1; i >= 0; i--) { //проход справа на лево по массиву, с указанием расстояния от правого нуля
            if (street[i] == 0)
                indexZero = i;
            else {
                int value = Math.abs(i - indexZero);
                if (result[i] > value) // если расстояние после первого прохода отличается в большую сторону, меняем
                    result[i] = value;
            }
        }

        //output
        writeIntArray(result);
    }

    private static int readIntValue(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine());
    }

    private static int[] readIntArray(BufferedReader reader, int len) throws IOException {
        final int[] array = new int[len];
        final StringTokenizer tokenizer = new StringTokenizer(reader.readLine());

        for (int i = 0; i < len; i++)
            array[i] = Integer.parseInt(tokenizer.nextToken());;
        return array;
    }

    private static void writeIntArray(int[] arr) throws IOException {
        try (final BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            for (int i = 0; i < arr.length; i++) {
                writer.write(String.valueOf(arr[i]));
                writer.write(" ");
            }
            writer.flush();
        }
    }
}
