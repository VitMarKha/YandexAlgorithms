package org.fngoc.sprint_7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

/*
 * B. Расписание
 */

public class Schedule {

    public static void main(String[] args) throws Exception {
        schedule();
    }

    private static class Gap implements Comparable<Gap>{
        private Double start;
        private Double end;

        public Gap(Double start, Double end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Gap o) {
            if (this.end.equals(o.end))
                return (int) (this.start - o.start);
            return (int) (this.end - o.end);
        }

        @Override
        public String toString() {
            String resultStr;
            double a = start;
            long b = (long) a;

            if ((a - (double) b) > 0)
                resultStr = String.valueOf(start);
            else
                resultStr = String.valueOf(start.intValue());

            resultStr += " ";

            a = end;
            b = (long) a;

            if ((a - (double) b) > 0)
                resultStr += String.valueOf(end);
            else
                resultStr += end.intValue();

            return resultStr;
        }
    }

    private static Gap[] gaps;
    private static Stack<Gap> stack;

    private static void schedule() throws IOException {
        input();
        Arrays.sort(gaps);
        stack = new Stack<>();
        stack.push(gaps[0]);

        for (int i = 1; i < gaps.length; i++) {
            if (stack.peek().end <= gaps[i].start)
                stack.push(gaps[i]);
        }
        output();
    }

    private static void input() throws IOException {
        final int N;

        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            N = Integer.parseInt(reader.readLine());
            StringTokenizer tokenizer;

            gaps = new Gap[N];

            for (int i = 0; i < N; i++) {
                tokenizer = new StringTokenizer(reader.readLine());
                double gapStart = Double.parseDouble(tokenizer.nextToken());
                double gapEnd = Double.parseDouble(tokenizer.nextToken());

                gaps[i] = new Gap(gapStart, gapEnd);
            }
        }
    }

    private static void output() {
        System.out.println(stack.size());
        for (Gap gap : stack) {
            System.out.println(gap);
        }
    }
}
