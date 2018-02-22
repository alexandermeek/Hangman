package com;

/**
 * This class represents an exception raised when you have no
 * guesses left.
 * @author Alex Meek
 */
public class NoGuessesLeftException extends IllegalStateException{
    public NoGuessesLeftException(String message) {
        super(message);
    }
}

