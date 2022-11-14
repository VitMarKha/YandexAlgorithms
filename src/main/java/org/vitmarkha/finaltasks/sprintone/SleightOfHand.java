package org.vitmarkha.finaltasks.sprintone;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SleightOfHand {

    private static final Integer LEN_Y = 4; //длинна поля
    private static final Integer LEN_X = 4; //ширина поля

    public static void main(String[] args) throws IOException {
        //input
        final int k;
        final int[][] field;
        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            k = readIntValue(reader) * 2; //умножаю на два, так как игрока 2
            field = readCharField(reader, LEN_Y, LEN_X);
        }

        //program
        int result = 0;
        int[] countT = new int[10];

        for (int i = 0; i < LEN_Y; i++) { //подсчитываю кол-во каждого элемента на поле
            for (int j = 0; j < LEN_X; j++) {
                countT[field[i][j]] += 1;
            }
        }

        for (int i = 1; i < countT.length; i++) { //считаю кол-во возможных побед, кроме 0, так как '.' я заменял на 0
            if (countT[i] <= k && countT[i] != 0) {
                result += 1;
            }
        }

        //output
        System.out.print(result);
    }

    private static int readIntValue(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine());
    }

    private static int[][] readCharField(BufferedReader reader, int lenY, int lenX) throws IOException {
        int[][] result = new int[lenY][lenX];

        for (int i = 0; i < lenY; i++) {
            String line = reader.readLine();
            for (int j = 0; j < lenX; j++) {
                if (line.charAt(j) == '.')
                    result[i][j] = 0;
                else
                    result[i][j] = Integer.parseInt(String.valueOf(line.charAt(j)));
            }
        }
        return result;
    }
}
