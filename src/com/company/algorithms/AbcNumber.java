package com.company.algorithms;

/**
 * Created by Yevhen on 19.06.2016.
 */
/*
Дано рядок отриманий шляхом заміни цифр 0,1,2,...,9 певного числа на букви a,b,c,..,j.
Необхідно повернути початкове число маючи конвертований рядок.
Наприклад:
Для "bcd" повернути 123
 */
public class AbcNumber {
    public int convert(String num) throws IllegalArgumentException {
        int result = 0;
        for(char c: num.toCharArray()) {
            if (Character.isLetter(c)) {
                int number = Character.getNumericValue(c)-10;
                if (number >= 0 && number <=9) {
                    result *= 10;
                    result += number;
                } else {
                    throw new IllegalArgumentException(Character.toString(c));
                }
            } else {
                throw new IllegalArgumentException(Character.toString(c));
            }
        }

        return result;
    }
}
