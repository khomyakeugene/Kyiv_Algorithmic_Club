package com.company.algorithms;

/**
 * Created by Yevhen on 19.06.2016.
 */

/*
Реалізуйте структуру даних  - Бінарна Купа (Binary Heap).
Конструктор проймає один параметр size.
Методи insert(int) що працює за O(logN) і poll(),
який видаляє і повертає максимальне число з купи і також працює за O(logN).
*/

import java.util.Arrays;

public class BinaryHeap {
    private int[] binaryHeap;
    private int heapSize;

    public BinaryHeap(int size) {
        binaryHeap = new int[size];
        heapSize = 0;

    }

    public int[] getBinaryHeap() {
        return Arrays.copyOf(binaryHeap, getHeapSize());
    }

    public int getHeapSize() {
        return heapSize;
    }

    public void insert(int val) {
        binaryHeap[heapSize++] = val;

        int i = heapSize - 1;
        int parent = (i - 1) / 2;

        while (i > 0 && binaryHeap[parent] < binaryHeap[i]) {
            int temp = binaryHeap[i];
            binaryHeap[i] = binaryHeap[parent];
            binaryHeap[parent] = temp;

            i = parent;
            parent = (i - 1) / 2;
        }
    }

    public void heapify(int i) {
        int leftChild;
        int rightChild;
        int largestChild;

        while (true) {
            leftChild = 2 * i + 1;
            rightChild = 2 * i + 2;
            largestChild = i;

            if (leftChild < heapSize && binaryHeap[leftChild] > binaryHeap[largestChild]) {
                largestChild = leftChild;
            }
            if (rightChild < heapSize && binaryHeap[rightChild] > binaryHeap[largestChild]) {
                largestChild = rightChild;
            }
            if (largestChild == i) {
                break;
            }

            int temp = binaryHeap[i];
            binaryHeap[i] = binaryHeap[largestChild];
            binaryHeap[largestChild] = temp;
            i = largestChild;
        }
    }

    public int poll() {
        int result = binaryHeap[0];
        binaryHeap[0] = binaryHeap[heapSize - 1];
        heapSize--;
        heapify(0);

        return result;
    }
}
