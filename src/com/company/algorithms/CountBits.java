package com.company.algorithms;

/**
 * Created by Yevhen on 19.06.2016.
 */
/*
Для даного числа порахувати кількість біт.
Наприклад:
Для числа 13 в бінарному вигляді 1101 повернути 3.
 */
public class CountBits {
    public int count(int num) {
        int result = 0;

        for (int i = 0; i < Integer.SIZE; i++) {
            result += (num >>> i) & 1;
        }

        return result;
    }
}
