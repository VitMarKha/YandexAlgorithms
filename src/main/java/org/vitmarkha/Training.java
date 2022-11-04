package org.vitmarkha;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Training {

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
