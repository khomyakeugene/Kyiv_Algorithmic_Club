package com.company.algorithms;

/**
 * Created by Yevhen on 19.06.2016.
 */
/*
Дано 2 числа в системі числення з основою 36. Будь-яка цифра може бути в межах [0-9a-z].
Повернути суму чисел, також в системі 36.
Наприклад:
"9" + "1" = "a"
"z" + "1" = "10"
Легка
 */
import java.util.Arrays;

public class AddNumberBase36 {
    public static final int CALCULATION_BASE = 36;

    private char[] stringToCharsWithLeadingZero(String str, int zeroCount) {
        char[] leadingZero = new char[zeroCount];
        Arrays.fill(leadingZero, '0');

        return (new String(leadingZero) + str).toCharArray();
    }


    private char getCharValue(int numericValue) {
        char result = ' ';

        if (numericValue >= 0 && numericValue <= 9) {
            result = (char)(numericValue + '0');
        } else {
            if (numericValue >= 10 && numericValue <= 35) {
                result = (char)(numericValue-10 + 'a');
            }
        }

        return result;
    }

    String add(String a, String b) throws IllegalArgumentException {
        int aLength = a.length();
        int bLength = b.length();
        boolean aLongerThanB = aLength > bLength;
        char[] resultChars = stringToCharsWithLeadingZero(aLongerThanB ? a : b, 1);
        char[] itemChars = stringToCharsWithLeadingZero(aLongerThanB ? b : a, Math.abs(aLength - bLength)+1);

        int resultValue, itemValue, sum, currentNumeric;
        int transfer = 0;
        for (int i = resultChars.length-1; i >= 0; i--) {
            resultValue = Character.getNumericValue(resultChars[i]);
            if (resultValue < 0) {
                throw new IllegalArgumentException(new String(resultChars));
            }
            itemValue = Character.getNumericValue(itemChars[i]);
            if (itemValue < 0) {
                throw new IllegalArgumentException(new String(itemChars));
            }
            sum = resultValue + itemValue + transfer;
            currentNumeric = sum % CALCULATION_BASE;
            resultChars[i] = getCharValue(currentNumeric);
            transfer = (sum - currentNumeric) / CALCULATION_BASE;
        }

        return new String((resultChars[0] == '0') ? Arrays.copyOfRange(resultChars, 1, resultChars.length): resultChars);
    }
}
