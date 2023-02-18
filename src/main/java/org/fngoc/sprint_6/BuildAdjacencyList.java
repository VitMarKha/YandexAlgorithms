package org.fngoc.sprint_6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
 * A. Построить список смежности
 */

public class BuildAdjacencyList {

    public static void main(String[] args) throws IOException {
        //input and program
        final int V; //вершины
        final int E; //ребра
        List<Integer>[] adjacencyList; //список смежности
        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            V = Integer.parseInt(tokenizer.nextToken());
            E = Integer.parseInt(tokenizer.nextToken());

            adjacencyList = new ArrayList[V];

            for (int i = 0; i < E; i++) {
                tokenizer = new StringTokenizer(reader.readLine());
                int v1 = Integer.parseInt(tokenizer.nextToken()) - 1;
                int v2 = Integer.parseInt(tokenizer.nextToken());

                if (adjacencyList[v1] == null) {
                    adjacencyList[v1] = new ArrayList<>();
                    adjacencyList[v1].add(v2);
                } else
                    adjacencyList[v1].add(v2);
            }
        }

        //output
        for (List<Integer> integers : adjacencyList) {
            if (integers == null)
                System.out.println(0);
            else {
                System.out.print(integers.size() + " ");
                for (Integer integer : integers)
                    System.out.print(integer + " ");
                System.out.println();
            }
        }
    }
}
