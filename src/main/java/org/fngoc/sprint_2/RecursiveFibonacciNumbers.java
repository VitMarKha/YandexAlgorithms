package org.fngoc.sprint_2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * K. Рекурсивные числа Фибоначчи
 */

public class RecursiveFibonacciNumbers {

    public static void main(String[] args) throws IOException {
        //input
        int N;
        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            N = Integer.parseInt(reader.readLine());
        }

        //program
        int countCommits = recursiveFibonacciPlus(N);

        //output
        System.out.println(countCommits);
    }

    private static int recursiveFibonacciPlus(int n) {
        if (n == 0 || n == 1)
            return 1;
        return recursiveFibonacciPlus(n - 1) + recursiveFibonacciPlus(n - 2);
    }
}
