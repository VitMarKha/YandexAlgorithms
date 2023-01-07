package org.fngoc;

import java.io.*;
import java.util.*;

public class App {

    public static void main( String[] args ) throws Exception {
        System.out.println("Start program!\n");

        //Тренировка
//        System.out.println(Arrays.toString(Training.movingAverageSlow(new int[]{4, 3, 8, 1, 5, 6, 3}, 2)));
//        System.out.println(Arrays.toString(Training.movingAverageFast(new int[]{4, 3, 8, 1, 5, 6, 3}, 3)));
//        System.out.println(Arrays.toString(Training.twoSumSlow(new int[]{2, 1, 3, 5, 5}, 4)));
//        System.out.println(Arrays.toString(Training.twoSumFastWithSort(new int[]{2, 1, 3, 5, 5}, 8)));
//        System.out.println(Arrays.toString(Training.twoSumFastWithCreateMap(new int[]{2, 1, 3, 5, 4}, 5)));
//        System.out.println(Arrays.toString(Training.twoSumFastWithGenerateMap(new int[]{2, 1, 3, 5, 4}, 4)));
//        System.out.println(Training.findNumberByArray(new int[] {5,3,7,2,8,1}, 2));
//        System.out.println(Training.bestVisitor(new int[] {0,2,3,2,0,4,1,1,2,4,1,1,3,3,3,3,3}, 5));
//        Training.countGenomeSlow(new char[] {'C', 'C', 'A', 'T', 'G', 'A', 'T', 'C'}, 5, 7, 'C');
//        Training.countGenomeFast(new char[] {'C', 'C', 'A', 'T', 'G', 'A', 'T', 'C'}, 5, 7);
//        System.out.println(Training.isSimpleSlow(13));
//        System.out.println(Training.isSimpleFast(13));
//        int[] array = new int[] {2,3,4,5,6,7,8,9,10,11};
//        System.out.println(Training.recursiveBinSearch(array, 4, 0,  array.length));
//        System.out.println(Training.binSearch(array, 4));
//        Training.printTree(3, "");

        //Задачи
//        Solution.zipper();
//        Solution.movingAverageFastTask();
//        Solution.twoChips();
//        Solution.twoChipsFast();
//        Solution.functionValues();
//        Solution.evenAndOddNumbers();
//        Solution.neighbours();
//        Solution.randomWeather();
//        Solution.longestWord();
//        Solution.palindrome();
//        Solution.workFromHome();
//        Solution.binarySystem();
//        Solution.powerOfFour();
//        Solution.factorization();
//        Solution.listForm();
//        Solution.extraLetter();
//        Solution.monitoring();
//        Solution.todoList(new Node<>("node3", null));
//        Solution.unlovedBusiness()
//        Solution.caringMother()
//        Solution.wayAround()
//        Solution.stackMax();
//        Solution.bracketSequence();
//        Solution.limitedQueue();
//        Solution.listQueue();
//        Solution.recursiveFibonacciNumbers();
//        Solution.fibonacciModulo();
//        Solution.twoBicycles();
//        Solution.parenthesesGenerator();
//        Solution.combinations();
//        Solution.bubble();
//        Solution.bigNumber();
//        flowerbeds();
//        subsequence();
//        merge(new int[]{1,6,8,9,3,5,6},0, 4, 7);
//        Solution.wardrobe();
//        cookies();
//        Solution.buyingHouses();
//        Solution.perimeterTriangle();
//        Solution.strangeComparison();
//        Solution.polynomialHash();
//        sum4();
//        sum4String();
//        Solution.substrings();
//        Solution.anagramGrouping();
//        Solution.breakMe();
//        System.out.println("friabili".hashCode());
//        System.out.println("tensada".hashCode());
//        Solution.lightBulbs();
//        Solution.searchTree();
//        Solution.differentSearchTrees();
//        Solution.printRange();
//        Solution.insert();
//        Solution.isBalanceTree();

        System.out.println("\nEnd program!");
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
