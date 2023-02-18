package org.fngoc.sprint_2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * L. Фибоначчи по модулю
 */

public class FibonacciModulo {

    private static int first;
    private static int second = 0;
    private static int fibonacci = 1;

    public static void main(String[] args) throws IOException {
        //input
        final int N;
        int K;
        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            N = Integer.parseInt(tokenizer.nextToken());
            K = Integer.parseInt(tokenizer.nextToken());
        }

        //program
        K = (int) Math.pow(10, K);
        int result = 1;

        for (int i = 0; i < N; i++) {
            first = second % K;
            second = fibonacci % K;
            fibonacci = first + second;
            result = fibonacci % K;
        }

        //output
        System.out.println(result);
    }
}
