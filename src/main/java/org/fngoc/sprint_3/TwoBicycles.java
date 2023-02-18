package org.fngoc.sprint_3;

import java.io.*;
import java.util.StringTokenizer;

/*
 * L. Два велосипеда
 */

public class TwoBicycles {

    public static void main(String[] args) throws IOException {
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
}
