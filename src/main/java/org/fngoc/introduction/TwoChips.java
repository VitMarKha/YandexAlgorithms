package org.fngoc.introduction;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/*
 * D. Две фишки
 */

public class TwoChips {

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
}
