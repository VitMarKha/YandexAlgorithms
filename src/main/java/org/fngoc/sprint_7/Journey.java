package org.fngoc.sprint_7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
 * J. Путешествие
 */

public class Journey {

    private static int N;
    private static int len;
    private static int[] islands;
    private static int[] dp;
    private static int[] p;

    public static void main(String[] args) throws IOException {
        input();

        if (N <= 1) {
            System.out.print("1\n1");
        } else {
            dpCount();
            int pos = findLen();
            List<Integer> path = findPath(pos);
            output(path);
        }
    }

    private static List<Integer> findPath(int pos) {
        List<Integer> path = new ArrayList<>();

        while (pos != -1) {
            path.add(pos);
            pos = p[pos];
        }
        return path;
    }

    private static int findLen() {
        len = dp[1];
        int pos = 0;

        for (int i = 0; i < N; i++) {
            if (dp[i] > len) {
                len = dp[i];
                pos = i;
            }
        }
        return pos;
    }

    private static void dpCount() {
        dp = new int[N];
        p = new int[N];

        for (int i = 0; i < N; i++) {
            dp[i] = 1;
            p[i] = -1;

            for (int j = 0; j < i; j++) {
                if (islands[j] < islands[i]) {
                    if (dp[j] + 1 > dp[i]) {
                        dp[i] = dp[j] + 1;
                        p[i] = j;
                    }
                }
            }
        }
    }

    private static void output(List<Integer> path) {
        System.out.println(len);
        for (int i = path.size() - 1; i >= 0; i--) {
            System.out.print((path.get(i) + 1) + " ");
        }
    }

    private static void input() throws IOException {
        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            N = Integer.parseInt(reader.readLine());

            islands = new int[N];

            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            for (int i = 0; i < N; i++) {
                islands[i] = Integer.parseInt(tokenizer.nextToken());
            }
        }
    }
}
