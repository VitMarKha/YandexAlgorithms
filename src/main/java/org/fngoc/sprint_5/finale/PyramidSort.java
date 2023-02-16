package org.fngoc.sprint_5.finale;

/*
-- ПРИНЦИП РАБОТЫ --
Реализовал пирамидальную сортировку на базе приоритетной очереди.

Добавляю элементы после считывания в кучу (массив),
затем делаю просеивание вверх.

Во время печати, удаляю самый приоритетный элемент
(добавляя в строку вывода), затем делаю просеивание вниз.

-- ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ --
Размер кучи задаю: кол-во данных + 1. Так как куча
начинает с 1 индекса, с нулевым элементом не взаимодействую.

После считывания элемента начинаю добавлять его
на следующее свободное место в куче. После чего
запускаю функцию siftUp на этот элемент.

Во время печати, в цикле использую функцию popMax
для получения самого приоритетного стажера. В этой функции
меняются местами первый элемент с последним,
после чего используются функция siftDown на первый элемент.

Для сравнения элементов, добавил в класс стажеров функцию compareTo.

-- ВРЕМЕННАЯ СЛОЖНОСТЬ --
N - кол-во элементов в куче.

Добавление элементов происходит за O(logN), так как
элементы добавляются по индексу в первую свободную ячейку это O(1),
но потом мы делаем просеивание вверх, ко-во элементов с каждым сравнением
уменьшается, скорость O(logN).

Удаление происходит за O(logN), так же за O(1) удалится элемент по индексу,
после чего происходит просеивание вниз за O(logN).

Временная сложность выполнения программы
при n кол-ва стажеров будет O(n * logN).

-- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
N - кол-во стажеров подаваемых на вход программе.

Создается массив с N + 1 размером для всех элементов,
постоянная пространственная сложность O(N+1).
*/

import java.io.*;
import java.util.*;

public class PyramidSort {

    private static class Intern implements Comparable<Intern> {

        private final String login;
        private final Integer solved;
        private final Integer fine;

        public Intern(String login, Integer solved, Integer fine) {
            this.login = login;
            this.solved = solved;
            this.fine = fine;
        }

        public String getLogin() {
            return login;
        }

        public Integer getSolved() {
            return solved;
        }

        public Integer getFine() {
            return fine;
        }

        @Override
        public int compareTo(Intern o) {
            if (!this.solved.equals(o.getSolved()))
                return o.getSolved() - this.solved;
            if (!this.fine.equals(o.getFine()))
                return this.fine - o.getFine();
            return this.login.compareTo(o.getLogin());
        }
    }

    private static Intern[] heap;
    private static int sizeHeap = 0;

    public static void main(String[] args) throws IOException {
        final int N;

        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            N = Integer.parseInt(reader.readLine()) + 1;
            StringTokenizer tokenizer;
            heap = new Intern[N];

            for (int i = 1; i < N; i++) {
                tokenizer = new StringTokenizer(reader.readLine());
                insert(new Intern(
                        tokenizer.nextToken(),
                        Integer.parseInt(tokenizer.nextToken()),
                        Integer.parseInt(tokenizer.nextToken())));
            }
        }
        output();
    }

    private static boolean isFirstBigger(Intern one, Intern two) {
        return one.compareTo(two) < 0;
    }

    public static void insert(Intern intern) {
        sizeHeap += 1;
        heap[sizeHeap] = intern;
        siftUp(heap, sizeHeap);
    }

    public static void siftUp(Intern[] heap, int idx) {
        int root = idx / 2;

        while (idx != 1) {
            if (isFirstBigger(heap[root], heap[idx]))
                return;

            if (isFirstBigger(heap[idx], heap[root])) {
                Intern tmp = heap[idx];
                heap[idx] = heap[root];
                heap[root] = tmp;
            }
            idx = root;
            root = idx / 2;
        }
    }

    public static void siftDown(Intern[] heap, int idx) {
        int left = idx * 2;
        int right = idx * 2 + 1;
        int large = left;

        while (left <= sizeHeap) {
            if (right <= sizeHeap && isFirstBigger(heap[right], heap[large]))
                large = right;

            if (isFirstBigger(heap[large], heap[idx])) {
                Intern tmp = heap[idx];
                heap[idx] = heap[large];
                heap[large] = tmp;
                idx = large;
                left = idx * 2;
                right = idx * 2 + 1;
                large = left;
                continue;
            }
            break;
        }
    }

    private static Intern popMax(Intern[] heap) {
        Intern result = heap[1];
        heap[1] = heap[sizeHeap];
        heap[sizeHeap] = null;
        sizeHeap -= 1;
        siftDown(heap, 1);
        return result;
    }

    private static void output() throws IOException {
        final StringBuilder output = new StringBuilder();
        final BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        for (int i = 1; i < heap.length; i++) {
            output.append(popMax(heap).getLogin()).append("\n");
        }
        writer.write(output.toString());
        writer.flush();
    }
}
