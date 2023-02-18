package org.fngoc.sprint_3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/*
 * E. Покупка домов
 */

public class BuyingHouses {

    public static void main(String[] args) throws IOException {
        //input
        final int N;
        final int K;
        final List<Integer> houses = new ArrayList<>();
        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            N = Integer.parseInt(tokenizer.nextToken());
            K = Integer.parseInt(tokenizer.nextToken());

            tokenizer = new StringTokenizer(reader.readLine());
            for (int i = 0; i < N; i++) {
                houses.add(Integer.parseInt(tokenizer.nextToken()));
            }
        }

        //program
        Collections.sort(houses);
        int countHouse = 0;
        int sum = 0;
        for (Integer housePrice : houses) {
            sum += housePrice;
            if (sum <= K)
                countHouse += 1;
            else
                break;
        }

        //output
        System.out.print(countHouse);
    }
}
