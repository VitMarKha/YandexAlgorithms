package org.fngoc.sprint_6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * B. Перевести список ребер в матрицу смежности
 */

public class ConvertListOfEdgesToAdjacencyMatrix {

    public static void main(String[] args) throws IOException {
        //input and program
        final int V; //вершины
        final int E; //ребра
        int[][] adjacencyMatrix; //список смежности
        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            V = Integer.parseInt(tokenizer.nextToken());
            E = Integer.parseInt(tokenizer.nextToken());

            adjacencyMatrix = new int[V][V];

            for (int i = 0; i < E; i++) {
                tokenizer = new StringTokenizer(reader.readLine());
                int v1 = Integer.parseInt(tokenizer.nextToken()) - 1;
                int v2 = Integer.parseInt(tokenizer.nextToken()) - 1;

                adjacencyMatrix[v1][v2] += 1;
            }
        }

        //output
        for (int[] matrix : adjacencyMatrix) {
            for (int i : matrix)
                System.out.print(i + " ");
            System.out.println();
        }
    }
}
