package org.fngoc.sprint_7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * L. Золото лепреконов
 */

public class LeprechaunGold {

    private static int N;
    private static int M;
    private static int[] weight;
    private static int[][] dp;

    public static void main(String[] args) throws IOException {
        input();

        dp = new int[N + 1][M + 1];

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                if (j - weight[i] < 0)
                    dp[i][j] = dp[i - 1][j];
                else
                    dp[i][j] = Math.max(dp[i - 1][j], weight[i] + dp[i - 1][j - weight[i]]);
            }
        }
        output();
    }

    private static void output() {
        System.out.println(dp[N][M]);
    }

    private static void input() throws IOException {
        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            N = Integer.parseInt(tokenizer.nextToken());
            M = Integer.parseInt(tokenizer.nextToken());

            weight = new int[N + 1];

            tokenizer = new StringTokenizer(reader.readLine());
            for (int i = 1; i < N + 1; i++) {
                weight[i] = Integer.parseInt(tokenizer.nextToken());
            }
        }
    }
}
