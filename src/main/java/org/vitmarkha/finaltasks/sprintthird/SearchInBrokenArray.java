package org.vitmarkha.finaltasks.sprintthird;

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
