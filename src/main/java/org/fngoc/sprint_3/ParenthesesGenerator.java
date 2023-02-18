package org.fngoc.sprint_3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
 * A. Генератор скобок
 */

public class ParenthesesGenerator {

    private static final Map<Character, Character> mapBracket = new HashMap<>();
    private static final List<String> list = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        //input
        final int N;
        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            N = Integer.parseInt(reader.readLine());
        }

        //program
        printParentheses(N, N, "");

        //output
        for (String item : list) {
            if (isBracketSequence(item))
                System.out.println(item);
        }
    }

    private static void printParentheses(int open, int close, String line) {
        if (open == 0 && close == 0)
            list.add(line);

        if (open != 0)
            printParentheses(open - 1, close, line + "(");
        if (close != 0)
            printParentheses(open, close - 1, line + ")");
    }

    private static boolean isBracketSequence(String line) {
        Stack<Character> stack = stringToStack(line);
        Stack<Character> buffer = new Stack<>();
        char bracket;

        while (!stack.isEmpty()) {
            bracket = stack.pop();

            if (isCloseBracket(bracket))
                buffer.push(bracket);
            else if (isOpenBracket(bracket)) {
                if (buffer.empty() || !compareBracket(bracket, buffer.pop()))
                    return false;
            } else
                return false;
        }
        return true;
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

    private static Stack<Character> stringToStack(String str) {
        Stack<Character> result = new Stack<>();

        for (int i = 0; i < str.length(); i++) {
            result.push(str.charAt(i));
        }
        return result;
    }
}
