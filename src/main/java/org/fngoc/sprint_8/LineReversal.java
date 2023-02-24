package org.fngoc.sprint_8;

import java.io.*;
import java.util.Stack;
import java.util.StringTokenizer;

/*
 * A. Разворот строки
 */

public class LineReversal {

    private static final Stack<String> stack = new Stack<>();

    public static void main(String[] args) throws IOException {
        input();
        output();
    }

    private static void output() throws IOException {
        final BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder output = new StringBuilder();
        int size = stack.size();

        for (int i = 0; i < size; i++)
            output.append(stack.pop()).append(" ");

        writer.write(output.toString());
        writer.flush();
    }

    private static void input() throws IOException {
        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            StringTokenizer tokenizer =  new StringTokenizer(reader.readLine());

            while (tokenizer.hasMoreTokens())
                stack.push(tokenizer.nextToken());
        }
    }
}
