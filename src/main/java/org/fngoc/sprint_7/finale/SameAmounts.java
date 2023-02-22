package org.fngoc.sprint_7.finale;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SameAmounts {

    private static int[] points;

    public static void main(String[] args) throws IOException {
        input();
        output(isSameAmounts());
    }

    private static boolean isSameAmounts() {
        int sum = Arrays.stream(points).sum();

        if (sum % 2 != 0)
            return false;

        int halfSum = sum / 2;
        boolean[] dp = new boolean[halfSum + 1];
        dp[0] = true;

        for (int point : points) {
            if (point == halfSum)
                return true;
            if (point > halfSum)
                return false;

            for (int j = halfSum; j > point - 1; j--)
                dp[j] = dp[j - point] || dp[j];
        }
        return dp[dp.length - 1];
    }

    private static void output(boolean isTrue) {
        if (isTrue)
            System.out.println("True");
        else
            System.out.println("False");
    }

    private static void input() throws IOException {
        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            int n = Integer.parseInt(tokenizer.nextToken());

            points = new int[n];

            tokenizer = new StringTokenizer(reader.readLine());
            for (int i = 0; i < n; i++)
                points[i] = Integer.parseInt(tokenizer.nextToken());
        }
    }
}
