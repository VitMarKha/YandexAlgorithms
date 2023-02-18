package org.fngoc.sprint_3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
 * H. Большое число
 */

public class BigNumber {

    public static void main(String[] args) throws IOException {
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
}
