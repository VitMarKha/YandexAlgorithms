package org.fngoc.sprint_8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/*
 * K. Сравнить две строки
 */

public class CompareTwoStrings {

    private static Map<Character, Integer> map = new HashMap<>();

    static {
        map.put('a', 1);
        map.put('b', 2);
        map.put('c', 3);
        map.put('d', 4);
        map.put('e', 5);
        map.put('f', 6);
        map.put('g', 7);
        map.put('h', 8);
        map.put('i', 9);
        map.put('j', 10);
        map.put('k', 11);
        map.put('l', 12);
        map.put('m', 13);
        map.put('n', 14);
        map.put('o', 15);
        map.put('p', 16);
        map.put('q', 17);
        map.put('r', 18);
        map.put('s', 19);
        map.put('t', 20);
        map.put('u', 21);
        map.put('v', 22);
        map.put('w', 23);
        map.put('x', 24);
        map.put('y', 25);
        map.put('z', 26);
    }

    private static String first;
    private static String second;

    private static StringBuilder firstBuild = new StringBuilder();
    private static StringBuilder secondBuild = new StringBuilder();

    public static void main(String[] args) throws IOException {
        input();
        removingOddLetters(first, firstBuild);
        removingOddLetters(second, secondBuild);
        output(firstBuild.toString().compareTo(secondBuild.toString()));
    }

    private static void removingOddLetters(String str, StringBuilder builder) {
        for (int i = 0; i < str.length(); i++) {
            if (map.get(str.charAt(i)) % 2 == 0)
                builder.append(str.charAt(i));
        }
    }

    private static void output(int number) {
        if (number < 0)
            System.out.println(-1);
        else if (number > 0)
            System.out.println(1);
        else
            System.out.println(0);
    }

    private static void input() throws IOException {
        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            first = reader.readLine();
            second = reader.readLine();
        }
    }
}
