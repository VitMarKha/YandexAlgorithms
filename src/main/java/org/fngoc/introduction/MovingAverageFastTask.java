package org.fngoc.introduction;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/*
 * C. Скользящее среднее
 */

public class MovingAverageFastTask {

    public static void main(String[] args) throws IOException, RuntimeException {
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
}
