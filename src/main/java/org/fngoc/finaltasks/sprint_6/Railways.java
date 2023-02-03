package org.fngoc.finaltasks.sprint_6;

/*
-- ПРИНЦИП РАБОТЫ --
Реализовал расчет оптимальности железнодорожных путей.

Оптимальный жд путь - тот что с одним валидным путем по B или R,
от 1 до столицы n. Для определения не валидного пути,
достаточно найти цикл в графе, следовательно, нужно
развернуть один из жд путей в обратную сторону.

-- ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ --
Считываю ребра графа в список смежности, контейнера map, где
ключом является номер вершины, а значением массив ребер,
который хранит номера городов.

Если дорога R, то разворачиваю ребро.

После чего по алгоритму DFS ищу цикл в графе.
Если цвет вершины серый, значит цикл найден
и жд не является оптимальной.

-- ВРЕМЕННАЯ СЛОЖНОСТЬ --
|V| - кол-во вершин.
|E| - кол-во ребер.

По алгоритму DFS нам необходимо посетить все вершины
и ребра, следовательно O(|V| + |E|).

-- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
В реализации графов, через лист смежности
мы постоянно занимаем O(V + E) памяти.

Так же дополнительно мы храним массив цветов
всех вершин O(V), но его можно не учитывать.
*/

import java.io.*;
import java.util.*;

public class Railways {

    private static int capital;
    private static int[] colors;
    private static Map<Integer, List<Integer>> vertexes;

    public static void main(String[] args) throws IOException {
        input();
        if (capital == 1)
            System.out.println("YES");
        else {
            if (!findCycleDFS())
                System.out.println("YES");
            else
                System.out.println("NO");
        }
    }

    private static boolean findCycleDFS() {
        for (int i = 1; i < capital; i++) {
            if (colors[i] == 0) {
                if (isCycle(i))
                    return true;
            }
        }
        return false;
    }

    private static boolean isCycle(int city) {
        Stack<Integer> stack = new Stack<>();
        stack.push(city);

        while (!stack.isEmpty()) {
            int currentCity = stack.pop();

            if (colors[currentCity] == 0) {
                colors[currentCity] = 1;
                stack.push(currentCity);

                List<Integer> roads = vertexes.get(currentCity);

                if (roads == null)
                    continue;

                for (Integer road : roads) {
                    if (colors[road] == 0)
                        stack.push(road);
                    else if (colors[road] == 1)
                        return true;
                }
            } else if (colors[currentCity] == 1)
                colors[currentCity] = 2;
        }
        return false;
    }

    private static void input() throws IOException {
        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            capital = Integer.parseInt(tokenizer.nextToken());

            vertexes = new HashMap<>();
            colors = new int[capital + 1];

            for (int i = 0; i < capital - 1; i++) {
                tokenizer = new StringTokenizer(reader.readLine());
                String line = tokenizer.nextToken();

                for (int j = 0; j < line.length(); j++) {
                    putRailway(line.charAt(j), i, j);
                }
            }
        }
    }

    private static void putRailway(char railway, int cityFrom, int cityOn) {
        if (railway == 'B') {
            if (vertexes.containsKey(cityFrom))
                vertexes.get(cityFrom).add(cityFrom + cityOn + 1);
            else {
                List<Integer> list = new ArrayList<>();
                list.add(cityFrom + cityOn + 1);
                vertexes.put(cityFrom, list);
            }
        } else if (railway == 'R') {
            if (vertexes.containsKey(cityFrom + cityOn + 1))
                vertexes.get(cityFrom + cityOn + 1).add(cityFrom);
            else {
                List<Integer> list = new ArrayList<>();
                list.add(cityFrom);
                vertexes.put(cityFrom + cityOn + 1, list);
            }
        }
    }
}
