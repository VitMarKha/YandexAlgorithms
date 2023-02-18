package org.fngoc.sprint_1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
 * J. Факторизация
 */

public class Factorization {

    public static void main(String[] args) throws IOException {
        //input
        int N;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            N = Integer.parseInt(reader.readLine().strip());
        }

        //program
        List<Integer> resultList = new ArrayList<>();
        int divider = 2;

        while (divider * divider <= N) {
            if (N % divider == 0) {
                resultList.add(divider);
                N /= divider;
                continue;
            }
            divider += 1;
        }

        if (N != 0 && N != 1 && isSimpleFast(N))
            resultList.add(N);

        //output
        Collections.sort(resultList);
        for (int i = 0; i < resultList.size(); i++) {
            System.out.print(resultList.get(i) + " ");
        }
    }

    private static boolean isSimpleFast(int number) {
        if (number == 1)
            return true;
        int i = 2;

        while (i * i <= number) {
            if (number % i == 0)
                return false;
            i += 1;
        }
        return true;
    }
}
