package org.fngoc.sprint_3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/*
 * F. Периметр треугольника
 */

public class PerimeterTriangle {

    public static void main(String[] args) throws IOException {
        //input
        final int N;
        final List<Integer> segments = new ArrayList<>();
        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            N = Integer.parseInt(reader.readLine());
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());

            for (int i = 0; i < N; i++)
                segments.add(Integer.parseInt(tokenizer.nextToken()));
        }

        //program
        Collections.sort(segments);
        int perimeterMax = 0;
        for (int i = 0; i < segments.size(); i++) {
            if (segments.size() == i + 1 || segments.size() == i + 2)
                break;
            int a = segments.get(i);
            int b = segments.get(i + 1);
            int c = segments.get(i + 2);

            if (c < (a + b) && (a + b + c) > perimeterMax)
                perimeterMax = a + b + c;
        }

        //output
        System.out.print(perimeterMax);
    }
}
