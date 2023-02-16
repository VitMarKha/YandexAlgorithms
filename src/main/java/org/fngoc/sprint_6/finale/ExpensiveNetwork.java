package org.fngoc.sprint_6.finale;

/*
-- ПРИНЦИП РАБОТЫ --
Реализовал поиск максимального остовного дерева
с помощью алгоритма Прима, с использованием очереди с приоритетом.

Граф храню в списке смежности.

-- ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ --
Считываю ребра графа в список смежности, массива листов, где
по индексу массива (номер вершины) находятся все его ребра
класса Edge, который хранит конец ребра и его вес.

После чего по алгоритму Прима иду с 1 вершины по ребрам с большим весом
складывая их значения в переменную на вывод.

Для хранения ребер, во время выбора максимального веса,
использую очередь с приоритетом. Для сравнения ребер,
добавил в класс Edge функцию compareTo.

В случае если остались не посещенные вершины или если
есть в графе несколько компонент связности, вывожу ошибку.

-- ВРЕМЕННАЯ СЛОЖНОСТЬ --
|V| - кол-во вершин.
|E| - кол-во ребер.

Алгоритму Прима для поиска "требуется число шагов,
пропорциональное количеству вершин". То есть, он работает за O(|V| * |E|).
Но благодаря очереди с приоритетом, в котором получение
и удаление элемента происходит за O(logN), нам не требуется искать
ребро с большим весом, мы его получаем сразу по приоритету.

Таким образом временная сложность программы O(|E| * log|V|).

-- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
В реализации графов, через лист смежности мы постоянно занимаем O(V + E).

Так же дополнительно мы храним boolean массив всех
не посещенных вершин за O(V), и ребра отдельной вершины,
но это можно не учитывать.
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class ExpensiveNetwork {

    private static class Edge implements Comparable<Edge> {

        private final int end;
        private final int weight;

        public Edge(int end, int weight) {
            this.end = end;
            this.weight = weight;
        }

        public int getEnd() {
            return end;
        }

        public int getWeight() {
            return weight;
        }

        @Override
        public int compareTo(Edge o) {
            return o.getWeight() - this.getWeight();
        }
    }

    private static int V;
    private static int E;
    private static int maxSpanningTree;
    private static List<Edge>[] vertexes;
    private static Queue<Edge> edges;
    private static boolean[] nodeNotAdded;
    private static int counterNotAddedNode = 0;

    public static void main(String[] args) throws IOException {
        input();
        findMST();
        output();
    }

    private static void findMST() {
        addVertex(1);

        while (counterNotAddedNode > 0 && !edges.isEmpty()) {
            Edge edge = getExtractMaxEdge();

            if (nodeNotAdded[edge.getEnd()]) {
                maxSpanningTree += edge.getWeight();
                addVertex(edge.getEnd());
            }
        }
    }

    private static Edge getExtractMaxEdge() {
        return edges.remove();
    }

    private static void addVertex(int v) {
        nodeNotAdded[v] = false;
        counterNotAddedNode -= 1;

        List<Edge> list = vertexes[v];
        if (list == null)
            return;

        for (Edge edge : list) {
            if (nodeNotAdded[edge.getEnd()])
                edges.add(edge);
        }
    }

    private static void input() throws IOException {
        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            V = Integer.parseInt(tokenizer.nextToken());
            E = Integer.parseInt(tokenizer.nextToken());

            vertexes = new List[V + 1];
            edges = new PriorityQueue<>();
            nodeNotAdded = new boolean[V + 1];

            for (int i = 0; i < E; i++) {
                tokenizer = new StringTokenizer(reader.readLine());

                int v1 = Integer.parseInt(tokenizer.nextToken());
                int v2 = Integer.parseInt(tokenizer.nextToken());
                int weight = Integer.parseInt(tokenizer.nextToken());

                if (v1 != v2)
                    putWay(v1, v2, weight);
            }
        }
    }

    private static void putWay(int v1, int v2, int weight) {
        if (!nodeNotAdded[v1]) {
            nodeNotAdded[v1] = true;
            counterNotAddedNode += 1;
        }
        if (!nodeNotAdded[v2]) {
            nodeNotAdded[v2] = true;
            counterNotAddedNode += 1;
        }

        if (vertexes[v1] == null)
            vertexes[v1] = new ArrayList<>();
        if (vertexes[v2] == null)
            vertexes[v2] = new ArrayList<>();

        vertexes[v1].add(new Edge(v2, weight));
        vertexes[v2].add(new Edge(v1, weight));
    }

    private static void output() {
        if (counterNotAddedNode > 0 || V - 1 > E)
            System.out.println("Oops! I did it again");
        else
            System.out.println(maxSpanningTree);
    }
}
