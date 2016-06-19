package com.company.algorithms;

/**
 * Created by Yevhen on 19.06.2016.
 */
/*
Знайти середнє значення двох цілих чисел.
Приклади:
average( 4, 6 ) = 5
average( -4, -7 ) = -5
average( -4, 7 ) = 1
 */
public class AverageNumber {
    public int average(int a, int b) {

        return (int)(((long)a + (long)b) / 2);
    }
}
