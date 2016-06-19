package com.company.algorithms;

/**
 * Created by Yevhen on 19.06.2016.
 */
/*
Дано число. Треба замінити i-й біт замінити на нуль.
Наприклад:
для числа 6 в бінарному вигляді 110,
замінивши 2-й біт на нуль отримаємо 100 тобто 4.
1<= i <= 32
 */
public class SetZero {
    public int set(int num, int i) {
        int mask = 0;

        int bitNumber = i-1;
        for (int j = 0; j < 32; j++) {
            mask |= (j == bitNumber) ? 0 : (1 << j);
        }

        return (num & mask);
    }
}
