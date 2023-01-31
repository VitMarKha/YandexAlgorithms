package org.fngoc.finaltasks.sprint_6;

/*
-- ПРИНЦИП РАБОТЫ --
Реализовал поиск максимального остовного дерева
с помощью алгоритма Прима, с использованием очереди с приоритетом.

Граф храню в списке смежности.

-- ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ --
Считываю ребра графа в список смежности, контейнера map, где
ключом является номер вершины, а значением множество уникальных ребер
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

Так же дополнительно мы храним множество всех не посещенных вершин за O(V),
и ребра отдельной вершины, но это можно не учитывать, так как по мере
выполнения программы элементы удаляются из множеств.
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
    private static Map<Integer, Set<Edge>> vertexes;
    private static Queue<Edge> edges;
    private static Set<Integer> nodeNotAdded;

    public static void main(String[] args) throws IOException {
        input();
        findMST();
        output();
    }

    private static void findMST() {
        addVertex(1);

        while (!nodeNotAdded.isEmpty() && !edges.isEmpty()) {
            Edge edge = getExtractMaxEdge();

            if (nodeNotAdded.contains(edge.getEnd())) {
                maxSpanningTree += edge.getWeight();
                addVertex(edge.getEnd());
            }
        }
    }

    private static Edge getExtractMaxEdge() {
        return edges.remove();
    }

    private static void addVertex(int v) {
        nodeNotAdded.remove(v);

        Set<Edge> set = vertexes.get(v);
        if (set == null)
            return;

        for (Edge edge : set) {
            if (nodeNotAdded.contains(edge.getEnd()))
                edges.add(edge);
        }
    }

    private static void input() throws IOException {
        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            V = Integer.parseInt(tokenizer.nextToken());
            E = Integer.parseInt(tokenizer.nextToken());

            vertexes = new HashMap<>();
            edges = new PriorityQueue<>();
            nodeNotAdded = new HashSet<>();

            for (int i = 0; i < E; i++) {
                tokenizer = new StringTokenizer(reader.readLine());

                int v1 = Integer.parseInt(tokenizer.nextToken());
                int v2 = Integer.parseInt(tokenizer.nextToken());
                int weight = Integer.parseInt(tokenizer.nextToken());

                nodeNotAdded.add(v1);
                nodeNotAdded.add(v2);

                if (!vertexes.containsKey(v1) && !vertexes.containsKey(v2)) {
                    Set<Edge> set1 = new HashSet<>();
                    Set<Edge> set2 = new HashSet<>();
                    set1.add(new Edge(v2, weight));
                    set2.add(new Edge(v1, weight));
                    vertexes.put(v1, set1);
                    vertexes.put(v2, set2);
                } else if (vertexes.containsKey(v1) && !vertexes.containsKey(v2)) {
                    vertexes.get(v1).add(new Edge(v2, weight));
                    Set<Edge> set2 = new HashSet<>();
                    set2.add(new Edge(v1, weight));
                    vertexes.put(v2, set2);
                } else if (!vertexes.containsKey(v1) && vertexes.containsKey(v2)) {
                    vertexes.get(v2).add(new Edge(v1, weight));
                    Set<Edge> set1 = new HashSet<>();
                    set1.add(new Edge(v2, weight));
                    vertexes.put(v1, set1);
                } else {
                    vertexes.get(v1).add(new Edge(v2, weight));
                    vertexes.get(v2).add(new Edge(v1, weight));
                }
            }
        }
    }

    private static void output() {
        if (!nodeNotAdded.isEmpty() || V - 1 > E)
            System.out.println("Oops! I did it again");
        else
            System.out.println(maxSpanningTree);
    }
}
