package com.company.algorithms;

/**
 * Created by Yevhen on 19.06.2016.
 */
/*
Знайти і повернути перший унікальний символ в рядку.
Якщо всі символи повторюються, повернути null
 */
import java.util.HashSet;

public class FirstUniqueCharacter {

    public Character find(String s) {
        Character result = null;

        HashSet<Character> hashSet = new HashSet<>();
        HashSet<Character>  notUniqueChars = new HashSet<>();

        char[] chars = s.toCharArray();
        for(char c: chars) {
            if (!hashSet.add(c)) {
                notUniqueChars.add(c);
            }
        }

        for(char c: chars) {
            if (!notUniqueChars.contains(c)) {
                result = c;
                break;
            }
        }

        return result;
    }
}