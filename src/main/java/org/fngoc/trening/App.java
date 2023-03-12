package org.fngoc.trening;

import java.io.*;
import java.util.*;

public class App {

    public static void main(String[] args) throws Exception {
        System.out.println("Start program!\n");



        System.out.println("\nEnd program!");
    }


    private static int V; //вершины
    private static int E; //ребра
    private static boolean[] visited;
    private static int[] dist;
    private static int[] previus;

    private static int[][] adjacencyMatrix; //матрица смежности

    public static void attractions() throws IOException {
        //input
        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            V = Integer.parseInt(tokenizer.nextToken());
            E = Integer.parseInt(tokenizer.nextToken());

            adjacencyMatrix = new int[V][V];
            visited = new boolean[V];
            dist = new int[V];
            previus = new int[V];

            for (int i = 0; i < E; i++) {
                tokenizer = new StringTokenizer(reader.readLine());
                int v1 = Integer.parseInt(tokenizer.nextToken()) - 1;
                int v2 = Integer.parseInt(tokenizer.nextToken()) - 1;
                int distant = Integer.parseInt(tokenizer.nextToken());

                adjacencyMatrix[v1][v2] = distant;
                adjacencyMatrix[v2][v1] = distant;
            }
        }

        //program
        dijkstra();

        //output
        for (int[] matrix : adjacencyMatrix) {
            for (int i : matrix)
                System.out.print(i + " ");
            System.out.println();
        }
    }

    private static void dijkstra() {
        for (int i = 0; i < V; i++) {
            dist[i] = Integer.MAX_VALUE;
            previus[i] = 0;
            visited[i] = false;
        }

        while (!isVisitAll(visited)) {
            int u = getMinDistNotVisitedVertex();

            visited[u] = true;

            int[] neighbours = vertexByHead(u);

            for (int neighbour : neighbours) {
                if (neighbour != 0)
                    relax(u, neighbour);
            }
        }
    }

    private static void relax(int u, int neighbour) {
        if (dist[neighbour] > dist[u] + adjacencyMatrix[u][neighbour]) {
            dist[neighbour] = dist[u] + adjacencyMatrix[u][neighbour];
            previus[neighbour] = u;
            System.out.println(u);
        }
    }

    private static boolean isVisitAll(boolean[] visited) {
        for (int i = 0; i < visited.length; i++) {
            if (!visited[i] && dist[i] != Integer.MAX_VALUE)
                return true;
        }
        return false;
    }

    private static Integer getMinDistNotVisitedVertex() {
        int currentMin = Integer.MAX_VALUE;
        Integer currentMinVertex = null;

        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                if (!visited[i] && adjacencyMatrix[i][j] < currentMin) {
                    currentMin = dist[i];
                    currentMinVertex = i;
                }
            }
        }
        return currentMinVertex;
    }

    private static int[] vertexByHead(int v) {
        int[] result = new int[V];

        for (int i = 0; i < V; i++) {
            if (adjacencyMatrix[v][i] != 0)
                result[i] = i;
        }
        return result;
    }

    public static void sum4String() throws IOException {
        //input
        final int N;
        final int S;
        final int[] array;
        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            N = Integer.parseInt(reader.readLine());
            S = Integer.parseInt(reader.readLine());
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());

            array = new int[N];
            for (int i = 0; i < N; i++)
                array[i] = Integer.parseInt(tokenizer.nextToken());
        }

        //program
        Arrays.sort(array);

        final Set<Integer> history = new HashSet<>();
        final Set<String> result = new HashSet<>();

        for (int i = 0; i < array.length; i++) {
            for (int j = i + 1; j < array.length; j++) {
                for (int k = j + 1; k < array.length; k++) {
                    int target = S - array[i] - array[j] - array[k];

                    if (history.contains(target)) {
                        String tmpArray = target + " " + array[i] + " " + array[j] + " " + array[k];
                        result.add(tmpArray);
                    }
                }
            }
            history.add(array[i]);
        }

        //output
        System.out.println(result.size());
        for (String s : result) {
            System.out.println(s);
        }
    }

    public static void sum4() throws IOException {
        //input
        final int N;
        final int S;
        final int[] array;
        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            N = Integer.parseInt(reader.readLine());
            S = Integer.parseInt(reader.readLine());
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());

            array = new int[N];
            for (int i = 0; i < N; i++)
                array[i] = Integer.parseInt(tokenizer.nextToken());
        }

        //program
        Arrays.sort(array);
        final Set<Integer> history = new HashSet<>();
        final List<int[]> result = new ArrayList<>();
        for (int i = 0; i < array.length; i++) {
            for (int j = i + 1; j < array.length; j++) {
                for (int k = j + 1; k < array.length; k++) {
                    int target = S - array[i] - array[j] - array[k];

                    if (history.contains(target)) {
                        int[] arr = new int[] {target, array[i], array[j], array[k]};
                        if (!compareInts(result, arr))
                            result.add(arr);
                    }
                }
            }
            history.add(array[i]);
        }
        result.sort((one, two) -> one[0] - two[0] - one[1] - two[1] - one[2] - two[2] - one[3] - two[3]);

        //output
        System.out.println(result.size());
        for (int[] ints: result) {
            for (int anInt : ints) {
                System.out.print(anInt + " ");
            }
            System.out.println();
        }
    }

    private static boolean compareInts(List<int[]> result, int[] arr) {
        for (int[] ints : result) {
            if (ints[0] == arr[0] && ints[1] == arr[1] && ints[2] == arr[2] && ints[3] == arr[3])
                return true;
        }
        return false;
    }

    public static void cookies() throws IOException {
        //input
        final int N;
        final int M;
        final Integer[] kids;
        final List<Integer> cookies = new ArrayList<>();
        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            N = Integer.parseInt(reader.readLine());
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());

            kids = new Integer[N];
            for (int i = 0; i < N; i++)
                kids[i] = Integer.parseInt(tokenizer.nextToken());

            M = Integer.parseInt(reader.readLine());
            tokenizer = new StringTokenizer(reader.readLine());
            for (int i = 0; i < M; i++)
                cookies.add(Integer.parseInt(tokenizer.nextToken()));
        }

        //program
        Collections.sort(cookies);
        int countHappyChildren = 0;
        for (int i = 0; i < N; i++) {
            if (cookies.contains(kids[i])) {
                countHappyChildren += 1;
                cookies.remove(kids[i]);
            } else
                countHappyChildren += findMinCookieByFactor(cookies, kids[i]);
        }

        //output
        System.out.print(countHappyChildren);
    }

    private static int findMinCookieByFactor(List<Integer> cookies, int kidFactor) {
        for (Integer cookie : cookies) {
            if (cookie > kidFactor) {
                cookies.remove(cookie);
                return 1;
            }
        }
        return 0;
    }

//    private static int binSearchMinCookieByFactor(List<Integer> cookies, int kidFactor) {
//        int mid;
//        int min = 0;
//        int max = cookies.size();
//
//        while (min < max) {
//            mid = (min + max) / 2;
//
//            if (cookies.get(mid) < kidFactor) {
//                for (int i = mid + 1; i < cookies.size(); i++) {
//                    if (cookies.get(i) > kidFactor) {
//                        cookies.remove(mid);
//                        return 1;
//                    }
//                }
//            }
//            else if (kidFactor < cookies.get(mid))
//                max = mid;
//            else
//                min = mid + 1;
//        }
//        return 0;
//    }

    public static int[] merge(int[] arr, int left, int mid, int right) {
        int[] result = new int[arr.length];
        int midOld = mid;

        int i = 0;
        while (left < midOld && mid < right) {
            if (arr[left] <= arr[mid]) {
                result[i] = arr[left];
                left += 1;
            } else {
                result[i] = arr[mid];
                mid += 1;
            }
            i += 1;
        }

        while (left < midOld) {
            result[i] = arr[left];
            left += 1;
            i += 1;
        }
        while (mid < right) {
            result[i] = arr[mid];
            mid += 1;
            i += 1;
        }
        return result;
    }

    public static void merge_sort(int[] arr, int left, int right) {
        if (right - left <= 1)
            return;

        int mid = (left + right) / 2;

        merge_sort(arr, 0, mid);
        merge_sort(arr, mid, right);

        int[] res = merge(arr, left, mid, right);
        for (int i = left; i < right; i++) {
            arr[i] = res[i - left];
        }
    }

    public static void subsequence() throws IOException {
        //input
        String S;
        String T;
        final BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            S = reader.readLine();
            T = reader.readLine();
        }

        //program and output
        if (isSubsequence(S, T))
            writer.write("True");
        else
            writer.write("False");
        writer.flush();
    }

    private static boolean isSubsequence(String S, String T) {
        char desiredChar;
        int countCurrentFind = 0;
        int startIndex = 0;
        for (int i = 0; i < S.length(); i++) {
            desiredChar = S.charAt(i);

            for (int j = startIndex; j < T.length(); j++) {
                if (T.charAt(j) == desiredChar) {
                    startIndex = j + 1;
                    countCurrentFind += 1;
                    break;
                } else if (T.length() == j + 1)
                    return false;
            }
        }
        return countCurrentFind == S.length();
    }

    private static class Flowerbed {
        public int start;
        public int end;

        public Flowerbed(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public String toString() {
            return start + " " + end;
        }
    }

    public static void flowerbeds() throws IOException {
        //input
        final int N;
        List<Flowerbed> segments = new ArrayList<>();
        StringBuilder output = new StringBuilder();
        final BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            N = Integer.parseInt(reader.readLine());
            StringTokenizer tokenizer;

            for (int i = 0; i < N; i++) {
                tokenizer = new StringTokenizer(reader.readLine());
                segments.add(new Flowerbed(Integer.parseInt(tokenizer.nextToken()),
                        Integer.parseInt(tokenizer.nextToken())));
            }
        }

        //program
//        boolean flag = true;
//        while (flag) {
//            int reminderLenSegments = segments.size();
        mergerSegments(segments, 0, 1);
//            if (reminderLenSegments == segments.size())
//                flag = false;
//        }
        sortSegments(segments);

        //output
        for (Flowerbed flowerbed : segments)
            output.append(flowerbed.start).append(" ").append(flowerbed.end).append("\n");
        writer.write(output.toString());
        writer.flush();
    }

    private static void sortSegments(List<Flowerbed> list) {
        for (int j = 0; j < list.size(); j++) {
            for (int i = 0; i < list.size(); i++)
                if (i + 1 < list.size() && comparatorSegments(list.get(i), list.get(i + 1)))
                    swapSegments(list, i);
        }
    }

    private static boolean comparatorSegments(Flowerbed first, Flowerbed second) {
        return first.start > second.start && first.end > second.end;
    }

    private static void swapSegments(List<Flowerbed> list, int i) {
        Flowerbed tmp = list.get(i + 1);
        list.set(i + 1, list.get(i));
        list.set(i, tmp);
    }

    private static void mergerSegments(List<Flowerbed> segments, int indexFirst, int indexSecond) {
        if (segments.size() <= indexFirst || segments.size() <= indexSecond)
            return;
        else {
            for (int i = 0; i < segments.size(); i++) {
                mergerSegments(segments, i, i + 1);
            }

            Flowerbed flowerbedFirst = segments.get(indexFirst);
            Flowerbed flowerbedSecond = segments.get(indexSecond);

            if (flowerbedFirst.end == flowerbedSecond.end && flowerbedFirst.start == flowerbedSecond.start) {
                segments.remove(flowerbedFirst);
//            mergerSegments(segments, indexFirst, indexSecond);
            }
            else if (flowerbedFirst.start <= flowerbedSecond.start && flowerbedSecond.end <= flowerbedFirst.end) {
                segments.remove(flowerbedSecond);
//            mergerSegments(segments, indexFirst, indexSecond);
            }
            else if (flowerbedFirst.end == flowerbedSecond.start && flowerbedFirst.end <= flowerbedSecond.end) {
                flowerbedSecond.start = flowerbedFirst.start;
                segments.remove(flowerbedFirst);
//            mergerSegments(segments, indexFirst, indexSecond);
            }
        }


//        for (int k = 0; k < segments.size(); k++) {


//            for (int i = 0; i < segments.size(); i++) {


//            }
//        }
    }
}
