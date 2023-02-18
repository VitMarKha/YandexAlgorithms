package org.fngoc.sprint_1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/*
 * C. Соседи
 */

public class Neighbours {

    public static void main(String[] args) throws IOException {
        //input
        int vertical;
        int horizontal;
        int[][] matrix;
        int y;
        int x;

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            vertical = Integer.parseInt(reader.readLine().strip());
            horizontal = Integer.parseInt(reader.readLine().strip());

            matrix = new int[vertical][horizontal];
            for (int i = 0; i < vertical; i++) {
                List<Integer> integers = Arrays.asList(reader.readLine().strip().split(" "))
                        .stream()
                        .map(Integer::parseInt)
                        .collect(Collectors.toList());
                for (int j = 0; j < horizontal; j++) {
                    matrix[i][j] = integers.get(j);
                }
            }

            y = Integer.parseInt(reader.readLine().strip());
            x = Integer.parseInt(reader.readLine().strip());
        }

        //program
        List<Integer> listResult = new ArrayList<>();

        try {
            listResult.add(matrix[y - 1][x]);
        } catch (ArrayIndexOutOfBoundsException e) {}
        try {
            listResult.add(matrix[y + 1][x]);
        } catch (ArrayIndexOutOfBoundsException e) {}
        try {
            listResult.add(matrix[y][x - 1]);
        } catch (ArrayIndexOutOfBoundsException e) {}
        try {
            listResult.add(matrix[y][x + 1]);
        } catch (ArrayIndexOutOfBoundsException e) {}

        //output
        Collections.sort(listResult);
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < listResult.size(); i++) {
            stringBuilder.append(listResult.get(i) + " ");
        }
        System.out.println(stringBuilder.toString());
    }
}
