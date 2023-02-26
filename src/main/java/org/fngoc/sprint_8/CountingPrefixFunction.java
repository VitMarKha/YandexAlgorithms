package org.fngoc.sprint_8;

import java.io.*;

/*
 * L. Подсчёт префикс-функции
 */

public class CountingPrefixFunction {

    private static String str;

    public static void main(String[] args) throws IOException {
        input();
        StringBuilder output = new StringBuilder();
        output.append(0).append(' ');
        int[] prefixCount = new int[str.length()];

        for (int i = 1; i < str.length(); i++) {
            int j = prefixCount[i - 1];

            while (j > 0 && str.charAt(j) != str.charAt(i))
                j = prefixCount[j - 1];
            if (str.charAt(j) == str.charAt(i))
                j += 1;
            prefixCount[i] = j;
            output.append(j).append(' ');
        }
        output(output);
    }

    private static void output(StringBuilder output) throws IOException {
        final BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        writer.write(output.toString());
        writer.flush();
    }

    private static void input() throws IOException {
        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            str = reader.readLine();
        }
    }
}
