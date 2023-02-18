package org.fngoc.sprint_4;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/*
 * H. Странное сравнение
 */

public class StrangeComparison {

    public static void main(String[] args) throws IOException {
        //input
        final String str1;
        final String str2;
        final BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            str1 = reader.readLine();
            str2 = reader.readLine();
        }

        //program and output
        writer.write(strangeCompare(str1, str2));
        writer.flush();
    }

    private static String strangeCompare(final String str1, final String str2) {
        if (str1.length() != str2.length())
            return "NO";

        char ch1;
        char ch2;
        final Map<Character, Character> map = new HashMap<>();
        final Map<Character, Character> mapReversed = new HashMap<>();
        for (int i = 0; i < str1.length(); i++) {
            ch1 = str1.charAt(i);
            ch2 = str2.charAt(i);

            if (map.containsKey(ch1) && !map.get(ch1).equals(ch2))
                return "NO";
            if (mapReversed.containsKey(ch2) && !mapReversed.get(ch2).equals(ch1))
                return "NO";

            map.put(ch1, ch2);
            mapReversed.put(ch2, ch1);
        }
        return "YES";
    }
}
