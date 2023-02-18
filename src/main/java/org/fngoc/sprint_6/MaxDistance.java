package org.fngoc.sprint_6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
 * G. Максимальное расстояние
 */

public class MaxDistance {

    private static int V;
    private static int E;
    private static int S;

    private static int maxDistant;

    private static int[] colors;
    private static int[] distance;
    private static int[] previus;
    private static Map<Integer, List<Integer>> vertexes;

    public static void main(String[] args) throws IOException {
        maxDistance();
    }

    public static void maxDistance() throws IOException {
        inputBFS();
        Queue<Integer> planned = new ArrayDeque<>();
        planned.add(S);
        distance[S] = 0;

        while (!planned.isEmpty()) {
            int parent = planned.remove();
            colors[parent] = 1;
            List<Integer> list = vertexes.get(parent);
            if (list == null)
                continue;

            for (Integer integer : list) {
                if (colors[integer] == 0) {
                    previus[integer] = parent;
                    distance[integer] = distance[parent] + 1;
                    colors[integer] = 1;
                    if (distance[integer] > maxDistant)
                        maxDistant = distance[integer];
                    planned.add(integer);
                }
            }

            colors[parent] = 2;
        }
        System.out.print(maxDistant);
    }

    private static void inputBFS() throws IOException {
        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            V = Integer.parseInt(tokenizer.nextToken());
            E = Integer.parseInt(tokenizer.nextToken());

            colors = new int[V + 1];
            distance = new int[V + 1];
            previus = new int[V + 1];
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
