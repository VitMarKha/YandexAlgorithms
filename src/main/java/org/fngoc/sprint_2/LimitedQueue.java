package org.fngoc.sprint_2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

/*
 * I. Ограниченная очередь
 */

public class LimitedQueue {

    private static int maxSize;
    private static LinkedList<Integer> queue = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        //input, program and output
        int N;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            N = Integer.parseInt(reader.readLine());
            maxSize = Integer.parseInt(reader.readLine());

            StringTokenizer tokenizer;
            String command;
            for (int i = 0; i < N; i++) {
                tokenizer = new StringTokenizer(reader.readLine());
                command = tokenizer.nextToken();

                if (command.equals("push")) {
                    limitedQueuePush(Integer.parseInt(tokenizer.nextToken()));
                } else if (command.equals("pop"))
                    limitedQueuePop();
                else if (command.equals("peek"))
                    limitedQueuePeek();
                else
                    limitedQueueSize();
            }
        }
    }

    private static void limitedQueuePush(int num) {
        if (queue.size() < maxSize) {
            queue.add(num);
        } else
            System.out.println("error");
    }

    private static void limitedQueuePop() {
        if (queue.size() > 0) {
            int number = queue.get(0);
            System.out.println(number);
            queue.remove(0);
        } else
            System.out.println("None");
    }

    private static void limitedQueuePeek() {
        if (queue.size() > 0)
            System.out.println(queue.get(0));
        else
            System.out.println("None");
    }

    private static void limitedQueueSize() {
        System.out.println(queue.size());
    }
}
