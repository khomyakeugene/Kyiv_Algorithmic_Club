package com.company.algorithms;

/**
 * Created by Yevhen on 19.06.2016.
 */
/*
Для даного числа поміняти всі біти місцями відносно центра.
Наприклад:
Для 2 (10) повернути 1073741824 (01000000000000000000000000000000).
 */
public class ReverseBits {
    public int reverse(int input) {
        int result = 0;
        int bitQuantity = Integer.SIZE;
        for (int i = 0; i < bitQuantity; i++) {
            result |= ((input & (1 << i)) >>> i) << (bitQuantity - i - 1);
        }

        return result;
    }
}
