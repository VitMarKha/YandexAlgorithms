package org.fngoc.sprint_5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

/*
 * I. Разные деревья поиска
 */

public class DifferentSearchTrees {

    private static void main(String[] args) throws IOException {
        //input
        final BigInteger N;
        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            N = BigInteger.valueOf(Long.parseLong(reader.readLine()));
        }

        //program and output
        System.out.println(factorial(N.multiply(BigInteger.TWO)).divide(factorial(N).multiply(factorial(N.add(BigInteger.ONE)))));
    }

    private static BigInteger factorial(BigInteger n) {
        if (n.equals(BigInteger.ZERO) || n.equals(BigInteger.ONE))
            return BigInteger.ONE;

        return factorial(n.subtract(BigInteger.ONE)).multiply(n);
    }
}
