package com.company.algorithms;

/**
 * Created by Yevhen on 19.06.2016.
 */
/*
Дано рядок в csv форматі.
Роздільник - кома, лапки - подвійні.
Необхідно повернути таблицюList<List<String>> записів.
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CSVParser {
    private static final char END_OF_LINE = '\n';
    private static final char COLUMN_DELIMITER = ',';
    private static final char QUOTATION_MARK = '"';

    private class StringParser {
        List<String> parse(String input, char delimiterCharacter, boolean retainQuotationMark) {
            ArrayList<String> result = new ArrayList<>();

            int fieldLength = 0;
            boolean oddQuotationMark = false;
            char[] sourceData = input.toCharArray();
            char[] targetField = new char[sourceData.length];
            for (int i = 0; i < sourceData.length; i++) {
                if (sourceData[i] == QUOTATION_MARK) {
                    // Quotation mark "counter"
                    oddQuotationMark = !oddQuotationMark;
                    if (retainQuotationMark) {
                        // Store quotation mark  as a part of current field
                        targetField[fieldLength++] = sourceData[i];
                    } else {
                        // If the previous character is also quotation mark, then consider double quotation makr as
                        // a single one, independently if the quotation mark is odd or even
                        if (i > 0 && sourceData[i-1] == QUOTATION_MARK) {
                            targetField[fieldLength++] = sourceData[i];
                        }
                    }
                } else {
                    if (sourceData[i] == delimiterCharacter) {
                        if (oddQuotationMark) {
                            // Delimiter is a part of the current field
                            targetField[fieldLength++] = sourceData[i];
                        } else {
                            // Finish current field
                            result.add(new String(Arrays.copyOf(targetField,fieldLength)));
                            // Start new field
                            fieldLength = 0;
                        }
                    } else {
                        // Store other character as a part of current field
                        targetField[fieldLength++] = sourceData[i];
                    }
                }
            }
            // Store last field
            result.add(new String(Arrays.copyOf(targetField,fieldLength)));

            return result;
        }
    }


    public List<List<String>> parse(String input) {
        ArrayList<List<String>> result = new ArrayList<>();

        StringParser stringParser = new StringParser();
          /*
          Cannot understand why (maybe, just because of using Java 7?), but "testing environment" of "codegym.in.ua"
          considers construction
          stream().forEach(l -> result.add(stringParser.parse(l, COLUMN_DELIMITER, false)));
          as a "illegal start of expression"
          stringParser.parse(input, END_OF_LINE, true).
                  stream().forEach(l -> result.add(stringParser.parse(l, COLUMN_DELIMITER, false)));
          Because of it try to use "old fashion" simple for()
          */
        // External loop - by END_OF_LINE retaining quotation marks,
        for (String line: stringParser.parse(input, END_OF_LINE, true)) {
            // internal loop - by COLUMN_DELIMITER cutting quotation marks
            result.add(stringParser.parse(line, COLUMN_DELIMITER, false));
        }

        return result;
    }
}
