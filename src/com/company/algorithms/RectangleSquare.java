package com.company.algorithms;

/**
 * Created by Yevhen on 19.06.2016.
 */
/*
Знайти площу яку займають N прямокутників на площині.
У всіх прямокутників одна зі сторін лежить на осі абсцис (X).
Дано три масива довжиною N.
В першому масиві Х координата нижньої-лівої вершини і-го прямокутника ,
висота в другому, ширина в третьому. Всі значення невід'ємні.
Приклад:
X - [0, 0]
H - [20, 10]
W - [10, 20]
Відповідь - 300.
 */
public class RectangleSquare {
    private final static String ARRAYS_LENGTH_INEQUALITY_PATTERN =
            "Source data arrays lengths are unequal: x.length = %d, h.length = %d, w.length = %d";

    private int max(int[] source) {
        int result = Integer.MIN_VALUE;

        for (int item: source) {
            if (item > result) {
                result = item;
            }
        }

        return result;
    }

    private int min(int[] source) {
        int result = Integer.MAX_VALUE;

        for (int item: source) {
            if (item < result) {
                result = item;
            }
        }

        return result;
    }

    public int measure(int[] x, int[] h, int[] w) {
        int result = 0;

        // Check data consistency: control source data arrays length equality
        if (x.length != h.length || x.length != w.length) {
            throw new IllegalArgumentException(String.format(ARRAYS_LENGTH_INEQUALITY_PATTERN,
                    x.length, h.length, w.length));
        }

        // Calc height of the "all rectangle area"
        int areaHeight = max(h);
        // Calc width of the "all rectangle area"
        int minX = min(x);
        int rectangleWidth;
        int areaWidth = 0;
        for (int i = 0; i < x.length; i++) {
            rectangleWidth = x[i] + w[i] - minX;
            if (rectangleWidth > areaWidth) {
                areaWidth = rectangleWidth;
            }
        }
        // Allocate "all rectangle area"
        int area[][] = new int[areaHeight][areaWidth];

        // Through all rectangles - mark all covered "points" as 1
        for (int i = 0; i < x.length; i++) {
            // Evaluate rectangle number "i"
            for (int j = 0; j < h[i]; j++) {
                for (int k = 0; k < w[i]; k++) {
                    area[j][k+x[i]-minX] |= 1;
                }
            }
        }

        // Summarize all covered "points"
        for (int[] line: area) {
            for (int point: line) {
                result += point;
            }
        }

        return result;
    }
}