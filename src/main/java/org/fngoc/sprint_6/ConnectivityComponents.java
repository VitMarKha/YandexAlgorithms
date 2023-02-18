package org.fngoc.sprint_6;

import java.io.*;
import java.util.*;

/*
 * E. Компоненты связности
 */

public class ConnectivityComponents {

    private static int V; //ко-во вершины
    private static int E; //ко-во ребер
    private static Map<Integer, List<Integer>> vertexes; //список смежности
    private static Map<Integer, Set<Integer>> result;
    private static int[] colors;

    private static int countSegment = 1;

    public static void main(String[] args) throws IOException {
        connectivityComponents();
    }

    public static void connectivityComponents() throws IOException {
        inputConnectivityComponents();
        mainConnectivityComponentsDFS();
        outputConnectivityComponents();
    }

    private static void putResult(int v) {
        if (result.containsKey(countSegment)) {
            result.get(countSegment).add(v);
        } else {
            Set<Integer> list = new TreeSet<>();
            list.add(v);
            result.put(countSegment, list);
        }
    }

    private static void mainConnectivityComponentsDFS() throws IOException {
        for (int i = 1; i < V + 1; i++) {
            if (colors[i] == 0) {
                connectivityComponentsDFS(i, vertexes.get(i));
                countSegment += 1;
            }
        }
    }

    private static void connectivityComponentsDFS(int v, List<Integer> list) {
        colors[v] = countSegment;
        if (list != null) {
            for (int node : list) {
                if (colors[node] == 0)
                    connectivityComponentsDFS(node, vertexes.get(node));
            }
        }
        colors[v] = countSegment;
        putResult(v);
    }

    private static void outputConnectivityComponents() throws IOException {
        final BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder output = new StringBuilder();
        output.append(countSegment - 1).append("\n");

        for (Map.Entry entry : result.entrySet()) {
            for (Integer integer : (Set<Integer>) entry.getValue()) {
                output.append(integer).append(" ");
            }
            output.append("\n");
        }
        writer.write(output.toString());
        writer.flush();
    }

    private static void inputConnectivityComponents() throws IOException {
        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            V = Integer.parseInt(tokenizer.nextToken());
            E = Integer.parseInt(tokenizer.nextToken());

            colors = new int[V + 1];
            vertexes = new HashMap<>();
            result = new HashMap<>();

            for (int i = 0; i < E; i++) {
                tokenizer = new StringTokenizer(reader.readLine());

                int v1 = Integer.parseInt(tokenizer.nextToken());
                int v2 = Integer.parseInt(tokenizer.nextToken());

                if (!vertexes.containsKey(v1) && !vertexes.containsKey(v2)) {
                    List<Integer> listv1 = new ArrayList<>();
                    List<Integer> listv2 = new ArrayList<>();
                    listv1.add(v2);
                    listv2.add(v1);
                    vertexes.put(v1, listv1);
                    vertexes.put(v2, listv2);
                } else if (vertexes.containsKey(v1) && !vertexes.containsKey(v2)) {
                    vertexes.get(v1).add(v2);
                    List<Integer> listv2 = new ArrayList<>();
                    listv2.add(v1);
                    vertexes.put(v2, listv2);
                } else if (!vertexes.containsKey(v1) && vertexes.containsKey(v2)) {
                    vertexes.get(v2).add(v1);
                    List<Integer> listv1 = new ArrayList<>();
                    listv1.add(v2);
                    vertexes.put(v1, listv1);
                } else {
                    vertexes.get(v1).add(v2);
                    vertexes.get(v2).add(v1);
                }
            }
        }
    }
}
