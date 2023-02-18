package org.fngoc.sprint_4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

/*
 * E. Подстроки
 */

public class Substrings {

    public static void main(String[] args) throws IOException {
        final String str;
        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            str = reader.readLine();
        }

        int resultLen = 0;
        for (int i = 0; i < str.length(); i++) {
            Set<Character> set = new HashSet<>();
            for (int j = i; j < str.length(); j++) {
                if (set.contains(str.charAt(j)))
                    break;
                set.add(str.charAt(j));
            }
            if (resultLen < set.size())
                resultLen = set.size();
        }
        System.out.print(resultLen);
    }
}
