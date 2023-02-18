package org.fngoc.sprint_1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

/*
 * E. Самое длинное слово
 */

public class LongestWord {

    public static void main(String[] args) throws IOException {
        //input
        int lenText;
        ArrayList<String> words;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            lenText = Integer.parseInt(reader.readLine().strip());
            words = new ArrayList<>(Arrays.asList(reader.readLine().strip().split(" ")));
        }

        //program
        String resultWord = null;
        int resultLen = 0;
        for (int i = words.size() - 1; i >= 0; i--) {
            int len = words.get(i).length();
            if (resultLen <= len) {
                resultWord = words.get(i);
                resultLen = len;
            }
        }

        //output
        System.out.println(resultWord);
        System.out.print(resultLen);
    }
}
