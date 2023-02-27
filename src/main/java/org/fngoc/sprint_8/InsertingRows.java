package org.fngoc.sprint_8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//TODO не решенная задача
public class InsertingRows {

    private static String mainLine;
    private static int trueSize;
    private static int currentSize;

    public static void main(String[] args) throws IOException {
        input();
        output();
    }

    private static void insert(String str, int index) {
        int len = mainLine.length();
        int shift = str.length();

        if (index > 0) {

        }
    }

    private static void output() {
        System.out.println(mainLine);
    }

    private static void input() throws IOException {
        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            mainLine = reader.readLine();
            trueSize = mainLine.length();
            int n = Integer.parseInt(reader.readLine());
            StringTokenizer tokenizer;

            for (int i = 0; i < n; i++) {
                tokenizer = new StringTokenizer(reader.readLine());
                String str = tokenizer.nextToken();
                int index = Integer.parseInt(tokenizer.nextToken());
                insert(str, index);
            }
        }
    }
}
