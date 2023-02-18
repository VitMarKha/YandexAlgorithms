package org.fngoc.sprint_7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * H. Поле с цветочками
 */

public class FieldWithFlowers {

    private static int N;
    private static int M;
    private static int[][] dp;
    private static int[][] field;

    public static void main(String[] args) throws IOException {
        input();

        dp = new int[N + 1][M + 1];
        dp[0][0] = field[0][0];

        for (int i = 1; i < N + 1; i++) {
            for (int j = 1; j < M + 1; j++) {
                dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]) + field[i - 1][j - 1];
            }
        }
        System.out.println(dp[N][M]);
    }

    private static void input() throws IOException {
        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            N = Integer.parseInt(tokenizer.nextToken());
            M = Integer.parseInt(tokenizer.nextToken());

            field = new int[N][M];

            for (int i = 0; i < N; i++) {
                tokenizer = new StringTokenizer(reader.readLine());
                String line = tokenizer.nextToken();
                for (int j = 0; j < M; j++) {
                    field[i][j] = Integer.parseInt(String.valueOf(line.charAt(j)));
                }
            }

            for (int i = 0; i < field.length / 2; i++) {
                int[] tmp = field[i];
                field[i] = field[field.length - i - 1];
                field[field.length - i - 1] = tmp;
            }
        }
    }
}
