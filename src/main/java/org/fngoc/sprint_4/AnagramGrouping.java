package org.fngoc.sprint_4;

import java.io.*;
import java.util.*;

/*
 * F. Анаграммная группировка
 */

public class AnagramGrouping {

    public static void main(String[] args) throws IOException {
        //input
        final int N;
        final List<char[]> mainList = new ArrayList<>();
        final BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            N = Integer.parseInt(reader.readLine());
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());

            for (int i = 0; i < N; i++) {
                char[] chars = tokenizer.nextToken().toCharArray();
                Arrays.sort(chars);
                mainList.add(chars);
            }
        }

        //program
        final StringBuilder output = new StringBuilder();
        final Set<Set<Integer>> checkSet = new HashSet<>();

        for (int i = 0; i < mainList.size(); i++) {
            final Set<Integer> appendSet = new TreeSet<>();
            if (chetInSet(checkSet, i))
                continue;
            appendSet.add(i);

            for (int j = i; j < mainList.size(); j++) {
                if (Arrays.equals(mainList.get(i), mainList.get(j)) && mainList.get(i) != mainList.get(j)) {
                    appendSet.add(j);
                }
            }

            if (!checkSet.contains(appendSet)) {
                checkSet.add(appendSet);
                for (Integer integer : appendSet)
                    output.append(integer).append(' ');
                output.append('\n');
            }
        }

        //output
        writer.write(output.toString());
        writer.flush();
    }

    private static boolean chetInSet(Set<Set<Integer>> checkSet, int i) {
        for (Set<Integer> set : checkSet) {
            if (set.contains(i))
                return true;
        }
        return false;
    }
}
