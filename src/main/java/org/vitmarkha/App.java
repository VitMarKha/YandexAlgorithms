package org.vitmarkha;

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
        bigNumber();

        System.out.println("\nEnd program!");
    }

    public static void bigNumber() throws IOException {
        //input
        final int N;
        List<String> list = new ArrayList<>();
        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            N = Integer.parseInt(reader.readLine());
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            for (int i = 0; i < N; i++)
                list.add(tokenizer.nextToken());
        }

        //program
        bubbleSortList(list);
//        Collections.sort(list);
        System.out.println(list);
        StringBuilder output = new StringBuilder();
        for (int i = list.size() - 1; i >= 0; i--)
            output.append(list.get(i));

        //output
        System.out.println(output);
    }

    private static void bubbleSortList(List<String> list) {
        for (int j = 0; j < list.size(); j++)
            for (int i = 0; i < list.size(); i++)
                if (i + 1 < list.size() && comparator(list.get(i), list.get(i + 1)))
                    swap(list, i);
    }

    private static void swap(List<String> list, int i) {
        String tmp = list.get(i + 1);
        list.set(i + 1, list.get(i));
        list.set(i, tmp);
    }

    private static boolean comparator(String s1, String s2) {
        if (s1.length() == s2.length()) //34 44
            return Integer.parseInt(s1) > Integer.parseInt(s2);

        if (s1.length() > s2.length()) { //991 8, 15 2
            for (int i = 0; i < s1.length(); i++) {
                if (s1.charAt(i) == s2.charAt(0))
                    continue;
                if (s1.charAt(i) > s2.charAt(0))
                    return true;
            }
        }
        if (s1.length() < s2.length()) { //8 991, 2 15
            for (int i = 0; i < s2.length(); i++) {
                if (s2.charAt(i) == s1.charAt(0))
                    continue;
                if (s2.charAt(i) > s1.charAt(0))
                    return true;
            }
        }
        return false;

//        if (s1.length() > s2.length()) //95 9
//        return s1.charAt(s1.length() - 1) > s2.charAt(0);

//        if (s1.length() > s2.length()) {
//            int max = s1.length();
//            for (int i = 1; i < max; i++) {
//                s2 = s2 + "0";
//            }
//        } else {
//            int max = s2.length();
//            for (int i = 1; i < max; i++) {
//                s1 = s1 + "0";
//            }
//        }
//        int int1 = Integer.parseInt(s1);
//        int int2 = Integer.parseInt(s2);
//        System.out.println("int1 " + int1);
//        System.out.println("int2 " + int2);
//        if (int1 > int2)
//            return true;
//        else
//            return false;

        //        if (s1.length() > s2.length()) { //95 9
//            if (s1.charAt(s1.length() - 1) < s2.charAt(0)) //5 9
//                return true;
//            return false;
//        } else if (s1.length() < s2.length()) { //9 95
//            if (s2.charAt(s2.length() - 1) < s1.charAt(0)) // 5 9
//                return true;
//            return false;
//        }
    }

//    private static String getMaxByFirstIndex(List<String> list) {
//        String max = null;
//        if (list.isEmpty())
//            return null;
//
//        for (int i = 0; i < list.size(); i++) {
//            if (max == null || max.charAt(0) < list.get(i).charAt(0))
//                max = list.get(i);
//        }
//        return max;
//    }
}
