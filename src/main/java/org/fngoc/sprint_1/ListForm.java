package org.fngoc.sprint_1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * K. Списочная форма
 */

public class ListForm {

    public static void main(String[] args) throws IOException {
        //input
        int X;
        int K;
        int formNumber;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            X = Integer.parseInt(reader.readLine().strip());

            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            StringBuilder form = new StringBuilder();
            for (int i = 0; i < X; i++)
                form.append(tokenizer.nextToken());
            formNumber = Integer.parseInt(form.toString());

            K = Integer.parseInt(reader.readLine().strip());
        }

        //program
        String result = String.valueOf(formNumber + K);

        //output
        for (int i = 0; i < result.length(); i++) {
            System.out.print(result.charAt(i) + " ");
        }
    }
}
