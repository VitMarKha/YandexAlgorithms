package org.fngoc.sprint_2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.StringTokenizer;

/*
 * F. Стек - Max
 */

public class StackMax {

    private static List<Integer> stack = new ArrayList<>();
    private static List<Integer> stackMax = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        //input, output and program
        int N;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            N = Integer.parseInt(reader.readLine().strip());
            StringTokenizer tokenizer;

            for (int i = 0; i < N; i++) {
                tokenizer = new StringTokenizer(reader.readLine());
                String command = tokenizer.nextToken();
                if (command.equals("push")) {
                    int num = Integer.parseInt(tokenizer.nextToken());
                    push(num);
                } else if (command.equals("pop"))
                    pop();
                else
                    get_max();
            }
        }
    }

    private static void push(int num) {
        if (stackMax.isEmpty() || num >= stackMax.get(stackMax.size() - 1))
            stackMax.add(num);
        stack.add(num);
    }

    private static void pop() {
        if (stack.isEmpty())
            System.out.println("error");
        else {
            if (!stackMax.isEmpty() && Objects.equals(stack.get(stack.size() - 1), stackMax.get(stackMax.size() - 1)))
                stackMax.remove(stackMax.size() - 1);
            stack.remove(stack.size() - 1);
        }
    }

    private static void get_max() {
        if (stack.isEmpty())
            System.out.println("None");
        else
            System.out.println(stackMax.get(stackMax.size() - 1));
    }
}
