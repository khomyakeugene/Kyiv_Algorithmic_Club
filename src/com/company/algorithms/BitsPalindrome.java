package com.company.algorithms;

/**
 * Created by Yevhen on 19.06.2016.
 */
/*
Перевірити чи являється бінарне представлення числа поліндромом.
 */
public class BitsPalindrome {
    private int bitValue(int numberValue, int bitNumber) {
        return  ((numberValue & (1 << bitNumber)) >>> bitNumber);
    }


    public boolean isPalindrome(int input) {
        boolean result = true;
        int bitQuantity = Integer.SIZE;
        int bitHalfQuantity = bitQuantity>>>1;
        for (int i = 0; (i < bitHalfQuantity) && result; i++) {
            result = bitValue(input, i) == bitValue(input, bitQuantity-i-1);
        }

        return result;
    }
}