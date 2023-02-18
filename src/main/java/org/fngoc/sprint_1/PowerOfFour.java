package org.fngoc.sprint_1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * I. Степень четырёх
 */

public class PowerOfFour {

    public static void main(String[] args) throws IOException {
        //input
        final int N;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            N = Integer.parseInt(reader.readLine().strip());
        }

        //program and output
        if (N == 1 || N == 4) {
            System.out.println("True");
            return;
        }

        int base = 4;
        int temp = N;

        while (temp >= 1) {
            base *= 4;
            if (base == N) {
                System.out.println("True");
                return;
            }
            temp -= 1;
        }
        System.out.println("False");
    }
}
