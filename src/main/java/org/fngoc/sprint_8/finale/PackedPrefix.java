package org.fngoc.sprint_8.finale;

/*
-- ПРИНЦИП РАБОТЫ --
Реализовал расчет общего префикса у запакованных строк.

Распаковываю и рассчитываю префикс строки во время чтения
входных данных, после окончания чтения вывожу результат.

-- ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ --
После прочтения строки, начинается рекурсивная распаковка ЗС.

Если это не цифра и не скобка, то мы добавляем элемент
в итоговую распакованную строку.

Если встречается цифра, то мы сначала находим
диапазон в котором находится открытая и закрытая скобка.
После чего вызываем рекурсию на новую строку.

На выходе из рекурсии происходит умножение на найденную цифру
и прибавление к итоговой распакованной строке.

После чего мы ищем одинаковый префикс у только что
распакованной строки с предыдущей распакованной строкой.

-- ВРЕМЕННАЯ СЛОЖНОСТЬ --
N - кол-во строк.
L - длина самой длинной распакованной строки.

Для распаковки строк нам нужно пройтись по всем строка O(N) и по всем их элементам
это O(L). Получается временная сложность O(N * L).

Так же происходил поиск префикса в каждой строке, нужно пройтись по всем строкам
и сравнить от начала их символы, это O(N * L).

Получается средняя временная сложность O(N * L).
Константы убираем.

-- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
В памяти мы постоянно держим строку префикса. Ее максимальная
длина может быть максимальной длиной одинаковых строк, то есть O(L).

Так же мы используем стек во время рекурсии, в среднем если цифры в
прочитанной строке распределены равномерно, память будет занимать O(logL).

Так же мы временно держим прочитанную строку за O(L), но это можно не учитывать.

Получается средняя пространственная сложность O(L * logL).
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class PackedPrefix {

    private static String prefix;

    public static void main(String[] args) throws IOException {
        input();
        output();
    }

    private static void output() {
        System.out.println(prefix);
    }

    private static void input() throws IOException {
        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int n = Integer.parseInt(reader.readLine());

            for (int i = 0; i < n; i++) {
                String line = unpacked(reader.readLine());
                prefix = line.substring(0, findNewPrefix(line));
            }
        }
    }

    private static int findNewPrefix(String line) {
        if (prefix == null)
            return line.length();

        int len = 0;
        for (int i = 0; i < line.length(); i++) {
            if (i < prefix.length() && prefix.charAt(i) == line.charAt(i))
                len += 1;
            else
                break;
        }
        return len;
    }

    private static String unpacked(String line) {
        StringBuilder str = new StringBuilder();

        for (int i = 0; i < line.length(); i++) {
            if (line.charAt(i) >= 49 && line.charAt(i) <= 57) {
                int endBracketIdx = getEndBracketIdx(line, i + 1);

                str.append(multiStr(Character.getNumericValue(line.charAt(i)),
                        unpacked(line.substring(i + 1, endBracketIdx  + 1))));
                i = endBracketIdx;
            } else if (line.charAt(i) != '[' && line.charAt(i) != ']')
                str.append(line.charAt(i));
        }
        return str.toString();
    }

    private static int getEndBracketIdx(String line, int startIdx) {
        Stack<Character> stack = new Stack<>();

        for (int i = startIdx; i < line.length(); i++) {
            if (line.charAt(i) == '[')
                stack.push(line.charAt(i));
            else if (line.charAt(i) == ']') {
                stack.pop();
                if (stack.isEmpty())
                    return i;
            }
        }
        return line.length();
    }

    private static String multiStr(int number, String str) {
        StringBuilder strBuilder = new StringBuilder();
        for (int i = 0; i < number; i++)
            strBuilder.append(str);
        return strBuilder.toString();
    }
}
