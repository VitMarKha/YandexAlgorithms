package org.fngoc.sprint_7.finale;

/*
-- ПРИНЦИП РАБОТЫ --
Реализовал расчет расстояния по Левенштейну.

Решаю через алгоритм Вагнера — Фишера - использование динамического
программирования со сравнением элементов строк.

-- ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ --
Считываю две строки, преобразую их в массивы символов.

После чего создаю матрицу для расчета. Заполняю края
матрицы номерами символов строк.

По алгоритму Вагнера — Фишера сравниваю каждый элемент
первого слова с каждым элементом второго слова.
Что бы заполнить матрицу, нужно брать минимальное
значение из 3 элементов:
1) Слева от элемента
2) Справа от элемента
3) Слева-сверху от элемента (если символы разные, нужно прибавить 1)

Крайний элемент справа внизу матрицы - является расстоянием между двумя строками.

-- ВРЕМЕННАЯ СЛОЖНОСТЬ --
Для расчета последнего элемента матрицы
нам нужно рассчитать все остальные элементы,
следовательно, нам нужно пройтись
по всем элементам матрицы, это O(N * M).

Так же происходил заполнение крайней левой
и верхней стороны матрицы номерами элементов строк, O(N) и O(M),
но это можно не учитывать.

-- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
N - длинна первой строки
M - длинна второй строки

Постоянная пространственная сложность для матрицы O(N * M).

Так же храню массивы элементов строк O(N) и O(M),
но это можно не учитывать.
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class LEV {

    private static char[] firstStr;
    private static char[] secondStr;

    public static void main(String[] args) throws IOException {
        input();
        System.out.println(levenshteinDistanceCalculation());
    }

    private static int levenshteinDistanceCalculation() {
        int[][] dp = new int[firstStr.length + 1][secondStr.length + 1];
        fillingStrIndex(dp);

        for (int i = 1; i < firstStr.length; i++) {
            for (int j = 1; j < secondStr.length; j++) {
                int isDifferent = firstStr[i] != secondStr[j] ? 1 : 0;
                dp[i][j] = Math.min(Math.min(dp[i - 1][j] + 1, dp[i][j - 1] + 1), dp[i - 1][j - 1] + isDifferent);
            }
        }
        return dp[firstStr.length - 1][secondStr.length - 1];
    }

    private static void fillingStrIndex(int[][] dp) {
        for (int i = 1; i < firstStr.length; i++)
            dp[i][0] = i;

        for (int i = 1; i < secondStr.length; i++)
            dp[0][i] = i;
    }

    private static void input() throws IOException {
        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            char[] str = reader.readLine().toCharArray();
            firstStr = new char[str.length + 1];
            for (int i = 0; i < str.length; i++) {
                firstStr[i + 1] = str[i];
            }

            str = reader.readLine().toCharArray();
            secondStr = new char[str.length + 1];
            for (int i = 0; i < str.length; i++) {
                secondStr[i + 1] = str[i];
            }
        }
    }
}
