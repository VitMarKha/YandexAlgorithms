package org.fngoc.sprint_8;

import java.io.*;

/*
 * H. Глобальная замена
 */

public class GlobalReplacement {

    private static StringBuilder line;
    private static String pattern;
    private static String insertText;

    public static void main(String[] args) throws IOException {
        input();

        for (int i = 0; i < line.length(); i++) {
            boolean match = true;
            for (int j = 0; j < pattern.length(); j++) {
                if (i + j == line.length() || line.charAt(i + j) != pattern.charAt(j)) {
                    match = false;
                    break;
                }
            }
            if (match) {
                StringBuilder start = new StringBuilder(line.substring(0, i));
                StringBuilder end = new StringBuilder(line.substring(i + pattern.length()));
                line = new StringBuilder(start).append(insertText).append(end);
                if (pattern.length() <= insertText.length())
                    i += insertText.length() - 1;
            }
        }

        output();
    }

    private static void output() throws IOException {
        final BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        writer.write(line.toString());
        writer.flush();
    }

    private static void input() throws IOException {
        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            line = new StringBuilder(reader.readLine());
            pattern = reader.readLine();
            insertText = reader.readLine();
        }
    }
}
