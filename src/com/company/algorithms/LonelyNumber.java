package com.company.algorithms;

/**
 * Created by Yevhen on 19.06.2016.
 */
/*
Дано масив цілих чисел. Всі числа в масиві повторюються рівно 5 разів і лише одне число не має дублікатів.
Знайти число, що неповторюється.
Складність рішення по часу повинна бути O(N).
 */
public class LonelyNumber {
    private static final String INVALID_REPEAT_COUNT_PATTERN = "Error: some number(s) repeat(s) not 1 but not %d times!";
    private static final int DEFAULT_REPEAT_COUNT = 5;

    private int find(int[] input, int repeatCount) throws IllegalArgumentException {
        int bits[]= new int[Integer.SIZE];

        int mask = 1;
        for (int i = 0; i < Integer.SIZE; i++) {
            bits[i] = 0;
            for (int number: input) {
                bits[i] += (number & mask) >>> i;
            }
            mask <<= 1;
        }

        int result = 0;
        for (int i = 0; i < Integer.SIZE; i++) {
            int remainder = bits[i] % repeatCount;
            if (remainder != 0 && remainder != 1) {
                throw new IllegalArgumentException(String.format(INVALID_REPEAT_COUNT_PATTERN, repeatCount));
            }

            result += remainder << i;
        }

        return result;
    }

    public int find(int[] input) throws IllegalArgumentException {
        return find(input, DEFAULT_REPEAT_COUNT);
    }

}