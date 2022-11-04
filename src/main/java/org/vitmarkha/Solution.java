package org.vitmarkha;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Solution {

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
