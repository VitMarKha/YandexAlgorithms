package org.fngoc.sprint_1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * F. Палиндром
 */

public class Palindrome {

    public static void main(String[] args) throws IOException {
        //input
        String line;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            line = reader.readLine().strip();
        }

        //program and output
        int start = 0;
        int end = line.length() - 1;

        while (start < end) {
            if (!isLetterASCII(line.charAt(start)))
                start += 1;
            else if (!isLetterASCII(line.charAt(end)))
                end -= 1;
            else {
                if (line.charAt(start) == line.charAt(end)) {
                    start += 1;
                    end -= 1;
                    continue;
                }
                int one = line.charAt(start) - line.charAt(end);
                int two = line.charAt(end) - line.charAt(start);
                if (one == 32 || one == -32 || two == 32 || two == -32) {
                    start += 1;
                    end -= 1;
                } else {
                    System.out.println("False");
                    return;
                }
            }
        }
        System.out.print("True");
    }

    public static boolean isLetterASCII(char c) {
        return (c >= 65 && c <= 90) || (c >= 97 && c <= 122);
    }
}
