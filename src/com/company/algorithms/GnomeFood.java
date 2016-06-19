package com.company.algorithms;

/**
 * Created by Yevhen on 19.06.2016.
 */
/*
В країні гномів прийнято, що більша порція їжі дістається вищому гному.
Дано два мисиви int[] довжиною N, з висотами гномів в першому і кілограмами їжі в другому.
Для кожного i-го гнома треба знайти j-ту порцію їжі і записати в результат j-індекси.
Приклад:
[5, 7, 6, 9, 4] - гноми
[8, 5, 6, 2, 3] - порції
Найвищому гному з висотою 9 дістається найбільша порція 8 з індексом 0.
Другий за висотою гном (7), отримує другу завбільшки порцію (6) з індексом 2, і т.д.
Найнищий (4) отримує найменшу порцію 2 (індекс 3)
Резульнат
[4, 2, 1, 0, 3]
 */
import java.util.Arrays;

public class GnomeFood {
    private final static String ARRAYS_LENGTH_INEQUALITY_PATTERN =
            "Source data arrays lengths are unequal: gnames.length = %d, portions.length = %d";

    public int[] find(int[] gnames, int[] portions) {
        if (gnames.length != portions.length) {
            throw new IllegalArgumentException(String.format(ARRAYS_LENGTH_INEQUALITY_PATTERN,
                    gnames.length, portions.length));
        }

        // "Internal changeable" copies
        int[] internalGnamesCopy = Arrays.copyOf(gnames, gnames.length);
        int[] internalPortionsCopy = Arrays.copyOf(portions, portions.length);

        int[] result = new int[gnames.length];
        int maxGNamesIndex;
        int bigPortionIndex;
        for (int gname : gnames) {
            // Search max from "unprocessed" data
            maxGNamesIndex = 0;
            bigPortionIndex = 0;
            for (int j = 0; j < gnames.length; j++) {
                if (internalGnamesCopy[j] > internalGnamesCopy[maxGNamesIndex]) {
                    maxGNamesIndex = j;
                }
                if (internalPortionsCopy[j] > internalPortionsCopy[bigPortionIndex]) {
                    bigPortionIndex = j;
                }
            }

            // Fix current "big portion"
            result[maxGNamesIndex] = bigPortionIndex;
            // "Clear" data which was already used
            internalGnamesCopy[maxGNamesIndex] = Integer.MIN_VALUE;
            internalPortionsCopy[bigPortionIndex] = Integer.MIN_VALUE;
        }

        return result;
    }
}