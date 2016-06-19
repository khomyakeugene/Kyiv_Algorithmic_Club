package com.company.algorithms;

/**
 * Created by Yevhen on 19.06.2016.
 */
/*
Обчислити суму цифр числа.
Наприклад сума цифр числа 123 дорівнює  6.
 */
public class SumDigits {
    public int sum(int number) {
        int remainder, result;
        if (number == Integer.MIN_VALUE) {
            remainder = Integer.MAX_VALUE;
            result = 1;
        } else {
            remainder = Math.abs(number);
            result = 0;
        }

        while (remainder != 0) {
            result += remainder % 10;
            remainder /= 10;
        }

        return result;
    }
}
