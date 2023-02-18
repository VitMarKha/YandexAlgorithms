package org.fngoc.sprint_6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
 * C. DFS
 */

public class DFS {

    private static final class Node implements Comparable<Node>{
        private final int value;
        private Color color = Color.WHITE;
        private List<Node> ribs = new ArrayList<>();

        public Node(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        public Color getColor() {
            return color;
        }

        public List<Node> getRibs() {
            return ribs;
        }

        public void setColor(Color color) {
            this.color = color;
        }

        @Override
        public int compareTo(Node o) {
            return this.getValue() - o.getValue();
        }
    }

    private enum Color {
        WHITE,
        GREY,
        BLACK
    }

    private static int N;

    public static void main(String[] args) throws IOException {
        mainDFS(outputGraph());
    }

    public static void mainDFS(Node[] nodes) {
        if (nodes[N] != null)
            DFS(nodes[N]);
        else
            System.out.println(N);
    }

    public static void DFS(Node v) {
        v.setColor(Color.GREY);
        System.out.print(v.getValue() + " ");
        Collections.sort(v.getRibs());
        for (Node node : v.getRibs()) {
            if (node.getColor() == Color.WHITE)
                DFS(node);
        }
        v.setColor(Color.BLACK);
    }

    public static Node[] outputGraph() throws IOException {
        //input
        final int V; //ко-во вершины
        final int E; //ко-во ребра
        Node[] tops;
        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            V = Integer.parseInt(tokenizer.nextToken());
            E = Integer.parseInt(tokenizer.nextToken());

            tops = new Node[V + 1];

            for (int i = 0; i < E; i++) {
                tokenizer = new StringTokenizer(reader.readLine());
                Node v1 = new Node(Integer.parseInt(tokenizer.nextToken()));
                Node v2 = new Node(Integer.parseInt(tokenizer.nextToken()));

                if (tops[v1.getValue()] == null && tops[v2.getValue()] == null) {
                    tops[v1.getValue()] = v1;
                    tops[v2.getValue()] = v2;
                    tops[v1.getValue()].getRibs().add(tops[v2.getValue()]);
                    tops[v2.getValue()].getRibs().add(tops[v1.getValue()]);
                } else if (tops[v1.getValue()] != null && tops[v2.getValue()] == null) {
                    tops[v2.getValue()] = v2;
                    tops[v1.getValue()].getRibs().add(tops[v2.getValue()]);
                    tops[v2.getValue()].getRibs().add(tops[v1.getValue()]);
                } else if (tops[v1.getValue()] == null && tops[v2.getValue()] != null) {
                    tops[v1.getValue()] = v1;
                    tops[v1.getValue()].getRibs().add(tops[v2.getValue()]);
                    tops[v2.getValue()].getRibs().add(tops[v1.getValue()]);
                } else {
                    tops[v1.getValue()].getRibs().add(tops[v2.getValue()]);
                    tops[v2.getValue()].getRibs().add(tops[v1.getValue()]);
                }
            }
            tokenizer = new StringTokenizer(reader.readLine());
            N = Integer.parseInt(tokenizer.nextToken());
        }
        return tops;
    }
}
