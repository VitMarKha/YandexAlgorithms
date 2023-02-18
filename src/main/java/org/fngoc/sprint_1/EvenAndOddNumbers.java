package org.fngoc.sprint_1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * B. Чётные и нечётные числа
 */

public class EvenAndOddNumbers {

    public static void main(String[] args) throws IOException {
        //input
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        int a = Integer.parseInt(tokenizer.nextToken());
        int b = Integer.parseInt(tokenizer.nextToken());
        int c = Integer.parseInt(tokenizer.nextToken());

        //program and output
        if (a % 2 == 0 && b % 2 == 0 && c % 2 == 0)
            System.out.println("WIN");
        else if (a % 2 != 0 && b % 2 != 0 && c % 2 != 0)
            System.out.println("WIN");
        else
            System.out.println("FAIL");
    }
}
