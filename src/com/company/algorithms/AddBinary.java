package com.company.algorithms;

/**
 * Created by Yevhen on 19.06.2016.
 */
/*
Додайте два беззнакових числа, що передаються як рядки.
"101" + "100" = "1001"
 */
import java.util.Arrays;

class AddBinary {
    private char[] stringToCharsWithLeadingZero(String str, int zeroCount) {
        char [] leadingZero = new char[zeroCount];
        Arrays.fill(leadingZero, '0');

        return (new String(leadingZero) + str).toCharArray();
    }

    String add(String a, String b) throws IllegalArgumentException {
        int aLength = a.length();
        int bLength = b.length();
        boolean aLongerThanB = aLength > bLength;
        char[] resultChars = stringToCharsWithLeadingZero(aLongerThanB ? a : b, 1);
        char[] itemChars = stringToCharsWithLeadingZero(aLongerThanB ? b : a, Math.abs(aLength - bLength)+1);

        int resultBit, itemBit, sum;
        int transfer = 0;
        for (int i = resultChars.length-1; i >= 0; i--) {
            resultBit = resultChars[i] - '0';
            if (resultBit != 0 && resultBit != 1) {
                throw new IllegalArgumentException(new String(resultChars));
            }
            itemBit = itemChars[i] - '0';
            if (itemBit != 0 && itemBit != 1) {
                throw new IllegalArgumentException(new String(itemChars));
            }
            sum = resultBit + itemBit + transfer;
            resultChars[i] = (char)((sum & 1) + '0');
            transfer = (sum & 2) >> 1;
        }

        return new String((resultChars[0] == '0') ? Arrays.copyOfRange(resultChars, 1, resultChars.length): resultChars);
    }
}
