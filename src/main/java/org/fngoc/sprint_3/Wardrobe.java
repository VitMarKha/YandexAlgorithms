package org.fngoc.sprint_3;

import java.io.*;
import java.util.StringTokenizer;

/*
 * G. Гардероб
 */

public class Wardrobe {

    public static void main(String[] args) throws IOException {
        //input
        int N;
        int[] arr;
        StringBuilder output = new StringBuilder();
        final BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            N = Integer.parseInt(reader.readLine());
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());

            arr = new int[N];
            for (int i = 0; i < N; i++)
                arr[i] = Integer.parseInt(tokenizer.nextToken());
        }

        //program
        int[] count = new int[3];
        for (int i = 0; i < arr.length; i++)
            count[arr[i]] += 1;

        for (int i = 0; i < count.length; i++)
            for (int j = 0; j < count[i]; j++)
                output.append(i).append(" ");

        //output
        writer.write(output.toString());
        writer.flush();
    }
}
