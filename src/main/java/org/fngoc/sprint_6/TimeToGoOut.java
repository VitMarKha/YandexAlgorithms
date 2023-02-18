package org.fngoc.sprint_6;

import java.io.*;
import java.util.*;

/*
 * H. Время выходить
 */

public class TimeToGoOut {

    public static void main(String[] args) throws IOException {
        timeToGoOut();
    }

    public static void timeToGoOut() throws IOException {
        inputGraph();
        mainDFS();
    }

    private static int V; //ко-во вершины
    private static int E; //ко-во ребер
    private static Map<Integer, Set<Integer>> vertexes; //список смежности
    private static int time = -1;
    private static int[] timeEntry;
    private static int[] timeLeave;
    private static int[] colors;

    public static void mainDFS() throws IOException {
        if (V > 1) {
            DFS(1, vertexes.get(1));
            output();
        } else
            System.out.println("0 1");
    }

    public static void DFS(int v, Set<Integer> list) {
        time += 1;
        timeEntry[v] = time;
        colors[v] = 1;

        if (list != null) {
            for (int node : list) {
                if (colors[node] == 0)
                    DFS(node, vertexes.get(node));
            }
        }

        time += 1;
        timeLeave[v] = time;
        colors[v] = 2;
    }

    public static void output() throws IOException {
        final BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder output = new StringBuilder();
        for (int i = 1; i < V + 1; i++)
            output.append(timeEntry[i]).append(" ").append(timeLeave[i]).append("\n");

        writer.write(output.toString());
        writer.flush();
    }

    public static void inputGraph() throws IOException {
        //input
        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            V = Integer.parseInt(tokenizer.nextToken());
            E = Integer.parseInt(tokenizer.nextToken());

            vertexes = new HashMap<>();
            timeEntry = new int[V + 1];
            timeLeave = new int[V + 1];
            colors = new int[V + 1];

            for (int i = 0; i < E; i++) {
                tokenizer = new StringTokenizer(reader.readLine());

                int v1 = Integer.parseInt(tokenizer.nextToken());
                int v2 = Integer.parseInt(tokenizer.nextToken());

                if (vertexes.containsKey(v1))
                    vertexes.get(v1).add(v2);
                else {
                    Set<Integer> list = new TreeSet<>();
                    list.add(v2);
                    vertexes.put(v1, list);
                }
            }
        }
    }
}
