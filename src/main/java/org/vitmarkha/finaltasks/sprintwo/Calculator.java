package org.vitmarkha.finaltasks.sprintwo;
/*
-- ПРИНЦИП РАБОТЫ --
Я реализовал Calculator в основе которого лежит стек.

Считываю всю строку, после чего по разделенным пробелам,
прохожусь по элементам, есть 2 варианта:
    1. Я понимаю, что это число, добавляю в стек с числами.
    2. Понимаю что это знак операции, беру из стека с числами 2
        последних числа, произвожу операцию над ними,
        кладу результат в стек с числами.

В конце вывожу оставшийся элемент в стеке с числами.

-- ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ --
Так как данные подаются в формате обратной польской нотацией,
подразумевается, что с 2 последними цифрами будет происходить
операция по первому найденному символу операции.

Так как стек у нас LIFO, я могу при нахождении символа с операцией,
быстро достать 2 последних числа, и так же быстро вернуть результат в стек.

-- ВРЕМЕННАЯ СЛОЖНОСТЬ --
Добавление и извлечение из стека происходит за O(1).
Вычисление операции так же будет происходить за O(1).

Замедлить работу программы может парсинг, и преобразование String в Integer.

-- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
В худшем случае, если нам подадут сначала числа,
а потом знаки операций, стек будет хранить все поданные числа k то есть O(k).

Если знаки операций идут после 2 чисел (необходимых для вычисления),
то после вычисления в стеке будет не 2, а 1 число, следовательно,
из всех числе в стеке может быть максимум O(k/2).
Это будет средний и лучший случай.
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Calculator {

    private static final Stack<Integer> stackNumbers = new Stack<>();

    public static void main(String[] args) throws IOException {
        StringTokenizer tokenizer;
        String argument;
        char sign;

        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            tokenizer = new StringTokenizer(reader.readLine());

            while (tokenizer.hasMoreTokens()) {
                argument = tokenizer.nextToken();
                sign = argument.charAt(0);

                if (!Character.isDigit(sign) && argument.length() == 1) {
                    int a = stackNumbers.pop();
                    int b = stackNumbers.pop();
                    stackNumbers.push(calculate(b, a, sign));
                }
                else
                    stackNumbers.push(Integer.parseInt(argument));
            }
        }
        System.out.print(stackNumbers.pop());
    }

    private static int calculate(int a, int b, char sign) {
        if (sign == '*')
            return a * b;
        else if (sign == '+')
            return a + b;
        else if (sign == '-')
            return a - b;
        else
            return (int) Math.floor((double) a / b);
    }
}
