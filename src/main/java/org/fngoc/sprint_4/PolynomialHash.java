package org.fngoc.sprint_4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * A. Полиномиальный хеш
 */

public class PolynomialHash {

    public static void main(String[] args) throws IOException {
        //input
        final int A;
        final int M;
        final String S;
        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            A = Integer.parseInt(reader.readLine());
            M = Integer.parseInt(reader.readLine());
            S = reader.readLine();
        }

        //program
        long hash = 0;
        for (int i = 0; i < S.length(); i++) {
            hash = (hash * A + S.charAt(i)) % M;
        }

        //output
        System.out.print(hash);
    }
}
