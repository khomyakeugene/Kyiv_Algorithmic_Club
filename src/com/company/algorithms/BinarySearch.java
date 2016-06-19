package com.company.algorithms;

/**
 * Created by Yevhen on 19.06.2016.
 */
/*
Дано відсортований масив унікальних чисел.
Необхідно реалізувати функцію пошуку target числа,
що працює за час O( log(N) ).
Якщо число існує в масиві - повернути індекс, в іншому випадку -1 - (insertionIndex).
Де insertionIndex це індекс куди можна було б вставити target.
 */

public class BinarySearch {
    public int find(int[] array, int target) {
        int low = 0;
        int high = array.length - 1;
        int targetIndex = 0;

        while (low <= high) {
            // Use "targetIndex = low + ((high - low)>>>1)" in place of "(low + high) / 2" just because of the risk
            // of the int-overflow in case if "low" and "high" contain "huge" values
            targetIndex = low + ((high - low)>>>1);
            if (array[targetIndex] > target) {
                high = targetIndex - 1;
            } else {
                if (array[targetIndex] < target) {
                    low = targetIndex + 1;
                } else {
                    return targetIndex; // target is found;
                }
            }
        }

        // Additional check for the "boundary conditions"
        if (array.length > 0 && targetIndex == (array.length - 1) && target > array[targetIndex]) {
            targetIndex++;
        }

        return -1 - targetIndex;
    }
}