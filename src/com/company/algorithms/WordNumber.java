package com.company.algorithms;

/**
 * Created by Yevhen on 19.06.2016.
 */
/*
Дано рядок. Порахувати кількість слів.
Словом вважається послідовність символів англійського алфавіту [a-zA-Z].

Алгоритм повинен працювати за O(N) часу, тому RegExp використовувати не можна.
Середня
 */
public class WordNumber {
    public int count(String input) {
        int result = 0;

        char[] inputChars = input.toCharArray();
        for (int wordStart = 0; wordStart < inputChars.length; ) {
            // To start of word
            while (wordStart < inputChars.length && !Character.isLetter(inputChars[wordStart])) {
                wordStart++;
            }
            // Is it the beginning of another word?
            if (wordStart < inputChars.length) result++;
            // To end of word
            while (wordStart < inputChars.length && Character.isLetter(inputChars[wordStart])) {
                wordStart++;
            }
        }

        return result;
    }
}