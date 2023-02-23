package org.fngoc.sprint_7.finale;

/*
-- ПРИНЦИП РАБОТЫ --
Решаю задачу через динамическое программирование,
с использованием одного дополнительного массива.

-- ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ --
Считываю строку, сохраняя в массив. Если сумма всех
элементов не четная, возвращаю false.

Создаю массив boolean, в котором на последнем элементе
будет храниться возможность половины суммы всех элементов быть равной
второй половине.

Базовый случай true в нулевом элементе. Прохожусь по всем элементам.
Если текущий элемент равен половине суммы, или если он больше, то ответ найден.

Во вложенном цикле за счет динамического программирования
заполняю массив значениями true.

-- ВРЕМЕННАЯ СЛОЖНОСТЬ --
В среднем нам потребуется пройтись по всем элементам прочитанного массива
и по сумме всех элементов деленной на 2 для заполнения массива
динамического программирования.

Среднее время O(N * (sum(N)/2)).
Где N - кол-во элементов.

-- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
Будем постоянно держать в памяти все прочитанные элементы
и элементы sum(N)/2.

Пространственная сложность O(N + (sum(N)/2)).
Где N - кол-во элементов.
*/

import java.io.*;
import java.util.*;

public class SameAmounts {

    private static int[] points;

    public static void main(String[] args) throws IOException {
        input();
        output(isSameAmounts());
    }

    private static boolean isSameAmounts() {
        int sum = Arrays.stream(points).sum();

        if (sum % 2 != 0)
            return false;

        int halfSum = sum / 2;
        boolean[] dp = new boolean[halfSum + 1];
        dp[0] = true;

        for (int point : points) {
            if (point == halfSum)
                return true;
            if (point > halfSum)
                return false;

            for (int j = halfSum; j > point - 1; j--)
                dp[j] = dp[j - point] || dp[j];
        }
        return dp[dp.length - 1];
    }

    private static void output(boolean isTrue) {
        if (isTrue)
            System.out.println("True");
        else
            System.out.println("False");
    }

    private static void input() throws IOException {
        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            int n = Integer.parseInt(tokenizer.nextToken());

            points = new int[n];

            tokenizer = new StringTokenizer(reader.readLine());
            for (int i = 0; i < n; i++) {
                points[i] = Integer.parseInt(tokenizer.nextToken());
            }
        }
    }
}
