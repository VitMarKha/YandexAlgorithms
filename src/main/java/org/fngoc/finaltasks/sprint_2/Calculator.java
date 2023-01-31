package org.fngoc.finaltasks.sprint_2;
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
Если у нас n подаваемых данных, то выполнение работы программы будет O(n).

Добавление и извлечение из стека происходит за O(1).
Вычисление операции так же будет происходить за O(1).

Замедлить работу программы может парсинг, и преобразование String в Integer.

-- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
Где n все подаваемые символы.

Если знаки операций идут после 2 чисел (необходимых для вычисления),
то после вычисления в стеке будет не 2, а 1 число, следовательно,
из всех подаваемых символов в стеке может быть максимум O(n * 1/3 + n%2).
Это будет средний и лучший случай.

В худшем случае, если нам подадут сначала числа, а потом
знаки операций, стек будет хранить все поданные числа, то есть O(n/2 + n%2) ~ O(n).
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
        return Math.floorDiv(a, b);
    }
}
