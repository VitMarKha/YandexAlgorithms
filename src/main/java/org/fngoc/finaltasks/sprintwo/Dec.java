package org.fngoc.finaltasks.sprintwo;
/*
-- ПРИНЦИП РАБОТЫ --
Я реализовал Dec в основе которого лежит кольцевой буфер.
Кольцевой буфер реализован на массиве.

Есть голова и хвост буфера, которые перемещаются по массиву,
в зависимости от команды, происходит добавление/удаление элемента
в начало по индексу головы, или добавление/удаление в конец по индексу хвоста.

-- ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ --
Я инициализирую голову, хвост, и текущий size.
После прочтения maxSize я так же инициализирую буфер,
в котором по умолчанию все элементы null.

В каждой из команд есть проверка на size. При добавлении
проверяется не превышен ли maxSize, при удалении элемента
проверяется не равен ли текущий размер 0.

При push_back я устанавливаю в хвост значение value,
после чего перехожу к следующему индексу, теперь он является хвостом.
Если индекс хвоста становится равным maxSize, это значит,
что он дошел до границы массива, и для того что бы закольцевать буфер,
нужно присвоить ему 0 индекс.

По аналогии делаю с push_front, только теперь при соприкосновении с
границей начала массива, голова становится равной maxSize - 1,
то есть концом массива.

При pop_front и pop_back происходит возврат головы/хвоста на предыдущее значение.
Печать и зануление элемента.

-- ВРЕМЕННАЯ СЛОЖНОСТЬ --
Если у нас n команд, то выполнение работы программы будет O(n).

Добавление 1 элемента в Dec стоит всегда O(1), потому что при добавлении
мы устанавливаем элемент по индексу в массив.

Извлечение 1 элемента из Dec стоит всегда O(1), потому, что
происходит это по индексу массива.

-- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
Dec, содержащий maxSize элементов, занимает O(maxSize) памяти,
так как блок памяти для массива выделяется заранее,
как только мы узнаем maxSize.
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.OptionalInt;
import java.util.StringTokenizer;

public class Dec {

    private static int N;
    private static int maxSize;
    private static int currentSize = 0;
    private static int head = 0;
    private static int tail = 1;

    private static Integer[] buffer;

    public static void main(String[] args) throws IOException {
        StringTokenizer tokenizer;
        String command;

        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            N = Integer.parseInt(reader.readLine());
            maxSize = Integer.parseInt(reader.readLine());
            buffer = new Integer[maxSize];

            for (int i = 0; i < N; i++) {
                tokenizer = new StringTokenizer(reader.readLine());
                command = tokenizer.nextToken();

                if (command.equals("push_back"))
                    printCheckError(push_back(Integer.parseInt(tokenizer.nextToken())));
                else if (command.equals("push_front"))
                    printCheckError(push_front(Integer.parseInt(tokenizer.nextToken())));
                else if (command.equals("pop_front"))
                    printValueCheckError(pop_front());
                else
                    printValueCheckError(pop_back());
            }
        }
    }

    private static void printValueCheckError(OptionalInt value) {
        System.out.println(value.isPresent() ? value.getAsInt() : "error");
    }

    private static void printCheckError(boolean isGood) {
        if (!isGood)
            System.out.println("error");
    }

    private static boolean push_back(int value) {
        if (currentSize >= maxSize)
            return false;

        buffer[tail] = value;
        tail = tail + 1 == maxSize ? 0 : tail + 1;
        currentSize += 1;
        return true;
    }

    private static boolean push_front(int value) {
        if (currentSize >= maxSize)
            return false;

        buffer[head] = value;
        head = head == 0 ? maxSize - 1 : head - 1;
        currentSize += 1;
        return true;
    }

    private static OptionalInt pop_front() {
        if (currentSize <= 0)
            return OptionalInt.empty();

        head = head + 1 >= maxSize ? 0 : head + 1;
        int value = buffer[head];
        buffer[head] = null;
        currentSize -= 1;
        return OptionalInt.of(value);
    }

    private static OptionalInt pop_back() {
        if (currentSize <= 0)
            return OptionalInt.empty();

        tail = tail - 1 < 0 ? maxSize - 1 : tail - 1;
        int value = buffer[tail];
        buffer[tail] = null;
        currentSize -= 1;
        return OptionalInt.of(value);
    }
}
