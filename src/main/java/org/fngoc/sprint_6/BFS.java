package org.fngoc.sprint_6;

import java.io.*;
import java.util.*;

/*
 * D. BFS
 */

public class BFS {

    private static int V;
    private static int E;
    private static int S;

    private static int[] colors;
    private static Map<Integer, List<Integer>> vertexes;
    private static StringBuilder output = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BFS();
    }

    public static void BFS() throws IOException {
        inputBFS();
        Queue<Integer> planned = new ArrayDeque<>();
        planned.add(S);

        while (!planned.isEmpty()) {
            int v = planned.remove();
            colors[v] = 1;
            output.append(v).append(" ");
            List<Integer> list = vertexes.get(v);
            if (list == null)
                continue;
            Collections.sort(list);

            for (Integer integer : list) {
                if (colors[integer] == 0) {
                    colors[integer] = 1;
                    planned.add(integer);
                }
            }

            colors[v] = 2;
        }
        final BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        writer.write(output.toString());
        writer.flush();
    }

    private static void inputBFS() throws IOException {
        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            V = Integer.parseInt(tokenizer.nextToken());
            E = Integer.parseInt(tokenizer.nextToken());

            colors = new int[V + 1];
            vertexes = new HashMap<>();

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
            tokenizer = new StringTokenizer(reader.readLine());
            S = Integer.parseInt(tokenizer.nextToken());
        }
    }
}
