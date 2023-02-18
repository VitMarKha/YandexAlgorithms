package org.fngoc.sprint_1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/*
 * L. Лишняя буква
 */

public class ExtraLetter {

    public static void main(String[] args) throws IOException {
        //input
        String S;
        String T;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            S = reader.readLine().strip();
            T = reader.readLine().strip();
        }

        //program and output
        Map<Character, Integer> map = new HashMap<>();

        for (int i = 0; i < S.length(); i++)
            map.put(S.charAt(i), map.get(S.charAt(i)) == null ? 1 : map.get(S.charAt(i)) + 1);

        for (int i = 0; i < T.length(); i++) {
            Character ch = T.charAt(i);

            if (map.containsKey(ch) && map.get(ch) != 0)
                map.put(ch, map.get(ch) - 1);
            else {
                System.out.println(ch);
                return;
            }
        }
    }
}
