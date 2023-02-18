package org.fngoc.sprint_2;

import java.io.*;
import java.util.StringTokenizer;

/*
 * A. Мониторинг
 */

public class Monitoring {

    public static void main(String[] args) throws IOException {
        //input
        int N;
        int M;
        int[][] matrix;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            N = Integer.parseInt(reader.readLine().strip());
            M = Integer.parseInt(reader.readLine().strip());

            StringTokenizer tokenizer;
            matrix = new int[N][M];
            for (int i = 0; i < N; i++) {
                tokenizer = new StringTokenizer(reader.readLine());
                for (int j = 0; j < M; j++)
                    matrix[i][j] = Integer.parseInt(tokenizer.nextToken());
            }
        }

        //program
        int[][] reversMatrix = new int[M][N];

        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                reversMatrix[i][j] = matrix[j][i];
            }
        }

        //output
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                writer.write(String.valueOf(reversMatrix[i][j]));
                writer.append(' ');
            }
            writer.append('\n');
        }
        writer.flush();
    }
}
