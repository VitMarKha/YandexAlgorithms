package org.fngoc.sprint_5;

/*
 * L. Просеивание вниз
 */

public class SiftDown {

    public static int siftDown(int[] heap, int idx) {
        int left = idx * 2;
        int right = idx * 2 + 1;
        int large = left;

        if (left >= heap.length)
            return idx;

        if (right < heap.length && heap[left] < heap[right])
            large = right;

        if (heap[idx] < heap[large]) {
            int tmp = heap[idx];
            heap[idx] = heap[large];
            heap[large] = tmp;
            return siftDown(heap, large);
        }
        return idx;
    }
}
