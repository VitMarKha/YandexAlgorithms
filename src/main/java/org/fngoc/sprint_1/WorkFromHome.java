package org.fngoc.sprint_1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * G. Работа из дома
 */

public class WorkFromHome {

    public static void main(String[] args) throws IOException {
        //input
        int decimal;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            decimal = Integer.parseInt(reader.readLine().strip());
        }

        //program
        if (decimal == 0) {
            System.out.print(0);
            return;
        }

        StringBuilder binary = new StringBuilder();

        while (decimal != 0) {
            if (decimal % 2 == 0)
                binary.append(0);
            else
                binary.append(1);
            decimal /= 2;
        }

        //output
        System.out.print(binary.reverse().toString());
    }
}
