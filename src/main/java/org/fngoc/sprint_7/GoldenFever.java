package org.fngoc.sprint_7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * C. Золотая лихорадка
 */

public class GoldenFever {

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

    public static void main(String[] args) throws IOException {
        input();
        counting();
        output();
    }

    private static void counting() {
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
    }

    private static void input() throws IOException {
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

    private static void output() {
        System.out.println(resultCountPrice);
    }
}
