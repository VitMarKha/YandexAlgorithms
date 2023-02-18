package org.fngoc.sprint_3;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

/*
 * I. Любители конференций
 */

public class ConferenceLovers {

    public static void main(String[] args) throws IOException {
        //input
        final int N;
        final int K;
        final int[] ids;
        final StringBuilder output = new StringBuilder();
        final BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            N = Integer.parseInt(reader.readLine());
            ids = new int[N];
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());

            for (int i = 0; i < ids.length; i++)
                ids[i] = Integer.parseInt(tokenizer.nextToken());

            K = Integer.parseInt(reader.readLine());
        }

        //program
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < ids.length; i++) {
            if (map.containsKey(ids[i]))
                map.put(ids[i], map.get(ids[i]) + 1);
            else
                map.put(ids[i], 1);
        }

        List<Map.Entry<Integer, Integer>> list = map.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toList());

        //output
        for (int i = 0; i < K; i++)
            output.append(list.get(i).getKey()).append(" ");
        writer.write(output.toString());
        writer.flush();
    }
}
