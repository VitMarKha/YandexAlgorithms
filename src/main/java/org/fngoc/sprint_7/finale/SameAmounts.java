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

        int sum = Arrays.stream(points).sum();
        int[] dp = new int[sum + 1];
        dp[0] = 1;

        for( int i = 0; i < N; i++ )
            for( int j = sum - points[i]; j >= 0; j--)
                if(dp[j] == 1)
                    dp[j + points[i]] = 1;

        System.out.println(Arrays.toString(dp));

//        for (int i = 0; i < N; i++) {
//            for (int j = N - points[i]; j >= 0; j--) {
//                if (j == points[i] || j - points[i])
//            }
//        }

//        bool partition( vector< int > C ) {
//            // compute the total sum
//            int n = C.size();
//            int N = 0;
//            for( int i = 0; i < n; i++ ) N += C[i];
//            // initialize the table
//            T[0] = true;
//            for( int i = 1; i <= N; i++ ) T[i] = false;

//            // process the numbers one by one
//            for( int i = 0; i < n; i++ )
//                for( int j = N - C[i]; j >= 0; j--)
//                    if( T[j] ) T[j + C[i]] = true;
//
//            for(int i = N/2;i>=0;i--)
//                if (T[i])
//                    return i;
//            return 0;
//        }

//        for (int i = 0; i < dp.length; i++) {
//            for (int j = 0; j < dp[i].length; j++) {
//                System.out.print(dp[i][j]);
//            }
//            System.out.println();
//        }

//        int sum = Arrays.stream(points).sum();
//        if (dp[] == 1) {
//            System.out.println("True");
//        } else
//            System.out.println("False");
    }

    private static void output() {
        System.out.println(N);
        System.out.println(Arrays.toString(points));
    }

    private static void input() throws IOException {
        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            N = Integer.parseInt(tokenizer.nextToken());

            points = new int[N + 1];

            tokenizer = new StringTokenizer(reader.readLine());
            for (int i = 1; i < N + 1; i++) {
                points[i] = Integer.parseInt(tokenizer.nextToken());
            }
        }
    }
}
