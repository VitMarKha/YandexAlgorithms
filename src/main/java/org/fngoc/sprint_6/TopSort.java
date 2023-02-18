package org.fngoc.sprint_6;

import java.io.*;
import java.util.*;

/*
 * J. Топологическая сортировка
 */

public class TopSort {

    public static void main(String[] args) throws IOException {
        topSort();
    }

    public static void topSort() throws IOException {
        inputTopSort();
        mainTopSortDFS();
        outputTopSort();
    }

    private static List<Integer> stackResult; //отсортированный граф
    private static int V; //ко-во вершины
    private static int E; //ко-во ребер
    private static Map<Integer, List<Integer>> vertexes; //список смежности
    private static int[] colors;

    private static void mainTopSortDFS() throws IOException {
        for (int i = 1; i < V + 1; i++) {
            if (colors[i] == 0)
                topSort(i, vertexes.get(i));
        }
    }

    private static void topSort(int v, List<Integer> list) {
        colors[v] = 1;
        if (list != null) {
            for (int node : list) {
                if (colors[node] == 0)
                    topSort(node, vertexes.get(node));
            }
        }
        colors[v] = 2;
        stackResult.add(v);
    }

    private static void outputTopSort() throws IOException {
        final BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder output = new StringBuilder();
        for (int i = stackResult.size() - 1; i >= 0; i--) {
            output.append(stackResult.get(i)).append(" ");
        }
        writer.write(output.toString());
        writer.flush();
    }

    private static void inputTopSort() throws IOException {
        //input
        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            V = Integer.parseInt(tokenizer.nextToken());
            E = Integer.parseInt(tokenizer.nextToken());

            stackResult = new ArrayList<>();
            vertexes = new HashMap<>();
            colors = new int[V + 1];

            for (int i = 0; i < E; i++) {
                tokenizer = new StringTokenizer(reader.readLine());

                int v1 = Integer.parseInt(tokenizer.nextToken());
                int v2 = Integer.parseInt(tokenizer.nextToken());

                if (vertexes.containsKey(v1))
                    vertexes.get(v1).add(v2);
                else {
                    List<Integer> list = new ArrayList<>();
                    list.add(v2);
                    vertexes.put(v1, list);
                }
            }
        }
    }
}
