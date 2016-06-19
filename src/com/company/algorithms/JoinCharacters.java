package com.company.algorithms;

/**
 * Created by Yevhen on 19.06.2016.
 */
/*
З'єднати масив символів в число.
Приклад:
Для [ '1', '2', '3'] повернути 123
 */
public class JoinCharacters {
    public int join(char[] input) throws IllegalArgumentException {
        int result = 0;

        for(char c: input) {
            if (Character.isDigit(c)) {
                result *= 10;
                result += Character.getNumericValue(c);
            } else {
                throw new IllegalArgumentException(new Character(c).toString());
            }
        }

        return result;
    }
}
