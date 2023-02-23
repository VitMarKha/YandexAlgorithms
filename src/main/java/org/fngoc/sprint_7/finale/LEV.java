package org.fngoc.sprint_7.finale;

/*
-- ПРИНЦИП РАБОТЫ --
Реализовал расчет расстояния по Левенштейну.

Решаю через алгоритм Вагнера — Фишера - использование динамического
программирования со сравнением элементов строк.

-- ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ --
Считываю две строки, преобразую их в массивы символов.

После чего создаю два массива равной длинны,
в которых будет храниться благодаря динамическому
программированию две последних строки потенциальной матрицы.

По алгоритму Вагнера — Фишера сравниваю каждый элемент
первого слова с каждым элементом второго слова.
Что бы заполнить потенциальную матрицу, нужно брать минимальное
значение из 3 элементов:
1) Слева от элемента
2) Справа от элемента
3) Слева-сверху от элемента (если символы разные, нужно прибавить 1)

Последний элемент - является расстоянием между двумя строками.

-- ВРЕМЕННАЯ СЛОЖНОСТЬ --
N - длинна первой строки
M - длинна второй строки

Для расчета последнего элемента
нам нужно рассчитать все остальные элементы,
следовательно, нам нужно пройтись
по всем элементам, каждой строки,
и сравнить их, это O(N * M).

Так же происходил перезапись строк O(N) и O(M),
но это можно не учитывать.

-- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
Постоянная пространственная сложность для двух массивах O(N + M).

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
        int[] dpFirst = new int[secondStr.length + 1];
        int[] dpSecond = new int[secondStr.length + 1];

        for (int i = 0; i <= secondStr.length; i++)
            dpSecond[i] = i;

        for (int i = 1; i <= firstStr.length; i++) {
            for (int j = 0; j < dpSecond.length; j++)
                dpFirst[j] = dpSecond[j];

            dpSecond[0] = i;
            for (int j = 1; j <= secondStr.length; j++) {
                int isDifferent = firstStr[i - 1] != secondStr[j - 1] ? 1 : 0;
                dpSecond[j] = Math.min(Math.min(dpFirst[j] + 1, dpSecond[j - 1] + 1), dpFirst[j - 1] + isDifferent);
            }
        }
        return dpSecond[dpSecond.length - 1];
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
