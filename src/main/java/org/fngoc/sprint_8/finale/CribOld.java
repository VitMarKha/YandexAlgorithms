package org.fngoc.sprint_8.finale;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class CribOld {

    private static String line;
    private static List<String> patterns;

    public static void main(String[] args) throws IOException {
        input();
        output(isLineConsistsPatterns());
    }

    private static boolean isLineConsistsPatterns() {
        patterns.sort(Comparator.comparingInt(String::length).reversed());

        for (String pattern : patterns) {
            int startPatterIdx = findPrefixIdx(pattern, line);

            while (startPatterIdx != -1) {
                line = deleteStrByGap(startPatterIdx, startPatterIdx + pattern.length());
                startPatterIdx = findPrefixIdx(pattern, line);
            }
        }
        return line.isEmpty();
    }

    private static int findPrefixIdx(String prefix, String text) {
        String str = prefix + "#" + text;
        int[] prefixArray = new int[prefix.length()];
        int prevIdx = 0;

        for (int i = 1; i < str.length(); i++) {
            int k = prevIdx;

            while (k > 0 && str.charAt(k) != str.charAt(i))
                k = prefixArray[k - 1];

            if (str.charAt(k) == str.charAt(i))
                k += 1;

            if (i < prefix.length())
                prefixArray[i] = k;

            prevIdx = k;

            if (k == prefix.length())
                return (i - 2 * prefixArray.length);
        }
        return -1;
    }

    private static String deleteStrByGap(int startIdx, int endIdx) {
        return line.substring(0, startIdx) + line.substring(endIdx);
    }

    private static void output(boolean isTrue) {
        if (isTrue)
            System.out.println("YES");
        else
            System.out.println("NO");
    }

    private static void input() throws IOException {
        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            line = reader.readLine();
            int n = Integer.parseInt(reader.readLine());
            patterns = new ArrayList<>();

            for (int i = 0; i < n; i++)
                patterns.add(reader.readLine());
        }
    }
}
