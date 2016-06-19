package com.company.algorithms;

/**
 * Created by Yevhen on 19.06.2016.
 */
/*
Дано рядок. Перевірити чи містить він всі букви англійського алфавіту a-zA-Z.
 */
import java.util.HashSet;
import java.util.Set;

public class Alphabet {
    private static final int NUMBER_OF_LETTERS = 26;
    private static final char FIRST_UPPER_LETTER = 'A';

    private void addCharSequenceToSet(Set<Character> set, char firstChar, int numberOfChar) {
        for (int i = 0; i < numberOfChar; i++) {
            set.add((char)(firstChar + i));
        }
    }

    public boolean check(String input) {
        HashSet<Character> hashSet = new HashSet<>();
        addCharSequenceToSet(hashSet, FIRST_UPPER_LETTER, NUMBER_OF_LETTERS);

        for(char c: input.toCharArray()) {
            hashSet.remove(Character.toUpperCase(c));
        }

        return hashSet.isEmpty();
    }
}