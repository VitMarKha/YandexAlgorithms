package org.fngoc.sprint_8;

import java.io.*;
import java.util.*;

//TODO не решенная задача
public class CamelCase {

    private static Set<String> setAdded = new HashSet<>();
    private static List<List<Character>> patterArray = new ArrayList<>();
    private static String[] lines;
    private static List<Character>[] matches;

    private static List<String>[] arrayMathResult;

    public static void main(String[] args) throws IOException {
        input();

        arrayMathResult = new ArrayList[matches.length];

        for (int i = 0; i < patterArray.size(); i++) {
            List<Character> patter = patterArray.get(i);

            for (int j = 0; j < lines.length; j++) {
                String line = lines[j];
                List<Character> patternLine = matches[j];

                boolean math = true;
                int iterator = 0;
                if (patternLine.size() == 0)
                    math = false;
                while (iterator < patternLine.size() && iterator < patter.size()) {
                    if (patter.get(iterator) != patternLine.get(iterator)) {
                        math = false;
                        break;
                    }
                    iterator += 1;
                }
                if (math) {
                    if (!setAdded.contains(line)) {
                        if (arrayMathResult[i] == null)
                            arrayMathResult[i] = new ArrayList<>();
                        arrayMathResult[i].add(line);
                        setAdded.add(line);
                    }
                }
            }
        }

        output();
    }

    private static void output() throws IOException {
        final BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder output = new StringBuilder();

        for (int i = 0; i < arrayMathResult.length; i++) {
            List<String> list = arrayMathResult[i];
            if (list == null) {
                output.append("").append('\n');
                continue;
            }
            Collections.sort(list);
            for (String str : list) {
                output.append(str).append('\n');
            }
        }

        writer.write(output.toString());
        writer.flush();
    }

    private static void input() throws IOException {
        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int n = Integer.parseInt(reader.readLine());
            matches = new ArrayList[n];
            lines = new String[n];

            for (int i = 0; i < n; i++) {
                List<Character> chars = new ArrayList<>();
                String line = reader.readLine();

                for (int j = 0; j < line.length(); j++) {
                    char ch = line.charAt(j);
                    if (isPatternChar(ch, j == 0 ? 'a' : line.charAt(j - 1),
                            j + 1 == line.length() ? 'a' : line.charAt(j + 1)))
                        chars.add(ch);
                }
                matches[i] = chars;
                lines[i] = line;
            }

            int m = Integer.parseInt(reader.readLine());
            for (int i = 0; i < m; i++) {
                List<Character> chars = new ArrayList<>();
                String line = reader.readLine();
                for (int j = 0; j < line.length(); j++)
                    chars.add(line.charAt(j));
                patterArray.add(chars);
            }
        }
    }

    private static boolean isPatternChar(char ch, char prev, char next) {
        return ch >= 65 && ch <= 90 && prev >= 97 && prev <= 122 && next >= 97 && next <= 122;
    }
}
