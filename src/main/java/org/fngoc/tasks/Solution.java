package org.fngoc.tasks;

import java.io.*;
import java.math.BigInteger;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Solution {

    ///////////////////Гороскопы///////////////////
    private static int N4;
    private static int M4;
    private static int[] firstSubsequence;
    private static int[] secondSubsequence;
    private static List<Integer> indexesFirstSubsequence;
    private static List<Integer> indexesSecondSubsequence;
    private static int[][] dp4;

    public static void horoscopes() throws IOException {
        inputHoroscopes();

        dp4 = new int[N4 + 1][M4 + 1];

        for (int i = 1; i <= N4; i++) {
            for (int j = 1; j <= M4; j++) {
                if (firstSubsequence[i] == secondSubsequence[j])
                    dp4[i][j] = dp4[i - 1][j - 1] + 1;
                else
                    dp4[i][j] = Math.max(dp4[i - 1][j], dp4[i][j - 1]);
            }
        }

        int i = firstSubsequence.length - 1;
        int j = secondSubsequence.length - 1;
        indexesFirstSubsequence = new ArrayList<>();
        indexesSecondSubsequence = new ArrayList<>();
        while (i > 0 && j > 0) {
            if (firstSubsequence[i] == secondSubsequence[j]) {
                indexesFirstSubsequence.add(i);
                indexesSecondSubsequence.add(j);
                i -= 1;
                j -= 1;
            } else {
                if (dp4[i][j] == dp4[i - 1][j])
                    i -= 1;
                else
                    j -= 1;
            }
        }
        outputHoroscopes();
    }

    private static void outputHoroscopes() {
        System.out.println(dp4[N4][M4]);

        for (int i = indexesFirstSubsequence.size() - 1; i >= 0; i--)
            System.out.print(indexesFirstSubsequence.get(i) + " ");
        System.out.println();
        for (int i = indexesSecondSubsequence.size() - 1; i >= 0; i--)
            System.out.print(indexesSecondSubsequence.get(i) + " ");
    }

    private static void inputHoroscopes() throws IOException {
        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            N4 = Integer.parseInt(tokenizer.nextToken());

            tokenizer = new StringTokenizer(reader.readLine());
            firstSubsequence = new int[N4 + 1];
            for (int i = 1; i < N4 + 1; i++) {
                firstSubsequence[i] = Integer.parseInt(tokenizer.nextToken());
            }

            tokenizer = new StringTokenizer(reader.readLine());
            M4 = Integer.parseInt(tokenizer.nextToken());

            tokenizer = new StringTokenizer(reader.readLine());
            secondSubsequence = new int[M4 + 1];
            for (int i = 1; i < M4 + 1; i++) {
                secondSubsequence[i] = Integer.parseInt(tokenizer.nextToken());
            }
        }
    }

    ///////////////////Золото лепреконов///////////////////
    private static int N5;
    private static int M5;
    private static int[] weight;
    private static int[][] dp5;

    public static void leprechaunGold() throws IOException {
        inputLeprechaunGold();

        dp5 = new int[N5 + 1][M5 + 1];

        for (int i = 1; i <= N5; i++) {
            for (int j = 1; j <= M5; j++) {
                if (j - weight[i] < 0)
                    dp5[i][j] = dp5[i - 1][j];
                else
                    dp5[i][j] = Math.max(dp5[i - 1][j], weight[i] + dp5[i - 1][j - weight[i]]);
            }
        }
        outputLeprechaunGold();
    }

    private static void outputLeprechaunGold() {
        System.out.println(dp5[N5][M5]);
    }

    private static void inputLeprechaunGold() throws IOException {
        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            N5 = Integer.parseInt(tokenizer.nextToken());
            M5 = Integer.parseInt(tokenizer.nextToken());

            weight = new int[N5 + 1];

            tokenizer = new StringTokenizer(reader.readLine());
            for (int i = 1; i < N5 + 1; i++) {
                weight[i] = Integer.parseInt(tokenizer.nextToken());
            }
        }
    }

    ///////////////////Сложное поле с цветочками///////////////////
    private static int N3;
    private static int M3;
    private static int[][] dp3;
    private static int[][] dpSumWay;
    private static int[][] field2;

    public static void complexFieldFlowers() throws IOException {
        inputComplexFieldFlowers();

        dp3 = new int[N3 + 1][M3 + 1];
        dpSumWay = new int[N3][M3];

        dp3[0][0] = field2[0][0];
        dpSumWay[0][0] = field2[0][0];

        for (int i = 1; i < N3 + 1; i++) {
            for (int j = 1; j < M3 + 1; j++) {
                int sum = Math.max(dp3[i - 1][j], dp3[i][j - 1]) + field[i - 1][j - 1];
                dp3[i][j] = sum;
                dpSumWay[i - 1][j - 1] = sum;
            }
        }
        reverseArray(dpSumWay);

        List<Character> way = new ArrayList<>();
        int i = 0;
        int j = M3 - 1;
        while (i + 1 != N || j != 0) {
            if (j != 0 &&i + 1 != N ) {
                if (dpSumWay[i + 1][j] > dpSumWay[i][j - 1]) {
                    way.add('U');
                    i += 1;
                } else {
                    way.add('R');
                    j -= 1;
                }
            } else if (j == 0) {
                way.add('U');
                i += 1;
            } else if (i + 1 == N) {
                way.add('R');
                j -= 1;
            }
        }
        outputComplexFieldFlowers(way);
    }

    private static void outputComplexFieldFlowers(List<Character> way) {
        System.out.println(dp3[N3][M3]);

        for (int i = way.size() - 1; i >= 0; i--) {
            System.out.print(way.get(i));
        }
    }

    private static void reverseArray(int[][] arr) {
        for (int i = 0; i < arr.length / 2; i++) {
            int[] tmp = arr[i];
            arr[i] = arr[arr.length - i - 1];
            arr[arr.length - i - 1] = tmp;
        }
    }

    private static void inputComplexFieldFlowers() throws IOException {
        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            N3 = Integer.parseInt(tokenizer.nextToken());
            M3 = Integer.parseInt(tokenizer.nextToken());

            field = new int[N3][M3];

            for (int i = 0; i < N3; i++) {
                tokenizer = new StringTokenizer(reader.readLine());
                String line = tokenizer.nextToken();
                for (int j = 0; j < M3; j++) {
                    field2[i][j] = Integer.parseInt(String.valueOf(line.charAt(j)));
                }
            }
            reverseArray(field2);
        }
    }

    ///////////////////Поле с цветочками///////////////////
    private static int N2;
    private static int M2;
    private static int[][] dp2;
    private static int[][] field;

    public static void fieldWithFlowers() throws IOException {
        input();

        dp2 = new int[N2 + 1][M2 + 1];
        dp2[0][0] = field[0][0];

        for (int i = 1; i < N2 + 1; i++) {
            for (int j = 1; j < M2 + 1; j++) {
                dp2[i][j] = Math.max(dp2[i - 1][j], dp2[i][j - 1]) + field[i - 1][j - 1];
            }
        }
        System.out.println(dp2[N2][M2]);
    }

    private static void input() throws IOException {
        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            N2 = Integer.parseInt(tokenizer.nextToken());
            M2 = Integer.parseInt(tokenizer.nextToken());

            field = new int[N2][M2];

            for (int i = 0; i < N2; i++) {
                tokenizer = new StringTokenizer(reader.readLine());
                String line = tokenizer.nextToken();
                for (int j = 0; j < M2; j++) {
                    field[i][j] = Integer.parseInt(String.valueOf(line.charAt(j)));
                }
            }

            for (int i = 0; i < field.length / 2; i++) {
                int[] tmp = field[i];
                field[i] = field[field.length - i - 1];
                field[field.length - i - 1] = tmp;
            }
        }
    }

    ///////////////////Прыжки по лестнице///////////////////
    private static int K;
    private static int N_STEPS;
    private static int MODULE = 1_000_000_007;

    private static int[] dp;

    public static void jumpingStairs() throws IOException {
        //input
        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            N_STEPS = Integer.parseInt(tokenizer.nextToken());
            K = Integer.parseInt(tokenizer.nextToken());

            dp = new int[N_STEPS + 1];
        }

        //program
        long result = countSteps(N_STEPS);

        //output
        System.out.println(result);
    }

    private static long countSteps(int number) {
        if (number < 1)
            return 0;
        else if (number == 1)
            return 1;
        else if (dp[number] == 0) {
            for (int i = 1; i <= K; i++) {
                dp[number] += countSteps(number - i);
                dp[number] %= MODULE;
            }
        }
        return dp[number] % MODULE;
    }

    ///////////////////Золотая лихорадка///////////////////
    private static class Heap implements Comparable<Heap>{
        private final long price;
        private final long count;

        public Heap(long price, long count) {
            this.price = price;
            this.count = count;
        }

        @Override
        public int compareTo(Heap o) {
            return (int) (o.price - this.price);
        }
    }

    private static long M;
    private static Heap[] heaps;
    private static long resultCountPrice;

    public static void counting() throws IOException {
        inputCounting();
        Arrays.sort(heaps);

        for (Heap heap : heaps) {
            if (M > 0) {
                for (int i = 0; i < heap.count; i++) {
                    if (M > 0) {
                        resultCountPrice += heap.price;
                        M -= 1;
                    } else
                        break;
                }
            } else
                break;
        }
        outputCounting();
    }

    private static void inputCounting() throws IOException {
        final long N;

        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            M = Long.parseLong(reader.readLine());
            N = Long.parseLong(reader.readLine());
            StringTokenizer tokenizer;

            heaps = new Heap[(int)N];

            for (int i = 0; i < N; i++) {
                tokenizer = new StringTokenizer(reader.readLine());
                long price = Long.parseLong(tokenizer.nextToken());
                long count = Long.parseLong(tokenizer.nextToken());

                heaps[i] = new Heap(price, count);
            }
        }
    }

    private static void outputCounting() {
        System.out.println(resultCountPrice);
    }

    ///////////////////Расписание///////////////////
    private static class Gap implements Comparable<Gap>{
        private Double start;
        private Double end;

        public Gap(Double start, Double end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Gap o) {
            if (this.end.equals(o.end))
                return (int) (this.start - o.start);
            return (int) (this.end - o.end);
        }

        @Override
        public String toString() {
            String resultStr;
            double a = start;
            long b = (long) a;

            if ((a - (double) b) > 0)
                resultStr = String.valueOf(start);
            else
                resultStr = String.valueOf(start.intValue());

            resultStr += " ";

            a = end;
            b = (long) a;

            if ((a - (double) b) > 0)
                resultStr += String.valueOf(end);
            else
                resultStr += end.intValue();

            return resultStr;
        }
    }

    private static Gap[] gaps;
    private static Stack<Gap> stackGaps;

    public static void schedule() throws IOException {
        scheduleInput();
        Arrays.sort(gaps);
        stackGaps = new Stack<>();
        stackGaps.push(gaps[0]);

        for (int i = 1; i < gaps.length; i++) {
            if (stackGaps.peek().end <= gaps[i].start)
                stackGaps.push(gaps[i]);
        }
        scheduleOutput();
    }

    private static void scheduleInput() throws IOException {
        final int N;

        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            N = Integer.parseInt(reader.readLine());
            StringTokenizer tokenizer;

            gaps = new Gap[N];

            for (int i = 0; i < N; i++) {
                tokenizer = new StringTokenizer(reader.readLine());
                double gapStart = Double.parseDouble(tokenizer.nextToken());
                double gapEnd = Double.parseDouble(tokenizer.nextToken());

                gaps[i] = new Gap(gapStart, gapEnd);
            }
        }
    }

    private static void scheduleOutput() {
        System.out.println(stackGaps.size());
        for (Gap gap : stackGaps) {
            System.out.println(gap);
        }
    }

    ///////////////////Биржа///////////////////
    private static int[] daysPrice;
    private static boolean purchase;
    private static int priceNow;
    private static int result;

    public static void greedyAlgorith(String[] args) throws IOException {
        greedyAlgorithmInput();
        greedyAlgorithmProgram();
        greedyAlgorithmOutput();
    }

    private static void greedyAlgorithmProgram() {
        if (isSorted(daysPrice)) {
            result = daysPrice[daysPrice.length - 1] - daysPrice[0];
            return;
        }

        for (int i = 0; i < daysPrice.length; i++) {
            if (!purchase || priceNow > daysPrice[i]) {
                priceNow = daysPrice[i];
                purchase = true;
            } else {
                if (i + 1 == daysPrice.length) {
                    if (daysPrice[daysPrice.length - 2] < daysPrice[daysPrice.length - 1])
                        result += daysPrice[daysPrice.length - 1] - daysPrice[daysPrice.length - 2];
                } else {
                    if (priceNow <= daysPrice[i] && daysPrice[i] >= daysPrice[i + 1]) {
                        result += daysPrice[i] - priceNow;
                        purchase = false;
                    } else if (priceNow <= daysPrice[i + 1]) {
                        result += daysPrice[i + 1] - priceNow;
                        purchase = false;
                    }
                }

            }
        }
    }

    public static boolean isSorted(int[] a) {
        if (a == null || a.length <= 1)
            return true;

        return IntStream.range(0, a.length - 1).noneMatch(i -> a[i] > a[i + 1]);
    }

    private static void greedyAlgorithmInput() throws IOException {
        final int N;

        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            N = Integer.parseInt(reader.readLine());
            StringTokenizer tokenizer;

            daysPrice = new int[N];

            tokenizer = new StringTokenizer(reader.readLine());
            for (int i = 0; i < N; i++) {
                int price = Integer.parseInt(tokenizer.nextToken());
                daysPrice[i] = price;
            }
        }
    }

    private static void greedyAlgorithmOutput() {
        System.out.println(result);
    }

    ///////////////////Максимальное расстояние///////////////////
    private static int V5;
    private static int E5;
    private static int S5;

    private static int maxDistant5;

    private static int[] colors5;
    private static int[] distance5;
    private static int[] previus5;
    private static Map<Integer, List<Integer>> vertexes5;

    public static void maxDistance() throws IOException {
        inputMaxDistance();
        Queue<Integer> planned = new ArrayDeque<>();
        planned.add(S5);
        distance5[S5] = 0;

        while (!planned.isEmpty()) {
            int parent = planned.remove();
            colors5[parent] = 1;
            List<Integer> list = vertexes5.get(parent);
            if (list == null)
                continue;

            for (Integer integer : list) {
                if (colors5[integer] == 0) {
                    previus5[integer] = parent;
                    distance5[integer] = distance5[parent] + 1;
                    colors5[integer] = 1;
                    if (distance5[integer] > maxDistant5)
                        maxDistant5 = distance5[integer];
                    planned.add(integer);
                }
            }

            colors5[parent] = 2;
        }

        System.out.print(maxDistant5);
    }

    private static void inputMaxDistance() throws IOException {
        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            V5 = Integer.parseInt(tokenizer.nextToken());
            E5 = Integer.parseInt(tokenizer.nextToken());

            colors = new int[V5 + 1];
            distance5 = new int[V5 + 1];
            previus5 = new int[V5 + 1];
            vertexes5 = new HashMap<>();

            for (int i = 0; i < E5; i++) {
                tokenizer = new StringTokenizer(reader.readLine());

                int v1 = Integer.parseInt(tokenizer.nextToken());
                int v2 = Integer.parseInt(tokenizer.nextToken());

                if (!vertexes5.containsKey(v1) && !vertexes5.containsKey(v2)) {
                    List<Integer> listv1 = new ArrayList<>();
                    List<Integer> listv2 = new ArrayList<>();
                    listv1.add(v2);
                    listv2.add(v1);
                    vertexes5.put(v1, listv1);
                    vertexes5.put(v2, listv2);
                } else if (vertexes5.containsKey(v1) && !vertexes5.containsKey(v2)) {
                    vertexes5.get(v1).add(v2);
                    List<Integer> listv2 = new ArrayList<>();
                    listv2.add(v1);
                    vertexes5.put(v2, listv2);
                } else if (!vertexes5.containsKey(v1) && vertexes5.containsKey(v2)) {
                    vertexes.get(v2).add(v1);
                    List<Integer> listv1 = new ArrayList<>();
                    listv1.add(v2);
                    vertexes5.put(v1, listv1);
                } else {
                    vertexes5.get(v1).add(v2);
                    vertexes5.get(v2).add(v1);
                }
            }
            tokenizer = new StringTokenizer(reader.readLine());
            S5 = Integer.parseInt(tokenizer.nextToken());
        }
    }

    ///////////////////BFS///////////////////
    private static int V4;
    private static int E4;
    private static int S4;

    private static int[] colors4;
    private static Map<Integer, List<Integer>> vertexes4;
    private static StringBuilder output4 = new StringBuilder();

    public static void BFS() throws IOException {
        inputBFS();
        Queue<Integer> planned = new ArrayDeque<>();
        planned.add(S4);

        while (!planned.isEmpty()) {
            int v = planned.remove();
            colors4[v] = 1;
            output.append(v).append(" ");
            List<Integer> list = vertexes4.get(v);
            if (list == null)
                continue;
            Collections.sort(list);

            for (Integer integer : list) {
                if (colors4[integer] == 0) {
                    colors4[integer] = 1;
                    planned.add(integer);
                }
            }

            colors4[v] = 2;
        }
        final BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        writer.write(output.toString());
        writer.flush();
    }

    private static void inputBFS() throws IOException {
        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            V4 = Integer.parseInt(tokenizer.nextToken());
            E4 = Integer.parseInt(tokenizer.nextToken());

            colors4 = new int[V4 + 1];
            vertexes4 = new HashMap<>();

            for (int i = 0; i < E4; i++) {
                tokenizer = new StringTokenizer(reader.readLine());

                int v1 = Integer.parseInt(tokenizer.nextToken());
                int v2 = Integer.parseInt(tokenizer.nextToken());

                if (!vertexes4.containsKey(v1) && !vertexes4.containsKey(v2)) {
                    List<Integer> listv1 = new ArrayList<>();
                    List<Integer> listv2 = new ArrayList<>();
                    listv1.add(v2);
                    listv2.add(v1);
                    vertexes4.put(v1, listv1);
                    vertexes4.put(v2, listv2);
                } else if (vertexes4.containsKey(v1) && !vertexes4.containsKey(v2)) {
                    vertexes4.get(v1).add(v2);
                    List<Integer> listv2 = new ArrayList<>();
                    listv2.add(v1);
                    vertexes4.put(v2, listv2);
                } else if (!vertexes4.containsKey(v1) && vertexes4.containsKey(v2)) {
                    vertexes4.get(v2).add(v1);
                    List<Integer> listv1 = new ArrayList<>();
                    listv1.add(v2);
                    vertexes4.put(v1, listv1);
                } else {
                    vertexes4.get(v1).add(v2);
                    vertexes4.get(v2).add(v1);
                }
            }
            tokenizer = new StringTokenizer(reader.readLine());
            S4 = Integer.parseInt(tokenizer.nextToken());
        }
    }

    ///////////////////Компоненты связности///////////////////
    private static int V3; //ко-во вершины
    private static int E3; //ко-во ребер
    private static Map<Integer, List<Integer>> vertexes3; //список смежности
    private static Map<Integer, Set<Integer>> result3;
    private static int[] colors3;

    private static int countSegment = 1;

    public static void connectivityComponents() throws IOException {
        inputConnectivityComponents();
        mainConnectivityComponentsDFS();
        outputConnectivityComponents();
    }

    private static void putResult(int v) {
        if (result3.containsKey(countSegment)) {
            result3.get(countSegment).add(v);
        } else {
            Set<Integer> list = new TreeSet<>();
            list.add(v);
            result3.put(countSegment, list);
        }
    }

    private static void mainConnectivityComponentsDFS() throws IOException {
        for (int i = 1; i < V3 + 1; i++) {
            if (colors3[i] == 0) {
                connectivityComponentsDFS(i, vertexes3.get(i));
                countSegment += 1;
            }
        }
    }

    private static void connectivityComponentsDFS(int v, List<Integer> list) {
        colors3[v] = countSegment;
        if (list != null) {
            for (int node : list) {
                if (colors3[node] == 0)
                    connectivityComponentsDFS(node, vertexes3.get(node));
            }
        }
        colors3[v] = countSegment;
        putResult(v);
    }

    private static void outputConnectivityComponents() throws IOException {
        final BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder output = new StringBuilder();
        output.append(countSegment - 1).append("\n");

        for (Map.Entry entry : result3.entrySet()) {
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
            V3 = Integer.parseInt(tokenizer.nextToken());
            E3 = Integer.parseInt(tokenizer.nextToken());

            colors3 = new int[V3 + 1];
            vertexes3 = new HashMap<>();
            result3 = new HashMap<>();

            for (int i = 0; i < E3; i++) {
                tokenizer = new StringTokenizer(reader.readLine());

                int v1 = Integer.parseInt(tokenizer.nextToken());
                int v2 = Integer.parseInt(tokenizer.nextToken());

                if (!vertexes3.containsKey(v1) && !vertexes3.containsKey(v2)) {
                    List<Integer> listv1 = new ArrayList<>();
                    List<Integer> listv2 = new ArrayList<>();
                    listv1.add(v2);
                    listv2.add(v1);
                    vertexes3.put(v1, listv1);
                    vertexes3.put(v2, listv2);
                } else if (vertexes3.containsKey(v1) && !vertexes3.containsKey(v2)) {
                    vertexes3.get(v1).add(v2);
                    List<Integer> listv2 = new ArrayList<>();
                    listv2.add(v1);
                    vertexes3.put(v2, listv2);
                } else if (!vertexes3.containsKey(v1) && vertexes3.containsKey(v2)) {
                    vertexes3.get(v2).add(v1);
                    List<Integer> listv1 = new ArrayList<>();
                    listv1.add(v2);
                    vertexes3.put(v1, listv1);
                } else {
                    vertexes3.get(v1).add(v2);
                    vertexes3.get(v2).add(v1);
                }
            }
        }
    }

    ///////////////////Топологическая сортировка///////////////////
    public static void topSort() throws IOException {
        inputTopSort();
        mainTopSortDFS();
        outputTopSort();
    }

    private static List<Integer> stackResult; //отсортированный граф
    private static int V2; //ко-во вершины
    private static int E2; //ко-во ребер
    private static Map<Integer, List<Integer>> vertexes2; //список смежности
    private static int[] colors2;

    private static void mainTopSortDFS() throws IOException {
        for (int i = 1; i < V2 + 1; i++) {
            if (colors2[i] == 0)
                topSort(i, vertexes2.get(i));
        }
    }

    private static void topSort(int v, List<Integer> list) {
        colors2[v] = 1;
        if (list != null) {
            for (int node : list) {
                if (colors2[node] == 0)
                    topSort(node, vertexes2.get(node));
            }
        }
        colors2[v] = 2;
        stackResult.add(v);
    }

    private static void outputTopSort() throws IOException {
        final BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder output = new StringBuilder();
        for (int i = stackResult.size() - 1; i >= 0; i--) {
            output.append(stackResult.get(i)).append(" ");
        }
        writer.write(output.toString());
        writer.flush();
    }

    private static void inputTopSort() throws IOException {
        //input
        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            V2 = Integer.parseInt(tokenizer.nextToken());
            E2 = Integer.parseInt(tokenizer.nextToken());

            stackResult = new ArrayList<>();
            vertexes2 = new HashMap<>();
            colors = new int[V2 + 1];

            for (int i = 0; i < E2; i++) {
                tokenizer = new StringTokenizer(reader.readLine());

                int v1 = Integer.parseInt(tokenizer.nextToken());
                int v2 = Integer.parseInt(tokenizer.nextToken());

                if (vertexes2.containsKey(v1))
                    vertexes2.get(v1).add(v2);
                else {
                    List<Integer> list = new ArrayList<>();
                    list.add(v2);
                    vertexes2.put(v1, list);
                }
            }
        }
    }

    ///////////////////Время выходить///////////////////
    public static void timeToGoOut() throws IOException {
        inputGraphTimeToGoOut();
        mainDFS();
    }

    private static int V; //ко-во вершины
    private static int E; //ко-во ребер
    private static Map<Integer, Set<Integer>> vertexes; //список смежности
    private static int time = -1;
    private static int[] timeEntry;
    private static int[] timeLeave;
    private static int[] colors;

    private static void mainDFS() throws IOException {
        if (V > 1) {
            DFS(1, vertexes.get(1));
            output();
        } else
            System.out.println("0 1");
    }

    private static void DFS(int v, Set<Integer> list) {
        time += 1;
        timeEntry[v] = time;
        colors[v] = 1;

        if (list != null) {
            for (int node : list) {
                if (colors[node] == 0)
                    DFS(node, vertexes.get(node));
            }
        }

        time += 1;
        timeLeave[v] = time;
        colors[v] = 2;
    }

    private static void output() throws IOException {
        final BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder output = new StringBuilder();
        for (int i = 1; i < V + 1; i++)
            output.append(timeEntry[i]).append(" ").append(timeLeave[i]).append("\n");

        writer.write(output.toString());
        writer.flush();
    }

    private static void inputGraphTimeToGoOut() throws IOException {
        //input
        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            V = Integer.parseInt(tokenizer.nextToken());
            E = Integer.parseInt(tokenizer.nextToken());

            vertexes = new HashMap<>();
            timeEntry = new int[V + 1];
            timeLeave = new int[V + 1];
            colors = new int[V + 1];

            for (int i = 0; i < E; i++) {
                tokenizer = new StringTokenizer(reader.readLine());

                int v1 = Integer.parseInt(tokenizer.nextToken());
                int v2 = Integer.parseInt(tokenizer.nextToken());

                if (vertexes.containsKey(v1))
                    vertexes.get(v1).add(v2);
                else {
                    Set<Integer> list = new TreeSet<>();
                    list.add(v2);
                    vertexes.put(v1, list);
                }
            }
        }
    }

    ///////////////////DFS///////////////////
    private static int N;

    private enum Color {
        WHITE,
        GREY,
        BLACK
    }

    private static final class NodeGraph implements Comparable<NodeGraph> {

        private final int value;
        private Color color = Color.WHITE;
        private List<NodeGraph> ribs = new ArrayList<>();

        public NodeGraph(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        public Color getColor() {
            return color;
        }

        public List<NodeGraph> getRibs() {
            return ribs;
        }

        public void setColor(Color color) {
            this.color = color;
        }

        @Override
        public int compareTo(NodeGraph o) {
            return this.getValue() - o.getValue();
        }
    }

    public static void mainDFS(NodeGraph[] nodes) {
        if (nodes[N] != null)
            DFS(nodes[N]);
        else
            System.out.println(N);
    }

    private static void DFS(NodeGraph v) {
        v.setColor(Color.GREY);
        System.out.print(v.getValue() + " ");
        Collections.sort(v.getRibs());
        for (NodeGraph node : v.getRibs()) {
            if (node.getColor() == Color.WHITE)
                DFS(node);
        }
        v.setColor(Color.BLACK);
    }

    private static NodeGraph[] inputGraph() throws IOException {
        //input
        final int V; //ко-во вершины
        final int E; //ко-во ребра
        NodeGraph[] tops; //вершины
        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            V = Integer.parseInt(tokenizer.nextToken());
            E = Integer.parseInt(tokenizer.nextToken());

            tops = new NodeGraph[V + 1];

            for (int i = 0; i < E; i++) {
                tokenizer = new StringTokenizer(reader.readLine());
                NodeGraph v1 = new NodeGraph(Integer.parseInt(tokenizer.nextToken()));
                NodeGraph v2 = new NodeGraph(Integer.parseInt(tokenizer.nextToken()));

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

    ///////////////////Перевести список ребер в матрицу смежности///////////////////
    public static void convertListOfEdgesToAdjacencyMatrix() throws IOException {
        //input and program
        final int V; //вершины
        final int E; //ребра
        int[][] adjacencyMatrix; //список смежности
        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            V = Integer.parseInt(tokenizer.nextToken());
            E = Integer.parseInt(tokenizer.nextToken());

            adjacencyMatrix = new int[V][V];

            for (int i = 0; i < E; i++) {
                tokenizer = new StringTokenizer(reader.readLine());
                int v1 = Integer.parseInt(tokenizer.nextToken()) - 1;
                int v2 = Integer.parseInt(tokenizer.nextToken()) - 1;

                adjacencyMatrix[v1][v2] += 1;
            }
        }

        //output
        for (int[] matrix : adjacencyMatrix) {
            for (int i : matrix)
                System.out.print(i + " ");
            System.out.println();
        }
    }

    ///////////////////Построить список смежности///////////////////
    public static void buildAdjacencyList() throws IOException {
        //input and program
        final int V; //вершины
        final int E; //ребра
        List<Integer>[] adjacencyList; //список смежности
        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            V = Integer.parseInt(tokenizer.nextToken());
            E = Integer.parseInt(tokenizer.nextToken());

            adjacencyList = new ArrayList[V];

            for (int i = 0; i < E; i++) {
                tokenizer = new StringTokenizer(reader.readLine());
                int v1 = Integer.parseInt(tokenizer.nextToken()) - 1;
                int v2 = Integer.parseInt(tokenizer.nextToken());

                if (adjacencyList[v1] == null) {
                    adjacencyList[v1] = new ArrayList<>();
                    adjacencyList[v1].add(v2);
                } else
                    adjacencyList[v1].add(v2);
            }
        }

        //output
        for (List<Integer> integers : adjacencyList) {
            if (integers == null)
                System.out.println(0);
            else {
                System.out.print(integers.size() + " ");
                for (Integer integer : integers)
                    System.out.print(integer + " ");
                System.out.println();
            }
        }
    }

    ///////////////////Просеивание вниз///////////////////
    public static int siftDown(int[] heap, int idx) {
        int left = idx * 2;
        int right = idx * 2 + 1;
        int large = left;

        if (left >= heap.length)
            return idx;

        if (right < heap.length && heap[left] < heap[right])
            large = right;

        if (heap[idx] < heap[large]) {
            int tmp = heap[idx];
            heap[idx] = heap[large];
            heap[large] = tmp;
            return siftDown(heap, large);
        }
        return idx;
    }

    ///////////////////Просеивание вверх///////////////////
    public static int siftUp(int[] heap, int idx) {
        int root = idx / 2;

        if (idx == 1 || heap[root] > heap[idx])
            return idx;

        if (heap[root] < heap[idx]) {
            int tmp = heap[idx];
            heap[idx] = heap[root];
            heap[idx / 2] = tmp;
        }
        return siftUp(heap, idx / 2);
    }

    ///////////////////Сбалансированное дерево///////////////////
    private static boolean flag = true;

    public static boolean isBalanceTree(NodeIsBalanceTree head) {
        recursiveIsBalanceTree(head);
        return flag;
    }

    private static int recursiveIsBalanceTree(NodeIsBalanceTree root) {
        if (root == null || !flag)
            return 0;

        int leftRootCount = recursiveIsBalanceTree(root.left);
        int rightRootCount = recursiveIsBalanceTree(root.right);

        if (Math.abs(leftRootCount - rightRootCount) > 1)
            flag = false;

        return Math.max(leftRootCount, rightRootCount) + 1;
    }

    private static class NodeIsBalanceTree {
        int value;
        NodeIsBalanceTree left;
        NodeIsBalanceTree right;

        NodeIsBalanceTree(int value) {
            this.value = value;
            this.left = null;
            this.right = null;
        }
    }

    ///////////////////Добавь узел///////////////////
    public static NodeInsert insert(NodeInsert root, int key) {
        NodeInsert head = root;
        recurseInsert(root, key);
        return head;
    }

    private static void recurseInsert(NodeInsert root, int key) {
        if (root == null)
            return;

        if (key < root.getValue()) {
            if (root.getLeft() == null)
                root.setLeft(new NodeInsert(null, null, key));
            else
                recurseInsert(root.getLeft(), key);
        }

        if (key >= root.getValue()) {
            if (root.getRight() == null)
                root.setRight(new NodeInsert(null, null, key));
            else
                recurseInsert(root.getRight(), key);
        }
    }

    private static class NodeInsert {
        private int value;
        private NodeInsert left;
        private NodeInsert right;

        NodeInsert(NodeInsert left, NodeInsert right, int value) {
            this.left = left;
            this.right = right;
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        public NodeInsert getRight() {
            return right;
        }

        public void setRight(NodeInsert right) {
            this.right = right;
        }

        public NodeInsert getLeft() {
            return left;
        }

        public void setLeft(NodeInsert left) {
            this.left = left;
        }

        public void setValue(int value) {
            this.value = value;
        }
    }

    ///////////////////Выведи диапазон///////////////////
    public static void printRange(printRangeNode root, int L, int R, BufferedWriter writer) throws IOException {
        if (root == null)
            return;

        if (root.getLeft() != null)
            printRange(root.getLeft(), L, R, writer);

        if (inRange(root.getValue(), L, R))
            writer.append(String.valueOf(root.getValue())).append("\n");

        if (root.getRight() != null)
            printRange(root.getRight(), L, R, writer);
    }

    private static boolean inRange(int value, int L, int R) {
        return L <= value && value <= R;
    }

    private static class printRangeNode {
        private int value;
        private printRangeNode left;
        private printRangeNode right;

        printRangeNode(printRangeNode left, printRangeNode right, int value) {
            this.left = left;
            this.right = right;
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        public printRangeNode getRight() {
            return right;
        }

        public void setRight(printRangeNode right) {
            this.right = right;
        }

        public printRangeNode getLeft() {
            return left;
        }

        public void setLeft(printRangeNode left) {
            this.left = left;
        }

        public void setValue(int value) {
            this.value = value;
        }
    }

    ///////////////////Разные деревья поиска///////////////////
    private static void differentSearchTrees() throws IOException {
        //input
        final BigInteger N;
        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            N = BigInteger.valueOf(Long.parseLong(reader.readLine()));
        }

        //program and output
        System.out.println(factorial(N.multiply(BigInteger.TWO)).divide(factorial(N).multiply(factorial(N.add(BigInteger.ONE)))));
    }

    private static BigInteger factorial(BigInteger n) {
        if (n.equals(BigInteger.ZERO) || n.equals(BigInteger.ONE))
            return BigInteger.ONE;

        return factorial(n.subtract(BigInteger.ONE)).multiply(n);
    }

    ///////////////////Дерево поиска///////////////////
    public static boolean searchTree(NodeSearchTree head) {
        return check(head, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private static boolean check(NodeSearchTree root, int min, int max) {
        if (root == null)
            return true;

        if (root.value <= min ||root.value >= max)
            return false;
        return check(root.left, min, root.value) && check(root.right, root.value, max);
    }

    private static class NodeSearchTree {
        int value;
        NodeSearchTree left;
        NodeSearchTree right;

        NodeSearchTree(int value) {
            this.value = value;
            this.left = null;
            this.right = null;
        }

        NodeSearchTree(int value, NodeSearchTree left, NodeSearchTree right) {
            this.value = value;
            this.left = left;
            this.right = right;
        }
    }

    ///////////////////Лампочки///////////////////
    public static int lightBulbs(NodeLightBulbs head) {
        if (head.left != null && head.right != null)
            return Math.max(head.value, Math.max(lightBulbs(head.left), lightBulbs(head.right)));
        else if (head.left != null)
            return Math.max(head.value, lightBulbs(head.left));
        else if (head.right != null)
            return Math.max(head.value, lightBulbs(head.right));
        return head.value;
    }

    private static class NodeLightBulbs {
        int value;
        NodeLightBulbs left;
        NodeLightBulbs right;

        NodeLightBulbs(int value) {
            this.value = value;
            this.left = null;
            this.right = null;
        }
    }

    ///////////////////Сломай меня///////////////////
    public static void breakMe() throws IOException {
        //pgifrsy
        //lrimjcz
    }

    ///////////////////Анаграммная группировка///////////////////
    public static void anagramGrouping() throws IOException {
        //input
        final int N;
        final List<char[]> mainList = new ArrayList<>();
        final BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            N = Integer.parseInt(reader.readLine());
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());

            for (int i = 0; i < N; i++) {
                char[] chars = tokenizer.nextToken().toCharArray();
                Arrays.sort(chars);
                mainList.add(chars);
            }
        }

        //program
        final StringBuilder output = new StringBuilder();
        final Set<Set<Integer>> checkSet = new HashSet<>();

        for (int i = 0; i < mainList.size(); i++) {
            final Set<Integer> appendSet = new TreeSet<>();
            if (chetInSet(checkSet, i))
                continue;
            appendSet.add(i);

            for (int j = i; j < mainList.size(); j++) {
                if (Arrays.equals(mainList.get(i), mainList.get(j)) && mainList.get(i) != mainList.get(j)) {
                    appendSet.add(j);
                }
            }

            if (!checkSet.contains(appendSet)) {
                checkSet.add(appendSet);
                for (Integer integer : appendSet)
                    output.append(integer).append(' ');
                output.append('\n');
            }
        }

        //output
        writer.write(output.toString());
        writer.flush();
    }

    private static boolean chetInSet(Set<Set<Integer>> checkSet, int i) {
        for (Set<Integer> set : checkSet) {
            if (set.contains(i))
                return true;
        }
        return false;
    }

    ///////////////////Подстроки///////////////////
    public static void substrings() throws IOException {
        final String str;
        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            str = reader.readLine();
        }

        int resultLen = 0;
        for (int i = 0; i < str.length(); i++) {
            Set<Character> set = new HashSet<>();
            for (int j = i; j < str.length(); j++) {
                if (set.contains(str.charAt(j)))
                    break;
                set.add(str.charAt(j));
            }
            if (resultLen < set.size())
                resultLen = set.size();
        }
        System.out.print(resultLen);
    }

    ///////////////////Полиномиальный хеш///////////////////
    public static void polynomialHash() throws IOException{
        //input
        final int A;
        final int M;
        final String S;
        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            A = Integer.parseInt(reader.readLine());
            M = Integer.parseInt(reader.readLine());
            S = reader.readLine();
        }

        //program
        long hash = 0;
        for (int i = 0; i < S.length(); i++) {
            hash = (hash * A + S.charAt(i)) % M;
        }

        //output
        System.out.print(hash);
    }

    ///////////////////Странное сравнение///////////////////
    public static void strangeComparison() throws IOException {
        //input
        final String str1;
        final String str2;
        final BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            str1 = reader.readLine();
            str2 = reader.readLine();
        }

        //program and output
        writer.write(strangeCompare(str1, str2));
        writer.flush();
    }

    private static String strangeCompare(final String str1, final String str2) {
        if (str1.length() != str2.length())
            return "NO";

        char ch1;
        char ch2;
        final Map<Character, Character> map = new HashMap<>();
        final Map<Character, Character> mapReversed = new HashMap<>();
        for (int i = 0; i < str1.length(); i++) {
            ch1 = str1.charAt(i);
            ch2 = str2.charAt(i);

            if (map.containsKey(ch1) && !map.get(ch1).equals(ch2))
                return "NO";
            if (mapReversed.containsKey(ch2) && !mapReversed.get(ch2).equals(ch1))
                return "NO";

            map.put(ch1, ch2);
            mapReversed.put(ch2, ch1);
        }
        return "YES";
    }

    ///////////////////Кружки///////////////////
    public static void circles(String[] args) throws IOException {
        //input and program
        final int N;
        final Set<String> set = new LinkedHashSet<>();
        final StringBuilder output = new StringBuilder();
        final BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            N = Integer.parseInt(reader.readLine());

            String line;
            for (int i = 0; i < N; i++) {
                line = reader.readLine();
                if(!set.contains(line))
                    set.add(line);
            }
        }

        //output
        for (String s : set)
            output.append(s).append('\n');
        writer.write(output.toString());
        writer.flush();
    }

    ///////////////////Любители конференций///////////////////
    public static void conferenceLovers(String[] args) throws IOException {
        //input
        final int N;
        final int K;
        final int[] ids;
        final StringBuilder output = new StringBuilder();
        final BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            N = Integer.parseInt(reader.readLine());
            ids = new int[N];
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());

            for (int i = 0; i < ids.length; i++)
                ids[i] = Integer.parseInt(tokenizer.nextToken());

            K = Integer.parseInt(reader.readLine());
        }

        //program
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < ids.length; i++) {
            if (map.containsKey(ids[i]))
                map.put(ids[i], map.get(ids[i]) + 1);
            else
                map.put(ids[i], 1);
        }

        List<Map.Entry<Integer, Integer>> list = map.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toList());

        //output
        for (int i = 0; i < K; i++)
            output.append(list.get(i).getKey()).append(" ");
        writer.write(output.toString());
        writer.flush();
    }

    ///////////////////Периметр треугольника///////////////////
    public static void perimeterTriangle() throws IOException {
        //input
        final int N;
        final List<Integer> segments = new ArrayList<>();
        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            N = Integer.parseInt(reader.readLine());
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());

            for (int i = 0; i < N; i++)
                segments.add(Integer.parseInt(tokenizer.nextToken()));
        }

        //program
        Collections.sort(segments);
        int perimeterMax = 0;
        for (int i = 0; i < segments.size(); i++) {
            if (segments.size() == i + 1 || segments.size() == i + 2)
                break;
            int a = segments.get(i);
            int b = segments.get(i + 1);
            int c = segments.get(i + 2);

            if (c < (a + b) && (a + b + c) > perimeterMax)
                perimeterMax = a + b + c;
        }

        //output
        System.out.print(perimeterMax);
    }

    ///////////////////Покупка домов///////////////////
    public static void buyingHouses() throws IOException {
        //input
        final int N;
        final int K;
        final List<Integer> houses = new ArrayList<>();
        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            N = Integer.parseInt(tokenizer.nextToken());
            K = Integer.parseInt(tokenizer.nextToken());

            tokenizer = new StringTokenizer(reader.readLine());
            for (int i = 0; i < N; i++) {
                houses.add(Integer.parseInt(tokenizer.nextToken()));
            }
        }

        //program
        Collections.sort(houses);
        int countHouse = 0;
        int sum = 0;
        for (Integer housePrice : houses) {
            sum += housePrice;
            if (sum <= K)
                countHouse += 1;
            else
                break;
        }

        //output
        System.out.print(countHouse);
    }

    ///////////////////Гардероб///////////////////
    public static void wardrobe() throws IOException {
        //input
        int N;
        int[] arr;
        StringBuilder output = new StringBuilder();
        final BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            N = Integer.parseInt(reader.readLine());
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());

            arr = new int[N];
            for (int i = 0; i < N; i++)
                arr[i] = Integer.parseInt(tokenizer.nextToken());
        }

        //program
        int[] count = new int[3];
        for (int i = 0; i < arr.length; i++)
            count[arr[i]] += 1;

        for (int i = 0; i < count.length; i++)
            for (int j = 0; j < count[i]; j++)
                output.append(i).append(" ");

        //output
        writer.write(output.toString());
        writer.flush();
    }

    ///////////////////Большое число///////////////////
    public static void bigNumber() throws IOException {
        //input
        final int N;
        List<String> list = new ArrayList<>();
        StringBuilder output = new StringBuilder();
        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            N = Integer.parseInt(reader.readLine());
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            for (int i = 0; i < N; i++)
                list.add(tokenizer.nextToken());
        }

        //program
        bubbleSortList(list);
        for (int i = list.size() - 1; i >= 0; i--)
            output.append(list.get(i));

        //output
        System.out.print(output);
    }

    private static void bubbleSortList(List<String> list) {
        for (int j = 0; j < list.size() - 1; j++) {
            for (int i = 0; i < list.size(); i++)
                if (i + 1 < list.size() && comparator(list.get(i), list.get(i + 1)))
                    swap(list, i);
        }
    }

    private static void swap(List<String> list, int i) {
        String tmp = list.get(i + 1);
        list.set(i + 1, list.get(i));
        list.set(i, tmp);
    }

    private static boolean comparator(String s1, String s2) {
        return Integer.parseInt(s1 + s2) > Integer.parseInt(s2 + s1);
    }

    ///////////////////Пузырек///////////////////
    public static void bubble() throws IOException {
        //input
        final int N;
        int[] array;
        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            N = Integer.parseInt(reader.readLine());
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());

            array = new int[N];
            for (int i = 0; i < N; i++)
                array[i] = Integer.parseInt(tokenizer.nextToken());
        }

        //program and output
        if (isArraySorted(array)) {
            System.out.println(array2String(array));
            return;
        }

        for (int j = 0; j < N - 1; j++) {
            if (isArraySorted(array))
                break;
            for (int i = 0; i < array.length; i++) {
                if (i + 1 < array.length && array[i] > array[i + 1]) {
                    int tmp = array[i + 1];
                    array[i + 1] = array[i];
                    array[i] = tmp;
                }
            }
            System.out.println(array2String(array));
        }
    }

    private static String array2String(int[] array) {
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < array.length; i++) {
            stringBuilder.append(array[i]).append(" ");
        }
        return stringBuilder.toString();
    }

    private static boolean isArraySorted(int[] array) {
        for (int i = 0; i < array.length; i++) {
            if (i + 1 < array.length && array[i] > array[i + 1])
                return false;
        }
        return true;
    }

    ///////////////////Комбинации///////////////////
    private static final Map<Integer, char[]> mapCombination = new HashMap<>();

    static {
        mapCombination.put(2, new char[]{'a','b','c'});
        mapCombination.put(3, new char[]{'d','e','f'});
        mapCombination.put(4, new char[]{'g','h','i'});
        mapCombination.put(5, new char[]{'j','k','l'});
        mapCombination.put(6, new char[]{'m','n','o'});
        mapCombination.put(7, new char[]{'p','q','r','s'});
        mapCombination.put(8, new char[]{'t','u','v'});
        mapCombination.put(9, new char[]{'w','x','y','z'});
    }

    public static void combinations() throws IOException {
        //input
        final String combination;
        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            combination = reader.readLine();
        }

        int[] array = new int[combination.length()];
        for (int i = 0; i < combination.length(); i++) {
            array[i] = Integer.parseInt(String.valueOf(combination.charAt(i)));
        }

        //program
        StringBuilder output = new StringBuilder();
        recurseCombination(array, 0, output, "");

        //output
        System.out.println(output);
    }

    private static void recurseCombination(int[] array, int iterator, StringBuilder output, String line) {
        if (line.length() == array.length)
            output.append(line).append(" ");
        else {
            char[] chars = mapCombination.get(array[iterator]);
            for (int i = 0; i < chars.length; i++) {
                recurseCombination(array, iterator + 1, output, line + chars[i]);
            }
        }
    }

    ///////////////////Генератор скобок///////////////////
    private static final List<String> list = new ArrayList<>();

    public static void parenthesesGenerator() throws IOException {
        //input
        final int N;
        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            N = Integer.parseInt(reader.readLine());
        }

        //program
        printParentheses(N, N, "");

        //output
        for (String item : list) {
            if (isBracketSequence(item))
                System.out.println(item);
        }
    }

    private static void printParentheses(int open, int close, String line) {
        if (open == 0 && close == 0)
            list.add(line);

        if (open != 0)
            printParentheses(open - 1, close, line + "(");
        if (close != 0)
            printParentheses(open, close - 1, line + ")");
    }

    private static boolean isBracketSequence(String line) {
        Stack<Character> stack = stringToStack(line);
        Stack<Character> buffer = new Stack<>();
        char bracket;

        while (!stack.isEmpty()) {
            bracket = stack.pop();

            if (isCloseBracket(bracket))
                buffer.push(bracket);
            else if (isOpenBracket(bracket)) {
                if (buffer.empty() || !compareBracket(bracket, buffer.pop()))
                    return false;
            } else
                return false;
        }
        return true;
    }

    private static Stack<Character> stringToStack(String str) {
        Stack<Character> result = new Stack<>();

        for (int i = 0; i < str.length(); i++) {
            result.push(str.charAt(i));
        }
        return result;
    }

    ///////////////////Два велосипеда///////////////////
    public static void twoBicycles() throws IOException {
        //input and program
        final int N;
        final int price;
        int[] days;
        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            N = Integer.parseInt(reader.readLine());
            days = new int[N + 1];
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());

            for (int i = 1; i < days.length; i++)
                days[i] = Integer.parseInt(tokenizer.nextToken());

            price = Integer.parseInt(reader.readLine());
        }

        //program
        int firstPurchase = recursiveBinSearch(days, price, 1, days.length, -1);
        int secondPurchase = recursiveBinSearch(days, price * 2, 1, days.length, -1);

        //output
        final BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        writer.write(firstPurchase + " " + secondPurchase);
        writer.flush();
    }

    private static int recursiveBinSearch(int[] array, int number, int min, int max, int current) {
        if (max <= min)
            return current;

        int middle = (min + max) / 2;

        if (number <= array[middle])
            return recursiveBinSearch(array, number, min, middle, middle);
        else
            return recursiveBinSearch(array, number, middle + 1, max, current);
    }

    ///////////////////Фибоначчи по модулю///////////////////
    private static int first2;
    private static int second = 0;
    private static int fibonacci = 1;

    public static void fibonacciModulo() throws IOException {
        //input
        final int N;
        int K;
        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            N = Integer.parseInt(tokenizer.nextToken());
            K = Integer.parseInt(tokenizer.nextToken());
        }

        //program
        K = (int) Math.pow(10, K);
        int result = 1;

        for (int i = 0; i < N; i++) {
            first2 = second % K;
            second = fibonacci % K;
            fibonacci = first2 + second;
            result = fibonacci % K;
        }

        //output
        System.out.println(result);
    }

    ///////////////////Рекурсивные числа Фибоначчи///////////////////
    public static void recursiveFibonacciNumbers() throws IOException {
        //input
        int N;
        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            N = Integer.parseInt(reader.readLine());
        }

        //program
        int countCommits = recursiveFibonacciPlus(N);

        //output
        System.out.println(countCommits);
    }

    private static int recursiveFibonacciPlus(int n) {
        if (n == 0 || n == 1)
            return 1;
        return recursiveFibonacciPlus(n - 1) + recursiveFibonacciPlus(n - 2);
    }

    ///////////////////Списочная очередь///////////////////
    private static class Node3<T> {
        public T value;
        public Node3<T> next;
        public Node3<T> prev;

        public Node3(T num, Node3<T> prev, Node3<T> next) {
            this.value = num;
            this.prev = prev;
            this.next = next;
        }
    }

    private static Integer size = 0;
    private static Node3<Integer> first;
    private static Node3<Integer> last;

    private static final StringBuilder output = new StringBuilder();

    public static void listQueue() throws IOException {
        //input and program
        int N;
        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            N = Integer.parseInt(reader.readLine());

            StringTokenizer tokenizer;
            String command;
            for (int i = 0; i < N; i++) {
                tokenizer = new StringTokenizer(reader.readLine());
                command = tokenizer.nextToken();

                if (command.equals("put"))
                    listQueuePut(Integer.parseInt(tokenizer.nextToken()));
                else if (command.equals("get"))
                    listQueueGet();
                else
                    listQueueSize();
            }

            //output
            final BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
            writer.write(output.toString());
            writer.flush();
        }
    }

    private static void listQueuePut(int num) {
        if (first == null) {
            first = new Node3<>(num, null, null);
            last = new Node3<>(null, null, null);
            first.next = last;
            last.prev = first;
        } else {
            last.value = num;
            last.next = new Node3<>(null, last, null);
            last = last.next;
        }
        size += 1;
    }

    private static void listQueueGet() {
        if (size > 0) {
            output.append(first.value).append('\n');
            first = first.next;
            size -= 1;
            if (size == 0)
                first = null;
        } else
            output.append("error\n");
    }

    private static void listQueueSize() {
        output.append(size).append('\n');
    }

    ///////////////////Ограниченная очередь///////////////////
    private static int maxSize;
    private static LinkedList<Integer> queue = new LinkedList<>();

    public static void limitedQueue() throws IOException {
        //input, program and output
        int N;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            N = Integer.parseInt(reader.readLine());
            maxSize = Integer.parseInt(reader.readLine());

            StringTokenizer tokenizer;
            String command;
            for (int i = 0; i < N; i++) {
                tokenizer = new StringTokenizer(reader.readLine());
                command = tokenizer.nextToken();

                if (command.equals("push")) {
                    limitedQueuePush(Integer.parseInt(tokenizer.nextToken()));
                } else if (command.equals("pop"))
                    limitedQueuePop();
                else if (command.equals("peek"))
                    limitedQueuePeek();
                else
                    limitedQueueSize();
            }
        }
    }

    private static void limitedQueuePush(int num) {
        if (queue.size() < maxSize) {
            queue.add(num);
        } else
            System.out.println("error");
    }

    private static void limitedQueuePop() {
        if (queue.size() > 0) {
            int number = queue.get(0);
            System.out.println(number);
            queue.remove(0);
        } else
            System.out.println("None");
    }

    private static void limitedQueuePeek() {
        if (queue.size() > 0)
            System.out.println(queue.get(0));
        else
            System.out.println("None");
    }

    private static void limitedQueueSize() {
        System.out.println(queue.size());
    }

    ///////////////////Скобочная последовательность///////////////////
    private static final Map<Character, Character> mapBracket = new HashMap<>();

    static {
        mapBracket.put('(', ')');
        mapBracket.put('{', '}');
        mapBracket.put('[', ']');
    }

    public static void bracketSequence() throws IOException {
        //input
        String line;
        Stack<Character> stack = new Stack<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            line = reader.readLine().strip();

            for (int i = 0; i < line.length(); i++)
                stack.push(line.charAt(i));
        }

        //program and output
        System.out.println(isBracketSequence(stack));
    }

    private static String isBracketSequence(Stack<Character> stack) {
        Stack<Character> buffer = new Stack<>();
        char bracket;

        while (!stack.isEmpty()) {
            bracket = stack.pop();

            if (isCloseBracket(bracket))
                buffer.push(bracket);
            else if (isOpenBracket(bracket)) {
                if (buffer.empty() || !compareBracket(bracket, buffer.pop()))
                    return "False";
            } else
                return "False";
        }
        return "True";
    }

    private static boolean compareBracket(char openBracket, char closeBracket) {
        return mapBracket.get(openBracket).equals(closeBracket);
    }

    private static boolean isCloseBracket(char ch) {
        return ch == ')' || ch == ']' || ch == '}';
    }

    private static boolean isOpenBracket(char ch) {
        return ch == '(' || ch == '[' || ch == '{';
    }

    ///////////////////Стек - Max и Стек - MaxEffective///////////////////
    private static List<Integer> stack = new ArrayList<>();
    private static List<Integer> stackMax = new ArrayList<>();

    public static void stackMax() throws IOException {
        //input, output and program
        int N;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            N = Integer.parseInt(reader.readLine().strip());
            StringTokenizer tokenizer;

            for (int i = 0; i < N; i++) {
                tokenizer = new StringTokenizer(reader.readLine());
                String command = tokenizer.nextToken();
                if (command.equals("push")) {
                    int num = Integer.parseInt(tokenizer.nextToken());
                    push(num);
                } else if (command.equals("pop"))
                    pop();
                else
                    get_max();
            }
        }
    }

    private static void push(int num) {
        if (stackMax.isEmpty() || num >= stackMax.get(stackMax.size() - 1))
            stackMax.add(num);
        stack.add(num);
    }

    private static void pop() {
        if (stack.isEmpty())
            System.out.println("error");
        else {
            if (!stackMax.isEmpty() && Objects.equals(stack.get(stack.size() - 1), stackMax.get(stackMax.size() - 1)))
                stackMax.remove(stackMax.size() - 1);
            stack.remove(stack.size() - 1);
        }
    }

    private static void get_max() {
        if (stack.isEmpty())
            System.out.println("None");
        else
            System.out.println(stackMax.get(stackMax.size() - 1));
    }

    ///////////////////Всё наоборот///////////////////
    public static Node2<String> wayAround(Node2<String> head) {
        Node2<String> pointer = head;
        int len = 0;

        while (pointer.next != null) {
            pointer = pointer.next;
            len += 1;
        }

        Node2<String> resutlt = new Node2<>(pointer.value, null, null);
        Node2<String> headResutlt = resutlt;
        for (int i = 0; i < len; i++) {

            if (pointer.prev != null)
                resutlt.next = new Node2<>(pointer.prev.value, pointer.prev, pointer.next);
            if (pointer.next != null)
                resutlt.prev = new Node2<>(pointer.next.value, pointer.next, pointer.prev);

            resutlt = resutlt.next;
            pointer = pointer.prev;
            if (i + 1 == len)
                resutlt.next = null;
        }
        return headResutlt;
    }

    static class Node2<V> {
        public V value;
        public Node2<V> next;
        public Node2<V> prev;

        public Node2(V value, Node2<V> next, Node2<V> prev) {
            this.value = value;
            this.next = next;
            this.prev = prev;
        }
    }

    ///////////////////Заботливая мама///////////////////
    public static int caringMother(Node<String> head, String elem) {
        int index = 0;

        while (head != null) {
            if (head.value.equals(elem))
                return index;
            head = head.next;
            index += 1;
        }
        return -1;
    }

    ///////////////////Нелюбимое дело///////////////////
    public static Node<String> unlovedBusiness(Node<String> head, int idx) {
        Node<String> pointer = head;
        int delete = 1;

        if (idx == 0)
            return head.next;

        if (idx == lenList(head))
            delete = 2;

        for (int i = 0; i < idx - delete; i++)
            pointer = pointer.next;

        pointer.next = pointer.next.next;
        return head;
    }

    private static int lenList(Node<String> head) {
        int len = 0;

        while (head != null) {
            head = head.next;
            len += 1;
        }
        return len;
    }

    ///////////////////Список дел///////////////////
    static class Node<V> {
        public V value;
        public Node<V> next;

        public Node(V value, Node<V> next) {
            this.value = value;
            this.next = next;
        }
    }

    public static void todoList(Node<String> head) {
        while (head != null) {
            System.out.println(head.value);
            head = head.next;
        }
    }

    ///////////////////Мониторинг///////////////////
    public static void monitoring() throws IOException {
        //input
        int N;
        int M;
        int[][] matrix;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            N = Integer.parseInt(reader.readLine().strip());
            M = Integer.parseInt(reader.readLine().strip());

            StringTokenizer tokenizer;
            matrix = new int[N][M];
            for (int i = 0; i < N; i++) {
                tokenizer = new StringTokenizer(reader.readLine());
                for (int j = 0; j < M; j++)
                    matrix[i][j] = Integer.parseInt(tokenizer.nextToken());
            }
        }

        //program
        int[][] reversMatrix = new int[M][N];

        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                reversMatrix[i][j] = matrix[j][i];
            }
        }

        //output
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                writer.write(String.valueOf(reversMatrix[i][j]));
                writer.append(' ');
            }
            writer.append('\n');
        }
        writer.flush();
    }

    ///////////////////Две фишки 2///////////////////
    public static void twoChipsFast() throws Exception {
        //input
        List<Integer> chips;
        int len;
        int X;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            len = Integer.parseInt(reader.readLine().strip());
            chips = Arrays.asList(reader.readLine().strip().split(" "))
                    .stream()
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
            X = Integer.parseInt(reader.readLine().strip());
        }

        //program
        Map<Integer, Integer> previous = new HashMap<>();
        int[] result = new int[2];

        for (int i = 0; i < chips.size(); i++) {
            int key = X - chips.get(i);

            if (previous.containsKey(key)) {
                result[0] = chips.get(i);
                result[1] = chips.get(previous.get(key));
                break;
            }
            else
                previous.put(chips.get(i), i);
        }

        //output
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        if (result[0] == 0 && result[1] == 0) {
            writer.write("None");
            writer.flush();
            return;
        }

        for (int i = 0; i < result.length; i++) {
            writer.write(String.valueOf(result[i]));
            writer.write(" ");
        }
        writer.flush();
    }

    ///////////////////Две фишки///////////////////
    public static void twoChips() throws Exception {
        //input
        List<Integer> chips;
        int len;
        int X;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            len = Integer.parseInt(reader.readLine().strip());
            chips = Arrays.asList(reader.readLine().strip().split(" "))
                    .stream()
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
            X = Integer.parseInt(reader.readLine().strip());
        }

        //program
        int [] result = new int[2];

        for (int i = 0; i < chips.size(); i++) {
            int value = chips.get(i);

            for (int j = i + 1; j < chips.size(); j++) {
                if (value + chips.get(j) == X) {
                    result[0] = value;
                    result[1] = chips.get(j);
                }
            }
        }

        //output
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        if (result[0] == 0 && result[1] == 0) {
            writer.write("None");
            writer.flush();
            return;
        }

        for (int i = 0; i < result.length; i++) {
            writer.write(String.valueOf(result[i]));
            writer.write(" ");
        }
        writer.flush();
    }

    ///////////////////Скользящее среднее///////////////////
    public static void movingAverageFastTask() throws IOException {
        //input
        List<Integer> times;
        int len;
        int K;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            len = Integer.parseInt(reader.readLine().strip());
            times = Arrays.asList(reader.readLine().strip().split(" "))
                    .stream()
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
            K = Integer.parseInt(reader.readLine().strip());
        }

        //program
        float[] result = new float[times.size() - (K - 1)];
        float currentSum = 0;

        for (int i = 0; i < K; i++) {
            currentSum += times.get(i);
        }
        result[0] = currentSum / K;

        for (int i = 0; i < times.size() - K; i++) {
            currentSum -= times.get(i);
            currentSum += times.get(K + i);
            result[i + 1] = currentSum / K;
        }

        //output
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        for (int i = 0; i < result.length; i++) {
            writer.write(String.valueOf(result[i]));
            writer.write(" ");
        }
        writer.flush();
    }

    ///////////////////Застёжка-молния///////////////////
    public static void zipper() throws IOException, RuntimeException {
        //input
        List<Integer> arr1;
        List<Integer> arr2;
        int n;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            n = Integer.parseInt(reader.readLine().strip());
            arr1 = Arrays.asList(reader.readLine().strip().split(" "))
                    .stream()
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
            arr2 = Arrays.asList(reader.readLine().strip().split(" "))
                    .stream()
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
        }

        //program
        List<Integer> result = new ArrayList<>();
        int lenResultArr = n * 2;
        int mainIndex = 0;

        for (int i = 0; i < lenResultArr; i++) {
            if (i % 2 == 0) {
                result.add(arr1.get(mainIndex));
            } else {
                result.add(arr2.get(mainIndex));
                ++mainIndex;
            }
        }

        //output
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        for (int i : result) {
            writer.write(String.valueOf(i));
            writer.write(" ");
        }
        writer.flush();
    }
}
