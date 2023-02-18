package org.fngoc.sprint_1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * D. Хаотичность погоды
 */

public class RandomWeather {

    public static void main(String[] args) throws IOException {
        //input
        int n;
        int[] days;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            n = Integer.parseInt(reader.readLine().strip());
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            days = new int[n];
            for (int i = 0; i < n; i++) {
                days[i] = Integer.parseInt(tokenizer.nextToken());
            }
        }

        //program
        if (n == 1) {
            System.out.println(1);
            return;
        }

        int randomDays = 0;

        if (days[0] > days[1])
            randomDays += 1;
        if (days[days.length - 1] > days[days.length - 2])
            randomDays += 1;

        for (int i = 1; i < days.length - 1; i++) {
            if (days[i] > days[i + 1] && days[i] > days[i - 1])
                randomDays += 1;
        }

        //output
        System.out.print(randomDays);
    }
}
