package org.vitmarkha.finaltasks.sprintthird;

/*
-- ПРИНЦИП РАБОТЫ --
Реализовал программу на бинарных поисках.

Изначально проверяю условие, что массив может
прийти без разрыва. Проверяю, что первое число меньше последнего.
Если это истина, то можно воспользоваться
одним бинарным поиском и вывести результат.

Если понимаю, что разрыв есть, то с помощью первого бинарного
поиска ищу разрыв в массиве (где он не отсортирован),
после чего ищу в промежутках массива искомый элемент,
ищу с помощью бинарных поисков его индекс.

В случае отсутствия элемента, возвращаю -1.

-- ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ --
Так как массив, по условию задачи, раньше был отсортирован,
значит нахождение разрыва возможно по условию, что
(предыдущее_число < текущее_число > следующие_число).

Бинарный поиск элемента ограничивается индексами разрыва.
Сначала бинарный поиск происходит в первом промежутке,
если не было найдено число, возвращается -1, поиск
происходит во втором промежутке.

-- ВРЕМЕННАЯ СЛОЖНОСТЬ --
Если у нас O(n) отсортированных элементов, то найти
искомый элемент мы можем за O(log n).

Если у нас O(n) отсортированных элементов с "разрывом",
с помощью бинарного поиска мы можем найти разрыв за O(log n).

Далее, если искомый элемент находится в первом разрыве,
то это лучший случай, и проходясь по нему еще раз бинарным поиском
мы получаем О(2 * log n).

Если элемента находится во втором разрыве,
то мы получаем O(3 * log n), это худший случай.

Так как константы убираются, средний случай будет O(log n).

-- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
Программа, держит в памяти n элементов, пространственная сложность O(n).

При передаче примитивных типов в функции происходит их копирование,
из-за чего пространственная сложность может быть O(n * 3),
так как мы передаем массив, в худшем случае, в 3 функции.
Что бы избежать этого можно использовать ArrayList,
тогда мы будем передавать копию ссылки на память,
но мне показалось на примитивных типах решение
выглядит более наглядно.
*/

public class SearchInBrokenArray {

    public static void main(String[] args) {
        System.out.println(brokenSearch(new int[]{19, 21, 100, 101, 102, 103, 104, 1, 4, 5, 7, 12}, 100));
        System.out.println(brokenSearch(new int[]{19, 21, 22, 44, 100, 101, 1, 4, 5, 7, 12}, 5));
        System.out.println(brokenSearch(new int[]{19, 21, 100, 101, 1, 4, 5}, 5));
        System.out.println(brokenSearch(new int[]{19, 21, 1, 4, 5, 7, 12, 13}, 4));
        System.out.println(brokenSearch(new int[]{19, 21, 1, 4, 5, 7, 12, 13}, 19));
        System.out.println(brokenSearch(new int[]{19, 21, 33, 44, 55, 74, 122, 133}, 33));
        System.out.println(brokenSearch(new int[]{5, 1}, 1));
        System.out.println(brokenSearch(new int[]{5, 1}, 1));
        System.out.println(brokenSearch(new int[]{1}, 1));
        System.out.println(brokenSearch(new int[]{1}, 4));
    }

    public static int brokenSearch(int[] arr, int k) {
        int gapIndex;

        if (arr[0] < arr[arr.length - 1])
            return binSearch(arr, k, 0, arr.length);

        gapIndex = binSearchGapIndex(arr, 0, arr.length);
        int result = binSearch(arr, k, 0, gapIndex);
        if (result == -1)
            return binSearch(arr, k, gapIndex, arr.length);
        return result;
    }

    private static int binSearchGapIndex(int[] arr, int min, int max) {
        int mid;

        while (min <= max) {
            mid = (min + max) / 2;

            if (mid + 1 < arr.length && mid - 1 >= 0) {
                if (arr[mid - 1] < arr[mid] && arr[mid] > arr[mid + 1])
                    return mid + 1;
                else if (arr[mid] > arr[arr.length - 1])
                    min = mid + 1;
                else
                    max = mid;
            } else
                return mid;
        }
        return -1;
    }

    private static int binSearch(int[] arr, int k, int indexStart, int indexEnd) {
        int mid;
        int min = indexStart;
        int max = indexEnd;

        while (min < max) {
            mid = (min + max) / 2;

            if (arr[mid] == k)
                return mid;
            else if (k < arr[mid])
                max = mid;
            else
                min = mid + 1;
        }
        return -1;
    }
}
