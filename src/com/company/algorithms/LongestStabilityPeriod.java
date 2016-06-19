package com.company.algorithms;

/**
 * Created by Yevhen on 19.06.2016.
 */
/*
Дано масив чисел в якому знаходяться значення ВВП за кожен місяць в мільярдах доларів США.
Необхідно знайти найдовший період стабільності.
Період стабільності - період часу де всі значення ВВП попарно відрізняються один від одного максимум на 1.
Повернути кількість місяців.
 */
public  class LongestStabilityPeriod {
    public final static int STABILITY_PERIOD_DIFFERENCE_CRITERION = 1;

    public int count(int[] gdp) {
        int result = 0;
        int previousLongestStabilityPeriod = -1;
        if (gdp.length > 0) {
            int monthGDP;
            int minMonthGDP = gdp[0];
            int maxMonthGDP = gdp[0];
            int newBoundaryValueType = 0;
            result = 1;
            for (int i = 1; i < gdp.length; i++) {
                monthGDP = gdp[i];
                // Possibly - re-init boundary values
                if (monthGDP > maxMonthGDP) {
                    maxMonthGDP = monthGDP;
                    newBoundaryValueType = 1;
                } else {
                    if (monthGDP < minMonthGDP) {
                        minMonthGDP = monthGDP;
                        newBoundaryValueType = -1;
                    } else {
                        newBoundaryValueType = 0;
                    }
                }
                // New period?
                if (maxMonthGDP - minMonthGDP > STABILITY_PERIOD_DIFFERENCE_CRITERION) {
                    // Possibly, store "current period" as longest
                    if (result > previousLongestStabilityPeriod) {
                        previousLongestStabilityPeriod = result;
                    }
                    int j = i - 1;
                    for (result = 1; j > 0 && Math.abs(monthGDP - gdp[j]) <= STABILITY_PERIOD_DIFFERENCE_CRITERION; j--) {
                        result++;
                    }
                    j++;
                    // What is new boundary type?
                    if (newBoundaryValueType == 1) {
                        // New min value
                        minMonthGDP = gdp[j];
                    } else {
                        // New max value
                        maxMonthGDP = gdp[j];
                    }
                } else {
                    // Continue current period
                    result++;
                }
            }
        }

        return (result > previousLongestStabilityPeriod) ? result : previousLongestStabilityPeriod;
    }
}