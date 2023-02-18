package org.fngoc.sprint_1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * H. Двоичная система
 */

public class BinarySystem {

    public static void main(String[] args) throws IOException {
        //input
        String firstLine;
        String secondLine;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            firstLine = reader.readLine().strip();
            secondLine = reader.readLine().strip();
        }

        //program
        StringBuilder result = new StringBuilder();
        StringBuilder max;
        StringBuilder min;
        int remember = 0;

        if (firstLine.length() != secondLine.length()) {
            max = new StringBuilder(firstLine.length() > secondLine.length() ? firstLine : secondLine);
            min = new StringBuilder(firstLine.length() < secondLine.length() ? firstLine : secondLine);
            int difference = max.length() - min.length();

            for (int i = 0; i < difference; i++) {
                min.insert(0,0);
            }
        } else {
            max = new StringBuilder(firstLine);
            min = new StringBuilder(secondLine);
        }

        for (int i = max.length() - 1; i >= 0; i--) {
            if (max.charAt(i) == '0' && min.charAt(i) == '0') {
                if (remember > 0) {
                    result.append(1);
                    remember -= 1;
                } else
                    result.append(0);
            }
            else if (max.charAt(i) == '1' && min.charAt(i) == '0') {
                if (remember > 0)
                    result.append(0);
                else
                    result.append(1);
            }
            else if (max.charAt(i) == '0' && min.charAt(i) == '1') {
                if (remember > 0)
                    result.append(0);
                else
                    result.append(1);
            }
            else if (max.charAt(i) == '1' && min.charAt(i) == '1') {
                if (remember > 0) {
                    result.append(1);
                } else {
                    result.append(0);
                    remember += 1;
                }
            }
        }
        for (int i = 0; i < remember; i++) {
            result.append(1);
        }

        //output
        System.out.print(result.reverse().toString());
    }
}
