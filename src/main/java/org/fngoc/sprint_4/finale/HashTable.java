package org.fngoc.sprint_4.finale;

/*
-- ПРИНЦИП РАБОТЫ --
Реализовал HashTable методом открытой адресации.

При считывании команд из input, происходит вызов
функций put, get, delete.

При выполнении команд, рассчитывается хеш по ключу,
для расчета коллизии происходит переход к следующему
индексу, по логике работы метода открытой адресации.

-- ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ --
Заранее задается 2 числа, размер массива SIMPLE_LEN
и коэффициент для расчета хеша SIMPLE_INT.
Оба числа простые, для того что бы максимально
распределить элементы по всему массиву.

В случае с get и delete, проверяется, на не пустую ячейку
и совпадающий ключ. Если проверку не проходит,
то в цикле ищем следующую ячейку прибавляя к хешу += 1.
При нахождении элемента возвращаем его или удаляем.
При отсутствии возвращаем -1, что сигнализирует
о добавлении "None" в строку вывода.

В put, же мы с начала проверяем на пустую ячейку,
только потом на одинаковый ключ. Если не нашли, ищем циклом хеш += 1
пока не найдем пустую ячейку или пока не найдем одинаковый ключ.

-- ВРЕМЕННАЯ СЛОЖНОСТЬ --
Все Bucket хранятся в массиве. Получение элемента
из массива и его установка в массив происходит за O(1).

В лучшем и среднем случа при хорошей распределенности
по массиву за счет хеш функции, поиск/удаление/вставка
будет выполняться за O(1).

Временная сложность выполнения программы
при n кол-ва команд будет O(n).

-- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
В данном задание от нас не требовалось реализовывать
расширение capacity, по этому на протяжении всей программы
будет выделено константное ко-во памяти SIMPLE_LEN,
постоянная пространственная сложность O(SIMPLE_LEN) ~ O(1).
*/

import java.io.*;
import java.util.*;

public class HashTable {

    final static class Bucket {
        private final Integer key;
        private Integer value;

        public Bucket(Integer key, Integer value) {
            this.key = key;
            this.value = value;
        }
    }

    private static final int SIMPLE_LEN = 10_000_019;
    private static final int SIMPLE_INT = 9973;

    private static final Bucket[] table = new Bucket[SIMPLE_LEN];

    private static final StringBuilder output = new StringBuilder();

    public static void main(String[] args) throws IOException {
        final int N;

        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            N = Integer.parseInt(reader.readLine());
            StringTokenizer tokenizer;

            for (int i = 0; i < N; i++) {
                tokenizer = new StringTokenizer(reader.readLine());
                String command = tokenizer.nextToken();
                if (command.equals("put")) {
                    int key = Integer.parseInt(tokenizer.nextToken());
                    int value = Integer.parseInt(tokenizer.nextToken());
                    put(key, value);
                } else if (command.equals("get"))
                    addAnswerInLine(get(Integer.parseInt(tokenizer.nextToken())));
                else
                    addAnswerInLine(delete(Integer.parseInt(tokenizer.nextToken())));
            }
        }
        output();
    }

    private static void put(int key, int value) {
        int index = hash(key);

        if (isEmptyBucket(index))
            table[index] = new Bucket(key, value);
        else if (isCellFound(index, key))
            table[index].value = value;
        else {
            index = searchNextBucket(index, key);

            if (isEmptyBucket(index))
                table[index] = new Bucket(key, value);
            else if (isCellFound(index, key))
                table[index].value = value;
        }
    }

    private static int get(int key) {
        int index = hash(key);

        if (isCellFound(index, key))
            return table[index].value;

        index = searchNextBucket(index, key);

        if (isCellFound(index, key))
            return table[index].value;
        return -1;
    }

    private static int delete(int key) {
        int index = hash(key);

        if (isCellFound(index, key)) {
            int resultValue = table[index].value;
            table[index] = null;
            return resultValue;
        }

        index = searchNextBucket(index, key);

        if (isCellFound(index, key)) {
            int resultValue = table[index].value;
            table[index] = null;
            return resultValue;
        }
        return -1;
    }

    private static int searchNextBucket(int index, int key) {
        while (!isEmptyBucket(index) && !table[index].key.equals(key))
            index += 1;
        return index;
    }

    private static boolean isCellFound(int index, int key) {
        return !isEmptyBucket(index) && table[index].key.equals(key);
    }

    private static boolean isEmptyBucket(int index) {
        return table[index] == null;
    }

    private static int hash(Integer key) {
        return Math.abs((key * SIMPLE_INT) % SIMPLE_LEN);
    }

    private static void output() throws IOException {
        final BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        writer.write(output.toString());
        writer.flush();
    }

    private static void addAnswerInLine(int integer) {
        if (integer == -1)
            output.append("None");
        else
            output.append(integer);
        output.append('\n');
    }
}
