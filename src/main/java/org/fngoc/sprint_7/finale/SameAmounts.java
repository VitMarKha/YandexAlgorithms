package org.fngoc.sprint_7.finale;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SameAmounts {

    private static int N;
    private static int[] points;

    public static void main(String[] args) throws IOException {
        input();
        output(isTwoSum());
    }

    private static boolean isTwoSum() {
        int sum = Arrays.stream(points).sum();

        if (sum % 2 != 0)
            return false;

        int halfSum = sum / 2;
        boolean[] dp = new boolean[N + 1];
        dp[0] = true;

        for (int point : points) {
            if (point == halfSum)
                return true;
            if (point > halfSum)
                return false;

            for (int j = halfSum; j > point - 1; j--) {
                dp[j] = dp[j - point];
                if (dp[dp.length - 1])
                    return true;
            }
        }
        return dp[dp.length - 1];


//        int halfSum = sum / 2;
//        boolean[] dp = new boolean[halfSum + 1];
//        boolean[] dpPrev = new boolean[halfSum + 1];
//
//        for (int i = 1; i < N + 1; i++) {
//            for (int j = 1; j < halfSum + 1; j++) {
//                dp[j] = dpPrev[j];
//
//                if (j == points[i - 1])
//                    dp[j] = true;
//                if (j > points[i - 1] && dpPrev[j - points[i - 1]])
//                    dp[j] = true;
//            }
//            dpPrev = dp;
//            dp = new boolean[halfSum + 1];
//        }
//        return dpPrev[halfSum];
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
            N = Integer.parseInt(tokenizer.nextToken());

            points = new int[N];

            tokenizer = new StringTokenizer(reader.readLine());
            for (int i = 0; i < N; i++) {
                points[i] = Integer.parseInt(tokenizer.nextToken());
            }
        }
    }
}
