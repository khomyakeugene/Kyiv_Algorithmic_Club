package com.company.algorithms;

/**
 * Created by Yevhen on 19.06.2016.
 */
/*
Знайти середнє арифметике двух додатніх чисел.
Наприклад:
average( 2, 4 ) = 3
average( 4, 7 ) = 5
 */
public class PositiveAverageNumber {
    public int average(int a, int b) {
        return (a >>> 1) + (b >>> 1) + (((a & 1) + (b & 1)) >>> 1);
    }
}