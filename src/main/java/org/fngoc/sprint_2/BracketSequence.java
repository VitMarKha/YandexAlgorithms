package org.fngoc.sprint_2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/*
 * H. Скобочная последовательность
 */

public class BracketSequence {

    private static final Map<Character, Character> mapBracket = new HashMap<>();

    static {
        mapBracket.put('(', ')');
        mapBracket.put('{', '}');
        mapBracket.put('[', ']');
    }

    public static void main(String[] args) throws IOException {
        //input
        String line;
        Stack<Character> stack = new Stack<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            line = reader.readLine().strip();

            for (int i = 0; i < line.length(); i++)
                stack.push(line.charAt(i));
        }

        //program and output
        System.out.println(isBracketSequence(stack));
    }

    private static String isBracketSequence(Stack<Character> stack) {
        Stack<Character> buffer = new Stack<>();
        char bracket;

        while (!stack.isEmpty()) {
            bracket = stack.pop();

            if (isCloseBracket(bracket))
                buffer.push(bracket);
            else if (isOpenBracket(bracket)) {
                if (buffer.empty() || !compareBracket(bracket, buffer.pop()))
                    return "False";
            } else
                return "False";
        }
        return "True";
    }

    private static boolean compareBracket(char openBracket, char closeBracket) {
        return mapBracket.get(openBracket).equals(closeBracket);
    }

    private static boolean isCloseBracket(char ch) {
        return ch == ')' || ch == ']' || ch == '}';
    }

    private static boolean isOpenBracket(char ch) {
        return ch == '(' || ch == '[' || ch == '{';
    }
}
