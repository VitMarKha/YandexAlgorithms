package org.fngoc.introduction;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/*
 * B. Застёжка-молния
 */

public class Zipper {

    public static void main(String[] args) throws IOException, RuntimeException {
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

        // program
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
