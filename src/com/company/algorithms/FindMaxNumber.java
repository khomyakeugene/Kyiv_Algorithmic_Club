package com.company.algorithms;

/**
 * Created by Yevhen on 19.06.2016.
 */
/*
Знайти максимальне число в масиві.
Гарантується, що масив завжди не пустий.
 */
public class FindMaxNumber {
    public int max(int[] input) {
        int max = input[0];
        for(int member: input) {
            if (member > max) {
                max = member;
            }
        }

        return max;
    }
}
