package com.company.algorithms;

/**
 * Created by Yevhen on 19.06.2016.
 */
/*
Дано арифметичний вираз у вигляді Польського Інверсного запису.
Необхідно обчислити значення виразу і повернути його.

Вираз складається лише з цілих чисел і операцій +, -, *, /. Гарантується, що результат також ціне число.
 */
import java.util.ArrayDeque;

public class ReversePolishNotation {
    private static final char DELIMITER_SYMBOL = ' ';
    private static final char ADD_OPERATION_SIGN = '+';
    private static final char SUB_OPERATION_SIGN = '-';
    private static final char MUL_OPERATION_SIGN = '*';
    private static final char DIV_OPERATION_SIGN = '/';

    public int evaluate(String expression) {
        String validOperation =
                new String (new char[] {ADD_OPERATION_SIGN, SUB_OPERATION_SIGN,
                        MUL_OPERATION_SIGN, DIV_OPERATION_SIGN});
        ArrayDeque<Integer> arrayDeque = new ArrayDeque<>();

        int currentNumber = 0;
        boolean numberIsPresent = false;
        for (char item: (expression.trim() + " ").toCharArray()) {
            if (item == DELIMITER_SYMBOL) {
                // Store "last number" to stack
                if (numberIsPresent) {
                    arrayDeque.push(currentNumber);
                }
                // Start new symbol sequence
                currentNumber = 0;
                // To the next item in the reverse polish notation contained in the
                // "expression" parameter
                continue;
            }
            else {
                // Check data correctness
                numberIsPresent = Character.isDigit(item);
                if (numberIsPresent) {
                    currentNumber = currentNumber * 10 + Character.getNumericValue(item);
                    // To the next item in the reverse polish notation contained in the
                    // "expression" parameter
                    continue;
                } else {
                    // Check validation of the operation codes
                    if (validOperation.indexOf(item) >= 0) {
                        Integer secondOperand = arrayDeque.poll();
                        Integer firstOperand = arrayDeque.poll();
                        boolean doContinuation =(firstOperand != null) && (secondOperand != null);
                        if(doContinuation) {
                            int result = 0; // The initialisation here is needed just to "simplify of the "steady" logic" below
                            switch (item) {
                                case ADD_OPERATION_SIGN:
                                    result = firstOperand + secondOperand;
                                    break;
                                case SUB_OPERATION_SIGN:
                                    result = firstOperand - secondOperand;
                                    break;
                                case MUL_OPERATION_SIGN:
                                    result = firstOperand * secondOperand;
                                    break;
                                case DIV_OPERATION_SIGN:
                                    result = firstOperand / secondOperand;
                                    break;
                                default:
                                    // It could be seemed unnecessary but "such an approach" is
                                    // useful at least for the stability of the code
                                    doContinuation = false;
                                    break;
                            }
                            if (doContinuation) {
                                arrayDeque.push(result);
                                // To the next item in the reverse polish notation contained
                                // in the "expression" parameter
                                continue;
                            }
                        }
                    }
                }
            }

            // If we are here, then the data contained in the "expression" parameter is inconsistent
            throw new IllegalArgumentException(expression);
        }

        // Get result from stack
        return arrayDeque.poll();
    }
}
