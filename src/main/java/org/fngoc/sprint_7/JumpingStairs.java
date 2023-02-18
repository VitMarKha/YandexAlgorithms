package org.fngoc.sprint_7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * F. Прыжки по лестнице
 */

public class JumpingStairs {

    private static int K;
    private static int N;
    private static int MODULE = 1_000_000_007;

    private static int[] dp;

    public static void main(String[] args) throws IOException {
        jumpingStairs();
    }

    private static void jumpingStairs() throws IOException {
        //input
        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            N = Integer.parseInt(tokenizer.nextToken());
            K = Integer.parseInt(tokenizer.nextToken());

            dp = new int[N + 1];
        }

        //program
        int result = countSteps(N);

        //output
        System.out.println(result);
    }

    private static int countSteps(int number) {
        if (number < 1)
            return 0;
        else if (number == 1)
            return 1;
        else if (dp[number] == 0) {
            for (int i = 1; i <= K; i++) {
                dp[number] += countSteps(number - i);
                dp[number] %= MODULE;
            }
        }
        return dp[number] % MODULE;
    }
}
