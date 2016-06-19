package com.company.algorithms;

/**
 * Created by Yevhen on 19.06.2016.
 */
/*
Дано ланки трьох кольорів: білого і 1 м завдовжки, жовтого - 2 м і червоного - 3 м.
Скількома способами можна зібрати, з них, ланцюжок довжиною  N.
Кількість наявних ланок кожного кольору вважати бескінечною.
Середня
 */
import java.util.Arrays;

public class ColorChain {
    public static final String SOME_ALGORITHM_MISTAKE_MESSAGE =
            "There should be some logical mistake in the permutation calculation algorithm implementation!";

    public static final int WHITE_SECTION_LENGTH = 1;
    public static final int YELLOW_SECTION_LENGTH = 2;
    public static final int RED_SECTION_LENGTH = 3;
    public static final int[] sections = {WHITE_SECTION_LENGTH, YELLOW_SECTION_LENGTH, RED_SECTION_LENGTH};

    private int variantCount;
    private int[] sectionCounter = new int[sections.length];

    public static int sum(int[] data) {
        int result = 0;
        for (int number: data) {
            result += number;
        }

        return result;
    }

    public static long mul(int[] data) {
        long result = 1;
        for (int number: data) {
            result *= number;
        }

        return result;
    }

    public static int GreatestCommonDivisor(int a, int b) {
        int minNumber = (a < b) ? a : b;
        int maxNumber = (a > b) ? a : b;

        if (minNumber == 0) return maxNumber;
        int x = maxNumber % minNumber;

        return GreatestCommonDivisor(minNumber, x);
    }

    public static int[] deleteNumberFromArray(int[] data, int number) {
        int notNullDataLength = 0;
        int [] notNullData = new int[data.length];
        for (int i = 0; i < data.length; i++) {
            if (data[i] != number) {
                notNullData[notNullDataLength++] = data[i];
            }
        }

        return Arrays.copyOf(notNullData, notNullDataLength);
    }


    public static int[] deleteZeroFromArray(int[] data) {
        return deleteNumberFromArray(data, 0);
    }

    public static int[] deleteOneFromArray(int[] data) {
        return deleteNumberFromArray(data, 1);
    }


    public static int permutationCount(int[] inputElements) throws Exception {
        // The classical formula is
        // (sum(repetitionElements)! / (repetitionElements[0]! * repetitionElement[1]! * ...)
        // But because of the huge values of factorials itself, let's try to optimize this algorithm
        int result = 1;

        // Delete empty repetition elements
        int[] repetitionElements = deleteZeroFromArray(inputElements);
        //It is necessary to calc number of permutations?
        if (repetitionElements.length > 1) {
            int elemSum = sum(repetitionElements);
            int[] numeratorComponents = new int[elemSum];
            for (int i = 0; i < numeratorComponents.length; i++) {
                numeratorComponents[i] = i + 1;
            }

            // Reduce some factors: factorials parts
            for (int i = 0; i < repetitionElements.length; i++) {
                while (repetitionElements[i] > 1 && repetitionElements[i] == numeratorComponents[repetitionElements[i] - 1]) {
                    numeratorComponents[repetitionElements[i] - 1] = 1;
                    repetitionElements[i]--;
                }
            }

            // Decompose factorials in denominator to multipliers
            int[] denominatorComponents = new int[elemSum - repetitionElements.length];
            for (int i = 0, j = 0; i < repetitionElements.length; i++) {
                if (repetitionElements[i] > 1) {
                    for (int k = 2; k <= repetitionElements[i]; k++) {
                        denominatorComponents[j++] = k;
                    }
                }
            }
            // Delete empty repetition elements
            denominatorComponents = deleteZeroFromArray(denominatorComponents);

            // Try to reduce by common divisors
            for (int i = 0; i < numeratorComponents.length; i++) {
                if (numeratorComponents[i] > 1) {
                    do {
                        boolean dataWasChanged = false;
                        for (int j = 0; j < denominatorComponents.length; j++) {
                            if (denominatorComponents[j] > 1) {
                                if (numeratorComponents[i] == denominatorComponents[j]) {
                                    numeratorComponents[i] = 1;
                                    denominatorComponents[j] = 1;
                                    break;
                                } else {
                                    if (numeratorComponents[i] > 1) {
                                        int gcd = GreatestCommonDivisor(numeratorComponents[i], denominatorComponents[j]);
                                        if (gcd > 1) {
                                            numeratorComponents[i] /= gcd;
                                            denominatorComponents[j] /= gcd;
                                            dataWasChanged = true;
                                        }
                                    }
                                }
                            }
                        }
                        if (!dataWasChanged) {
                            break;
                        }
                    } while (numeratorComponents[i] > 1);
                }
            }
            // Although it is unnecessarily, but let's "clear" <numeratorComponents>!
            numeratorComponents = deleteOneFromArray(numeratorComponents);

            // If the implementation of permutation calculation algorithm is performed right, the denominator should not equal 1
            if (deleteOneFromArray(denominatorComponents).length > 0) {
                throw new Exception(SOME_ALGORITHM_MISTAKE_MESSAGE);
            }

            result = (int)mul(numeratorComponents);
        }

        return result;
    }


    private void tryToMatchSum(int number, int sections[], int thisSectionIndex) throws Exception {
        // Some sort of protection
        if (thisSectionIndex < sections.length) {
            int thisPiece = sections[thisSectionIndex];
            int maxCount = (int)(number / thisPiece);
            int nextSectionIndex = thisSectionIndex + 1;
            int numberRemainder = number;
            // Clear "current" section counter
            sectionCounter[thisSectionIndex] = 0;
            for (int i = 0; i <= maxCount && numberRemainder > 0; i++) {
                if (nextSectionIndex < sections.length) {
                    tryToMatchSum(numberRemainder, sections, nextSectionIndex);
                }
                numberRemainder -= thisPiece;
                if (numberRemainder >= 0) {
                    sectionCounter[thisSectionIndex]++;
                    if (numberRemainder == 0) {
                        // Successful attempt - accumulate
                        variantCount += permutationCount(sectionCounter);
                    }
                }
            }
        }
    }

    public int count(int N) throws Exception {
        // Init counters
        variantCount = 0;
        Arrays.fill(sectionCounter, 0);

        tryToMatchSum(N, sections, 0);

        return variantCount;
    }
}

