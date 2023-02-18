package org.fngoc.sprint_7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

/*
 * A. Биржа
 */

public class Exchange {

    private static int[] daysPrice;
    private static boolean purchase;
    private static int priceNow;
    private static int result;

    public static void main(String[] args) throws IOException {
        input();
        greedyAlgorithm();
        output();
    }

    private static void greedyAlgorithm() {
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

    private static void input() throws IOException {
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

    private static void output() {
        System.out.println(result);
    }
}
