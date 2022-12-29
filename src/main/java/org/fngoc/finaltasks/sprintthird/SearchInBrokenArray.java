package org.fngoc.finaltasks.sprintthird;

/*
-- ПРИНЦИП РАБОТЫ --
Реализовал программу на бинарных поисках.

Изначально проверяю условие, что массив может
прийти без разрыва. Проверяю, что первое число меньше последнего.
Если это истина, то можно воспользоваться
одним бинарным поиском и вывести результат.

Если понимаю, что разрыв есть, то с помощью первого бинарного
поиска ищу разрыв в массиве (где он не отсортирован),
после чего узнаю в каком из промежутков находится искомый элемент,
затем с помощью второго бинарного поиска нахожу его индекс.

В случае отсутствия элемента, возвращаю -1.

-- ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ --
Так как массив, по условию задачи, раньше был отсортирован,
значит нахождение разрыва возможно по условию:
(предыдущее_число < текущее_число > следующие_число).

Бинарный поиск элемента ограничивается индексами разрыва.
Поиск происходит в одном из промежутков и выводит его индекс.

-- ВРЕМЕННАЯ СЛОЖНОСТЬ --
Если у нас O(n) отсортированных элементов, без "разрыва", то найти
искомый элемент мы можем за O(log n). Это лучший случай.

Если у нас O(n) отсортированных элементов с "разрывом",
с помощью бинарного поиска мы можем найти разрыв за O(log n).

Далее, за O(1) узнаем в каком из промежутков находится искомый элемент,
после чего все тем же бинарным поиском находим искомый элемент
в одном из "разрывах", получаем О(2 * log n).

Так как константы убираются, средний и худший случай будет O(log n).

-- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
Программа, держит в памяти n элементов, пространственная сложность O(n).
*/

public class SearchInBrokenArray {

    public static int brokenSearch(int[] arr, int k) {
        int gapIndex;

        if (arr[0] < arr[arr.length - 1])
            return binSearch(arr, k, 0, arr.length);

        gapIndex = binSearchGapIndex(arr, 0, arr.length);

        if (arr[gapIndex] <= k && k <= arr[arr.length - 1])
            return binSearch(arr, k, gapIndex, arr.length);
        return binSearch(arr, k, 0, gapIndex);
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
            } else {
                if (mid + 1 != arr.length)
                    return mid + 1;
                return mid;
            }
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
