package org.fngoc.finaltasks.sprint_6;

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
                    if (line.charAt(j) == 'B') {
                        if (vertexes.containsKey(i))
                            vertexes.get(i).add(i + j + 1);
                        else {
                            List<Integer> list = new ArrayList<>();
                            list.add(i + j + 1);
                            vertexes.put(i, list);
                        }
                    } else if (line.charAt(j) == 'R') {
                        if (vertexes.containsKey(i + j + 1))
                            vertexes.get(i + j + 1).add(i);
                        else {
                            List<Integer> list = new ArrayList<>();
                            list.add(i);
                            vertexes.put(i + j + 1, list);
                        }
                    }
                }
            }
        }
    }
}
