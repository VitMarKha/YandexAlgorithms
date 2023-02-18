package org.fngoc.introduction;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/*
 * E. Две фишки - 2
 */

public class TwoChipsTwo {

    public static void main(String[] args) throws Exception {
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
}
