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
//        int[] array = new int[] {2,3,4,5,6,7,8,9,10};
//        System.out.println(recursiveBinarySearch(array, 7, array.length - 1, 0));

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
        twoBicycles();

        System.out.println("\nEnd program!");
    }

    private static void twoBicycles() throws IOException {
        //input and program
        final int N;
        final int price;
        int[] days;
        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            N = Integer.parseInt(reader.readLine());
            days = new int[N];
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());

            for (int i = 0; i < days.length; i++)
                days[i] = Integer.parseInt(tokenizer.nextToken());

            price = Integer.parseInt(reader.readLine());
        }

        //program and output
        System.out.print(binSearch(days, price) + 1 + " ");
        System.out.print(binSearch(days, price * 2) + 1);
    }

    private static int recursiveBinSearch(int[] array, int number, int min, int max) {
        if (min > max)
            return -2;

        int middle = (min + max) / 2;
        if (array[middle] == number)
            return middle;
        else if (number < array[middle])
            return recursiveBinarySearch(array, number, min, middle);
        else
            return recursiveBinarySearch(array, number, middle, max);
    }

    private static int binSearch(int[] array, int num) {
        int middle;
        int min = 0;
        int max = array.length - 1;

        while (min < max) {
            middle = (min + max) / 2;
            if (array[middle] >= num)
                return middle;
            else if (num < array[middle])
                max = middle - 1;
            else
                min = middle + 1;
        }
        return -2;
    }

    private static int recursiveBinarySearch(int[] array, int num, int max, int min) {
        if (max <= min)
            return -1;

        int middle = (min + max) / 2;
        if (array[middle] == num)
            return middle;
        else if (array[middle] < num)
            return recursiveBinarySearch(array, num, max, middle);
        else
            return recursiveBinarySearch(array, num, middle, min);
    }
}
