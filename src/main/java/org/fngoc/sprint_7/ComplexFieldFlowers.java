package org.fngoc.sprint_7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
 * I. Сложное поле с цветочками
 */

public class ComplexFieldFlowers {

    private static int N;
    private static int M;
    private static int[][] dp;
    private static int[][] dpSumWay;
    private static int[][] field;

    public static void main(String[] args) throws IOException {
        input();

        dp = new int[N + 1][M + 1];
        dpSumWay = new int[N][M];

        dp[0][0] = field[0][0];
        dpSumWay[0][0] = field[0][0];

        for (int i = 1; i < N + 1; i++) {
            for (int j = 1; j < M + 1; j++) {
                int sum = Math.max(dp[i - 1][j], dp[i][j - 1]) + field[i - 1][j - 1];
                dp[i][j] = sum;
                dpSumWay[i - 1][j - 1] = sum;
            }
        }
        reverseArray(dpSumWay);

        List<Character> way = new ArrayList<>();
        int i = 0;
        int j = M - 1;
        while (i + 1 != N || j != 0) {
            if (j != 0 &&i + 1 != N ) {
                if (dpSumWay[i + 1][j] > dpSumWay[i][j - 1]) {
                    way.add('U');
                    i += 1;
                } else {
                    way.add('R');
                    j -= 1;
                }
            } else if (j == 0) {
                way.add('U');
                i += 1;
            } else if (i + 1 == N) {
                way.add('R');
                j -= 1;
            }
        }
        output(way);
    }

    private static void output(List<Character> way) {
        System.out.println(dp[N][M]);

        for (int i = way.size() - 1; i >= 0; i--) {
            System.out.print(way.get(i));
        }
    }

    private static void reverseArray(int[][] arr) {
        for (int i = 0; i < arr.length / 2; i++) {
            int[] tmp = arr[i];
            arr[i] = arr[arr.length - i - 1];
            arr[arr.length - i - 1] = tmp;
        }
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
            reverseArray(field);
        }
    }
}
