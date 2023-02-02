package org.fngoc.finaltasks.sprint_6;

import java.io.*;
import java.util.*;

public class Railways {

    private static class Road implements Comparable<Road> {

        private final int city;
        private final char railway;

        public Road(int city, char railway) {
            this.city = city;
            this.railway = railway;
        }

        public int getCity() {
            return city;
        }

        public int getRailway() {
            return railway;
        }

        @Override
        public String toString() {
            return "Road{" +
                    "city=" + city +
                    ", railway=" + railway +
                    '}';
        }

        @Override
        public int compareTo(Road o) {
            return this.getCity() - o.getCity();
        }
    }

    private static int capital;
//    private static int[] colors;
    private static boolean[] visited;
    private static Map<Integer, Set<Road>> vertexes;

    public static void main(String[] args) throws IOException {
        input();
        if (capital == 1)
            System.out.println("YES");
        else
            output(findWayBFS('B'), findWayBFS('R'));
//            output(findWayDFS('B'), findWayDFS('R'));
    }

//    private static boolean findWayDFS(char railway) {
//        for (int i = 1; i < visited.length; i++) {
//            if (!visited[i]) {
//                if (isCycleDFS(i, railway))
//                    return true;
//                else
//                    isCycleDFS(i, railway);
//            }
//        }
//        return true;
//    }
//
//    private static boolean isCycleDFS(int city, char railway) {
//        if (city == capital) {
//            System.out.println("IS DONE CAPITAL");
//            return false;
//        }
//        visited[city] = true;
//
//        Set<Road> roads = vertexes.get(city);
//
//        for (Road road : roads) {
//            if (!visited[road.getCity()] && road.getRailway() == railway)
//                isCycleDFS(road.getCity(), railway);
//            else {
//                System.out.println("cycle");
//                return true;
//            }
//        }
//        return false;
//    }

    //colors[road.getCity()] == 0 &&

    private static boolean findWayBFS(char railway) {
        Queue<Integer> planned = new ArrayDeque<>();
        planned.add(1);

        while (!planned.isEmpty()) {
            int currentCity = planned.remove();
            if (currentCity == capital)
                return true;

            Set<Road> roads = vertexes.get(currentCity);
            if (roads == null)
                continue;

            for (Road road : roads) {
                if (road.getRailway() == railway)
                    planned.add(road.getCity());
            }
        }
        return false;
    }

    private static void input() throws IOException {
        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            capital = Integer.parseInt(tokenizer.nextToken());

            vertexes = new HashMap<>();
            visited = new boolean[capital + 1];

            for (int i = 1; i < capital; i++) {
                tokenizer = new StringTokenizer(reader.readLine());
                String line = tokenizer.nextToken();

                Set<Road> roads = new TreeSet<>();
                int nextCityIterator = 1;
                for (int j = 0; j < line.length(); j++) {
                    roads.add(new Road(i + nextCityIterator, line.charAt(j)));
                    nextCityIterator += 1;
                }
                vertexes.put(i, roads);
            }
        }
    }

    private static void output(boolean B, boolean R) {
        if ((B && R) || (!B && !R))
            System.out.print("NO");
        else
            System.out.print("YES");

//        for (Map.Entry entry : vertexes.entrySet()) {
//            System.out.println(entry.getKey() + " " + entry.getValue());
//        }
    }
}
