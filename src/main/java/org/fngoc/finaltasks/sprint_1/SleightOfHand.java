package org.fngoc.finaltasks.sprint_1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SleightOfHand {

    private static final Integer LEN_Y = 4; //длинна поля
    private static final Integer LEN_X = 4; //ширина поля

    public static void main(String[] args) throws IOException {
        //input
        final int k;
        final int[] buttonLine;
        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            k = readIntValue(reader) * 2; //умножаю на два, так как игрока 2
            buttonLine = readCharField(reader, LEN_Y, LEN_X);
        }

        //program
        int result = 0;
        int[] countT = new int[10];

        for (int i = 0; i < buttonLine.length; i++) { //подсчитываю кол-во каждого элемента на поле
            countT[buttonLine[i]] += 1;
        }

        for (int i = 1; i < countT.length; i++) { //считаю кол-во возможных побед, кроме 0, так как '.' я заменял на 0
            if (countT[i] <= k && countT[i] != 0)
                result += 1;
        }

        //output
        System.out.print(result);
    }

    private static int readIntValue(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine());
    }

    private static int[] readCharField(BufferedReader reader, int lenY, int lenX) throws IOException {
        int[] result = new int[lenY * lenX];
        StringBuilder line = new StringBuilder();

        for (int i = 0; i < lenY; i++)
            line.append(reader.readLine());

        for (int i = 0; i <line.length(); i++)
            result[i] = line.charAt(i) == '.' ? 0 : line.charAt(i) - '0';
        return result;
    }
}
