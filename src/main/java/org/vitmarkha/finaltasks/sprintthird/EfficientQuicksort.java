package org.vitmarkha.finaltasks.sprintthird;

/*
-- ПРИНЦИП РАБОТЫ --
Реализовал программу эффективной сортировки.

Сортировка работает рекурсивно. Изначально в
нее подается диапазон от 0 до array.size() - 1,
рандомно находится опорный элемент в диапазоне,
после чего следуя инструкции в описании задачи происходит
следующие: "будем двигать левый указатель вправо
до тех пор, пока он указывает на элемент, меньший опорного.
Аналогично двигаем правый указатель влево, пока он стоит на
элементе, превосходящем опорный". Нарушающие порядок элементы
меняются местами.

После чего запускаются рекурсии с новыми диапазонами.

-- ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ --
Для корректного сравнения, создал класс Intern
и имплементировали в нем метод compareTo().

Создание массива происходит один раз на этапе input,
и нигде не расширяется/дублируется,
что позволяет утверждать, что сортировка работает in-place.

Во время рекурсивных вызовов происходит проверка на
базовый случай (start > end), в новую рекурсию подается
каждый раз новый диапазон в индексах.

-- ВРЕМЕННАЯ СЛОЖНОСТЬ --
Скорость сортировки зависит от хорошо подобранного опорного элемента.

Количество шагов деления (глубина рекурсии) составляет O(log n),
но на каждом шаге рекурсии мы проделываем O(n) операций.
Следовательно, лучший и средний случай будет O(n * log n).

"Количество операций в процессе сортировки пропорционально количеству
элементов в получившемся дереве обработки элементов" - в худшем случае,
если опорный элемент будет всегда меньше всех отсталых элементов,
или если массив уже отсортирован, у нас получится O(n^2).

-- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
Программа, держит в памяти n элементов, постоянная пространственная
сложность O(n). Но для быстрой сортировки требуется O(log n) пространства стека,
чтобы отслеживать подмассивы в стратегии разделяй и властвуй.

В среднем и лучшем случае O(n + log n).

В худшем случае, если массив отсортирован или если неправильно будет
выбираться опорный элемент, пространственная сложность может быть
O(n) + O(n) пространства стека, то есть O(2n).
*/

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class EfficientQuicksort {

    private static final class Intern implements Comparable<Intern> {

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
        public String toString() {
            return login;
        }

        @Override
        public int compareTo(Intern o) {
            if (this.solved > o.getSolved())
                return o.getSolved() - this.solved;
            if (this.solved.equals(o.getSolved())) {
                if (this.fine < o.getFine())
                    return this.fine - o.getFine();
                else if (this.fine > o.getFine())
                    return this.fine - o.getFine();
                else
                    return this.login.compareTo(o.getLogin());
            }
            return 1;
        }
    }

    public static void main(String[] args) throws IOException {
        List<Intern> array = input();
        quickSortInPlace(array, 0, array.size() - 1);
        output(array);
    }

    private static void quickSortInPlace(List<Intern> array, int start, int end) {
        if (start > end)
            return;

        Intern pivot = getRandomPivot(array, start, end);
        int left = start;
        int right = end;

        while (left <= right) {
            while (compare(pivot, array.get(left)))
                left += 1;
            while (compare(array.get(right), pivot))
                right -= 1;
            if (left <= right) {
                swap(array, left, right);
                left += 1;
                right -= 1;
            }
        }
        quickSortInPlace(array, start, right);
        quickSortInPlace(array, left, end);
    }

    private static Intern getRandomPivot(List<Intern> array, int start, int end) {
        return array.get(start + (int) (Math.random() * (end - start)));
    }

    private static boolean compare(Intern first, Intern second) {
        return first.compareTo(second) < 0;
    }

    private static void swap(List<Intern> array, int left, int right) {
        Intern tmp = array.get(left);
        array.set(left, array.get(right));
        array.set(right, tmp);
    }

    private static List<Intern> input() throws IOException {
        final int N;
        final List<Intern> array = new ArrayList<>();
        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            N = Integer.parseInt(reader.readLine());
            StringTokenizer tokenizer;

            for (int i = 0; i < N; i++) {
                tokenizer = new StringTokenizer(reader.readLine());
                Intern intern = new Intern(tokenizer.nextToken(),
                        Integer.parseInt(tokenizer.nextToken()),
                        Integer.parseInt(tokenizer.nextToken()));
                array.add(intern);
            }
        }
        return array;
    }

    private static void output(List<Intern> array) throws IOException {
        final StringBuilder output = new StringBuilder();
        final BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        for (int i = array.size() - 1; i >= 0; i--)
            output.append(array.get(i)).append('\n');
        writer.write(output.toString());
        writer.flush();
    }
}
