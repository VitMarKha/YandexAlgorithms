package org.fngoc.sprint_4;

import java.io.*;
import java.util.LinkedHashSet;
import java.util.Set;

/*
 * D. Кружки
 */

public class Circles {

    public static void main(String[] args) throws IOException {
        //input and program
        final int N;
        final Set<String> set = new LinkedHashSet<>();
        final StringBuilder output = new StringBuilder();
        final BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            N = Integer.parseInt(reader.readLine());

            String line;
            for (int i = 0; i < N; i++) {
                line = reader.readLine();
                if(!set.contains(line))
                    set.add(line);
            }
        }

        //output
        for (String s : set)
            output.append(s).append('\n');
        writer.write(output.toString());
        writer.flush();
    }
}
