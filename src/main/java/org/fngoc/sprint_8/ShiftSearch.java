package org.fngoc.sprint_8;

import java.io.*;
import java.util.StringTokenizer;

/*
 * G. Поиск со сдвигом
 */

public class ShiftSearch {

    private static int[] subsequence;
    private static int[] template;
    private static int[] pattern;

    public static void main(String[] args) throws IOException {
        StringBuilder startIndexPatterns = new StringBuilder();
        input();
        countPattern();

        for (int i = 0; i <= subsequence.length - template.length; i++) {
            boolean match = true;
            for (int j = 0; j < pattern.length; j++) {
                if (Math.abs(subsequence[j + i] - subsequence[j + i + 1]) != pattern[j]) {
                    match = false;
                    break;
                }
            }
            if (match)
                startIndexPatterns.append(i + 1).append(" ");
        }
        output(startIndexPatterns);
    }

    private static void countPattern() {
        pattern = new int[template.length - 1];
        for (int i = 0; i < template.length; i++) {
            if (i + 1 != template.length)
                pattern[i] = Math.abs(template[i] - template[i + 1]);
        }
    }

    private static void output(StringBuilder output) throws IOException {
        final BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        writer.write(output.toString());
        writer.flush();
    }

    private static void input() throws IOException {
        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int n = Integer.parseInt(reader.readLine());
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());

            subsequence = new int[n];
            for (int i = 0; i < n; i++) {
                subsequence[i] = Integer.parseInt(tokenizer.nextToken());
            }

            int m = Integer.parseInt(reader.readLine());
            tokenizer = new StringTokenizer(reader.readLine());
            template = new int[m];
            for (int i = 0; i < m; i++) {
                template[i] = Integer.parseInt(tokenizer.nextToken());
            }
        }
    }
}
