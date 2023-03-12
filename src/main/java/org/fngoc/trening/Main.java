package org.fngoc.trening;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
//        generate("", 3, 3);
//        System.out.println(formatStr("AAAABBBCCXYZDDDDEEEFFFAAAAAABBBBBBBBBBBBBBBBBBBBBBBBBBBBDD"));
//        System.out.println(lenVertexOne());
//        System.out.println(countUnicNumbers());
//        rightBracketByNumber(3);
//        System.out.println(isAnagram("daa","ad"));
//        input();
//        System.out.println(Arrays.toString(towSum(new int[]{2,4,3,5}, 7)));
        mergeSortedLists();
    }

    private static void mergeSortedLists() throws IOException {
        int[][] arrays;

        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int n = Integer.parseInt(reader.readLine());
            StringTokenizer tokenizer;
            arrays = new int[n][0];

            for (int i = 0; i < n; i++) {
                tokenizer = new StringTokenizer(reader.readLine());
                int lenArray = Integer.parseInt(tokenizer.nextToken());
                arrays[i] = new int[lenArray];

                for (int j = 0; j < lenArray; j++) {
                    arrays[i][j] = Integer.parseInt(tokenizer.nextToken());
                }
            }
            System.out.println(Arrays.deepToString(arrays));
        }

        int[] ans = arrays[0];
        for (int i = 1; i < arrays.length; i++) {
            ans = concatWithArrayCopy(ans, arrays[i]);
            ans = merge(ans);
        }
        System.out.println(Arrays.toString(ans));
    }

    private static int[] concatWithArrayCopy(int[] array1, int[] array2) {
        int[] result = Arrays.copyOf(array1, array1.length + array2.length);
        System.arraycopy(array2, 0, result, array1.length, array2.length);
        return result;
    }

    private static int[] merge(int[] array) {
        if (array.length == 1)
            return array;

        int[] left = merge(Arrays.copyOfRange(array, 0, array.length / 2));
        int[] right = merge(Arrays.copyOfRange(array, array.length / 2, array.length));

        int[] result = new int[array.length];
        int i = 0;
        int j = 0;
        int k = 0;
        while (i < left.length && j < right.length) {
            if (left[i] <= right[j]) {
                result[k] = left[i];
                i += 1;
            } else {
                result[k] = right[j];
                j += 1;
            }
            k += 1;
        }

        while (i < left.length) {
            result[k] = left[i];
            i += 1;
            k += 1;
        }
        while (j < right.length) {
            result[k] = right[j];
            j += 1;
            k += 1;
        }

        return result;
    }


    public static void mainDFS(Map<Integer, List<Integer>> graph, int[] color) {
        for (int i = 0; i < color.length; i++) {
            if (color[i] == 0)
                DFS(i, color, graph);
        }
    }

    private static void DFS(int vertex, int[] color, Map<Integer, List<Integer>> graph) {
        color[vertex] = 1;
        List<Integer> ribs = graph.get(vertex);

        for (int j = 0; j < ribs.size(); j++) {
            if (color[ribs.get(j)] == 0)
                DFS(j, color, graph);
        }
        color[vertex] = 2;
    }

    public static void BFS(int startVertex, Map<Integer, List<Integer>> graph) {
        Queue<Integer> planned = new ArrayDeque<>();
        int[] color = new int[graph.size() + 1];
        int[] previous = new int[graph.size() + 1];
        int[] distant = new int[graph.size() + 1];

        planned.add(startVertex);
        color[startVertex] = 1;
        distant[startVertex] = 0;

        while (!planned.isEmpty()) {
            int currentVertex = planned.remove();
            List<Integer> ribs = graph.get(currentVertex);

            for (Integer rib : ribs) {
                if (color[rib] == 0) {
                    color[rib] = 1;
                    planned.add(rib);
                    previous[rib] = currentVertex;
                    distant[rib] = distant[previous[rib]] + 1;
                }
            }
            color[currentVertex] = 2;
        }
    }

    private static int[] towSum(int[] array, int target) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < array.length; i++) {
            int com = target - array[i];

            if (map.containsKey(com))
                return new int[] {map.get(com), i};
            else
                map.put(array[i], i);
        }
        return new int[]{};
    }

    private static void input() throws IOException {
        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int n = Integer.parseInt(reader.readLine());

            int[][] citiesXY = new int[n][2];

            for (int i = 0; i < n; i++) {
                StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
                citiesXY[i][1] = Integer.parseInt(tokenizer.nextToken());
                citiesXY[i][2] = Integer.parseInt(tokenizer.nextToken());
            }
            int maxDist = Integer.parseInt(reader.readLine());
        }
    }


    private static int isAnagram(String str1, String str2) {
        char[] chars1 = str1.toCharArray();
        Arrays.sort(chars1);
        char[] chars2 = str2.toCharArray();
        Arrays.sort(chars2);
        return Arrays.equals(chars1, chars2) ? 1 : 0;
    }

    private static void rightBracketByNumber(int number) {
        StringBuilder output = new StringBuilder();
        int left = number;
        int right = number;

        while (left != 0 && right != 0) {
            if (left > 0) {
                output.append("(");
                left -= 1;
            }
            if (right > 0) {
                output.append(")");
                right -= 1;
            }
        }
        System.out.println(output);
//        if (left > right)
//            return;
//
//        if (left == 0 && right == 0) {
//            System.out.println(str);
//            return;
//        }
//
//        if (left > 0)
//            rightBracketByNumber(str + "(", left - 1, right);
//        if (right > 0)
//            rightBracketByNumber(str + ")", left, right - 1);
    }

    private static String countUnicNumbers() throws IOException {
        StringBuilder output = new StringBuilder();

        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int n = Integer.parseInt(reader.readLine());
            int prev = 0;
            boolean flag = false;

            for (int i = 0; i < n; i++) {
                int value = Integer.parseInt(reader.readLine());
                if (!flag) {
                    output.append(value).append('\n');
                    prev = value;
                    flag = true;
                }
                if (value != prev) {
                    output.append(value).append('\n');
                    prev = value;
                }
            }
        }
        return output.toString();
    }

    public static int lenVertexOne() throws IOException {
        int max = 0;

        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int n = Integer.parseInt(reader.readLine());
            int counter = 0;

            for (int i = 0; i < n; i++) {
                int value = Integer.parseInt(reader.readLine());
                if (value == 1)
                    counter += 1;
                else
                    counter = 0;
                max = Math.max(max, counter);
            }
        }
        return max;
    }

    private static void generate(String str, int left, int right) {
        if (left > right)
            return;

        if (left == 0 && right == 0)
            System.out.println(str);

        if (left > 0)
            generate(str + "(", left - 1, right);
        if (right > 0)
            generate(str + ")", left, right - 1);
    }

    public static String formatStr(String line) {
        StringBuilder formatLine = new StringBuilder();
        int counter = 1;
        char prev = line.charAt(0);

        for (int i = 1; i < line.length(); i++) {
            if (line.charAt(i) != prev) {
                if (counter == 1)
                    formatLine.append(prev);
                else
                    formatLine.append(format(prev, counter));
                prev = line.charAt(i);
                counter = 1;
            } else
                counter += 1;
        }
        if (counter == 1)
            formatLine.append(prev);
        else
            formatLine.append(format(prev, counter));
        return formatLine.toString();
    }

    private static String format(char ch, int number) {
        return ch + "[" + number + "]";
    }
}
