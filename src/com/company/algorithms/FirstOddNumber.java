package com.company.algorithms;

/**
 * Created by Yevhen on 19.06.2016.
 */
/*
Знайти перше непарне число і повернути його індекс.
Якщо такого немає, повернути -1
 */
public class FirstOddNumber {
    public int find(int[] input) {
        int result = -1;
        for(int i = 0; i < input.length; i++) {
            if ((input[i] % 2) == 1) {
                result = i;
                break;
            }
        }

        return result;
    }
}
