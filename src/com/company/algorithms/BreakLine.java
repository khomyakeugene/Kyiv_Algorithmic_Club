package com.company.algorithms;

/**
 * Created by Yevhen on 19.06.2016.
 */
/*
Дано рядок без символів переходу рядка \n.
Також дано максимальну довжину рядка width.
Необхідно розбити рядок символами переходу рядка так, щоб кодна частина була не більша ніж width.
Слово може бути розбити на частини тільки в тому випадку якщо воно довше ніж width.
 */
import java.util.Arrays;

public class BreakLine {
    private static final char END_OF_LINE = '\n';

    private static boolean isWordCharacter(char character) {
        return Character.isLetter(character);
    }

    public String format(String input, int width) {
        char[] sourceChars = input.toCharArray();
        char[] resultChars = new char[sourceChars.length<<1];

        int linePosition = 0;
        int resultLength = 0;
        for (int i = 0; i < sourceChars.length; i++) {
            // Check if there should be the end of line
            if (linePosition >= width) {
                if (isWordCharacter(sourceChars[i])) {
                    // Back search of the first non-word symbol
                    int j = i - 1;
                    int k = 0;
                    for (; j >= 0 && k < width && isWordCharacter(sourceChars[j]); k++, j--);
                    // Non-word character was found?
                    if (j >= 0 && k < width) {
                        // To first symbol of word
                        j++;
                        // Correct <resultLength>
                        resultLength -= i - j;
                        // Fix "restored" "current position"
                        i = j;
                    }
                }
                // Trim possible end-of-line spaces
                int j = resultLength - 1;
                for (; j >= 0 && Character.isSpaceChar(resultChars[j]); j--);
                resultLength = j + 1;
                // Fix end-of-line sign
                resultChars[resultLength++] = END_OF_LINE;
                // Start of a new line
                linePosition = 0;
            }

            // Fix current character
            resultChars[resultLength++] = sourceChars[i];
            // Increment length of current line
            linePosition++;
        }

        return new String(Arrays.copyOf(resultChars, resultLength));
    }
}