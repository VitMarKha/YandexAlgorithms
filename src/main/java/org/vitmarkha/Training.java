package org.vitmarkha;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Training {

    ///////////////////Все варианты 0 1 по кол-ву n///////////////////
    public static void printTree(int n, String line) {
        if (n == 0)
            System.out.println(line);
        else {
            printTree(n - 1, line + "0");
            printTree(n - 1, line + "1");
        }
    }

    ///////////////////Бинарный поиск рекурсивно///////////////////
    public static int recursiveBinSearch(int[] array, int number, int min, int max) {
        if (max <= min)
            return -1;

        int middle = (min + max) / 2;

        if (array[middle] == number)
            return middle;
        else if (number < array[middle])
            return recursiveBinSearch(array, number, min, middle);
        else
            return recursiveBinSearch(array, number, middle + 1, max);
    }

    ///////////////////Бинарный поиск///////////////////
    public static int binSearch(int[] array, int num) {
        int middle;
        int min = 0;
        int max = array.length;

        while (min < max) {
            middle = (min + max) / 2;

            if (array[middle] == num)
                return middle;
            else if (num < array[middle])
                max = middle;
            else
                min = middle + 1;
        }
        return -1;
    }

    ///////////////////Простое число быстро///////////////////
    public static boolean isSimpleFast(int number) {
        if (number == 1)
            return true;
        int i = 2;

        while (i * i <= number) {
            if (number % i == 0)
                return false;
            i += 1;
        }
        return true;
    }

    ///////////////////Простое число медленно///////////////////
    public static boolean isSimpleSlow(int number) {
        if (number == 1)
            return true;
        int i = 2;

        while (i < number) {
            if (number % i == 0)
                return false;
            i += 1;
        }
        return true;
    }

    ///////////////////Пример быстрого чтения из input///////////////////
    public static void readerExample(String[] args) throws IOException {
        StringBuilder output_buffer = new StringBuilder();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int num_lines = Integer.parseInt(reader.readLine());
        for (int i = 0; i < num_lines; ++i) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            int value_1 = Integer.parseInt(tokenizer.nextToken());
            int value_2 = Integer.parseInt(tokenizer.nextToken());
            int result = value_1 + value_2;
            output_buffer.append(result).append("\n");
        }
        System.out.println(output_buffer.toString());
    }

    ///////////////////Подсчет геномов быстро///////////////////
    public static void countGenomeFast(char[] sequence, int left, int right) {
        String[] arrayMapString = new String[sequence.length];
        Map<Character, Integer> map = new HashMap<>();
        map.put('C', 0);
        map.put('A', 0);
        map.put('T', 0);
        map.put('G', 0);

        //Если кэшировать результат этого цикла, то программа отрабатывает быстрее, но с большим потреблением памяти
        for (int i = 0; i < sequence.length; i++) {
            map.put(sequence[i],map.get(sequence[i]) + 1);
            arrayMapString[i] = map.toString();
        }

        for (int i = left; i <= right; i++) {
            System.out.println(arrayMapString[i]);
        }
    }

    ///////////////////Подсчет геномов///////////////////
    public static void countGenomeSlow(char[] sequence, int left, int right, char genome) {
        int count = 0;

        for (int i = left; i <= right; i++) {
            if (sequence[i] == genome)
                count++;
        }
        System.out.println(count);
    }

    ///////////////////Лучший посетитель///////////////////
    public static int bestVisitor(int[] visitors, int countVisitor) {
        int bestVisitor = 0;
        int[] visitorsCountArray = new int[countVisitor];

        for (int i = 0; i < visitors.length; i++) {
            ++visitorsCountArray[visitors[i]];
            if (visitorsCountArray[bestVisitor] < visitorsCountArray[visitors[i]])
                bestVisitor = visitors[i];
        }
        System.out.println(Arrays.toString(visitorsCountArray));
        return bestVisitor;
    }

    ///////////////////Поиск числа в массиве///////////////////
    public static int findNumberByArray(int[] array, int searchNum) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == searchNum)
                return i;
        }
        return -1;
    }

    ///////////////////Скользящая средняя///////////////////
    //оптимизированный алгоритм с пополняющейся Map в процесе выполнения программы (самый быстрый вариант)
    public static int[] twoSumFastWithGenerateMap(int[] array, int X) {
        Map<Integer, Integer> previous = new HashMap<>();

        for (int i = 0; i < array.length; i++) {
            int key = X - array[i];

            if (previous.containsKey(key))
                return new int[]{array[i], array[previous.get(key)]};
            else
                previous.put(array[i], i);
        }
        return new int[]{0, 0};
    }

    //оптимизированный алгоритм с заранее созданной Map
    public static int[] twoSumFastWithCreateMap(int[] array, int X) {
        Map<Integer, Integer> hashMap = new HashMap<>();

        for (int i = 0; i < array.length; i++) {
            hashMap.put(array[i], i);
        }

        for (int i = 0; i < array.length; i++) {
            int key = X - array[i];

            if (hashMap.containsKey(key) && array[i] != array[hashMap.get(key)]) {
                return new int[]{array[i], array[hashMap.get(key)]};
            }
        }
        return new int[]{0, 0};
    }

    //оптимизированный алгоритм с сортировкой
    public static int[] twoSumFastWithSort(int[] array, int X) {
        Arrays.sort(array);

        int left = 0;
        int right = array.length - 1;

        while (left != right) {
            int sum = array[left] + array[right];

            if (sum == X)
                return new int[]{array[left], array[right]};
            else if (sum > X)
                --right;
            else if (sum < X)
                ++left;
        }
        return new int[]{0, 0};
    }

    //ленивое алгоритм
    public static int[] twoSumSlow(int[] array, int X) throws Exception {
        int [] result = new int[2];

        for (int i = 0; i < array.length; i++) {
            int value = array[i];

            for (int j = i + 1; j < array.length; j++) {
                if (value + array[j] == X) {
                    result[0] = value;
                    result[1] = array[j];
                    return result;
                }
            }
        }
        throw new Exception("В массиве нет подходящих значений, сумма которых дает " + X);
    }

    ///////////////////Скользящая средняя///////////////////
    //ленивое алгоритм
    public static float[] movingAverageSlow(int[] times, int K) {
        float[] movingAverageArr = new float[times.length - (K - 1)];

        for (int i = 0; i < movingAverageArr.length; i++) {
            int begin = i;
            int end = i + (K - 1);
            float sum = 0;

            for (int j = begin; j <= end; j++) {
                sum += times[j];
            }
            movingAverageArr[i] = sum / K;
        }
        return movingAverageArr;
    }

    //оптимизированный алгоритм
    public static float[] movingAverageFast(int[] times, int K) {
        float[] movingAverageArr = new float[times.length - (K - 1)];
        float currentSum = 0;

        for (int i = 0; i < K; i++) {
            currentSum += times[i];
        }
        movingAverageArr[0] = currentSum / K;

        for (int i = 0; i < times.length - K; i++) {
            currentSum -= times[i];
            currentSum += times[K + i];
            movingAverageArr[i + 1] = currentSum / K;
        }
        return movingAverageArr;
    }
}
