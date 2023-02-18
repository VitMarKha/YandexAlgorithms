package org.fngoc.sprint_7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
 * K. Гороскопы
 */

public class Horoscopes {

    private static int N;
    private static int M;
    private static int[] firstSubsequence;
    private static int[] secondSubsequence;
    private static List<Integer> indexesFirstSubsequence;
    private static List<Integer> indexesSecondSubsequence;
    private static int[][] dp;

    public static void main(String[] args) throws IOException {
        input();

        dp = new int[N + 1][M + 1];

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                if (firstSubsequence[i] == secondSubsequence[j])
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                else
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
            }
        }

        int i = firstSubsequence.length - 1;
        int j = secondSubsequence.length - 1;
        indexesFirstSubsequence = new ArrayList<>();
        indexesSecondSubsequence = new ArrayList<>();
        while (i > 0 && j > 0) {
            if (firstSubsequence[i] == secondSubsequence[j]) {
                indexesFirstSubsequence.add(i);
                indexesSecondSubsequence.add(j);
                i -= 1;
                j -= 1;
            } else {
                if (dp[i][j] == dp[i - 1][j])
                    i -= 1;
                else
                    j -= 1;
            }
        }
        output();
    }

    private static void output() {
        System.out.println(dp[N][M]);

        for (int i = indexesFirstSubsequence.size() - 1; i >= 0; i--)
            System.out.print(indexesFirstSubsequence.get(i) + " ");
        System.out.println();
        for (int i = indexesSecondSubsequence.size() - 1; i >= 0; i--)
            System.out.print(indexesSecondSubsequence.get(i) + " ");
    }

    private static void input() throws IOException {
        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            N = Integer.parseInt(tokenizer.nextToken());

            tokenizer = new StringTokenizer(reader.readLine());
            firstSubsequence = new int[N + 1];
            for (int i = 1; i < N + 1; i++) {
                firstSubsequence[i] = Integer.parseInt(tokenizer.nextToken());
            }

            tokenizer = new StringTokenizer(reader.readLine());
            M = Integer.parseInt(tokenizer.nextToken());

            tokenizer = new StringTokenizer(reader.readLine());
            secondSubsequence = new int[M + 1];
            for (int i = 1; i < M + 1; i++) {
                secondSubsequence[i] = Integer.parseInt(tokenizer.nextToken());
            }
        }
    }
}
