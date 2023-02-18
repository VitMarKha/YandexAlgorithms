package org.fngoc.sprint_3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/*
 * B. Комбинации
 */

public class Combinations {

    private static final Map<Integer, char[]> mapCombination = new HashMap<>();

    static {
        mapCombination.put(2, new char[]{'a','b','c'});
        mapCombination.put(3, new char[]{'d','e','f'});
        mapCombination.put(4, new char[]{'g','h','i'});
        mapCombination.put(5, new char[]{'j','k','l'});
        mapCombination.put(6, new char[]{'m','n','o'});
        mapCombination.put(7, new char[]{'p','q','r','s'});
        mapCombination.put(8, new char[]{'t','u','v'});
        mapCombination.put(9, new char[]{'w','x','y','z'});
    }

    public static void main(String[] args) throws IOException {
        //input
        final String combination;
        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            combination = reader.readLine();
        }

        int[] array = new int[combination.length()];
        for (int i = 0; i < combination.length(); i++) {
            array[i] = Integer.parseInt(String.valueOf(combination.charAt(i)));
        }

        //program
        StringBuilder output = new StringBuilder();
        recurseCombination(array, 0, output, "");

        //output
        System.out.println(output);
    }

    private static void recurseCombination(int[] array, int iterator, StringBuilder output, String line) {
        if (line.length() == array.length)
            output.append(line).append(" ");
        else {
            char[] chars = mapCombination.get(array[iterator]);
            for (int i = 0; i < chars.length; i++) {
                recurseCombination(array, iterator + 1, output, line + chars[i]);
            }
        }
    }
}
