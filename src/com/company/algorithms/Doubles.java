package com.company.algorithms;

/**
 * Created by Yevhen on 19.06.2016.
 */
/*
Дано рядок, що представляє певне чило. Необхідно повернути число.
Якщо це неможливо, повернути null
Наприклад:
"-2.e10"
"+.3"
".e2" повертає null
 */

public class Doubles {
    private static class StateMachine {
        static final char PLUS_SIGN_CHARACTER = '+';
        static final char MINUS_SIGN_CHARACTER = '-';
        static final char SCIENTIFIC_NOTATION_LOWER_CASE_CHARACTER = 'e';
        static final char SCIENTIFIC_NOTATION_UPPER_CASE_CHARACTER = 'E';
        static final char DECIMAL_POINT_CHARACTER = '.';

        private State state;
        private static Double result;
        private static int signFactor = 1;
        private static double fractionFactor = 1.0;
        private static int powerFactor = 0;
        private static int scientificNotationSignFactor = 1;
        private static boolean decimalPointIsFound = false;
        private static boolean scientificNotationIsFound = false;
        private static boolean anyDigitIsFound = true;

        StateMachine() {
            startNewParsing();
        }

        void startNewParsing() {
            result = 0.0;
            signFactor = 1;
            powerFactor = 0;
            scientificNotationSignFactor = 1;
            decimalPointIsFound = false;
            scientificNotationIsFound = false;
            anyDigitIsFound = true;

            this.state = State.INITIAL;
        }

        private enum State {
            INITIAL {
                @Override
                State next(char character) {
                    State nextState = determineStateByCharacter(character);
                    switch (nextState) {
                        case NUMBER:
                            accumulateDigital(character);
                            break;

                        case PLUS_SIGN:
                        case MINUS_SIGN:
                        case DECIMAL_POINT:
                        case SCIENTIFIC_NOTATION:
                            break;

                        default:
                            nextState = INVALID_FINAL;
                    }

                    return nextState;
                }
            },

            PLUS_SIGN {
                @Override
                State next(char character) {
                    State nextState = determineStateByCharacter(character);
                    switch (nextState) {
                        case NUMBER:
                            accumulateDigital(character);
                            break;

                        default:
                            nextState = INVALID_FINAL;
                    }

                    return nextState;
                }
            },

            MINUS_SIGN {
                @Override
                State next(char character) {
                    signFactor = -1;
                    State nextState = determineStateByCharacter(character);
                    switch (nextState) {
                        case NUMBER:
                            accumulateDigital(character);
                            break;

                        case DECIMAL_POINT:
                            break;

                        default:
                            nextState = INVALID_FINAL;
                    }

                    return nextState;
                }
            },

            NUMBER {
                @Override
                State next(char character) {
                    State nextState = determineStateByCharacter(character);
                    switch (nextState) {
                        case NUMBER:
                            accumulateDigital(character);
                            break;

                        case DECIMAL_POINT:
                        case SCIENTIFIC_NOTATION:
                            break;

                        default:
                            nextState = INVALID_FINAL;
                    }

                    return nextState;
                }
            },

            DECIMAL_POINT {
                @Override
                State next(char character) {
                    State nextState;
                    if (decimalPointIsFound) {
                        nextState = INVALID_FINAL;
                    } else {
                        decimalPointIsFound = true;
                        nextState = determineStateByCharacter(character);
                        switch (nextState) {
                            case NUMBER:
                                accumulateDigital(character);
                                break;

                            case SCIENTIFIC_NOTATION:
                                break;

                            default:
                                nextState = INVALID_FINAL;
                        }
                    }

                    return nextState;
                }
            },

            SCIENTIFIC_NOTATION {
                @Override
                State next(char character) {
                    State nextState;
                    if (scientificNotationIsFound || anyDigitIsFound) {
                        nextState = INVALID_FINAL;
                    } else {
                        scientificNotationIsFound = true;
                        nextState = determineStateByCharacter(character);
                        switch (nextState) {
                            case NUMBER:
                                accumulateDigital(character);
                                break;

                            default:
                                nextState = INVALID_FINAL;
                        }
                    }

                    return nextState;
                }
            },

            INVALID_FINAL {
                @Override
                State next(char character) {
                    return this;
                }
            };

            abstract State next(char character);

            State determineStateByCharacter(char character) {
                return Character.isDigit(character) ? NUMBER :
                        (character == PLUS_SIGN_CHARACTER) ? PLUS_SIGN :
                                (character == MINUS_SIGN_CHARACTER) ? MINUS_SIGN :
                                        (character == DECIMAL_POINT_CHARACTER) ? DECIMAL_POINT :
                                                (character == SCIENTIFIC_NOTATION_UPPER_CASE_CHARACTER ||
                                                        character ==SCIENTIFIC_NOTATION_LOWER_CASE_CHARACTER) ?
                                                        SCIENTIFIC_NOTATION :
                                                        INVALID_FINAL;
            }
        }

        private static void enhanceFractionFactor() {
            fractionFactor *= 0.1;
        }

        void next(char character) {
            state = state.next(character);
        }

        private static void accumulateDigital(int number) {
            anyDigitIsFound = false;

            if (scientificNotationIsFound) {
                powerFactor = powerFactor * 10 + number;
            } else {
                if (decimalPointIsFound) {
                    enhanceFractionFactor();
                    result += number * fractionFactor;
                } else {
                    result = result * 10 + number;
                }
            }
        }

        private static void accumulateDigital(char character) {
            accumulateDigital(Character.getNumericValue(character));
        }

        public Double getResult() {
            return (state == State.INVALID_FINAL) || anyDigitIsFound ?
                    null : result * signFactor * Math.pow(10, scientificNotationSignFactor * powerFactor);
        }
    }


    public Double parse(String s) {
        StateMachine stateMachine = new StateMachine();
        for (char character: s.trim().toCharArray()) {
            stateMachine.next(character);
        }

        return stateMachine.getResult();
    }
}
