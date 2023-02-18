package org.fngoc.sprint_5;

/*
 * M. Просеивание вверх
 */

public class SiftUp {

    public static int siftUp(int[] heap, int idx) {
        int root = idx / 2;

        if (idx == 1 || heap[root] > heap[idx])
            return idx;

        if (heap[root] < heap[idx]) {
            int tmp = heap[idx];
            heap[idx] = heap[root];
            heap[idx / 2] = tmp;
        }
        return siftUp(heap, idx / 2);
    }
}
