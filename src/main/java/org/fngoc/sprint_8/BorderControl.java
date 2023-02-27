package org.fngoc.sprint_8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * B. Пограничный контроль
 */

public class BorderControl {

    private static String firstStr;
    private static String secondStr;

    public static void main(String[] args) throws IOException {
        input();
        output(levenshteinDistanceCalculation());
    }

    private static boolean levenshteinDistanceCalculation() {
        int counter = 0;
        int idxFirst = 0;
        int idxSecond = 0;

        if (Math.abs(firstStr.length() - secondStr.length()) > 1)
            return true;

        while (idxFirst < firstStr.length() && idxSecond < secondStr.length()) {
            if (firstStr.charAt(idxFirst) == secondStr.charAt(idxSecond)) {
                idxFirst += 1;
                idxSecond += 1;
                continue;
            }
            counter += 1;
            if (counter > 1)
                return true;

            if (firstStr.length() == secondStr.length()) {
                idxFirst += 1;
                idxSecond += 1;
            } else if (firstStr.length() < secondStr.length())
                idxSecond += 1;
            else
                idxFirst += 1;
        }
        return false;
    }

    private static void output(boolean isTrue) {
        if (isTrue)
            System.out.println("FAIL");
        else
            System.out.println("OK");
    }

    private static void input() throws IOException {
        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            firstStr = reader.readLine();
            secondStr = reader.readLine();
        }
    }
}
